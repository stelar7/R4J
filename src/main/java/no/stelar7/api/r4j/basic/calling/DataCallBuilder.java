package no.stelar7.api.r4j.basic.calling;

import com.google.gson.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.constants.types.RealmSpecificEnum;
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
import java.util.concurrent.*;
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

        RetryState state = new RetryState();
        state.attempts = (retrys != null && retrys.length == 1) ? retrys[0] : 0;

        // Retries are done in a loop (instead of recursion) so the parallel-call permit is never held while sleeping or re-acquired while already held
        while (true)
        {
            final DataCallResponse response = this.execute(url);
            if (response == null)
            {
                // interrupted while waiting for a permit
                return null;
            }

            Object result = interpret(url, response, state);
            if (result != RETRY)
            {
                return result;
            }

            if (state.delay > 0)
            {
                try
                {
                    Thread.sleep(state.delay);
                } catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    throw new APINoValidResponseException("Something interupted the API timeout;" + e.getMessage());
                }
            }
        }
    }

    /**
     * What has to be remembered between the attempts of one call
     */
    private static final class RetryState
    {
        // the amount of retries done after a server error or a service ratelimit
        private int  attempts;
        // the amount of retries done after an application or method ratelimit
        private int  ratelimitRetries;
        // how long to wait before the next attempt
        private long delay;
    }

    /**
     * Returned by {@link #interpret(String, DataCallResponse, RetryState)} when the call has to be made again
     */
    private static final Object RETRY = new Object();

    /**
     * Decides what a response means: a result, an exception, or another attempt.
     * Shared by the blocking and the non-blocking way of making a call.
     *
     * @return the result of the call (null and Pair included), or {@link #RETRY} after setting {@code state.delay} to how long to wait first
     */
    private Object interpret(String url, DataCallResponse response, RetryState state)
    {
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

                    return context.bind(Utils.getGson().fromJson(returnValue, returnType));
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
                    state.delay = nextRetryDelay(state, response.getResponseCode());
                    return RETRY;
                }

                String error = response.getResponseData() + "429 ratelimit hit! " +
                               "Please do not restart your application to refresh the timer! " +
                               "This isn't supposed to happen unless you restarted your app before the last limit was hit!";

                logger.error(error);

                if (++state.ratelimitRetries > MAX_RATELIMIT_RETRIES)
                {
                    throw new APIResponseException(APIHTTPErrorReason.ERROR_429, error);
                }

                // the limiter has been told about the Retry-After header, so the next attempt waits for it
                state.delay = 0;
                return RETRY;
            }

            case 500:
            case 502:
            case 503:
            case 504:
            {
                state.delay = nextRetryDelay(state, response.getResponseCode());
                return RETRY;
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

    /**
     * Makes the call without blocking a thread: waiting for the ratelimit, the request itself and the retries all happen in the background.
     * <p>
     * The outcome is the same as {@link #build(int...)}: the future completes with the parsed result (or a Pair for a 404),
     * and completes exceptionally with the exception build() would have thrown.
     * <p>
     * Calls to the game client on this machine over https (LCU, live client data, replay api) need certificate checks relaxed per connection,
     * which only the blocking transport can do; those are run with build() on a background thread instead.
     *
     * @return a future of what build() returns
     */
    public CompletableFuture<Object> buildAsync()
    {
        final String url;
        try
        {
            url = this.getURL();

            URL target = new URL(url);
            if ("https".equalsIgnoreCase(target.getProtocol()) && isLoopback(target))
            {
                return CompletableFuture.supplyAsync(() -> this.build(), context.getAsyncExecutor());
            }
        } catch (IOException | RuntimeException e)
        {
            return CompletableFuture.failedFuture(e);
        }

        CompletableFuture<Object> result = new CompletableFuture<>();
        attemptAsync(url, new RetryState(), result);
        return result;
    }

    /**
     * Like {@link #buildAs(Class)}, without blocking a thread.
     *
     * @param type the type the endpoint returns
     * @param <T>  the type the endpoint returns
     * @return a future of the result, or of null if the api returned 404 (or something that is not of the requested type)
     */
    public <T> CompletableFuture<T> buildAsAsync(Class<T> type)
    {
        return buildAsync().thenApply(result -> type.isInstance(result) ? type.cast(result) : null);
    }

    /**
     * Like {@link #buildCached(Class, Map)}, without blocking a thread for the call to the api.
     * The cache itself is read and written directly, so a slow cache (ie. a database) is waited for.
     *
     * @param type     the type the endpoint returns
     * @param cacheKey the values identifying this call in the cache (ie. platform, puuid), in a consistent order
     * @param <T>      the type the endpoint returns
     * @return a future of the result, or of null if the api returned 404 (or something that is not of the requested type)
     */
    public <T> CompletableFuture<T> buildCachedAsync(Class<T> type, Map<String, Object> cacheKey)
    {
        URLEndpoint endpoint = this.dc.getEndpoint();

        try
        {
            Optional<?> cached = context.getCacheProvider().get(endpoint, cacheKey);
            if (cached.isPresent() && type.isInstance(cached.get()))
            {
                return CompletableFuture.completedFuture(context.bind(type.cast(cached.get())));
            }
        } catch (RuntimeException e)
        {
            return CompletableFuture.failedFuture(e);
        }

        return buildAsAsync(type).thenApply(result -> {
            if (result != null)
            {
                Map<String, Object> data = new LinkedHashMap<>(cacheKey);
                data.put("value", result);
                context.getCacheProvider().store(endpoint, data);
            }

            return result;
        });
    }

    /**
     * How often a call that could not get a parallel-call permit tries again
     */
    private static final long PERMIT_POLL_MS = 10;

    /**
     * One attempt of a non-blocking call. Nothing in here waits: when the call can not be made yet, the attempt is scheduled again.
     */
    private void attemptAsync(final String url, final RetryState state, final CompletableFuture<Object> result)
    {
        if (result.isDone())
        {
            // cancelled by the caller
            return;
        }

        try
        {
            if (this.dc.useRatelimiter())
            {
                if (context.getCredentials() == null)
                {
                    throw new APIUnsupportedActionException("No API Creds set!");
                }

                dc.getUrlHeaders().putIfAbsent(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getLoLAPIKey());
            }

            final ApiKeyType keyTypeUsed = this.dc.getKeyUsedByHeadersUsed();
            final Enum       platform    = this.dc.getPlatform();
            final Semaphore  semaphore   = context.getSemaphore(keyTypeUsed, platform);

            if (semaphore != null && !semaphore.tryAcquire())
            {
                context.schedule(() -> attemptAsync(url, state, result), PERMIT_POLL_MS);
                return;
            }

            boolean handedOver = false;
            long    wait;
            try
            {
                wait = this.dc.useRatelimiter() ? tryAcquireLimits(keyTypeUsed, platform, this.dc.getEndpoint()) : 0;
                if (wait == 0)
                {
                    logger.trace("Trying url: {}", url);

                    final long sentAt = System.currentTimeMillis();
                    CompletableFuture<java.net.http.HttpResponse<String>> pending = context.getHttpClient().sendAsync(toRequest(url), java.net.http.HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
                    handedOver = true;

                    pending.whenComplete((httpResponse, error) -> {
                        try
                        {
                            finishCall(keyTypeUsed, platform, semaphore);

                            if (error != null)
                            {
                                Throwable cause = error instanceof CompletionException && error.getCause() != null ? error.getCause() : error;
                                throw new APIResponseException(APIHTTPErrorReason.ERROR_599, APIHTTPErrorReason.ERROR_599.getReason() + " (" + cause + ")", cause);
                            }

                            int    code        = httpResponse.statusCode();
                            String limitReason = handleRateLimitHeaders(url, code, name -> httpResponse.headers().firstValue(name).orElse(null), sentAt, Instant.now());

                            DataCallResponse response;
                            if (limitReason != null)
                            {
                                response = new DataCallResponse(code, limitReason);
                            } else
                            {
                                response = new DataCallResponse(code, code == 204 || httpResponse.body() == null ? "" : httpResponse.body());
                            }

                            Object outcome = interpret(url, response, state);
                            if (outcome != RETRY)
                            {
                                result.complete(outcome);
                            } else
                            {
                                context.schedule(() -> attemptAsync(url, state, result), state.delay);
                            }
                        } catch (Throwable t)
                        {
                            result.completeExceptionally(t);
                        }
                    });
                    return;
                }
            } finally
            {
                if (!handedOver && semaphore != null)
                {
                    semaphore.release();
                }
            }

            // The parallel-call permit was given back, and nothing waits here: the attempt simply runs again when the ratelimit allows it
            logger.debug("{} {}: waiting {} ms for the ratelimit", platform, this.dc.getEndpoint(), wait);
            context.schedule(() -> attemptAsync(url, state, result), wait);
        } catch (Throwable t)
        {
            result.completeExceptionally(t);
        }
    }

    /**
     * What has to happen when a call has finished, whatever the outcome
     */
    private void finishCall(ApiKeyType keyTypeUsed, Enum platform, Semaphore semaphore)
    {
        try
        {
            if (this.dc.useRatelimiter())
            {
                // lets the next call through if this was the first call of the bucket (see RateLimiter.callFinished)
                context.getLimiter(keyTypeUsed, platform, platform).callFinished();
                if (this.dc.getEndpoint() != null)
                {
                    context.getLimiter(keyTypeUsed, platform, this.dc.getEndpoint()).callFinished();
                }
            }
        } finally
        {
            if (semaphore != null)
            {
                semaphore.release();
            }
        }
    }

    /**
     * The request of the non-blocking transport, with the same headers, method and body as the blocking one sends
     */
    private java.net.http.HttpRequest toRequest(String url)
    {
        java.net.http.HttpRequest.Builder request = java.net.http.HttpRequest.newBuilder(URI.create(url))
                                                                             .timeout(Duration.ofMillis(this.dc.getReadTimeout()))
                                                                             .header("User-Agent", "R4J")
                                                                             .header("Accept-Charset", "ISO-8859-1,utf-8")
                                                                             .header("Accept-Language", "en-US")
                                                                             .header("Cache-Control", "no-store,max-age=0,no-cache")
                                                                             .header("Expires", "0")
                                                                             .header("Pragma", "no-cache");

        // setHeader replaces, like setRequestProperty does for the blocking transport
        request.setHeader("Content-Type", "application/json");
        this.dc.getUrlHeaders().forEach(request::setHeader);

        java.net.http.HttpRequest.BodyPublisher body = (this.postData != null && !this.postData.isEmpty())
                                                       ? java.net.http.HttpRequest.BodyPublishers.ofString(this.postData, StandardCharsets.UTF_8)
                                                       : java.net.http.HttpRequest.BodyPublishers.noBody();

        if (requestMethod.equalsIgnoreCase("PATCH"))
        {
            request.setHeader("X-HTTP-Method-Override", "PATCH");
            request.method("POST", body);
        } else
        {
            request.method(requestMethod.toUpperCase(Locale.ENGLISH), body);
        }

        return request.build();
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

        // We allow only a given number of calls to be made at the same time / per platform + game
        final Semaphore semaphore = context.getSemaphore(keyTypeUsed, platform);

        while (true)
        {
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

            long wait;
            try
            {
                wait = this.dc.useRatelimiter() ? tryAcquireLimits(keyTypeUsed, platform, this.dc.getEndpoint()) : 0;
                if (wait == 0)
                {
                    logger.trace("Trying url: {}", url);

                    try
                    {
                        return this.getResponse(url);
                    } finally
                    {
                        if (this.dc.useRatelimiter())
                        {
                            // lets the next call through if this was the first call of the bucket (see RateLimiter.callFinished)
                            context.getLimiter(keyTypeUsed, platform, platform).callFinished();
                            if (this.dc.getEndpoint() != null)
                            {
                                context.getLimiter(keyTypeUsed, platform, this.dc.getEndpoint()).callFinished();
                            }
                        }
                    }
                }
            } finally
            {
                if (semaphore != null)
                {
                    semaphore.release();
                }
            }

            // The parallel-call permit is not held while waiting for the ratelimit, so a call that has to wait
            // does not keep calls to other endpoints of the platform from being made.
            logger.debug("{} {}: waiting {} ms for the ratelimit", platform, this.dc.getEndpoint(), wait);
            try
            {
                Thread.sleep(wait);
            } catch (InterruptedException e)
            {
                logger.error("Interrupted while waiting for the ratelimit for the API call: {}", url, e);
                Thread.currentThread().interrupt();
                return null;
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
            return context.bind(type.cast(cached.get()));
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

    /**
     * Like {@link #buildCached(Class, Map)}, for the type the endpoint is declared to return.
     * Useful when that type is a list wrapper (ie. the endpoint returns a ClashPlayerList, which is a {@code List<ClashPlayer>}).
     *
     * @param cacheKey the values identifying this call in the cache, in a consistent order
     * @param <T>      the type the endpoint returns, or something it can be assigned to
     * @return the result, or null if the api returned 404
     */
    @SuppressWarnings("unchecked")
    public <T> T buildCached(Map<String, Object> cacheKey)
    {
        return (T) buildCached(this.dc.getEndpoint().getType(), cacheKey);
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
     * How long to wait before the next attempt after a server error or a service ratelimit (500ms longer for each attempt).
     *
     * @param state     the retries done so far; the attempt is counted
     * @param errorCode the response code that caused the retry
     * @return how long to wait, in ms
     * @throws APINoValidResponseException if the total time waited is over the max sleep value
     */
    private long nextRetryDelay(RetryState state, int errorCode)
    {
        int  attempts           = ++state.attempts;
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

        return nextSleepDuration;
    }

    /**
     * Takes a permit from both the app limit (of the platform) and the method limit (of the endpoint on the platform), if both have one.
     *
     * @return 0 if both permits were taken, otherwise how long to wait (in ms) before trying again
     * @throws APIEndpointCooldownException if the endpoint is in a service-limit cooldown, and calls should fail instead of wait
     */
    private long tryAcquireLimits(ApiKeyType keyType, Enum platform, Enum endpoint)
    {
        // for app limits, the "endpoint" is the platform itself
        RateLimiter app    = context.getLimiter(keyType, platform, platform);
        RateLimiter method = endpoint == null ? null : context.getLimiter(keyType, platform, endpoint);

        long now = System.currentTimeMillis();

        // Service-limit cooldown in fail-fast mode: the caller prefers an exception over waiting out the cooldown.
        if (method != null && method.isCooldownFailFast() && method.getCooldownRemaining(now) > 0)
        {
            throw new APIEndpointCooldownException(endpoint.name(), method.getCooldownRemaining(now));
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

        // a cooldown started by another process (sharing the limits through a store) is only known after asking for a permit
        if (wait != 0 && method != null && method.isCooldownFailFast() && method.getCooldownRemaining(now) > 0)
        {
            throw new APIEndpointCooldownException(endpoint.name(), method.getCooldownRemaining(now));
        }

        return wait;
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

            String limitReason = handleRateLimitHeaders(url, responseCode, con::getHeaderField, sentAt, received);
            if (limitReason != null)
            {
                // the body is not used, but has to be read for the connection to be reused
                drain(con.getErrorStream());

                return new DataCallResponse(responseCode, limitReason);
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

            // The connection is not disconnected: once the body has been read and closed, the JDK keeps it open and uses it
            // for the next call to the same host, which saves a TCP and TLS handshake per call.

            return new DataCallResponse(responseCode, data.toString());
        } catch (final IOException e)
        {
            // keep the cause, so a timeout can be told apart from a dns/tls/connection failure
            throw new APIResponseException(APIHTTPErrorReason.ERROR_599, APIHTTPErrorReason.ERROR_599.getReason() + " (" + e + ")", e);
        }
    }

    /**
     * Reads and closes a stream we have no use for, so the connection can be reused
     */
    private static void drain(InputStream stream)
    {
        if (stream == null)
        {
            return;
        }

        try (InputStream in = stream)
        {
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1)
            {
                // discard
            }
        } catch (IOException e)
        {
            // nothing to do, the connection is simply not reused
        }
    }

    /**
     * Updates the ratelimiters from the headers of a response: the limits and call counts the server reports,
     * and the cooldown after a 429. Shared by the blocking and the non-blocking transport.
     *
     * @param header gives the value of a response header by its name, or null
     * @return null, or for a 429 the text describing which limit was hit (used instead of the body of the response)
     */
    private String handleRateLimitHeaders(String url, int responseCode, java.util.function.Function<String, String> header, long sentAt, Instant received)
    {
        String appA    = header.apply("X-App-Rate-Limit");
        String appB    = header.apply("X-App-Rate-Limit-Count");
        String methodA = header.apply("X-Method-Rate-Limit");
        String methodB = header.apply("X-Method-Rate-Limit-Count");

        final ApiKeyType    keyType   = dc.getKeyUsedByHeadersUsed();
        final Enum          platform  = dc.getPlatform();
        final URLEndpoint   endpoint  = dc.getEndpoint();
        final RateLimitType limitType = responseCode == 429 ? RateLimitType.getBestMatch(header.apply("X-Rate-Limit-Type")) : null;

        // Quickly notify if a ratelimit was hit, we don't wait on any lock
        if (responseCode == 429)
        {
            logger.warn("Ratelimit hit for platform: {}, endpoint: {}, method: {}, app limit: {}/{}, method limit: {}/{}",
                        platform, endpoint, this.requestMethod, appB, appA, methodB, methodA);

            // calls made without limiters (ddragon, local client..) have no limiter to notify
            RateLimiter methodLimiter = findLimiter(keyType, platform, endpoint);
            RateLimiter appLimiter    = findLimiter(keyType, platform, platform);
            String      retryAfter    = header.apply("Retry-After");

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

        String deprecationHeader = header.apply("X-Riot-Deprecated");
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

            return reason;
        }

        return null;
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
            url[0] = url[0].replace(Constants.REGION_PLACEHOLDER, ((RealmSpecificEnum) dc.getPlatform()).getRealmValue());
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
