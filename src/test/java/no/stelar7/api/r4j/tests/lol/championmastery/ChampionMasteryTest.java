package no.stelar7.api.r4j.tests.lol.championmastery;

import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.championmastery.ChampionMasteryBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.impl.lol.raw.SpectatorAPI;
import no.stelar7.api.r4j.pojo.lol.championmastery.ChampionMastery;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.*;

public class ChampionMasteryTest
{
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    @BeforeEach
    public void clearCache() {
        DataCall.getCacheProvider().clear(URLEndpoint.V4_MASTERY_BY_CHAMPION, new HashMap<>());
        DataCall.getCacheProvider().clear(URLEndpoint.V4_MASTERY_BY_CHAMPION, new HashMap<>());
        DataCall.getCacheProvider().clear(URLEndpoint.V4_MASTERY_BY_PUUID, new HashMap<>());
        DataCall.getCacheProvider().clear(URLEndpoint.V4_MASTERY_TOP, new HashMap<>());
    }
    
    @Test
    public void testChampionMastery()
    {
        String   id = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withPUUID(id).get();
        
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
        String   id = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withPUUID(id).get();
        
        List<ChampionMastery> all = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withPUUID(s.getPUUID()).getTopChampions(null);
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryAll()
    {
        String   id = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withPUUID(id).get();
        
        List<ChampionMastery> all = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withPUUID(s.getPUUID()).getChampionMasteries();
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryScore()
    {
        String   id = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withPUUID(id).get();
        
        Integer score = new ChampionMasteryBuilder().withPlatform(s.getPlatform()).withPUUID(s.getPUUID()).getMasteryScore();
        Assertions.assertNotNull(score, "no data");
    }
    
    @Test
    public void testChampionMasteryRaw()
    {
        String   id = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withPUUID(id).get();
        
        ChampionMastery mastery = r4J.getLoLAPI().getMasteryAPI().getChampionMastery(s.getPlatform(), s.getPUUID(), 1);
        assert mastery != null;
        
        mastery = mastery = r4J.getLoLAPI().getMasteryAPI().getChampionMastery(s.getPlatform(), s.getPUUID(), 2);
        assert mastery != null;
    }
    
    
    @Test
    public void testChampionMasteryTopRaw()
    {
        String   id = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withPUUID(id).get();
        
        List<ChampionMastery> all = r4J.getLoLAPI().getMasteryAPI().getTopChampions(s.getPlatform(), s.getPUUID(), null);
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryAllRaw()
    {
        String   id = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withPUUID(id).get();
        
        List<ChampionMastery> all = r4J.getLoLAPI().getMasteryAPI().getChampionMasteries(s.getPlatform(), s.getPUUID());
        assert all != null;
    }
    
    @Test
    public void testChampionMasteryScoreRaw()
    {
        String   id = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withPUUID(id).get();
        
        Integer score = r4J.getLoLAPI().getMasteryAPI().getMasteryScore(s.getPlatform(), s.getPUUID());
        Assertions.assertNotNull(score, "no data");
    }
    
}
