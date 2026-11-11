package no.stelar7.api.r4j.basic.calling;

import com.google.gson.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

@SuppressWarnings("rawtypes")
public class DataCallBuilder
{
    private static final Logger logger = LoggerFactory.getLogger(DataCallBuilder.class);
    
    private final R4JContext context;
    private final DataCall   dc;

    /**
     * A call using the default context (the credentials, cache and limiters set through {@code new R4J(creds)} / the static methods on DataCall)
     */
    public DataCallBuilder()
    {
        this(R4JContext.getDefault());
    }

    /**
     * @param context the context to take the credentials, cache, settings and limiters from
     */
    public DataCallBuilder(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
        this.dc = new DataCall(this.context);
    }
    
    private static final BiFunction<String, String, String> MERGE        = (o, n) -> o + "," + n;
    private static final BiFunction<String, String, String> MERGE_AS_SET = (o, n) -> o + n;
    
    private String requestMethod = "GET";
    private String postData      = "";
    
    /**
     * How many times a call is re-sent after an application/method 429 before giving up.
     * (The limiter sleeps for the Retry-After duration between each attempt)
     */
    private static final int MAX_RATELIMIT_RETRIES = 5;

    /**
     * The game client (LCU, live client data, replay api) serves a certificate signed by a Riot root that is not in the JVM truststore.
     * This factory accepts any certificate, and is ONLY ever applied to connections going to the local machine.
     */
    private static volatile SSLSocketFactory loopbackSocketFactory;

    private static final HostnameVerifier loopbackHostnameVerifier = (hostname, session) -> true;

    private static SSLSocketFactory getLoopbackSocketFactory() throws IOException
    {
        SSLSocketFactory factory = loopbackSocketFactory;
        if (factory == null)
        {
            try
            {
                TrustManager[] trustAllCerts = {new X509TrustManager()
                {
                    public X509Certificate[] getAcceptedIssuers()
                    {
                        return new X509Certificate[0];
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType)
                    {
                        // local connection only
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType)
                    {
                        // local connection only
                    }
                }};

                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, trustAllCerts, new SecureRandom());
                loopbackSocketFactory = factory = sc.getSocketFactory();
            } catch (KeyManagementException | NoSuchAlgorithmException e)
            {
                throw new IOException("Unable to create a TLS context for the local game client", e);
            }
        }

        return factory;
    }

    private static boolean isLoopback(URL url)
    {
        String host = url.getHost();
        return "127.0.0.1".equals(host) || "localhost".equalsIgnoreCase(host) || "[::1]".equals(host) || "::1".equals(host);
    }

    /**
     * Puts together all the data, and then returns an object representing the JSON from the call
     *
     * @param retrys the amount of retries already done (should not be passed in!)
     * @return an object generated from the requested JSON
     */
    public Object build(int... retrys)
    {
        final String url = this.getURL();

        int attempts         = (retrys != null && retrys.length == 1) ? retrys[0] : 0;
        int ratelimitRetries = 0;

        // Retries are done in a loop (instead of recursion) so the parallel-call permit is never held while sleeping or re-acquired while already held
        while (true)
        {
            final DataCallResponse response = this.execute(url);
            if (response == null)
            {
                // interrupted while waiting for a permit
                return null;
            }

            logger.trace("Response: {}", response);

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
                    throw new APIResponseException(APIHTTPErrorReason.ERROR_405, reasonText + response.getResponseData());
                }

                case 415:
                {
                    String reasonText = "The API was unable to handle your request due to an unsupported media type.\n";
                    throw new APIResponseException(APIHTTPErrorReason.ERROR_415, reasonText + response.getResponseData());
                }

                case 429:
                {
                    if (response.getResponseData().startsWith(RateLimitType.LIMIT_UNDERLYING.getReason()) || response.getResponseData().startsWith(RateLimitType.LIMIT_SERVICE.getReason()))
                    {
                        attempts = sleepBeforeRetry(attempts, response.getResponseCode());
                        continue;
                    }

                    String error = response.getResponseData() + "429 ratelimit hit! " +
                                   "Please do not restart your application to refresh the timer! " +
                                   "This isn't supposed to happen unless you restarted your app before the last limit was hit!";

                    logger.error(error);

                    if (++ratelimitRetries > MAX_RATELIMIT_RETRIES)
                    {
                        throw new APIResponseException(APIHTTPErrorReason.ERROR_429, error);
                    }

                    // the limiter has been told about the Retry-After header, so the next attempt waits for it
                    continue;
                }

                case 500:
                case 502:
                case 503:
                case 504:
                {
                    attempts = sleepBeforeRetry(attempts, response.getResponseCode());
                    continue;
                }

                case 599:
                {
                    throw new APIResponseException(APIHTTPErrorReason.ERROR_599, response.getResponseData());
                }

                default:
                {
                    logger.error("Unhandled response code {}: {}", response.getResponseCode(), response.getResponseData());
                    throw new APINoValidResponseException(response.getResponseData());
                }
            }
        }
    }

    /**
     * Does a single attempt at the call; takes a parallel-call permit, waits for the ratelimiters, then does the request.
     * The permit is always released before this method returns.
     *
     * @return the response, or null if the thread was interrupted while waiting for a permit
     */
    private DataCallResponse execute(final String url)
    {
        if (this.dc.useRatelimiter())
        {
            if (context.getCredentials() == null)
            {
                throw new APIUnsupportedActionException("No API Creds set!");
            }

            dc.getUrlHeaders().putIfAbsent(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getLoLAPIKey());
        }

        // resolved after the default key has been added, so the permit and the limiters agree on which key is in use
        final ApiKeyType keyTypeUsed = this.dc.getKeyUsedByHeadersUsed();
        final Enum       platform    = this.dc.getPlatform();

        // We allow only a given number of thread to check limits and make calls at the same time / per platform + game
        final Semaphore semaphore = context.getSemaphore(keyTypeUsed, platform);
        if (semaphore != null)
        {
            try
            {
                semaphore.acquire();
            } catch (InterruptedException e)
            {
                logger.error("Interrupted while trying to acquire a permit for the API call: {}", url, e);
                Thread.currentThread().interrupt();
                return null;
            }
        }

        try
        {
            if (this.dc.useRatelimiter())
            {
                try
                {
                    acquireLimits(keyTypeUsed, platform, this.dc.getEndpoint());
                } catch (InterruptedException e)
                {
                    logger.error("Interrupted while waiting for the ratelimit for the API call: {}", url, e);
                    Thread.currentThread().interrupt();
                    return null;
                }
            }

            logger.trace("Trying url: {}", url);

            return this.getResponse(url);
        } finally
        {
            if (semaphore != null)
            {
                semaphore.release();
            }
        }
    }

    /**
     * Does the call, and returns the result as the requested type.
     *
     * @param type the type the endpoint returns
     * @param <T>  the type the endpoint returns
     * @return the result, or null if the api returned 404 (or something that is not of the requested type)
     */
    public <T> T buildAs(Class<T> type)
    {
        Object result = this.build();
        return type.isInstance(result) ? type.cast(result) : null;
    }

    /**
     * Returns the value from the cache if it is there, otherwise does the call and stores the result in the cache.
     *
     * @param type     the type the endpoint returns
     * @param cacheKey the values identifying this call in the cache (ie. platform, puuid), in a consistent order
     * @param <T>      the type the endpoint returns
     * @return the result, or null if the api returned 404 (or something that is not of the requested type)
     */
    public <T> T buildCached(Class<T> type, Map<String, Object> cacheKey)
    {
        URLEndpoint endpoint = this.dc.getEndpoint();

        Optional<?> cached = context.getCacheProvider().get(endpoint, cacheKey);
        if (cached.isPresent() && type.isInstance(cached.get()))
        {
            return type.cast(cached.get());
        }

        T result = buildAs(type);
        if (result != null)
        {
            Map<String, Object> data = new LinkedHashMap<>(cacheKey);
            data.put("value", result);
            context.getCacheProvider().store(endpoint, data);
        }

        return result;
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
                URLEndpoint.V4_SUMMONER_BY_PUUID,
                URLEndpoint.V4_SUMMONER_ME,
                URLEndpoint.V1_TFT_SUMMONER_BY_PUUID,
                URLEndpoint.V1_TFT_SUMMONER_ME);
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
                parent.remove(key);
                logger.warn("Item/Rune received without a valid Id ({}), item removed from the list", key);
            }
        }
        
        return Utils.getGson().toJson(elem);
    }
    
    private String postProcessSummoner(String returnValue)
    {
        JsonObject element = (JsonObject) JsonParser.parseString(returnValue);
        element.addProperty("platform", this.dc.getPlatform().toString());
        return Utils.getGson().toJson(element);
    }
    
    /**
     * Sleeps before the next attempt (500ms longer for each attempt).
     *
     * @param previousAttempts the amount of retries already done
     * @param errorCode        the response code that caused the retry
     * @return the new amount of retries done
     * @throws APINoValidResponseException if the total time slept is over the max sleep value
     */
    private int sleepBeforeRetry(int previousAttempts, int errorCode)
    {
        int  attempts           = previousAttempts + 1;
        long nextSleepDuration  = attempts * 500L;
        long totalSleepDuration = 0;
        for (int i = 1; i < attempts; i++)
        {
            totalSleepDuration += i * 500L;
        }

        if (totalSleepDuration > this.dc.getMaxSleep())
        {
            throw new APINoValidResponseException(String.format("API did not return a valid response in time. Total sleep time is over the max sleep value %s > %s...%n" +
                                                                "Try setting `DataCall.setDefaultMaxSleep(long)` to a larger number (default is 10000)",
                                                                (nextSleepDuration + totalSleepDuration), this.dc.getMaxSleep()));
        }

        if (errorCode == 429)
        {
            logger.info("Ratelimit reached too many times, waiting {} ms then retrying", nextSleepDuration);
        } else
        {
            logger.info("Server error ({}), waiting {} ms then retrying", errorCode, nextSleepDuration);
        }

        try
        {
            Thread.sleep(nextSleepDuration);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new APINoValidResponseException("Something interupted the API timeout;" + e.getMessage());
        }

        return attempts;
    }

    /**
     * Waits until both the app limit (of the platform) and the method limit (of the endpoint on the platform) have a permit, then takes both.
     * Nothing is locked while waiting, so waiting on one platform or endpoint never blocks calls to another.
     */
    private void acquireLimits(ApiKeyType keyType, Enum platform, Enum endpoint) throws InterruptedException
    {
        // for app limits, the "endpoint" is the platform itself
        RateLimiter app    = context.getLimiter(keyType, platform, platform);
        RateLimiter method = endpoint == null ? null : context.getLimiter(keyType, platform, endpoint);

        while (true)
        {
            long now = System.currentTimeMillis();

            // Service-limit cooldown in fail-fast mode: waiting here would hold the caller thread (and its parallel-call permit) for the whole cooldown.
            if (method != null && method.isCooldownFailFast())
            {
                long remaining = method.getCooldownRemaining(now);
                if (remaining > 0)
                {
                    throw new APIEndpointCooldownException(endpoint.name(), remaining);
                }
            }

            long wait = app.tryAcquire(now);
            if (wait == 0 && method != null)
            {
                wait = method.tryAcquire(now);
                if (wait != 0)
                {
                    // the call is not made yet, so it should not count against the app limit while we wait for the method limit
                    app.release();
                }
            }

            if (wait == 0)
            {
                return;
            }

            // a cooldown started by another process (sharing the limits through a store) is only known after asking for a permit
            if (method != null && method.isCooldownFailFast() && method.getCooldownRemaining(now) > 0)
            {
                throw new APIEndpointCooldownException(endpoint.name(), method.getCooldownRemaining(now));
            }

            logger.debug("{} {}: waiting {} ms for the ratelimit", platform, endpoint, wait);
            Thread.sleep(wait);
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
            final URL               target = new URL(url);
            final HttpURLConnection con    = (HttpURLConnection) target.openConnection();

            if (con instanceof HttpsURLConnection && isLoopback(target))
            {
                // only the local game client gets the relaxed certificate checks; calls to the internet use the JVM defaults
                ((HttpsURLConnection) con).setSSLSocketFactory(getLoopbackSocketFactory());
                ((HttpsURLConnection) con).setHostnameVerifier(loopbackHostnameVerifier);
            }

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

            if (logger.isTraceEnabled())
            {
                // the request headers are taken from the call, as the connection hides the security sensitive ones
                StringBuilder sb = new StringBuilder();
                this.dc.getUrlHeaders().forEach((key, value) -> sb.append(String.format(Constants.TABBED2X_VERBOSE_STRING_FORMAT, key, isSecretHeader(key) ? "[REDACTED]" : value)).append("\n"));

                String printMe = new StringBuilder("\n")
                        .append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "Url", url)).append("\n")
                        .append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "Request Method", con.getRequestMethod())).append("\n")
                        .append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "POST data", this.postData)).append("\n")
                        .append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "Request Headers", "")).append("\n")
                        .append(sb).toString();

                logger.trace(printMe);
            }

            final long sentAt = System.currentTimeMillis();

            if (null != this.postData && !this.postData.isEmpty())
            {
                con.setDoOutput(true);
                try (OutputStream writer = con.getOutputStream())
                {
                    // the body is json, which is always UTF-8
                    writer.write(this.postData.getBytes(StandardCharsets.UTF_8));
                    writer.flush();
                }
            }

            con.connect();
            final int responseCode = con.getResponseCode();
            Instant   received     = Instant.now();

            if (logger.isTraceEnabled())
            {
                StringBuilder sb2 = new StringBuilder("\n");
                con.getHeaderFields().forEach((key, value) -> sb2.append(String.format(Constants.TABBED2X_VERBOSE_STRING_FORMAT, key, value)).append("\n"));

                String printMe2 = new StringBuilder("\n").append(String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, "Response Headers", ""))
                                                         .append(sb2)
                                                         .toString();
                logger.trace(printMe2);
            }

            String appA    = con.getHeaderField("X-App-Rate-Limit");
            String appB    = con.getHeaderField("X-App-Rate-Limit-Count");
            String methodA = con.getHeaderField("X-Method-Rate-Limit");
            String methodB = con.getHeaderField("X-Method-Rate-Limit-Count");

            final ApiKeyType    keyType   = dc.getKeyUsedByHeadersUsed();
            final Enum          platform  = dc.getPlatform();
            final URLEndpoint   endpoint  = dc.getEndpoint();
            final RateLimitType limitType = responseCode == 429 ? RateLimitType.getBestMatch(con.getHeaderField("X-Rate-Limit-Type")) : null;

            // Quickly notify if a ratelimit was hit, we don't wait on any lock
            if (responseCode == 429)
            {
                logger.debug("All headers: {}", con.getHeaderFields());

                logger.warn("Ratelimit hit for platform: {}, endpoint: {}, method: {}, app limit: {}/{}, method limit: {}/{}",
                            platform, endpoint, this.requestMethod, appB, appA, methodB, methodA);

                // calls made without limiters (ddragon, local client..) have no limiter to notify
                RateLimiter methodLimiter = findLimiter(keyType, platform, endpoint);
                RateLimiter appLimiter    = findLimiter(keyType, platform, platform);
                String      retryAfter    = con.getHeaderField("Retry-After");

                if (limitType == RateLimitType.LIMIT_METHOD && methodLimiter != null)
                {
                    methodLimiter.cooldown(received.toEpochMilli(), retryAfter, false);
                }

                if (limitType == RateLimitType.LIMIT_USER && appLimiter != null)
                {
                    appLimiter.cooldown(received.toEpochMilli(), retryAfter, false);
                }

                if ((limitType == RateLimitType.LIMIT_UNDERLYING || limitType == RateLimitType.LIMIT_SERVICE) && methodLimiter != null)
                {
                    methodLimiter.cooldown(received.toEpochMilli(), retryAfter, context.shouldFailFastOnServiceRatelimit());
                }
            }

            if (appA == null)
            {
                logger.debug("Header 'X-App-Rate-Limit' missing from call: {} ", url);
            } else
            {
                // app level -> platform
                updateRatelimiter(appA, appB, platform, platform, sentAt);
            }

            if (methodA == null)
            {
                logger.debug("Header 'X-Method-Rate-Limit' missing from call: {}", url);
            } else if (endpoint != null)
            {
                // method level -> endpoint
                updateRatelimiter(methodA, methodB, platform, endpoint, sentAt);
            }

            String deprecationHeader = con.getHeaderField("X-Riot-Deprecated");
            if (deprecationHeader != null)
            {
                try
                {
                    logger.info("You are using a deprecated method, this method will stop working at: {}", Instant.ofEpochMilli(Long.parseLong(deprecationHeader)));
                } catch (NumberFormatException e)
                {
                    logger.info("You are using a deprecated method ({})", deprecationHeader);
                }
            }

            if (responseCode == 429)
            {
                StringBuilder          valueList        = new StringBuilder();
                Map<Enum, RateLimiter> platformLimiters = context.getLimiters(keyType).get(platform);
                if (platformLimiters != null)
                {
                    platformLimiters.forEach((key, value) -> {
                        valueList.append(key);
                        valueList.append("=");
                        valueList.append(value.getCallCounts());
                        valueList.append("\n");
                    });
                }

                String reasonString = String.format("%s%n%s", limitType.getReason(), valueList.toString().trim());
                String reason       = String.format("%s%n", reasonString);

                return new DataCallResponse(responseCode, reason);
            }

            if (responseCode == 204)
            {
                return new DataCallResponse(responseCode, "");
            }

            InputStream stream = (responseCode <= 399) ? con.getInputStream() : con.getErrorStream();

            if (stream == null)
            {
                return new DataCallResponse(responseCode, "Unable to read stream!");
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)))
            {
                br.lines().forEach(data::append);
            }

            con.disconnect();

            return new DataCallResponse(responseCode, data.toString());
        } catch (final IOException e)
        {
            // keep the cause, so a timeout can be told apart from a dns/tls/connection failure
            throw new APIResponseException(APIHTTPErrorReason.ERROR_599, APIHTTPErrorReason.ERROR_599.getReason() + " (" + e + ")", e);
        }
    }

    private static boolean isSecretHeader(String key)
    {
        return Constants.X_RIOT_TOKEN_HEADER_KEY.equalsIgnoreCase(key) || Constants.AUTHORIZATION.equalsIgnoreCase(key);
    }

    private RateLimiter findLimiter(ApiKeyType keyType, Enum platform, Enum endpoint)
    {
        if (endpoint == null)
        {
            return null;
        }

        Map<Enum, RateLimiter> platformLimiters = context.getLimiters(keyType).get(platform);
        return platformLimiters == null ? null : platformLimiters.get(endpoint);
    }

    /**
     * Makes sure the limiter obeys the limits the server reported, then syncs our call count with the count the server reported.
     */
    private void updateRatelimiter(String limitHeader, String countHeader, Enum platform, Enum endpoint, long sentAt)
    {
        RateLimiter limiter = context.getLimiter(dc.getKeyUsedByHeadersUsed(), platform, endpoint);

        try
        {
            if (limiter.updateLimits(parseLimits(limitHeader)))
            {
                logger.debug("Updated Ratelimit For {}: {}", endpoint, limiter.getLimits());
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
        {
            logger.warn("Unable to parse ratelimit header '{}'", limitHeader);
            return;
        }

        if (countHeader != null)
        {
            try
            {
                limiter.syncWithServerCounts(parseLimitFromHeader(countHeader), sentAt, System.currentTimeMillis());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
            {
                logger.warn("Unable to parse ratelimit count header '{}'", countHeader);
            }
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
    
    private List<RateLimit> parseLimits(String limitHeader)
    {
        List<RateLimit> limits = new ArrayList<>();
        for (Entry<Integer, Integer> entry : parseLimitFromHeader(limitHeader).entrySet())
        {
            limits.add(new RateLimit(entry.getValue(), entry.getKey(), TimeUnit.SECONDS));
        }

        return limits;
    }

    /**
     * @param limitHeader the value of a X-App-Rate-Limit or X-Method-Rate-Limit header (ie. "20:1,100:120")
     * @return a limiter obeying those limits
     */
    public RateLimiter createLimiter(String limitHeader)
    {
        return new RateLimiter(parseLimits(limitHeader));
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
        String encodedKey   = Utils.normalizeString(key);
        String encodedValue = Utils.normalizeString(value);
        this.dc.getUrlData().merge(encodedKey, (this.dc.getUrlData().get(encodedKey) != null) ? ("&" + encodedKey + "=" + encodedValue) : encodedValue, MERGE_AS_SET);
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
        // the values are url-encoded here (and not when the url is built), as repeated keys are merged into one value
        this.dc.getUrlData().merge(Utils.normalizeString(key), Utils.normalizeString(value), MERGE);
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
    
    /**
     * Sets the access token of a player that signed in with RSO (sent as "Authorization: Bearer token")
     *
     * @param accessToken the access token, with or without the "Bearer " prefix
     * @return this
     */
    public DataCallBuilder withAccessToken(final String accessToken)
    {
        this.dc.getUrlHeaders().put(Constants.AUTHORIZATION, accessToken.startsWith("Bearer ") ? accessToken : "Bearer " + accessToken);
        return this;
    }

    public DataCallBuilder withProxy(String proxy)
    {
        this.dc.setProxy(proxy);
        return this;
    }
    public DataCallBuilder withMaxSleep(long maxSleep)
    {
        this.dc.setMaxSleep(maxSleep);
        return this;
    }
}
