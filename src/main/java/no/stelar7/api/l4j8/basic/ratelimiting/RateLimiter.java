package no.stelar7.api.l4j8.basic.ratelimiting;

import java.time.Instant;
import java.util.*;

public abstract class RateLimiter
{
    
    protected List<RateLimit> limits;
    protected Map<RateLimit, Instant> firstCallInTime;
    protected Map<RateLimit, Long>    callCountInTime;
    
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
            callCountInTime.put(limit, 0L);
        }
    }
    
    public abstract void acquire();
    
    public abstract void updatePermitsPerX(Map<Integer, Long> data);
    
    public Map<RateLimit, Instant> getFirstCallInTime()
    {
        return firstCallInTime;
    }
    
    public Map<RateLimit, Long> getCallCountInTime()
    {
        return callCountInTime;
    }
    
    public List<RateLimit> getLimits()
    {
        return limits;
    }
}
