package no.stelar7.api.r4j.basic.ratelimiting;

import org.slf4j.*;

import no.stelar7.api.r4j.basic.calling.DataCallBuilder;

import java.time.Instant;
import java.util.*;
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

	protected static final Lock overloadTimerLock = new ReentrantLock();

	private static final Logger logger = LoggerFactory.getLogger(RateLimiter.class);

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

	public abstract void acquire(Enum platformOrEndpoint);

	public abstract void updatePermitsTakenPerX(Map<Integer, Integer> data, Enum platformOrEndpoint);

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
				//resetCalls(); We should reset calls only after the sleep has been applied
			}
			logger.debug("Forcing next sleep to be atleast: {} seconds (starting at : {})", overloadTimer, timeReceived);

		} catch (NumberFormatException e)
		{
			overloadTimer = 5;
			overloadReceivedTime = now;
		} finally
		{
			overloadTimerLock.unlock();
		}
	}

	protected void resetCalls()
	{
		for (RateLimit limit : limits)
		{
			firstCallInTime.get(limit).set(Instant.now().minusMillis(limit.getTimeframeInMS()).toEpochMilli());
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
					firstCallInTime.put(limit, oldLimit.getFirstCallInTime().get(toConvert.getDummyLimit()));
					callCountInTime.put(limit, oldLimit.getCallCountInTime().get(toConvert.getDummyLimit()));
				}
				
				this.overloadTimer = oldLimit.overloadTimer;
				this.overloadReceivedTime = oldLimit.overloadReceivedTime;
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
}
