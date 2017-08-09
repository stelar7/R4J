package no.stelar7.api.l4j8.basic.calling;

import no.stelar7.api.l4j8.basic.Utils;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.exceptions.*;
import no.stelar7.api.l4j8.basic.ratelimiting.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class DataCallBuilder
{
    private final DataCall dc = new DataCall();
    
    private static final BiFunction<String, String, String> MERGE        = (o, n) -> o + "," + n;
    private static final BiFunction<String, String, String> MERGE_AS_SET = (o, n) -> o + n;
    
    private String requestMethod = "GET";
    private String postData      = "";
    
    private static void updateRatelimiter(Enum server, Enum endpoint)
    {
        DataCall.getLimiter().get(server).get(endpoint).updatePermitsTakenPerX(DataCall.getCallData().get(server).get(endpoint));
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
        if (DataCall.getCredentials() == null)
        {
            throw new APIUnsupportedActionException("No API Creds set!");
        }
        
        dc.getUrlHeaders().put("X-Riot-Token", DataCall.getCredentials().getBaseAPIKey());
        final String url = this.getURL();
        if (DataCall.getLogLevel().ordinal() >= LogLevel.INFO.ordinal())
        {
            System.err.format("Trying url: %s%n", url);
        }
        
        // method limit
        applyLimit(this.dc.getPlatform(), this.dc.getEndpoint());
        // app limit
        applyLimit(this.dc.getPlatform(), this.dc.getPlatform());
        
        
        final DataCallResponse response = this.getResponse(url);
        if (DataCall.getLogLevel().ordinal() >= LogLevel.INFO.ordinal())
        {
            System.err.println(response);
        }
        
        try
        {
            switch (response.getResponseCode())
            {
                case 200:
                case 204:
                {
                    final Object returnType = this.dc.getEndpoint().getType();
                    Object       dtoobj     = Utils.getGson().fromJson(response.getResponseData(), (returnType instanceof Class<?>) ? (Class<?>) returnType : (Type) returnType);
                    
                    return process(dtoobj);
                }
                
                case 403:
                {
                    String reasonText = "Your Api key is invalid!\n";
                    reasonText += "You may be trying to call a endpoint you dont have access to\n";
                    reasonText += "or if you just regenerated it; wait a few seconds, then try again\n";
                    throw new APIResponseException(APIHTTPErrorReason.ERROR_403, reasonText + response.getResponseData());
                }
                
                case 404:
                {
                    return null;
                }
                
                case 429:
                    if (response.getResponseData().startsWith(RateLimitType.LIMIT_UNDERLYING.getReason()) || response.getResponseData().startsWith(RateLimitType.LIMIT_SERVICE.getReason()))
                    {
                        int attempts = (retrys != null && retrys.length == 1) ? ++retrys[0] : 1;
                        if (attempts > 2)
                        {
                            System.err.println("Service ratelimit reached too many times, waiting 10 second and retrying");
                            Thread.sleep(10000);
                            return this.build(attempts);
                        }
                        
                        System.err.format("Service ratelimit reached (%s / 3 times), waiting 1 second and retrying%n", attempts);
                        Thread.sleep(1000);
                        return this.build(attempts);
                    } else
                    {
                        System.err.println(response.getResponseData());
                        System.err.println("429 ratelimit hit! Please do not restart your application to refresh the timer!");
                        System.err.println("This isnt supposed to happen unless you restarted your app before the last limit was hit!");
                    }
                    
                    return this.build();
                
                case 500:
                case 502:
                case 503:
                case 504:
                {
                    int attempts = (retrys != null && retrys.length == 1) ? ++retrys[0] : 1;
                    System.err.format("Server error, waiting 1 second and retrying%n");
                    Thread.sleep(1000);
                    
                    if (attempts > 3)
                    {
                        throw new APIResponseException(APIHTTPErrorReason.ERROR_500, response.getResponseData());
                    }
                    
                    return this.build(attempts);
                }
                
                default:
                {
                    break;
                }
            }
            
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        System.err.println("Response Code:" + response.getResponseCode());
        System.err.println("Response Data:" + response.getResponseData());
        throw new APINoValidResponseException(response.getResponseData());
    }
    
    
    // TODO
    private void applyLimit(Enum platform, Enum endpoint)
    {
        Map<Enum, RateLimiter> limits = DataCall.getLimiter().get(platform);
        if (limits != null)
        {
            RateLimiter limitr = limits.get(endpoint);
            if (limitr != null)
            {
                limitr.acquire();
            }
        }
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
                f.set(sum, this.dc.getPlatform());
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
            this.dc.getUrlHeaders().forEach(con::setRequestProperty);
            
            con.setRequestMethod(requestMethod);
            
            if (DataCall.getLogLevel().ordinal() >= LogLevel.EXTENDED_INFO.ordinal())
            {
                System.err.format(Constants.VERBOSE_STRING_FORMAT, "URL", url);
                System.err.format(Constants.VERBOSE_STRING_FORMAT, "Request Method", con.getRequestMethod());
                System.err.format(Constants.VERBOSE_STRING_FORMAT, "POST", this.postData);
                System.err.format(Constants.VERBOSE_STRING_FORMAT, "Request Headers", "");
                
                con.getRequestProperties().forEach((key, value) -> System.err.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value));
            }
            
            if (!this.postData.isEmpty())
            {
                con.setDoOutput(true);
                final DataOutputStream writer = new DataOutputStream(con.getOutputStream());
                writer.writeBytes(this.postData);
                writer.flush();
                writer.close();
            }
            
            con.connect();
            
            if (DataCall.getLogLevel().ordinal() >= LogLevel.EXTENDED_INFO.ordinal())
            {
                System.err.format(Constants.VERBOSE_STRING_FORMAT, "Response Headers", "");
                con.getHeaderFields().forEach((key, value) -> System.err.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value));
            }
            
            
            String appA    = con.getHeaderField("X-App-Rate-Limit");
            String appB    = con.getHeaderField("X-App-Rate-Limit-Count");
            String methodA = con.getHeaderField("X-Method-Rate-Limit");
            String methodB = con.getHeaderField("X-Method-Rate-Limit-Count");
            
            if (appA == null && DataCall.getLogLevel().ordinal() >= LogLevel.DEBUG.ordinal())
            {
                System.err.println("Header 'X-App-Rate-Limit' missing from call: " + getURL());
            }
            
            if (appA != null)
            {
                createRatelimiterIfMissing(appA, dc.getPlatform(), dc.getPlatform());
                saveHeaderRateLimit(appB, dc.getPlatform(), dc.getPlatform());
            }
            
            if (methodA != null && DataCall.getLogLevel().ordinal() >= LogLevel.DEBUG.ordinal())
            {
                System.err.println("Header 'X-Method-Rate-Limit' missing from call: " + getURL());
            }
            
            if (methodA != null)
            {
                createRatelimiterIfMissing(methodA, dc.getPlatform(), dc.getEndpoint());
                saveHeaderRateLimit(methodB, dc.getPlatform(), dc.getEndpoint());
            }
            
            
            if (con.getResponseCode() == 429)
            {
                final RateLimitType limitType = RateLimitType.getBestMatch(con.getHeaderField("X-Rate-Limit-Type"));
                String              reason    = String.format("%s%n%s", limitType.getReason(), DataCall.getLimiter().get(dc.getPlatform()));
                
                if (limitType == RateLimitType.LIMIT_METHOD)
                {
                    RateLimiter limter = DataCall.getLimiter().get(this.dc.getPlatform()).get(this.dc.getEndpoint());
                    limter.updateSleep(con.getHeaderField("Retry-After"));
                    limter.resetCalls();
                }
                
                if (limitType == RateLimitType.LIMIT_USER)
                {
                    RateLimiter limter = DataCall.getLimiter().get(this.dc.getPlatform()).get(this.dc.getPlatform());
                    limter.updateSleep(con.getHeaderField("Retry-After"));
                    limter.resetCalls();
                }
                
                return new DataCallResponse(con.getResponseCode(), reason);
            }
            
            InputStream stream = (con.getResponseCode() == 200) ? con.getInputStream() : con.getErrorStream();
            
            if (stream == null)
            {
                return new DataCallResponse(APIHTTPErrorReason.ERROR_500.getCode(), APIHTTPErrorReason.ERROR_500.getReason());
            }
            
            try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)))
            {
                br.lines().forEach(data::append);
            }
            
            con.disconnect();
            
            return new DataCallResponse(con.getResponseCode(), data.toString());
        } catch (final IOException e)
        {
            e.printStackTrace();
            return new DataCallResponse(APIHTTPErrorReason.ERROR_599.getCode(), APIHTTPErrorReason.ERROR_599.getReason());
        }
    }
    
    private void createRatelimiterIfMissing(String methodA, Enum platform, Enum endpoint)
    {
        Map<Enum, RateLimiter> child = DataCall.getLimiter().getOrDefault(platform, new HashMap<>());
        
        RateLimiter oldLimit   = child.getOrDefault(endpoint, createLimiter(endpoint, methodA));
        RateLimiter newerLimit = createLimiter(endpoint, methodA);
        
        if (!oldLimit.equals(newerLimit))
        {
            newerLimit.mergeFrom(oldLimit);
            child.put(endpoint, newerLimit);
        }
        
        DataCall.getLimiter().put(platform, child);
        
    }
    
    private void saveHeaderRateLimit(String limitCount, Enum platform, Enum endpoint)
    {
        
        Map<Enum, Map<Integer, Integer>> parent = DataCall.getCallData().getOrDefault(platform, new HashMap<>());
        Map<Integer, Integer>            child  = parent.getOrDefault(endpoint, new HashMap<>());
        
        child.putAll(parseLimitFromHeader(limitCount));
        parent.put(endpoint, child);
        DataCall.getCallData().put(platform, parent);
        
        updateRatelimiter(platform, endpoint);
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
    
    public RateLimiter createLimiter(Enum key, String limitCount)
    {
        Map<Integer, Integer> timeout = parseLimitFromHeader(limitCount);
        
        List<RateLimit> limits = new ArrayList<>();
        for (Entry<Integer, Integer> entry : timeout.entrySet())
        {
            limits.add(new RateLimit(entry.getValue(), entry.getKey(), TimeUnit.SECONDS));
        }
        
        if (DataCall.getLogLevel().ordinal() >= LogLevel.DEBUG.ordinal())
        {
            System.err.println("Creating Missing Ratelimit For " + key);
            System.err.println(limits);
        }
        
        return new BurstRateLimiter(limits.toArray(new RateLimit[limits.size()]));
    }
    
    
    /**
     * Generates the URL to use for the call.
     *
     * @return the URL to use for the call.
     */
    private String getURL()
    {
        String[] url = {dc.getProxy()};
        url[0] = url[0].replace(Constants.PLATFORM_PLACEHOLDER, dc.getPlatform().toString());
        url[0] = url[0].replace(Constants.GAME_PLACEHOLDER, dc.getEndpoint().getGame());
        url[0] = url[0].replace(Constants.SERVICE_PLACEHOLDER, dc.getEndpoint().getService());
        url[0] = url[0].replace(Constants.VERSION_PLACEHOLDER, dc.getEndpoint().getVersion());
        url[0] = url[0].replace(Constants.RESOURCE_PLACEHOLDER, dc.getEndpoint().getResource());
        dc.getUrlParams().forEach((k, v) -> url[0] = url[0].replace(k, v));
        
        boolean first = true;
        for (Entry<String, String> pair : dc.getUrlData().entrySet())
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
        this.dc.setEndpoint(endpoint);
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
        this.dc.getUrlHeaders().merge(key, value, MERGE);
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
        this.postData = data;
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
        this.requestMethod = method;
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
        this.dc.setPlatform(server);
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
        this.dc.getUrlData().merge(key, (this.dc.getUrlData().get(key) != null) ? ("&" + key + "=" + value) : value, MERGE_AS_SET);
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
        this.dc.getUrlData().merge(key, value, MERGE);
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
        this.dc.getUrlParams().merge(key, value, MERGE);
        return this;
    }
}
