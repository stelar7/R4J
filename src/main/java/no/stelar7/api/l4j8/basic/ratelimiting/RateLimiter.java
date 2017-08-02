package no.stelar7.api.l4j8.basic.ratelimiting;

import no.stelar7.api.l4j8.basic.DataCall;
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
    
    public abstract void acquire();
    
    public abstract void updatePermitsPerX(Map<Integer, Integer> data);
    
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
            
            if (DataCall.getLogLevel().ordinal() >= LogLevel.DEBUG.ordinal())
            {
                System.err.println("Forcing next sleep to be atleast: " + overloadTimer + " seconds");
            }
            
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
}
