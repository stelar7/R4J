package no.stelar7.api.r4j.basic.ratelimiting;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import no.stelar7.api.r4j.basic.calling.DataCallBuilder;

/**
 * 
 */
public class CounterRateLimiter extends RateLimiter {

	public CounterRateLimiter() {
		super(new RateLimit(999999, 99999, TimeUnit.DAYS)); // We use a very high limit to only count, this count will be transfered when limit are known
	}
	
	@Override
	public void acquire(Enum platformOrEndpoint) {
		DataCallBuilder.getLock(platformOrEndpoint).lock();
		try {
			for (RateLimit limit : limits)
			{
				firstCallInTime.computeIfAbsent(limit, (key) -> new AtomicLong(Instant.now().toEpochMilli()));
				callCountInTime.computeIfAbsent(limit, (key) -> new AtomicLong(0)).incrementAndGet();
			}
		}finally {
			DataCallBuilder.getLock(platformOrEndpoint).unlock();
		}
	}
	
	public RateLimit getDummyLimit() {
		for (RateLimit limit : limits)
		{
			return limit;
		}
		return null;
	}

	@Override
	public void updatePermitsTakenPerX(Map<Integer, Integer> data, Enum platformOrEndpoint) {
		// Do nothing, as this is a counter rate limiter
	}

}
