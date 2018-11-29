package no.stelar7.api.l4j8.tests.championmastery;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.impl.builders.championmastery.ChampionMasteryBuilder;
import no.stelar7.api.l4j8.impl.builders.spectator.SpectatorBuilder;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.List;

public class ChampionMasteryTest
{
    
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    
    @Test
    public void testChampionMastery()
    {
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        ChampionMastery        mastery;
        ChampionMasteryBuilder bu = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withSummonerId(s.getSummonerId());
        
        mastery = bu.withChampionId(1).getChampionMastery();
        assert mastery != null;
        
        mastery = bu.withChampionId(2).getChampionMastery();
        assert mastery != null;
        
    }
    
    
    @Test
    public void testChampionMasteryTop()
    {
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        List<ChampionMastery> all = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withSummonerId(s.getSummonerId()).getTopChampions(null);
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryAll()
    {
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        List<ChampionMastery> all = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withSummonerId(s.getSummonerId()).getChampionMasteries();
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryScore()
    {
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        Integer score = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withSummonerId(s.getSummonerId()).getMasteryScore();
        Assert.assertNotNull("no data", score);
    }
    
}
