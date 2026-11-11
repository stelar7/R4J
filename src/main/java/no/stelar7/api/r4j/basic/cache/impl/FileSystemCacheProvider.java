package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Utils;
import org.slf4j.*;

import java.io.*;
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
    
    private final ScheduledExecutorService clearService = Executors.newSingleThreadScheduledExecutor(r -> {
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

        if (timeToLive > 0)
        {
            clearTask = clearService.scheduleAtFixedRate(this::clearOldCache, timeToLive, timeToLive, TimeUnit.MILLISECONDS);
        }
    }

    private String getKeyCombination()
    {
        // inject api key so cache still works in v4
        return DataCall.getCredentials() == null ? "STATIC_DATA" : DataCall.getCredentials().getUniqueKeyCombination();
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
            Files.createDirectories(storePath.getParent());
            writeObject(storePath, storeItem);
        } catch (IOException e)
        {
            logger.warn("Unable to store data in the cache ({})", type, e);
        }
    }
    
    private void writeObject(Path path, Object obj) throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos))
        {
            out.writeObject(obj);
        }

        // Files.write creates or replaces the file, and sets the modified time the lifetime is counted from
        Files.write(path, bos.toByteArray());
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
        
        try (ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(filepath)); ObjectInput in = new ObjectInputStream(bis))
        {
            logger.debug("Loaded data from cache ({} {} {})", this.getClass().getName(), type, vals.toString());
            Object o = in.readObject();
            
            // if the object we are trying to load is not valid, remove it
            if (o == null)
            {
                Files.deleteIfExists(filepath);
                return Optional.empty();
            }
            
            return Optional.of(o);
            
        } catch (IOException | ClassNotFoundException e)
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
            paths.sorted(Comparator.reverseOrder()).forEach(p -> {
                try
                {
                    clearPath(p);
                } catch (IOException | RuntimeException e)
                {
                    // silent fail
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
