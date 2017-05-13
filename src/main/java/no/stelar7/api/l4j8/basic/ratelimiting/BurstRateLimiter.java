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
            semaphore.acquireUninterruptibly();
            
            Thread.sleep(getDelay());
            
            update();
            
            semaphore.release();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updatePermitsPerX(Map<Integer, Long> data)
    {
        semaphore.acquireUninterruptibly();
        
        for (Entry<Integer, Long> key : data.entrySet())
        {
            limits.stream().filter(l -> l.getTimeframeInMS() / 1000 == key.getKey()).forEach(l ->
                                                                                             {
                                                                                                 long oldVal = callCountInTime.get(l);
                                                                                                 long newVal = key.getValue();
                                                                                                 if (oldVal + 1 < newVal)
                                                                                                 {
                                                                                                     callCountInTime.put(l, newVal);
                    
                                                                                                     if (DataCall.VERBOSE_LIMITING)
                                                                                                     {
                                                                                                         System.out.println("limit " + key + " has changed from " + oldVal + " to " + newVal);
                                                                                                     }
                                                                                                 }
                                                                                             });
        }
        semaphore.release();
    }
    
    private long getDelay()
    {
        Instant now   = Instant.now();
        long[]  delay = {0};
        int     bias  = 2;
        
        limits.stream().sorted(Comparator.comparing(RateLimit::getTimeframeInMS)).forEachOrdered(l ->
                                                                                                 {
                                                                                                     if (callCountInTime.get(l) >= l.getRequests())
                                                                                                     {
                                                                                                         long newDelay = firstCallInTime.get(l).toEpochMilli() + l.getTimeframeInMS() - now.toEpochMilli();
                                                                                                         if (newDelay > delay[0])
                                                                                                         {
                                                                                                             delay[0] = newDelay;
                                                                                                         }
                                                                                                     }
                                                                                                 });
        
        if (delay[0] != 0)
        {
            delay[0] = (Math.round(delay[0] / 1000f) + bias) * 1000L;
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
        limits.forEach(l ->
                       {
                           if (firstCallInTime.get(l).toEpochMilli() < now.toEpochMilli() - l.getTimeframeInMS())
                           {
                               firstCallInTime.put(l, now);
                               callCountInTime.put(l, 0L);
                           }
            
                           callCountInTime.compute(l, (k, v) -> v + 1);
            
                           if (DataCall.VERBOSE_LIMITING)
                           {
                               System.out.println("Calls made: " + callCountInTime.get(l) + " in: " + l.getTimeframeInMS() / 1000);
                           }
                       });
    }
    
}