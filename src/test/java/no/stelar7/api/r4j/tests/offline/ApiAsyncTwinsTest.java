package no.stelar7.api.r4j.tests.offline;

import com.sun.net.httpserver.HttpServer;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
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
import no.stelar7.api.r4j.pojo.lol.tournament.*;
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
            if (path.contains("/slow/"))
            {
                // a Data Dragon that takes its time
                try
                {
                    Thread.sleep(700);
                } catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
            String body;
            if (path.contains("/unknown"))
            {
                body = null;
            } else if (path.contains("/number"))
            {
                // what registering a tournament or a provider answers
                body = "7";
            } else if (path.contains("/list"))
            {
                body = "[]";
            } else if (path.contains("/ddragon") || path.contains("/slow/"))
            {
                // a small Data Dragon
                if (path.endsWith("versions.json") || path.endsWith("languages.json"))
                {
                    body = "[\"15.1.1\",\"15.1.0\"]";
                } else if (path.contains("/realms/"))
                {
                    body = "{\"v\":\"15.1.1\",\"cdn\":\"https://example.invalid/cdn\",\"n\":{\"mastery\":\"7.23.1\",\"rune\":\"7.23.1\"}}";
                } else if (path.endsWith("runesReforged.json"))
                {
                    body = "[{\"id\":8100,\"key\":\"Domination\",\"name\":\"Domination\",\"slots\":[{\"runes\":[{\"id\":1,\"key\":\"Electrocute\",\"name\":\"Electrocute\"}]}]}]";
                } else if (path.endsWith("championFull.json"))
                {
                    body = "{\"data\":{\"Annie\":{\"id\":\"Annie\",\"key\":\"1\",\"name\":\"Annie\"}}}";
                } else
                {
                    body = "{\"data\":{}}";
                }
            } else if (path.contains("/unexpected"))
            {
                body = "{}";
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
                                      && !method.getName().equals("isStub")
                                      && !method.getName().equals("getBaseUrl");
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

        Assertions.assertTrue(missing.isEmpty(), "methods without an Async twin: " + missing);
        Assertions.assertTrue(twins > 100, "only " + twins + " twins found");
    }

    /**
     * Asking for something that does not exist used to end in a NullPointerException for these
     */
    @Test
    public void nothingFoundIsNeverANullPointerException()
    {
        R4JContext context = new R4JContext(APICredentials.builder().all("key").build())
                .setRequestUrl("http://127.0.0.1:" + server.getAddress().getPort() + "/unknown/{game}/{service}/{version}/{resource}");
        R4J nothing = R4J.withContext(context);

        // a match that does not exist
        Assertions.assertNull(nothing.getTFTAPI().getMatchAPI().getMatch(RegionShard.EUROPE, "EUW1_1"));
        Assertions.assertNull(nothing.getTFTAPI().getMatchAPI().getMetadata(RegionShard.EUROPE, "EUW1_1"));
        Assertions.assertNull(nothing.getLORAPI().getMatchAPI().getMatch(RuneterraShard.EUROPE, "1"));
        Assertions.assertNull(nothing.getLORAPI().getMatchAPI().getMetadata(RuneterraShard.EUROPE, "1"));

        // an apex league that does not exist
        Assertions.assertTrue(nothing.getLoLAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.CHALLENGER_I, 1).isEmpty());
        Assertions.assertTrue(nothing.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.MASTER_I, 1).isEmpty());

        // nobody is ranked "unranked"
        Assertions.assertTrue(nothing.getLoLAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.UNRANKED, 1).isEmpty());
        Assertions.assertTrue(nothing.getLoLAPI().getLeagueAPI().getLeagueExpByTierDivision(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.UNRANKED, 1).isEmpty());
        Assertions.assertTrue(nothing.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.UNRANKED, 1).isEmpty());
    }

    /**
     * A twin that needs the newest version first used to look it up with a blocking call, before returning its future
     */
    @Test
    public void aTwinThatMakesTwoCallsDoesNotWaitForTheFirst() throws Exception
    {
        R4J slow = R4J.withContext(new R4JContext(APICredentials.builder().lol("lol-key").build()));
        slow.getDDragonAPI().setBaseUrl("http://127.0.0.1:" + server.getAddress().getPort() + "/slow/");

        long                                                                                         start  = System.nanoTime();
        CompletableFuture<Map<Integer, no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion>> future = slow.getDDragonAPI().getChampionsAsync();
        long                                                                                         waited = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

        // the server takes 700 ms for the versions, and as long again for the champions
        Assertions.assertTrue(waited < 500, "getChampionsAsync returned after " + waited + " ms");
        Assertions.assertFalse(future.isDone());
        Assertions.assertEquals("Annie", future.get(20, TimeUnit.SECONDS).get(1).getName());
    }

    /**
     * A value of the type that is good enough to make a call with
     */
    private static Object sample(Class<?> type)
    {
        if (type.isEnum())
        {
            // the last constant: the first one is often UNKNOWN
            Object[] constants = type.getEnumConstants();
            return constants[constants.length - 1];
        }
        if (type == String.class)
        {
            return "sample";
        }
        if (type == int.class || type == Integer.class)
        {
            return 1;
        }
        if (type == long.class || type == Long.class)
        {
            return 1L;
        }
        if (type == boolean.class || type == Boolean.class)
        {
            return true;
        }
        if (type == Optional.class)
        {
            return Optional.empty();
        }
        if (List.class.isAssignableFrom(type) || type == Collection.class)
        {
            // the only list taken is one of champion ids
            return Arrays.asList(1, 2);
        }
        if (type == TournamentCodeParameters.class)
        {
            return new TournamentCodeParameters();
        }
        if (type == ProviderRegistrationParameters.class)
        {
            return new ProviderRegistrationParameters(LeagueShard.EUW1, "https://example.invalid/callback");
        }
        if (type == TournamentRegistrationParameters.class)
        {
            return new TournamentRegistrationParameters("sample", 1L);
        }

        // parameter objects (ie. for the tournament api); both methods get the same null
        return null;
    }

    /**
     * What a call did: its result, or the kind of exception it ended with
     */
    private static String outcome(Callable<Object> call)
    {
        try
        {
            return "result: " + call.call();
        } catch (Throwable t)
        {
            Throwable cause = t;
            while ((cause instanceof ExecutionException || cause instanceof CompletionException || cause instanceof InvocationTargetException) && cause.getCause() != null)
            {
                cause = cause.getCause();
            }
            return "exception: " + cause.getClass().getName();
        }
    }

    /**
     * Calls every twin and its blocking method with the same arguments, and expects the same outcome from both.
     * Done with a server that knows nothing (404), with servers that answer something most endpoints do not expect (an empty object, a number, an empty list),
     * and with a server that is a small Data Dragon.
     */
    @Test
    public void everyTwinBehavesLikeItsBlockingMethod() throws Exception
    {
        List<String> differences = new ArrayList<>();
        int          compared    = 0;

        for (String scenario : Arrays.asList("unknown", "unexpected", "number", "list", "ddragon"))
        {
            // a fresh instance per scenario, pointed at the part of the server that plays it
            R4JContext context = new R4JContext(APICredentials.builder().all("key").build())
                    .setRequestUrl("http://127.0.0.1:" + server.getAddress().getPort() + "/" + scenario + "/{game}/{service}/{version}/{resource}")
                    .setMaxSleep(0);
            R4J isolated = R4J.withContext(context);
            CDragonAPI.getInstance(context).setBaseUrl("http://127.0.0.1:" + server.getAddress().getPort() + "/" + scenario + "/");
            DDragonAPI.getInstance(context).setBaseUrl("http://127.0.0.1:" + server.getAddress().getPort() + "/" + scenario + "/");

            List<Object> apis = Arrays.asList(
                    isolated.getLoLAPI().getChallengeAPI(), isolated.getLoLAPI().getChampionAPI(), isolated.getLoLAPI().getClashAPI(), isolated.getLoLAPI().getLeagueAPI(),
                    isolated.getLoLAPI().getMasteryAPI(), isolated.getLoLAPI().getMatchAPI(), isolated.getLoLAPI().getSpectatorAPI(), isolated.getLoLAPI().getSummonerAPI(),
                    isolated.getLoLAPI().getTournamentAPI(false), isolated.getLoLAPI().getTournamentAPI(true),
                    isolated.getAccountAPI(), isolated.getStatusAPI(), isolated.getCDragonAPI(), isolated.getDDragonAPI(), isolated.getRiftboundContentAPI(),
                    isolated.getTFTAPI().getLeagueAPI(), isolated.getTFTAPI().getMatchAPI(), isolated.getTFTAPI().getSpectatorAPI(), isolated.getTFTAPI().getSummonerAPI(),
                    isolated.getVALAPI().getAccoladesAPI(), isolated.getVALAPI().getConsoleAPI(), isolated.getVALAPI().getContentAPI(), isolated.getVALAPI().getMatchAPI(), isolated.getVALAPI().getRankedAPI(),
                    isolated.getLORAPI().getMatchAPI(), isolated.getLORAPI().getRankedAPI());

            for (Object target : apis)
            {
                for (Method twin : target.getClass().getDeclaredMethods())
                {
                    if (!twin.getName().endsWith("Async") || !Modifier.isPublic(twin.getModifiers()))
                    {
                        continue;
                    }

                    String name     = twin.getName().substring(0, twin.getName().length() - "Async".length());
                    Method blocking = target.getClass().getDeclaredMethod(name, twin.getParameterTypes());

                    Object[] args = new Object[twin.getParameterCount()];
                    for (int i = 0; i < args.length; i++)
                    {
                        args[i] = sample(twin.getParameterTypes()[i]);
                    }

                    String expected = outcome(() -> blocking.invoke(target, args));
                    String actual   = outcome(() -> ((CompletableFuture<?>) twin.invoke(target, args)).get(30, TimeUnit.SECONDS));
                    compared++;

                    if (!expected.equals(actual))
                    {
                        differences.add(scenario + " " + target.getClass().getSimpleName() + "." + name + ": blocking gave [" + expected + "], the twin gave [" + actual + "]");
                    }
                }
            }
        }

        Assertions.assertTrue(differences.isEmpty(), differences.size() + " twins behave differently:\n" + String.join("\n", differences));
        Assertions.assertTrue(compared > 600, "only " + compared + " comparisons were made");
    }
}
