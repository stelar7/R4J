package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import org.slf4j.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Keeps the responses in memory.
 * Items are keyed on the endpoint and the parameters of the call, in the order they were added to the map.
 */
public class MemoryCacheProvider implements CacheProvider
{
    private static final Logger logger = LoggerFactory.getLogger(MemoryCacheProvider.class);

    private static final class CacheEntry
    {
        private final Object value;
        private final long   insertedAt;

        private CacheEntry(Object value)
        {
            this.value = value;
            this.insertedAt = System.currentTimeMillis();
        }
    }

    private final Map<URLEndpoint, Map<List<String>, CacheEntry>> cache = new ConcurrentHashMap<>();

    private final ScheduledExecutorService clearService = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "R4J-MemoryCache-cleaner");
        t.setDaemon(true);
        return t;
    });

    private ScheduledFuture<?> clearTask;
    private long               timeToLive;
    private CacheLifetimeHint  hints = CacheLifetimeHint.DEFAULTS;

    /**
     * Creates a memory cache, where items expire after ttl milliseconds
     *
     * @param ttl the amount of time to keep cached items (in milliseconds), or one of {@link CacheProvider#TTL_INFINITY}, {@link CacheProvider#TTL_USE_HINTS}
     */
    public MemoryCacheProvider(long ttl)
    {
        setTimeToLiveGlobal(ttl);
    }

    public MemoryCacheProvider()
    {
        this(CacheProvider.TTL_USE_HINTS);
    }


    @Override
    public synchronized void setTimeToLiveGlobal(long timeToLive)
    {
        this.timeToLive = timeToLive;

        if (clearTask != null)
        {
            clearTask.cancel(false);
            clearTask = null;
        }

        if (timeToLive == CacheProvider.TTL_INFINITY)
        {
            return;
        }

        // expired items are never returned by get(), this only makes sure unused items do not stay in memory
        long interval = timeToLive > 0 ? timeToLive : TimeUnit.MINUTES.toMillis(1);
        clearTask = clearService.scheduleAtFixedRate(this::clearOldCache, interval, interval, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setTimeToLive(CacheLifetimeHint hints)
    {
        this.hints = hints;
    }

    private List<String> toKey(Map<String, Object> data)
    {
        List<String> key = new ArrayList<>();
        data.forEach((k, v) -> {
            if (!"value".equals(k))
            {
                key.add(String.valueOf(v));
            }
        });

        return key;
    }

    private boolean isExpired(URLEndpoint type, CacheEntry entry)
    {
        long ttl = getTimeToLive(type);
        if (ttl == CacheProvider.TTL_INFINITY)
        {
            return false;
        }

        return System.currentTimeMillis() - entry.insertedAt > ttl;
    }

    private boolean matchesFilter(List<String> key, List<String> filter)
    {
        return key.size() >= filter.size() && key.subList(0, filter.size()).equals(filter);
    }

    @Override
    public void store(URLEndpoint type, Map<String, Object> obj)
    {
        if (!obj.containsKey("value"))
        {
            throw new IllegalArgumentException("Invalid cache insert!");
        }

        // if the object we are trying to store is not valid, dont store it.
        Object value = obj.get("value");
        if (value == null)
        {
            return;
        }

        cache.computeIfAbsent(type, k -> new ConcurrentHashMap<>()).put(toKey(obj), new CacheEntry(value));
    }


    @Override
    public void update(URLEndpoint type, Map<String, Object> obj)
    {
        store(type, obj);
    }

    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> data)
    {
        Map<List<String>, CacheEntry> entries = cache.get(type);
        if (entries == null)
        {
            return Optional.empty();
        }

        List<String> key   = toKey(data);
        CacheEntry   entry = entries.get(key);
        if (entry == null)
        {
            return Optional.empty();
        }

        if (isExpired(type, entry))
        {
            entries.remove(key, entry);
            return Optional.empty();
        }

        logger.debug("Loaded data from cache ({} {} {})", this.getClass().getName(), type, data);
        return Optional.of(entry.value);
    }

    @Override
    public void clear(URLEndpoint type, Map<String, Object> filter)
    {
        Map<List<String>, CacheEntry> entries = cache.get(type);
        if (entries == null)
        {
            return;
        }

        List<String> filterKey = toKey(filter);
        entries.keySet().removeIf(key -> matchesFilter(key, filterKey));
    }

    @Override
    public void clearOldCache()
    {
        cache.forEach((type, entries) -> entries.values().removeIf(entry -> isExpired(type, entry)));
    }

    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return timeToLive == TTL_USE_HINTS ? hints.get(type) : timeToLive;
    }

    @Override
    public long getSize(URLEndpoint type, Map<String, Object> filter)
    {
        Map<List<String>, CacheEntry> entries = cache.get(type);
        if (entries == null)
        {
            return 0;
        }

        List<String> filterKey = toKey(filter);
        return entries.keySet().stream().filter(key -> matchesFilter(key, filterKey)).count();
    }
}
