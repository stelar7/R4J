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
        Assertions.assertTrue(app instanceof BurstRateLimiter, "the app limiter should be built from the X-App-Rate-Limit header");
        Assertions.assertEquals(2, app.getLimits().size());

        // we made 1 call, but the server says 55 calls were made in the 120s window (another process, or a restart)
        long count120 = app.getCallCountInTime().entrySet().stream()
                           .filter(e -> e.getKey().getTimeframeInMS() == TimeUnit.SECONDS.toMillis(120))
                           .mapToLong(e -> e.getValue().get())
                           .findFirst().orElse(-1);
        Assertions.assertEquals(55, count120);

        RateLimiter method = limiters.get(URLEndpoint.V4_STATUS_LOL);
        Assertions.assertTrue(method instanceof BurstRateLimiter);
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
