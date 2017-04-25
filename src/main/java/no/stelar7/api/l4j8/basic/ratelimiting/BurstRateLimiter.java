package no.stelar7.api.l4j8.basic.ratelimiting;


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
            
            boolean shouldSleep = firstRequestInTime.isBefore(Instant.now().minusMillis(delayInMs));
            
            if (shouldSleep)
            {
                firstRequestInTime = Instant.now();
                requestsInTime = 0;
            }
            
            semaphore.acquireUninterruptibly();
            requestsInTime++;
            
            long delay = 0;
            if (requestsInTime >= permits)
            {
                long  timeInMs = firstRequestInTime.toEpochMilli() - Instant.now().toEpochMilli() + delayInMs;
                float preRound = timeInMs / 1000f;
                long  rounded  = Math.round(preRound);
                
                // add one second to the delay to make sure we dont hit the limit
                delay = (rounded + 1) * 1000;
            }
            TimeUnit.MILLISECONDS.sleep(delay);
            
            semaphore.release();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}