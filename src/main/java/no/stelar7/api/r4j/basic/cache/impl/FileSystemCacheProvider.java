package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Utils;
import com.google.gson.stream.JsonWriter;
import org.slf4j.*;

import java.io.*;
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
            Files.createDirectories(storePath.getParent());
            writeObject(storePath, type, storeItem);
        } catch (IOException e)
        {
            logger.warn("Unable to store data in the cache ({})", type, e);
        }
    }
    
    /**
     * Only classes of this library and the JDK can be read from files in the old (java serialization) format
     */
    private static final ObjectInputFilter SERIALIZATION_FILTER = ObjectInputFilter.Config.createFilter("no.stelar7.api.r4j.**;java.**;com.google.gson.**;!*");

    /**
     * Turns the item into the bytes to store.
     * <p>
     * The item is stored as json when it is what the endpoint returns and reading the json back gives an equal object.
     * Json does not break when a field is added to a class, and reading it can not run code.
     * Everything else (ie. the lists and maps built from the static data) is stored with java serialization, as before.
     */
    private byte[] serialize(URLEndpoint type, Object obj) throws IOException
    {
        if (obj.getClass() == type.getType())
        {
            try
            {
                String json = toJson(obj);
                if (obj.equals(Utils.getGson().fromJson(json, type.getType())))
                {
                    return json.getBytes(StandardCharsets.UTF_8);
                }
            } catch (RuntimeException e)
            {
                logger.debug("{} can not be stored as json, using java serialization", type, e);
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos))
        {
            out.writeObject(obj);
        }

        return bos.toByteArray();
    }

    private String toJson(Object obj)
    {
        // written without the pretty printing of the shared gson instance, to keep the files small
        StringWriter writer = new StringWriter();
        Utils.getGson().toJson(obj, obj.getClass(), new JsonWriter(writer));
        return writer.toString();
    }

    private Object deserialize(URLEndpoint type, byte[] data) throws IOException, ClassNotFoundException
    {
        // java serialization always starts with the bytes AC ED, which is never the start of json
        boolean serialized = data.length >= 2 && (data[0] & 0xFF) == 0xAC && (data[1] & 0xFF) == 0xED;
        if (!serialized)
        {
            try
            {
                return Utils.getGson().fromJson(new String(data, StandardCharsets.UTF_8), type.getType());
            } catch (RuntimeException e)
            {
                throw new IOException("Unreadable json in the cache", e);
            }
        }

        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data)))
        {
            in.setObjectInputFilter(SERIALIZATION_FILTER);
            return in.readObject();
        }
    }

    private void writeObject(Path path, URLEndpoint type, Object obj) throws IOException
    {
        byte[] data = serialize(type, obj);

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
            Object o = deserialize(type, Files.readAllBytes(filepath));
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
