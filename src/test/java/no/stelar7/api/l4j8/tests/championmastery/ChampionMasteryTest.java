package no.stelar7.api.l4j8.tests.championmastery;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.List;

public class ChampionMasteryTest
{
    
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    MasteryAPI api = l4j8.getMasteryAPI();
    
    @Test
    public void testChampionMastery()
    {
        ChampionMastery mastery;
        mastery = api.getChampionMastery(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0], Constants.TEST_CHAMPION_IDS[0]);
        assert mastery != null;
        mastery = api.getChampionMastery(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0], Constants.TEST_CHAMPION_IDS[1]);
        assert mastery != null;
        
    }
    
    @Test
    public void testChampionMasteryAll()
    {
        List<ChampionMastery> all = api.getChampionMasteries(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryScore()
    {
        Integer score = api.getMasteryScore(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        Assert.assertNotNull("no data", score);
    }
    
}
