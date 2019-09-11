package no.stelar7.api.l4j8.basic.ratelimiting;

import org.slf4j.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class RateLimiter
{
    
    protected List<RateLimit>            limits;
    protected Map<RateLimit, AtomicLong> firstCallInTime;
    protected Map<RateLimit, AtomicLong> callCountInTime;
    
    protected int overloadTimer;
    
    private static final Logger logger = LoggerFactory.getLogger(RateLimiter.class);
    
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
            firstCallInTime.put(limit, new AtomicLong(Instant.now().toEpochMilli()));
            callCountInTime.put(limit, new AtomicLong(0));
        }
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        RateLimiter that = (RateLimiter) o;
        
        return Objects.equals(limits, that.limits);
    }
    
    @Override
    public int hashCode()
    {
        int result = limits != null ? limits.hashCode() : 0;
        result = 31 * result + (firstCallInTime != null ? firstCallInTime.hashCode() : 0);
        result = 31 * result + (callCountInTime != null ? callCountInTime.hashCode() : 0);
        result = 31 * result + overloadTimer;
        return result;
    }
    
    public abstract void acquire();
    
    public abstract void updatePermitsTakenPerX(Map<Integer, Integer> data);
    
    public Map<RateLimit, AtomicLong> getFirstCallInTime()
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
    
    
    @Override
    public String toString()
    {
        return "RateLimiter{" +
               "limits=" + limits +
               ", firstCallInTime=" + firstCallInTime +
               ", callCountInTime=" + callCountInTime +
               '}';
    }
    
    public void updateSleep(String sleep)
    {
        try
        {
            overloadTimer = Integer.parseInt(sleep);
            logger.debug("Forcing next sleep to be atleast: {} seconds", overloadTimer);
            
        } catch (NumberFormatException e)
        {
            overloadTimer = 5;
        }
    }
    
    public void resetCalls()
    {
        for (RateLimit limit : limits)
        {
            firstCallInTime.get(limit).set(Instant.now().minusMillis(limit.getTimeframeInMS()).toEpochMilli());
            callCountInTime.get(limit).set(0);
        }
    }
    
    public void mergeFrom(RateLimiter oldLimit)
    {
        if (oldLimit != null)
        {
            oldLimit.getCallCountInTime().forEach((key, value) -> {
                if (callCountInTime.containsKey(key))
                {
                    callCountInTime.put(key, value);
                }
            });
            
            oldLimit.getFirstCallInTime().forEach((key, value) -> {
                if (firstCallInTime.containsKey(key))
                {
                    firstCallInTime.put(key, value);
                }
            });
            
            this.overloadTimer = oldLimit.overloadTimer;
        }
    }
    
    public void setCallCountInTime(Map<RateLimit, AtomicLong> callCountInTime)
    {
        this.callCountInTime = callCountInTime;
    }
    
    public void setFirstCallInTime(Map<RateLimit, AtomicLong> firstCallInTime)
    {
        this.firstCallInTime = firstCallInTime;
    }
}
