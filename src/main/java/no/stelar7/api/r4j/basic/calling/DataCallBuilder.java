package no.stelar7.api.r4j.basic.calling;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.types.RealmSpesificEnum;
import no.stelar7.api.r4j.basic.exceptions.*;
import no.stelar7.api.r4j.basic.ratelimiting.*;
import no.stelar7.api.r4j.basic.utils.*;
import org.slf4j.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.X509Certificate;
import java.time.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.prefs.BackingStoreException;

@SuppressWarnings("rawtypes")
public class DataCallBuilder
{
    private static final Logger logger = LoggerFactory.getLogger(DataCallBuilder.class);
    
    private final DataCall dc = new DataCall();
    
    private static final BiFunction<String, String, String> MERGE        = (o, n) -> o + "," + n;
    private static final BiFunction<String, String, String> MERGE_AS_SET = (o, n) -> o + n;
    
    private String requestMethod = "GET";
    private String postData      = "";
    
    private static final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager()
    {
        public java.security.cert.X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }
        
        public void checkClientTrusted(X509Certificate[] certs, String authType)
        {
        }
        
        public void checkServerTrusted(X509Certificate[] certs, String authType)
        {
        }
    }
    };
    
    // Create all-trusting host name verifier
    private static final HostnameVerifier allHostsValid = (hostname, session) -> true;
    
    static
    {
        try
        {
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }
    
    
    private static void updateRatelimiter(Enum server, Enum endpoint)
    {
        RateLimiter limiter = DataCall.getLimiter().get(server).get(endpoint);
        limiter.updatePermitsTakenPerX(DataCall.getCallData().get(server).get(endpoint));
    }
    
    private static final Map<URLEndpoint, AtomicLong> requestCount = new HashMap<>();
    
    /**
     * Puts together all the data, and then returns an object representing the JSON from the call
     *
     * @param retrys the amount of retries already done (should not be passed in!)
     * @return an object generated from the requested JSON
     */
    public Object build(int... retrys)
    {
        final String url = this.getURL();
        logger.info("Trying url: {}", url);
        
        if (this.dc.useRatelimiter())
        {
            if (DataCall.getCredentials() == null)
            {
                throw new APIUnsupportedActionException("No API Creds set!");
            }
            
            dc.getUrlHeaders().putIfAbsent("X-Riot-Token", DataCall.getCredentials().getLoLAPIKey());
            
            // app limit
            applyLimit(this.dc.getPlatform(), this.dc.getPlatform());
            // method limit
            applyLimit(this.dc.getPlatform(), this.dc.getEndpoint());
        }
        
        
        final DataCallResponse response = this.getResponse(url);
        logger.debug(response.toString());
        
        switch (response.getResponseCode())
        {
            case 200:
            case 201:
            case 204:
            {
                String returnValue = response.getResponseData();
                
                if (this.dc.shouldReturnRawResponse())
                {
                    return returnValue;
                }
                
                if (this.dc.getEndpoint() != null)
                {
                    final Class<?> returnType = this.dc.getEndpoint().getType();
                    returnValue = postProcess(returnValue);
                    
                    return Utils.getGson().fromJson(returnValue, returnType);
                } else
                {
                    return returnValue;
                }
            }
            
            case 400:
            {
                String reasonText = "Your api request is malformed!\n";
                reasonText += url + "\n";
                throw new APIResponseException(APIHTTPErrorReason.ERROR_400, reasonText + response.getResponseData());
            }
            
            case 401:
            {
                
                String reasonText = "The API denied your request!\n";
                reasonText += "Your API key was not present in the request\n";
                reasonText += "Make sure you have setup your APICredentials before doing a API call!\n";
                throw new APIResponseException(APIHTTPErrorReason.ERROR_401, reasonText + response.getResponseData());
            }
            
            case 403:
            {
                
                String reasonText = "The API denied your request!\n";
                reasonText += "Possible reasons:\n";
                reasonText += " - Your API key is invalid\n";
                reasonText += " - You just regenerated the key; wait a few seconds, then try again\n";
                reasonText += " - You are trying to call a endpoint you dont have access to\n";
                throw new APIResponseException(APIHTTPErrorReason.ERROR_403, reasonText + response.getResponseData());
            }
            
            case 404:
            {
                return new Pair<>(response.getResponseCode(), response.getResponseData());
            }
            
            case 405:
            {
                String reasonText = "The API was unable to handle your request due to the wrong HTTP method being used.\n";
                throw new APIResponseException(APIHTTPErrorReason.ERROR_403, reasonText + response.getResponseData());
            }
            case 429:
                if (response.getResponseData().startsWith(RateLimitType.LIMIT_UNDERLYING.getReason()) || response.getResponseData().startsWith(RateLimitType.LIMIT_SERVICE.getReason()))
                {
                    return sleepAndRetry(retrys, response.getResponseCode());
                } else
                {
                    String error = response.getResponseData() + "429 ratelimit hit! " +
                                   "Please do not restart your application to refresh the timer! " +
                                   "This isn't supposed to happen unless you restarted your app before the last limit was hit!";
                    
                    logger.error(error);
                    
                }
                
                return this.build();
            
            case 500:
            case 502:
            case 503:
            case 504:
            {
                return sleepAndRetry(retrys, response.getResponseCode());
            }
            
            case 599:
            {
                throw new APIResponseException(APIHTTPErrorReason.ERROR_599, response.getResponseData());
            }
            
            default:
            {
                break;
            }
        }
        
        System.err.println("UNHANDLED RESPONSE CODE!!!");
        System.err.println("Response Code:" + response.getResponseCode());
        System.err.println("Response Data:" + response.getResponseData());
        throw new APINoValidResponseException(response.getResponseData());
    }
    
    private String postProcess(String returnValue)
    {
        final List<URLEndpoint> ddragon = Arrays.asList(URLEndpoint.DDRAGON_CHAMPION_MANY, URLEndpoint.DDRAGON_SUMMONER_SPELLS);
        if (ddragon.contains(this.dc.getEndpoint()))
        {
            returnValue = postProcessDDragonMany(returnValue);
        }
        
        if (this.dc.getEndpoint() == URLEndpoint.DDRAGON_ITEMS || this.dc.getEndpoint() == URLEndpoint.DDRAGON_RUNES)
        {
            returnValue = postProcessDDragonAddId(returnValue);
        }
        
        final List<URLEndpoint> summonerEndpoints = Arrays.asList(
                URLEndpoint.V4_SUMMONER_BY_ACCOUNT,
                URLEndpoint.V4_SUMMONER_BY_ID,
                URLEndpoint.V4_SUMMONER_BY_NAME,
                URLEndpoint.V4_SUMMONER_BY_PUUID,
                URLEndpoint.V1_TFT_SUMMONER_BY_ACCOUNT,
                URLEndpoint.V1_TFT_SUMMONER_BY_ID,
                URLEndpoint.V1_TFT_SUMMONER_BY_NAME,
                URLEndpoint.V1_TFT_SUMMONER_BY_PUUID);
        if (summonerEndpoints.contains(this.dc.getEndpoint()))
        {
            returnValue = postProcessSummoner(returnValue);
        }
        
        final List<URLEndpoint> apexEndpoints = Arrays.asList(URLEndpoint.V4_LEAGUE_MASTER, URLEndpoint.V4_LEAGUE_GRANDMASTER, URLEndpoint.V4_LEAGUE_CHALLENGER,
                                                              URLEndpoint.V1_TFT_LEAGUE_MASTER, URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER, URLEndpoint.V1_TFT_LEAGUE_CHALLENGER);
        if (apexEndpoints.contains(this.dc.getEndpoint()))
        {
            returnValue = postProcessApex(returnValue);
        }
        
        return returnValue;
    }
    
    private String postProcessApex(String returnValue)
    {
        JsonObject elem    = (JsonObject) JsonParser.parseString(returnValue);
        JsonArray  entries = elem.getAsJsonArray("entries");
        if (entries == null)
        {
            entries = new JsonArray();
        }
        
        entries.forEach(e -> {
            JsonObject ob = (JsonObject) e;
            ob.add("leagueId", elem.get("leagueId"));
            ob.add("queueType", elem.get("queue"));
            ob.add("tier", elem.get("tier"));
        });
        
        return Utils.getGson().toJson(elem);
    }
    
    private String postProcessDDragonMany(String returnValue)
    {
        JsonObject elem   = (JsonObject) JsonParser.parseString(returnValue);
        JsonObject parent = elem.getAsJsonObject("data");
        for (String key : new HashSet<>(parent.keySet()))
        {
            JsonObject child = parent.getAsJsonObject(key);
            String     id    = child.get("key").getAsString();
            child.addProperty("key", key);
            child.addProperty("id", id);
            parent.add(id, child);
            parent.remove(key);
        }
        
        return Utils.getGson().toJson(elem);
    }
    
    private String postProcessDDragonAddId(String returnValue)
    {
        List<String> buggedElem = new ArrayList<>();
        JsonObject elem   = (JsonObject) JsonParser.parseString(returnValue);
        JsonObject parent = elem.getAsJsonObject("data");
        for (String key : new HashSet<>(parent.keySet()))
        {
            JsonObject child = parent.getAsJsonObject(key);
            try 
            {
                int keyAsInt = Integer.parseInt(key);
                child.addProperty("id", keyAsInt);
                
            }   catch(NumberFormatException e) 
            {
                buggedElem.add(key);
                logger.warn("Item received without a valid Id ({}), item removed from the list", key);
            }
        }
        
        for(String elemToRemove : buggedElem) 
        {
            elem.getAsJsonObject("data").remove(elemToRemove);
        }
        
        return Utils.getGson().toJson(elem);
    }
    
    private String postProcessPerkPath(String returnValue)
    {
        JsonObject elem     = (JsonObject) JsonParser.parseString(returnValue);
        String     pathName = elem.get("name").getAsString();
        String     pathId   = elem.get("id").getAsString();
        
        JsonArray slots = elem.getAsJsonArray("slots");
        for (JsonElement slot : slots)
        {
            JsonArray runes = slot.getAsJsonObject().getAsJsonArray("runes");
            for (JsonElement rune : runes)
            {
                JsonObject obj = (JsonObject) rune;
                obj.addProperty("runePathName", pathName);
                obj.addProperty("runePathId", pathId);
            }
        }
        
        return Utils.getGson().toJson(elem);
    }
    
    private String postProcessPerkPaths(String returnValue)
    {
        JsonArray element = (JsonArray) JsonParser.parseString(returnValue);
        
        for (JsonElement elem : element)
        {
            String pathName = elem.getAsJsonObject().get("name").getAsString();
            String pathId   = elem.getAsJsonObject().get("id").getAsString();
            
            JsonArray slots = elem.getAsJsonObject().getAsJsonArray("slots");
            for (JsonElement slot : slots)
            {
                JsonArray runes = slot.getAsJsonObject().getAsJsonArray("runes");
                for (JsonElement rune : runes)
                {
                    JsonObject obj = (JsonObject) rune;
                    obj.addProperty("runePathName", pathName);
                    obj.addProperty("runePathId", pathId);
                }
            }
        }
        
        return Utils.getGson().toJson(element);
    }
    
    private String postProcessSummoner(String returnValue)
    {
        JsonObject element = (JsonObject) JsonParser.parseString(returnValue);
        element.addProperty("platform", this.dc.getPlatform().toString());
        return Utils.getGson().toJson(element);
    }
    
    private Object sleepAndRetry(int[] retrys, int errorCode)
    {
        try
        {
            int  attempts           = (retrys != null && retrys.length == 1) ? ++retrys[0] : 1;
            long nextSleepDuration  = attempts * 500L;
            long totalSleepDuration = 0;
            for (int i = 1; i < attempts; i++)
            {
                totalSleepDuration += i * 500L;
            }
            
            
            String message = "";
            if (errorCode == 429)
            {
                message = "Ratelimit reached too many times, waiting " + nextSleepDuration / 1000 + " seconds then retrying";
            } else
            {
                message = "Server error (" + errorCode + ") , waiting " + nextSleepDuration / 1000 + " seconds then retrying";
            }
            
            logger.info(message);
            
            if (totalSleepDuration > this.dc.getMaxSleep())
            {
                throw new APINoValidResponseException(String.format("API did not return a valid response in time. Total sleep time is over the max sleep value %s > %s...\n" +
                                                                    "Try setting `DataCall.setDefaultMaxSleep(long)` to a larger number (default is 10000)",
                                                                    (nextSleepDuration + totalSleepDuration), this.dc.getMaxSleep()));
            }
            
            Thread.sleep(nextSleepDuration);
            return this.build(attempts);
        } catch (InterruptedException e)
        {
            throw new APINoValidResponseException("Something interupted the API timeout;" + e.getMessage());
        }
    }
    
    public static final ReentrantLock lock = new ReentrantLock();
    
    private void applyLimit(Enum platform, Enum endpoint)
    {
        lock.lock();
        try
        {
            Map<Enum, RateLimiter> child = DataCall.getLimiter().getOrDefault(platform, new HashMap<>());
            
            if (child.get(endpoint) == null)
            {
                loadLimiterFromCache(platform, endpoint, child);
            }
        } finally
        {
            lock.unlock();
        }
        
        RateLimiter limitr = DataCall.getLimiter().getOrDefault(platform, new HashMap<>()).get(endpoint);
        
        if (limitr != null)
        {
            limitr.acquire();
            storeLimiter(platform, endpoint);
        }
    }
    
    private void storeLimiter(Enum platform, Enum endpoint)
    {
        RateLimiter limiter  = DataCall.getLimiter().get(platform).get(endpoint);
        String      baseKey  = platform.toString() + "/" + endpoint.toString();
        String      limitKey = "limits/" + baseKey;
        String      firstKey = "first/" + baseKey;
        String      callKey  = "call/" + baseKey;
        
        DataCall.getRatelimiterCache().put(firstKey, Utils.getGson().toJson(limiter.getFirstCallInTime()));
        DataCall.getRatelimiterCache().put(callKey, Utils.getGson().toJson(limiter.getCallCountInTime()));
    }
    
    private void loadLimiterFromCache(Enum platform, Enum endpoint, Map<Enum, RateLimiter> child)
    {
        String baseKey  = platform.toString() + "/" + endpoint.toString();
        String limitKey = "limits/" + baseKey;
        String firstKey = "first/" + baseKey;
        String callKey  = "call/" + baseKey;
        
        String lastLimit = DataCall.getRatelimiterCache().get(limitKey, null);
        String lastFirst = DataCall.getRatelimiterCache().get(firstKey, null);
        String lastKey   = DataCall.getRatelimiterCache().get(callKey, null);
        
        if (lastLimit == null)
        {
            logger.debug("No instance of an old ratelimiter found");
            return;
        } else
        {
            logger.debug("Loading old ratelimiter data");
        }
        
        try
        {
            List<RateLimit>            knownLimits = Utils.getGson().fromJson(lastLimit, new TypeToken<List<RateLimit>>() {}.getType());
            Map<RateLimit, AtomicLong> knownTime   = Utils.getGson().fromJson(lastFirst, new TypeToken<Map<RateLimit, AtomicLong>>() {}.getType());
            Map<RateLimit, AtomicLong> knownCount  = Utils.getGson().fromJson(lastKey, new TypeToken<Map<RateLimit, AtomicLong>>() {}.getType());
            
            
            RateLimiter newerLimit = new BurstRateLimiter(knownLimits);
            newerLimit.setCallCountInTime(knownCount);
            newerLimit.setFirstCallInTime(knownTime);
            
            logger.debug("Loaded ratelimit for {}", endpoint);
            
            child.put(endpoint, newerLimit);
            DataCall.getLimiter().put(platform, child);
        } catch (JsonSyntaxException s)
        {
            try
            {
                logger.debug("Old ratelimiter was of incompatible type, re-creating");
                DataCall.getRatelimiterCache().clear();
                DataCall.getRatelimiterCache().sync();
            } catch (BackingStoreException e)
            {
                e.printStackTrace();
            }
        }
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
            con.setRequestProperty("User-Agent", "R4J");
            con.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8");
            con.setRequestProperty("Accept-Language", "en-US");
            con.setRequestProperty("Cache-Control", "no-store,max-age=0,no-cache");
            con.setRequestProperty("Expires", "0");
            con.setRequestProperty("Pragma", "no-cache");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(this.dc.getConnectTimeout());
            con.setReadTimeout(this.dc.getReadTimeout());
            this.dc.getUrlHeaders().forEach(con::setRequestProperty);
            
            if (requestMethod.equalsIgnoreCase("PATCH"))
            {
                con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                con.setRequestMethod("POST");
            } else
            {
                con.setRequestMethod(requestMethod);
            }
            
            StringBuilder sb = new StringBuilder();
            con.getRequestProperties().forEach((key, value) -> sb.append(String.format(Constants.TABBED2X_VERBOSE_STRING_FORMAT, key, value)).append("\n"));
            
            String printMe = new StringBuilder("\n")
                    .append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "Url", url)).append("\n")
                    .append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "Request Method", con.getRequestMethod())).append("\n")
                    .append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "POST data", this.postData)).append("\n")
                    .append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "Request Headers", "")).append("\n")
                    .append(sb).toString();
            
            logger.debug(printMe);
            
            
            if (null != this.postData && !this.postData.isEmpty())
            {
                con.setDoOutput(true);
                final DataOutputStream writer = new DataOutputStream(con.getOutputStream());
                writer.writeBytes(this.postData);
                writer.flush();
                writer.close();
            }
            
            con.connect();
            
            StringBuilder sb2 = new StringBuilder("\n");
            con.getHeaderFields().forEach((key, value) -> sb2.append(String.format(Constants.TABBED2X_VERBOSE_STRING_FORMAT, key, value)).append("\n"));
            
            String printMe2 = new StringBuilder("\n").append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "Response Headers", ""))
                                                     .append(sb2)
                                                     .toString();
            logger.debug(printMe2);
            
            
            String appA    = con.getHeaderField("X-App-Rate-Limit");
            String appB    = con.getHeaderField("X-App-Rate-Limit-Count");
            String methodA = con.getHeaderField("X-Method-Rate-Limit");
            String methodB = con.getHeaderField("X-Method-Rate-Limit-Count");
            
            if (appA == null)
            {
                logger.debug("Header 'X-App-Rate-Limit' missing from call: {} ", getURL());
            } else
            {
                lock.lock();
                createRatelimiterIfMissing(appA, dc.getPlatform(), dc.getPlatform());
                saveHeaderRateLimit(appB, dc.getPlatform(), dc.getPlatform());
                lock.unlock();
            }
            
            if (methodA == null)
            {
                logger.debug("Header 'X-Method-Rate-Limit' missing from call: {}", getURL());
            } else
            {
                lock.lock();
                createRatelimiterIfMissing(methodA, dc.getPlatform(), dc.getEndpoint());
                saveHeaderRateLimit(methodB, dc.getPlatform(), dc.getEndpoint());
                lock.unlock();
            }
            
            String deprecationHeader = con.getHeaderField("X-Riot-Deprecated");
            if (deprecationHeader != null)
            {
                LocalDateTime timeout = LocalDateTime.ofEpochSecond(Long.parseLong(deprecationHeader) / 1000, 0, ZoneOffset.ofHours(-7));
                logger.info("You are using a deprecated method, this method will stop working at: {}", timeout);
            }
            
            if (con.getResponseCode() == 429)
            {
                final RateLimitType limitType = RateLimitType.getBestMatch(con.getHeaderField("X-Rate-Limit-Type"));
                
                StringBuilder valueList = new StringBuilder();
                DataCall.getLimiter().get(dc.getPlatform()).forEach((key, value) -> {
                    valueList.append(key);
                    valueList.append("=");
                    valueList.append(value.getCallCountInTime());
                    valueList.append("\n");
                });
                
                String reasonString = String.format("%s%n%s", limitType.getReason(), valueList.toString().trim());
                String reason       = String.format("%s%n", reasonString);
                
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
            
            if (con.getResponseCode() == 204)
            {
                return new DataCallResponse(con.getResponseCode(), "");
            }
            
            InputStream stream = (con.getResponseCode() <= 399) ? con.getInputStream() : con.getErrorStream();
            
            if (stream == null)
            {
                return new DataCallResponse(con.getResponseCode(), "Unable to read stream!");
            }
            
            try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)))
            {
                br.lines().forEach(data::append);
            }
            
            con.disconnect();
            
            return new DataCallResponse(con.getResponseCode(), data.toString());
        } catch (final IOException e)
        {
            throw new APIResponseException(APIHTTPErrorReason.ERROR_599, APIHTTPErrorReason.ERROR_599.getReason());
        }
    }
    
    private void createRatelimiterIfMissing(String methodA, Enum platform, Enum endpoint)
    {
        Map<Enum, RateLimiter> child = DataCall.getLimiter().getOrDefault(platform, new HashMap<>());
        
        RateLimiter oldLimit   = child.get(endpoint);
        RateLimiter newerLimit = createLimiter(methodA);
        
        if (!newerLimit.equals(oldLimit))
        {
            newerLimit.mergeFrom(oldLimit);
            child.put(endpoint, newerLimit);
            
            logger.debug("Updating Ratelimit For {}", endpoint);
            logger.debug(newerLimit.getLimits().toString());
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
        storeLimiter(platform, endpoint);
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
    
    public RateLimiter createLimiter(String limitCount)
    {
        Map<Integer, Integer> timeout = parseLimitFromHeader(limitCount);
        
        List<RateLimit> limits = new ArrayList<>();
        for (Entry<Integer, Integer> entry : timeout.entrySet())
        {
            limits.add(new RateLimit(entry.getValue(), entry.getKey(), TimeUnit.SECONDS));
        }
        
        return new BurstRateLimiter(limits);
    }
    
    
    /**
     * Generates the URL to use for the call.
     *
     * @return the URL to use for the call.
     */
    private String getURL()
    {
        String[] url = {dc.getProxy()};
        if (dc.getEndpoint() != null)
        {
            url[0] = url[0].replace(Constants.GAME_PLACEHOLDER, dc.getEndpoint().getGame());
            url[0] = url[0].replace(Constants.SERVICE_PLACEHOLDER, dc.getEndpoint().getService());
            url[0] = url[0].replace(Constants.VERSION_PLACEHOLDER, dc.getEndpoint().getVersion());
            url[0] = url[0].replace(Constants.RESOURCE_PLACEHOLDER, dc.getEndpoint().getResource());
        }
        if (dc.getPlatform() != null)
        {
            url[0] = url[0].replace(Constants.PLATFORM_PLACEHOLDER, dc.getPlatform().toString().toLowerCase());
            url[0] = url[0].replace(Constants.REGION_PLACEHOLDER, ((RealmSpesificEnum) dc.getPlatform()).getRealmValue());
        }
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
     * enables ratelimiters for this call
     *
     * @param flag enabled or disabled
     * @return this
     */
    public DataCallBuilder withLimiters(final boolean flag)
    {
        this.dc.setUseLimiters(flag);
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
    public DataCallBuilder withPlatform(final Enum server)
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
     * Adds parameters to the url (ie. ?api_key)
     *
     * @param key   the parameter to add (ie. api_key)
     * @param value the value to add after the parameter (ie. 6fa459ea-ee8a-3ca4-894e-db77e160355e)
     * @return this
     */
    public DataCallBuilder withQueryParameter(final String key, final String value)
    {
        this.dc.getUrlData().merge(key, value, MERGE);
        return this;
    }
    
    /**
     * Replaces placeholders in the URL (ie. {region})
     *
     * @param key   The key to replace (ie. {region})
     * @param value The data to replace it with (ie. EUW)
     * @return this
     */
    public DataCallBuilder withURLParameter(final String key, final String value)
    {
        this.dc.getUrlParams().merge(key, value, MERGE);
        return this;
    }
    
    public DataCallBuilder withProxy(String proxy)
    {
        this.dc.setProxy(proxy);
        return this;
    }
}
