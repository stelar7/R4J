package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.pojo.match.Match;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import java.util.*;

public abstract class CacheProvider
{
    public static final CacheProvider EMPTY = new CacheProvider()
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
    };
    
    public abstract void store(URLEndpoint clazz, Object obj);
    
    public abstract Optional<?> get(URLEndpoint type, Object... data);
    
    public abstract void clear(URLEndpoint type);
    
    public static final CacheProvider MEMORY = new CacheProvider()
    {
        
        private List<Summoner> summoners = new ArrayList<>();
        private List<Match> matches = new ArrayList<>();
        
        @Override
        public void store(URLEndpoint type, Object obj)
        {
            switch (type)
            {
                case V3_SUMMONER_BY_ACCOUNT:
                case V3_SUMMONER_BY_NAME:
                case V3_SUMMONER_BY_ID:
                    summoners.add((Summoner) obj);
                    break;
                case V3_MATCH:
                    matches.add((Match) obj);
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
                    return summoners.stream().filter(s -> s.getPlatform().equals(data[0])).filter(s -> s.getAccountId() == (Long) data[1]).findFirst();
                case V3_SUMMONER_BY_NAME:
                    return summoners.stream().filter(s -> s.getPlatform().equals(data[0])).filter(s -> s.getName().equals(data[1])).findFirst();
                case V3_SUMMONER_BY_ID:
                    return summoners.stream().filter(s -> s.getPlatform().equals(data[0])).filter(s -> s.getSummonerId() == (Long) data[1]).findFirst();
                case V3_MATCH:
                    return matches.stream().filter(m -> m.getPlatform().equals(data[0])).filter(m -> m.getMatchId() == (Long) data[1]).findFirst();
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
    };
}
