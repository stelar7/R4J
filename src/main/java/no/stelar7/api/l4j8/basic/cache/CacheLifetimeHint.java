package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CacheLifetimeHint
{
    private static final long HOURS_6 = TimeUnit.HOURS.toMillis(6);
    
    private final Map<URLEndpoint, Long> timeToLiveMS = new EnumMap<>(URLEndpoint.class);
    
    public void add(URLEndpoint endpoint, long time, TimeUnit unit)
    {
        timeToLiveMS.put(endpoint, unit.toMillis(time));
    }
    
    public Long get(URLEndpoint endpoint)
    {
        return timeToLiveMS.getOrDefault(endpoint, CacheLifetimeHint.HOURS_6);
    }
    
}
