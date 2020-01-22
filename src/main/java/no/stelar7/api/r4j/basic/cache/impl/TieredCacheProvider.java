package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import org.slf4j.*;

import java.util.*;

public class TieredCacheProvider implements CacheProvider
{
    private static final Logger              logger    = LoggerFactory.getLogger(TieredCacheProvider.class);
    private final        List<CacheProvider> providers = new LinkedList<>();
    
    public TieredCacheProvider(CacheProvider... provs)
    {
        providers.addAll(Arrays.asList(provs));
    }
    
    
    @Override
    public void store(URLEndpoint type, Map<String, Object> obj)
    {
        providers.forEach(p -> p.store(type, obj));
    }
    
    @Override
    public void update(URLEndpoint type, Map<String, Object> obj)
    {
        providers.forEach(p -> p.update(type, obj));
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> data)
    {
        
        for (CacheProvider provider : providers)
        {
            Optional<?> returnValue = provider.get(type, data);
            
            if (returnValue.isPresent())
            {
                logger.debug("Loaded from: {}", provider.getClass().getName());
                
                data.put("value", returnValue.get());
                restoreCache(provider, type, data);
                return returnValue;
            }
        }
        
        return Optional.empty();
    }
    
    private void restoreCache(CacheProvider cacheDepth, URLEndpoint type, Map<String, Object> data)
    {
        
        boolean shouldUpdate = false;
        
        for (CacheProvider provider : providers)
        {
            if (provider.equals(cacheDepth))
            {
                shouldUpdate = true;
            }
            
            
            if (!shouldUpdate)
            {
                logger.debug("Saved to: {}", provider.getClass().getName());
                provider.store(type, data);
            } else
            {
                logger.debug("Updating timestamp: {}", provider.getClass().getName());
                provider.update(type, data);
            }
        }
    }
    
    @Override
    public void clear(URLEndpoint type, Map<String, Object> filter)
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
    public long getSize(URLEndpoint type, Map<String, Object> filter)
    {
        return providers.stream().mapToLong(provider -> provider.getSize(type, filter)).sum();
    }
    
    @Override
    public void setTimeToLiveGlobal(long timeToLive)
    {
        // this is ignored for tiered caches as the individuals control it themself
    }
    
    @Override
    public void setTimeToLive(CacheLifetimeHint hints)
    {
        // this is ignored for tiered caches as the individuals control it themself
    }
    
}
