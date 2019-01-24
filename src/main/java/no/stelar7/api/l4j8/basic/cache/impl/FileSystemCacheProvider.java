package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;

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
    private       CacheLifetimeHint hints;
    
    private ScheduledExecutorService clearService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?>       clearTask;
    
    public FileSystemCacheProvider(Path pathToFiles, int ttl)
    {
        setTimeToLiveGlobal(ttl);
        home = pathToFiles != null ? pathToFiles : Paths.get(".", "l4j8cache").normalize();
    }
    
    public FileSystemCacheProvider(Path pathToFiles)
    {
        this(pathToFiles, CacheProvider.TTL_INFINITY);
    }
    
    public FileSystemCacheProvider(int ttl)
    {
        this(null, ttl);
    }
    
    public FileSystemCacheProvider()
    {
        this(CacheProvider.LOCATION_DEFAULT, CacheProvider.TTL_INFINITY);
    }
    
    @Override
    public void setTimeToLiveGlobal(long timeToLive)
    {
        this.timeToLive = timeToLive;
        
        if (timeToLive > 0)
        {
            clearTask = clearService.scheduleAtFixedRate(this::clearOldCache, timeToLive, timeToLive, TimeUnit.SECONDS);
        } else
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
            storePath = storePath.resolve(datum != null ? datum.toString() : "null");
        }
        
        return storePath;
    }
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
        try
        {
            // inject api key so cache still works in v4
            List<Object> pathData = new ArrayList<>(Arrays.asList(obj));
            pathData.add(DataCall.getCredentials().getBaseAPIKey());
            pathData.remove(0);
            
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
        
        if (!Files.exists(filepath))
        {
            return Optional.empty();
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
                    if (!Files.exists(p))
                    {
                        return;
                    }
                    
                    BasicFileAttributes attributes = Files.readAttributes(p, BasicFileAttributes.class);
                    long                life       = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() - attributes.lastAccessTime().toInstant().toEpochMilli();
                    
                    if (timeToLive != CacheProvider.TTL_USE_HINTS)
                    {
                        if (timeToLive < life)
                        {
                            // no point in deleting the folders..
                            if (Files.isDirectory(p))
                            {
                                return;
                            }
                            
                            Files.deleteIfExists(p);
                        }
                    } else
                    {
                        Path folder = p;
                        while (!folder.getParent().equals(home))
                        {
                            folder = p.getParent();
                        }
                        
                        URLEndpoint endpoint     = URLEndpoint.valueOf(folder.toFile().toString());
                        long        expectedLife = hints.get(endpoint);
                        
                        if (expectedLife < life)
                        {
                            // no point in deleting the folders..
                            if (Files.isDirectory(p))
                            {
                                return;
                            }
                            
                            Files.deleteIfExists(p);
                        }
                        
                    }
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
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return timeToLive;
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
