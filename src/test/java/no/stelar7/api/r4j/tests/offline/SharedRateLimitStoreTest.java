package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.ratelimiting.*;
import org.junit.jupiter.api.*;
import redis.clients.jedis.JedisPooled;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The same checks are run against the in-memory store and, when one is available, a real Redis.
 * <p>
 * To include Redis, point the R4J_TEST_REDIS environment variable (or system property) at it, ie.
 * {@code docker run --rm -p 6379:6379 redis} and {@code R4J_TEST_REDIS=localhost:6379 mvn test}
 */
public class SharedRateLimitStoreTest
{
    private static JedisPooled jedis;

    private static String redisAddress()
    {
        String address = System.getProperty("R4J_TEST_REDIS");
        return address != null ? address : System.getenv("R4J_TEST_REDIS");
    }

    @AfterAll
    public static void close()
    {
        if (jedis != null)
        {
            jedis.close();
        }
    }

    private SharedRateLimitStore redis()
    {
        String address = redisAddress();
        Assumptions.assumeTrue(address != null && !address.isEmpty(), "R4J_TEST_REDIS is not set, skipping the Redis test");

        if (jedis == null)
        {
            String[] parts = address.split(":");
            jedis = new JedisPooled(parts[0], parts.length > 1 ? Integer.parseInt(parts[1]) : 6379);
        }

        return new RedisRateLimitStore((script, keys, args) -> jedis.eval(script, keys, args));
    }

    @Test
    public void inMemoryStoreFollowsTheContract() throws InterruptedException
    {
        checkContract(new InMemoryRateLimitStore());
    }

    @Test
    public void redisStoreFollowsTheContract() throws InterruptedException
    {
        checkContract(redis());
    }

    @Test
    public void redisStoreNeverGoesOverTheLimitUnderConcurrency() throws Exception
    {
        checkConcurrency(redis());
    }

    @Test
    public void inMemoryStoreNeverGoesOverTheLimitUnderConcurrency() throws Exception
    {
        checkConcurrency(new InMemoryRateLimitStore());
    }

    private void checkContract(SharedRateLimitStore store) throws InterruptedException
    {
        // a unique prefix per run, with a hash tag like the limiter uses
        String       bucket   = "{r4j-test:" + UUID.randomUUID() + "}";
        String       block    = bucket + ":cooldown";
        List<String> counters = Arrays.asList(bucket + ":a", bucket + ":b");
        List<Long>   maxima   = Arrays.asList(3L, 5L);
        List<Long>   ttls     = Arrays.asList(400L, 60_000L);

        // 3 permits of the first counter
        for (int i = 0; i < 3; i++)
        {
            Assertions.assertEquals(0, store.tryIncrement(block, counters, maxima, ttls));
        }
        Assertions.assertEquals(3, store.getCounter(counters.get(0)));
        Assertions.assertEquals(3, store.getCounter(counters.get(1)));

        // the first counter is full: nothing changes, and the wait is what is left of its 400ms
        long wait = store.tryIncrement(block, counters, maxima, ttls);
        Assertions.assertTrue(wait > 0 && wait <= 400, "wait was " + wait);
        Assertions.assertEquals(3, store.getCounter(counters.get(0)));
        Assertions.assertEquals(3, store.getCounter(counters.get(1)));

        // giving one back makes room for exactly one
        store.decrement(counters);
        Assertions.assertEquals(2, store.getCounter(counters.get(0)));
        Assertions.assertEquals(0, store.tryIncrement(block, counters, maxima, ttls));
        Assertions.assertTrue(store.tryIncrement(block, counters, maxima, ttls) > 0);

        // once the first counter has expired it starts over, the second one keeps counting
        Thread.sleep(450);
        Assertions.assertEquals(0, store.getCounter(counters.get(0)));
        Assertions.assertEquals(0, store.tryIncrement(block, counters, maxima, ttls));
        Assertions.assertEquals(1, store.getCounter(counters.get(0)));
        Assertions.assertEquals(4, store.getCounter(counters.get(1)));

        // now the second counter fills up, and the wait is what is left of its minute
        Assertions.assertEquals(0, store.tryIncrement(block, counters, maxima, ttls));
        wait = store.tryIncrement(block, counters, maxima, ttls);
        Assertions.assertTrue(wait > 50_000 && wait <= 60_000, "wait was " + wait);

        // raise only ever raises, returns what the counter was, and creates the counter if needed
        Assertions.assertEquals(2, store.raise(counters.get(0), 1, 1));
        Assertions.assertEquals(2, store.getCounter(counters.get(0)));
        Assertions.assertEquals(2, store.raise(counters.get(0), 3, 1));
        Assertions.assertEquals(3, store.getCounter(counters.get(0)));
        String fresh = bucket + ":c";
        Assertions.assertEquals(0, store.raise(fresh, 7, 300));
        Assertions.assertEquals(7, store.getCounter(fresh));
        long ttl = store.getTimeToLive(fresh);
        Assertions.assertTrue(ttl > 0 && ttl <= 300, "ttl was " + ttl);
        
        // it never shortens the life of a counter, but can lengthen it
        store.raise(fresh, 1, 50);
        ttl = store.getTimeToLive(fresh);
        Assertions.assertTrue(ttl > 200 && ttl <= 300, "ttl was " + ttl);
        store.raise(fresh, 1, 5000);
        ttl = store.getTimeToLive(fresh);
        Assertions.assertTrue(ttl > 4000 && ttl <= 5000, "ttl was " + ttl);
        Assertions.assertEquals(7, store.getCounter(fresh));
        
        // plain values, with and without expiry
        Assertions.assertNull(store.get(bucket + ":limits"));
        store.put(bucket + ":limits", "20:1000,100:120000", 0);
        Assertions.assertEquals("20:1000,100:120000", store.get(bucket + ":limits"));
        Assertions.assertEquals(0, store.getTimeToLive(bucket + ":limits"));
        Assertions.assertEquals(0, store.getTimeToLive(bucket + ":missing"));

        // the block key stops everything while it exists, and reports its remaining time as a negative number
        List<String> free = Collections.singletonList(bucket + ":d");
        store.put(block, "1", 300);
        wait = store.tryIncrement(block, free, Collections.singletonList(10L), Collections.singletonList(1000L));
        Assertions.assertTrue(wait < 0 && wait >= -300, "wait was " + wait);
        Assertions.assertEquals(0, store.getCounter(free.get(0)));
        Assertions.assertEquals("1", store.get(block));

        Thread.sleep(350);
        Assertions.assertEquals(0, store.tryIncrement(block, free, Collections.singletonList(10L), Collections.singletonList(1000L)));

        // no counters at all (limits not known yet): only the block key matters
        Assertions.assertEquals(0, store.tryIncrement(block, Collections.emptyList(), Collections.emptyList(), Collections.emptyList()));
    }

    private void checkConcurrency(SharedRateLimitStore store) throws Exception
    {
        String       bucket   = "{r4j-test:" + UUID.randomUUID() + "}";
        List<String> counters = Collections.singletonList(bucket + ":a");
        List<Long>   maxima   = Collections.singletonList(50L);
        List<Long>   ttls     = Collections.singletonList(30_000L);

        // 8 threads fight over 50 permits; exactly 50 must be handed out
        AtomicInteger   taken = new AtomicInteger();
        ExecutorService pool  = Executors.newFixedThreadPool(8);
        List<Future<?>> jobs  = new ArrayList<>();
        for (int t = 0; t < 8; t++)
        {
            jobs.add(pool.submit(() -> {
                for (int i = 0; i < 40; i++)
                {
                    if (store.tryIncrement(bucket + ":cooldown", counters, maxima, ttls) == 0)
                    {
                        taken.incrementAndGet();
                    }
                }
            }));
        }

        for (Future<?> job : jobs)
        {
            job.get(30, TimeUnit.SECONDS);
        }
        pool.shutdown();

        Assertions.assertEquals(50, taken.get());
        Assertions.assertEquals(50, store.getCounter(counters.get(0)));
    }
}
