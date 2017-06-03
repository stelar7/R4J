package no.stelar7.api.l4j8.basic.cache;

import java.lang.reflect.Type;

public abstract class CacheProvider
{
    
    //public Map<Long, Match> getMatches(Platform platform, List<Long> ids);
    
    //public Map<Long, Summoner> getSummoners(Platform platform, List<Long> accountIds);
    
    public static final CacheProvider EMPTY = new CacheProvider()
    {
        @Override
        public void store(Type clazz, Object obj) { /*void cache*/}
    };
    
    public abstract void store(Type clazz, Object obj);
}
