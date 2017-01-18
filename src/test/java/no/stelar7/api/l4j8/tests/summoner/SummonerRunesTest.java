package no.stelar7.api.l4j8.tests.summoner;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.runes.*;
import no.stelar7.api.l4j8.tests.*;
import org.junit.*;

import java.util.*;
import java.util.function.*;
import java.util.logging.*;
import java.util.stream.*;

public class SummonerRunesTest
{
    
    public static final Logger LOGGER = Logger.getGlobal();
    
    private final BiConsumer<Long, Optional<RunePages>> doAssertions = (final Long key, final Optional<RunePages> optional) ->
    {
        if (optional.isPresent())
        {
            final RunePages pages = optional.get();
            
            pages.getPages().forEach(page ->
                                     {
                
                                         Assert.assertNotNull("Rune Page does not have an id", page.getId());
                                         Assert.assertNotNull("Rune Page does not have a name", page.getName());
                                         Assert.assertNotNull("Rune Page does not contain any slots", page.getSlots());
                                         Assert.assertNotNull("Unable to determine current Rune page", page.isCurrent());
                
                                         page.getSlots().forEach((final RuneSlot slot) ->
                                                                 {
                                                                     Assert.assertNotNull("Rune slot does not have a slot id", slot.getRuneSlotId());
                                                                     Assert.assertNotNull("Rune slot does not have a rune id", slot.getRuneId());
                                                                     Assert.assertNotEquals("Rune slot does not have a valid id", slot.getRuneSlotId(), (Integer) 0);
                                                                     Assert.assertNotEquals("Rune slot does not have a valid rune id", slot.getRuneId(), (Integer) 0);
                                                                 });
                                     });
            
            SummonerRunesTest.LOGGER.log(Level.INFO, "RunePages " + key + " = OK");
        } else
        {
            SummonerRunesTest.LOGGER.log(Level.INFO, "RunePages " + key + " = BAD");
        }
    };
    
    @Test
    public void doTest()
    {
        final L4J8 api = new L4J8(SecretFile.CREDS);
        
        // Generate list of summoner IDs
        final List<Long> values = Stream.of(Constants.TEST_SUMMONER_IDS).collect(Collectors.toList());
        api.getRunes(Server.EUW, values).forEach(this.doAssertions);
    }
    
}
