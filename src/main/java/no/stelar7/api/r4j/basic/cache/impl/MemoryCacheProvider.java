package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.match.lol.Match;
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
    
    private Map<Summoner, LocalDateTime>            summoners = new HashMap<>();
    private Map<Match, LocalDateTime>               matches   = new HashMap<>();
    private Pair<StaticChampionList, LocalDateTime> champions;
    
    private ScheduledExecutorService clearService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?>       clearTask;
    private long                     timeToLive;
    private CacheLifetimeHint        hints        = CacheLifetimeHint.DEFAULTS;
    
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
    public void store(URLEndpoint type, Object... obj)
    {
        // if the object we are trying to store is not valid, dont store it.
        if (obj[0] == null)
        {
            return;
        }
        
        switch (type)
        {
            case V4_SUMMONER_BY_ACCOUNT:
            case V4_SUMMONER_BY_NAME:
            case V4_SUMMONER_BY_ID:
                summoners.put((Summoner) obj[0], LocalDateTime.now());
                break;
            case V4_MATCH:
                matches.put((Match) obj[0], LocalDateTime.now());
                break;
            default:
                break;
        }
    }
    
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
        store(type, obj);
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        Object      platform = data[0];
        Optional<?> opt;
        
        switch (type)
        {
            case V4_SUMMONER_BY_ACCOUNT:
            case V4_SUMMONER_BY_NAME:
            case V4_SUMMONER_BY_ID:
            {
                Object value = data[1];
                
                Stream<Summoner> sums = summoners.keySet().stream().filter(s -> s.getPlatform().equals(platform));
                if (type == URLEndpoint.V4_SUMMONER_BY_ID)
                {
                    sums = sums.filter(s -> value.equals(s.getSummonerId()));
                }
                if (type == URLEndpoint.V4_SUMMONER_BY_ACCOUNT)
                {
                    sums = sums.filter(s -> value.equals(s.getAccountId()));
                }
                if (type == URLEndpoint.V4_SUMMONER_BY_NAME)
                {
                    sums = sums.filter(s -> value.equals(s.getName()));
                }
                
                opt = sums.findFirst();
                break;
            }
            case V4_MATCH:
            {
                Object matchId = data[1];
                
                opt = matches.keySet().stream()
                             .filter(m -> m.getPlatform().equals(platform))
                             .filter(m -> matchId.equals(m.getMatchId()))
                             .findFirst();
                break;
            }
            
            default:
            {
                opt = Optional.empty();
            }
        }
        
        if (opt.isPresent())
        {
            logger.info("Loaded data from cache ({} {} {})", this.getClass().getName(), type, Arrays.toString(data));
        }
        
        return opt;
    }
    
    @Override
    public void clear(URLEndpoint type, Object... filter)
    {
        // TODO: respect filters
        switch (type)
        {
            case V4_SUMMONER_BY_ACCOUNT:
            case V4_SUMMONER_BY_NAME:
            case V4_SUMMONER_BY_ID:
                summoners.clear();
                break;
            case V4_MATCH:
                matches.clear();
                break;
            default:
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
        clearOldCache(URLEndpoint.V4_SUMMONER_BY_NAME, summoners);
        clearOldCache(URLEndpoint.V4_SUMMONER_BY_ID, summoners);
        clearOldCache(URLEndpoint.V4_MATCH, matches);
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return timeToLive == TTL_USE_HINTS ? hints.get(type) : timeToLive;
    }
    
    @Override
    public long getSize(URLEndpoint type)
    {
        long size = 0;
        size += summoners.size();
        size += matches.size();
        return size;
    }
    
    private void clearOldCache(URLEndpoint endpoint, Map<?, LocalDateTime> data)
    {
        List<Entry<?, LocalDateTime>> list = new ArrayList<>(data.entrySet());
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
