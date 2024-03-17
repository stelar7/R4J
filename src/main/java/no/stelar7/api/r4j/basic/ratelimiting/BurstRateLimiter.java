package no.stelar7.api.r4j.basic.ratelimiting;


import no.stelar7.api.r4j.basic.calling.DataCall;
import org.slf4j.*;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Burst ratelimiter will use as many calls as possible, then wait when it reaches the limit
 */
public class BurstRateLimiter extends RateLimiter
{
    
    private static final Logger logger = LoggerFactory.getLogger(BurstRateLimiter.class);
    
    private ReentrantLock lock = new ReentrantLock();
    
    public BurstRateLimiter(List<RateLimit> limits)
    {
        super(limits.toArray(new RateLimit[0]));
    }
    
    @Override
    public void acquire()
    {
        lock.lock();
        try
        {
            update();
            long sleepTime = getDelay();
            
            if (sleepTime != 0)
            {
                Duration dur = Duration.of(sleepTime, ChronoUnit.MILLIS);
                
                logger.info("Ratelimited activated! Sleeping for: {}", dur);
                logger.info("Callstack:");
                Arrays.stream(Thread.currentThread().getStackTrace())
                      .skip(DataCall.getCallStackSkip())
                      .limit(DataCall.getCallStackLimit())
                      .forEachOrdered(s -> logger.info(s.toString()));
            }
            
            
            Thread.sleep(sleepTime);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            lock.unlock();
        }
    }
    
    @Override
    public void updatePermitsTakenPerX(Map<Integer, Integer> data)
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
                        logger.debug("limit {} has changed from {} to {}", key, oldVal, newVal);
                    }
                }
            }
        }
    }
    
    private long getDelay()
    {
        int     bias               = 1;
        int     multiplicativeBias = 1;
        Instant now                = Instant.now();
        long[]  delay              = {overloadTimer * 1000L};
        overloadTimer = 0;
        
        if (delay[0] == 0)
        {
            for (RateLimit limit : limits)
            {
                long actual = callCountInTime.get(limit).get();
                if (actual >= limit.getPermits())
                {
                    
                    logger.debug("Calls made in the time frame: {}", actual);
                    logger.debug("Limit for the time frame: {}", limit.getPermits());
                    
                    int newBias = (int) Math.floorDiv(actual, (long) limit.getPermits());
                    if (newBias > multiplicativeBias)
                    {
                        multiplicativeBias = newBias;
                    }
                    
                    long newDelay = firstCallInTime.get(limit).get() + limit.getTimeframeInMS() - now.toEpochMilli();
                    if (newDelay > delay[0])
                    {
                        delay[0] = newDelay;
                    }
                }
            }
        }
        
        if (delay[0] != 0)
        {
            delay[0] = (long) ((Math.ceil(delay[0] / 1000f) + bias) * (1000L * multiplicativeBias));
        }
        
        return delay[0];
    }
    
    private void update()
    {
        Instant now = Instant.now();
        for (RateLimit limit : limits)
        {
            AtomicLong firstCall = firstCallInTime.computeIfAbsent(limit, (key) -> new AtomicLong(0));
            AtomicLong counter = callCountInTime.computeIfAbsent(limit, (key) -> new AtomicLong(0));
            
            if ((firstCall.get() - now.toEpochMilli()) + limit.getTimeframeInMS() < 0)
            {
                firstCallInTime.get(limit).set(now.toEpochMilli());
                callCountInTime.get(limit).set(0);
            }
            
            callCountInTime.get(limit).incrementAndGet();
            
            logger.debug("{}: current call count: {}", limit, callCountInTime.get(limit));
        }
    }
    
}