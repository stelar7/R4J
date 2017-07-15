package no.stelar7.api.l4j8.basic.ratelimiting;


import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.LogLevel;

import java.time.Instant;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Burst ratelimiter will use as many calls as possible, then wait when it reaches the limit
 */
public class BurstRateLimiter extends RateLimiter
{
    
    public BurstRateLimiter(RateLimit... limits)
    {
        super(limits);
    }
    
    @Override
    public void acquire()
    {
        try
        {
            update();
            
            Thread.sleep(getDelay());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updatePermitsPerX(Map<Integer, Integer> data)
    {
        for (Entry<Integer, Integer> key : data.entrySet())
        {
            for (RateLimit l : limits)
            {
                if (l.getTimeframeInMS() / 1000 == key.getKey())
                {
                    long oldVal = callCountInTime.get(l).get();
                    long newVal = key.getValue();
                    if (oldVal + 1 < newVal)
                    {
                        callCountInTime.get(l).set(newVal);
                        
                        if (DataCall.logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                        {
                            System.err.println("limit " + key + " has changed from " + oldVal + " to " + newVal);
                        }
                    }
                }
            }
        }
    }
    
    private long getDelay()
    {
        Instant now                = Instant.now();
        long[]  delay              = {0};
        int     bias               = 1;
        int     multiplicativeBias = 1;
        
        for (RateLimit limit : limits)
        {
            long actual = callCountInTime.get(limit).get();
            if (actual >= limit.getPermits())
            {
                
                if (DataCall.logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                {
                    System.err.println("Calls made in the time frame: " + actual);
                    System.err.println("Limit for the time frame: " + limit.getPermits());
                }
                
                int newBias = (int) Math.floorDiv(actual, limit.getPermits());
                if (newBias > multiplicativeBias)
                {
                    multiplicativeBias = newBias;
                }
                
                long newDelay = firstCallInTime.get(limit).toEpochMilli() + limit.getTimeframeInMS() - now.toEpochMilli();
                if (newDelay > delay[0])
                {
                    delay[0] = newDelay;
                }
            }
        }
        
        if (delay[0] != 0)
        {
            delay[0] = (long) ((Math.ceil(delay[0] / 1000f) + bias) * (1000L * multiplicativeBias));
            System.err.println("Limit hit! Sleeping for: " + delay[0]);
        }
        
        return delay[0];
    }
    
    private void update()
    {
        Instant now = Instant.now();
        for (RateLimit limit : limits)
        {
            if ((firstCallInTime.get(limit).toEpochMilli() - now.toEpochMilli()) + limit.getTimeframeInMS() < 0)
            {
                firstCallInTime.put(limit, now);
                callCountInTime.get(limit).set(0);
            }
            
            callCountInTime.get(limit).incrementAndGet();
            
            if (DataCall.logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
            {
                System.err.println("Calls made: " + callCountInTime.get(limit) + " in: " + limit.getTimeframeInMS() / 1000);
            }
        }
    }
    
}