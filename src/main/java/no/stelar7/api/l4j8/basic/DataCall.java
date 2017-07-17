package no.stelar7.api.l4j8.basic;


import no.stelar7.api.l4j8.basic.cache.CacheProvider;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.exceptions.*;
import no.stelar7.api.l4j8.basic.ratelimiting.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import javax.annotation.Nullable;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.logging.*;

public final class DataCall
{
    
    public static class DataCallBuilder
    {
        private final DataCall dc = new DataCall();
        
        private final BiFunction<String, String, String> merge      = (o, n) -> o + "," + n;
        private final BiFunction<String, String, String> mergeAsSet = (o, n) -> o + n;
        
        private static void updateRatelimiter(Enum server)
        {
            DataCall.limiter.get(server).updatePermitsPerX(callData.get(server));
        }
        
        /**
         * Puts together all the data, and then returns an object representing the JSON from the call
         *
         * @param retrys the amount of retries already done (should not be passed in!)
         * @return an object generated from the requested JSON
         * @throws APINoValidResponseException if the request failed in some fashion
         */
        public Object build(int... retrys)
        {
            if (DataCall.creds == null)
            {
                throw new APIUnsupportedAction("No API Creds set!");
            }
            
            if (DataCall.limiter.get(this.dc.endpoint) != null)
            {
                DataCall.limiter.get(this.dc.endpoint).acquire();
            }
            
            
            if (!this.dc.endpoint.name().startsWith("V3_STATIC"))
            {
                if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                {
                    System.err.println("Call to platform limited endpoint!");
                    System.err.println(this.dc.endpoint.name());
                }
                
                if (DataCall.limiter.get(this.dc.platform) != null)
                {
                    DataCall.limiter.get(this.dc.platform).acquire();
                }
                
            } else
            {
                if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                {
                    System.err.println("Call to unlimited endpoint!");
                    System.err.println(this.dc.endpoint.name());
                }
            }
            
            dc.urlHeaders.put("X-Riot-Token", creds.getBaseAPIKey());
            
            final String url = this.getURL();
            if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
            {
                System.err.println(url);
            }
            
            final DataCallResponse response = this.getResponse(url);
            if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
            {
                System.err.println(response);
            }
            
            if ((response.getResponseCode() == 200) || (response.getResponseCode() == 204))
            {
                final Object returnType = this.dc.endpoint.getType();
                Object       dtoobj     = Utils.getGson().fromJson(response.getResponseData(), (returnType instanceof Class<?>) ? (Class<?>) returnType : (Type) returnType);
                
                return process(dtoobj);
            }
            
            
            if (response.getResponseCode() == 400)
            {
                if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                {
                    System.err.println(url);
                }
                throw new APIResponseException(APIHTTPErrorReason.ERROR_400, "L4J8 error.. contact developer to get this fixed ..." + response.getResponseData());
            }
            
            if (response.getResponseCode() == 403)
            {
                if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                {
                    System.err.println(url);
                }
                throw new APIResponseException(APIHTTPErrorReason.ERROR_403, "Your Api key is invalid! If you just regenerated it, wait a few seconds, then try again. " + response.getResponseData());
            }
            
            if (response.getResponseCode() == 404)
            {
                if (logLevel.ordinal() >= LogLevel.INFO.ordinal())
                {
                    System.err.format("No data from url %s %s%n", url, response.getResponseData());
                }
                return null;
            }
            
            if (response.getResponseCode() == 429)
            {
                if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                {
                    System.err.println(response.getResponseData());
                }
                
                if (response.getResponseData().startsWith(RateLimitType.LIMIT_UNDERLYING.getReason()) || response.getResponseData().startsWith(RateLimitType.LIMIT_SERVICE.getReason()))
                {
                    try
                    {
                        int attempts = (retrys != null && retrys.length == 1) ? ++retrys[0] : 1;
                        if (attempts > 2)
                        {
                            System.err.println("Service ratelimit reached too many times, waiting 7 second and retrying");
                            Thread.sleep(7000);
                            return this.build(attempts);
                        }
                        
                        System.err.format("Service ratelimit reached (%s / 3 times), waiting 1 second and retrying%n", attempts);
                        Thread.sleep(1000);
                        return this.build(attempts);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                
                return this.build();
            }
            
            if (response.getResponseCode() >= 500)
            {
                if (logLevel.ordinal() >= LogLevel.INFO.ordinal())
                {
                    System.err.println("Server error, retrying");
                }
                
                int attempts = (retrys != null && retrys.length == 1) ? retrys[0]++ : 1;
                if (attempts > 3)
                {
                    throw new APIResponseException(APIHTTPErrorReason.ERROR_500, response.getResponseData());
                }
                
                return this.build(attempts);
            }
            
            DataCall.LOGGER.log(Level.WARNING, "Response Code:" + response.getResponseCode());
            DataCall.LOGGER.log(Level.WARNING, "Response Data:" + response.getResponseData());
            throw new APINoValidResponseException(response.getResponseData());
        }
        
        private Object process(Object dtoobj)
        {
            try
            {
                if (dtoobj instanceof Summoner)
                {
                    Summoner sum = (Summoner) dtoobj;
                    Field    f   = sum.getClass().getDeclaredField("platform");
                    f.setAccessible(true);
                    f.set(sum, this.dc.platform);
                }
                
            } catch (NoSuchFieldException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
            
            return dtoobj;
        }
        
        
        /**
         * Opens a connection to the URL, then reads the data into a Response.
         *
         * @param url the URL to call
         * @return a DataCallResponse with the data from the call
         * @throws APINoValidResponseException if the datacall failed in any fashion
         */
        private DataCallResponse getResponse(final String url)
        {
            final StringBuilder data = new StringBuilder();
            try
            {
                final HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
                
                con.setUseCaches(false);
                con.setDefaultUseCaches(false);
                con.setRequestProperty("User-Agent", "L4J8");
                con.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8");
                con.setRequestProperty("Accept-Language", "en-US");
                con.setRequestProperty("Cache-Control", "no-store,max-age=0,no-cache");
                con.setRequestProperty("Expires", "0");
                con.setRequestProperty("Pragma", "no-cache");
                con.setRequestProperty("Connection", "keep-alive");
                con.setRequestProperty("Content-Type", "application/json");
                this.dc.urlHeaders.forEach(con::setRequestProperty);
                
                con.setRequestMethod(this.dc.requestMethod);
                
                if (logLevel.ordinal() >= LogLevel.EXTENDED_INFO.ordinal())
                {
                    System.err.format(Constants.VERBOSE_STRING_FORMAT, "URL", url);
                    System.err.format(Constants.VERBOSE_STRING_FORMAT, "Request Method", con.getRequestMethod());
                    System.err.format(Constants.VERBOSE_STRING_FORMAT, "POST", this.dc.postData);
                    System.err.format(Constants.VERBOSE_STRING_FORMAT, "Request Headers", "");
                    
                    con.getRequestProperties().forEach((key, value) -> System.err.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value));
                }
                
                if (!this.dc.postData.isEmpty())
                {
                    con.setDoOutput(true);
                    final DataOutputStream writer = new DataOutputStream(con.getOutputStream());
                    writer.writeBytes(this.dc.postData);
                    writer.flush();
                    writer.close();
                }
                
                con.connect();
                
                if (logLevel.ordinal() >= LogLevel.EXTENDED_INFO.ordinal())
                {
                    System.err.format(Constants.VERBOSE_STRING_FORMAT, "Response Headers", "");
                    con.getHeaderFields().forEach((key, value) -> System.err.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value));
                }
                
                
                String appA    = con.getHeaderField("X-App-Rate-Limit");
                String appB    = con.getHeaderField("X-App-Rate-Limit-Count");
                String methodA = con.getHeaderField("X-Method-Rate-Limit");
                String methodB = con.getHeaderField("X-Method-Rate-Limit-Count");
                
                if (appA != null)
                {
                    createRatelimiterIfMissing(appA, dc.platform);
                    saveHeaderRateLimit(appB, dc.platform);
                } else
                {
                    if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                    {
                        System.err.println("Header 'X-App-Rate-Limit' missing from call: " + getURL());
                    }
                }
                
                if (methodA != null)
                {
                    createRatelimiterIfMissing(methodA, dc.endpoint);
                    saveHeaderRateLimit(methodB, dc.endpoint);
                } else
                {
                    if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
                    {
                        System.err.println("Header 'X-Method-Rate-Limit' missing from call: " + getURL());
                    }
                }
                
                if (con.getResponseCode() == 429)
                {
                    final RateLimitType limitType = RateLimitType.getBestMatch(con.getHeaderField("X-Rate-Limit-Type"));
                    String              reason    = String.format("%s%n%s%n%s%n%s%n", limitType.getReason(), callData, limiter.get(dc.platform).getCallCountInTime(), limiter.get(dc.platform).getFirstCallInTime());
                    return new DataCallResponse(con.getResponseCode(), reason);
                }
                
                InputStream stream = (con.getResponseCode() == 200) ? con.getInputStream() : con.getErrorStream();
                
                try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)))
                {
                    br.lines().forEach(data::append);
                }
                
                con.disconnect();
                
                return new DataCallResponse(con.getResponseCode(), data.toString());
            } catch (final IOException e)
            {
                DataCall.LOGGER.log(Level.WARNING, e.getMessage(), e);
                return new DataCallResponse(APIHTTPErrorReason.ERROR_599.getCode(), APIHTTPErrorReason.ERROR_599.getReason());
            }
        }
        
        private Map<Integer, Integer> parseLimitFromHeader(String headerValue)
        {
            final String[]        limits  = headerValue.split(",");
            Map<Integer, Integer> timeout = new HashMap<>();
            for (final String limitPair : limits)
            {
                final String[] limit = limitPair.split(":");
                final Integer  call  = Integer.parseInt(limit[0]);
                final Integer  time  = Integer.parseInt(limit[1]);
                timeout.put(time, call);
            }
            
            return timeout;
        }
        
        private void createRatelimiterIfMissing(String limitCount, Enum type)
        {
            DataCall.limiter.computeIfAbsent(type, (Enum key) -> createLimiter(key, limitCount));
        }
        
        public RateLimiter createLimiter(Enum key, String limitCount)
        {
            // X-App-Rate-Limit    : [100:120,20:1]
            Map<Integer, Integer> timeout = parseLimitFromHeader(limitCount);
            
            List<RateLimit> limits = new ArrayList<>();
            for (Entry<Integer, Integer> entry : timeout.entrySet())
            {
                limits.add(new RateLimit(entry.getValue(), entry.getKey(), TimeUnit.SECONDS));
            }
            
            if (logLevel.ordinal() >= LogLevel.DEBUG.ordinal())
            {
                System.err.println("Creating Missing Ratelimit For " + key);
                System.err.println(limits);
            }
            
            return new BurstRateLimiter(limits.toArray(new RateLimit[limits.size()]));
        }
        
        private void saveHeaderRateLimit(String limitCount, Enum type)
        {
            Map<Integer, Integer> timeout = parseLimitFromHeader(limitCount);
            DataCall.callData.put(type, timeout);
            updateRatelimiter(type);
        }
        
        
        /**
         * Generates the URL to use for the call.
         *
         * @return the URL to use for the call.
         */
        private String getURL()
        {
            String[] url = {dc.baseURL};
            url[0] = url[0].replace(Constants.PLATFORM_PLACEHOLDER, dc.platform.toString());
            url[0] = url[0].replace(Constants.GAME_PLACEHOLDER, dc.endpoint.getGame());
            url[0] = url[0].replace(Constants.SERVICE_PLACEHOLDER, dc.endpoint.getService());
            url[0] = url[0].replace(Constants.VERSION_PLACEHOLDER, dc.endpoint.getVersion());
            url[0] = url[0].replace(Constants.RESOURCE_PLACEHOLDER, dc.endpoint.getResource());
            dc.urlParams.forEach((k, v) -> url[0] = url[0].replace(k, v));
            
            boolean first = true;
            for (Entry<String, String> pair : dc.urlData.entrySet())
            {
                char token = first ? '?' : '&';
                
                if (first)
                {
                    first = !first;
                }
                
                url[0] = url[0] + token + pair.getKey() + '=' + pair.getValue();
            }
            
            return url[0];
        }
        
        
        /**
         * Sets the endpoint to make the call to
         *
         * @param endpoint the endpoint to make the call to
         * @return this
         */
        public DataCallBuilder withEndpoint(final URLEndpoint endpoint)
        {
            this.dc.endpoint = endpoint;
            return this;
        }
        
        /**
         * Sets the headers to use with the call
         *
         * @param key   the header key
         * @param value the header value
         * @return this
         */
        public DataCallBuilder withHeader(final String key, final String value)
        {
            this.dc.urlHeaders.merge(key, value, this.merge);
            return this;
        }
        
        /**
         * Sets the data to send with the request if its a POST call
         *
         * @param data the data to send
         * @return this
         */
        public DataCallBuilder withPostData(final String data)
        {
            this.dc.postData = data;
            return this;
        }
        
        
        /**
         * The request-method on the call (usually GET or POST)
         *
         * @param method the request method
         * @return this
         */
        public DataCallBuilder withRequestMethod(final String method)
        {
            this.dc.requestMethod = method;
            return this;
        }
        
        /**
         * Set the platform to make this call to. (ie. EUW1)
         *
         * @param server the server to make the call to
         * @return this
         */
        public DataCallBuilder withPlatform(final Platform server)
        {
            this.dc.platform = server;
            return this;
        }
        
        /**
         * Replaces placeholders in the URL (ie. {region})
         *
         * @param key   The key to replace (ie. {region})
         * @param value The data to replace it with (ie. EUW)
         * @return this
         */
        public DataCallBuilder withURLDataAsSet(final String key, final String value)
        {
            this.dc.urlData.merge(key, (this.dc.urlData.get(key) != null) ? ("&" + key + "=" + value) : value, this.mergeAsSet);
            return this;
        }
        
        
        /**
         * Replaces placeholders in the URL (ie. {region})
         *
         * @param key   The key to replace (ie. {region})
         * @param value The data to replace it with (ie. EUW)
         * @return this
         */
        public DataCallBuilder withURLData(final String key, final String value)
        {
            this.dc.urlData.merge(key, value, this.merge);
            return this;
        }
        
        /**
         * Adds parameters to the url (ie. ?api_key)
         *
         * @param key   the parameter to add (ie. api_key)
         * @param value the value to add after the parameter (ie. 6fa459ea-ee8a-3ca4-894e-db77e160355e)
         * @return this
         */
        public DataCallBuilder withURLParameter(final String key, final String value)
        {
            this.dc.urlParams.merge(key, value, this.merge);
            return this;
        }
        
        public DataCallBuilder withURL(String url)
        {
            this.dc.baseURL = url;
            return this;
        }
        
    }
    
    private static class DataCallResponse
    {
        private final int    responseCode;
        private final String responseData;
        
        DataCallResponse(final int a, final String b)
        {
            this.responseCode = a;
            this.responseData = b;
        }
        
        /**
         * Response code
         */
        int getResponseCode()
        {
            return this.responseCode;
        }
        
        /**
         * Response data
         */
        String getResponseData()
        {
            return this.responseData;
        }
        
        @Override
        public String toString()
        {
            return "DataCallResponse{" +
                   "responseCode=" + responseCode +
                   ", responseData='" + responseData + '\'' +
                   '}';
        }
    }
    
    
    private static final Logger LOGGER = Logger.getGlobal();
    
    private static final Map<Enum, RateLimiter>           limiter  = new HashMap<>();
    private static final Map<Enum, Map<Integer, Integer>> callData = new HashMap<>();
    
    private final Map<String, String> urlParams  = new TreeMap<>();
    private final Map<String, String> urlData    = new TreeMap<>();
    private final Map<String, String> urlHeaders = new TreeMap<>();
    
    private String baseURL       = Constants.REQUEST_URL;
    private String requestMethod = "GET";
    private String postData      = "";
    
    private Platform    platform;
    private URLEndpoint endpoint;
    
    
    private static APICredentials creds;
    private static CacheProvider cache = CacheProvider.EMPTY;
    
    private static LogLevel logLevel = LogLevel.NONE;
    
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
        cache = provider == null ? CacheProvider.EMPTY : provider;
    }
    
    public static void setCredentials(final APICredentials creds)
    {
        DataCall.creds = creds;
    }
    
}
