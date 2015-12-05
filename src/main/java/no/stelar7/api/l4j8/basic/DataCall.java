package no.stelar7.api.l4j8.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;

import javafx.util.Pair;

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
                DataCall.limiter.computeIfAbsent(this.dc.server, (final Server s) -> RateLimiter.create((double) DataCall.LIMITER_PERMITS_PER_10_MINUTES / (double) DataCall.LIMITER_10_MINUTES)).acquire();
            }

            final String url = this.getURL();
            final Pair<Integer, Pair<String, Integer>> response = this.getResponse(url);

            try
            {
                if (response.getKey() == 200)
                {
                    final Object type = this.dc.endpoint.getType();
                    Object dtoobj;
                    if (type instanceof Class<?>)
                    {
                        dtoobj = new Gson().fromJson(response.getValue().getKey(), (Class<?>) this.dc.endpoint.getType());
                    } else
                    {
                        dtoobj = new Gson().fromJson(response.getValue().getKey(), (Type) this.dc.endpoint.getType());
                    }
                    return dtoobj;
                }

                if (response.getKey() == 429)
                {
                    if (this.dc.retry)
                    {
                        TimeUnit.SECONDS.sleep(response.getValue().getValue());
                        return this.build();
                    }
                }
            } catch (final Exception e)
            {
                e.printStackTrace();
            }

            throw new RuntimeException(response.getValue().getKey());
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

        private Pair<Integer, Pair<String, Integer>> getResponse(final String url) throws RuntimeException
        {
            try
            {
                final HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

                con.setUseCaches(false);
                con.setDefaultUseCaches(false);
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36");
                con.setRequestProperty("Cache-Control", "no-store,max-age=0,no-cache");
                con.setRequestProperty("Expires", "0");
                con.setRequestProperty("Pragma", "no-cache");
                con.setRequestProperty("Connection", "keep-alive");
                con.connect();

                if (this.dc.verbose)
                {
                    System.out.println(url);
                    con.getHeaderFields().forEach((key, value) -> {
                        System.out.println(key + ":\t" + value);
                    });
                }

                final StringBuilder data = new StringBuilder();

                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)))
                {
                    br.lines().forEach(s -> data.append(s));
                }

                final String limitType = con.getHeaderField("X-Rate-Limit-Type");
                int timeout = 0;

                if ((limitType != null) && (limitType.equals(DataCall.LIMIT_USER) || limitType.equals(DataCall.LIMIT_SERVICE)))
                {
                    final String timeoutString = con.getHeaderField("Retry-After");

                    timeout = timeoutString == null ? this.dc.retryTime : Integer.parseInt(timeoutString);
                }

                con.disconnect();

                return new Pair<Integer, Pair<String, Integer>>(con.getResponseCode(), new Pair<String, Integer>(data.toString(), timeout));
            } catch (final IOException e)
            {
                e.printStackTrace();
            }

            throw new RuntimeException("Reached end of getResponse, without a valid response!!");
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

            this.withURLData("{region}", this.dc.region.asURLFormat());
            this.withURLData("{version}", this.dc.endpoint.getVersion());

            this.dc.urlData.forEach((k, v) -> {
                String temp = this.urlBuilder.toString();
                temp = temp.toString().replace(k, v);
                this.urlBuilder.setLength(0);
                this.urlBuilder.append(temp);
            });

            this.urlBuilder.append("?api_key=").append(this.dc.key);
            this.dc.urlParams.forEach((k, v) -> this.urlBuilder.append("&").append(k).append("=").append(v));

            return this.urlBuilder.toString();
        }

        public DataCallBuilder useCache(final boolean flag)
        {
            this.dc.useCache = flag;
            return this;
        }

        public DataCallBuilder withAPIKey(final String key)
        {
            this.dc.key = key;
            return this;
        }

        public DataCallBuilder withEndpoint(final URLEndpoint endpoint)
        {
            this.dc.endpoint = endpoint;
            return this;
        }

        public DataCallBuilder withRegion(final Server region)
        {
            this.dc.region = region;
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

    private static final Map<Server, RateLimiter> limiter                        = new HashMap<Server, RateLimiter>();

    private static final String                   HTTP                           = "http://";

    private static final String                   HTTPS                          = "https://";

    private static final String                   LIMIT_USER                     = "service";
    private static final String                   LIMIT_SERVICE                  = "user";
    private static final Integer                  LIMITER_PERMITS_PER_10_MINUTES = 500;
    private static final Integer                  LIMITER_10_MINUTES             = 600;

    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }

    public static void setRateLimit(final int permits, final int seconds)
    {
        final double permitsPerSecond = (double) permits / (double) seconds;
        Arrays.stream(Server.values()).filter(s -> s.isLimited()).forEach(s -> DataCall.limiter.put(s, RateLimiter.create(permitsPerSecond)));
    }

    private String                    key;
    private final Map<String, String> urlData   = new HashMap<>();

    private final Map<String, String> urlParams = new HashMap<>();
    private boolean                   retry     = true;
    private boolean                   verbose   = false;

    public boolean                    useCache  = true;

    private int                       retryTime = 5;
    private Server                    server;

    private Server                    region;

    private URLEndpoint               endpoint;

}
