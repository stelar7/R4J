package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;

import java.util.*;

public class TieredCacheProvider implements CacheProvider
{
    private final List<CacheProvider> providers = new LinkedList<>();
    
    public TieredCacheProvider(CacheProvider... provs)
    {
        providers.addAll(Arrays.asList(provs));
    }
    
    
    @Override
    public void store(URLEndpoint type, Object... obj)
    {
        providers.forEach(p -> p.store(type, obj));
    }
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
        providers.forEach(p -> p.update(type, obj));
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        
        for (CacheProvider provider : providers)
        {
            Optional<?> returnValue = provider.get(type, data);
            
            if (returnValue.isPresent())
            {
                restoreCache(provider, type, returnValue.get());
                return returnValue;
            }
        }
        
        return Optional.empty();
    }
    
    private void restoreCache(CacheProvider stoppingPoint, URLEndpoint type, Object obj)
    {
        
        boolean inTheFuture = false;
        
        for (CacheProvider provider : providers)
        {
            if (provider.equals(stoppingPoint))
            {
                inTheFuture = true;
            }
            
            
            if (!inTheFuture)
            {
                provider.store(type, obj);
            } else
            {
                // Only update if its not an infinite store
                if (provider.getTimeToLive(type) == CacheProvider.TTL_INFINITY)
                {
                    provider.update(type, obj);
                }
            }
        }
    }
    
    @Override
    public void clear(URLEndpoint type, Object... filter)
    {
        providers.forEach(p -> p.clear(type, filter));
    }
    
    @Override
    public void clearOldCache()
    {
        providers.forEach(CacheProvider::clearOldCache);
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return providers.stream().mapToLong(provider -> provider.getTimeToLive(type)).sum();
    }
    
    @Override
    public long getSize(URLEndpoint type)
    {
        return providers.stream().mapToLong(provider -> provider.getSize(type)).sum();
    }
    
    @Override
    public void setTimeToLiveGlobal(long timeToLive)
    {
        // this is ignored for tiered caches
    }
    
    @Override
    public void setTimeToLive(CacheLifetimeHint hints)
    {
        // this is ignored for tiered caches
    }
    
}
