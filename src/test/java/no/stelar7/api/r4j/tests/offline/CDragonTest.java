package no.stelar7.api.r4j.tests.offline;

import com.sun.net.httpserver.HttpServer;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.MemoryCacheProvider;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.impl.lol.raw.CDragonAPI;
import no.stelar7.api.r4j.pojo.lol.cdragon.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

/**
 * CDragonAPI against a local server that answers like the CommunityDragon CDN (trimmed to the fields that matter here)
 */
public class CDragonTest
{
    private static final String ANNIE =
            "{\"id\":1,\"name\":\"Annie\",\"alias\":\"Annie\",\"title\":\"the Dark Child\",\"shortBio\":\"Dangerous, yet disarmingly precocious\",\"isVisibleInClient\":true," +
            "\"tacticalInfo\":{\"style\":10,\"difficulty\":1,\"damageType\":\"kMagic\",\"attackType\":\"ranged\"}," +
            "\"playstyleInfo\":{\"damage\":3,\"durability\":1,\"crowdControl\":3,\"mobility\":1,\"utility\":2}," +
            "\"championTagInfo\":{\"championTagPrimary\":\"Mage\",\"championTagSecondary\":\"Support\"}," +
            "\"squarePortraitPath\":\"/lol-game-data/assets/v1/champion-icons/1.png\",\"roles\":[\"mage\",\"support\"]," +
            "\"skins\":[{\"id\":1000,\"isBase\":true,\"name\":\"Annie\",\"splashPath\":\"/lol-game-data/assets/annie_splash_centered_0.jpg\",\"rarity\":\"kNoRarity\",\"isLegacy\":false," +
            "\"chromaPath\":null,\"skinLines\":null,\"somethingNew\":{\"a\":1}}," +
            "{\"id\":1001,\"isBase\":false,\"name\":\"Goth Annie\",\"rarity\":\"kNoRarity\",\"isLegacy\":true}]," +
            "\"passive\":{\"name\":\"Pyromania\",\"abilityIconPath\":\"/lol-game-data/assets/Annie_Passive.png\",\"description\":\"After casting 4 spells\"}," +
            "\"spells\":[{\"spellKey\":\"q\",\"name\":\"Disintegrate\",\"cost\":\"@Cost@ Mana\",\"cooldown\":\"@Cooldown@\",\"range\":[625.0,625.0],\"costCoefficients\":[60.0,65.0]," +
            "\"coefficients\":{\"coefficient1\":0.8},\"effectAmounts\":{\"Effect1Amount\":[0.0,70.0]},\"ammo\":{\"ammoRechargeTime\":[0.0],\"maxAmmo\":[0]},\"maxLevel\":5}]," +
            "\"aFieldThatDoesNotExistYet\":[1,2,3]}";

    private static HttpServer   server;
    private static List<String> paths;
    private static List<String> apiKeysSeen;
    private static R4JContext   context;
    private static CDragonAPI   api;

    @BeforeAll
    public static void start() throws IOException
    {
        paths = new CopyOnWriteArrayList<>();
        apiKeysSeen = new CopyOnWriteArrayList<>();

        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            paths.add(exchange.getRequestURI().getPath());
            if (exchange.getRequestHeaders().containsKey("X-Riot-Token"))
            {
                apiKeysSeen.add(exchange.getRequestHeaders().getFirst("X-Riot-Token"));
            }

            boolean known = exchange.getRequestURI().getPath().endsWith("/champion/1/data") || exchange.getRequestURI().getPath().endsWith("/champion/annie/data");
            byte[]  bytes = (known ? ANNIE : "{\"statusCode\":404,\"error\":\"Not Found\"}").getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(known ? 200 : 404, bytes.length);
            try (OutputStream os = exchange.getResponseBody())
            {
                os.write(bytes);
            }
            exchange.close();
        });
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        // a context with real looking credentials, to check they are not sent
        context = new R4JContext(new APICredentials("RGAPI-must-not-leave")).setCacheProvider(new MemoryCacheProvider(CacheProvider.TTL_INFINITY));
        api = CDragonAPI.getInstance(context);
        api.setBaseUrl("http://127.0.0.1:" + server.getAddress().getPort());
    }

    @AfterAll
    public static void stop()
    {
        server.stop(0);
    }

    @Test
    public void championDataIsParsedAndUnknownFieldsAreIgnored()
    {
        CDragonChampion annie = api.getChampion(1);

        Assertions.assertEquals(1, annie.getId());
        Assertions.assertEquals("Annie", annie.getName());
        Assertions.assertEquals("the Dark Child", annie.getTitle());
        Assertions.assertTrue(annie.isVisibleInClient());
        Assertions.assertEquals(Arrays.asList("mage", "support"), annie.getRoles());
        Assertions.assertEquals("kMagic", annie.getTacticalInfo().getDamageType());
        Assertions.assertEquals(3, annie.getPlaystyleInfo().getCrowdControl());
        Assertions.assertEquals("Mage", annie.getChampionTagInfo().getChampionTagPrimary());
        Assertions.assertEquals("Pyromania", annie.getPassive().getName());

        Assertions.assertEquals(2, annie.getSkins().size());
        CDragonSkin goth = annie.getSkins().get(1);
        Assertions.assertEquals(1001, goth.getId());
        Assertions.assertEquals(1, goth.getNum());
        Assertions.assertTrue(goth.isLegacy());
        Assertions.assertFalse(goth.isBase());
        Assertions.assertTrue(annie.getSkins().get(0).isBase());

        CDragonSpell q = annie.getSpells().get(0);
        Assertions.assertEquals("q", q.getSpellKey());
        Assertions.assertEquals(5, q.getMaxLevel());
        Assertions.assertEquals(Arrays.asList(625.0, 625.0), q.getRange());
        Assertions.assertEquals(0.8, q.getCoefficients().get("coefficient1"));
    }

    @Test
    public void requestsGoToTheRightPathAndCarryNoApiKey()
    {
        paths.clear();
        Assertions.assertNotNull(api.getChampion("15.1.1", "Annie"));
        Assertions.assertEquals(Collections.singletonList("/15.1.1/champion/annie/data"), paths);

        // the same champion again comes from the cache
        Assertions.assertNotNull(api.getChampion("15.1.1", "annie"));
        Assertions.assertEquals(1, paths.size());

        // no patch means the latest one
        Assertions.assertNotNull(api.getChampion(null, "Annie"));
        Assertions.assertEquals("/latest/champion/annie/data", paths.get(1));

        // CommunityDragon is not Riot: our api key has no business there
        Assertions.assertTrue(apiKeysSeen.isEmpty(), "an api key was sent: " + apiKeysSeen);
    }

    @Test
    public void anUnknownChampionIsNull()
    {
        Assertions.assertNull(api.getChampion(null, "999999"));
    }

    @Test
    public void assetUrlsMatchTheDocumentedEndpoints()
    {
        CDragonAPI cdn = CDragonAPI.getInstance(new R4JContext());
        String     b   = "https://cdn.communitydragon.org/";

        Assertions.assertEquals(b + "latest/champion/generic/square", cdn.getGenericChampionSquare(null));
        Assertions.assertEquals(b + "latest/champion/1/square", cdn.getChampionSquare(null, "1"));
        Assertions.assertEquals(b + "15.1.1/champion/annie/square", cdn.getChampionSquare("15.1.1", "Annie"));
        Assertions.assertEquals(b + "latest/champion/1/splash-art", cdn.getSplashArt(null, "1"));
        Assertions.assertEquals(b + "latest/champion/1/splash-art/skin/1", cdn.getSplashArt(null, "1", 1));
        Assertions.assertEquals(b + "latest/champion/1/splash-art/centered", cdn.getCenteredSplashArt(null, "1"));
        Assertions.assertEquals(b + "latest/champion/1/splash-art/centered/skin/1", cdn.getCenteredSplashArt(null, "1", 1));
        Assertions.assertEquals(b + "latest/champion/1/tile", cdn.getTile(null, "1"));
        Assertions.assertEquals(b + "latest/champion/1/tile/skin/1", cdn.getTile(null, "1", 1));
        Assertions.assertEquals(b + "latest/champion/1/portrait", cdn.getPortrait(null, "1"));
        Assertions.assertEquals(b + "latest/champion/1/portrait/skin/1", cdn.getPortrait(null, "1", 1));
        Assertions.assertEquals(b + "latest/champion/1/ability-icon/passive", cdn.getAbilityIcon(null, "1", CDragonAPI.Ability.PASSIVE));
        Assertions.assertEquals(b + "latest/champion/1/ability-icon/r", cdn.getAbilityIcon(null, "1", CDragonAPI.Ability.R));
        Assertions.assertEquals(b + "latest/champion/1/champ-select/sounds/ban", cdn.getChampSelectSound(null, "1", CDragonAPI.ChampSelectSound.BAN));
        Assertions.assertEquals(b + "latest/champion/1/champ-select/sounds/choose", cdn.getChampSelectSound(null, "1", CDragonAPI.ChampSelectSound.CHOOSE));
        Assertions.assertEquals(b + "latest/champion/1/champ-select/sounds/sfx", cdn.getChampSelectSound(null, "1", CDragonAPI.ChampSelectSound.SFX));
        Assertions.assertEquals(b + "latest/ward/1", cdn.getWard(null, 1));
        Assertions.assertEquals(b + "latest/ward/1/shadow", cdn.getWardShadow(null, 1));
        Assertions.assertEquals(b + "latest/profile-icon/588", cdn.getProfileIcon(null, 588));
        Assertions.assertEquals(b + "latest/honor/generic", cdn.getGenericHonor(null));
        Assertions.assertEquals(b + "latest/honor/3", cdn.getHonor(null, 3));
        Assertions.assertEquals(b + "latest/honor/3/locked", cdn.getLockedHonor(null, 3));
        Assertions.assertEquals(b + "latest/honor/3/level/2", cdn.getHonor(null, 3, 2));
        Assertions.assertEquals(b + "latest/honor/emblem/generic", cdn.getGenericHonorEmblem(null));
        Assertions.assertEquals(b + "latest/honor/emblem/3", cdn.getHonorEmblem(null, 3));
        Assertions.assertEquals(b + "latest/honor/emblem/3/locked", cdn.getLockedHonorEmblem(null, 3));
        Assertions.assertEquals(b + "latest/honor/emblem/3/level/2", cdn.getHonorEmblem(null, 3, 2));
    }
}
