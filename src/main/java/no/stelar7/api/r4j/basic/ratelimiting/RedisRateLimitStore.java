package no.stelar7.api.r4j.basic.ratelimiting;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Keeps the shared rate limit counters in Redis, so every process using the same api key (on any machine) obeys the limits together.
 * <p>
 * R4J does not depend on a Redis client. Everything is done with Lua scripts, so all this class needs is a way to run EVAL, which every client has:
 * <pre>{@code
 * // Jedis
 * JedisPooled jedis = new JedisPooled("localhost", 6379);
 * SharedRateLimitStore store = new RedisRateLimitStore((script, keys, args) -> jedis.eval(script, keys, args));
 *
 * // Lettuce
 * RedisCommands<String, String> redis = RedisClient.create("redis://localhost").connect().sync();
 * SharedRateLimitStore store = new RedisRateLimitStore((script, keys, args) ->
 *         redis.eval(script, ScriptOutputType.VALUE, keys.toArray(new String[0]), args.toArray(new String[0])));
 *
 * R4JContext.getDefault().setRateLimitStore(store);
 * }</pre>
 * Optionally give it a way to run EVALSHA as well. The scripts are then sent by their hash instead of in full with every call:
 * <pre>{@code
 * new RedisRateLimitStore((script, keys, args) -> jedis.eval(script, keys, args),
 *                         (sha1, keys, args) -> jedis.evalsha(sha1, keys, args));
 * }</pre>
 * The executors must be safe to use from several threads (a pool, or a thread safe connection).
 * The keys of one bucket share a hash tag, so the scripts also work on Redis Cluster.
 */
public class RedisRateLimitStore implements SharedRateLimitStore
{
    /**
     * Runs a Lua script on Redis (the EVAL command)
     */
    @FunctionalInterface
    public interface ScriptExecutor
    {
        /**
         * @param script the Lua script
         * @param keys   the keys the script uses (KEYS)
         * @param args   the arguments of the script (ARGV)
         * @return what the script returned: a number, a string (or its bytes), or null
         */
        Object eval(String script, List<String> keys, List<String> args);
    }

    // KEYS[1] is the block key, the rest are the counters. ARGV holds the maximum and the ttl of each counter.
    private static final String TRY_INCREMENT =
            "local block = redis.call('PTTL', KEYS[1]) " +
            "if block > 0 then return -block end " +
            "local wait = 0 " +
            "for i = 2, #KEYS do " +
            "  local count = tonumber(redis.call('GET', KEYS[i]) or '0') " +
            "  if count >= tonumber(ARGV[(i - 2) * 2 + 1]) then " +
            "    local ttl = redis.call('PTTL', KEYS[i]) " +
            "    if ttl == -1 then " +
            "      ttl = tonumber(ARGV[(i - 2) * 2 + 2]) " +
            "      redis.call('PEXPIRE', KEYS[i], ttl) " +
            "    end " +
            "    if ttl < 1 then ttl = 1 end " +
            "    if ttl > wait then wait = ttl end " +
            "  end " +
            "end " +
            "if wait > 0 then return wait end " +
            "for i = 2, #KEYS do " +
            "  if redis.call('INCR', KEYS[i]) == 1 then " +
            "    redis.call('PEXPIRE', KEYS[i], ARGV[(i - 2) * 2 + 2]) " +
            "  end " +
            "end " +
            "return 0";

    private static final String DECREMENT =
            "for i = 1, #KEYS do " +
            "  if tonumber(redis.call('GET', KEYS[i]) or '0') > 0 then redis.call('DECR', KEYS[i]) end " +
            "end " +
            "return 0";

    // ARGV[1] is the value, ARGV[2] the least time the counter should still live. Returns the previous value.
    private static final String RAISE =
            "local current = redis.call('GET', KEYS[1]) " +
            "if not current then " +
            "  redis.call('SET', KEYS[1], ARGV[1], 'PX', ARGV[2]) " +
            "  return 0 " +
            "end " +
            "local ttl = redis.call('PTTL', KEYS[1]) " +
            "if ttl < tonumber(ARGV[2]) then ttl = tonumber(ARGV[2]) end " +
            "if tonumber(current) < tonumber(ARGV[1]) then " +
            "  redis.call('SET', KEYS[1], ARGV[1], 'PX', ttl) " +
            "else " +
            "  redis.call('PEXPIRE', KEYS[1], ttl) " +
            "end " +
            "return tonumber(current)";
    
    private static final String GET = "return redis.call('GET', KEYS[1])";

    private static final String PUT =
            "if tonumber(ARGV[2]) > 0 then " +
            "  redis.call('SET', KEYS[1], ARGV[1], 'PX', ARGV[2]) " +
            "else " +
            "  redis.call('SET', KEYS[1], ARGV[1]) " +
            "end " +
            "return 0";

    private static final String TIME_TO_LIVE =
            "local ttl = redis.call('PTTL', KEYS[1]) " +
            "if ttl < 0 then return 0 end " +
            "return ttl";

    /**
     * Runs a Lua script that Redis already knows, by the SHA-1 of its source (the EVALSHA command)
     */
    @FunctionalInterface
    public interface HashedScriptExecutor
    {
        /**
         * @param sha1 the lowercase hex SHA-1 of the script
         * @param keys the keys the script uses (KEYS)
         * @param args the arguments of the script (ARGV)
         * @return what the script returned. If Redis does not know the script, the exception it gives (a NOSCRIPT error) should be thrown as is.
         */
        Object evalsha(String sha1, List<String> keys, List<String> args);
    }

    private final ScriptExecutor       executor;
    private final HashedScriptExecutor hashedExecutor;
    private final Map<String, String>  hashes = new java.util.concurrent.ConcurrentHashMap<>();

    /**
     * @param executor runs a Lua script on Redis, see the class description for examples
     */
    public RedisRateLimitStore(ScriptExecutor executor)
    {
        this(executor, null);
    }

    /**
     * @param executor       runs a Lua script on Redis (EVAL), see the class description for examples
     * @param hashedExecutor runs a script Redis already knows by its hash (EVALSHA), or null to always send the full script
     */
    public RedisRateLimitStore(ScriptExecutor executor, HashedScriptExecutor hashedExecutor)
    {
        this.executor = Objects.requireNonNull(executor);
        this.hashedExecutor = hashedExecutor;
    }

    private static String sha1(String script)
    {
        try
        {
            byte[]        hash = java.security.MessageDigest.getInstance("SHA-1").digest(script.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb   = new StringBuilder();
            for (byte b : hash)
            {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e)
        {
            throw new IllegalStateException("SHA-1 is not available", e);
        }
    }

    private static boolean isUnknownScript(Throwable e)
    {
        for (Throwable t = e; t != null; t = t.getCause())
        {
            if (t.getMessage() != null && t.getMessage().contains("NOSCRIPT"))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Runs the script by its hash when possible. Redis remembers every script it has run with EVAL,
     * so the full script is only sent the first time, and again if Redis has forgotten it (a restart, SCRIPT FLUSH).
     */
    private Object run(String script, List<String> keys, List<String> args)
    {
        if (hashedExecutor == null)
        {
            return executor.eval(script, keys, args);
        }

        try
        {
            return hashedExecutor.evalsha(hashes.computeIfAbsent(script, RedisRateLimitStore::sha1), keys, args);
        } catch (RuntimeException e)
        {
            if (!isUnknownScript(e))
            {
                throw e;
            }

            return executor.eval(script, keys, args);
        }
    }

    private static long asLong(Object result)
    {
        if (result == null)
        {
            return 0;
        }

        if (result instanceof Number)
        {
            return ((Number) result).longValue();
        }

        return Long.parseLong(asString(result));
    }

    private static String asString(Object result)
    {
        if (result == null)
        {
            return null;
        }

        if (result instanceof byte[])
        {
            return new String((byte[]) result, StandardCharsets.UTF_8);
        }

        return result.toString();
    }

    @Override
    public long tryIncrement(String blockKey, List<String> counters, List<Long> maxima, List<Long> ttls)
    {
        List<String> keys = new ArrayList<>();
        keys.add(blockKey);
        keys.addAll(counters);

        List<String> args = new ArrayList<>();
        for (int i = 0; i < counters.size(); i++)
        {
            args.add(String.valueOf(maxima.get(i)));
            args.add(String.valueOf(ttls.get(i)));
        }

        return asLong(run(TRY_INCREMENT, keys, args));
    }

    @Override
    public void decrement(List<String> counters)
    {
        if (!counters.isEmpty())
        {
            run(DECREMENT, counters, Collections.emptyList());
        }
    }

    @Override
    public long raise(String counter, long value, long ttl)
    {
        return asLong(run(RAISE, Collections.singletonList(counter), Arrays.asList(String.valueOf(value), String.valueOf(ttl))));
    }
    
    @Override
    public long getCounter(String counter)
    {
        return asLong(run(GET, Collections.singletonList(counter), Collections.emptyList()));
    }

    @Override
    public void put(String key, String value, long ttl)
    {
        run(PUT, Collections.singletonList(key), Arrays.asList(value, String.valueOf(ttl)));
    }

    @Override
    public String get(String key)
    {
        return asString(run(GET, Collections.singletonList(key), Collections.emptyList()));
    }

    @Override
    public long getTimeToLive(String key)
    {
        return asLong(run(TIME_TO_LIVE, Collections.singletonList(key), Collections.emptyList()));
    }
}
