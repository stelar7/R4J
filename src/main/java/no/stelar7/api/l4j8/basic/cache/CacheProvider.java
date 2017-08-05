package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.pojo.match.Match;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import java.time.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

public abstract class CacheProvider
{
    
    long timeToLive = -1;
    
    public abstract void store(URLEndpoint clazz, Object obj);
    
    public abstract Optional<?> get(URLEndpoint type, Object... data);
    
    public abstract void clear(URLEndpoint type);
    
    public abstract void clearOldCache();
    
    protected CacheProvider(long timeToLive)
    {
        this.timeToLive = timeToLive;
        
        if (timeToLive > 0)
        {
            ScheduledExecutorService clearService = Executors.newScheduledThreadPool(1);
            clearService.scheduleAtFixedRate(this::clearOldCache, timeToLive, timeToLive, TimeUnit.SECONDS);
        }
    }
    
    public static final CacheProvider EMPTY = new CacheProvider(-1)
    {
        @Override
        public void store(URLEndpoint clazz, Object obj) { /*void cache*/}
        
        @Override
        public Optional<?> get(URLEndpoint type, Object... data)
        {
            return Optional.empty();
        }
        
        @Override
        public void clear(URLEndpoint type)
        {/*void*/}
        
        @Override
        public void clearOldCache()
        {/*void*/}
    };
    
    
    public static final CacheProvider MEMORY = new CacheProvider(3600)
    {
        private Map<Summoner, LocalDateTime> summoners = new HashMap<>();
        private Map<Match, LocalDateTime> matches = new HashMap<>();
        
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
            long livingCount = matches.size();
            
            clearOldCache(summoners);
            clearOldCache(matches);
            
            System.out.println("killed caches: " + (livingCount - matches.size()));
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
    };
    
    public long getTimeToLive()
    {
        return timeToLive;
    }
}
