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
            final Triplet<Integer, String, Integer> response = this.getResponse(url);

            try
            {
                if ((response.getA() == 200) || (response.getA() == 204))
                {
                    final Object type = this.dc.endpoint.getType();
                    Object dtoobj;
                    if (type instanceof Class<?>)
                    {
                        dtoobj = new Gson().fromJson(response.getB(), (Class<?>) this.dc.endpoint.getType());
                    } else
                    {
                        dtoobj = new Gson().fromJson(response.getB(), (Type) this.dc.endpoint.getType());
                    }

                    if (dtoobj instanceof Map)
                    {

                        Map<?, ?> map = (Map<?, ?>) dtoobj;
                        if (map.get(map.keySet().iterator().next()) instanceof Summoner)
                        {
                            map.values().forEach(value -> {
                                try
                                {
                                    Field field = Summoner.class.getDeclaredField("server");
                                    field.setAccessible(true);
                                    field.set(value, this.dc.region);
                                } catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                            });
                        }
                    }

                    return dtoobj;
                }

                if (response.getA() == 404)
                {
                    return Optional.empty();
                }

                if (response.getA() == 429)
                {
                    if (this.dc.retry)
                    {
                        if (this.dc.verbose)
                        {
                            System.out.println("HIT 429, WAITING " + response.getC() + " SECONDS THEN TRYING AGAIN");
                        }
                        TimeUnit.SECONDS.sleep(response.getC());
                        return this.build();
                    }
                }
            } catch (final Exception e)
            {
                e.printStackTrace();
            }

            throw new RuntimeException(response.getB());
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

        private Triplet<Integer, String, Integer> getResponse(final String url) throws RuntimeException
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
                    return new Triplet<Integer, String, Integer>(con.getResponseCode(), null, timeout);
                }

                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)))
                {
                    br.lines().forEach(s -> data.append(s));
                }

                con.disconnect();

                return new Triplet<Integer, String, Integer>(con.getResponseCode(), data.toString(), timeout);
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

    public enum ResponseType
    {
        OK,
        ERROR;
    }

    static class Triplet<T, U, V>
    {
        T a;
        U b;
        V c;

        Triplet(final T a, final U b, final V c)
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        /**
         * Response code
         *
         */
        T getA()
        {
            return this.a;
        }

        /**
         * Response data
         *
         */
        U getB()
        {
            return this.b;
        }

        /**
         * Retry timeout
         *
         */
        V getC()
        {
            return this.c;
        }
    }

    private static final Map<Server, RateLimiter> limiter                        = new HashMap<Server, RateLimiter>();

    private static final String                   HTTP                           = "http://";

    private static final String                   HTTPS                          = "https://";
    private static final String                   LIMIT_USER                     = "service";
    private static final String                   LIMIT_SERVICE                  = "user";
    private static final Double                   LIMITER_PERMITS_PER_10_MINUTES = 500.0;

    private static final Double                   LIMITER_10_MINUTES             = 600.0;

    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }

    public static void setRateLimit(final int permits, final int seconds)
    {
        final double permitsPerSecond = (double) permits / (double) seconds;
        Arrays.stream(Server.values()).filter(s -> s.isLimited()).forEach(s -> DataCall.limiter.put(s, RateLimiter.create(permitsPerSecond)));
    }

    private String                    postData      = "";

    private String                    requestMethod = "GET";
    private final Map<String, String> urlData       = new TreeMap<>();
    private final Map<String, String> urlParams     = new TreeMap<>();

    private final Map<String, String> urlHeaders    = new TreeMap<>();
    private boolean                   retry         = true;

    private boolean                   verbose       = false;

    private int                       retryTime     = 5;
    private Server                    server;

    private Server                    region;

    private URLEndpoint               endpoint;

    private APICredentials            creds;

}
