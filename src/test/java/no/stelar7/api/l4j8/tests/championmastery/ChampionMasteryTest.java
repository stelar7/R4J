package no.stelar7.api.l4j8.tests.championmastery;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.impl.builders.championmastery.ChampionMasteryBuilder;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.List;

public class ChampionMasteryTest
{
    
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    
    @Test
    public void testChampionMastery()
    {
        ChampionMastery        mastery;
        ChampionMasteryBuilder bu = l4j8.getMastery().withPlatform(Constants.TEST_PLATFORM[0]).withSummonerId(Constants.TEST_SUMMONER_IDS[0]);
        
        mastery = bu.withChampionId(Constants.TEST_CHAMPION_IDS[0]).getChampionMastery();
        assert mastery != null;
        
        mastery = bu.withChampionId(Constants.TEST_CHAMPION_IDS[1]).getChampionMastery();
        assert mastery != null;
        
    }
    
    @Test
    public void testChampionMasteryAll()
    {
        List<ChampionMastery> all = l4j8.getMastery().withPlatform(Constants.TEST_PLATFORM[0]).withSummonerId(Constants.TEST_SUMMONER_IDS[0]).getChampionMasteries();
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryScore()
    {
        Integer score = l4j8.getMastery().withPlatform(Constants.TEST_PLATFORM[0]).withSummonerId(Constants.TEST_SUMMONER_IDS[0]).getMasteryScore();
        Assert.assertNotNull("no data", score);
    }
    
}
