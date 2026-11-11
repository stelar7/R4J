package no.stelar7.api.r4j.tests.offline;

import com.sun.net.httpserver.HttpServer;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.exceptions.APIUnsupportedActionException;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.*;
import no.stelar7.api.r4j.impl.riftbound.RiftboundContentAPI;
import no.stelar7.api.r4j.impl.shared.*;
import no.stelar7.api.r4j.impl.tft.*;
import no.stelar7.api.r4j.impl.val.*;
import no.stelar7.api.r4j.pojo.lol.champion.ChampionRotationInfo;
import no.stelar7.api.r4j.pojo.lol.clash.ClashPlayer;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

/**
 * The ...Async methods of the api classes, against a local server standing in for the Riot api.
 */
public class ApiAsyncTwinsTest
{
    private static HttpServer server;
    private static R4J        api;

    @BeforeAll
    public static void start() throws IOException
    {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            String path = exchange.getRequestURI().getPath();
            String body;
            if (path.contains("/unknown"))
            {
                body = null;
            } else if (path.contains("/clash/v1/players"))
            {
                body = "[{\"puuid\":\"abc\",\"teamId\":\"team\"}]";
            } else if (path.contains("champion-rotations"))
            {
                body = "{\"freeChampionIds\":[1,2],\"freeChampionIdsForNewPlayers\":[3],\"maxNewPlayerLevel\":10}";
            } else if (path.contains("/summoner/v4/"))
            {
                body = "{\"puuid\":\"abc\",\"profileIconId\":588,\"summonerLevel\":30}";
            } else
            {
                body = "{\"id\":\"EUW1\",\"name\":\"EU West\",\"locales\":[],\"maintenances\":[],\"incidents\":[]}";
            }

            byte[] bytes = (body == null ? "{\"status\":{\"status_code\":404}}" : body).getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(body == null ? 404 : 200, bytes.length);
            try (OutputStream os = exchange.getResponseBody())
            {
                os.write(bytes);
            }
            exchange.close();
        });
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        // an isolated instance that talks to the local server instead of Riot, and has a lol key only
        R4JContext context = new R4JContext(APICredentials.builder().lol("lol-key").build())
                .setRequestUrl("http://127.0.0.1:" + server.getAddress().getPort() + "/{game}/{service}/{version}/{resource}");
        api = R4J.withContext(context);
    }

    @AfterAll
    public static void stop()
    {
        server.stop(0);
    }

    @Test
    public void twinsGiveTheSameAnswerAsTheBlockingMethods() throws Exception
    {
        ChampionRotationInfo rotation = api.getLoLAPI().getChampionAPI().getFreeToPlayRotation(LeagueShard.EUW1);
        Assertions.assertEquals(Arrays.asList(1, 2), rotation.getFreeChampionIds());
        Assertions.assertEquals(rotation, api.getLoLAPI().getChampionAPI().getFreeToPlayRotationAsync(LeagueShard.EUW1).get(20, TimeUnit.SECONDS));

        PlatformData status = api.getStatusAPI().getShardStatus(LeagueShard.EUW1);
        Assertions.assertEquals("EUW1", status.getId());
        Assertions.assertEquals(status, api.getStatusAPI().getShardStatusAsync(LeagueShard.EUW1).get(20, TimeUnit.SECONDS));

        // a method returning a list
        List<ClashPlayer> players = api.getLoLAPI().getClashAPI().getPlayerInfoByPuuid(LeagueShard.EUW1, "abc");
        Assertions.assertEquals(1, players.size());
        Assertions.assertEquals(players, api.getLoLAPI().getClashAPI().getPlayerInfoByPuuidAsync(LeagueShard.EUW1, "abc").get(20, TimeUnit.SECONDS));

        // a method that goes through a builder, whose twin runs it in the background
        Summoner summoner = api.getLoLAPI().getSummonerAPI().getSummonerByPUUID(LeagueShard.EUW1, "abc");
        Assertions.assertEquals("abc", summoner.getPUUID());
        Summoner async = api.getLoLAPI().getSummonerAPI().getSummonerByPUUIDAsync(LeagueShard.EUW1, "abc").get(20, TimeUnit.SECONDS);
        Assertions.assertEquals(summoner, async);

        // what comes back belongs to the instance that fetched it
        Assertions.assertSame(api.getContext(), async.getContext());
    }

    @Test
    public void nothingFoundIsTheSameAsForTheBlockingMethods() throws Exception
    {
        // a list is empty, an object is null
        Assertions.assertTrue(api.getLoLAPI().getClashAPI().getPlayerInfoByPuuid(LeagueShard.EUW1, "unknown").isEmpty());
        Assertions.assertTrue(api.getLoLAPI().getClashAPI().getPlayerInfoByPuuidAsync(LeagueShard.EUW1, "unknown").get(20, TimeUnit.SECONDS).isEmpty());

        Assertions.assertNull(api.getLoLAPI().getClashAPI().getTeam(LeagueShard.EUW1, "unknown"));
        Assertions.assertNull(api.getLoLAPI().getClashAPI().getTeamAsync(LeagueShard.EUW1, "unknown").get(20, TimeUnit.SECONDS));
    }

    @Test
    public void whatTheBlockingMethodThrowsArrivesThroughTheFuture()
    {
        // this instance has no tft key
        Assertions.assertThrows(APIUnsupportedActionException.class, () -> api.getStatusAPI().getTFTShardStatus(LeagueShard.EUW1));

        CompletableFuture<PlatformData> future = api.getStatusAPI().getTFTShardStatusAsync(LeagueShard.EUW1);
        ExecutionException failure = Assertions.assertThrows(ExecutionException.class, () -> future.get(20, TimeUnit.SECONDS));
        Assertions.assertTrue(failure.getCause() instanceof APIUnsupportedActionException, String.valueOf(failure.getCause()));
    }

    /**
     * Every method of the api classes that makes a call has a twin, so the two sets can not drift apart unnoticed
     */
    @Test
    public void everyApiMethodHasATwin()
    {
        List<Class<?>> classes = Arrays.asList(
                ChallengeAPI.class, ChampionAPI.class, ClashAPI.class, DDragonAPI.class, LeagueAPI.class, MasteryAPI.class, MatchV5API.class,
                SpectatorAPI.class, SummonerAPI.class, TournamentAPI.class, AccountAPI.class, StatusAPI.class,
                TFTLeagueAPI.class, TFTMatchAPI.class, TFTSpectatorAPI.class, TFTSummonerAPI.class,
                VALAccoladesAPI.class, VALConsoleAPI.class, VALContentAPI.class, VALMatchAPI.class, VALRankedAPI.class,
                no.stelar7.api.r4j.impl.lor.LORMatchAPI.class, no.stelar7.api.r4j.impl.lor.LORRankedAPI.class, RiftboundContentAPI.class);

        List<String> missing = new ArrayList<>();
        int          twins   = 0;
        for (Class<?> type : classes)
        {
            for (Method method : type.getDeclaredMethods())
            {
                // lazy lists and iterators only make their calls when they are used, so they have no twin
                Class<?> returns = method.getReturnType();
                boolean  lazy    = LazyList.class.isAssignableFrom(returns) || (Iterable.class.isAssignableFrom(returns) && !Collection.class.isAssignableFrom(returns));
                
                boolean callsTheApi = Modifier.isPublic(method.getModifiers())
                                      && !Modifier.isStatic(method.getModifiers())
                                      && !method.isSynthetic()
                                      && !method.isAnnotationPresent(Deprecated.class)
                                      && !method.getName().endsWith("Async")
                                      && returns != void.class
                                      && !lazy
                                      // only reads a setting
                                      && !method.getName().equals("isStub");
                if (!callsTheApi)
                {
                    continue;
                }

                try
                {
                    Method twin = type.getDeclaredMethod(method.getName() + "Async", method.getParameterTypes());
                    Assertions.assertEquals(CompletableFuture.class, twin.getReturnType(), type.getSimpleName() + "." + twin.getName());
                    twins++;
                } catch (NoSuchMethodException e)
                {
                    missing.add(type.getSimpleName() + "." + method.getName());
                }
            }
        }

        Assertions.assertTrue(missing.isEmpty(), "methods without an Async twin (run the generator again): " + missing);
        Assertions.assertTrue(twins > 100, "only " + twins + " twins found");
    }
}
