package no.stelar7.api.r4j.tests.lol.championmastery;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.championmastery.ChampionMasteryBuilder;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.championmastery.ChampionMastery;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.List;

public class ChampionMasteryTest
{
    
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    
    @Test
    public void testChampionMastery()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        ChampionMastery        mastery;
        ChampionMasteryBuilder bu = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withSummonerId(s.getSummonerId());
        
        mastery = bu.withChampionId(1).getChampionMastery();
        assert mastery != null;
        
        mastery = bu.withChampionId(2).getChampionMastery();
        assert mastery != null;
        
    }
    
    @Test
    public void testChampionMasteryWithPUUID()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        ChampionMastery        mastery;
        ChampionMasteryBuilder bu = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withPUUID(s.getPUUID());
        
        mastery = bu.withChampionId(1).getChampionMastery();
        assert mastery != null;
        
        mastery = bu.withChampionId(2).getChampionMastery();
        assert mastery != null;
        
    }
    
    
    @Test
    public void testChampionMasteryTop()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        List<ChampionMastery> all = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withSummonerId(s.getSummonerId()).getTopChampions(null);
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryAll()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        List<ChampionMastery> all = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withSummonerId(s.getSummonerId()).getChampionMasteries();
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryScore()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        Integer score = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withSummonerId(s.getSummonerId()).getMasteryScore();
        Assertions.assertNotNull(score, "no data");
    }
    
}
