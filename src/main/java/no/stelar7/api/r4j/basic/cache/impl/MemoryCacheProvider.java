package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampionList;
import org.slf4j.*;

import java.time.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

public class MemoryCacheProvider implements CacheProvider
{
    private static final Logger logger = LoggerFactory.getLogger(MemoryCacheProvider.class);
    
    private       Pair<StaticChampionList, LocalDateTime> champions;
    
    private final ScheduledExecutorService clearService = Executors.newScheduledThreadPool(1);
    private       ScheduledFuture<?>       clearTask;
    private       long                     timeToLive;
    private       CacheLifetimeHint        hints        = CacheLifetimeHint.DEFAULTS;
    
    /**
     * Creates a memory cache, where items expire after ttl seconds
     *
     * @param ttl the amount of time to keep cached items (in seconds)
     */
    public MemoryCacheProvider(long ttl)
    {
        setTimeToLiveGlobal(ttl);
    }
    
    public MemoryCacheProvider()
    {
        this(CacheProvider.TTL_USE_HINTS);
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
    public void store(URLEndpoint type, Map<String, Object> obj)
    {
        // if the object we are trying to store is not valid, dont store it.
        if (obj.get("value") == null)
        {
            return;
        }
        
        switch (type)
        {
            default:
                break;
        }
    }
    
    
    @Override
    public void update(URLEndpoint type, Map<String, Object> obj)
    {
        store(type, obj);
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> data)
    {
        Object      platform = data.get("platform");
        Optional<?> opt;
        
        switch (type)
        {
            default:
            {
                opt = Optional.empty();
            }
        }
        
        if (opt.isPresent())
        {
            logger.debug("Loaded data from cache ({} {} {})", this.getClass().getName(), type, data);
        }
        
        return opt;
    }
    
    @Override
    public void clear(URLEndpoint type, Map<String, Object> filter)
    {
        switch (type)
        {
            default:
                break;
        }
    }
    
    @Override
    public void clearOldCache()
    {
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return timeToLive == TTL_USE_HINTS ? hints.get(type) : timeToLive;
    }
    
    @Override
    public long getSize(URLEndpoint type, Map<String, Object> filter)
    {
        long size = 0;
        return size;
    }
    
    private void clearOldCache(URLEndpoint endpoint, Map<?, LocalDateTime> data)
    {
        List<Entry<?, LocalDateTime>> list = new ArrayList<>(data.entrySet());
        
        // cant do that because of a wildcard
        //noinspection RedundantComparatorComparing
        list.sort(Comparator.comparing(Entry::getValue));
        
        for (Entry<?, LocalDateTime> entry : list)
        {
            long life = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() - entry.getValue().toInstant(ZoneOffset.UTC).toEpochMilli();
            if (timeToLive != CacheProvider.TTL_USE_HINTS)
            {
                if (timeToLive < life)
                {
                    data.remove(entry.getKey());
                }
            } else
            {
                long expectedLife = hints.get(endpoint);
                if (expectedLife < life)
                {
                    data.remove(entry.getKey());
                }
            }
        }
    }
}
