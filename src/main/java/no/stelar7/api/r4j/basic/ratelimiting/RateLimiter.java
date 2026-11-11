package no.stelar7.api.r4j.basic.ratelimiting;

import org.slf4j.*;

import java.util.*;

/**
 * Tracks how many calls have been made in each rate limit window of one bucket (the application limit of a platform, or the limit of one method on a platform).
 * <p>
 * The limits are fixed windows, like the ones the api uses: a window starts with the first call made in it, and allows a number of calls until it ends.
 * The limiter never sleeps or blocks itself; {@link #tryAcquire(long)} either takes a permit or says how long to wait, so the caller decides how to wait.
 * All methods are thread safe.
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

    private static final class Window
    {
        private final RateLimit limit;
        private       long      start;
        private       long      count;

        private Window(RateLimit limit)
        {
            this.limit = limit;
        }

        private boolean isOpen()
        {
            return count > 0;
        }
    }

    private List<Window> windows = new ArrayList<>();

    // calls made before the limits were known (the limits are only known after the first response)
    private long unknownStart;
    private long unknownCount;

    private long    cooldownUntil;
    private boolean cooldownFailFast;
    private long    cooldownStartedAt;

    private long latency;

    /**
     * A limiter for a bucket with unknown limits. It never makes a caller wait, but counts the calls, so they are accounted for once the limits are known.
     */
    public RateLimiter()
    {
    }

    /**
     * @param limits the limits to obey
     */
    public RateLimiter(Collection<RateLimit> limits)
    {
        updateLimits(limits);
    }

    /**
     * @param limits the limits to obey
     */
    public RateLimiter(RateLimit... limits)
    {
        this(Arrays.asList(limits));
    }

    private long windowEnd(Window window)
    {
        return window.start + window.limit.getTimeframeInMS() + Math.min(MAX_WINDOW_MARGIN_MS, Math.max(MIN_WINDOW_MARGIN_MS, latency));
    }

    private void closeExpiredWindows(long now)
    {
        if (cooldownUntil != 0 && now >= cooldownUntil)
        {
            // the server told us when it would accept calls again, so everything counted before that is void
            cooldownUntil = 0;
            cooldownFailFast = false;
            unknownCount = 0;
            windows.forEach(w -> w.count = 0);
        }

        for (Window window : windows)
        {
            if (window.isOpen() && now >= windowEnd(window))
            {
                window.count = 0;
            }
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
        closeExpiredWindows(now);

        if (cooldownUntil != 0)
        {
            return cooldownUntil - now;
        }

        long wait = 0;
        for (Window window : windows)
        {
            if (window.count >= window.limit.getPermits())
            {
                wait = Math.max(wait, windowEnd(window) - now);
            }
        }

        if (wait > 0)
        {
            return wait;
        }

        if (windows.isEmpty())
        {
            if (unknownCount == 0)
            {
                unknownStart = now;
            }
            unknownCount++;
        }

        for (Window window : windows)
        {
            if (!window.isOpen())
            {
                window.start = now;
            }
            window.count++;
        }

        return 0;
    }

    /**
     * Gives back a permit taken with {@link #tryAcquire(long)}, for a call that ended up not being made.
     */
    public synchronized void release()
    {
        if (windows.isEmpty() && unknownCount > 0)
        {
            unknownCount--;
        }

        for (Window window : windows)
        {
            if (window.count > 0)
            {
                window.count--;
            }
        }
    }

    /**
     * Sets the limits of this bucket, as reported by the server (X-App-Rate-Limit / X-Method-Rate-Limit).
     * The calls already counted are kept: a limit that stays keeps its window, and a new limit starts with the calls made so far.
     *
     * @param limits the limits to obey
     * @return true if the limits changed
     */
    public synchronized boolean updateLimits(Collection<RateLimit> limits)
    {
        List<RateLimit> sorted = new ArrayList<>(new LinkedHashSet<>(limits));
        sorted.sort(Comparator.comparingLong(RateLimit::getTimeframeInMS));

        if (sorted.equals(getLimits()))
        {
            return false;
        }

        // what a new limit starts with: the calls of the longest known window, or the calls made before any limit was known
        long seedStart = unknownStart;
        long seedCount = unknownCount;
        if (!windows.isEmpty())
        {
            Window longest = windows.get(windows.size() - 1);
            seedStart = longest.start;
            seedCount = longest.count;
        }

        List<Window> updated = new ArrayList<>();
        for (RateLimit limit : sorted)
        {
            Window window = new Window(limit);
            window.start = seedStart;
            window.count = seedCount;

            for (Window old : windows)
            {
                if (old.limit.getTimeframeInMS() == limit.getTimeframeInMS())
                {
                    window.start = old.start;
                    window.count = old.count;
                }
            }

            updated.add(window);
        }

        windows = updated;
        unknownCount = 0;

        logger.debug("Ratelimits updated to {}", sorted);
        return true;
    }

    /**
     * Raises our call counts to the counts reported by the server (X-App-Rate-Limit-Count / X-Method-Rate-Limit-Count).
     * This keeps the limiter correct when the key is shared with another process, or the application was just restarted.
     * A count is never lowered, and a response to a request sent before the current window started is ignored.
     *
     * @param countsByWindowSeconds the calls the server has counted, by the size of the window in seconds
     * @param sentAt                when the request this response belongs to was sent (epoch millis)
     * @param receivedAt            when the response was received (epoch millis)
     */
    public synchronized void syncWithServerCounts(Map<Integer, Integer> countsByWindowSeconds, long sentAt, long receivedAt)
    {
        if (receivedAt >= sentAt)
        {
            // slowly follows the round trip time, which decides how much longer than its size a window is kept open
            latency = latency == 0 ? receivedAt - sentAt : (latency * 3 + (receivedAt - sentAt)) / 4;
        }

        closeExpiredWindows(receivedAt);

        for (Window window : windows)
        {
            Integer serverCount = countsByWindowSeconds.get((int) (window.limit.getTimeframeInMS() / 1000));
            if (serverCount == null)
            {
                continue;
            }

            if (!window.isOpen())
            {
                // we think the window is over, but the server is still counting in it
                window.start = sentAt;
                window.count = serverCount;
                continue;
            }

            if (window.start <= sentAt && window.count < serverCount)
            {
                logger.debug("Call count for {} raised from {} to {} to match the server", window.limit, window.count, serverCount);
                window.count = serverCount;
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
        long duration;
        try
        {
            duration = Long.parseLong(retryAfterSeconds.trim()) * 1000L + COOLDOWN_MARGIN_MS;
        } catch (NumberFormatException | NullPointerException e)
        {
            duration = DEFAULT_COOLDOWN_MS;
        }

        // a response that was received before the one that set the current cooldown is outdated
        if (cooldownUntil != 0 && receivedAt < cooldownStartedAt)
        {
            return;
        }

        cooldownStartedAt = receivedAt;
        cooldownUntil = receivedAt + duration;
        cooldownFailFast = failFast;

        logger.debug("No calls for {} ms (failFast={})", duration, failFast);
    }

    /**
     * @param now the current time (epoch millis)
     * @return how long the cooldown started by a 429 still lasts, 0 if there is none
     */
    public synchronized long getCooldownRemaining(long now)
    {
        return cooldownUntil == 0 ? 0 : Math.max(0, cooldownUntil - now);
    }

    /**
     * @return true if calls made during the current cooldown should fail immediately instead of waiting for it to end
     */
    public synchronized boolean isCooldownFailFast()
    {
        return cooldownUntil != 0 && cooldownFailFast;
    }

    /**
     * @return the limits this limiter obeys, shortest window first (empty if they are not known yet)
     */
    public synchronized List<RateLimit> getLimits()
    {
        List<RateLimit> limits = new ArrayList<>();
        windows.forEach(w -> limits.add(w.limit));
        return limits;
    }

    /**
     * @return the calls counted in the current window of each limit
     */
    public synchronized Map<RateLimit, Long> getCallCounts()
    {
        Map<RateLimit, Long> counts = new LinkedHashMap<>();
        windows.forEach(w -> counts.put(w.limit, w.count));
        return counts;
    }

    @Override
    public synchronized String toString()
    {
        return "RateLimiter{" + "calls=" + getCallCounts() + ", cooldownUntil=" + cooldownUntil + '}';
    }
}
