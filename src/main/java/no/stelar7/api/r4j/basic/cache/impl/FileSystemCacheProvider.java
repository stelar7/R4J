package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Utils;
import org.slf4j.*;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class FileSystemCacheProvider implements CacheProvider
{
    private static final Logger logger = LoggerFactory.getLogger(FileSystemCacheProvider.class);
    
    private final Path              home;
    private       long              timeToLive;
    private       CacheLifetimeHint hints = CacheLifetimeHint.DEFAULTS;
    
    private final ScheduledExecutorService clearService = Executors.newScheduledThreadPool(1);
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
    public void setTimeToLiveGlobal(long timeToLive)
    {
        this.timeToLive = timeToLive;
        
        if (timeToLive > 0)
        {
            clearTask = clearService.scheduleAtFixedRate(this::clearOldCache, timeToLive, timeToLive, TimeUnit.MILLISECONDS);
        } else if (timeToLive == CacheProvider.TTL_INFINITY)
        {
            if (clearTask != null)
            {
                clearTask.cancel(false);
            }
        }
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
                throw new RuntimeException("Invalid cache insert!");
            }
            
            List<Object> vals      = new ArrayList<>(obj.values());
            Object       storeItem = obj.get("value");
            vals.remove(storeItem);
            
            // if the object we are trying to store is not valid, dont store it.
            if (storeItem == null)
            {
                return;
            }
            
            // inject api key so cache still works in v4
            vals.add(0, DataCall.getCredentials() == null ? "STATIC_DATA" : DataCall.getCredentials().getUniqueKeyCombination());
            
            Path storePath = resolvePath(type, vals);
            Files.createDirectories(storePath.getParent());
            try
            {
                Files.createFile(storePath);
            } catch (FileAlreadyExistsException e)
            {
                // ignore it
            }
            writeObject(storePath, storeItem);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void writeObject(Path path, Object obj) throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput          out = new ObjectOutputStream(bos);
        out.writeObject(obj);
        out.flush();
        
        Files.write(path, bos.toByteArray());
        
        bos.close();
    }
    
    
    private Path resolvePath(URLEndpoint type, List<Object> obj)
    {
        Path storePath = home.resolve(type.toString());
        
        List<Object> pathData = new ArrayList<>(obj);
        Collections.reverse(pathData);
        
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
                throw new RuntimeException("Invalid cache insert!");
            }
            
            List<Object> vals      = new ArrayList<>(obj.values());
            Object       storeItem = obj.get("value");
            vals.remove(storeItem);
            
            // if the object we are trying to store is not valid, dont store it.
            if (storeItem == null)
            {
                return;
            }
            
            vals.add(0, DataCall.getCredentials().getUniqueKeyCombination());
            
            Path storePath = resolvePath(type, vals);
            if (Files.exists(storePath))
            {
                Files.setLastModifiedTime(storePath, FileTime.from(Instant.now()));
            } else
            {
                store(type, obj);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> obj)
    {
        List<Object> vals = new ArrayList<>(obj.values());
        
        // inject api key so cache still works in v4
        vals.add(0, DataCall.getCredentials() == null ? "STATIC_DATA" : DataCall.getCredentials().getUniqueKeyCombination());
        Path filepath = resolvePath(type, vals);
        
        try
        {
            clearPath(filepath);
        } catch (IOException e)
        {
            e.printStackTrace();
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
            
            Files.walk(pathToWalk).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void clearOldCache()
    {
        if (timeToLive == CacheProvider.TTL_INFINITY)
        {
            return;
        }
        
        try
        {
            Files.walk(home).sorted(Comparator.reverseOrder()).forEach(p -> {
                try
                {
                    clearPath(p);
                } catch (IOException e)
                {
                    // silent fail
                    // e.printStackTrace();
                }
            });
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void clearPath(Path p) throws IOException
    {
        if (!Files.exists(p) || p.equals(home))
        {
            return;
        }
        
        LocalDateTime accessTime = ((FileTime) Files.readAttributes(p, "lastAccessTime").get("lastAccessTime")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime nowTime    = LocalDateTime.now();
        long          life       = Duration.between(accessTime, nowTime).getSeconds() * 1000;
        
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
            
            return Files.walk(pathToWalk).filter(p -> !Files.isDirectory(p)).count();
        } catch (IOException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
