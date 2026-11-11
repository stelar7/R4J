package no.stelar7.api.r4j.tests.lol.staticdata;

import no.stelar7.api.r4j.basic.constants.types.lol.MapType;
import no.stelar7.api.r4j.impl.lol.raw.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * Requests every kind of url ImageAPI builds, to make sure they point at something.
 * Needs the network (Data Dragon), but no api key. Runs with -Plive.
 */
public class ImageUrlTest
{
    private final ImageAPI images = ImageAPI.getInstance();

    private void assertIsAnImage(String what, String url) throws IOException
    {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(10_000);
        con.setReadTimeout(20_000);

        Assertions.assertTrue(url.startsWith("https://"), what + " is not https: " + url);
        Assertions.assertEquals(200, con.getResponseCode(), what + ": " + url);
        Assertions.assertTrue(String.valueOf(con.getContentType()).startsWith("image/"), what + ": " + url + " is " + con.getContentType());
        con.getInputStream().close();
    }

    @Test
    public void everyKindOfUrlPointsAtAnImage() throws IOException
    {
        StaticChampion aatrox = DDragonAPI.getInstance().getChampion(266);
        Assertions.assertEquals("Aatrox", aatrox.getKey());

        Map<String, String> urls = new LinkedHashMap<>();
        urls.put("profile icon", images.getProfileIcon("588", null));
        urls.put("splash art", images.getSplashArt("Aatrox", 0));
        urls.put("loading screen art", images.getLoadingScreenArt("Aatrox", 0));
        urls.put("loading screen art of a skin", images.getLoadingScreenArt(aatrox.getSkins().get(1)));
        urls.put("square", images.getSquare("Aatrox", null));
        urls.put("square of a champion", images.getSquare(aatrox, null));
        urls.put("passive", images.getPassive(aatrox.getPassive(), null));
        urls.put("ability", images.getAbility(aatrox.getSpells().get(0), null));
        urls.put("summoner spell", images.getSummonerSpell("SummonerFlash", null));
        urls.put("item", images.getItem("1001", null));
        urls.put("map", images.getMap(MapType.SUMMONERS_RIFT, null));
        urls.put("sprite sheet", images.getSpriteSheet("champion0.png", null));
        // the old runes and masteries only exist for the last patch that had them
        urls.put("rune", images.getRune("8001", null));
        urls.put("mastery", images.getMastery("6111", null));

        for (Map.Entry<String, String> url : urls.entrySet())
        {
            assertIsAnImage(url.getKey(), url.getValue());
        }
    }
}
