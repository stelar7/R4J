package no.stelar7.api.l4j8.basic.ratelimiting;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class RateLimiter
{
    
    protected List<RateLimit>            limits;
    protected Map<RateLimit, Instant>    firstCallInTime;
    protected Map<RateLimit, AtomicLong> callCountInTime;
    
    /**
     * @param limiters the limits to obey
     */
    public RateLimiter(RateLimit... limiters)
    {
        limits = Arrays.asList(limiters);
        
        firstCallInTime = new HashMap<>();
        callCountInTime = new HashMap<>();
        
        for (RateLimit limit : limits)
        {
            firstCallInTime.put(limit, Instant.now());
            callCountInTime.put(limit, new AtomicLong(0));
        }
    }
    
    public abstract void acquire();
    
    public abstract void updatePermitsPerX(Map<Integer, Long> data);
    
    public Map<RateLimit, Instant> getFirstCallInTime()
    {
        return firstCallInTime;
    }
    
    public Map<RateLimit, AtomicLong> getCallCountInTime()
    {
        return callCountInTime;
    }
    
    public List<RateLimit> getLimits()
    {
        return limits;
    }
}
