package no.stelar7.api.l4j8.basic;


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
import java.util.function.BiFunction;
import java.util.logging.*;

public final class DataCall
{
    
    
    public static class DataCallBuilder
    {
        private final DataCall dc = new DataCall();
        
        private final BiFunction<String, String, String> merge      = (o, n) -> o + "," + n;
        private final BiFunction<String, String, String> mergeAsSet = (o, n) -> o + n;
        
        private static void updateRatelimiter(Platform server)
        {
            DataCall.limiter.get(server).updatePermitsPerX(appLimit.get(server));
        }
        
        /**
         * Print out as much data as possible about this call
         *
         * @param flag true if verbose
         * @return this
         */
        public DataCallBuilder asVerbose(final boolean flag)
        {
            this.dc.verbose = flag;
            return this;
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
            
            if (!this.dc.endpoint.name().startsWith("V3_STATIC"))
            {
                DataCall.limiter.get(this.dc.platform).acquire();
            }
            
            dc.urlHeaders.put("X-Riot-Token", creds.getBaseAPIKey());
            
            final String url = this.getURL();
            if (this.dc.verbose)
            {
                System.out.println(url);
            }
            
            final DataCallResponse response = this.getResponse(url);
            if (this.dc.verbose)
            {
                System.out.println(response);
            }
            
            if ((response.getResponseCode() == 200) || (response.getResponseCode() == 204))
            {
                final Object returnType = this.dc.endpoint.getType();
                Object       dtoobj     = Utils.getGson().fromJson(response.getResponseData(), (returnType instanceof Class<?>) ? (Class<?>) returnType : (Type) returnType);
                
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
            
            
            if (response.getResponseCode() == 400)
            {
                throw new APIResponseException(APIHTTPErrorReason.ERROR400, "L4J8 error.. contact developer to get this fixed ..." + response.getResponseData());
            }
            
            if (response.getResponseCode() == 404)
            {
                DataCall.LOGGER.log(Level.INFO, String.format("No data from url %s %s", url, response.getResponseData()));
                return null;
            }
            
            if (response.getResponseCode() == 429)
            {
                DataCall.LOGGER.log(Level.INFO, String.format("HIT 429! (%s)", response.getResponseData()));
                return this.build();
            }
            
            
            if (response.getResponseCode() >= 500)
            {
                DataCall.LOGGER.log(Level.INFO, "Server error, retrying");
                
                int attempts = (retrys != null && retrys.length == 1) ? retrys[0]++ : 1;
                if (attempts > 3)
                {
                    throw new APIResponseException(APIHTTPErrorReason.ERROR500, response.getResponseData());
                }
                
                return this.build(attempts);
            }
            
            DataCall.LOGGER.log(Level.WARNING, "Response Code:" + response.getResponseCode());
            DataCall.LOGGER.log(Level.WARNING, "Response Data:" + response.getResponseData());
            throw new APINoValidResponseException(response.getResponseData());
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
                
                if (this.dc.verbose)
                {
                    DataCall.LOGGER.log(Level.INFO, String.format(Constants.VERBOSE_STRING_FORMAT, "URL", url));
                    DataCall.LOGGER.log(Level.INFO, String.format(Constants.VERBOSE_STRING_FORMAT, "Request Method", con.getRequestMethod()));
                    DataCall.LOGGER.log(Level.INFO, String.format(Constants.VERBOSE_STRING_FORMAT, "POST", this.dc.postData));
                    DataCall.LOGGER.log(Level.INFO, String.format(Constants.VERBOSE_STRING_FORMAT, "Request Headers", ""));
                    con.getRequestProperties().forEach((key, value) -> DataCall.LOGGER.log(Level.INFO, String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value)));
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
                
                if (this.dc.verbose)
                {
                    DataCall.LOGGER.log(Level.INFO, String.format(Constants.VERBOSE_STRING_FORMAT, "Response Headers", ""));
                    con.getHeaderFields().forEach((key, value) -> DataCall.LOGGER.log(Level.INFO, String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value)));
                }
                
                
                // Log this for future use(?)
                final String limitCount = con.getHeaderField("X-App-Rate-Limit-Count");
                if (limitCount != null)
                {
                    final String[]     limits  = limitCount.split(",");
                    Map<Integer, Long> timeout = new HashMap<>();
                    for (final String limitPair : limits)
                    {
                        final String[] limit = limitPair.split(":");
                        final Long     call  = Long.parseLong(limit[0]);
                        final Integer  time  = Integer.parseInt(limit[1]);
                        timeout.put(time, call);
                    }
                    DataCall.appLimit.put(dc.platform, timeout);
                    updateRatelimiter(dc.platform);
                }
                
                // Log this for future use(?)
                final String methodLimitCount = con.getHeaderField("X-Method-Rate-Limit-Count");
                if (methodLimitCount != null)
                {
                    final String[]     limits  = methodLimitCount.split(",");
                    Map<Integer, Long> timeout = new HashMap<>();
                    for (final String limitPair : limits)
                    {
                        final String[] limit = limitPair.split(":");
                        final Long     call  = Long.parseLong(limit[0]);
                        final Integer  time  = Integer.parseInt(limit[1]);
                        timeout.put(time, call);
                    }
                    DataCall.methodLimit.put(dc.endpoint, timeout);
                }
                
                if (con.getResponseCode() == 429)
                {
                    final String limitType = RateLimitType.getBestMatch(con.getHeaderField("X-Rate-Limit-Type")).getReason();
                    String       reason    = String.format("%s %s %s %s %s%n", limitType, appLimit.toString(), methodLimit.toString(), limiter.get(dc.platform).getFirstCallInTime(), limiter.get(dc.platform).getCallCountInTime());
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
            }
            
            throw new APINoValidResponseException("Reached end of getResponse, without a valid response!!");
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
            
            // url[0] = url[0] + "?api_key=" + apiKey;
            //dc.urlData.forEach((k, v) -> url[0] = url[0] + "&" + k + "=" + v);
            
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
    
    private static final EnumMap<Platform, RateLimiter> limiter = new EnumMap<>(Platform.class);
    
    private final Map<String, String> urlParams  = new TreeMap<>();
    private final Map<String, String> urlData    = new TreeMap<>();
    private final Map<String, String> urlHeaders = new TreeMap<>();
    
    // How many calls our app has made in a timeframe, MapType<Timeframe, CallCount>
    private static final Map<Platform, Map<Integer, Long>>    appLimit    = new TreeMap<>();
    private static final Map<URLEndpoint, Map<Integer, Long>> methodLimit = new TreeMap<>();
    
    
    private String requestMethod = "GET";
    private String postData      = "";
    
    private Platform    platform;
    private URLEndpoint endpoint;
    
    private String baseURL = Constants.REQUEST_URL;
    
    public static boolean VERBOSE_DEFAULT  = false;
    public static boolean VERBOSE_LIMITING = false;
    
    private boolean verbose = VERBOSE_DEFAULT;
    
    
    private static APICredentials creds;
    
    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }
    
    public static APICredentials getCredentials()
    {
        return DataCall.creds;
    }
    
    public static void setCredentials(final APICredentials creds)
    {
        DataCall.creds = creds;
    }
    
    public static void setRatelimiter(RateLimiter... limiters)
    {
        Arrays.stream(limiters).forEach(l -> Arrays.stream(Platform.values()).forEach(p -> DataCall.limiter.put(p, l)));
    }
    
    static
    {
        Arrays.stream(Platform.values()).forEach(s -> DataCall.limiter.put(s, new BurstRateLimiter(Constants.DEV_KEY_LIMIT_10, Constants.DEV_KEY_LIMIT_600)));
    }
    
}
