package no.stelar7.api.r4j.basic.ratelimiting;

import org.slf4j.*;

import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RateLimiter
{
    
    protected List<RateLimit>            limits;
    protected Map<RateLimit, AtomicLong> firstCallInTime;
    protected Map<RateLimit, AtomicLong> callCountInTime;
    
    protected volatile int overloadTimer;
    protected volatile Instant overloadReceivedTime;
    protected volatile boolean overloadFailFast;
    
    protected static final Lock overloadTimerLock = new ReentrantLock();
    
    private static final Logger logger = LoggerFactory.getLogger(RateLimiter.class);
    
    /**
     * @param limiters the limits to obey
     */
    public RateLimiter(RateLimit... limiters)
    {
        limits = Arrays.asList(limiters);
        
        firstCallInTime = new ConcurrentHashMap<>();
        callCountInTime = new ConcurrentHashMap<>();
        
        for (RateLimit limit : limits)
        {
            firstCallInTime.put(limit, new AtomicLong(Instant.now().toEpochMilli()));
            callCountInTime.put(limit, new AtomicLong(0));
        }
        
        overloadReceivedTime = Instant.now(); // default value, will be updated when a 429 is received
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(limits);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RateLimiter other = (RateLimiter) obj;
        return Objects.equals(limits, other.limits);
    }
    
    /**
     * Acquires a permit for the specified platform or endpoint.
     * This method should be called before making an API call to ensure compliance with rate limits.
     * The caller must hold the lock for this limiter (DataCallBuilder.getLock(keyType, platform, endpoint)).
     * Return the validity date of the request, if this date is passed, the request must go through the rate limiter another time.
     *
     * @param platformOrEndpoint the platform or endpoint for which to acquire a permit
     * @return the time at which point the permit is not valid anymore
     */
    public abstract Instant acquire(ApiKeyType keyType, Enum platformOrEndpoint);
    
    public abstract void updatePermitsTakenPerX(Map<Integer, Integer> data, ApiKeyType keyTypeUsed, Enum platformOrEndpoint);
    
    /**
     * Raises our call counts to the counts reported by the server (X-App-Rate-Limit-Count / X-Method-Rate-Limit-Count).
     * This keeps the limiter correct when the key is shared with another process, or the application was just restarted.
     * The count is never lowered, and a response to a request sent before the current window started is ignored.
     *
     * @param countsByWindowSeconds the calls the server has counted, by the size of the window in seconds
     * @param requestSentAt         when the request this response belongs to was sent (epoch millis)
     */
    public void syncWithServerCounts(Map<Integer, Integer> countsByWindowSeconds, long requestSentAt)
    {
        for (RateLimit limit : limits)
        {
            Integer serverCount = countsByWindowSeconds.get((int) (limit.getTimeframeInMS() / 1000));
            if (serverCount == null)
            {
                continue;
            }
            
            AtomicLong windowStart = firstCallInTime.get(limit);
            AtomicLong localCount  = callCountInTime.get(limit);
            if (windowStart == null || localCount == null || windowStart.get() > requestSentAt)
            {
                continue;
            }
            
            long previous = localCount.getAndAccumulate(serverCount, Math::max);
            if (previous < serverCount)
            {
                logger.debug("Call count for {} raised from {} to {} to match the server", limit, previous, serverCount);
            }
        }
    }
    
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
    
    public void updateSleep(Instant timeReceived, String sleep, Enum platform)
    {
        updateSleep(timeReceived, sleep, platform, false);
    }

    /**
     * @param failFast when true, calls arriving while this cooldown is active fail immediately
     *                 with an APIEndpointCooldownException instead of sleeping it out.
     */
    public void updateSleep(Instant timeReceived, String sleep, Enum platform, boolean failFast)
    {
        Instant now = Instant.now();
        overloadTimerLock.lock();
        try
        {
            int futurTimer = Integer.parseInt(sleep);

            if(timeReceived.toEpochMilli() + (futurTimer * 1000L) < now.toEpochMilli()) {
                logger.debug("Received 429 sleep time {} for platform {}, but the time has already passed (now: {}, received: {})", futurTimer, platform, now, timeReceived);
                return;
            }

            // We prioritize the most recent sleep time
            if(overloadReceivedTime.toEpochMilli() < timeReceived.toEpochMilli()) {
                overloadReceivedTime = timeReceived;
                overloadTimer = futurTimer;
                overloadFailFast = failFast;
                //resetCalls(); We should reset calls only after the sleep has been applied
            }
            logger.debug("Forcing next sleep to be atleast: {} seconds (starting at : {})", overloadTimer, timeReceived);

        } catch (NumberFormatException e)
        {
            overloadTimer = 5;
            overloadReceivedTime = now;
            overloadFailFast = failFast;
        } finally
        {
            overloadTimerLock.unlock();
        }
    }
    
    protected void resetCalls()
    {
        Instant now = Instant.now();
        for (RateLimit limit : limits)
        {
            firstCallInTime.get(limit).set(now.minusMillis(limit.getTimeframeInMS()).toEpochMilli());
            callCountInTime.get(limit).set(0);
        }
    }
    
    public void mergeFrom(RateLimiter oldLimit)
    {
        if (oldLimit != null)
        {
            
            if(oldLimit instanceof CounterRateLimiter) {
                CounterRateLimiter toConvert = (CounterRateLimiter) oldLimit;
                for(RateLimit limit : limits) {
                    firstCallInTime.put(limit, new AtomicLong(oldLimit.getFirstCallInTime().get(toConvert.getDummyLimit()).get()));
                    callCountInTime.put(limit, new AtomicLong(oldLimit.getCallCountInTime().get(toConvert.getDummyLimit()).get()));
                }
                
                this.overloadTimer = oldLimit.overloadTimer;
                this.overloadReceivedTime = oldLimit.overloadReceivedTime;
                this.overloadFailFast = oldLimit.overloadFailFast;
                return;
            }
            
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
            this.overloadReceivedTime = oldLimit.overloadReceivedTime;
            this.overloadFailFast = oldLimit.overloadFailFast;
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
    
    public static Lock getOverloadTimerLock()
    {
        return overloadTimerLock;
    }
}
