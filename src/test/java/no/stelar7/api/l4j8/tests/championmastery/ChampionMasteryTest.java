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
        if (data.getChampionLevel() == 0)
        {
            Assert.assertNotNull("champion is null", data.getChampion());
            Assert.assertNotNull("summoner is null", data.getSummonerId());
            return;
        }
        
        Assert.assertNotNull("ChampionId is null", data.getChampion().getId());
        Assert.assertNotNull("Max level is not 5?", data.getChampionLevel());
        Assert.assertTrue("Points less than 80k", data.getChampionPoints() > 80000);
        Assert.assertTrue("0 Points?", data.getChampionPointsSinceLastLevel() > 0L);
        Assert.assertTrue("There is another level?", data.getChampionPointsUntilNextLevel() == 0L);
        Assert.assertNotNull("Chest has been removed implemented", data.getChestGranted());
        Assert.assertNotNull("Last play date DATE is NULL", data.getLastPlayTimeAsDate());
    };
    
    Consumer<List<ChampionMastery>> doListAssertions = (final List<ChampionMastery> list) ->
    {
        for (ChampionMastery championMastery : list)
        {
            Assert.assertTrue("no data", championMastery.getChampion().getId() != 0);
        }
    };
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    MasteryAPI api = l4j8.getMasteryAPI();
    
    @Test
    public void testChampionMastery()
    {
        ChampionMastery mastery;
        
        mastery = api.getChampionMastery(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0], Constants.TEST_CHAMPION_IDS[0]);
        doAssertions.accept(mastery);
        mastery = api.getChampionMastery(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0], Constants.TEST_CHAMPION_IDS[1]);
        doAssertions.accept(mastery);
    }
    
    @Test
    public void testChampionMasteryAll()
    {
        List<ChampionMastery> all = api.getChampionMasteries(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        doListAssertions.accept(all);
        
    }
    
    @Test
    public void testChampionMasteryScore()
    {
        Integer score = api.getMasteryScore(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        Assert.assertNotNull("no data", score);
    }
    
}
