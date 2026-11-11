package no.stelar7.api.r4j.basic.ratelimiting;

import org.slf4j.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Limits the calls of one bucket (the application limit of a platform, or the limit of one method on a platform).
 * <p>
 * The limits are fixed windows, like the ones the api uses: a window starts with the first call made in it, and allows a number of calls until it ends.
 * The limiter never sleeps or blocks itself; {@link #tryAcquire(long)} either takes a permit or says how long to wait, so the caller decides how to wait.
 * All methods are thread safe.
 * <p>
 * The counters live in a {@link SharedRateLimitStore}. There is one implementation of the limiter, and where the counters are kept decides how
 * well several processes sharing an api key work together:
 * <ul>
 * <li>By default every limiter has a private in-memory store. Processes sharing a key do not see each other, so each one follows the counts the
 * server reports in its responses, which include the calls of the others. That keeps a shared key close to its limits, but not exactly on them:
 * calls in flight at the same time in different processes are invisible to each other, so an occasional 429 is possible.</li>
 * <li>With a shared store (ie. Redis, see {@code R4JContext.setRateLimitStore}) every process takes its permits from the same counters,
 * so a call is seen by all of them before it is sent.</li>
 * </ul>
 * In both cases the limiter follows the server ({@link #syncWithServerCounts(Map, long, long)}):
 * <ul>
 * <li>a higher count than the counter means something else is using the key, so the counter is raised</li>
 * <li>it then keeps a reserve as large as the amount of calls made by others while one of ours was in flight</li>
 * <li>a count lower than the calls the server has already answered for us means the server has started a new window, so the counter is restarted</li>
 * </ul>
 * A 429 stops all calls for the Retry-After the server gives ({@link #cooldown(long, String, boolean)}).
 */
public class RateLimiter
{
    private static final Logger logger = LoggerFactory.getLogger(RateLimiter.class);

    /**
     * A window is kept open a bit longer than its size: our clock starts when the request is sent, the clock of the server when it arrives.
     */
    private static final long MIN_WINDOW_MARGIN_MS = 200;
    private static final long MAX_WINDOW_MARGIN_MS = 2000;

    /**
     * Added to the Retry-After of a 429, which is only given in whole seconds
     */
    private static final long COOLDOWN_MARGIN_MS = 1000;

    /**
     * Used when a 429 has no usable Retry-After header
     */
    private static final long DEFAULT_COOLDOWN_MS = 5000;

    private static final long WARN_INTERVAL_MS = 60_000;

    /**
     * What this process knows about the current window of one limit. The count itself is in the store.
     */
    private static final class Window
    {
        // when we joined the window, and when it ends (in our own time)
        private long start;
        private long end;
        // the margin that was in use when we learned when the window ends
        private long margin;
        // our own calls in this window
        private long own;
        // our own calls in this window that the server has answered
        private long answered;
        // when the newest of the answered calls was sent
        private long newestAnswered = Long.MIN_VALUE;
        // how many calls were made by others (that the store did not know about) while one of ours was in flight
        private long foreignPerCall;

        private boolean isOpen(long now)
        {
            return now < end;
        }

        private void open(long start, long end, long margin, long own, long answered)
        {
            this.start = start;
            this.end = end;
            this.margin = margin;
            this.own = own;
            this.answered = answered;
            this.newestAnswered = Long.MIN_VALUE;
            // foreignPerCall is kept: the others are most likely still there in the next window
        }
    }

    private final SharedRateLimitStore store;
    // used when the store can not be reached
    private final SharedRateLimitStore fallback;
    private final String               bucket;

    private List<RateLimit>        limits  = Collections.emptyList();
    private Map<RateLimit, Window> windows = new HashMap<>();

    // calls made before the limits were known (the limits are only known after the first response)
    private long unknownStart;
    private long unknownCount;

    // what this process knows about the cooldown, so the store is not asked about it for every call
    private long    cooldownUntil;
    private boolean cooldownFailFast;
    private long    cooldownStartedAt;

    private long latency;
    // the latest time this limiter was told about
    private long lastNow;
    private long lastWarning;

    /**
     * A limiter that counts for itself, for a bucket with unknown limits.
     * It does not make a caller wait until the limits are known, but counts the calls, so they are accounted for once they are.
     */
    public RateLimiter()
    {
        this.fallback = new InMemoryRateLimitStore(() -> lastNow);
        this.store = this.fallback;
        this.bucket = "local";
    }

    /**
     * A limiter that counts for itself.
     *
     * @param limits the limits to obey
     */
    public RateLimiter(Collection<RateLimit> limits)
    {
        this();
        updateLimits(limits);
    }

    /**
     * A limiter that counts for itself.
     *
     * @param limits the limits to obey
     */
    public RateLimiter(RateLimit... limits)
    {
        this(Arrays.asList(limits));
    }

    /**
     * A limiter that shares its counters, limits and cooldown with every other limiter using the same store and bucket.
     *
     * @param store  where the counters are kept
     * @param bucket identifies the bucket (key + platform + endpoint); must be the same in every process sharing it
     */
    public RateLimiter(SharedRateLimitStore store, String bucket)
    {
        this.fallback = new InMemoryRateLimitStore(() -> lastNow);
        this.store = Objects.requireNonNull(store);
        // the braces are a Redis Cluster hash tag: all keys of a bucket end up in the same slot, which the scripts need
        this.bucket = "{" + bucket + "}";
    }

    private interface StoreCall<T>
    {
        T on(SharedRateLimitStore store);
    }

    /**
     * Runs the call on the store, or on the private in-memory store if the store can not be reached
     */
    private <T> T withStore(StoreCall<T> call)
    {
        if (store == fallback)
        {
            return call.on(store);
        }

        try
        {
            return call.on(store);
        } catch (RuntimeException e)
        {
            if (lastNow - lastWarning > WARN_INTERVAL_MS || lastWarning == 0)
            {
                lastWarning = Math.max(1, lastNow);
                logger.warn("The shared rate limit store could not be used, counting locally instead", e);
            }

            return call.on(fallback);
        }
    }

    private void touch(long now)
    {
        lastNow = Math.max(lastNow, now);
    }

    private String counterKey(RateLimit limit)
    {
        return bucket + ":count:" + limit.getTimeframeInMS();
    }

    private String cooldownKey()
    {
        return bucket + ":cooldown";
    }

    private String limitsKey()
    {
        return bucket + ":limits";
    }

    private static List<RateLimit> sort(Collection<RateLimit> limits)
    {
        List<RateLimit> sorted = new ArrayList<>(new LinkedHashSet<>(limits));
        sorted.sort(Comparator.comparingLong(RateLimit::getTimeframeInMS));
        return sorted;
    }

    private static String format(List<RateLimit> limits)
    {
        StringJoiner joiner = new StringJoiner(",");
        limits.forEach(l -> joiner.add(l.getPermits() + ":" + l.getTimeframeInMS()));
        return joiner.toString();
    }

    private static List<RateLimit> parse(String value)
    {
        List<RateLimit> limits = new ArrayList<>();
        if (value != null && !value.isEmpty())
        {
            for (String part : value.split(","))
            {
                String[] pair = part.split(":");
                limits.add(new RateLimit(Integer.parseInt(pair[0]), Long.parseLong(pair[1]), TimeUnit.MILLISECONDS));
            }
        }

        return sort(limits);
    }

    /**
     * @return how much longer than its size (in ms) a window is kept open; follows the measured round trip time
     */
    private long getWindowMargin()
    {
        return Math.min(MAX_WINDOW_MARGIN_MS, Math.max(MIN_WINDOW_MARGIN_MS, latency));
    }

    private static long getCooldownDuration(String retryAfterSeconds)
    {
        try
        {
            return Long.parseLong(retryAfterSeconds.trim()) * 1000L + COOLDOWN_MARGIN_MS;
        } catch (NumberFormatException | NullPointerException e)
        {
            return DEFAULT_COOLDOWN_MS;
        }
    }

    private Window window(RateLimit limit)
    {
        return windows.computeIfAbsent(limit, k -> new Window());
    }

    /**
     * Another process may already have learned the limits
     */
    private void loadSharedLimits()
    {
        if (limits.isEmpty() && store != fallback)
        {
            limits = parse(withStore(s -> s.get(limitsKey())));
        }
    }

    /**
     * Takes a permit if one is available.
     *
     * @param now the current time (epoch millis)
     * @return 0 if a permit was taken, otherwise the amount of milliseconds to wait before trying again
     */
    public synchronized long tryAcquire(long now)
    {
        touch(now);
        loadSharedLimits();

        List<String> counters = new ArrayList<>();
        List<Long>   maxima   = new ArrayList<>();
        List<Long>   ttls     = new ArrayList<>();
        for (RateLimit limit : limits)
        {
            Window window = window(limit);

            // the reserve is for the calls others are expected to make while this call is in flight
            long reserve = window.isOpen(now) ? window.foreignPerCall : 0;

            counters.add(counterKey(limit));
            maxima.add(Math.max(1, limit.getPermits() - reserve));
            ttls.add(limit.getTimeframeInMS() + getWindowMargin());
        }

        // with unknown limits there are no counters, but a cooldown still blocks
        long wait = withStore(s -> s.tryIncrement(cooldownKey(), counters, maxima, ttls));
        if (wait > 0)
        {
            return wait;
        }

        if (wait < 0)
        {
            // a cooldown is active; remember it, and whether calls should fail instead of wait
            if (cooldownUntil < now - wait)
            {
                cooldownUntil = now - wait;
                cooldownFailFast = "1".equals(withStore(s -> s.get(cooldownKey())));
            }
            return -wait;
        }

        if (limits.isEmpty())
        {
            if (unknownCount == 0)
            {
                unknownStart = now;
            }
            unknownCount++;
        }

        for (RateLimit limit : limits)
        {
            Window window = window(limit);
            if (!window.isOpen(now))
            {
                // the first call we make in this window; ask how long it lasts, as someone else may have started it
                long remaining = withStore(s -> s.getTimeToLive(counterKey(limit)));
                window.open(now, now + Math.max(1, remaining), getWindowMargin(), 0, 0);
            }
            window.own++;
        }

        return 0;
    }

    /**
     * Gives back a permit taken with {@link #tryAcquire(long)}, for a call that ended up not being made.
     */
    public synchronized void release()
    {
        if (limits.isEmpty())
        {
            if (unknownCount > 0)
            {
                unknownCount--;
            }
            return;
        }

        List<String> counters = new ArrayList<>();
        for (RateLimit limit : limits)
        {
            Window window = window(limit);
            if (window.own > 0)
            {
                window.own--;
                counters.add(counterKey(limit));
            }
        }

        withStore(s -> {
            s.decrement(counters);
            return null;
        });
    }

    /**
     * Sets the limits of this bucket, as reported by the server (X-App-Rate-Limit / X-Method-Rate-Limit).
     * The counters are per window size, so a limit that only changes its amount of calls keeps its count.
     * The calls made before any limit was known are counted in the new limits.
     *
     * @param newLimits the limits to obey
     * @return true if the limits changed
     */
    public synchronized boolean updateLimits(Collection<RateLimit> newLimits)
    {
        List<RateLimit> sorted = sort(newLimits);
        if (sorted.equals(limits))
        {
            return false;
        }

        boolean wasUnknown = limits.isEmpty();

        // what we know about a window belongs to its size, not to how many calls it allows
        Map<RateLimit, Window> updated = new HashMap<>();
        for (RateLimit limit : sorted)
        {
            for (Map.Entry<RateLimit, Window> old : windows.entrySet())
            {
                if (old.getKey().getTimeframeInMS() == limit.getTimeframeInMS())
                {
                    updated.put(limit, old.getValue());
                }
            }
        }

        limits = sorted;
        windows = updated;
        withStore(s -> {
            s.put(limitsKey(), format(sorted), 0);
            return null;
        });

        if (wasUnknown && unknownCount > 0)
        {
            for (RateLimit limit : sorted)
            {
                long remaining = limit.getTimeframeInMS() + getWindowMargin() - (lastNow - unknownStart);
                if (remaining > 0)
                {
                    long count = unknownCount;
                    withStore(s -> s.raise(counterKey(limit), count, remaining));
                    window(limit).open(unknownStart, lastNow + remaining, getWindowMargin(), count, 0);
                }
            }
        }
        unknownCount = 0;

        logger.debug("Ratelimits updated to {}", sorted);
        return true;
    }

    /**
     * Follows the counts reported by the server (X-App-Rate-Limit-Count / X-Method-Rate-Limit-Count). Call this once for every response.
     * <p>
     * This is what keeps the limiter correct when something the store does not know about is using the key too (another process without a shared store,
     * another application), or when the application was just restarted: a counter is raised when the server has seen more calls than it holds,
     * and restarted when the server has started a new window. A response to a request sent before we joined the current window is ignored.
     *
     * @param countsByWindowSeconds the calls the server has counted, by the size of the window in seconds
     * @param sentAt                when the request this response belongs to was sent (epoch millis)
     * @param receivedAt            when the response was received (epoch millis)
     */
    public synchronized void syncWithServerCounts(Map<Integer, Integer> countsByWindowSeconds, long sentAt, long receivedAt)
    {
        touch(receivedAt);

        if (receivedAt >= sentAt)
        {
            // slowly follows the round trip time, which decides how much longer than its size a window is kept open
            latency = latency == 0 ? receivedAt - sentAt : (latency * 3 + (receivedAt - sentAt)) / 4;
        }

        for (RateLimit limit : limits)
        {
            Integer serverCount = countsByWindowSeconds.get((int) (limit.getTimeframeInMS() / 1000));
            if (serverCount == null)
            {
                continue;
            }

            Window window = window(limit);
            String counter = counterKey(limit);
            long   fullTtl = limit.getTimeframeInMS() + getWindowMargin();

            if (!window.isOpen(receivedAt))
            {
                // we think the window is over (or never made a call in it), but the server is counting
                withStore(s -> s.raise(counter, serverCount, fullTtl));
                long remaining = withStore(s -> s.getTimeToLive(counter));
                window.open(sentAt, receivedAt + Math.max(1, remaining), getWindowMargin(), 1, 1);
                window.newestAnswered = sentAt;
                continue;
            }

            if (window.start > sentAt)
            {
                // the request was sent before we joined this window
                continue;
            }

            window.answered++;
            boolean newest = sentAt > window.newestAnswered;
            window.newestAnswered = Math.max(window.newestAnswered, sentAt);

            if (newest && serverCount < window.answered)
            {
                // The server has counted fewer calls than it has already answered for us in this window, so it has started a new window.
                // (the counter was started before the window of the server, which happens when something else made the first call of that window)
                // Only the newest call is trusted for this, as responses can arrive out of order.
                long inFlight = Math.max(0, window.own - window.answered);
                logger.debug("The server started a new window for {}, call count set to {}", limit, serverCount + inFlight);
                withStore(s -> {
                    s.put(counter, String.valueOf(serverCount + inFlight), fullTtl);
                    return null;
                });
                window.open(sentAt, receivedAt + fullTtl, getWindowMargin(), inFlight + 1, 1);
                window.newestAnswered = sentAt;
                continue;
            }

            if (getWindowMargin() > window.margin)
            {
                // the connection is slower than we thought when the window started, so the window of the server ends later than we assumed
                window.end += getWindowMargin() - window.margin;
                window.margin = getWindowMargin();
            }

            long remaining = Math.max(1, window.end - receivedAt);
            long previous  = withStore(s -> s.raise(counter, serverCount, remaining));

            // the calls made by others, that the store did not know about, since our previous call
            long foreign = Math.max(0, serverCount - previous);
            if (window.answered > 1)
            {
                // the first answer of a window only tells us what happened before we joined it, not how fast the others are going
                window.foreignPerCall = foreign >= window.foreignPerCall ? foreign : (window.foreignPerCall + foreign) / 2;
            }

            if (foreign > 0)
            {
                logger.debug("Call count for {} raised from {} to {} to match the server", limit, previous, serverCount);
            }
        }
    }

    /**
     * Stops all calls for the time the server asked for in the Retry-After header of a 429.
     *
     * @param receivedAt        when the 429 was received (epoch millis)
     * @param retryAfterSeconds the value of the Retry-After header (may be null or malformed, a default is used then)
     * @param failFast          true if calls made during the cooldown should fail instead of wait (see {@link #isCooldownFailFast()})
     */
    public synchronized void cooldown(long receivedAt, String retryAfterSeconds, boolean failFast)
    {
        // a response that was received before the one that set the current cooldown is outdated
        if (cooldownUntil > lastNow && receivedAt < cooldownStartedAt)
        {
            return;
        }

        touch(receivedAt);

        long duration  = getCooldownDuration(retryAfterSeconds);
        long remaining = receivedAt + duration - lastNow;
        if (remaining <= 0)
        {
            return;
        }

        cooldownStartedAt = receivedAt;
        cooldownUntil = receivedAt + duration;
        cooldownFailFast = failFast;

        withStore(s -> {
            s.put(cooldownKey(), failFast ? "1" : "0", remaining);
            return null;
        });

        logger.debug("No calls for {} ms (failFast={})", remaining, failFast);
    }

    /**
     * @param now the current time (epoch millis)
     * @return how long the cooldown started by a 429 still lasts, 0 if there is none.
     * A cooldown started by another process (through a shared store) is known once {@link #tryAcquire(long)} has run into it.
     */
    public synchronized long getCooldownRemaining(long now)
    {
        touch(now);
        return Math.max(0, cooldownUntil - now);
    }

    /**
     * @return true if calls made during the current cooldown should fail immediately instead of waiting for it to end
     */
    public synchronized boolean isCooldownFailFast()
    {
        return cooldownFailFast && lastNow < cooldownUntil;
    }

    /**
     * @return the limits this limiter obeys, shortest window first (empty if they are not known yet)
     */
    public synchronized List<RateLimit> getLimits()
    {
        loadSharedLimits();
        return new ArrayList<>(limits);
    }

    /**
     * @return the calls counted in the current window of each limit (by everyone using the store, and as far as the server told us, by others)
     */
    public synchronized Map<RateLimit, Long> getCallCounts()
    {
        loadSharedLimits();

        Map<RateLimit, Long> counts = new LinkedHashMap<>();
        for (RateLimit limit : limits)
        {
            counts.put(limit, withStore(s -> s.getCounter(counterKey(limit))));
        }
        return counts;
    }

    @Override
    public synchronized String toString()
    {
        return "RateLimiter{" + "calls=" + getCallCounts() + ", cooldownUntil=" + cooldownUntil + '}';
    }
}
