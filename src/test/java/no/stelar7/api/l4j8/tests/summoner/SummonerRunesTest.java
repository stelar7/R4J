package no.stelar7.api.l4j8.tests.summoner;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.runes.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.List;
import java.util.function.Consumer;

public class SummonerRunesTest
{
    
    private final Consumer<List<RunePage>> doAssertions = (final List<RunePage> pages) ->
    {
        pages.forEach(page ->
                      {
                          Assert.assertNotNull("StaticRune Page does not have a name", page.getName());
                          Assert.assertNotNull("StaticRune Page does not contain any slots", page.getSlots());
            
                          page.getSlots().forEach((final RuneSlot slot) ->
                                                  {
                                                      Assert.assertNotEquals("StaticRune slot does not have a valid id", slot.getRuneSlotId(), 0);
                                                      Assert.assertNotEquals("StaticRune slot does not have a valid rune id", slot.getRuneId(), 0);
                                                  });
                      });
        
    };
    
    @Test
    public void testRunePages()
    {
        final L4J8     l4j8     = new L4J8(SecretFile.CREDS);
        SummonerAPI    api      = l4j8.getSummonerAPI();
        List<RunePage> optional = api.getRunes(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        doAssertions.accept(optional);
    }
}
    
