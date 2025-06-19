package no.stelar7.api.r4j.basic.ratelimiting;


import no.stelar7.api.r4j.basic.calling.DataCallBuilder;

import org.slf4j.*;

import java.time.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Burst ratelimiter will use as many calls as possible, then wait when it reaches the limit
 */
public class BurstRateLimiter extends RateLimiter
{

	private static final Logger logger = LoggerFactory.getLogger(BurstRateLimiter.class);

	public BurstRateLimiter(List<RateLimit> limits)
	{
		super(limits.toArray(new RateLimit[0]));
	}

	@Override
	public void acquire(Enum platformOrEndpoint)
	{
		DataCallBuilder.getLock(platformOrEndpoint).lock();
		try
		{	
			manageOverloadTimer(platformOrEndpoint);
			manageRateLimit(platformOrEndpoint);
		} finally
		{
			DataCallBuilder.getLock(platformOrEndpoint).unlock();
		}
	}

	private void manageOverloadTimer(Enum platformOrEndpoint) {
		long msToWait = 0;

		overloadTimerLock.lock();
		try {
			if(overloadTimer != 0) {
				Instant now = Instant.now();
				// 1s is added as security margin, test 
				long realtimeToWait = overloadReceivedTime.toEpochMilli() + ((overloadTimer * 1000) + 1000) - now.toEpochMilli();
				if (realtimeToWait <= 0) {
					logger.debug("Overload timer reset for {} since time overpassed", platformOrEndpoint.name());
					overloadTimer = 0; // Reset overload timer after waiting
					resetCalls(); // Reset the call counts since we are no longer in an overload state
					return; // Overload timer has passed, no need to wait
				} else {
					logger.debug("Overload timer still active for {}. Waiting {} ms", platformOrEndpoint.name(), realtimeToWait);
					msToWait = realtimeToWait;
				}
			}
		}finally {
			overloadTimerLock.unlock();
		}

		if(msToWait > 0) {
			try {
				Thread.sleep(msToWait);
			} catch (InterruptedException e) {
				logger.error("Thread interrupted while waiting for overload timer", e);
				Thread.currentThread().interrupt();
			}
			// Recheck the overload timer after waiting (an old call with a longer required delay might have been received, even if it's highly unlikely)
			manageOverloadTimer(platformOrEndpoint);
		}
	}


	private void manageRateLimit(Enum platformOrEndpoint)
	{
		List<RateLimit> limitsToResetAfterWait = new ArrayList<>();
		
		for (RateLimit limit : limits)
		{
			Instant now = Instant.now();

			AtomicLong firstCall = firstCallInTime.computeIfAbsent(limit, (key) -> new AtomicLong(0));
			callCountInTime.computeIfAbsent(limit, (key) -> new AtomicLong(0));

			if ((firstCall.get()+200 - now.toEpochMilli()) + limit.getTimeframeInMS() < 0) // 200 ms of security margin, test
			{
				limitsToResetAfterWait.add(limit); // We reset later in case another limit is hit
				continue;
			}

			long actualCountCall = callCountInTime.get(limit).incrementAndGet();

			// ACTUAL CHECK IF WE ARE OVER THE LIMIT
			if (actualCountCall >= limit.getPermits()) { // We add 10 query as security margin, test
				
				// We need to wait for the next sliding window to make our request
				long delayForNextWindow = firstCallInTime.get(limit).get() + limit.getTimeframeInMS() - now.toEpochMilli();

				try {
					logger.debug("{}: Waiting for next sliding window, delay for next window is {} ms", platformOrEndpoint.name(), delayForNextWindow);
					Thread.sleep(delayForNextWindow + 500); // Add 500 ms to the delay to ensure we don't hit the limit again immediately, test
				}catch (InterruptedException e) {
					logger.error("Thread interrupted while sleeping for next window", e);
					Thread.currentThread().interrupt();
				}
				
				limitsToResetAfterWait.add(limit); // Add this limit to the list of limits to reset after waiting
			}
		}
		
		Instant now = Instant.now();
		for(RateLimit limit : limitsToResetAfterWait) {
			// Reset the call count after waiting for the next sliding window the next sliding window is when we make the first call again
			firstCallInTime.get(limit).set(now.toEpochMilli());
			callCountInTime.get(limit).set(1); // Reset to 1 since we just made a call
			logger.debug("{}: Resetting call count for limit {}", platformOrEndpoint.name(), limit);
		}
		
		for (RateLimit limit : limits)
		{
			logger.debug("{}: current call count={}, limit={}", platformOrEndpoint.name(), callCountInTime.get(limit), limit);
		}
		
	}
	
	@Override
	public void updatePermitsTakenPerX(Map<Integer, Integer> data, Enum platformOrEndpoint)
	{
		DataCallBuilder.getLock(platformOrEndpoint).lock();
		try 
		{
			for (Entry<Integer, Integer> key : data.entrySet())
			{
				for (RateLimit l : limits)
				{
					if (l.getTimeframeInMS() / 1000 == key.getKey())
					{
						long oldVal = callCountInTime.get(l).get();
						long newVal = key.getValue();
						if (oldVal < newVal)
						{
							callCountInTime.get(l).set(newVal);
							logger.debug("limit {} has changed from {} to {}", key, oldVal, newVal);
						}
					}
				}
			}
		} finally 
		{
			DataCallBuilder.getLock(platformOrEndpoint).unlock();
		}

	}

}