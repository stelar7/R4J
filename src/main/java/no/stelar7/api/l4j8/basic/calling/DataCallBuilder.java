package no.stelar7.api.l4j8.basic.calling;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.exceptions.*;
import no.stelar7.api.l4j8.basic.ratelimiting.*;
import no.stelar7.api.l4j8.basic.utils.*;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.prefs.BackingStoreException;

public class DataCallBuilder
{
    private final DataCall dc = new DataCall();
    
    private static final BiFunction<String, String, String> MERGE        = (o, n) -> o + "," + n;
    private static final BiFunction<String, String, String> MERGE_AS_SET = (o, n) -> o + n;
    
    private String requestMethod = "GET";
    private String postData      = "";
    
    private static void updateRatelimiter(Enum server, Enum endpoint)
    {
        RateLimiter limiter = DataCall.getLimiter().get(server).get(endpoint);
        limiter.updatePermitsTakenPerX(DataCall.getCallData().get(server).get(endpoint));
    }
    
    /**
     * Puts together all the data, and then returns an object representing the JSON from the call
     *
     * @param retrys the amount of retries already done (should not be passed in!)
     * @return an object generated from the requested JSON
     */
    public Object build(int... retrys)
    {
        if (DataCall.getCredentials() == null)
        {
            throw new APIUnsupportedActionException("No API Creds set!");
        }
        
        dc.getUrlHeaders().put("X-Riot-Token", DataCall.getCredentials().getBaseAPIKey());
        final String url = this.getURL();
        
        DataCall.getLogLevel().printIf(LogLevel.INFO, String.format("Trying url: %s", url));
        
        if (!this.dc.getEndpoint().isDDragon())
        {
            // app limit
            applyLimit(this.dc.getPlatform(), this.dc.getPlatform());
            // method limit
            applyLimit(this.dc.getPlatform(), this.dc.getEndpoint());
        }
        
        final DataCallResponse response = this.getResponse(url);
        DataCall.getLogLevel().printIf(LogLevel.EXTENDED_INFO, response.toString());
        
        switch (response.getResponseCode())
        {
            case 200:
            case 204:
            {
                String returnValue = response.getResponseData();
                
                final Object returnType = this.dc.getEndpoint().getType();
                returnValue = postProcess(returnValue);
                
                return Utils.getGson().fromJson(returnValue, (returnType instanceof Class<?>) ? (Class<?>) returnType : (Type) returnType);
            }
            
            case 403:
            {
                
                String reasonText = "The API denied your request!\n";
                reasonText += "Your API key might be invalid\n";
                reasonText += "You may be trying to call a endpoint you dont have access to\n";
                reasonText += "or if you just regenerated it; wait a few seconds, then try again\n";
                throw new APIResponseException(APIHTTPErrorReason.ERROR_403, reasonText + response.getResponseData());
            }
            
            case 404:
            {
                return new Pair<>(response.getResponseCode(), response.getResponseData());
            }
            
            case 429:
                if (response.getResponseData().startsWith(RateLimitType.LIMIT_UNDERLYING.getReason()) || response.getResponseData().startsWith(RateLimitType.LIMIT_SERVICE.getReason()))
                {
                    return sleepAndRetry(retrys, "Ratelimit reached too many times, waiting 10 second and retrying", "Ratelimit reached (%s / 3 times), waiting 1 second and retrying%n");
                } else
                {
                    System.err.println();
                    System.err.println(response.getResponseData());
                    System.err.println("429 ratelimit hit! Please do not restart your application to refresh the timer!");
                    System.err.println("This isn't supposed to happen unless you restarted your app before the last limit was hit!");
                }
                
                return this.build();
            
            case 500:
            case 502:
            case 503:
            case 504:
            {
                return sleepAndRetry(retrys, "Server error, waiting 10 second and retrying", "Server error (%s / 3 times), waiting 1 second and retrying%n");
            }
            
            default:
            {
                break;
            }
        }
        
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
        
        if (this.dc.getEndpoint() == URLEndpoint.V3_MATCH)
        {
            returnValue = postProcessMatch(returnValue);
        }
        
        if (this.dc.getEndpoint() == URLEndpoint.V3_STATIC_PERKPATHS)
        {
            returnValue = postProcessPerkPaths(returnValue);
        }
        
        if (this.dc.getEndpoint() == URLEndpoint.V3_STATIC_PERKPATH_BY_ID)
        {
            returnValue = postProcessPerkPath(returnValue);
        }
        
        final List<URLEndpoint> summonerEndpoints = Arrays.asList(URLEndpoint.V3_SUMMONER_BY_ACCOUNT, URLEndpoint.V3_SUMMONER_BY_ID, URLEndpoint.V3_SUMMONER_BY_NAME);
        if (summonerEndpoints.contains(this.dc.getEndpoint()))
        {
            returnValue = postProcessSummoner(returnValue);
        }
        
        return returnValue;
    }
    
    private String postProcessDDragonMany(String returnValue)
    {
        JsonObject elem   = (JsonObject) new JsonParser().parse(returnValue);
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
        JsonObject elem   = (JsonObject) new JsonParser().parse(returnValue);
        JsonObject parent = elem.getAsJsonObject("data");
        for (String key : new HashSet<>(parent.keySet()))
        {
            JsonObject child = parent.getAsJsonObject(key);
            child.addProperty("id", key);
        }
        
        return Utils.getGson().toJson(elem);
    }
    
    private String postProcessPerkPath(String returnValue)
    {
        JsonObject elem     = (JsonObject) new JsonParser().parse(returnValue);
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
        JsonArray element = (JsonArray) new JsonParser().parse(returnValue);
        
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
        JsonObject element = (JsonObject) new JsonParser().parse(returnValue);
        element.addProperty("platform", this.dc.getPlatform().toString());
        return Utils.getGson().toJson(element);
    }
    
    private String postProcessMatch(String returnValue)
    {
        JsonObject element = (JsonObject) new JsonParser().parse(returnValue);
        
        JsonArray participantIds = element.getAsJsonArray("participantIdentities");
        for (JsonElement participant : participantIds)
        {
            JsonObject pid    = participant.getAsJsonObject();
            JsonObject player = participant.getAsJsonObject().getAsJsonObject("player");
            for (String key : player.keySet())
            {
                pid.add(key, player.get(key));
            }
            pid.remove("player");
        }
        
        JsonArray participants = element.getAsJsonArray("participants");
        for (JsonElement participant : participants)
        {
            JsonObject stats = participant.getAsJsonObject().getAsJsonObject("stats");
            JsonObject part  = participant.getAsJsonObject();
            if (!stats.has("perkPrimaryStyle"))
            {
                return Utils.getGson().toJson(element);
            }
            
            JsonObject mPerk = new JsonObject();
            JsonArray  array = new JsonArray();
            
            for (int i = 0; i < 6; i++)
            {
                JsonObject perk = new JsonObject();
                
                perk.add("perkId", stats.get("perk" + i));
                perk.add("perkVar1", stats.get("perk" + i + "Var1"));
                perk.add("perkVar2", stats.get("perk" + i + "Var2"));
                perk.add("perkVar3", stats.get("perk" + i + "Var3"));
                array.add(perk);
                
                stats.remove("perk" + i);
                stats.remove("perk" + i + "Var1");
                stats.remove("perk" + i + "Var2");
                stats.remove("perk" + i + "Var3");
            }
            
            mPerk.add("perks", array);
            mPerk.add("perkPrimaryStyle", stats.get("perkPrimaryStyle"));
            mPerk.add("perkSubStyle", stats.get("perkSubStyle"));
            
            stats.remove("perkPrimaryStyle");
            stats.remove("perkSubStyle");
            
            part.add("perks", mPerk);
        }
        
        return Utils.getGson().toJson(element);
    }
    
    private Object sleepAndRetry(int[] retrys, String shortMessage, String longMessage)
    {
        try
        {
            int attempts = (retrys != null && retrys.length == 1) ? ++retrys[0] : 1;
            
            if (attempts > 5)
            {
                System.err.println("Error! too many times!");
                return null;
            }
            
            
            if (attempts > 2)
            {
                System.err.println(shortMessage);
                Thread.sleep(10000);
                return this.build(attempts);
            }
            
            System.err.format(longMessage, attempts);
            Thread.sleep(1000);
            return this.build(attempts);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
            return null;
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
        
        try
        {
            DataCall.getRatelimiterCache().put(limitKey, Utils.getGson().toJson(limiter.getLimits()));
            DataCall.getRatelimiterCache().put(firstKey, Utils.getGson().toJson(limiter.getFirstCallInTime()));
            DataCall.getRatelimiterCache().put(callKey, Utils.getGson().toJson(limiter.getCallCountInTime()));
            DataCall.getRatelimiterCache().sync();
        } catch (BackingStoreException e)
        {
            e.printStackTrace();
        }
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
            DataCall.getLogLevel().printIf(LogLevel.DEBUG, "No instance of an old ratelimiter found");
            return;
        }
        
        List<RateLimit>            knownLimits = Utils.getGson().fromJson(lastLimit, new TypeToken<List<RateLimit>>() {}.getType());
        Map<RateLimit, Instant>    knownTime   = Utils.getGson().fromJson(lastFirst, new TypeToken<Map<RateLimit, Instant>>() {}.getType());
        Map<RateLimit, AtomicLong> knownCount  = Utils.getGson().fromJson(lastKey, new TypeToken<Map<RateLimit, AtomicLong>>() {}.getType());
        
        
        RateLimiter newerLimit = new BurstRateLimiter(knownLimits);
        newerLimit.setCallCountInTime(knownCount);
        newerLimit.setFirstCallInTime(knownTime);
        
        DataCall.getLogLevel().printIf(LogLevel.DEBUG, String.format("Loaded ratelimit for %s", endpoint));
        
        child.put(endpoint, newerLimit);
        DataCall.getLimiter().put(platform, child);
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
            
            
            StringBuilder sb = new StringBuilder();
            con.getRequestProperties().forEach((key, value) -> sb.append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value)).append("\n"));
            
            String printMe = new StringBuilder()
                    .append(String.format(Constants.VERBOSE_STRING_FORMAT, "URL", url)).append("\n")
                    .append(String.format(Constants.VERBOSE_STRING_FORMAT, "Request Method", con.getRequestMethod())).append("\n")
                    .append(String.format(Constants.VERBOSE_STRING_FORMAT, "POST", this.postData)).append("\n")
                    .append(String.format(Constants.VERBOSE_STRING_FORMAT, "Request Headers", "")).append("\n")
                    .append(sb).toString().trim();
            
            DataCall.getLogLevel().printIf(LogLevel.EXTENDED_INFO, printMe);
            
            
            if (!this.postData.isEmpty())
            {
                con.setDoOutput(true);
                final DataOutputStream writer = new DataOutputStream(con.getOutputStream());
                writer.writeBytes(this.postData);
                writer.flush();
                writer.close();
            }
            
            con.connect();
            
            DataCall.getLogLevel().printIf(LogLevel.EXTENDED_INFO, String.format(Constants.VERBOSE_STRING_FORMAT, "Response Headers", ""));
            con.getHeaderFields()
               .forEach((key, value) -> DataCall.getLogLevel().printIf(LogLevel.EXTENDED_INFO, String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value)));
            
            
            String appA    = con.getHeaderField("X-App-Rate-Limit");
            String appB    = con.getHeaderField("X-App-Rate-Limit-Count");
            String methodA = con.getHeaderField("X-Method-Rate-Limit");
            String methodB = con.getHeaderField("X-Method-Rate-Limit-Count");
            
            if (appA == null)
            {
                DataCall.getLogLevel().printIf(LogLevel.DEBUG, "Header 'X-App-Rate-Limit' missing from call: " + getURL());
            } else
            {
                createRatelimiterIfMissing(appA, dc.getPlatform(), dc.getPlatform());
                saveHeaderRateLimit(appB, dc.getPlatform(), dc.getPlatform());
            }
            
            if (methodA == null)
            {
                DataCall.getLogLevel().printIf(LogLevel.DEBUG, "Header 'X-Method-Rate-Limit' missing from call: " + getURL());
            } else
            {
                createRatelimiterIfMissing(methodA, dc.getPlatform(), dc.getEndpoint());
                saveHeaderRateLimit(methodB, dc.getPlatform(), dc.getEndpoint());
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
        
        RateLimiter oldLimit   = child.get(endpoint);
        RateLimiter newerLimit = createLimiter(methodA);
        
        if (!newerLimit.equals(oldLimit))
        {
            newerLimit.mergeFrom(oldLimit);
            child.put(endpoint, newerLimit);
            
            DataCall.getLogLevel().printIf(LogLevel.DEBUG, String.format("Updating Ratelimit For %s", endpoint));
            DataCall.getLogLevel().printIf(LogLevel.DEBUG, newerLimit.getLimits().toString());
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
        url[0] = url[0].replace(Constants.GAME_PLACEHOLDER, dc.getEndpoint().getGame());
        url[0] = url[0].replace(Constants.SERVICE_PLACEHOLDER, dc.getEndpoint().getService());
        url[0] = url[0].replace(Constants.VERSION_PLACEHOLDER, dc.getEndpoint().getVersion());
        url[0] = url[0].replace(Constants.RESOURCE_PLACEHOLDER, dc.getEndpoint().getResource());
        if (dc.getPlatform() != null)
        {
            url[0] = url[0].replace(Constants.PLATFORM_PLACEHOLDER, dc.getPlatform().toString());
            url[0] = url[0].replace(Constants.REGION_PLACEHOLDER, ((Platform) dc.getPlatform()).getRealmValue());
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
    
    public DataCallBuilder withProxy(String proxy)
    {
        this.dc.setProxy(proxy);
        return this;
    }
}
