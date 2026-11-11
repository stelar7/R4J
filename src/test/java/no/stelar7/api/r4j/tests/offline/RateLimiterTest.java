package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.ratelimiting.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * The limiter takes the current time as a parameter, so these tests never sleep.
 */
public class RateLimiterTest
{
    private static final long T0 = 1_000_000;

    private static final RateLimit PER_SECOND     = new RateLimit(3, 1, TimeUnit.SECONDS);
    private static final RateLimit PER_TWO_MINUTE = new RateLimit(5, 120, TimeUnit.SECONDS);

    private static Map<Integer, Integer> counts(int... windowAndCount)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < windowAndCount.length; i += 2)
        {
            map.put(windowAndCount[i], windowAndCount[i + 1]);
        }
        return map;
    }

    @Test
    public void allowsExactlyTheLimitThenWaitsForTheWindow()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND);

        Assertions.assertEquals(0, limiter.tryAcquire(T0));
        Assertions.assertEquals(0, limiter.tryAcquire(T0 + 10));
        Assertions.assertEquals(0, limiter.tryAcquire(T0 + 20));

        // the window started at T0 and is 1000ms + a 200ms margin
        Assertions.assertEquals(1170, limiter.tryAcquire(T0 + 30));
        Assertions.assertEquals(1, limiter.tryAcquire(T0 + 1199));

        // a new window starts with this call
        Assertions.assertEquals(0, limiter.tryAcquire(T0 + 1200));
        Assertions.assertEquals(1L, limiter.getCallCounts().get(PER_SECOND));
    }

    @Test
    public void theLongestWaitWins()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND, PER_TWO_MINUTE);

        // 3 calls in the first second, 2 in the next: the two minute limit is now full
        for (int i = 0; i < 3; i++)
        {
            Assertions.assertEquals(0, limiter.tryAcquire(T0));
        }
        for (int i = 0; i < 2; i++)
        {
            Assertions.assertEquals(0, limiter.tryAcquire(T0 + 1500));
        }

        Assertions.assertEquals(120_200 - 1600, limiter.tryAcquire(T0 + 1600));
        Assertions.assertEquals(0, limiter.tryAcquire(T0 + 120_200));
    }

    @Test
    public void unknownLimitsNeverBlockButAreCarriedOver()
    {
        RateLimiter limiter = new RateLimiter();
        Assertions.assertTrue(limiter.getLimits().isEmpty());

        for (int i = 0; i < 3; i++)
        {
            Assertions.assertEquals(0, limiter.tryAcquire(T0 + i));
        }

        // the first response tells us the limits: the 3 calls already made count
        Assertions.assertTrue(limiter.updateLimits(Arrays.asList(PER_TWO_MINUTE, PER_SECOND)));
        Assertions.assertEquals(Arrays.asList(PER_SECOND, PER_TWO_MINUTE), limiter.getLimits());
        Assertions.assertEquals(3L, limiter.getCallCounts().get(PER_SECOND));
        Assertions.assertTrue(limiter.tryAcquire(T0 + 10) > 0);

        // the same limits again change nothing
        Assertions.assertFalse(limiter.updateLimits(Arrays.asList(PER_SECOND, PER_TWO_MINUTE)));
        Assertions.assertEquals(3L, limiter.getCallCounts().get(PER_SECOND));
    }

    @Test
    public void changedLimitsKeepTheCountsOfTheWindowsThatStay()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND, PER_TWO_MINUTE);
        limiter.tryAcquire(T0);
        limiter.tryAcquire(T0);

        RateLimit biggerPerSecond = new RateLimit(20, 1, TimeUnit.SECONDS);
        Assertions.assertTrue(limiter.updateLimits(Arrays.asList(biggerPerSecond, PER_TWO_MINUTE)));

        Assertions.assertEquals(2L, limiter.getCallCounts().get(biggerPerSecond));
        Assertions.assertEquals(2L, limiter.getCallCounts().get(PER_TWO_MINUTE));
    }

    @Test
    public void releaseGivesThePermitBack()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND);
        for (int i = 0; i < 3; i++)
        {
            limiter.tryAcquire(T0);
        }
        Assertions.assertTrue(limiter.tryAcquire(T0) > 0);

        limiter.release();
        Assertions.assertEquals(0, limiter.tryAcquire(T0));
    }

    @Test
    public void serverCountsRaiseOursButNeverLowerThem()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND, PER_TWO_MINUTE);
        limiter.tryAcquire(T0);
        limiter.tryAcquire(T0);

        // another process used the key too
        limiter.syncWithServerCounts(counts(1, 2, 120, 4), T0, T0 + 50);
        Assertions.assertEquals(2L, limiter.getCallCounts().get(PER_SECOND));
        Assertions.assertEquals(4L, limiter.getCallCounts().get(PER_TWO_MINUTE));

        // a lower count from the server does not lower ours (the response may be older than our latest call)
        limiter.syncWithServerCounts(counts(1, 1, 120, 1), T0, T0 + 60);
        Assertions.assertEquals(2L, limiter.getCallCounts().get(PER_SECOND));
        Assertions.assertEquals(4L, limiter.getCallCounts().get(PER_TWO_MINUTE));
    }

    @Test
    public void responsesFromAnOlderWindowAreIgnored()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND);
        limiter.tryAcquire(T0);

        // the window that started at T0 is over, and a new one starts
        limiter.tryAcquire(T0 + 5000);

        // a slow response to the request sent at T0 arrives now
        limiter.syncWithServerCounts(counts(1, 3), T0, T0 + 5010);
        Assertions.assertEquals(1L, limiter.getCallCounts().get(PER_SECOND));
    }

    @Test
    public void serverCountOpensAWindowWeThoughtWasOver()
    {
        RateLimiter limiter = new RateLimiter(PER_TWO_MINUTE);

        // nothing counted locally (ie. just restarted), but the server has seen 5 calls in its window
        limiter.syncWithServerCounts(counts(120, 5), T0, T0 + 40);
        Assertions.assertEquals(5L, limiter.getCallCounts().get(PER_TWO_MINUTE));
        Assertions.assertTrue(limiter.tryAcquire(T0 + 50) > 0);
    }

    @Test
    public void cooldownBlocksUntilRetryAfterThenStartsFresh()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND);
        for (int i = 0; i < 3; i++)
        {
            limiter.tryAcquire(T0);
        }

        // Retry-After: 2 -> 2000ms + 1000ms margin
        limiter.cooldown(T0 + 100, "2", false);
        Assertions.assertEquals(3000, limiter.getCooldownRemaining(T0 + 100));
        Assertions.assertFalse(limiter.isCooldownFailFast());
        Assertions.assertEquals(2900, limiter.tryAcquire(T0 + 200));

        // after the cooldown everything counted before is void
        Assertions.assertEquals(0, limiter.tryAcquire(T0 + 3100));
        Assertions.assertEquals(0, limiter.getCooldownRemaining(T0 + 3100));
        Assertions.assertEquals(1L, limiter.getCallCounts().get(PER_SECOND));
    }

    @Test
    public void cooldownWithoutRetryAfterUsesADefault()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND);
        limiter.cooldown(T0, null, true);

        Assertions.assertEquals(5000, limiter.getCooldownRemaining(T0));
        Assertions.assertTrue(limiter.isCooldownFailFast());

        limiter.cooldown(T0 + 10, "not a number", false);
        Assertions.assertEquals(5000, limiter.getCooldownRemaining(T0 + 10));
    }

    @Test
    public void anOlderCooldownDoesNotReplaceANewerOne()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND);
        limiter.cooldown(T0 + 500, "10", false);
        limiter.cooldown(T0, "1", false);

        Assertions.assertEquals(11_000, limiter.getCooldownRemaining(T0 + 500));
    }

    @Test
    public void aSlowConnectionKeepsTheWindowOpenLonger()
    {
        RateLimiter limiter = new RateLimiter(PER_SECOND);
        for (int i = 0; i < 3; i++)
        {
            limiter.tryAcquire(T0);
        }

        // an 800ms round trip: the server started its window up to 800ms after we did
        limiter.syncWithServerCounts(counts(1, 3), T0, T0 + 800);
        Assertions.assertEquals(1800 - 900, limiter.tryAcquire(T0 + 900));
    }
}
