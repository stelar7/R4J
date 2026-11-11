package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.ratelimiting.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Several processes sharing one api key through a {@link SharedRateLimitStore}.
 * Each process has its own RateLimiter, like separate JVMs pointing at the same Redis. Time is simulated.
 */
public class SharedRateLimiterTest
{
    private static final int       LIMIT     = 20;
    private static final long      WINDOW_MS = 1000;
    private static final RateLimit PER_SEC   = new RateLimit(LIMIT, WINDOW_MS, TimeUnit.MILLISECONDS);

    /**
     * Simulates the api: a fixed window that starts with the first call after the previous window ended
     */
    private static final class FakeServer
    {
        private long windowStart = Long.MIN_VALUE;
        private int  count;
        private int  accepted;
        private int  rejected;

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
            } else
            {
                accepted++;
            }

            return count;
        }
    }

    private FakeServer simulate(int processes, long latency, long duration)
    {
        AtomicLong           clock  = new AtomicLong();
        SharedRateLimitStore store  = new InMemoryRateLimitStore(clock::get);
        FakeServer           server = new FakeServer();

        List<RateLimiter> limiters = new ArrayList<>();
        for (int i = 0; i < processes; i++)
        {
            limiters.add(new RateLimiter(store, "test"));
        }

        // {process, sentAt, arrivesAt} on the way to the server, {process, sentAt, arrivesAt, count} on the way back
        List<long[]> requests  = new ArrayList<>();
        List<long[]> responses = new ArrayList<>();

        for (long now = 0; now < duration; now++)
        {
            clock.set(now);

            for (Iterator<long[]> it = requests.iterator(); it.hasNext(); )
            {
                long[] request = it.next();
                if (request[2] <= now)
                {
                    it.remove();
                    responses.add(new long[]{request[0], request[1], now + latency, server.handle(now)});
                }
            }

            for (Iterator<long[]> it = responses.iterator(); it.hasNext(); )
            {
                long[] response = it.next();
                if (response[2] <= now)
                {
                    it.remove();
                    RateLimiter limiter = limiters.get((int) response[0]);
                    limiter.updateLimits(Collections.singletonList(PER_SEC));
                    limiter.syncWithServerCounts(Collections.singletonMap(1, (int) response[3]), response[1], now);
                }
            }

            // every process tries to make a call every millisecond, several in flight at the same time
            for (int i = 0; i < processes; i++)
            {
                if (limiters.get(i).tryAcquire(now) == 0)
                {
                    requests.add(new long[]{i, now, now + latency});
                }
            }
        }

        return server;
    }

    @Test
    public void processesSharingAStoreAreNeverRejected()
    {
        for (int processes : new int[]{1, 2, 3, 8})
        {
            // the very first calls are made before any process knows the limits, so that window is not counted
            FakeServer server = simulate(processes, 20, 30_000);

            // a process sends a call every millisecond until the first response arrives (40ms), and only 20 of all those calls fit in the window
            Assertions.assertTrue(server.rejected <= processes * 40, processes + " processes: " + server.rejected + " rejected");
            Assertions.assertTrue(server.accepted >= 400, processes + " processes: only " + server.accepted + " calls were made");
        }
    }

    @Test
    public void onlyTheColdStartCanOvershoot()
    {
        AtomicLong           clock = new AtomicLong();
        SharedRateLimitStore store = new InMemoryRateLimitStore(clock::get);

        RateLimiter first = new RateLimiter(store, "test");
        first.updateLimits(Collections.singletonList(PER_SEC));

        // a process that starts later knows the limits before its first call, and sees the calls of the first
        RateLimiter second = new RateLimiter(store, "test");
        for (int i = 0; i < LIMIT; i++)
        {
            Assertions.assertEquals(0, (i % 2 == 0 ? first : second).tryAcquire(clock.get()));
        }

        Assertions.assertEquals(Collections.singletonList(PER_SEC), second.getLimits());
        Assertions.assertEquals((long) LIMIT, second.getCallCounts().get(PER_SEC));
        Assertions.assertTrue(first.tryAcquire(clock.get()) > 0);
        Assertions.assertTrue(second.tryAcquire(clock.get()) > 0);

        // a permit given back by one is available to the other
        first.release();
        Assertions.assertEquals(0, second.tryAcquire(clock.get()));

        // after the window (1000ms + 200ms margin) both can go again
        clock.set(1200);
        Assertions.assertEquals(0, first.tryAcquire(clock.get()));
    }

    @Test
    public void aCooldownIsSharedWithItsFailFastFlag()
    {
        AtomicLong           clock = new AtomicLong(System.currentTimeMillis());
        SharedRateLimitStore store = new InMemoryRateLimitStore(clock::get);

        RateLimiter first  = new RateLimiter(store, "test");
        RateLimiter second = new RateLimiter(store, "test");
        first.updateLimits(Collections.singletonList(PER_SEC));

        // one process gets a 429 with Retry-After: 2 (2000ms + 1000ms margin)
        first.cooldown(clock.get(), "2", true);

        long wait = second.tryAcquire(clock.get());
        Assertions.assertEquals(3000, wait);
        Assertions.assertTrue(second.isCooldownFailFast());
        Assertions.assertEquals(3000, second.getCooldownRemaining(clock.get()));

        clock.addAndGet(3000);
        Assertions.assertEquals(0, second.tryAcquire(clock.get()));
        Assertions.assertEquals(0, second.getCooldownRemaining(clock.get()));
    }

    @Test
    public void countsFromTheServerRaiseTheSharedCounter()
    {
        AtomicLong           clock = new AtomicLong();
        SharedRateLimitStore store = new InMemoryRateLimitStore(clock::get);

        RateLimiter first  = new RateLimiter(store, "test");
        RateLimiter second = new RateLimiter(store, "test");
        first.updateLimits(Collections.singletonList(PER_SEC));

        // something that does not use the store has used 15 of the 20 calls
        Assertions.assertEquals(0, first.tryAcquire(0));
        first.syncWithServerCounts(Collections.singletonMap(1, 15), 0, 10);

        Assertions.assertEquals(15L, second.getCallCounts().get(PER_SEC));
        for (int i = 0; i < 5; i++)
        {
            Assertions.assertEquals(0, second.tryAcquire(20));
        }
        Assertions.assertTrue(second.tryAcquire(20) > 0);
    }

    @Test
    public void anUnreachableStoreFallsBackToCountingLocally()
    {
        SharedRateLimitStore broken = new SharedRateLimitStore()
        {
            private RuntimeException down()
            {
                return new IllegalStateException("store is down");
            }

            public long tryIncrement(String blockKey, List<String> counters, List<Long> maxima, List<Long> ttls)
            {
                throw down();
            }

            public void decrement(List<String> counters)
            {
                throw down();
            }

            public long raise(String counter, long value, long ttl)
            {
                throw down();
            }

            public long getCounter(String counter)
            {
                throw down();
            }

            public void put(String key, String value, long ttl)
            {
                throw down();
            }

            public String get(String key)
            {
                throw down();
            }

            public long getTimeToLive(String key)
            {
                throw down();
            }
        };

        RateLimiter limiter = new RateLimiter(broken, "test");
        limiter.updateLimits(Collections.singletonList(new RateLimit(2, 1, TimeUnit.SECONDS)));

        // calls still go through, and the local count still stops the third one
        Assertions.assertEquals(0, limiter.tryAcquire(0));
        Assertions.assertEquals(0, limiter.tryAcquire(0));
        Assertions.assertTrue(limiter.tryAcquire(0) > 0);
        limiter.release();
        Assertions.assertEquals(0, limiter.tryAcquire(0));
    }

    /**
     * A store that takes a while to answer, like a Redis or a database on the other side of a network
     */
    private static final class SlowStore extends InMemoryRateLimitStore
    {
        private final AtomicInteger inside     = new AtomicInteger();
        private final AtomicInteger mostInside = new AtomicInteger();

        @Override
        public long tryIncrement(String blockKey, List<String> counters, List<Long> maxima, List<Long> ttls)
        {
            mostInside.accumulateAndGet(inside.incrementAndGet(), Math::max);
            try
            {
                Thread.sleep(300);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            } finally
            {
                inside.decrementAndGet();
            }

            return super.tryIncrement(blockKey, counters, maxima, ttls);
        }
    }

    @Test
    public void aSlowStoreDoesNotMakeCallsQueueUp() throws Exception
    {
        SlowStore   store   = new SlowStore();
        RateLimiter limiter = new RateLimiter(store, "test");
        limiter.updateLimits(Collections.singletonList(new RateLimit(100, 60, TimeUnit.SECONDS)));

        ExecutorService pool = Executors.newFixedThreadPool(4);
        try
        {
            long               start = System.currentTimeMillis();
            List<Future<Long>> calls = new ArrayList<>();
            for (int i = 0; i < 4; i++)
            {
                calls.add(pool.submit(() -> limiter.tryAcquire(System.currentTimeMillis())));
            }

            // while those are waiting for the store, what the limiter knows locally is still available right away
            Thread.sleep(100);
            long before = System.currentTimeMillis();
            Assertions.assertFalse(limiter.isCooldownFailFast());
            Assertions.assertEquals(0, limiter.getCooldownRemaining(System.currentTimeMillis()));
            Assertions.assertTrue(System.currentTimeMillis() - before < 100, "the limiter was blocked by a call to the store");

            for (Future<Long> call : calls)
            {
                Assertions.assertEquals(0L, call.get(10, TimeUnit.SECONDS));
            }
            long took = System.currentTimeMillis() - start;

            // the four calls were in the store at the same time, instead of one after the other (which would take 1200ms)
            Assertions.assertEquals(4, store.mostInside.get());
            Assertions.assertTrue(took < 1000, "took " + took + " ms");
            Assertions.assertEquals(4L, limiter.getCallCounts().values().iterator().next());
        } finally
        {
            pool.shutdownNow();
        }
    }

    @Test
    public void aContextWithAStoreCreatesSharedLimitersWithAStableBucket()
    {
        SharedRateLimitStore store = new InMemoryRateLimitStore();

        // two contexts with the same key (ie. two JVMs) end up in the same bucket, a different key in another
        R4JContext a     = new R4JContext(new APICredentials("same-key")).setRateLimitStore(store);
        R4JContext b     = new R4JContext(new APICredentials("same-key")).setRateLimitStore(store);
        R4JContext other = new R4JContext(new APICredentials("other-key")).setRateLimitStore(store);

        RateLimiter limiterA     = a.getLimiter(ApiKeyType.LOL, LeagueShard.EUW1, URLEndpoint.V4_STATUS_LOL);
        RateLimiter limiterB     = b.getLimiter(ApiKeyType.LOL, LeagueShard.EUW1, URLEndpoint.V4_STATUS_LOL);
        RateLimiter limiterOther = other.getLimiter(ApiKeyType.LOL, LeagueShard.EUW1, URLEndpoint.V4_STATUS_LOL);
        
        limiterA.updateLimits(Collections.singletonList(new RateLimit(1, 60, TimeUnit.SECONDS)));
        long now = System.currentTimeMillis();
        Assertions.assertEquals(0, limiterA.tryAcquire(now));
        Assertions.assertTrue(limiterB.tryAcquire(now) > 0);
        Assertions.assertEquals(0, limiterOther.tryAcquire(now));
    }
}
