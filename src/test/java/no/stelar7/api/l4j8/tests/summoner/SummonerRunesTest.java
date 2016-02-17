package no.stelar7.api.l4j8.tests.summoner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.stelar7.api.l4j8.basic.constants.api.Server;
import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePage;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePages;
import no.stelar7.api.l4j8.pojo.summoner.runes.RuneSlot;
import no.stelar7.api.l4j8.tests.TestBase;

public class SummonerRunesTest extends TestBase
{

    private final BiConsumer<String, RunePages> doAssertions = (final String key, final RunePages value) -> {

        value.getPages().forEach((final RunePage page) -> {
            Assert.assertNotNull("Rune Page does not have an id", page.getId());
            Assert.assertNotNull("Rune Page does not have a name", page.getName());
            Assert.assertNotNull("Rune Page does not contain any slots", page.getSlots());
            Assert.assertNotNull("Unable to determine current Rune page", page.isCurrent());

            page.getSlots().forEach((final RuneSlot slot) -> {
                Assert.assertNotNull("Rune slot does not have a slot id", slot.getRuneSlotId());
                Assert.assertNotNull("Rune slot does not have a rune id", slot.getRuneId());

                Assert.assertNotEquals("Rune slot does not have a valid id", slot.getRuneSlotId(), (Integer) 0);
                Assert.assertNotEquals("Rune slot does not have a valid rune id", slot.getRuneId(), (Integer) 0);
            });
        });

        Assert.assertTrue("There is not exactly ONE \"current\" page", value.getPages().stream().filter((final RunePage page) -> page.isCurrent()).count() == 1);
    };

    @Test
    public void doTest()
    {
        // Generate list of summoner IDs
        final List<String> keys = Arrays.asList("19613950", "22291359", "33540589");

        // Add them as a parameter to the URL
        keys.forEach((final String k) -> TestBase.builder.withURLData("{summonerId}", k));

        // Get the response
        final Map<String, RunePages> dataCall = (Map<String, RunePages>) TestBase.builder.build();

        // Make sure all the data is returned as expected
        dataCall.forEach(this.doAssertions);
    }

    @Before
    public void initBeforeTest()
    {
        TestBase.builder.withServer(Server.EUW);
        TestBase.builder.withEndpoint(URLEndpoint.SUMMONER_RUNES_BY_ID);
    }
}
