package no.stelar7.api.l4j8.basic;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.logging.*;

import com.google.common.util.concurrent.*;
import com.google.gson.*;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.exceptions.*;
import no.stelar7.api.l4j8.pojo.summoner.*;

public class DataCall
{

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

        private final DataCall                           dc         = new DataCall();

        private final BiFunction<String, String, String> merge      = (o, n) -> o + "," + n;

        final StringBuilder                              urlBuilder = new StringBuilder();

        /**
         * Print out as much data as possible about this call
         *
         * @param flag
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
         * @throws APINoValidResponseException
         */
        public Object build()
        {

            if (!this.dc.endpoint.isAvalibleFrom(this.dc.server))
            {
                throw new APIServerEndpointMissmatchException(this.dc.endpoint.toString() + " not avalible from " + this.dc.server.asURLFormat());
            }

            final boolean isServerRatelimited = this.dc.server.isLimited();
            final boolean isToRatelimitedURL = !this.dc.endpoint.getValue().startsWith(DataCall.HTTP);

            if (isServerRatelimited && isToRatelimitedURL)
            {
                DataCall.limiter.get(this.dc.server).acquire();
            }

            final String url = this.getURL();
            final DataCallResponse response = this.getResponse(url);

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
                    map.values().stream().filter(value -> value instanceof Summoner).forEach(this::setServerOnSummoner);
                }

                return dtoobj;
            }

            if (response.getResponseCode() == 404)
            {
                return Optional.empty();
            }

            if (response.getResponseCode() == 429)
            {

                if (this.dc.verbose)
                {
                    DataCall.LOGGER.log(Level.INFO, "HIT 429, WAITING " + response.getRetryTimeout() + " SECOND(S) THEN TRYING AGAIN");
                }

                if (this.dc.retry)
                {
                    try
                    {
                        TimeUnit.SECONDS.sleep(response.getRetryTimeout());
                        return this.build();
                    } catch (final Exception e)
                    {
                        DataCall.LOGGER.log(Level.WARNING, e.getMessage(), e);
                    }
                }
            }

            if (response.getResponseCode() == 500)
            {
                if (this.dc.verbose)
                {
                    DataCall.LOGGER.log(Level.INFO, "HIT 500 ERROR, WAITING 1 SECOND(S) THEN TRYING AGAIN");
                }

                if (this.dc.retry)
                {
                    try
                    {
                        TimeUnit.SECONDS.sleep(1);
                        return this.build();
                    } catch (final Exception e)
                    {
                        DataCall.LOGGER.log(Level.WARNING, e.getMessage(), e);
                    }
                }
            }

            DataCall.LOGGER.log(Level.WARNING, "Response Code:" + response.getResponseCode());
            DataCall.LOGGER.log(Level.WARNING, "Response Data:" + response.getResponseData());
            DataCall.LOGGER.log(Level.WARNING, "Rate-Limit:" + response.getRetryTimeout());
            throw new APINoValidResponseException(response.getResponseData());
        }

        /**
         * Builds this call in an async way
         *
         * @return a call that will complete in the future
         */
        public CompletableFuture<Object> buildAsync()
        {
            return CompletableFuture.supplyAsync(this::build);
        }

        /**
         * Clears all the set data from the URL
         *
         * @return
         */
        public DataCallBuilder clearURLData()
        {
            this.dc.urlData.clear();
            return this;
        }

        /**
         * Clears all the set parameters on the URL
         *
         * @return this
         */
        public DataCallBuilder clearURLParameter()
        {
            this.dc.urlParams.clear();
            return this;
        }

        /**
         * Opens a connection to the URL, then reads the data into a Response.
         *
         * @param url
         *            the URL to call
         * @return a DataCallResponse with the data from the call
         * @throws APINoValidResponseException
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
                    con.getRequestProperties().forEach((key, value) -> DataCall.LOGGER.log(Level.INFO, String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value)));
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
                    con.getHeaderFields().forEach((key, value) -> DataCall.LOGGER.log(Level.INFO, String.format(Constants.TABBED_VERBOSE_STRING_FORMAT, key, value)));
                }

                final String limitType = con.getHeaderField("X-Rate-Limit-Type");
                int timeout = 0;

                if (limitType != null)
                {
                    if (limitType.equals(DataCall.LIMIT_USER) || limitType.equals(DataCall.LIMIT_SERVICE))
                    {
                        final String timeoutString = con.getHeaderField("Retry-After");

                        timeout = timeoutString == null ? this.dc.retryTime : Integer.parseInt(timeoutString);
                    } else
                    {
                        timeout = this.dc.retryTime;
                    }
                }
                final String limitCount = con.getHeaderField("X-Rate-Limit-Count");

                if (limitCount != null)
                {
                    final String[] limits = limitCount.split(",");
                    for (final String limitPair : limits)
                    {
                        final String[] limit = limitPair.split(":");
                        final Integer call = Integer.parseInt(limit[0]);
                        final Integer time = Integer.parseInt(limit[1]);
                        DataCall.calls.put(time, call);
                    }
                }

                if (con.getResponseCode() != 200)
                {
                    return new DataCallResponse(con.getResponseCode(), null, timeout);
                }

                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)))
                {
                    br.lines().forEach(data::append);
                }

                con.disconnect();

                return new DataCallResponse(con.getResponseCode(), data.toString(), timeout);
            } catch (final IOException e)
            {
                DataCall.LOGGER.log(Level.WARNING, e.getMessage(), e);
            }

            throw new APINoValidResponseException("Reached end of getResponse, without a valid response!!\n");
        }

        /**
         * Generates the URL to use for the call.
         *
         * @return the URL to use for the call.
         */
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
                temp = temp.replace(k, v);
                this.urlBuilder.setLength(0);
                this.urlBuilder.append(temp);
            });

            this.dc.urlParams.put("api_key", DataCall.creds.baseApiKey);

            final Optional<String> firstKey = this.dc.urlParams.keySet().stream().findFirst();
            if (firstKey.isPresent())
            {
                this.urlBuilder.append("?").append(firstKey.get()).append("=").append(this.dc.urlParams.get(firstKey.get()));
                this.dc.urlParams.keySet().stream().skip(1).forEach(k -> this.urlBuilder.append("&").append(k).append("=").append(this.dc.urlParams.get(k)));
            }

            return this.urlBuilder.toString();
        }

        private void setServerOnSummoner(final Object sum)
        {
            try
            {
                final Field field = Summoner.class.getDeclaredField("server");
                field.setAccessible(true);
                field.set(sum, this.dc.region);
            } catch (final Exception e)
            {
                DataCall.LOGGER.log(Level.WARNING, e.getMessage(), e);
            }
        }

        /**
         * Sets the endpoint to make the call to
         *
         * @param endpoint
         *            the endpoint to make the call to
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
         * @param key
         *            the header key
         * @param value
         *            the header value
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
         * @param data
         *            the data to send
         * @return this
         */
        public DataCallBuilder withPostData(final String data)
        {
            this.dc.postData = data;
            return this;
        }

        /**
         * The region to make this call to. This is part of the URL, "{region}"
         *
         * @param region
         *            the region to make the call to
         * @return this
         */
        public DataCallBuilder withRegion(final Server region)
        {
            this.dc.region = region;
            return this;
        }

        /**
         * The request-method on the call (usually GET or POST)
         *
         * @param method
         *            the request method
         * @return this
         */
        public DataCallBuilder withRequestMethod(final String method)
        {
            this.dc.requestMethod = method;
            return this;
        }

        /**
         * The call should retry if it hits a 429 - Too Many Requests
         *
         * @param flag
         *            if it should retry
         * @return this
         */
        public DataCallBuilder withRetryOn429(final boolean flag)
        {
            this.dc.retry = flag;
            return this;
        }

        /**
         * The time to wait to retry, if the call fails
         *
         * @param retryTime
         *            the time to wait
         * @return this
         */
        public DataCallBuilder withRetryTime(final int retryTime)
        {
            this.dc.retryTime = retryTime;
            return this;
        }

        /**
         * Set the server to make this call to. (ie. EUW)
         *
         * @param server
         *            the server to make the call to
         * @return this
         */
        public DataCallBuilder withServer(final Server server)
        {
            this.dc.server = server;
            return this;
        }

        /**
         * Replaces placeholders in the URL (ie. {region})
         *
         * @param key
         *            The key to replace (ie. {region})
         * @param value
         *            The data to replace it with (ie. EUW)
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
         * @param key
         *            the parameter to add (ie. api_key)
         * @param value
         *            the value to add after the parameter (ie. 6fa459ea-ee8a-3ca4-894e-db77e160355e)
         * @return this
         */
        public DataCallBuilder withURLParameter(final String key, final String value)
        {
            this.dc.urlParams.merge(key, value, this.merge);
            return this;
        }

    }

    static class DataCallResponse
    {
        int    responseCode;
        String responseData;
        int    retryTimeout;

        DataCallResponse(final int a, final String b, final int c)
        {
            this.responseCode = a;
            this.responseData = b;
            this.retryTimeout = c;
        }

        /**
         * Response code
         *
         */
        int getResponseCode()
        {
            return this.responseCode;
        }

        /**
         * Response data
         *
         */
        String getResponseData()
        {
            return this.responseData;
        }

        /**
         * Retry timeout
         *
         */
        int getRetryTimeout()
        {
            return this.retryTimeout;
        }
    }

    private static final String                       HTTP                                   = "http://";
    private static final String                       HTTPS                                  = "https://";

    private static final String                       LIMIT_USER                             = "user";
    private static final String                       LIMIT_SERVICE                          = "service";

    private static final Double                       DEFAULT_LIMITER_PERMITS_PER_10_MINUTES = 500.0;

    private static final Double                       DEFAULT_LIMITER_10_MINUTES             = 600.0;
    public static final Logger                        LOGGER                                 = Logger.getGlobal();

    private static APICredentials                     creds;
    private static Map<Integer, Integer>              calls                                  = new HashMap<>();
    private static final EnumMap<Server, RateLimiter> limiter                                = new EnumMap<>(Server.class);

    public static DataCallBuilder builder()
    {
        return new DataCallBuilder();
    }

    public static Map<Integer, Integer> getCalls()
    {
        return DataCall.calls;
    }

    public static int getCallsInTimePeriod(final int period)
    {
        return DataCall.calls.get(period);
    }

    private String                    postData      = "";

    private final Map<String, String> urlData       = new TreeMap<>();
    private final Map<String, String> urlParams     = new TreeMap<>();

    private final Map<String, String> urlHeaders    = new TreeMap<>();

    private boolean                   retry         = true;

    private boolean                   verbose       = false;

    private int                       retryTime     = 5;

    private String                    requestMethod = "GET";

    private Server                    server;

    private Server                    region;

    private URLEndpoint               endpoint;

    private DataCall()
    {
        final double permitsPerSecond = DataCall.DEFAULT_LIMITER_PERMITS_PER_10_MINUTES / DataCall.DEFAULT_LIMITER_10_MINUTES;
        Arrays.stream(Server.values()).filter(s -> s.isLimited()).forEach(s -> DataCall.limiter.put(s, RateLimiter.create(permitsPerSecond)));

    }

}
