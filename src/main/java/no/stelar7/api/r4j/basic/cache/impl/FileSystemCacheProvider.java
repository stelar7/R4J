package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Utils;
import org.slf4j.*;

import java.io.*;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class FileSystemCacheProvider implements CacheProvider
{
    private static final Logger logger = LoggerFactory.getLogger(FileSystemCacheProvider.class);
    
    private final Path              home;
    private       long              timeToLive;
    private       CacheLifetimeHint hints = CacheLifetimeHint.DEFAULTS;
    
    private volatile R4JContext context = R4JContext.getDefault();

    /**
     * How often expired files are removed, at most. A shorter time to live cleans up more often.
     */
    private static final long MAX_CLEANUP_INTERVAL_MS = TimeUnit.HOURS.toMillis(1);

    // one thread cleans up for all file caches
    private static final ScheduledExecutorService clearService = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "R4J-FileSystemCache-cleaner");
        t.setDaemon(true);
        return t;
    });

    private       ScheduledFuture<?>       clearTask;
    
    public FileSystemCacheProvider(Path pathToFiles, int ttl)
    {
        setTimeToLiveGlobal(ttl);
        home = pathToFiles != null ? pathToFiles : Paths.get(".", "l4j8cache").normalize();
    }
    
    public FileSystemCacheProvider(Path pathToFiles)
    {
        this(pathToFiles, CacheProvider.TTL_USE_HINTS);
    }
    
    public FileSystemCacheProvider(int ttl)
    {
        this(null, ttl);
    }
    
    public FileSystemCacheProvider()
    {
        this(CacheProvider.LOCATION_DEFAULT, CacheProvider.TTL_USE_HINTS);
    }
    
    @Override
    public synchronized void setTimeToLiveGlobal(long timeToLive)
    {
        this.timeToLive = timeToLive;

        if (clearTask != null)
        {
            clearTask.cancel(false);
            clearTask = null;
        }

        if (timeToLive == CacheProvider.TTL_INFINITY)
        {
            return;
        }

        // Expired files are never returned, but without this they would stay on disk until someone asks for them again.
        // The task does not keep the cache alive: once the cache is no longer used, the task stops itself.
        long                                   interval = timeToLive > 0 ? Math.max(1000, Math.min(timeToLive, MAX_CLEANUP_INTERVAL_MS)) : MAX_CLEANUP_INTERVAL_MS;
        WeakReference<FileSystemCacheProvider> self     = new WeakReference<>(this);
        clearTask = clearService.scheduleWithFixedDelay(() -> {
            FileSystemCacheProvider cache = self.get();
            if (cache == null)
            {
                // an exception ends a scheduled task
                throw new CancellationException("the cache is no longer in use");
            }

            try
            {
                cache.clearOldCache();
            } catch (RuntimeException e)
            {
                logger.warn("Unable to clear old data from the cache", e);
            }
        }, interval, interval, TimeUnit.MILLISECONDS);
    }

    private String getKeyCombination()
    {
        // inject api key so cache still works in v4
        APICredentials credentials = context.getCredentials();
        return credentials == null ? "STATIC_DATA" : credentials.getUniqueKeyCombination();
    }
    
    @Override
    public void setContext(R4JContext context)
    {
        this.context = context;
    }

    @Override
    public void setTimeToLive(CacheLifetimeHint hints)
    {
        this.hints = hints;
    }
    
    @Override
    public void store(URLEndpoint type, Map<String, Object> obj)
    {
        try
        {
            if (!obj.containsKey("value"))
            {
                throw new IllegalArgumentException("Invalid cache insert!");
            }

            Object storeItem = obj.get("value");

            // if the object we are trying to store is not valid, dont store it.
            if (storeItem == null)
            {
                return;
            }

            Path storePath = resolvePath(type, getKeyValues(obj));
            try
            {
                Files.createDirectories(storePath.getParent());
                writeObject(storePath, type, storeItem);
            } catch (NoSuchFileException e)
            {
                // a cleanup removed the (still empty) folder before the file was written
                Files.createDirectories(storePath.getParent());
                writeObject(storePath, type, storeItem);
            }
        } catch (IOException e)
        {
            logger.warn("Unable to store data in the cache ({})", type, e);
        }
    }
    
    private void writeObject(Path path, URLEndpoint type, Object obj) throws IOException
    {
        byte[] data = CacheSerializer.serialize(type, obj);

        // Written to a temporary file and then moved in place, so another thread or process sharing the folder never reads half a file.
        // The move also sets the modified time the lifetime is counted from.
        Path temp = Files.createTempFile(path.getParent(), "r4j", ".tmp");
        try
        {
            Files.write(temp, data);
            try
            {
                Files.move(temp, path, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException e)
            {
                Files.move(temp, path, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally
        {
            Files.deleteIfExists(temp);
        }
    }

    /**
     * The values identifying the item in the cache; everything but the item itself, followed by the api key combination
     */
    private List<Object> getKeyValues(Map<String, Object> obj)
    {
        List<Object> vals = new ArrayList<>();
        obj.forEach((k, v) -> {
            if (!"value".equals(k))
            {
                vals.add(v);
            }
        });

        vals.add(getKeyCombination());
        return vals;
    }
    
    
    private Path resolvePath(URLEndpoint type, List<Object> obj)
    {
        Path storePath = home.resolve(type.toString());
        
        List<Object> pathData = new ArrayList<>(obj);
        
        for (Object datum : pathData)
        {
            storePath = storePath.resolve(datum != null ? Utils.normalizeString(datum.toString()) : "null");
        }
        
        return storePath;
    }
    
    @Override
    public void update(URLEndpoint type, Map<String, Object> obj)
    {
        try
        {
            if (!obj.containsKey("value"))
            {
                throw new IllegalArgumentException("Invalid cache insert!");
            }

            // if the object we are trying to store is not valid, dont store it.
            if (obj.get("value") == null)
            {
                return;
            }

            Path storePath = resolvePath(type, getKeyValues(obj));
            if (Files.exists(storePath))
            {
                Files.setLastModifiedTime(storePath, FileTime.from(Instant.now()));
            } else
            {
                store(type, obj);
            }
        } catch (IOException e)
        {
            logger.warn("Unable to update data in the cache ({})", type, e);
        }
    }

    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> obj)
    {
        List<Object> vals     = getKeyValues(obj);
        Path         filepath = resolvePath(type, vals);

        try
        {
            clearPath(filepath);
        } catch (IOException e)
        {
            logger.warn("Unable to check the age of the cached data ({})", type, e);
        }
        
        if (!Files.exists(filepath))
        {
            return Optional.empty();
        }
        
        try
        {
            Object o = CacheSerializer.deserialize(type, Files.readAllBytes(filepath));
            logger.debug("Loaded data from cache ({} {} {})", this.getClass().getName(), type, vals);

            // if the object we are trying to load is not valid, remove it
            if (o == null)
            {
                Files.deleteIfExists(filepath);
                return Optional.empty();
            }

            return Optional.of(o);

        } catch (NoSuchFileException e)
        {
            // removed by another thread or process between the check and the read
            return Optional.empty();
        } catch (IOException | ClassNotFoundException | RuntimeException e)
        {
            logger.info("Data in cache is from an older version of the library, removing it");
            try
            {
                Files.deleteIfExists(filepath);
            } catch (IOException ignored)
            {
                // it is replaced on the next store
            }
            return Optional.empty();
        }
    }
    
    @Override
    public void clear(URLEndpoint type, Map<String, Object> filter)
    {
        try
        {
            Path pathToWalk = home.resolve(type.toString());
            
            for (Object o : filter.values())
            {
                pathToWalk = pathToWalk.resolve(Utils.normalizeString(o.toString()));
            }
            
            if (!Files.exists(pathToWalk))
            {
                return;
            }
            
            try (Stream<Path> paths = Files.walk(pathToWalk))
            {
                paths.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            }
        } catch (IOException e)
        {
            logger.warn("Unable to clear the cache ({})", type, e);
        }
    }
    
    @Override
    public void clearOldCache()
    {
        if (timeToLive == CacheProvider.TTL_INFINITY)
        {
            return;
        }
        
        if (!Files.exists(home))
        {
            return;
        }

        try (Stream<Path> paths = Files.walk(home))
        {
            // deepest first, so a folder is looked at after everything in it
            paths.sorted(Comparator.reverseOrder()).forEach(p -> {
                try
                {
                    clearPath(p);

                    if (!p.equals(home) && Files.isDirectory(p))
                    {
                        // only succeeds if the folder is empty; without this every player and match ever cached leaves a folder behind
                        Files.delete(p);
                    }
                } catch (IOException | RuntimeException e)
                {
                    // a folder that is not empty, or something that is in use; it is looked at again next time
                }
            });
        } catch (IOException e)
        {
            logger.warn("Unable to clear old data from the cache", e);
        }
    }
    
    private void clearPath(Path p) throws IOException
    {
        // items never expire, and there is no point in deleting the folders..
        if (timeToLive == CacheProvider.TTL_INFINITY || !Files.exists(p) || p.equals(home) || Files.isDirectory(p))
        {
            return;
        }

        // The age is counted from when the item was stored (or refreshed with update()).
        // The access time is not used, as many filesystems do not track it, and where it is tracked reading an item would keep it alive forever
        long life = System.currentTimeMillis() - Files.getLastModifiedTime(p).toMillis();
        
        if (timeToLive != CacheProvider.TTL_USE_HINTS)
        {
            
            if (timeToLive < life)
            {
                // no point in deleting the folders..
                if (Files.isDirectory(p))
                {
                    return;
                }
                
                logger.debug("Data in cache is outdated, deleting...");
                Files.deleteIfExists(p);
            }
        } else
        {
            Path folder = p;
            while (!folder.getParent().equals(home))
            {
                folder = folder.getParent();
            }
            
            URLEndpoint endpoint     = URLEndpoint.valueOf(folder.getFileName().toString());
            long        expectedLife = hints.get(endpoint);
            
            if (expectedLife < life)
            {
                // no point in deleting the folders..
                if (Files.isDirectory(p))
                {
                    return;
                }
                
                logger.debug("Data in cache is outdated, deleting...");
                Files.deleteIfExists(p);
            }
            
        }
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return timeToLive == TTL_USE_HINTS ? hints.get(type) : timeToLive;
    }
    
    @Override
    public long getSize(URLEndpoint type, Map<String, Object> filter)
    {
        try
        {
            Path pathToWalk = home.resolve(type.toString());
            
            for (Object o : filter.values())
            {
                pathToWalk = pathToWalk.resolve(Utils.normalizeString(o.toString()));
            }
            
            if (!Files.exists(pathToWalk))
            {
                return 0;
            }

            try (Stream<Path> paths = Files.walk(pathToWalk))
            {
                return paths.filter(p -> !Files.isDirectory(p)).count();
            }
        } catch (IOException e)
        {
            logger.warn("Unable to get the size of the cache ({})", type, e);
            return -1;
        }
    }
}
