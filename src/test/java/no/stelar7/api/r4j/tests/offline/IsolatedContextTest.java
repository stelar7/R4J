package no.stelar7.api.r4j.tests.offline;

import com.sun.net.httpserver.HttpServer;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.MemoryCacheProvider;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.SummonerAPI;
import no.stelar7.api.r4j.impl.shared.StatusAPI;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.val.match.VALMatch;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

/**
 * Two contexts must not share credentials, caches or ratelimiters.
 */
public class IsolatedContextTest
{
    private static final String STATUS_JSON = "{\"id\":\"EUW1\",\"name\":\"EU West\",\"locales\":[],\"maintenances\":[],\"incidents\":[]}";

    private static HttpServer   server;
    private static String       proxy;
    private static List<String> keysSeen;

    @BeforeAll
    public static void startServer() throws IOException
    {
        keysSeen = new CopyOnWriteArrayList<>();
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            keysSeen.add(exchange.getRequestHeaders().getFirst("X-Riot-Token"));
            exchange.getResponseHeaders().add("X-App-Rate-Limit", "20:1");
            exchange.getResponseHeaders().add("X-App-Rate-Limit-Count", "1:1");
            byte[] bytes = STATUS_JSON.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody())
            {
                os.write(bytes);
            }
            exchange.close();
        });
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        proxy = "http://127.0.0.1:" + server.getAddress().getPort() + "/{game}/{service}/{version}/{resource}";
    }

    @AfterAll
    public static void stopServer()
    {
        server.stop(0);
    }

    private PlatformData status(R4JContext context)
    {
        Map<String, Object> key = new LinkedHashMap<>();
        key.put("platform", LeagueShard.EUW1);

        return new DataCallBuilder(context).withProxy(proxy)
                                           .withEndpoint(URLEndpoint.V4_STATUS_LOL)
                                           .withPlatform(LeagueShard.EUW1)
                                           .buildCached(PlatformData.class, key);
    }

    @Test
    public void contextsDoNotShareAnything()
    {
        R4JContext a = new R4JContext(new APICredentials("key-a")).setCacheProvider(new MemoryCacheProvider(CacheProvider.TTL_INFINITY));
        R4JContext b = new R4JContext(new APICredentials("key-b")).setCacheProvider(new MemoryCacheProvider(CacheProvider.TTL_INFINITY));

        keysSeen.clear();
        Assertions.assertNotNull(status(a));
        Assertions.assertNotNull(status(b));

        // each call used the key of its own context, and b did not get the value a cached
        Assertions.assertEquals(Arrays.asList("key-a", "key-b"), keysSeen);

        // the second call of each context comes from its own cache
        Assertions.assertNotNull(status(a));
        Assertions.assertNotNull(status(b));
        Assertions.assertEquals(2, keysSeen.size());

        // each context has its own limiters, and the default context was not touched
        Assertions.assertEquals(1, a.getLimiters(ApiKeyType.LOL).size());
        Assertions.assertEquals(1, b.getLimiters(ApiKeyType.LOL).size());
        Assertions.assertNotSame(a.getLimiter(ApiKeyType.LOL, LeagueShard.EUW1, LeagueShard.EUW1), b.getLimiter(ApiKeyType.LOL, LeagueShard.EUW1, LeagueShard.EUW1));
        Assertions.assertFalse(R4JContext.getDefault().getLimiters(ApiKeyType.LOL).containsKey(LeagueShard.EUW1));
    }

    @Test
    public void isolatedR4JHasItsOwnApis()
    {
        R4J first  = R4J.withContext(new R4JContext(new APICredentials("key-a")));
        R4J second = R4J.isolated(new APICredentials("key-b"));

        Assertions.assertNotSame(first.getContext(), second.getContext());
        Assertions.assertNotSame(first.getLoLAPI().getSummonerAPI(), second.getLoLAPI().getSummonerAPI());
        Assertions.assertNotSame(first.getLoLAPI().getSummonerAPI(), SummonerAPI.getInstance());

        // asking the same instance twice gives the same api object
        Assertions.assertSame(first.getLoLAPI().getSummonerAPI(), first.getLoLAPI().getSummonerAPI());
        Assertions.assertNotSame(first.getLoLAPI().getTournamentAPI(true), first.getLoLAPI().getTournamentAPI(false));

        // the default context still hands out the shared singletons
        Assertions.assertSame(SummonerAPI.getInstance(), SummonerAPI.getInstance(R4JContext.getDefault()));
    }

    @Test
    public void returnedObjectsRememberTheirContext()
    {
        R4JContext isolated = new R4JContext(new APICredentials("key-a")).setCacheProvider(new MemoryCacheProvider(CacheProvider.TTL_INFINITY));

        Map<String, Object> key = new LinkedHashMap<>();
        key.put("platform", LeagueShard.EUW1);
        key.put("puuid", "abc");

        // fresh from the api
        Summoner fresh = new DataCallBuilder(isolated).withProxy(proxy)
                                                      .withEndpoint(URLEndpoint.V4_SUMMONER_BY_PUUID).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, "abc")
                                                      .withPlatform(LeagueShard.EUW1)
                                                      .buildCached(Summoner.class, key);
        Assertions.assertSame(isolated, fresh.getContext());

        // and from the cache
        int      callsBefore = keysSeen.size();
        Summoner cached      = new DataCallBuilder(isolated).withProxy(proxy)
                                                            .withEndpoint(URLEndpoint.V4_SUMMONER_BY_PUUID).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, "abc")
                                                            .withPlatform(LeagueShard.EUW1)
                                                            .buildCached(Summoner.class, key);
        Assertions.assertEquals(callsBefore, keysSeen.size());
        Assertions.assertSame(isolated, cached.getContext());

        // an object that was not fetched through a context uses the default one
        Assertions.assertSame(R4JContext.getDefault(), Utils.getGson().fromJson("{}", Summoner.class).getContext());
    }

    @Test
    public void nestedObjectsAreBoundToo()
    {
        R4JContext isolated = new R4JContext(new APICredentials("key-a"));

        // players are inside a match, inside a list
        VALMatch match = Utils.getGson().fromJson("{\"matchInfo\":{\"matchId\":\"1\"},\"players\":[{\"puuid\":\"a\"},{\"puuid\":\"b\"}]}", VALMatch.class);
        List<VALMatch> matches = isolated.bind(new ArrayList<>(Collections.singletonList(match)));

        Assertions.assertSame(isolated, matches.get(0).getMatchInfo().getContext());
        Assertions.assertEquals(2, match.getPlayers().size());
        match.getPlayers().forEach(player -> Assertions.assertSame(isolated, player.getContext()));

        // binding with the default context changes nothing
        VALMatch other = Utils.getGson().fromJson("{\"players\":[{\"puuid\":\"a\"}]}", VALMatch.class);
        R4JContext.getDefault().bind(other);
        Assertions.assertSame(R4JContext.getDefault(), other.getPlayers().get(0).getContext());
    }

    @Test
    public void aContextCanSendItsCallsSomewhereElse()
    {
        R4JContext elsewhere = new R4JContext(new APICredentials("key-a")).setRequestUrl(proxy);
        
        // an ordinary api class, which knows nothing about the url
        keysSeen.clear();
        PlatformData status = StatusAPI.getInstance(elsewhere).getShardStatus(LeagueShard.EUW1);
        
        Assertions.assertEquals("EUW1", status.getId());
        Assertions.assertEquals(Collections.singletonList("key-a"), keysSeen);
        
        // the default context still goes to Riot
        Assertions.assertTrue(R4JContext.getDefault().getRequestUrl().contains("api.riotgames.com"));
    }
}
