package no.stelar7.api.r4j.basic.ratelimiting;

import org.slf4j.*;

import java.util.*;

/**
 * Tracks how many calls have been made in each rate limit window of one bucket (the application limit of a platform, or the limit of one method on a platform).
 * <p>
 * The limits are fixed windows, like the ones the api uses: a window starts with the first call made in it, and allows a number of calls until it ends.
 * The limiter never sleeps or blocks itself; {@link #tryAcquire(long)} either takes a permit or says how long to wait, so the caller decides how to wait.
 * All methods are thread safe.
 * <p>
 * Several processes (on the same or on different machines) can share an api key. They do not talk to each other; each one follows the
 * counts the server reports in its responses ({@link #syncWithServerCounts(Map, long, long)}), which include the calls of the others:
 * <ul>
 * <li>a higher count than ours means someone else is using the key, so the count is raised</li>
 * <li>the limiter then keeps a reserve as large as the amount of calls the others made while one of ours was in flight, because it can not see their calls until it gets a response</li>
 * <li>a count lower than the calls the server has already answered for us means the server has started a new window, so ours is restarted</li>
 * </ul>
 * This keeps a shared key close to its limits. It can not be exact: calls that are in flight at the same time in different processes are invisible to each other,
 * so an occasional 429 is still possible. Those are handled with the Retry-After the server gives ({@link #cooldown(long, String, boolean)}).
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

        // the calls the server has counted in this window, as far as we know (ours and the ones of other processes)
        private long count;
        // our own calls in this window
        private long own;
        // our own calls in this window that the server has answered
        private long answered;
        // when the newest of the answered calls was sent
        private long newestAnswered;
        // how many calls other processes made while one of ours was in flight
        private long foreignPerCall;

        private Window(RateLimit limit)
        {
            this.limit = limit;
        }

        private boolean isOpen()
        {
            return count > 0;
        }

        private void close()
        {
            count = 0;
            own = 0;
            answered = 0;
            newestAnswered = 0;
            // foreignPerCall is kept: the other processes are most likely still there in the next window
        }

        private void copyFrom(Window other)
        {
            start = other.start;
            count = other.count;
            own = other.own;
            answered = other.answered;
            newestAnswered = other.newestAnswered;
            foreignPerCall = other.foreignPerCall;
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
            windows.forEach(Window::close);
        }

        for (Window window : windows)
        {
            if (window.isOpen() && now >= windowEnd(window))
            {
                window.close();
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
            // the reserve is for the calls other processes sharing the key are expected to make while this call is in flight
            long reserve = window.isOpen() ? window.foreignPerCall : 0;
            if (window.count + reserve >= window.limit.getPermits())
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
            window.own++;
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
            if (window.own > 0)
            {
                window.own--;
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
        Window seed = new Window(null);
        seed.start = unknownStart;
        seed.count = unknownCount;
        seed.own = unknownCount;
        if (!windows.isEmpty())
        {
            seed.copyFrom(windows.get(windows.size() - 1));
        }

        List<Window> updated = new ArrayList<>();
        for (RateLimit limit : sorted)
        {
            Window window = new Window(limit);
            window.copyFrom(seed);

            for (Window old : windows)
            {
                if (old.limit.getTimeframeInMS() == limit.getTimeframeInMS())
                {
                    window.copyFrom(old);
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
     * Follows the counts reported by the server (X-App-Rate-Limit-Count / X-Method-Rate-Limit-Count). Call this once for every response.
     * <p>
     * This is what keeps the limiter correct when the key is shared with other processes, or the application was just restarted:
     * a count is raised when the server has seen more calls than we know of, and the window is restarted when the server has started a new one.
     * A response to a request sent before the current window started is ignored.
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
                // we think the window is over (or never made a call in it), but the server is counting
                window.start = sentAt;
                window.count = serverCount;
                window.own = 1;
                window.answered = 1;
                window.newestAnswered = sentAt;
                continue;
            }

            if (window.start > sentAt)
            {
                // the request was sent before this window started
                continue;
            }

            window.answered++;
            boolean newest = sentAt > window.newestAnswered;
            window.newestAnswered = Math.max(window.newestAnswered, sentAt);

            if (newest && serverCount < window.answered)
            {
                // The server has counted fewer calls than it has already answered for us in this window, so it has started a new window.
                // (ours started earlier than the one of the server, which happens when another process made the first call of that window)
                // Only the newest call is trusted for this, as responses can arrive out of order.
                long inFlight = Math.max(0, window.own - window.answered);
                logger.debug("The server started a new window for {}, call count changed from {} to {}", window.limit, window.count, serverCount + inFlight);
                window.start = sentAt;
                window.count = serverCount + inFlight;
                window.own = inFlight + 1;
                window.answered = 1;
                continue;
            }

            // the calls made by others since we last knew the count
            long foreign = Math.max(0, serverCount - window.count);
            if (window.answered > 1)
            {
                // the first answer of a window only tells us what happened before we joined it, not how fast the others are going
                window.foreignPerCall = foreign >= window.foreignPerCall ? foreign : (window.foreignPerCall + foreign) / 2;
            }

            if (foreign > 0)
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
     * @return the calls counted in the current window of each limit (ours and, as far as the server told us, the ones of other processes sharing the key)
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
