package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;

import java.util.Optional;

public abstract class CacheProvider
{
    public static final CacheProvider EMPTY = new CacheProvider()
    {
        @Override
        public void store(URLEndpoint clazz, Object obj) { /*void cache*/}
        
        @Override
        public Optional<Object> get(URLEndpoint type, Object... data)
        {
            return Optional.empty();
        }
    };
    
    public abstract void store(URLEndpoint clazz, Object obj);
    
    public abstract Optional<Object> get(URLEndpoint type, Object... data);
}
