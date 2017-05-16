package no.stelar7.api.l4j8.basic.ratelimiting;


import no.stelar7.api.l4j8.basic.DataCall;

import java.time.Instant;
import java.util.*;
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
    public void updatePermitsPerX(Map<Integer, Long> data)
    {
        for (Entry<Integer, Long> key : data.entrySet())
        {
            for (RateLimit l : limits)
            {
                if (l.getTimeframeInMS() / 1000 == key.getKey())
                {
                    long oldVal = callCountInTime.get(l).get();
                    long newVal = key.getValue();
                    if (oldVal + 1 < newVal)
                    {
                        callCountInTime.get(l).set(newVal + 1);
                        
                        if (DataCall.VERBOSE_LIMITING)
                        {
                            System.out.println("limit " + key + " has changed from " + oldVal + " to " + newVal);
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
            if (actual >= limit.getRequests())
            {
                
                int newBias = (int) Math.floorDiv(actual, limit.getRequests());
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
        }
        
        if (DataCall.VERBOSE_LIMITING)
        {
            System.out.println("Sleeping for: " + delay[0]);
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
            
            if (DataCall.VERBOSE_LIMITING)
            {
                System.out.println("Calls made: " + callCountInTime.get(limit) + " in: " + limit.getTimeframeInMS() / 1000);
            }
        }
    }
    
}