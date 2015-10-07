package no.stelar7.api.l4j8.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

import javafx.util.Pair;

public class DataCall
{

    public static class DataCallBuilder
    {
        private final DataCall dc = new DataCall();

        private final BiFunction<String, String, String> merge = ((o, n) -> o + "," + n);

        public Pair<ResponseType, Object> build()
        {
            if (!this.dc.endpoint.getValue().startsWith(DataCall.HTTP))
            {
                final Optional<Exception> error = DataCall.limiter.get(this.dc.server).acquire();
                if (error.isPresent())
                {
                    return new Pair<>(ResponseType.USER_ERROR, new APIError(error.get()));
                }
            }

            final String url = this.getURL();

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

                if (con.getResponseCode() != 200)
                {
                    if ((con.getResponseCode() == -1) || (con.getResponseCode() == 404))
                    {
                        return new Pair<>(ResponseType.USER_ERROR, new APIError(con.getResponseCode()));
                    }

                    if (con.getResponseCode() == 429)
                    {
                        if (this.dc.retry)
                        {
                            final String limitType = con.getHeaderField("X-Rate-Limit-Type");
                            if ((limitType != null) && (limitType.equals(DataCall.LIMIT_USER) || limitType.equals(DataCall.LIMIT_SERVICE)))
                            {
                                final String timeoutString = con.getHeaderField("Retry-After");
                                final long timeout = timeoutString == null ? this.dc.retryTime : Integer.parseInt(timeoutString);
                                TimeUnit.SECONDS.sleep(timeout);
                                con.disconnect();
                                return this.build();
                            }

                            TimeUnit.SECONDS.sleep(this.dc.retryTime);
                            con.disconnect();
                            return this.build();
                        }
                    } else
                    {
                        con.disconnect();
                        return new Pair<>(con.getResponseCode() < 499 ? ResponseType.USER_ERROR : ResponseType.SERVER_ERROR, new APIError(con.getResponseCode()));
                    }
                }

                final StringBuilder data = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())))
                {
                    br.lines().forEach(s -> data.append(s));
                }
                con.disconnect();

                final Object dtoobj = ((APIObject) this.dc.endpoint.getType().newInstance()).createFromString(this.dc.endpoint.getType(), data.toString());

                if ((dtoobj instanceof Map) && (((Map<?, ?>) dtoobj).values().size() == 1))
                {
                    return new Pair<>(ResponseType.OK, ((Map<?, ?>) dtoobj).values().stream().findFirst().get());
                }

                if ((dtoobj instanceof List) && (((List<?>) dtoobj).size() == 1))
                {
                    return new Pair<>(ResponseType.OK, ((List<?>) dtoobj).stream().findFirst().get());
                }

                return new Pair<>(ResponseType.OK, dtoobj);
            } catch (final Exception e)
            {
                return new Pair<>(ResponseType.LIBRARY_ERROR, new APIError(e));
            }
        }

        public String getURL()
        {
            final StringBuilder sb = new StringBuilder();
            if (!this.dc.endpoint.getValue().startsWith(DataCall.HTTP))
            {
                sb.append(DataCall.HTTPS);
            } else
            {
                this.dc.server = Server.GLOBAL;
            }

            sb.append(this.dc.server.getURL()).append(this.dc.endpoint.getValue());

            this.withURLData("{region}", this.dc.server.toReadable());
            this.withURLData("{version}", this.dc.endpoint.getVersion());

            this.dc.urlData.forEach((k, v) -> {
                String temp = sb.toString();
                temp = temp.toString().replace(k, v);
                sb.setLength(0);
                sb.append(temp);
            });

            sb.append("?api_key=").append(this.dc.key);
            this.dc.urlParams.forEach((k, v) -> sb.append("&").append(k).append("=").append(v));

            return sb.toString();
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
            try
            {
                this.dc.urlData.merge(key, URLEncoder.encode(value, "UTF-8"), this.merge);
            } catch (final UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return this;
        }

        public DataCallBuilder withURLParameter(final String key, final String value)
        {
            try
            {
                this.dc.urlParams.merge(key, URLEncoder.encode(value, "UTF-8"), this.merge);
            } catch (final UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return this;
        }

        public DataCallBuilder withVerbose(final boolean verbose)
        {
            this.dc.verbose = verbose;
            return this;
        }
    }

    public enum ResponseType
    {
        OK,
        USER_ERROR,
        LIBRARY_ERROR,
        SERVER_ERROR;
    }

    private static final Map<Server, RateLimiter> limiter       = new HashMap<Server, RateLimiter>()
    {
        {
            Arrays.stream(Server.values()).filter(s -> s.hasLimit()).forEach(o -> this.put(o, new RateLimiter()));
        }
    };
    private static final String                   HTTP          = "http://";
    private static final String                   HTTPS         = "https://";
    private static final String                   LIMIT_USER    = "service";
    private static final String                   LIMIT_SERVICE = "user";

    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }

    private final Map<String, String> urlData = new HashMap<>();

    private final Map<String, String> urlParams = new HashMap<>();
    private boolean                   retry     = true;
    private int                       retryTime = 5;
    private boolean                   verbose   = false;

    private Server server;

    private URLEndpoint endpoint;

    private String key;
}
