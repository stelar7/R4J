package no.stelar7.api.l4j8.basic.ratelimiting;

import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.LogLevel;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class RateLimiter
{
    
    protected List<RateLimit>            limits;
    protected Map<RateLimit, Instant>    firstCallInTime;
    protected Map<RateLimit, AtomicLong> callCountInTime;
    
    protected int overloadTimer;
    
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
        
        return limits != null ? limits.equals(that.limits) : (that.limits == null);
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
            DataCall.getLogLevel().printIf(LogLevel.DEBUG, String.format("Forcing next sleep to be atleast: %d seconds", overloadTimer));
            
        } catch (NumberFormatException e)
        {
            overloadTimer = 5;
        }
    }
    
    public void resetCalls()
    {
        for (RateLimit limit : limits)
        {
            firstCallInTime.put(limit, Instant.now().minusMillis(limit.getTimeframeInMS()));
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
    
    public void setFirstCallInTime(Map<RateLimit, Instant> firstCallInTime)
    {
        this.firstCallInTime = firstCallInTime;
    }
}
