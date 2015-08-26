package no.stelar7.api.l4j8.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javafx.util.Pair;

public class DataCall
{

    private static final Map<Server, RateLimiter> limiter = new HashMap<Server, RateLimiter>()
    {
        {
            Arrays.stream(Server.values()).filter(s -> s.hasLimit()).forEach(o -> put(o, new RateLimiter()));
        }
    };

    private Map<String, String> urlData   = new HashMap<>();
    private Map<String, String> urlParams = new HashMap<>();
    private boolean             retry     = true;
    private int                 retryTime = 5;
    private boolean             verbose   = false;
    private Server              server;
    private URLEndpoint         endpoint;
    private String              key;

    private static final String STATIC_PREFIX = "api/lol/static-data";
    private static final String HTTP          = "http://";
    private static final String HTTPS         = "https://";
    private static final String LIMIT_USER    = "service";
    private static final String LIMIT_SERVICE = "user";

    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }

    public enum ResponseType
    {
        OK,
        USER_ERROR,
        LIBRARY_ERROR,
        SERVER_ERROR;
    }

    public static class DataCallBuilder
    {
        private final DataCall dc = new DataCall();

        public DataCallBuilder withEndpoint(URLEndpoint endpoint)
        {
            dc.endpoint = endpoint;
            return this;
        }

        public DataCallBuilder withVerbose(boolean verbose)
        {
            dc.verbose = verbose;
            return this;
        }

        public DataCallBuilder withURLData(String key, String value)
        {
            try
            {
                dc.urlData.merge(key, URLEncoder.encode(value, "UTF-8"), (o, n) -> o + "," + n);
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return this;
        }

        public DataCallBuilder withURLParameter(String key, String value)
        {
            try
            {
                dc.urlParams.merge(key, URLEncoder.encode(value, "UTF-8"), (o, n) -> o + "," + n);
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return this;
        }

        public DataCallBuilder withServer(Server server)
        {
            dc.server = server;
            return this;
        }

        public DataCallBuilder withRetryOn429(boolean retry)
        {
            dc.retry = retry;
            return this;
        }

        public DataCallBuilder withRetryTime(int retryTime)
        {
            dc.retryTime = retryTime;
            return this;
        }

        public DataCallBuilder withAPIKey(String key)
        {
            dc.key = key;
            return this;
        }

        public String getURL()
        {
            StringBuilder sb = new StringBuilder();
            if (!dc.endpoint.getValue().startsWith(HTTP))
            {
                sb.append(HTTPS);
            } else
            {
                dc.server = Server.GLOBAL;
            }

            sb.append(dc.server.getURL()).append(dc.endpoint.getValue());

            withURLData("{region}", dc.server.toReadable());
            withURLData("{version}", dc.endpoint.getVersion());

            dc.urlData.forEach((k, v) -> {
                String temp = sb.toString();
                temp = temp.toString().replace(k, v);
                sb.setLength(0);
                sb.append(temp);
            });

            sb.append("?api_key=").append(dc.key);
            dc.urlParams.forEach((k, v) -> sb.append("&").append(k).append("=").append(v));

            return sb.toString();
        }

        public Pair<ResponseType, Object> build()
        {
            if (!dc.endpoint.getValue().startsWith(HTTP))
            {
                Optional<Exception> error = DataCall.limiter.get(dc.server).acquire();
                if (error.isPresent())
                {
                    return new Pair<>(ResponseType.USER_ERROR, new APIError(error.get()));
                }
            }

            String url = getURL();

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

                if (dc.verbose)
                {
                    System.out.println(url);
                    con.getHeaderFields().forEach((key, value) -> {
                        System.out.println(key + ":\t" + value);
                    });
                }

                if (con.getResponseCode() != 200)
                {
                    if (con.getResponseCode() == -1 || con.getResponseCode() == 404)
                    {
                        return new Pair<>(ResponseType.USER_ERROR, new APIError(con.getResponseCode()));
                    }

                    if (con.getResponseCode() == 429)
                    {
                        if (dc.retry)
                        {
                            String limitType = con.getHeaderField("X-Rate-Limit-Type");
                            if (limitType != null && (limitType.equals(LIMIT_USER) || limitType.equals(LIMIT_SERVICE)))
                            {
                                String timeoutString = con.getHeaderField("Retry-After");
                                long timeout = timeoutString == null ? dc.retryTime : Integer.parseInt(timeoutString);
                                TimeUnit.SECONDS.sleep(timeout);
                                con.disconnect();
                                return build();
                            }

                            TimeUnit.SECONDS.sleep(dc.retryTime);
                            con.disconnect();
                            return build();
                        }
                    } else
                    {
                        con.disconnect();
                        return new Pair<>(con.getResponseCode() < 499 ? ResponseType.USER_ERROR : ResponseType.SERVER_ERROR, new APIError(con.getResponseCode()));
                    }
                }

                StringBuilder data = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())))
                {
                    br.lines().forEach(s -> data.append(s));
                }
                con.disconnect();

                Object dtoobj = ((APIObject) dc.endpoint.type.newInstance()).createFromString(dc.endpoint.type, data.toString());

                if (dtoobj instanceof Map && ((Map<?, ?>) dtoobj).values().size() == 1)
                {
                    return new Pair<>(ResponseType.OK, ((Map<?, ?>) dtoobj).values().stream().findFirst().get());
                }

                if (dtoobj instanceof List && ((List<?>) dtoobj).size() == 1)
                {
                    return new Pair<>(ResponseType.OK, ((List<?>) dtoobj).stream().findFirst().get());
                }

                return new Pair<>(ResponseType.OK, dtoobj);
            } catch (Exception e)
            {
                return new Pair<>(ResponseType.LIBRARY_ERROR, new APIError(e));
            }
        }
    }
}
