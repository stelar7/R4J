package no.stelar7.api.l4j8.basic.ratelimiting;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.Semaphore;

public abstract class RateLimiter
{
    
    protected List<RateLimit> limits;
    protected Semaphore       semaphore;
    
    protected Map<RateLimit, Instant> firstCallInTime;
    protected Map<RateLimit, Long>    callCountInTime;
    
    /**
     * @param limiters the limits to obey
     */
    public RateLimiter(RateLimit... limiters)
    {
        limits = Arrays.asList(limiters);
        
        // This line here... just wow..
        int fewestRequests = limits.stream().sorted(Comparator.comparing(RateLimit::getRequests).reversed()).mapToInt(RateLimit::getRequests).limit(1).sum();
        semaphore = new Semaphore(fewestRequests);
        
        
        firstCallInTime = new HashMap<>();
        callCountInTime = new HashMap<>();
        
        for (RateLimit limit : limits)
        {
            firstCallInTime.put(limit, Instant.now().minusMillis(limit.getTimeframeInMS()));
            callCountInTime.put(limit, 0L);
        }
    }
    
    public Semaphore getSemaphore()
    {
        return semaphore;
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
