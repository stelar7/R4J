package no.stelar7.api.l4j8.basic.ratelimiting;

import java.util.*;
import java.util.concurrent.Semaphore;

public abstract class RateLimiter
{
    
    protected List<RateLimit> limits;
    protected Semaphore       semaphore;
    
    /**
     * @param limiters the limits to obey
     */
    public RateLimiter(RateLimit... limiters)
    {
        limits = Arrays.asList(limiters);
        
        // This line here... just wow..
        int fewestRequests = limits.stream().sorted(Comparator.comparing(RateLimit::getRequests).reversed()).mapToInt(RateLimit::getRequests).limit(1).sum();
        semaphore = new Semaphore(fewestRequests);
    }
    
    public Semaphore getSemaphore()
    {
        return semaphore;
    }
    
    public abstract void acquire();
    
    public abstract void updatePermitsPerX(Map<Integer, Long> data);
}
