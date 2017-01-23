package no.stelar7.api.l4j8.basic;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.exceptions.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.*;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import java.util.logging.*;

public class DataCall
{
    
    e state
    static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    
    private static final Double DEFAULT_LIMITER_PERMITS_PER_10_MINUTES = 500.0;
    
    private static final Double DEFAULT_LIMITER_10_MINUTES = 600.0;
    public static final  Logger LOGGER                     = Logger.getGlobal();
    
    private static APICredentials creds;
    private static final EnumMap<Server, RateLimiter> limiter = new EnumMap<>(Server.class);
    
    public statie String
    postData ="";
    
    private final Map<String, String> urlData   = new TreeMap<>();
    private final Map<String, String> urlParams = new TreeMap<>();
    
    private final Map<String, String> urlHeaders = new TreeMap<>();
    
    private boolean verbose = false;
    
    private String requestMethod = "GET";
    
    private Server server;
    
    private Serv
    c DataCallBuilder
    
    builder()
    {
        return new DataCallBuilder();
    }
    
    privater region;
    
    private URLE
    public static class DataCallBuilder
    {
        final         StringBuilder                      urlBuilder = new StringBuilder();
        private final DataCall                           dc         = new DataCall();
        private final BiFunction<String, String, String> merge      = (o, n) -> o + "," + n;
        
        public static APICredentials getCredentials()
        {
            return DataCall.creds;
        }
        
        public static void setCredentials(final APICredentials creds)
        {
            DataCall.creds = creds;
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
         * @return an object generated from the requested JSON
         * @throws APINoValidResponseException if the request failed in some fashion
         */
        public Object build()
        {
            
            if (!this.dc.endpoint.isAvalibleFrom(this.dc.server))
            {
                throw new APIServerEndpointMissmatchException(this.dc.endpoint.toString() + " not avalible from " + this.dc.server
                                                                                                                        .asURLFormat());
            }
            
            final boolean isServerRatelimited = this.dc.server.isLimited();
            final boolean isToRatelimitedURL  = !this.dc.endpoint.getValue().startsWith(DataCall.HTTP);
            
            if (isServerRatelimited && isToRatelimitedURL)
            {
                DataCall.limiter.get(this.dc.server).acquire();
            }
            
            final String           url      = this.getURL();
            final DataCallResponse response = this.getResponse(url);
            
            if ((response.getResponseCode() == 200) || (response.getResponseCode() == 204))
            {
                final Object type = this.dc.endpoint.getType();
                Object       dtoobj;
                if (type instanceof Class<?>)
                {
                    dtoobj = new Gson().fromJson(response.getResponseData(), (Class<?>) this.dc.endpoint.getType());
                } else
                {
                    dtoobj = new Gson().fromJson(response.getResponseData(), (Type) this.dc.endpoint.getType());
                }
                
                if (dtoobj instanceof Map)
                {
                    final Map<?, ?> map = (Map<?, ?>) dtoobj;
                    map.values().stream().filter(value -> value instanceof Summoner).forEach(this::setServerOnSummoner);
                }
                
                return dtoobj;
            }
            
            if (response.getResponseCode() == 400)
            {
                throw new APIResponseException(APIHTTPErrorReason.ERROR400, "API call error.. contact developer to get this fixed");
            }
            
            if (response.getResponseCode() == 404)
            {
                throw new APIResponseException(APIHTTPErrorReason.ERROR404, response.getResponseData());
            }
            
            if (response.getResponseCode() == 429)
            {
                
                if (this.dc.verbose)
                {
                    DataCall.LOGGER.log(Level.INFO, "HIT 429");
                }
                
                throw new APIResponseException(APIHTTPErrorReason.ERROR429, response.getResponseData());
                
            }
            
            if (response.getResponseCode() == 500)
            {
                if (this.dc.verbose)
                {
                    DataCall.LOGGER.log(Level.INFO, "HIT 500 ERROR");
                }
                
                throw new APIResponseException(APIHTTPErrorReason.ERROR500, response.getResponseData());
                
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
                    con.getRequestProperties()
                       .forEach((key, value) -> DataCall.LOGGER.log(Level.INFO, String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value)));
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
                    con.getHeaderFields()
                       .forEach((key, value) -> DataCall.LOGGER.log(Level.INFO, String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value)));
                }
                
                final String limitType = RateLimitType.getBestMatch(con.getHeaderField("X-Rate-Limit-Type")).getReason();
                
                if (con.getResponseCode() != 200)
                {
                    return new DataCallResponse(con.getResponseCode(), limitType);
                }
                
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)))
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
            if (this.urlBuilder.length() != 0)
            {
                return this.urlBuilder.toString();
            }
            
            if (!this.dc.endpoint.getValue().startsWith(DataCall.HTTP))
            {
                this.urlBuilder.append(DataCall.HTTPS);
            } else
            {
                this.dc.server = Server.GLOBAL;
            }
            
            this.urlBuilder.append(this.dc.server.getURL()).append(this.dc.endpoint.getValue());
            
            this.withURLData("{region}", this.dc.region == null ? this.dc.server.asURLFormat() : this.dc.region.asURLFormat());
            this.withURLData("{version}", this.dc.endpoint.getVersion());
            
            this.dc.urlData.forEach((k, v) ->
                                    {
                                        String temp = this.urlBuilder.toString();
                                        temp = temp.replace(k, v);
                                        this.urlBuilder.setLength(0);
                                        this.urlBuilder.append(temp);
                                    });
            
            this.dc.urlParams.put("api_key", DataCall.creds.getBaseAPIKey());
            
            final Optional<String> firstKey = this.dc.urlParams.keySet().stream().findFirst();
            if (firstKey.isPresent())
            {
                this.urlBuilder.append("?").append(firstKey.get()).append("=").append(this.dc.urlParams.get(firstKey.get()));
                this.dc.urlParams.keySet()
                                 .stream()
                                 .skip(1)
                                 .forEach(k -> this.urlBuilder.append("&")
                                                              .append(k)
                                                              .append("=")
                                                              .append(this.dc.urlParams.get(k)));
            }
            
            return this.urlBuilder.toString();
        }
        
        private void setServerOnSummoner(final Object sum)
        {
            try
            {
                final Field field = Summoner.class.getDeclaredField("server");
                field.setAccessible(true);
                field.set(sum, this.dc.region);
            } catch (final Exception e)
            {
                DataCall.LOGGER.log(Level.WARNING, e.getMessage(), e);
            }
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
         * The region to make this call to. This is part of the URL, "{region}"
         *
         * @param region the region to make the call to
         * @return this
         */
        public DataCallBuilder withRegion(final Server region)
        {
            this.dc.region = region;
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
         * Set the server to make this call to. (ie. EUW)
         *
         * @param server the server to make the call to
         * @return this
         */
        public DataCallBuilder withServer(final Server server)
        {
            this.dc.server = server;
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
        
    }
    
    privatndpoint endpoint;
    
    private Data
    ic
    
    class DataCallResponse
    {
        final int    responseCode;
        final String responseData;
        
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
        
    }
    
    privatCall()
    {
        final double permitsPerSecond = DataCall.DEFAULT_LIMITER_PERMITS_PER_10_MINUTES / DataCall.DEFAULT_LIMITER_10_MINUTES;
        Arrays.stream(Server.values())
              .filter(Server::isLimited)
              .forEach(s -> DataCall.limiter.put(s, new RateLimiter(permitsPerSecond)));
        
    }
    
}
