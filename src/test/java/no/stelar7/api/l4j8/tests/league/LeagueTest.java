package no.stelar7.api.l4j8.tests.league;

import ch.qos.logback.classic.*;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.utils.LazyList;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.league.LeagueBuilder;
import no.stelar7.api.l4j8.impl.builders.spectator.SpectatorBuilder;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.impl.raw.LeagueAPI;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LeagueTest
{
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    static
    {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
    }
    
    @Test
    public void testMasterLeague()
    {
        LazyList<LeagueEntry> data = LeagueAPI.getInstance().getLeagueByTierDivisionLazy(Platform.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.MASTER_I);
        data.loadFully();
        System.out.println(data.size());
    }
    
    @Test
    public void testGrandmasterLeague()
    {
        LazyList<LeagueEntry> data = LeagueAPI.getInstance().getLeagueByTierDivisionLazy(Platform.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.GRANDMASTER_I);
        data.loadFully();
        System.out.println(data.size());
    }
    
    @Test
    public void testChallengerLeague()
    {
        LazyList<LeagueEntry> data = LeagueAPI.getInstance().getLeagueByTierDivisionLazy(Platform.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.CHALLENGER_I);
        data.loadFully();
        System.out.println(data.size());
    }
    
    
    @Test
    public void testLeagueEntry()
    {
        String            id   = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner          s    = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        List<LeagueEntry> data = new LeagueBuilder().withPlatform(Platform.EUW1).withSummonerId(s.getSummonerId()).getLeagueEntries();
        System.out.println(data.size());
    }
    
    @Test
    @Ignore
    public void testPositionalRanks()
    {
        LazyList<LeagueEntry> data = l4j8.getLeagueAPI().getLeagueByTierDivisionLazy(Platform.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.DIAMOND_I);
        data.loadFully();
        System.out.println(data.size());
    }
    
    @Test
    @Ignore
    public void testPositionalRanksTFT()
    {
        LazyList<LeagueEntry> data = l4j8.getLeagueAPI().getLeagueByTierDivisionLazy(Platform.EUW1, GameQueueType.TEAMFIGHT_TACTICS_RANKED, TierDivisionType.GOLD_I);
        data.loadFully();
        System.out.println(data.size());
    }
}
