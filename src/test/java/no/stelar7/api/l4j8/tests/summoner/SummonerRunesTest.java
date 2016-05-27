package no.stelar7.api.l4j8.tests.summoner;

import java.util.function.*;

import org.junit.*;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.runes.*;
import no.stelar7.api.l4j8.tests.*;

public class SummonerRunesTest
{

    private final Consumer<RunePage> doAssertions = (final RunePage page) -> {

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

    };

    @Test
    public void doTest()
    {
        L4J8 api = new L4J8(SecretFile.CREDS);

        // Generate list of summoner IDs
        String[] values = { "stelar7", "henriko950", "vibbsen" };

        api.getSummoner(Server.EUW, values).forEach((a, b) -> b.get().getRunePages().get().forEach(doAssertions));
    }

}
