package no.stelar7.api.l4j8.basic.ratelimiting;


import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.LogLevel;

import java.time.*;
import java.time.temporal.ChronoUnit;
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
    public synchronized void acquire()
    {
        try
        {
            update();
            long sleepTime = getDelay();
            
            if (sleepTime != 0)
            {
                Duration dur = Duration.of(sleepTime, ChronoUnit.MILLIS);
                System.err.format("Ratelimited activated! Sleeping for: %s%n", dur);
                
                if (DataCall.getLogLevel().ordinal() >= LogLevel.INFO.ordinal())
                {
                    int skip  = DataCall.getCallStackSkip();
                    int limit = DataCall.getCallStackLimit();
                    System.err.println();
                    System.err.println("Callstack:");
                    Arrays.stream(Thread.currentThread().getStackTrace()).skip(skip).limit(limit).forEachOrdered(System.err::println);
                }
            }
            
            
            Thread.sleep(sleepTime);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
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
                        
                        if (DataCall.getLogLevel().ordinal() >= LogLevel.DEBUG.ordinal())
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
        int     bias               = 1;
        int     multiplicativeBias = 1;
        Instant now                = Instant.now();
        long[]  delay              = {overloadTimer * 1000};
        overloadTimer = 0;
        
        if (delay[0] == 0)
        {
            for (RateLimit limit : limits)
            {
                long actual = callCountInTime.get(limit).get();
                if (actual >= limit.getPermits())
                {
                    
                    if (DataCall.getLogLevel().ordinal() >= LogLevel.DEBUG.ordinal())
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
            if ((firstCallInTime.get(limit).toEpochMilli() - now.toEpochMilli()) + limit.getTimeframeInMS() < 0)
            {
                firstCallInTime.put(limit, now);
                callCountInTime.get(limit).set(0);
            }
            
            callCountInTime.get(limit).incrementAndGet();
            
            if (DataCall.getLogLevel().ordinal() >= LogLevel.DEBUG.ordinal())
            {
                System.err.println(limit + " Calls made: " + callCountInTime.get(limit));
            }
        }
    }
    
}