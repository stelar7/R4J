package no.stelar7.api.l4j8.tests.summoner;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.runes.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.function.Consumer;
import java.util.logging.Logger;

public class SummonerRunesTest
{
    
    public static final Logger LOGGER = Logger.getGlobal();
    
    private final Consumer<RunePages> doAssertions = (final RunePages pages) ->
    {
        pages.getPages().forEach(page ->
                                 {
            
                                     Assert.assertNotNull("StaticRune Page does not have an id", page.getId());
                                     Assert.assertNotNull("StaticRune Page does not have a name", page.getName());
                                     Assert.assertNotNull("StaticRune Page does not contain any slots", page.getSlots());
                                     Assert.assertNotNull("Unable to determine current StaticRune page", page.isCurrent());
            
                                     page.getSlots().forEach((final RuneSlot slot) ->
                                                             {
                                                                 Assert.assertNotNull("StaticRune slot does not have a slot id", slot.getRuneSlotId());
                                                                 Assert.assertNotNull("StaticRune slot does not have a rune id", slot.getRuneId());
                                                                 Assert.assertNotEquals("StaticRune slot does not have a valid id", slot.getRuneSlotId(), (Integer) 0);
                                                                 Assert.assertNotEquals("StaticRune slot does not have a valid rune id", slot.getRuneId(), (Integer) 0);
                                                             });
                                 });
        
    };
    
    @Test
    public void testRunePages()
    {
        final L4J8  l4j8     = new L4J8(SecretFile.CREDS);
        SummonerAPI api      = l4j8.getSummonerAPI();
        RunePages   optional = api.getRunes(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        doAssertions.accept(optional);
    }
}
    
