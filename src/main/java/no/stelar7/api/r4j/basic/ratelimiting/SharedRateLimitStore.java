package no.stelar7.api.r4j.basic.ratelimiting;

import java.util.List;

/**
 * A place outside of the JVM (ie. Redis) where the rate limit counters are kept, so every process using the same api key works from the same numbers.
 * <p>
 * Every {@link RateLimiter} keeps its counters in a store. By default that is a private {@link InMemoryRateLimitStore}, so each process counts
 * for itself and follows the counts the api reports, which is close but not exact when a key is shared.
 * Set a shared one with {@code R4JContext.setRateLimitStore(store)}, and a call made by any process is seen by all of them before it is even sent.
 * <p>
 * {@link RedisRateLimitStore} is the implementation for Redis, {@link InMemoryRateLimitStore} shares the counters inside one JVM.
 * Every method must be atomic, and expiry is measured by the clock of the store, so the clocks of the processes do not need to agree.
 * A method may throw a RuntimeException when the store can not be reached; the limiter then falls back to counting locally for that call.
 */
public interface SharedRateLimitStore
{
    /**
     * Takes one permit from every counter, but only if all of them have one left.
     *
     * @param blockKey a key that blocks all calls while it exists (set with {@link #put(String, String, long)} when the api answers 429)
     * @param counters the counters to increment
     * @param maxima   the highest value each counter may reach
     * @param ttls     how long (in ms) each counter lives, counted from when it is created
     * @return 0 if every counter was incremented.
     * Otherwise nothing was changed, and the result is how long to wait in ms: positive if a counter is full (until the last full counter expires),
     * negative if the block key exists (minus the time until it expires)
     */
    long tryIncrement(String blockKey, List<String> counters, List<Long> maxima, List<Long> ttls);

    /**
     * Gives back a permit taken with {@link #tryIncrement(String, List, List, List)}. A counter never goes below 0, and keeps its expiry.
     *
     * @param counters the counters to decrement
     */
    void decrement(List<String> counters);

    /**
     * Makes sure the counter is at least the value, and lives for at least the ttl.
     * A counter that does not exist is created with the value and the ttl; an existing one never gets a lower value or a shorter life.
     *
     * @param counter the counter
     * @param value   the value the counter should have at least
     * @param ttl     how long (in ms) the counter should live at least, counted from now
     * @return the value the counter had before the call, 0 if it did not exist
     */
    long raise(String counter, long value, long ttl);

    /**
     * @param counter the counter
     * @return the value of the counter, 0 if it does not exist
     */
    long getCounter(String counter);

    /**
     * Stores a value.
     *
     * @param key   the key
     * @param value the value
     * @param ttl   how long (in ms) the value is kept, 0 or less to keep it forever
     */
    void put(String key, String value, long ttl);

    /**
     * @param key the key
     * @return the value, or null if it does not exist
     */
    String get(String key);

    /**
     * @param key the key
     * @return how long (in ms) until the key expires, 0 if it does not exist or never expires
     */
    long getTimeToLive(String key);
}
