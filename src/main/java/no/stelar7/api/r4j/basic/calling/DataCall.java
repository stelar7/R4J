package no.stelar7.api.r4j.basic.calling;


import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.ratelimiting.RateLimiter;

import java.util.*;

/**
 * The data of a single call.
 * <p>
 * The static methods read and change the settings of the default {@link R4JContext}; they are kept so existing code keeps working.
 */
public final class DataCall
{
    /**
     * Used when no timeout is set, so a stalled connection can not block a thread (and its parallel-call permit) forever
     */
    public static final int DEFAULT_CONNECT_TIMEOUT = 10_000;
    public static final int DEFAULT_READ_TIMEOUT    = 30_000;

    private static int callStackSkip  = 5;
    private static int callStackLimit = 5;

    private final Map<String, String> urlParams  = new LinkedHashMap<>();
    private final Map<String, String> urlData    = new LinkedHashMap<>();
    private final Map<String, String> urlHeaders = new LinkedHashMap<>();

    private final R4JContext  context;
    private       Enum        platform;
    private       URLEndpoint endpoint;
    private       boolean     useLimiters       = true;
    private       long        sleep             = -1;
    private       int         readTimeout       = 0;
    private       int         connectTimeout    = 0;
    private       boolean     returnRawResponse = false;
    private       LeagueShard defaultPlatform   = LeagueShard.EUW1;
    private       String      urlProxy          = Constants.REQUEST_URL;

    public DataCall()
    {
        this(R4JContext.getDefault());
    }

    public DataCall(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
    }

    /**
     * @return the context this call belongs to
     */
    public R4JContext getContext()
    {
        return context;
    }

    public static Map<Enum, Map<Enum, RateLimiter>> getLimiter(ApiKeyType keyType)
    {
        return R4JContext.getDefault().getLimiters(keyType);
    }

    public Map<String, String> getUrlParams()
    {
        return urlParams;
    }

    public Map<String, String> getUrlData()
    {
        return urlData;
    }

    public Map<String, String> getUrlHeaders()
    {
        return urlHeaders;
    }

    public ApiKeyType getKeyUsedByHeadersUsed()
    {
        String keyUsed = urlHeaders.get(Constants.X_RIOT_TOKEN_HEADER_KEY);

        if (keyUsed == null || keyUsed.isEmpty())
        {
            return ApiKeyType.NONE;
        }

        // compared without the getters, as those throw when a key for another product is not set
        APICredentials creds = context.getCredentials();
        return creds == null ? ApiKeyType.UNKNOWN : creds.getKeyType(keyUsed);
    }

    public Enum getPlatform()
    {
        if (platform == null)
        {
            return defaultPlatform;
        }
        if (platform == LeagueShard.UNKNOWN)
        {
            return defaultPlatform;
        }
        return platform;
    }

    public URLEndpoint getEndpoint()
    {
        return endpoint;
    }

    public void setPlatform(Enum platform)
    {
        this.platform = platform;
    }

    public void setUseLimiters(boolean flag)
    {
        this.useLimiters = flag;
    }

    public void setEndpoint(URLEndpoint endpoint)
    {
        this.endpoint = endpoint;
    }

    public void setMaxSleep(long sleep)
    {
        this.sleep = sleep;
    }

    public long getMaxSleep()
    {
        return this.sleep != -1 ? this.sleep : context.getMaxSleep();
    }

    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }

    public static APICredentials getCredentials()
    {
        return R4JContext.getDefault().getCredentials();
    }

    public static CacheProvider getCacheProvider()
    {
        return R4JContext.getDefault().getCacheProvider();
    }

    public static void setNumberOfCallsAllowedInParallel(int numberOfCallsAllowedInParallel)
    {
        R4JContext.getDefault().setNumberOfCallsAllowedInParallel(numberOfCallsAllowedInParallel);
    }

    public static int getNumberOfCallsAllowedInParallel()
    {
        return R4JContext.getDefault().getNumberOfCallsAllowedInParallel();
    }

    public static void setCacheProvider(CacheProvider provider)
    {
        R4JContext.getDefault().setCacheProvider(provider);
    }

    public static void setCredentials(final APICredentials creds)
    {
        R4JContext.getDefault().setCredentials(creds);
    }

    /**
     * Takes in a proxy for the api.
     * The URL should contain the parts:
     * {platform}
     * {game}
     * {service}
     * {version}
     * {resource}
     * <p>
     * The default is https://{platform}.api.riotgames.com/{game}/{service}/{version}/{resource}
     *
     * @param proxy the url
     */
    public void setProxy(String proxy)
    {
        urlProxy = proxy == null ? Constants.REQUEST_URL : proxy;
    }

    public String getProxy()
    {
        return urlProxy;
    }

    public LeagueShard getDefaultPlatform()
    {
        return defaultPlatform;
    }

    public void setDefaultPlatform(LeagueShard defaultPlatform)
    {
        this.defaultPlatform = defaultPlatform;
    }

    public static int getCallStackSkip()
    {
        return callStackSkip;
    }

    public static void setCallStackSkip(int callStackSkip)
    {
        DataCall.callStackSkip = callStackSkip;
    }

    public static int getCallStackLimit()
    {
        return callStackLimit;
    }

    public static void setCallStackLimit(int callStackLimit)
    {
        DataCall.callStackLimit = callStackLimit;
    }

    public boolean useRatelimiter()
    {
        return useLimiters;
    }

    public static void setDefaultMaxSleep(long sleep)
    {
        R4JContext.getDefault().setMaxSleep(sleep);
    }

    public static long getDefaultMaxSleep()
    {
        return R4JContext.getDefault().getMaxSleep();
    }

    public int getReadTimeout()
    {
        if (readTimeout != 0)
        {
            return readTimeout;
        }

        return context.getGlobalTimeout() != 0 ? context.getGlobalTimeout() : DEFAULT_READ_TIMEOUT;
    }

    public void setReadTimeout(int readTimeout)
    {
        this.readTimeout = readTimeout;
    }

    public int getConnectTimeout()
    {
        if (connectTimeout != 0)
        {
            return connectTimeout;
        }

        return context.getGlobalTimeout() != 0 ? context.getGlobalTimeout() : DEFAULT_CONNECT_TIMEOUT;
    }

    public void setConnectTimeout(int connectTimeout)
    {
        this.connectTimeout = connectTimeout;
    }

    public static int getGlobalTimeout()
    {
        return R4JContext.getDefault().getGlobalTimeout();
    }

    /**
     * Sets the connect and read timeout (in ms) used by all calls that do not set their own.
     * 0 means "use the defaults" ({@link #DEFAULT_CONNECT_TIMEOUT} / {@link #DEFAULT_READ_TIMEOUT})
     *
     * @param globalTimeout the timeout in ms
     */
    public static void setGlobalTimeout(int globalTimeout)
    {
        R4JContext.getDefault().setGlobalTimeout(globalTimeout);
    }

    public static boolean shouldFailFastOnServiceRatelimit()
    {
        return R4JContext.getDefault().shouldFailFastOnServiceRatelimit();
    }

    /**
     * A service rate limit can sometimes be hit (limits that are not linked to your API key, but to the Riot server itself),
     * this flag determines whether this type of rate limit should fail immediately (throwing an APIEndpointCooldownException) or sleep until the cooldown expires.
     * Can be useful for high-throughput applications who prefer to fail fast so they can do other not limited calls instead of waiting for the cooldown to expire.
     */
    public static void setFailFastOnServiceRatelimit(boolean failFastOnServiceRatelimit)
    {
        R4JContext.getDefault().setFailFastOnServiceRatelimit(failFastOnServiceRatelimit);
    }

    public boolean shouldReturnRawResponse()
    {
        return returnRawResponse;
    }

    public void setShouldReturnRawResponse(boolean returnRawResponse)
    {
        this.returnRawResponse = returnRawResponse;
    }
}
