package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.Utils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class FileSystemCacheProvider implements CacheProvider
{
    
    private final Path              home;
    private       long              timeToLive;
    private       CacheLifetimeHint hints = CacheLifetimeHint.DEFAULTS;
    
    private ScheduledExecutorService clearService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?>       clearTask;
    
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
    public void store(URLEndpoint type, Object... obj)
    {
        try
        {
            // if the object we are trying to store is not valid, dont store it.
            if (obj[0] == null)
            {
                return;
            }
            
            // inject api key so cache still works in v4
            List<Object> pathData = new ArrayList<>(Arrays.asList(obj));
            pathData.add(DataCall.getCredentials() == null ? "STATIC_DATA" : DataCall.getCredentials().getBaseAPIKey());
            pathData.remove(0);
            
            Path storePath = resolvePath(type, pathData);
            Files.createDirectories(storePath.getParent());
            try
            {
                Files.createFile(storePath);
            } catch (FileAlreadyExistsException e)
            {
                // ignore it
            }
            writeObject(storePath, obj[0]);
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
        
        for (Object datum : pathData)
        {
            storePath = storePath.resolve(datum != null ? Utils.normalizeString(datum.toString()) : "null");
        }
        
        return storePath;
    }
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
        try
        {
            // if the object we are trying to store is not valid, dont store it.
            if (obj[0] == null)
            {
                return;
            }
            
            List<Object> pathData = new ArrayList<>(Arrays.asList(obj));
            pathData.add(DataCall.getCredentials().getBaseAPIKey());
            
            Path storePath = resolvePath(type, pathData);
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
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        // inject api key so cache still works in v4
        List<Object> pathData = new ArrayList<>(Arrays.asList(data));
        pathData.add(DataCall.getCredentials() == null ? "STATIC_DATA" : DataCall.getCredentials().getBaseAPIKey());
        Path filepath = resolvePath(type, pathData);
        
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
        } else
        {
            // need to find a way to refetch the data for this...?
            // update(type, data);
        }
        
        try (ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(filepath)); ObjectInput in = new ObjectInputStream(bis))
        {
            DataCall.getLogLevel().printIf(LogLevel.INFO, String.format("Loaded data from cache (%s %s %s)", this.getClass().getName(), type, Arrays.toString(data)));
            Object o = in.readObject();
            return Optional.of(o);
            
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    @Override
    public void clear(URLEndpoint type, Object... filter)
    {
        try
        {
            Path pathToWalk = home.resolve(type.toString());
            
            for (Object o : filter)
            {
                pathToWalk = pathToWalk.resolve(o.toString());
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
                    e.printStackTrace();
                }
            });
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void clearPath(Path p) throws IOException
    {
        if (!Files.exists(p))
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
                
                DataCall.getLogLevel().printIf(LogLevel.INFO, "Data in cache is outdated, deleting then re-fetching");
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
                
                DataCall.getLogLevel().printIf(LogLevel.INFO, "Data in cache is outdated, deleting then re-fetching");
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
    public long getSize(URLEndpoint type)
    {
        try
        {
            return Files.size(home.resolve(type.toString()));
        } catch (IOException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
