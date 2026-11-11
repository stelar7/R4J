package no.stelar7.api.r4j.basic.calling;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.EmptyCacheProvider;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.ratelimiting.RateLimiter;
import no.stelar7.api.r4j.basic.utils.Pair;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Everything the calls of one R4J instance share: the credentials, the cache, the settings and the ratelimiters.
 * <p>
 * There is one default context, which is what {@code new R4J(credentials)}, the static methods on {@link DataCall} and the
 * {@code getInstance()} method of the api classes use. Create your own context (or use {@code R4J.isolated(credentials)}) to get an
 * instance that shares nothing with the default one, for example to use two sets of api keys in the same application.
 */
public final class R4JContext
{
    private static final R4JContext DEFAULT = new R4JContext();

    /**
     * @return the context used by everything that is not given a context of its own
     */
    public static R4JContext getDefault()
    {
        return DEFAULT;
    }

    private volatile APICredentials credentials;
    private volatile CacheProvider  cache                          = EmptyCacheProvider.INSTANCE;
    private volatile int            numberOfCallsAllowedInParallel = -1;
    private volatile long           maxSleep                       = 10000;
    private volatile int            globalTimeout                  = 0;
    private volatile boolean        failFastOnServiceRatelimit     = false;

    private final Map<ApiKeyType, Map<Enum, Map<Enum, RateLimiter>>> limiters   = new ConcurrentHashMap<>();
    private final Map<Pair<ApiKeyType, Enum>, Semaphore>             semaphores = new ConcurrentHashMap<>();
    private final Map<Class<?>, Object>                              apis       = new ConcurrentHashMap<>();

    public R4JContext()
    {
    }

    public R4JContext(APICredentials credentials)
    {
        this.credentials = credentials;
    }

    public APICredentials getCredentials()
    {
        return credentials;
    }

    public R4JContext setCredentials(APICredentials credentials)
    {
        this.credentials = credentials;
        return this;
    }

    public CacheProvider getCacheProvider()
    {
        return cache;
    }

    /**
     * @param provider the cache to use, or null to not cache anything
     * @return this
     */
    public R4JContext setCacheProvider(CacheProvider provider)
    {
        this.cache = provider == null ? EmptyCacheProvider.INSTANCE : provider;
        this.cache.setContext(this);
        return this;
    }

    public int getNumberOfCallsAllowedInParallel()
    {
        return numberOfCallsAllowedInParallel;
    }

    /**
     * @param numberOfCallsAllowedInParallel how many calls can be in flight at the same time per platform and key, 0 or less for no limit.
     *                                       Changing it does not affect platforms that have already been called.
     * @return this
     */
    public R4JContext setNumberOfCallsAllowedInParallel(int numberOfCallsAllowedInParallel)
    {
        this.numberOfCallsAllowedInParallel = numberOfCallsAllowedInParallel;
        return this;
    }

    public long getMaxSleep()
    {
        return maxSleep;
    }

    /**
     * @param maxSleep how long (in ms) a call can spend sleeping between retries before it gives up
     * @return this
     */
    public R4JContext setMaxSleep(long maxSleep)
    {
        this.maxSleep = maxSleep;
        return this;
    }

    public int getGlobalTimeout()
    {
        return globalTimeout;
    }

    /**
     * @param globalTimeout the connect and read timeout (in ms) of calls that do not set their own, 0 to use the defaults
     * @return this
     */
    public R4JContext setGlobalTimeout(int globalTimeout)
    {
        this.globalTimeout = globalTimeout;
        return this;
    }

    public boolean shouldFailFastOnServiceRatelimit()
    {
        return failFastOnServiceRatelimit;
    }

    /**
     * @param failFastOnServiceRatelimit see {@link DataCall#setFailFastOnServiceRatelimit(boolean)}
     * @return this
     */
    public R4JContext setFailFastOnServiceRatelimit(boolean failFastOnServiceRatelimit)
    {
        this.failFastOnServiceRatelimit = failFastOnServiceRatelimit;
        return this;
    }

    /**
     * @param keyType the key the limiters belong to
     * @return the limiters of the key: platform, then endpoint (the app limiter of a platform is stored under the platform itself)
     */
    public Map<Enum, Map<Enum, RateLimiter>> getLimiters(ApiKeyType keyType)
    {
        return limiters.computeIfAbsent(keyType, k -> new ConcurrentHashMap<>());
    }

    /**
     * The limiter for a key + platform + endpoint, created if it does not exist yet
     */
    public RateLimiter getLimiter(ApiKeyType keyType, Enum platform, Enum endpoint)
    {
        return getLimiters(keyType).computeIfAbsent(platform, k -> new ConcurrentHashMap<>()).computeIfAbsent(endpoint, k -> new RateLimiter());
    }

    /**
     * @return the semaphore limiting the calls in flight for a key + platform, or null if there is no limit
     */
    Semaphore getSemaphore(ApiKeyType keyType, Enum platform)
    {
        int permits = numberOfCallsAllowedInParallel;
        if (permits <= 0)
        {
            return null;
        }

        return semaphores.computeIfAbsent(new Pair<>(keyType, platform), k -> new Semaphore(permits, true));
    }

    /**
     * The instance of an api class that belongs to this context, created on first use.
     *
     * @param type    the api class
     * @param creator creates the api for this context
     * @param <T>     the api class
     * @return the api
     */
    @SuppressWarnings("unchecked")
    public <T> T getApi(Class<T> type, Function<R4JContext, T> creator)
    {
        return (T) apis.computeIfAbsent(type, k -> creator.apply(this));
    }
}
