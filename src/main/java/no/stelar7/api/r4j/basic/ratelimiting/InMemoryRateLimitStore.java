package no.stelar7.api.r4j.basic.ratelimiting;

import java.util.*;
import java.util.function.LongSupplier;

/**
 * Keeps the shared counters in memory. Use one instance for several contexts in the same JVM that use the same api key
 * (ie. two isolated R4J instances), so they obey the limits together. It is also what the tests use in place of Redis.
 */
public class InMemoryRateLimitStore implements SharedRateLimitStore
{
    private static final class Entry
    {
        private String value;
        private long   expiresAt;
    }

    private final Map<String, Entry> entries = new HashMap<>();
    private final LongSupplier       clock;

    public InMemoryRateLimitStore()
    {
        this(System::currentTimeMillis);
    }

    /**
     * @param clock gives the current time in ms (lets tests control the time)
     */
    public InMemoryRateLimitStore(LongSupplier clock)
    {
        this.clock = clock;
    }

    private Entry find(String key)
    {
        Entry entry = entries.get(key);
        if (entry != null && entry.expiresAt != 0 && clock.getAsLong() >= entry.expiresAt)
        {
            entries.remove(key);
            return null;
        }

        return entry;
    }

    private long remaining(Entry entry)
    {
        return entry == null || entry.expiresAt == 0 ? 0 : Math.max(1, entry.expiresAt - clock.getAsLong());
    }

    private void store(String key, String value, long ttl)
    {
        Entry entry = new Entry();
        entry.value = value;
        entry.expiresAt = ttl > 0 ? clock.getAsLong() + ttl : 0;
        entries.put(key, entry);
    }

    @Override
    public synchronized long tryIncrement(String blockKey, List<String> counters, List<Long> maxima, List<Long> ttls)
    {
        Entry block = find(blockKey);
        if (block != null && block.expiresAt != 0)
        {
            return -remaining(block);
        }

        long wait = 0;
        for (int i = 0; i < counters.size(); i++)
        {
            Entry entry = find(counters.get(i));
            if (entry != null && Long.parseLong(entry.value) >= maxima.get(i))
            {
                wait = Math.max(wait, Math.max(1, remaining(entry)));
            }
        }

        if (wait > 0)
        {
            return wait;
        }

        for (int i = 0; i < counters.size(); i++)
        {
            Entry entry = find(counters.get(i));
            if (entry == null)
            {
                store(counters.get(i), "1", ttls.get(i));
            } else
            {
                entry.value = String.valueOf(Long.parseLong(entry.value) + 1);
            }
        }

        return 0;
    }

    @Override
    public synchronized void decrement(List<String> counters)
    {
        for (String counter : counters)
        {
            Entry entry = find(counter);
            if (entry != null && Long.parseLong(entry.value) > 0)
            {
                entry.value = String.valueOf(Long.parseLong(entry.value) - 1);
            }
        }
    }

    @Override
    public synchronized long raise(String counter, long value, long ttl)
    {
        Entry entry = find(counter);
        if (entry == null)
        {
            store(counter, String.valueOf(value), ttl);
            return 0;
        }
        
        long previous = Long.parseLong(entry.value);
        if (previous < value)
        {
            entry.value = String.valueOf(value);
        }
        
        if (entry.expiresAt != 0 && ttl > 0)
        {
            entry.expiresAt = Math.max(entry.expiresAt, clock.getAsLong() + ttl);
        }
        
        return previous;
    }

    @Override
    public synchronized long getCounter(String counter)
    {
        Entry entry = find(counter);
        return entry == null ? 0 : Long.parseLong(entry.value);
    }

    @Override
    public synchronized void put(String key, String value, long ttl)
    {
        store(key, value, ttl);
    }

    @Override
    public synchronized String get(String key)
    {
        Entry entry = find(key);
        return entry == null ? null : entry.value;
    }

    @Override
    public synchronized long getTimeToLive(String key)
    {
        return remaining(find(key));
    }
}
