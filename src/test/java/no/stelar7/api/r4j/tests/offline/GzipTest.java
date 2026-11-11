package no.stelar7.api.r4j.tests.offline;

import com.sun.net.httpserver.HttpServer;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.exceptions.APIResponseException;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.zip.GZIPOutputStream;

/**
 * Responses are asked for compressed, and read whether the server compresses them or not.
 */
public class GzipTest
{
    // has a character outside of ascii, so a wrong charset after decompressing is noticed
    private static final String STATUS_JSON = "{\"id\":\"EUW1\",\"name\":\"EU Wést\",\"locales\":[],\"maintenances\":[],\"incidents\":[]}";

    private static HttpServer server;
    private static String     base;

    private static final List<String> acceptEncodings = new CopyOnWriteArrayList<>();

    @BeforeAll
    public static void startServer() throws IOException
    {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            String path = exchange.getRequestURI().getPath();
            acceptEncodings.add(String.valueOf(exchange.getRequestHeaders().getFirst("Accept-Encoding")));

            boolean notFound = path.startsWith("/missing");
            byte[]  bytes    = (notFound ? "{\"status\":{\"status_code\":404}}" : STATUS_JSON).getBytes(StandardCharsets.UTF_8);

            if (path.startsWith("/broken"))
            {
                // claims to be compressed, but is not
                exchange.getResponseHeaders().add("Content-Encoding", "gzip");
            } else if (!path.startsWith("/plain"))
            {
                ByteArrayOutputStream compressed = new ByteArrayOutputStream();
                try (GZIPOutputStream gzip = new GZIPOutputStream(compressed))
                {
                    gzip.write(bytes);
                }
                bytes = compressed.toByteArray();
                exchange.getResponseHeaders().add("Content-Encoding", "gzip");
            }

            exchange.sendResponseHeaders(notFound ? 404 : 200, bytes.length);
            try (OutputStream os = exchange.getResponseBody())
            {
                os.write(bytes);
            }
            exchange.close();
        });
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        base = "http://127.0.0.1:" + server.getAddress().getPort() + "/";
    }

    @AfterAll
    public static void stopServer()
    {
        server.stop(0);
    }

    private static DataCallBuilder statusCall(String prefix)
    {
        R4JContext context = new R4JContext(new APICredentials("gzip-test-key")).setMaxSleep(0);
        return new DataCallBuilder(context).withProxy(base + prefix + "/{game}/{service}/{version}/{resource}").withEndpoint(URLEndpoint.V4_STATUS_LOL).withPlatform(LeagueShard.EUW1);
    }

    @Test
    public void compressedResponsesAreReadByBothTransports() throws Exception
    {
        acceptEncodings.clear();

        PlatformData blocking = statusCall("gzip").buildAs(PlatformData.class);
        Assertions.assertEquals("EU Wést", blocking.getName());

        PlatformData nonBlocking = statusCall("gzip").buildAsAsync(PlatformData.class).get(20, TimeUnit.SECONDS);
        Assertions.assertEquals(blocking, nonBlocking);

        // both asked for it
        Assertions.assertEquals(Arrays.asList("gzip", "gzip"), acceptEncodings);
    }

    @Test
    public void uncompressedResponsesAreStillRead() throws Exception
    {
        Assertions.assertEquals("EU Wést", statusCall("plain").buildAs(PlatformData.class).getName());
        Assertions.assertEquals("EU Wést", statusCall("plain").buildAsAsync(PlatformData.class).get(20, TimeUnit.SECONDS).getName());
    }

    @Test
    public void aCompressedNotFoundIsStillNotFound() throws Exception
    {
        Assertions.assertNull(statusCall("missing").buildAs(PlatformData.class));
        Assertions.assertNull(statusCall("missing").buildAsAsync(PlatformData.class).get(20, TimeUnit.SECONDS));
    }

    @Test
    public void aResponseThatCanNotBeDecompressedIsAFailedCall()
    {
        Assertions.assertThrows(APIResponseException.class, () -> statusCall("broken").buildAs(PlatformData.class));

        ExecutionException failure = Assertions.assertThrows(ExecutionException.class, () -> statusCall("broken").buildAsAsync(PlatformData.class).get(20, TimeUnit.SECONDS));
        Assertions.assertTrue(failure.getCause() instanceof APIResponseException, String.valueOf(failure.getCause()));
    }
}
