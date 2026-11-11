package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.ratelimiting.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Simulates several processes (separate JVMs) sharing one api key.
 * Each process has its own limiter and only learns about the others from the counts in its own responses,
 * exactly like separate JVMs do. Time is simulated, so the test is deterministic and does not sleep.
 */
public class SharedKeyRateLimiterTest
{
    private static final int  LIMIT     = 20;
    private static final long WINDOW_MS = 1000;

    /**
     * A fixed window counter like the one of the api: the window starts with the first call after the previous one ended
     */
    private static final class FakeServer
    {
        private long windowStart = Long.MIN_VALUE;
        private int  count;
        private int  accepted;
        private int  rejected;

        /**
         * @return the count to report in the response, negative if the call was rejected with a 429
         */
        private int handle(long now)
        {
            if (now >= windowStart + WINDOW_MS)
            {
                windowStart = now;
                count = 0;
            }

            count++;
            if (count > LIMIT)
            {
                rejected++;
                return -count;
            }

            accepted++;
            return count;
        }

        private long retryAfterSeconds(long now)
        {
            return Math.max(1, (windowStart + WINDOW_MS - now + 999) / 1000);
        }
    }

    private static final class PendingResponse
    {
        private final RateLimiter limiter;
        private final long        sentAt;
        private final long        arrivesAt;
        private final int         count;
        private final long        retryAfter;

        private PendingResponse(RateLimiter limiter, long sentAt, long arrivesAt, int count, long retryAfter)
        {
            this.limiter = limiter;
            this.sentAt = sentAt;
            this.arrivesAt = arrivesAt;
            this.count = count;
            this.retryAfter = retryAfter;
        }
    }

    /**
     * @param processes how many JVMs share the key
     * @param latency   how long a request takes to reach the server, and the response to get back (each way, in ms)
     * @param duration  how long to run (in ms)
     */
    private FakeServer simulate(int processes, long latency, long duration)
    {
        FakeServer        server   = new FakeServer();
        List<RateLimiter> limiters = new ArrayList<>();
        for (int i = 0; i < processes; i++)
        {
            // every process starts without knowing the limits, like a fresh JVM
            limiters.add(new RateLimiter());
        }

        // requests on their way to the server, and responses on their way back
        List<long[]>          requests  = new ArrayList<>();
        List<PendingResponse> responses = new ArrayList<>();

        for (long now = 0; now < duration; now++)
        {
            // requests arriving at the server
            for (Iterator<long[]> it = requests.iterator(); it.hasNext(); )
            {
                long[] request = it.next();
                if (request[2] <= now)
                {
                    it.remove();
                    int count = server.handle(now);
                    responses.add(new PendingResponse(limiters.get((int) request[0]), request[1], now + latency, count, count < 0 ? server.retryAfterSeconds(now) : 0));
                }
            }

            // responses arriving at the processes
            for (Iterator<PendingResponse> it = responses.iterator(); it.hasNext(); )
            {
                PendingResponse response = it.next();
                if (response.arrivesAt <= now)
                {
                    it.remove();
                    response.limiter.updateLimits(Collections.singletonList(new RateLimit(LIMIT, WINDOW_MS, TimeUnit.MILLISECONDS)));
                    response.limiter.syncWithServerCounts(Collections.singletonMap((int) (WINDOW_MS / 1000), Math.abs(response.count)), response.sentAt, now);
                    if (response.count < 0)
                    {
                        response.limiter.cooldown(now, String.valueOf(response.retryAfter), false);
                    }
                }
            }

            // every process wants to make a call every 7ms (far more than the limit allows), one in flight at a time per process
            for (int i = 0; i < processes; i++)
            {
                final int process = i;
                boolean   busy    = requests.stream().anyMatch(r -> r[0] == process) || responses.stream().anyMatch(r -> r.limiter == limiters.get(process));
                if (!busy && now % 7 == i % 7 && limiters.get(i).tryAcquire(now) == 0)
                {
                    requests.add(new long[]{i, now, now + latency});
                }
            }
        }

        return server;
    }

    @Test
    public void aSingleProcessUsesTheLimitWithoutBeingRejected()
    {
        FakeServer server = simulate(1, 20, 20_000);

        Assertions.assertEquals(0, server.rejected);
        // 20 seconds is at most 20 windows of 20 calls; the margin on each window costs some of that
        Assertions.assertTrue(server.accepted >= 280, "only " + server.accepted + " calls were made");
    }

    @Test
    public void processesSharingAKeyStayCloseToTheLimit()
    {
        for (int processes : new int[]{2, 3, 5})
        {
            FakeServer server = simulate(processes, 20, 20_000);

            // the processes can not see each others calls that are in flight, so a few rejections are expected, but they must stay rare
            Assertions.assertTrue(server.rejected <= server.accepted / 20, processes + " processes: " + server.rejected + " rejected, " + server.accepted + " accepted");
            // and sharing the key must not starve them
            Assertions.assertTrue(server.accepted >= 200, processes + " processes: only " + server.accepted + " calls were made");
        }
    }

    @Test
    public void aSlowConnectionDoesNotCauseAFloodOfRejections()
    {
        FakeServer server = simulate(3, 150, 20_000);

        Assertions.assertTrue(server.rejected <= server.accepted / 10, server.rejected + " rejected, " + server.accepted + " accepted");
        Assertions.assertTrue(server.accepted >= 150, "only " + server.accepted + " calls were made");
    }

    @Test
    public void serverRolloverIsDetectedFromALowerCount()
    {
        RateLimit   limit   = new RateLimit(LIMIT, WINDOW_MS, TimeUnit.MILLISECONDS);
        RateLimiter limiter = new RateLimiter(limit);

        // we make 3 calls; the server answers them with counts 18, 19, 20 (another process used the rest of its window)
        for (int i = 0; i < 3; i++)
        {
            Assertions.assertEquals(0, limiter.tryAcquire(i));
            limiter.syncWithServerCounts(Collections.singletonMap(1, 18 + i), i, i + 1);
        }
        Assertions.assertEquals(20L, limiter.getCallCounts().get(limit));
        Assertions.assertTrue(limiter.tryAcquire(10) > 0);

        // a response to a call of ours reports a count of 1: the server is in a new window, even though ours has not ended
        limiter.release();
        Assertions.assertEquals(0, limiter.tryAcquire(11));
        limiter.syncWithServerCounts(Collections.singletonMap(1, 1), 11, 12);

        Assertions.assertEquals(1L, limiter.getCallCounts().get(limit));
        Assertions.assertEquals(0, limiter.tryAcquire(13));
    }
}
