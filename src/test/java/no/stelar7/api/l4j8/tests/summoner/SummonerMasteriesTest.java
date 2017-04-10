package no.stelar7.api.l4j8.tests.summoner;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.masteries.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.*;

public class SummonerMasteriesTest
{
    
    public static final Logger LOGGER = Logger.getGlobal();
    
    private final Consumer<MasteryPages> doAssertions = (MasteryPages pages) ->
    {
        System.out.println(pages);
        pages.getPages().forEach(page ->
                                 {
            
                                     Assert.assertNotNull("Mastery Page does not have an id", page.getId());
                                     // Assert.assertNotNull("Mastery Page does not have a name",
                                     // page.getName()); Names can be empty, so ignore this...
                                     Assert.assertNotNull("Mastery Page does not contain any masteries", page.getMasteries());
                                     Assert.assertNotNull("Unable to determine current Mastery page", page.isCurrent());
            
                                     page.getMasteries().forEach((final Mastery mastery) ->
                                                                 {
                                                                     Assert.assertNotNull("Mastery does not have an id", mastery.getId());
                                                                     Assert.assertNotNull("Mastery does not have a rank", mastery.getRank());
                
                                                                     Assert.assertNotEquals("Mastery does not have a valid id", mastery.getId(), (Integer) 0);
                                                                     Assert.assertNotEquals("Mastery does not have a valid rank", mastery.getRank(), (Integer) 0);
                                                                 });
            
                                 });
    };
    
    @Test
    public void testMasteryPages()
    {
        final L4J8             l4j8     = new L4J8(SecretFile.CREDS);
        SummonerAPI            api      = l4j8.getSummonerAPI();
        Optional<MasteryPages> optional = api.getMasteries(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        
        Assert.assertTrue("No data returned", optional.isPresent());
        optional.ifPresent(doAssertions);
    }
}
