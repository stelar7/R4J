package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.pojo.match.Match;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import java.time.*;
import java.util.*;
import java.util.Map.Entry;

public class MemoryCacheProvider extends CacheProvider
{
    private Map<Summoner, LocalDateTime> summoners = new HashMap<>();
    private Map<Match, LocalDateTime>    matches   = new HashMap<>();
    
    /**
     * Creates a memory cache, where items expire after ttl seconds
     *
     * @param ttl the amount of time to keep cached items (in seconds)
     */
    public MemoryCacheProvider(long ttl)
    {
        setTimeToLive(ttl);
    }
    
    @Override
    public void store(URLEndpoint type, Object obj)
    {
        switch (type)
        {
            case V3_SUMMONER_BY_ACCOUNT:
            case V3_SUMMONER_BY_NAME:
            case V3_SUMMONER_BY_ID:
                summoners.put((Summoner) obj, LocalDateTime.now());
                break;
            case V3_MATCH:
                matches.put((Match) obj, LocalDateTime.now());
                break;
            default:
                break;
        }
    }
    
    
    @Override
    public void update(URLEndpoint type, Object obj)
    {
        store(type, obj);
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        switch (type)
        {
            case V3_SUMMONER_BY_ACCOUNT:
                return summoners.keySet().stream().filter(s -> s.getPlatform().equals(data[0])).filter(s -> data[1].equals(s.getAccountId())).findFirst();
            case V3_SUMMONER_BY_NAME:
                return summoners.keySet().stream().filter(s -> s.getPlatform().equals(data[0])).filter(s -> data[1].equals(s.getName())).findFirst();
            case V3_SUMMONER_BY_ID:
                return summoners.keySet().stream().filter(s -> s.getPlatform().equals(data[0])).filter(s -> data[1].equals(s.getSummonerId())).findFirst();
            case V3_MATCH:
                return matches.keySet().stream().filter(m -> m.getPlatform().equals(data[0])).filter(m -> data[1].equals(m.getMatchId())).findFirst();
            default:
                break;
        }
        
        return Optional.empty();
    }
    
    @Override
    public void clear(URLEndpoint type)
    {
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
        clearOldCache(summoners);
        clearOldCache(matches);
    }
    
    @Override
    public long getTimeToLive()
    {
        return timeToLive;
    }
    
    @Override
    public long getSize()
    {
        long size = 0;
        size += summoners.size();
        size += matches.size();
        return size;
    }
    
    private void clearOldCache(Map<?, LocalDateTime> data)
    {
        List<Entry<?, LocalDateTime>> list = new ArrayList<>(data.entrySet());
        list.sort(Comparator.comparing(Entry::getValue));
        
        for (Entry<?, LocalDateTime> entry : list)
        {
            long life = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() - entry.getValue().toInstant(ZoneOffset.UTC).toEpochMilli();
            
            if (timeToLive < life)
            {
                data.remove(entry.getKey());
            }
        }
    }
}
