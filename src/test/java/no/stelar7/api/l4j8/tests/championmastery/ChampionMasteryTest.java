package no.stelar7.api.l4j8.tests.championmastery;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.function.Consumer;

public class ChampionMasteryTest
{
    
    
    Consumer<ChampionMastery> doAssertions = (final ChampionMastery data) ->
    {
        Assert.assertTrue("ChampionId has changed?", data.getChampionId().equals(Constants.TEST_CHAMPION_IDS[0]));
        Assert.assertTrue("Max level is not 5?", data.getChampionLevel() > 5);
        Assert.assertTrue("Points less than 80k", data.getChampionPoints() > 80000);
        Assert.assertTrue("0 Points?", data.getChampionPointsSinceLastLevel() > 0L);
        Assert.assertTrue("There is another level?", data.getChampionPointsUntilNextLevel() == 0L);
        Assert.assertNotNull("Chest has been removed implemented", data.getChestGranted());
        Assert.assertNotNull("Last play date DATE is NULL", data.getLastPlayTimeAsDate());
        Assert.assertEquals("last play date and DATE do not match", data.getLastPlayTime(), (Long) data.getLastPlayTimeAsDate()
                                                                                                       .toInstant()
                                                                                                       .toEpochMilli());
    };
    
    Consumer<List<ChampionMastery>> doListAssertions = (final List<ChampionMastery> list) ->
    {
        for (ChampionMastery championMastery : list)
        {
            Assert.assertTrue("no data", championMastery.getChampionId() != 0);
        }
    };
    
    @Test
    public void testChampionMastery()
    {
        final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
        MasteryAPI api  = l4j8.getMasteryrAPI();
        
        Optional<ChampionMastery> mastrey = api.getChampionMastery(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0], Constants.TEST_CHAMPION_IDS[0]);
        Assert.assertTrue("No data returned", mastrey.isPresent());
        mastrey.ifPresent(doAssertions);
    }
    
    @Test
    public void testChampionMasteryAll()
    {
        final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
        MasteryAPI api  = l4j8.getMasteryrAPI();
        
        Optional<List<ChampionMastery>> all = api.getMasteries(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        Assert.assertTrue("No data returned", all.isPresent());
        all.ifPresent(doListAssertions);
        
    }
    
    @Test
    public void testChampionMasteryScore()
    {
        final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
        MasteryAPI api  = l4j8.getMasteryrAPI();
        
        Optional<Integer> score = api.getMasteryScore(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        Assert.assertTrue("No data returned", score.isPresent());
        score.ifPresent(Assert::assertNotNull);
    }
    
}
