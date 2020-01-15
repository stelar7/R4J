package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;

import java.util.Optional;

public final class EmptyCacheProvider implements CacheProvider
{
    public static final EmptyCacheProvider INSTANCE = new EmptyCacheProvider();
    
    private EmptyCacheProvider()
    {
        // Hide public constructor
    }
    
    @Override
    public void setTimeToLiveGlobal(long timeToLive)
    {
        /*void cache*/
    }
    
    @Override
    public void setTimeToLive(CacheLifetimeHint hints)
    {
        /*void cache*/
    }
    
    @Override
    public void store(URLEndpoint clazz, Object... obj)
    {
        /*void cache*/
    }
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
        /*void cache*/
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        return Optional.empty();
    }
    
    @Override
    public void clear(URLEndpoint type, Object... filter)
    {
        /*void cache*/
    }
    
    @Override
    public void clearOldCache()
    {
        /*void cache*/
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return 0;
    }
    
    @Override
    public long getSize(URLEndpoint type)
    {
        return 0;
    }
}
