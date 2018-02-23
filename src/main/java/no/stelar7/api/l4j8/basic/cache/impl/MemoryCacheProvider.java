package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.match.Match;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import java.time.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class MemoryCacheProvider implements CacheProvider
{
    private Map<Summoner, LocalDateTime> summoners = new HashMap<>();
    private Map<Match, LocalDateTime>    matches   = new HashMap<>();
    
    private ScheduledExecutorService clearService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> clearTask;
    private long               timeToLive;
    private CacheLifetimeHint  hints;
    
    /**
     * Creates a memory cache, where items expire after ttl seconds
     *
     * @param ttl the amount of time to keep cached items (in seconds)
     */
    public MemoryCacheProvider(long ttl)
    {
        setTimeToLiveGlobal(ttl);
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
        switch (type)
        {
            case V3_SUMMONER_BY_ACCOUNT:
            case V3_SUMMONER_BY_NAME:
            case V3_SUMMONER_BY_ID:
                summoners.put((Summoner) obj[0], LocalDateTime.now());
                break;
            case V3_MATCH:
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
        Object platform = data[0];
        if (type == URLEndpoint.V3_SUMMONER_BY_ACCOUNT || type == URLEndpoint.V3_SUMMONER_BY_NAME || type == URLEndpoint.V3_SUMMONER_BY_ID)
        {
            DataCall.getLogLevel().printIf(LogLevel.INFO, "Loaded data from cache");
            Object accountId    = data[1];
            Object summonerId   = data[2];
            Object summonerName = data[3];
            
            Stream<Summoner> sums = summoners.keySet().stream().filter(s -> s.getPlatform().equals(platform));
            if (type == URLEndpoint.V3_SUMMONER_BY_ID)
            {
                sums.filter(s -> summonerId.equals(s.getSummonerId()));
            }
            if (type == URLEndpoint.V3_SUMMONER_BY_ACCOUNT)
            {
                sums.filter(s -> accountId.equals(s.getAccountId()));
            }
            if (type == URLEndpoint.V3_SUMMONER_BY_NAME)
            {
                sums.filter(s -> summonerName.equals(s.getName()));
            }
            return sums.findFirst();
        }
        
        if (type == URLEndpoint.V3_MATCH)
        {
            DataCall.getLogLevel().printIf(LogLevel.INFO, "Loaded data from cache");
            Object matchId = data[1];
            return matches.keySet().stream()
                          .filter(m -> m.getPlatform().equals(platform))
                          .filter(m -> matchId.equals(m.getMatchId()))
                          .findFirst();
        }
        
        return Optional.empty();
    }
    
    @Override
    public void clear(URLEndpoint type, Object... filter)
    {
        // TODO: respect filter
        switch (type)
        {
            case V3_SUMMONER_BY_ACCOUNT:
            case V3_SUMMONER_BY_NAME:
            case V3_SUMMONER_BY_ID:
                summoners.clear();
                break;
            case V3_MATCH:
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
        
        clearOldCache(URLEndpoint.V3_SUMMONER_BY_ACCOUNT, summoners);
        clearOldCache(URLEndpoint.V3_SUMMONER_BY_NAME, summoners);
        clearOldCache(URLEndpoint.V3_SUMMONER_BY_ID, summoners);
        clearOldCache(URLEndpoint.V3_MATCH, matches);
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return timeToLive;
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
