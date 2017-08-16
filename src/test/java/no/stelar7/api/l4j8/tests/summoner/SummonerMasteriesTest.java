package no.stelar7.api.l4j8.tests.summoner;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.masteries.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.List;
import java.util.function.Consumer;

public class SummonerMasteriesTest
{
    
    private final Consumer<List<MasteryPage>> doAssertions = (List<MasteryPage> pages) ->
            pages.forEach(page ->
                          {
                              Assert.assertNotNull("SpectatorMastery Page does not contain any masteries", page.getMasteries());
                
                              page.getMasteries().forEach((final Mastery mastery) ->
                                                          {
                                                              Assert.assertNotEquals("SpectatorMastery does not have a valid id", mastery.getId(), 0);
                                                              Assert.assertNotEquals("SpectatorMastery does not have a valid rank", mastery.getRank(), 0);
                                                          });
                
                          });
    
    @Test
    public void testMasteryPages()
    {
        final L4J8        l4j8     = new L4J8(SecretFile.CREDS);
        SummonerAPI       api      = l4j8.getSummonerAPI();
        List<MasteryPage> optional = api.getMasteries(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        doAssertions.accept(optional);
    }
}
