package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.util.*;

public class FileSystemCacheProvider extends CacheProvider
{
    
    private final Path home;
    
    public FileSystemCacheProvider(Path pathToFiles, int ttl)
    {
        setTimeToLive(ttl);
        home = pathToFiles != null ? pathToFiles : Paths.get(".", "l4j8cache").normalize();
    }
    
    public FileSystemCacheProvider()
    {
        this(CacheProvider.LOCATION_DEFAULT, CacheProvider.TTL_INFINITY);
    }
    
    @Override
    public void store(URLEndpoint type, Object... obj)
    {
        try
        {
            List<Object> pathData = new ArrayList<>(Arrays.asList(obj));
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
        Collections.reverse(pathData);
        
        for (Object datum : pathData)
        {
            storePath = storePath.resolve(datum != null ? datum.toString() : "null");
        }
        
        return storePath;
    }
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
        store(type, obj);
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        List<Object> pathData = new ArrayList<>(Arrays.asList(data));
        Path         filepath = resolvePath(type, pathData);
        
        if (!Files.exists(filepath))
        {
            return Optional.empty();
        }
        
        try (ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(filepath)); ObjectInput in = new ObjectInputStream(bis))
        {
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
            
            Files.walk(pathToWalk).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void clearOldCache()
    {
        try
        {
            Files.walk(home).sorted(Comparator.reverseOrder()).forEach(p -> {
                try
                {
                    BasicFileAttributes attributes = Files.readAttributes(p, BasicFileAttributes.class);
                    long                life       = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() - attributes.lastAccessTime().toInstant().toEpochMilli();
                    if (timeToLive < life)
                    {
                        // no point in deleting the folders..
                        if (Files.isDirectory(p))
                        {
                            return;
                        }
                        
                        Files.deleteIfExists(p);
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
    public long getTimeToLive()
    {
        return timeToLive;
    }
    
    @Override
    public long getSize()
    {
        try
        {
            return Files.size(home);
        } catch (IOException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
