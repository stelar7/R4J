package no.stelar7.api.l4j8.basic.calling;


import no.stelar7.api.l4j8.basic.APICredentials;
import no.stelar7.api.l4j8.basic.cache.CacheProvider;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.ratelimiting.RateLimiter;

import javax.annotation.Nullable;
import java.util.*;

public final class DataCall
{
    
    private static final Map<Enum, Map<Enum, RateLimiter>>           limiter  = new HashMap<>();
    private static final Map<Enum, Map<Enum, Map<Integer, Integer>>> callData = new HashMap<>();
    
    private final Map<String, String> urlParams  = new TreeMap<>();
    private final Map<String, String> urlData    = new TreeMap<>();
    private final Map<String, String> urlHeaders = new TreeMap<>();
    
    private Platform    platform;
    private URLEndpoint endpoint;
    
    private static APICredentials creds;
    private static CacheProvider cache    = CacheProvider.EmptyProvider.INSTANCE;
    private static LogLevel      logLevel = LogLevel.NONE;
    
    
    public static Map<Enum, Map<Enum, RateLimiter>> getLimiter()
    {
        return limiter;
    }
    
    public static Map<Enum, Map<Enum, Map<Integer, Integer>>> getCallData()
    {
        return callData;
        
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
    
    public Platform getPlatform()
    {
        return platform;
    }
    
    public URLEndpoint getEndpoint()
    {
        return endpoint;
    }
    
    public void setPlatform(Platform platform)
    {
        this.platform = platform;
    }
    
    public void setEndpoint(URLEndpoint endpoint)
    {
        this.endpoint = endpoint;
    }
    
    public static void setLogLevel(LogLevel level)
    {
        DataCall.logLevel = level;
    }
    
    public static LogLevel getLogLevel()
    {
        return DataCall.logLevel;
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
    
    public static void setCacheProvider(@Nullable CacheProvider provider)
    {
        cache = provider == null ? CacheProvider.EmptyProvider.INSTANCE : provider;
    }
    
    public static void setCredentials(final APICredentials creds)
    {
        DataCall.creds = creds;
    }
    
}
