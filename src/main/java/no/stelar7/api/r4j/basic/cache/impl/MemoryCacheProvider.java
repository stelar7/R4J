package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampionList;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.slf4j.*;

import java.time.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class MemoryCacheProvider implements CacheProvider
{
    private static final Logger logger = LoggerFactory.getLogger(MemoryCacheProvider.class);
    
    private final Map<Summoner, LocalDateTime>            summoners = new HashMap<>();
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
            case V4_SUMMONER_BY_ACCOUNT:
            case V4_SUMMONER_BY_ID:
                summoners.put((Summoner) obj.get("value"), LocalDateTime.now());
                break;
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
            case V4_SUMMONER_BY_ACCOUNT:
            case V4_SUMMONER_BY_ID:
            {
                Object value = data.getOrDefault("name", data.getOrDefault("id", data.getOrDefault("accountid", data.getOrDefault("puuid", null))));
                
                Stream<Summoner> sums = summoners.keySet().stream().filter(s -> s.getPlatform().equals(platform));
                if (type == URLEndpoint.V4_SUMMONER_BY_ID)
                {
                    sums = sums.filter(s -> value.equals(s.getSummonerId()));
                }
                if (type == URLEndpoint.V4_SUMMONER_BY_ACCOUNT)
                {
                    sums = sums.filter(s -> value.equals(s.getAccountId()));
                }
                
                opt = sums.findFirst();
                break;
            }
            
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
        // TODO: respect filters
        switch (type)
        {
            case V4_SUMMONER_BY_ACCOUNT:
            case V4_SUMMONER_BY_ID:
                summoners.clear();
                break;
        }
    }
    
    @Override
    public void clearOldCache()
    {
        if (timeToLive == CacheProvider.TTL_INFINITY)
        {
            return;
        }
        
        clearOldCache(URLEndpoint.V4_SUMMONER_BY_ACCOUNT, summoners);
        clearOldCache(URLEndpoint.V4_SUMMONER_BY_ID, summoners);
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
        size += summoners.size();
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
