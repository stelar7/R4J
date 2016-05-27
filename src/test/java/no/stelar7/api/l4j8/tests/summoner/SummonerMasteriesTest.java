package no.stelar7.api.l4j8.tests.summoner;

import java.util.function.*;

import org.junit.*;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.masteries.*;
import no.stelar7.api.l4j8.tests.*;

public class SummonerMasteriesTest
{
    private final Consumer<MasteryPage> doAssertions = (final MasteryPage page) -> {

        Assert.assertNotNull("Mastery Page does not have an id", page.getId());
        // Assert.assertNotNull("Mastery Page does not have a name",
        // page.getName()); Names can be empty, so ignore this...
        Assert.assertNotNull("Mastery Page does not contain any masteries", page.getMasteries());
        Assert.assertNotNull("Unable to determine current Mastery page", page.isCurrent());

        page.getMasteries().forEach((final Mastery mastery) -> {
            Assert.assertNotNull("Mastery does not have an id", mastery.getId());
            Assert.assertNotNull("Mastery does not have a rank", mastery.getRank());

            Assert.assertNotEquals("Mastery does not have a valid id", mastery.getId(), (Integer) 0);
            Assert.assertNotEquals("Mastery does not have a valid rank", mastery.getRank(), (Integer) 0);
        });

    };

    @Test
    public void doTest()
    {
        L4J8 api = new L4J8(SecretFile.CREDS);

        // Generate list of summoner IDs
        String[] values = { "stelar7", "henriko950", "vibbsen" };

        api.getSummoner(Server.EUW, values).forEach((a, b) -> b.get().getMasteryPages().get().forEach(doAssertions));
    }

}
