package no.stelar7.api.l4j8.basic;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import com.google.common.util.concurrent.*;
import com.google.gson.*;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.*;

public class DataCall
{

    public static class DataCallBuilder
    {
        private final DataCall                           dc         = new DataCall();
        private final BiFunction<String, String, String> merge      = ((o, n) -> o + "," + n);
        final StringBuilder                              urlBuilder = new StringBuilder();

        public DataCallBuilder asVerbose(final boolean verbose)
        {
            this.dc.verbose = verbose;
            return this;
        }

        public Object build()
        {

            if (!this.dc.endpoint.isAvalibleFrom(this.dc.server))
            {
                throw new RuntimeException(this.dc.endpoint.toString() + " not avalible from " + this.dc.server.asURLFormat());
            }

            final boolean isServerRatelimited = this.dc.server.isLimited();
            final boolean isToRatelimitedURL = !this.dc.endpoint.getValue().startsWith(DataCall.HTTP);

            if (isServerRatelimited && isToRatelimitedURL)
            {
                DataCall.limiter.computeIfAbsent(this.dc.server, (final Server s) -> RateLimiter.create(DataCall.LIMITER_PERMITS_PER_10_MINUTES / DataCall.LIMITER_10_MINUTES)).acquire();
            }

            final String url = this.getURL();
            final DataCallResponse<Integer, String, Integer> response = this.getResponse(url);

            try
            {
                if ((response.getResponseCode() == 200) || (response.getResponseCode() == 204))
                {
                    final Object type = this.dc.endpoint.getType();
                    Object dtoobj;
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
                        if (map.get(map.keySet().iterator().next()) instanceof Summoner)
                        {
                            map.values().forEach(value -> {
                                try
                                {
                                    final Field field = Summoner.class.getDeclaredField("server");
                                    field.setAccessible(true);
                                    field.set(value, this.dc.region);
                                } catch (final Exception e)
                                {
                                    e.printStackTrace();
                                }

                            });
                        }
                    }

                    return dtoobj;
                }

                if (response.getResponseCode() == 404)
                {
                    return Optional.empty();
                }

                if (response.getResponseCode() == 429)
                {
                    if (this.dc.retry)
                    {
                        if (this.dc.verbose)
                        {
                            System.out.println("HIT 429, WAITING " + response.getRetryTimeout() + " SECOND(S) THEN TRYING AGAIN");
                        }
                        TimeUnit.SECONDS.sleep(response.getRetryTimeout());
                        return this.build();
                    }
                }

                if (response.getResponseCode() == 500)
                {
                    if (this.dc.retry)
                    {
                        if (this.dc.verbose)
                        {
                            System.out.println("HIT 500, WAITING 1 SECOND(S) THEN TRYING AGAIN");
                        }
                        TimeUnit.SECONDS.sleep(1);
                        return this.build();
                    }
                }

            } catch (final Exception e)
            {
                e.printStackTrace();
            }

            System.err.println("Response Code:" + response.getResponseCode());
            System.err.println("Response Data:" + response.getResponseData());
            System.err.println("Rate-Limit:" + response.getRetryTimeout());
            throw new RuntimeException(response.getResponseData());
        }

        public CompletableFuture<Object> buildAsync()
        {
            return CompletableFuture.supplyAsync(() -> this.build());
        }

        public DataCallBuilder clearURLData()
        {
            this.dc.urlData.clear();
            return this;
        }

        public DataCallBuilder clearURLParameter()
        {
            this.dc.urlParams.clear();
            return this;
        }

        private DataCallResponse<Integer, String, Integer> getResponse(final String url) throws RuntimeException
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
                this.dc.urlHeaders.forEach((k, v) -> con.setRequestProperty(k, v));

                con.setRequestMethod(this.dc.requestMethod);

                if (this.dc.verbose)
                {
                    System.out.format("%1$-20s: %2$-40s%n", "URL", url);
                    System.out.format("%1$-20s: %2$-40s%n", "Request Method", con.getRequestMethod());
                    System.out.format("%1$-20s: %2$-40s%n", "POST", this.dc.postData);
                    System.out.println();
                    System.out.format("%1$-20s: %2$-40s%n", "Request Headers", "");
                    con.getRequestProperties().forEach((key, value) -> System.out.format("\t%1$-20s: %2$-40s%n", key, value));
                    System.out.println();
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
                    System.out.format("%1$-20s: %2$-40s%n", "Response Headers", "");
                    con.getHeaderFields().forEach((key, value) -> System.out.format("\t%1$-20s: %2$-40s%n", key, value));
                    System.out.println();
                }

                final String limitType = con.getHeaderField("X-Rate-Limit-Type");
                int timeout = 0;

                if ((limitType != null) && (limitType.equals(DataCall.LIMIT_USER) || limitType.equals(DataCall.LIMIT_SERVICE)))
                {
                    final String timeoutString = con.getHeaderField("Retry-After");

                    timeout = timeoutString == null ? this.dc.retryTime : Integer.parseInt(timeoutString);
                }

                if (con.getResponseCode() != 200)
                {
                    return new DataCallResponse<Integer, String, Integer>(con.getResponseCode(), null, timeout);
                }

                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)))
                {
                    br.lines().forEach(s -> data.append(s));
                }

                con.disconnect();

                return new DataCallResponse<Integer, String, Integer>(con.getResponseCode(), data.toString(), timeout);
            } catch (final IOException e)
            {
                e.printStackTrace();
            }

            throw new RuntimeException("Reached end of getResponse, without a valid response!!\n");
        }

        public String getURL()
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

            this.dc.urlData.forEach((k, v) -> {
                String temp = this.urlBuilder.toString();
                temp = temp.toString().replace(k, v);
                this.urlBuilder.setLength(0);
                this.urlBuilder.append(temp);
            });

            this.dc.urlParams.put("api_key", this.dc.creds.API_KEY);

            final Optional<String> firstKey = this.dc.urlParams.keySet().stream().findFirst();
            if (firstKey.isPresent())
            {
                this.urlBuilder.append("?").append(firstKey.get()).append("=").append(this.dc.urlParams.get(firstKey.get()));
                this.dc.urlParams.keySet().stream().skip(1).forEach(k -> this.urlBuilder.append("&").append(k).append("=").append(this.dc.urlParams.get(k)));
            }

            return this.urlBuilder.toString();
        }

        public DataCallBuilder withAPICredentials(final APICredentials creds)
        {
            this.dc.creds = creds;
            return this;
        }

        public DataCallBuilder withEndpoint(final URLEndpoint endpoint)
        {
            this.dc.endpoint = endpoint;
            return this;
        }

        public DataCallBuilder withHeader(final String key, final String value)
        {
            this.dc.urlHeaders.merge(key, value, this.merge);
            return this;
        }

        public DataCallBuilder withPostData(final String data)
        {
            this.dc.postData = data;
            return this;
        }

        public DataCallBuilder withRegion(final Server region)
        {
            this.dc.region = region;
            return this;
        }

        public DataCallBuilder withRequestMethod(final String data)
        {
            this.dc.requestMethod = data;
            return this;
        }

        public DataCallBuilder withRetryOn429(final boolean retry)
        {
            this.dc.retry = retry;
            return this;
        }

        public DataCallBuilder withRetryTime(final int retryTime)
        {
            this.dc.retryTime = retryTime;
            return this;
        }

        public DataCallBuilder withServer(final Server server)
        {
            this.dc.server = server;
            return this;
        }

        public DataCallBuilder withURLData(final String key, final String value)
        {
            this.dc.urlData.merge(key, value, this.merge);
            return this;
        }

        public DataCallBuilder withURLParameter(final String key, final String value)
        {
            this.dc.urlParams.merge(key, value, this.merge);
            return this;
        }

    }

    static class DataCallResponse<T, U, V>
    {
        T responseCode;
        U responseData;
        V retryTimeout;

        DataCallResponse(final T a, final U b, final V c)
        {
            this.responseCode = a;
            this.responseData = b;
            this.retryTimeout = c;
        }

        /**
         * Response code
         *
         */
        T getResponseCode()
        {
            return this.responseCode;
        }

        /**
         * Response data
         *
         */
        U getResponseData()
        {
            return this.responseData;
        }

        /**
         * Retry timeout
         *
         */
        V getRetryTimeout()
        {
            return this.retryTimeout;
        }
    }

    public enum ResponseType
    {
        OK,
        ERROR;
    }

    private static final Map<Server, RateLimiter> limiter                        = new HashMap<Server, RateLimiter>();

    private static final String                   HTTP                           = "http://";
    private static final String                   HTTPS                          = "https://";

    private static final String                   LIMIT_USER                     = "user";
    private static final String                   LIMIT_SERVICE                  = "service";

    private static final Double                   LIMITER_PERMITS_PER_10_MINUTES = 500.0;
    private static final Double                   LIMITER_10_MINUTES             = 600.0;

    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }

    public static void setRateLimit(final double permits, final double seconds)
    {
        final double permitsPerSecond = permits / seconds;
        Arrays.stream(Server.values()).filter(s -> s.isLimited()).forEach(s -> DataCall.limiter.put(s, RateLimiter.create(permitsPerSecond)));
    }

    private String                    postData      = "";

    private final Map<String, String> urlData       = new TreeMap<>();
    private final Map<String, String> urlParams     = new TreeMap<>();
    private final Map<String, String> urlHeaders    = new TreeMap<>();

    private boolean                   retry         = true;
    private boolean                   verbose       = L4J8.verbose;
    private int                       retryTime     = 5;
    private String                    requestMethod = "GET";

    private Server                    server;
    private Server                    region;

    private URLEndpoint               endpoint;
    private APICredentials            creds;

}
