package no.stelar7.api.r4j.basic.calling;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.EmptyCacheProvider;
import no.stelar7.api.r4j.basic.constants.api.Constants;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.ratelimiting.*;
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

    private volatile SharedRateLimitStore rateLimitStore;
    
    private volatile String requestUrl = Constants.REQUEST_URL;

    private final Map<ApiKeyType, Map<Enum, Map<Enum, RateLimiter>>> limiters   = new ConcurrentHashMap<>();
    private final Map<Pair<ApiKeyType, Enum>, CallPermits>           permits    = new ConcurrentHashMap<>();
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
     * A service rate limit can sometimes be hit (limits that are not linked to your API key, but to the Riot server itself).
     * This decides whether calls to an endpoint in such a cooldown fail immediately (with an APIEndpointCooldownException) or wait until it is over.
     * Failing fast is useful for high-throughput applications, which can make other calls in the meantime.
     *
     * @param failFastOnServiceRatelimit true to fail instead of wait
     * @return this
     */
    public R4JContext setFailFastOnServiceRatelimit(boolean failFastOnServiceRatelimit)
    {
        this.failFastOnServiceRatelimit = failFastOnServiceRatelimit;
        return this;
    }

    /**
     * @return the url calls to the Riot api are made to, with its placeholders
     */
    public String getRequestUrl()
    {
        return requestUrl;
    }
    
    /**
     * Sends the calls to the Riot api somewhere else, ie. through a proxy of your own that adds the api key or shares a rate limit.
     * <p>
     * The url is a template, in which these parts are filled in for every call: {platform} (ie. euw1 or europe), {game}, {service}, {version} and {resource}.
     * The default is {@code https://{platform}.api.riotgames.com/{game}/{service}/{version}/{resource}}.
     * Calls that set their own url (Data Dragon, the local game clients) are not affected.
     *
     * @param requestUrl the url template, or null for the default
     * @return this
     */
    public R4JContext setRequestUrl(String requestUrl)
    {
        this.requestUrl = requestUrl == null ? Constants.REQUEST_URL : requestUrl;
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
        return getLimiters(keyType).computeIfAbsent(platform, k -> new ConcurrentHashMap<>()).computeIfAbsent(endpoint, k -> createLimiter(keyType, platform, endpoint));
    }

    private RateLimiter createLimiter(ApiKeyType keyType, Enum platform, Enum endpoint)
    {
        SharedRateLimitStore store = rateLimitStore;
        if (store == null)
        {
            return new RateLimiter();
        }

        return new RateLimiter(store, "r4j:" + describeKey(keyType) + ":" + platform.name() + ":" + endpoint.name());
    }

    /**
     * Identifies the api key in the name of a shared bucket, without putting the key itself in the store
     */
    private String describeKey(ApiKeyType keyType)
    {
        APICredentials creds = credentials;
        if (creds == null || !creds.hasKey(keyType))
        {
            return keyType.name();
        }

        try
        {
            byte[]        hash = java.security.MessageDigest.getInstance("SHA-256").digest(creds.getKey(keyType).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb   = new StringBuilder();
            for (int i = 0; i < 8; i++)
            {
                sb.append(String.format("%02x", hash[i]));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e)
        {
            throw new IllegalStateException("SHA-256 is not available", e);
        }
    }

    public SharedRateLimitStore getRateLimitStore()
    {
        return rateLimitStore;
    }

    /**
     * Keeps the rate limit counters in a store outside of this JVM (ie. Redis), so every process using the same api key obeys the limits together.
     * Without a store, each process counts for itself and follows the counts the api reports.
     * <p>
     * Set this before making calls: the limiters that already exist are dropped, and with them what they had counted.
     *
     * @param rateLimitStore the store, or null to count locally
     * @return this
     */
    public R4JContext setRateLimitStore(SharedRateLimitStore rateLimitStore)
    {
        this.rateLimitStore = rateLimitStore;
        this.limiters.clear();
        return this;
    }

    /**
     * @return what limits the calls in flight for a key + platform, or null if there is no limit
     */
    CallPermits getPermits(ApiKeyType keyType, Enum platform)
    {
        int permits = numberOfCallsAllowedInParallel;
        if (permits <= 0)
        {
            return null;
        }

        return this.permits.computeIfAbsent(new Pair<>(keyType, platform), k -> new CallPermits(permits, task -> schedule(task, 0)));
    }

    // timers for the non-blocking calls of every context: waiting for a ratelimit or a retry is a scheduled task, not a sleeping thread
    private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "R4J-timer");
        t.setDaemon(true);
        return t;
    });

    // runs blocking calls for callAsync, and the few non-blocking calls that have to use the blocking transport
    private static final ExecutorService ASYNC_EXECUTOR = Executors.newCachedThreadPool(r -> {
        Thread t = new Thread(r, "R4J-async");
        t.setDaemon(true);
        return t;
    });

    private volatile java.net.http.HttpClient httpClient;

    /**
     * Runs the task after the delay, on the timer thread. The task must not block.
     */
    void schedule(Runnable task, long delayMs)
    {
        SCHEDULER.schedule(task, Math.max(0, delayMs), TimeUnit.MILLISECONDS);
    }

    /**
     * @return the executor used to run blocking calls in the background
     */
    public Executor getAsyncExecutor()
    {
        return ASYNC_EXECUTOR;
    }

    /**
     * The http client used by the non-blocking calls ({@code DataCallBuilder.buildAsync()}). It is created on first use,
     * with the connect timeout of that moment (see {@link #setGlobalTimeout(int)}).
     */
    java.net.http.HttpClient getHttpClient()
    {
        java.net.http.HttpClient client = httpClient;
        if (client == null)
        {
            synchronized (this)
            {
                client = httpClient;
                if (client == null)
                {
                    int connectTimeout = globalTimeout != 0 ? globalTimeout : DataCall.DEFAULT_CONNECT_TIMEOUT;
                    httpClient = client = java.net.http.HttpClient.newBuilder().connectTimeout(java.time.Duration.ofMillis(connectTimeout)).build();
                }
            }
        }

        return client;
    }

    /**
     * Runs a call in the background and gives a future of its result, for the methods of the api classes, which all block:
     * <pre>{@code
     * context.callAsync(() -> api.getLoLAPI().getSummonerAPI().getSummonerByPUUID(LeagueShard.EUW1, puuid))
     *        .thenAccept(summoner -> ...);
     * }</pre>
     * This keeps your own thread free, but a background thread waits for each call.
     * To make calls without any thread waiting, build them with {@code DataCallBuilder.buildAsync()}.
     *
     * @param call the call to make
     * @param <T>  what the call returns
     * @return a future of what the call returns
     */
    public <T> CompletableFuture<T> callAsync(java.util.function.Supplier<T> call)
    {
        return CompletableFuture.supplyAsync(call, ASYNC_EXECUTOR);
    }

    /**
     * Tells the object, and everything it contains, which context it was fetched with (see {@link ContextAware}).
     * Objects of the default context are left alone, as that is what they use when nothing is set.
     *
     * @param result something returned by a call
     * @param <T>    the type of the object
     * @return the same object
     */
    public <T> T bind(T result)
    {
        if (result != null && this != DEFAULT)
        {
            bind(result, java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>()));
        }

        return result;
    }

    private void bind(Object object, java.util.Set<Object> seen)
    {
        if (object == null || !seen.add(object))
        {
            return;
        }

        if (object instanceof ContextAware)
        {
            ((ContextAware) object).setContext(this);
        }

        if (object instanceof java.util.Collection)
        {
            ((java.util.Collection<?>) object).forEach(o -> bind(o, seen));
            return;
        }

        if (object instanceof Map)
        {
            ((Map<?, ?>) object).values().forEach(o -> bind(o, seen));
            return;
        }

        if (object instanceof Object[])
        {
            for (Object o : (Object[]) object)
            {
                bind(o, seen);
            }
            return;
        }

        // only the objects of this library are looked into
        for (Class<?> type = object.getClass(); type != null && type.getName().startsWith("no.stelar7.api.r4j.pojo."); type = type.getSuperclass())
        {
            for (java.lang.reflect.Field field : type.getDeclaredFields())
            {
                if (field.getType().isPrimitive() || field.getType().isEnum() || field.getType() == String.class
                    || java.lang.reflect.Modifier.isStatic(field.getModifiers()) || java.lang.reflect.Modifier.isTransient(field.getModifiers()))
                {
                    continue;
                }

                try
                {
                    field.setAccessible(true);
                    bind(field.get(object), seen);
                } catch (IllegalAccessException | RuntimeException e)
                {
                    // a field we can not look at just keeps the default context
                }
            }
        }
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
