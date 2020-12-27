package no.stelar7.api.r4j.basic.calling;


import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.EmptyCacheProvider;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.ratelimiting.RateLimiter;

import java.util.*;
import java.util.prefs.Preferences;

public final class DataCall
{
    
    private static final Map<Enum, Map<Enum, RateLimiter>>           limiter  = new HashMap<>();
    private static final Map<Enum, Map<Enum, Map<Integer, Integer>>> callData = new HashMap<>();
    
    private static APICredentials creds;
    private static CacheProvider  cache          = EmptyCacheProvider.INSTANCE;
    private static int            callStackSkip  = 5;
    private static int            callStackLimit = 5;
    private static long           maxSleep       = 10000;
    private static int            globalTimeout  = 0;
    
    private final Map<String, String> urlParams  = new TreeMap<>();
    private final Map<String, String> urlData    = new TreeMap<>();
    private final Map<String, String> urlHeaders = new TreeMap<>();
    
    private        Enum        platform;
    private        URLEndpoint endpoint;
    private        boolean     useLimiters       = true;
    private        long        sleep             = -1;
    private        int         readTimeout       = 0;
    private        int         connectTimeout    = 0;
    private        boolean     returnRawResponse = false;
    private        LeagueShard defaultPlatform   = LeagueShard.EUW1;
    private        String      urlProxy          = Constants.REQUEST_URL;
    private static Preferences ratelimiterCache  = Preferences.userRoot().node("no/stelar7/r4j");
    
    public static Map<Enum, Map<Enum, RateLimiter>> getLimiter()
    {
        return limiter;
    }
    
    public static Map<Enum, Map<Enum, Map<Integer, Integer>>> getCallData()
    {
        return callData;
    }
    
    public static Preferences getRatelimiterCache()
    {
        return ratelimiterCache;
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
        return this.sleep != -1 ? this.sleep : DataCall.getDefaultMaxSleep();
    }
    
    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }
    
    public static APICredentials getCredentials()
    {
        return DataCall.creds;
    }
    
    public static CacheProvider getCacheProvider()
    {
        return cache;
    }
    
    public static void setCacheProvider(CacheProvider provider)
    {
        cache = provider == null ? EmptyCacheProvider.INSTANCE : provider;
    }
    
    public static void setCredentials(final APICredentials creds)
    {
        DataCall.creds = creds;
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
        DataCall.maxSleep = sleep;
    }
    
    public static long getDefaultMaxSleep()
    {
        return DataCall.maxSleep;
    }
    
    public int getReadTimeout()
    {
        return readTimeout == 0 ? globalTimeout : readTimeout;
    }
    
    public void setReadTimeout(int readTimeout)
    {
        this.readTimeout = readTimeout;
    }
    
    public int getConnectTimeout()
    {
        return connectTimeout == 0 ? globalTimeout : connectTimeout;
    }
    
    public void setConnectTimeout(int connectTimeout)
    {
        this.connectTimeout = connectTimeout;
    }
    
    public static int getGlobalTimeout()
    {
        return globalTimeout;
    }
    
    public static void setGlobalTimeout(int globalTimeout)
    {
        DataCall.globalTimeout = globalTimeout;
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
