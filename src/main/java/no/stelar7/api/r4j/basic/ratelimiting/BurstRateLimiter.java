package no.stelar7.api.r4j.basic.ratelimiting;


import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.calling.DataCallBuilder;

import org.slf4j.*;

import java.time.*;
import java.time.temporal.ChronoUnit;
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
	public void acquire(Enum platform)
	{
		long sleepTime = 0;
		DataCallBuilder.getLock(platform).lock();
		try
		{
			update();
			sleepTime = getDelay();

			if (sleepTime != 0)
			{
				Duration dur = Duration.of(sleepTime, ChronoUnit.MILLIS);

				StringBuilder sb = new StringBuilder();
				sb.append(String.format("Ratelimited activated! Sleeping for: %s%n", dur.toString()));
				sb.append("Callstack:\n");
				Arrays.stream(Thread.currentThread().getStackTrace())
				.skip(DataCall.getCallStackSkip())
				.limit(DataCall.getCallStackLimit())
				.forEachOrdered(s -> sb.append(s.toString() + "\n"));

				String message = sb.toString();
				logger.debug(message);
			}

			try 
			{
				Thread.sleep(sleepTime);
			}catch (InterruptedException e)
			{
				logger.error("Thread interrupted while sleeping", e);
				Thread.currentThread().interrupt();
			}
		} finally
		{
			DataCallBuilder.getLock(platform).unlock();
		}
	}

	@Override
	public void updatePermitsTakenPerX(Map<Integer, Integer> data, Enum platform)
	{
		DataCallBuilder.getLock(platform).lock();
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
			DataCallBuilder.getLock(platform).unlock();
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
				if (actual + 10 >= limit.getPermits()) // We add 10 query as security margin, test
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
			delay[0] = (long) ((Math.ceil(delay[0] / 1000f) + bias) * (1000L * multiplicativeBias)) + 1000L; // Add 1 second to the delay to ensure we don't hit the limit again immediately, test
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