package no.stelar7.api.l4j8.basic.ratelimiting;


import no.stelar7.api.l4j8.basic.DataCall;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Burst ratelimiter will use as many calls as possible, then wait when it reaches the limit
 */
public class BurstRateLimiter extends RateLimiter
{
    
    private Instant firstRequestInTime = Instant.now();
    private int     requestsInTime     = 0;
    
    /**
     * @inheritDoc
     */
    public BurstRateLimiter(final int permits, final int time, final TimeUnit unit)
    {
        super(permits, time, unit);
    }
    
    @Override
    public void acquire()
    {
        try
        {
            boolean shouldReset = firstRequestInTime.isBefore(Instant.now().minusMillis(delayInMs));
            
            if (shouldReset)
            {
                if (DataCall.VERBOSE_DEFAULT)
                {
                    System.out.println("10sec has passed since first call, resetting limit");
                }
                firstRequestInTime = Instant.now();
                requestsInTime = 0;
            }
            
            semaphore.acquireUninterruptibly();
            
            if (DataCall.VERBOSE_DEFAULT)
            {
                System.out.format("recieved requests in the last 10sec: %s%n", requestsInTime + 1);
            }
            if (requestsInTime >= this.requests)
            {
                long  timeInMs = firstRequestInTime.toEpochMilli() - Instant.now().toEpochMilli() + delayInMs;
                float preRound = timeInMs / 1000f;
                long  rounded  = Math.round(preRound);
                
                // add two seconds to the delay to make sure we dont hit the limit
                long delay = (rounded + 2) * 1000;
                
                if (DataCall.VERBOSE_DEFAULT)
                {
                    System.out.println("Requestcount at limit, sleeping to avoid 429");
                    System.out.format("Should sleep for %sms, but rounded to %sms%n", timeInMs, delay);
                    System.out.format("sleeping for %s%n", delay);
                }
                
                TimeUnit.MILLISECONDS.sleep(delay);
            }
            
            requestsInTime++;
            
            semaphore.release();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}