package no.stelar7.api.r4j.tests.offline;

import com.sun.net.httpserver.*;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.MemoryCacheProvider;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.exceptions.*;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * The non-blocking way of making a call (buildAsync and friends), against a local http server.
 */
public class AsyncPipelineTest
{
    private static final String STATUS_JSON = "{\"id\":\"EUW1\",\"name\":\"EU West\",\"locales\":[],\"maintenances\":[],\"incidents\":[]}";

    private static HttpServer server;
    private static String     proxy;

    private static volatile HttpHandler handler;

    @BeforeAll
    public static void startServer() throws IOException
    {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> handler.handle(exchange));
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        proxy = "http://127.0.0.1:" + server.getAddress().getPort() + "/{game}/{service}/{version}/{resource}";
    }

    @AfterAll
    public static void stopServer()
    {
        server.stop(0);
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

    /**
     * Every test gets its own context, so limiters and settings do not leak between them
     */
    private R4JContext context()
    {
        return new R4JContext(new APICredentials("async-test-key"));
    }

    private DataCallBuilder statusCall(R4JContext context)
    {
        return new DataCallBuilder(context).withProxy(proxy).withEndpoint(URLEndpoint.V4_STATUS_LOL).withPlatform(LeagueShard.EUW1);
    }

    private static Throwable failureOf(CompletableFuture<?> future) throws Exception
    {
        try
        {
            future.get(20, TimeUnit.SECONDS);
            return null;
        } catch (ExecutionException e)
        {
            return e.getCause();
        }
    }

    @Test
    public void theCallerIsNotBlockedAndGetsTheResult() throws Exception
    {
        handler = exchange -> {
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException ignored)
            {
                Thread.currentThread().interrupt();
            }
            respond(exchange, 200, STATUS_JSON);
        };

        long                            before = System.currentTimeMillis();
        CompletableFuture<PlatformData> future = statusCall(context()).buildAsAsync(PlatformData.class);
        long                            took   = System.currentTimeMillis() - before;

        // the server takes half a second; getting the future does not
        Assertions.assertTrue(took < 400, "buildAsAsync took " + took + " ms to return");
        Assertions.assertFalse(future.isDone());
        Assertions.assertEquals("EUW1", future.get(20, TimeUnit.SECONDS).getId());
    }

    @Test
    public void outcomesMatchTheBlockingCall() throws Exception
    {
        // 404: a Pair from buildAsync, null from the typed variant
        handler = exchange -> respond(exchange, 404, "{\"status\":{\"status_code\":404}}");
        Assertions.assertTrue(statusCall(context()).buildAsync().get(20, TimeUnit.SECONDS) instanceof Pair);
        Assertions.assertNull(statusCall(context()).buildAsAsync(PlatformData.class).get(20, TimeUnit.SECONDS));

        // errors arrive as the exception the blocking call throws
        handler = exchange -> respond(exchange, 403, "{}");
        Throwable forbidden = failureOf(statusCall(context()).buildAsync());
        Assertions.assertTrue(forbidden instanceof APIResponseException, String.valueOf(forbidden));
        Assertions.assertEquals(APIHTTPErrorReason.ERROR_403, ((APIResponseException) forbidden).getReason());

        handler = exchange -> respond(exchange, 405, "{}");
        Assertions.assertEquals(APIHTTPErrorReason.ERROR_405, ((APIResponseException) failureOf(statusCall(context()).buildAsync())).getReason());

        // no credentials
        Throwable noCredentials = failureOf(new DataCallBuilder(new R4JContext()).withProxy(proxy).withEndpoint(URLEndpoint.V4_STATUS_LOL).withPlatform(LeagueShard.EUW1).buildAsync());
        Assertions.assertTrue(noCredentials instanceof APIUnsupportedActionException, String.valueOf(noCredentials));

        // a server that can not be reached
        Throwable unreachable = failureOf(new DataCallBuilder(context()).withProxy("http://127.0.0.1:1/{game}/{service}/{version}/{resource}")
                                                                        .withEndpoint(URLEndpoint.V4_STATUS_LOL).withPlatform(LeagueShard.EUW1).buildAsync());
        Assertions.assertTrue(unreachable instanceof APIResponseException, String.valueOf(unreachable));
        Assertions.assertEquals(APIHTTPErrorReason.ERROR_599, ((APIResponseException) unreachable).getReason());
        Assertions.assertNotNull(unreachable.getCause());
    }

    @Test
    public void serverErrorsAreRetriedAndGiveUpAfterMaxSleep() throws Exception
    {
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

        Assertions.assertNotNull(statusCall(context()).buildAsAsync(PlatformData.class).get(20, TimeUnit.SECONDS));
        Assertions.assertEquals(3, calls.get());

        calls.set(0);
        handler = exchange -> {
            calls.incrementAndGet();
            respond(exchange, 500, "{}");
        };

        Throwable gaveUp = failureOf(statusCall(context()).withMaxSleep(600).buildAsync());
        Assertions.assertTrue(gaveUp instanceof APINoValidResponseException, String.valueOf(gaveUp));
        Assertions.assertEquals(3, calls.get());
    }

    @Test
    public void headersAndBodyAreSentLikeTheBlockingCall() throws Exception
    {
        String                  body     = "{\"name\":\"Ærlig øl ✓\"}";
        AtomicReference<String> received = new AtomicReference<>();
        AtomicReference<String> method   = new AtomicReference<>();
        AtomicReference<String> token    = new AtomicReference<>();
        AtomicReference<String> override = new AtomicReference<>();
        AtomicReference<String> query    = new AtomicReference<>();

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
            method.set(exchange.getRequestMethod());
            token.set(exchange.getRequestHeaders().getFirst("X-Riot-Token"));
            override.set(exchange.getRequestHeaders().getFirst("X-HTTP-Method-Override"));
            query.set(exchange.getRequestURI().getRawQuery());
            respond(exchange, 200, STATUS_JSON);
        };

        statusCall(context()).withRequestMethod("PATCH").withPostData(body).withQueryParameter("name", "a b").buildAsync().get(20, TimeUnit.SECONDS);

        Assertions.assertEquals(body, received.get());
        Assertions.assertEquals("POST", method.get());
        Assertions.assertEquals("PATCH", override.get());
        Assertions.assertEquals("async-test-key", token.get());
        Assertions.assertEquals("name=a%20b", query.get());
    }

    @Test
    public void theRatelimitIsObeyedWithoutBlockingThreads() throws Exception
    {
        // the server allows 3 calls per second, and counts like the api
        AtomicInteger overTheLimit = new AtomicInteger();
        AtomicInteger total        = new AtomicInteger();
        long[]        window       = {0, 0};

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
            total.incrementAndGet();

            exchange.getResponseHeaders().add("X-App-Rate-Limit", "3:1");
            exchange.getResponseHeaders().add("X-App-Rate-Limit-Count", count + ":1");
            respond(exchange, 200, STATUS_JSON);
        };

        R4JContext                            context = context();
        List<CompletableFuture<PlatformData>> calls   = new ArrayList<>();
        int                                   threads = Thread.activeCount();
        for (int i = 0; i < 8; i++)
        {
            calls.add(statusCall(context).buildAsAsync(PlatformData.class));
        }

        // eight calls are waiting for their turn, but not eight threads
        Thread.sleep(300);
        Assertions.assertTrue(Thread.activeCount() - threads < 6, "threads before: " + threads + ", now: " + Thread.activeCount());

        for (CompletableFuture<PlatformData> call : calls)
        {
            Assertions.assertNotNull(call.get(30, TimeUnit.SECONDS));
        }

        Assertions.assertEquals(8, total.get());
        Assertions.assertEquals(0, overTheLimit.get());
        Assertions.assertEquals(1, context.getLimiters(ApiKeyType.LOL).size());
    }

    @Test
    public void theParallelCallLimitIsObeyed() throws Exception
    {
        AtomicInteger inFlight     = new AtomicInteger();
        AtomicInteger mostInFlight = new AtomicInteger();
        handler = exchange -> {
            mostInFlight.accumulateAndGet(inFlight.incrementAndGet(), Math::max);
            try
            {
                Thread.sleep(150);
            } catch (InterruptedException ignored)
            {
                Thread.currentThread().interrupt();
            }
            inFlight.decrementAndGet();
            respond(exchange, 200, STATUS_JSON);
        };

        R4JContext                      context = context().setNumberOfCallsAllowedInParallel(2);
        List<CompletableFuture<Object>> calls   = new ArrayList<>();
        for (int i = 0; i < 6; i++)
        {
            calls.add(statusCall(context).buildAsync());
        }

        for (CompletableFuture<Object> call : calls)
        {
            Assertions.assertNotNull(call.get(30, TimeUnit.SECONDS));
        }

        // the first call goes alone (the limits are not known yet), after that never more than two at a time
        Assertions.assertTrue(mostInFlight.get() <= 2, "calls in flight: " + mostInFlight.get());
    }

    @Test
    public void cachedCallsDoNotReachTheServer() throws Exception
    {
        AtomicInteger calls = new AtomicInteger();
        handler = exchange -> {
            calls.incrementAndGet();
            respond(exchange, 200, STATUS_JSON);
        };

        R4JContext          context = context().setCacheProvider(new MemoryCacheProvider(CacheProvider.TTL_INFINITY));
        Map<String, Object> key     = new LinkedHashMap<>();
        key.put("platform", LeagueShard.EUW1);

        PlatformData first  = statusCall(context).buildCachedAsync(PlatformData.class, key).get(20, TimeUnit.SECONDS);
        PlatformData second = statusCall(context).buildCachedAsync(PlatformData.class, key).get(20, TimeUnit.SECONDS);

        Assertions.assertEquals("EUW1", first.getId());
        Assertions.assertEquals(first, second);
        Assertions.assertEquals(1, calls.get());

        // and the blocking call sees what the non-blocking one cached
        Assertions.assertNotNull(statusCall(context).buildCached(PlatformData.class, key));
        Assertions.assertEquals(1, calls.get());
    }

    @Test
    public void aCancelledCallStopsRetrying() throws Exception
    {
        AtomicInteger calls = new AtomicInteger();
        handler = exchange -> {
            calls.incrementAndGet();
            respond(exchange, 503, "{}");
        };

        CompletableFuture<Object> future = statusCall(context()).withMaxSleep(60_000).buildAsync();
        Thread.sleep(300);
        future.cancel(true);

        int after = calls.get();
        Thread.sleep(1500);
        Assertions.assertEquals(after, calls.get());
    }

    @Test
    public void blockingApiCallsCanBeRunInTheBackground() throws Exception
    {
        handler = exchange -> respond(exchange, 200, STATUS_JSON);

        R4JContext context = context();
        R4J        api     = R4J.withContext(context);

        String thread = Thread.currentThread().getName();
        String ranOn  = api.async(() -> {
            statusCall(context).build();
            return Thread.currentThread().getName();
        }).get(20, TimeUnit.SECONDS);

        Assertions.assertNotEquals(thread, ranOn);
    }
}
