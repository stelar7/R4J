package no.stelar7.api.r4j.tests.lol.staticdata;

import no.stelar7.api.r4j.impl.lol.raw.CDragonAPI;
import no.stelar7.api.r4j.pojo.lol.cdragon.CDragonChampion;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * Checks CDragonAPI against the real CommunityDragon CDN.
 * Needs the network, but no api key. Runs with -Plive.
 */
public class CDragonLiveTest
{
    private final CDragonAPI api = CDragonAPI.getInstance();

    private void assertExists(String what, String url, String contentType) throws IOException
    {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestProperty("User-Agent", "R4J");
        con.setConnectTimeout(10_000);
        con.setReadTimeout(30_000);

        Assertions.assertEquals(200, con.getResponseCode(), what + ": " + url);
        Assertions.assertTrue(String.valueOf(con.getContentType()).startsWith(contentType), what + ": " + url + " is " + con.getContentType());
        con.getInputStream().close();
    }

    @Test
    public void championDataIsReadFromTheCdn()
    {
        CDragonChampion annie = api.getChampion(1);

        Assertions.assertEquals(1, annie.getId());
        Assertions.assertEquals("Annie", annie.getName());
        Assertions.assertFalse(annie.getSkins().isEmpty());
        Assertions.assertTrue(annie.getSkins().get(0).isBase());
        Assertions.assertEquals(4, annie.getSpells().size());
        Assertions.assertNotNull(annie.getPassive().getName());
        Assertions.assertFalse(annie.getRoles().isEmpty());

        // by key, and for a specific patch
        Assertions.assertEquals(1, api.getChampion(null, "annie").getId());
        Assertions.assertEquals("Annie", api.getChampion("15.1.1", "1").getName());

        // a champion that does not exist
        Assertions.assertNull(api.getChampion(null, "999999"));
    }

    @Test
    public void assetUrlsPointAtAssets() throws IOException
    {
        Map<String, String> images = new LinkedHashMap<>();
        images.put("generic square", api.getGenericChampionSquare(null));
        images.put("square by id", api.getChampionSquare(null, "1"));
        images.put("square by key", api.getChampionSquare(null, "Annie"));
        images.put("square on a patch", api.getChampionSquare("15.1.1", "1"));
        images.put("splash art", api.getSplashArt(null, "1"));
        images.put("splash art of a skin", api.getSplashArt(null, "1", 1));
        images.put("centered splash art", api.getCenteredSplashArt(null, "1"));
        images.put("centered splash art of a skin", api.getCenteredSplashArt(null, "1", 1));
        images.put("tile", api.getTile(null, "1"));
        images.put("tile of a skin", api.getTile(null, "1", 1));
        images.put("portrait", api.getPortrait(null, "1"));
        images.put("portrait of a skin", api.getPortrait(null, "1", 1));
        images.put("ward", api.getWard(null, 1));
        images.put("ward shadow", api.getWardShadow(null, 1));
        images.put("profile icon", api.getProfileIcon(null, 588));
        for (CDragonAPI.Ability ability : CDragonAPI.Ability.values())
        {
            images.put("ability icon " + ability, api.getAbilityIcon(null, "1", ability));
        }

        for (Map.Entry<String, String> image : images.entrySet())
        {
            assertExists(image.getKey(), image.getValue(), "image/");
        }

        for (CDragonAPI.ChampSelectSound sound : CDragonAPI.ChampSelectSound.values())
        {
            assertExists("sound " + sound, api.getChampSelectSound(null, "1", sound), "audio/");
        }
    }
}
