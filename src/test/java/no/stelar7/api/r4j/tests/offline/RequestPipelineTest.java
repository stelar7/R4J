package no.stelar7.api.r4j.tests.offline;

import com.sun.net.httpserver.*;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.exceptions.*;
import no.stelar7.api.r4j.basic.ratelimiting.*;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Runs the request pipeline against a local http server, so it needs no api key and no network.
 */
public class RequestPipelineTest
{
    private static final String STATUS_JSON = "{\"id\":\"EUW1\",\"name\":\"EU West\",\"locales\":[],\"maintenances\":[],\"incidents\":[]}";

    private static HttpServer server;
    private static String     proxy;

    /**
     * what the server should do with the next requests; each test installs its own
     */
    private static volatile HttpHandler handler;

    private static APICredentials oldCredentials;
    private static int            oldParallel;

    @BeforeAll
    public static void startServer() throws IOException
    {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> handler.handle(exchange));
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        proxy = "http://127.0.0.1:" + server.getAddress().getPort() + "/{game}/{service}/{version}/{resource}";

        oldCredentials = DataCall.getCredentials();
        oldParallel = DataCall.getNumberOfCallsAllowedInParallel();
        DataCall.setCredentials(new APICredentials("offline-test-key"));
        DataCall.setCacheProvider(null);
    }

    @AfterAll
    public static void stopServer()
    {
        server.stop(0);
        DataCall.setCredentials(oldCredentials);
        DataCall.setNumberOfCallsAllowedInParallel(oldParallel);
    }

    private static void respond(HttpExchange exchange, int code, String body) throws IOException
    {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length == 0 ? -1 : bytes.length);
        if (bytes.length > 0)
        {
            try (OutputStream os = exchange.getResponseBody())
            {
                os.write(bytes);
            }
        }
        exchange.close();
    }

    private DataCallBuilder statusCall(LeagueShard platform)
    {
        return new DataCallBuilder().withProxy(proxy).withEndpoint(URLEndpoint.V4_STATUS_LOL).withPlatform(platform);
    }

    @Test
    public void retriesDoNotDeadlockWithASinglePermit()
    {
        DataCall.setNumberOfCallsAllowedInParallel(1);

        AtomicInteger calls = new AtomicInteger();
        handler = exchange -> {
            if (calls.incrementAndGet() <= 2)
            {
                respond(exchange, 503, "{}");
            } else
            {
                respond(exchange, 200, STATUS_JSON);
            }
        };

        // the old recursive retry acquired the (only) permit a second time and hung forever
        PlatformData data = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(20), () -> statusCall(LeagueShard.EUW1).buildAs(PlatformData.class));

        Assertions.assertNotNull(data);
        Assertions.assertEquals("EUW1", data.getId());
        Assertions.assertEquals(3, calls.get());

        DataCall.setNumberOfCallsAllowedInParallel(-1);
    }

    @Test
    public void permitIsReleasedAfterEveryOutcome() throws Exception
    {
        DataCall.setNumberOfCallsAllowedInParallel(1);
        handler = exchange -> respond(exchange, 403, "{}");

        // if a failed call leaked its permit, the second call would block
        for (int i = 0; i < 3; i++)
        {
            Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
                APIResponseException e = Assertions.assertThrows(APIResponseException.class, () -> statusCall(LeagueShard.NA1).build());
                Assertions.assertEquals(APIHTTPErrorReason.ERROR_403, e.getReason());
            });
        }

        DataCall.setNumberOfCallsAllowedInParallel(-1);
    }

    @Test
    public void serverErrorsGiveUpAfterMaxSleep()
    {
        AtomicInteger calls = new AtomicInteger();
        handler = exchange -> {
            calls.incrementAndGet();
            respond(exchange, 500, "{}");
        };

        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(20), () ->
                Assertions.assertThrows(APINoValidResponseException.class, () -> statusCall(LeagueShard.KR).withMaxSleep(600).build()));

        // first attempt, then a retry after 500ms and one after 1000ms
        Assertions.assertEquals(3, calls.get());
    }

    @Test
    public void notFoundIsNullWhenTyped()
    {
        handler = exchange -> respond(exchange, 404, "{\"status\":{\"status_code\":404}}");
        Assertions.assertNull(statusCall(LeagueShard.JP1).buildAs(PlatformData.class));
    }

    @Test
    public void methodNotAllowedIsReportedAs405()
    {
        handler = exchange -> respond(exchange, 405, "{}");
        APIResponseException e = Assertions.assertThrows(APIResponseException.class, () -> statusCall(LeagueShard.BR1).build());
        Assertions.assertEquals(APIHTTPErrorReason.ERROR_405, e.getReason());
    }

    @Test
    public void requestBodyIsSentAsUtf8()
    {
        String                  body     = "{\"name\":\"Ærlig øl ✓ 한글\"}";
        AtomicReference<String> received = new AtomicReference<>();
        AtomicReference<String> token    = new AtomicReference<>();

        handler = exchange -> {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[]                buf = new byte[1024];
            int                   read;
            try (InputStream is = exchange.getRequestBody())
            {
                while ((read = is.read(buf)) != -1)
                {
                    bos.write(buf, 0, read);
                }
            }

            received.set(new String(bos.toByteArray(), StandardCharsets.UTF_8));
            token.set(exchange.getRequestHeaders().getFirst("X-Riot-Token"));
            respond(exchange, 200, STATUS_JSON);
        };

        statusCall(LeagueShard.TR1).withRequestMethod("POST").withPostData(body).build();

        Assertions.assertEquals(body, received.get());
        Assertions.assertEquals("offline-test-key", token.get());
    }

    @Test
    public void queryParametersAreUrlEncoded()
    {
        AtomicReference<String> query = new AtomicReference<>();
        handler = exchange -> {
            query.set(exchange.getRequestURI().getRawQuery());
            respond(exchange, 200, STATUS_JSON);
        };

        statusCall(LeagueShard.SG2).withQueryParameter("name", "a b&c=d+e")
                                   .withQueryParameter("multi", "1")
                                   .withQueryParameter("multi", "2")
                                   .build();

        Assertions.assertEquals("name=a%20b%26c%3Dd%2Be&multi=1,2", query.get());
    }

    @Test
    public void aBurstOfFirstCallsStaysWithinTheLimit() throws Exception
    {
        // its own context, so nothing is known about the limits yet
        R4JContext fresh = new R4JContext(new APICredentials("cold-start-key"));

        // the server allows 3 calls per second, and counts like the api: a window starts with the first call after the previous one ended
        AtomicInteger inFlight      = new AtomicInteger();
        AtomicInteger mostInFlight  = new AtomicInteger();
        AtomicInteger firstCalls    = new AtomicInteger();
        AtomicInteger overTheLimit  = new AtomicInteger();
        long[]        window        = {0, 0};
        AtomicBoolean limitsHandedOut = new AtomicBoolean();

        handler = exchange -> {
            int count;
            synchronized (window)
            {
                long now = System.currentTimeMillis();
                if (now >= window[0] + 1000)
                {
                    window[0] = now;
                    window[1] = 0;
                }
                count = (int) ++window[1];
                if (count > 3)
                {
                    overTheLimit.incrementAndGet();
                }
            }

            if (!limitsHandedOut.get())
            {
                // calls that arrive before any response has left: these went out without knowing the limits
                firstCalls.incrementAndGet();
                mostInFlight.accumulateAndGet(inFlight.incrementAndGet(), Math::max);
                try
                {
                    Thread.sleep(300);
                } catch (InterruptedException ignored)
                {
                    Thread.currentThread().interrupt();
                }
                inFlight.decrementAndGet();
            }

            exchange.getResponseHeaders().add("X-App-Rate-Limit", "3:1");
            exchange.getResponseHeaders().add("X-App-Rate-Limit-Count", count + ":1");
            limitsHandedOut.set(true);
            respond(exchange, 200, STATUS_JSON);
        };

        ExecutorService pool = Executors.newFixedThreadPool(8);
        try
        {
            List<Future<Object>> calls = new ArrayList<>();
            for (int i = 0; i < 8; i++)
            {
                calls.add(pool.submit(() -> new DataCallBuilder(fresh).withProxy(proxy).withEndpoint(URLEndpoint.V4_STATUS_LOL).withPlatform(LeagueShard.VN2).build()));
            }

            for (Future<Object> call : calls)
            {
                Assertions.assertNotNull(call.get(30, TimeUnit.SECONDS));
            }
        } finally
        {
            pool.shutdownNow();
        }

        // only one call went out before the limits were known, and the other seven were then spread over the windows
        Assertions.assertEquals(1, firstCalls.get());
        Assertions.assertEquals(1, mostInFlight.get());
        Assertions.assertEquals(0, overTheLimit.get());
    }

    @Test
    public void connectionsAreReused()
    {
        Set<Integer>  clientPorts = ConcurrentHashMap.newKeySet();
        AtomicInteger calls       = new AtomicInteger();
        handler = exchange -> {
            clientPorts.add(exchange.getRemoteAddress().getPort());
            // a mix of outcomes: every one of them has to leave the connection usable
            switch (calls.incrementAndGet() % 3)
            {
                case 0:
                    respond(exchange, 404, "{\"status\":{\"status_code\":404}}");
                    break;
                case 1:
                    respond(exchange, 200, STATUS_JSON);
                    break;
                default:
                    respond(exchange, 204, "");
            }
        };

        for (int i = 0; i < 9; i++)
        {
            statusCall(LeagueShard.TW2).build();
        }

        // every call came from the same client port, so it was the same connection
        Assertions.assertEquals(9, calls.get());
        Assertions.assertEquals(1, clientPorts.size(), "ports used: " + clientPorts);
    }

    @Test
    public void theConnectionIsReusedAfterA429()
    {
        Set<Integer>  clientPorts = ConcurrentHashMap.newKeySet();
        AtomicInteger calls       = new AtomicInteger();
        handler = exchange -> {
            clientPorts.add(exchange.getRemoteAddress().getPort());
            if (calls.incrementAndGet() == 1)
            {
                // a service limit with a body; the body is not used, but it has to be read for the connection to be reusable
                respond(exchange, 429, "{\"status\":{\"message\":\"Rate limit exceeded\",\"status_code\":429}}");
            } else
            {
                respond(exchange, 200, STATUS_JSON);
            }
        };

        // retried after 500ms
        Assertions.assertNotNull(statusCall(LeagueShard.TH2).withLimiters(false).build());

        Assertions.assertEquals(2, calls.get());
        Assertions.assertEquals(1, clientPorts.size(), "ports used: " + clientPorts);
    }

    @Test
    public void ratelimitedCallWithoutLimitersDoesNotCrash()
    {
        // no X-Rate-Limit-Type header means a service limit, which is retried with a backoff
        handler = exchange -> {
            exchange.getResponseHeaders().add("Retry-After", "1");
            respond(exchange, 429, "{}");
        };

        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(20), () ->
                Assertions.assertThrows(APINoValidResponseException.class, () -> statusCall(LeagueShard.RU).withLimiters(false).withMaxSleep(100).build()));
    }

    @Test
    public void limiterIsCreatedFromHeadersAndSyncedWithServerCounts()
    {
        handler = exchange -> {
            exchange.getResponseHeaders().add("X-App-Rate-Limit", "20:1,100:120");
            exchange.getResponseHeaders().add("X-App-Rate-Limit-Count", "7:1,55:120");
            exchange.getResponseHeaders().add("X-Method-Rate-Limit", "500:10");
            exchange.getResponseHeaders().add("X-Method-Rate-Limit-Count", "1:10");
            respond(exchange, 200, STATUS_JSON);
        };

        statusCall(LeagueShard.OC1).build();

        Map<Enum, RateLimiter> limiters = DataCall.getLimiter(ApiKeyType.LOL).get(LeagueShard.OC1);
        Assertions.assertNotNull(limiters);

        RateLimiter app = limiters.get(LeagueShard.OC1);
        Assertions.assertEquals(2, app.getLimits().size(), "the app limiter should get its limits from the X-App-Rate-Limit header");
        
        // we made 1 call, but the server says 55 calls were made in the 120s window (another process, or a restart)
        long count120 = app.getCallCounts().entrySet().stream()
                           .filter(e -> e.getKey().getTimeframeInMS() == TimeUnit.SECONDS.toMillis(120))
                           .mapToLong(Map.Entry::getValue)
                           .findFirst().orElse(-1);
        Assertions.assertEquals(55, count120);
        
        RateLimiter method = limiters.get(URLEndpoint.V4_STATUS_LOL);
        Assertions.assertEquals(1, method.getLimits().size());
    }
    
    @Test
    public void waitingOnOnePlatformDoesNotBlockAnother() throws Exception
    {
        CountDownLatch la1Entered = new CountDownLatch(1);
        CountDownLatch release    = new CountDownLatch(1);

        handler = exchange -> {
            if (exchange.getRequestHeaders().getFirst("X-Test-Platform").equals("LA1"))
            {
                la1Entered.countDown();
                try
                {
                    release.await(15, TimeUnit.SECONDS);
                } catch (InterruptedException ignored)
                {
                    Thread.currentThread().interrupt();
                }
            }
            respond(exchange, 200, STATUS_JSON);
        };

        ExecutorService pool = Executors.newFixedThreadPool(2);
        try
        {
            Future<Object> slow = pool.submit(() -> statusCall(LeagueShard.LA1).withHeader("X-Test-Platform", "LA1").build());
            Assertions.assertTrue(la1Entered.await(10, TimeUnit.SECONDS));

            // LA1 is still in flight; the same endpoint on LA2 must go through
            Future<Object> fast = pool.submit(() -> statusCall(LeagueShard.LA2).withHeader("X-Test-Platform", "LA2").build());
            Assertions.assertNotNull(fast.get(10, TimeUnit.SECONDS));

            release.countDown();
            Assertions.assertNotNull(slow.get(10, TimeUnit.SECONDS));
        } finally
        {
            release.countDown();
            pool.shutdownNow();
        }
    }
}
