package no.stelar7.api.l4j8.basic;


import com.google.gson.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.deserializer.*;
import no.stelar7.api.l4j8.basic.exceptions.*;
import no.stelar7.api.l4j8.impl.RateLimiter;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiFunction;
import java.util.logging.*;

public final class DataCall
{
    
    private static final Gson gson;
    
    static
    {
        GsonBuilder builder = new GsonBuilder();
        
        // Register enum types with GSON
        builder.registerTypeAdapter(AscencionType.class, new AscencionTypeDeserializer());
        builder.registerTypeAdapter(BuildingType.class, new BuildingTypeDeserializer());
        builder.registerTypeAdapter(ChampionType.class, new ChampionTypeDeserializer());
        builder.registerTypeAdapter(EventType.class, new EventTypeDeserializer());
        builder.registerTypeAdapter(GameModeType.class, new GameModeTypeDeserializer());
        builder.registerTypeAdapter(GameQueueType.class, new GameQueueTypeDeserializer());
        builder.registerTypeAdapter(GameSubType.class, new GameSubTypeDeserializer());
        builder.registerTypeAdapter(GameType.class, new GameTypeDeserializer());
        builder.registerTypeAdapter(LaneType.class, new LaneTypeDeserializer());
        builder.registerTypeAdapter(LevelUpType.class, new LevelUpTypeDeserializer());
        builder.registerTypeAdapter(MapType.class, new MapTypeDeserializer());
        builder.registerTypeAdapter(MonsterType.class, new MonsterTypeDeserializer());
        builder.registerTypeAdapter(PlayerStatSummaryType.class, new PlayerStatSummaryTypeDeserializer());
        builder.registerTypeAdapter(PointType.class, new PointTypeDeserializer());
        builder.registerTypeAdapter(RankedQueueType.class, new RankedQueueTypeDeserializer());
        builder.registerTypeAdapter(RoleType.class, new RoleTypeDeserializer());
        builder.registerTypeAdapter(SeasonType.class, new SeasonTypeDeserializer());
        builder.registerTypeAdapter(SummonerSpellType.class, new SummonerSpellTypeDeserializer());
        builder.registerTypeAdapter(TeamType.class, new TeamTypeDeserializer());
        builder.registerTypeAdapter(TierType.class, new TierTypeDeserializer());
        builder.registerTypeAdapter(TournamentMapType.class, new TournamentMapTypeDeserializer());
        builder.registerTypeAdapter(TournamentPickType.class, new TournamentPickTypeDeserializer());
        builder.registerTypeAdapter(TournamentSpectatorType.class, new TournamentSpectatorTypeDeserializer());
        builder.registerTypeAdapter(TowerType.class, new TowerTypeDeserializer());
        builder.registerTypeAdapter(WardType.class, new WardTypeDeserializer());
        
        gson = builder.create();
    }
    
    public static class DataCallBuilder
    {
        public static APICredentials getCredentials()
        {
            return DataCall.creds;
        }
        
        public static void setCredentials(final APICredentials creds)
        {
            DataCall.creds = creds;
        }
        
        private final DataCall dc = new DataCall();
        
        private final BiFunction<String, String, String> merge = (o, n) -> o + "," + n;
        
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
            
            //DataCall.limiter.get(this.dc.platform).acquire();
            
            final String           url      = this.getURL();
            System.out.println(url);
            final DataCallResponse response = this.getResponse(url);
            
            if ((response.getResponseCode() == 200) || (response.getResponseCode() == 204))
            {
                final Object type = this.dc.endpoint.getType();
                Object       dtoobj;
                if (type instanceof Class<?>)
                {
                    dtoobj = gson.fromJson(response.getResponseData(), (Class<?>) this.dc.endpoint.getType());
                } else
                {
                    dtoobj = gson.fromJson(response.getResponseData(), (Type) this.dc.endpoint.getType());
                }
                
                return dtoobj;
            }
            
            if (response.getResponseCode() == 404)
            {
                return null;
            }
            
            if (response.getResponseCode() == 500)
            {
                if (this.dc.verbose)
                {
                    DataCall.LOGGER.log(Level.INFO, "Server error");
                }
                
                throw new APIResponseException(APIHTTPErrorReason.ERROR500, response.getResponseData());
                
            }
            
            if (response.getResponseCode() == 400)
            {
                throw new APIResponseException(APIHTTPErrorReason.ERROR400, "API call error.. contact developer to get this fixed ..." + response.getResponseData());
            }
            
            if (response.getResponseCode() == 429)
            {
                
                if (this.dc.verbose)
                {
                    DataCall.LOGGER.log(Level.INFO, "HIT 429");
                }
                
                throw new APIResponseException(APIHTTPErrorReason.ERROR429, response.getResponseData());
                
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
                
                
                // Log this for future use(?)
                final String limitCount = con.getHeaderField("X-App-Rate-Limit-Count");
                if (limitCount != null)
                {
                    final String[] limits = limitCount.split(",");
                    for (final String limitPair : limits)
                    {
                        final String[] limit = limitPair.split(":");
                        final Integer  call  = Integer.parseInt(limit[0]);
                        final Integer  time  = Integer.parseInt(limit[1]);
                        DataCall.appLimit.put(time, call);
                    }
                }
                
                // Log this for future use(?)
                final String methodLimitCount = con.getHeaderField("X-Method-Rate-Limit-Count");
                if (methodLimitCount != null)
                {
                    final String[] limits = methodLimitCount.split(",");
                    for (final String limitPair : limits)
                    {
                        final String[] limit = limitPair.split(":");
                        final Integer  call  = Integer.parseInt(limit[0]);
                        final Integer  time  = Integer.parseInt(limit[1]);
                        DataCall.methodLimit.getOrDefault(dc.endpoint, new TreeMap<>()).put(time, call);
                    }
                }
                
                if (con.getResponseCode() == 429)
                {
                    final String limitType = RateLimitType.getBestMatch(con.getHeaderField("X-Rate-Limit-Type")).getReason();
                    return new DataCallResponse(con.getResponseCode(), limitType);
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
            if (dc.platform == null)
            {
                url[0] = url[0].replace(Constants.SERVER_PLACEHOLDER, dc.server.asURLFormat());
            } else
            {
                url[0] = url[0].replace(Constants.PLATFORM_PLACEHOLDER, dc.platform.toString());
            }
            url[0] = url[0].replace(Constants.GAME_PLACEHOLDER, dc.endpoint.getGame());
            url[0] = url[0].replace(Constants.SERVICE_PLACEHOLDER, dc.endpoint.getService());
            url[0] = url[0].replace(Constants.VERSION_PLACEHOLDER, dc.endpoint.getVersion());
            url[0] = url[0].replace(Constants.RESOURCE_PLACEHOLDER, dc.endpoint.getResource());
            dc.urlParams.forEach((k, v) -> url[0] = url[0].replace(k, v));
            url[0] += "?" + Constants.API_KEY_PLACEHOLDER_DATA + "=" + creds.getBaseAPIKey();
            dc.urlData.forEach((k, v) -> url[0] = url[0] + "&" + k + "=" + v);
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
        
        public DataCallBuilder withServer(Server s)
        {
            this.dc.server = s;
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
        
    }
    
    private static final Double DEFAULT_LIMITER_PERMITS_PER_10_MINUTES = 500.0;
    private static final Double DEFAULT_LIMITER_10_MINUTES             = 600.0;
    
    private static final Logger LOGGER = Logger.getGlobal();
    
    private static final EnumMap<Platform, RateLimiter> limiter = new EnumMap<>(Platform.class);
    
    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }
    
    private static APICredentials creds;
    
    
    private final Map<String, String> urlParams = new TreeMap<>();
    private final Map<String, String> urlData   = new TreeMap<>();
    
    private final Map<String, String> urlHeaders = new TreeMap<>();
    
    // How many calls our app has made in a timeframe, MapType<Timeframe, CallCount>
    private static final Map<Integer, Integer>                   appLimit    = new TreeMap<>();
    private static final Map<URLEndpoint, Map<Integer, Integer>> methodLimit = new TreeMap<>();
    
    private boolean verbose;
    
    private String requestMethod = "GET";
    private String postData      = "";
    
    private Platform    platform;
    private URLEndpoint endpoint;
    
    private String baseURL = Constants.REQUEST_URL;
    
    private Server server;
    
    private DataCall()
    {
        final double permitsPerSecond = DataCall.DEFAULT_LIMITER_PERMITS_PER_10_MINUTES / DataCall.DEFAULT_LIMITER_10_MINUTES;
        Arrays.stream(Platform.values()).forEach(s -> DataCall.limiter.put(s, new RateLimiter(permitsPerSecond)));
        
    }
    
}
