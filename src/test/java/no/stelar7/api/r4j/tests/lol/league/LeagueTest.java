package no.stelar7.api.r4j.tests.lol.league;

import ch.qos.logback.classic.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.league.LeagueBuilder;
import no.stelar7.api.r4j.impl.lol.raw.LeagueAPI;
import no.stelar7.api.r4j.pojo.lol.league.LeagueEntry;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LeagueTest
{
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    static
    {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
    }
    
    @Test
    public void testMasterLeague()
    {
        List<LeagueEntry> data = LeagueAPI.getInstance().getLeagueByTierDivision(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.MASTER_I, 1);
        System.out.println(data.size());
    }
    
    @Test
    public void testGrandmasterLeague()
    {
        LazyList<LeagueEntry> data = LeagueAPI.getInstance().getLeagueByTierDivisionLazy(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.GRANDMASTER_I);
        data.loadFully();
        System.out.println(data.size());
    }
    
    @Test
    public void testChallengerLeague()
    {
        LazyList<LeagueEntry> data = LeagueAPI.getInstance().getLeagueByTierDivisionLazy(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.CHALLENGER_I);
        data.loadFully();
        System.out.println(data.size());
    }
    
    @Test
    public void testLeagueEntryByPUUID() 
    {
        RiotAccount       account  = r4J.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "KaluNight", "ABM2", ApiKeyType.LOL);
        List<LeagueEntry> data     = new LeagueBuilder().withPlatform(LeagueShard.EUW1).withPuuid(account.getPUUID()).getLeagueEntries();
        System.out.println(data);

        LeagueAPI.getInstance().getLeagueEntriesByPUUID(LeagueShard.EUW1, account.getPUUID());
        System.out.println(data);
    }
    
    @Test
    @Disabled
    public void testPositionalRanks()
    {
        LazyList<LeagueEntry> data = r4J.getLoLAPI().getLeagueAPI().getLeagueByTierDivisionLazy(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.DIAMOND_I);
        data.loadFully();
        System.out.println(data.size());
    }
}
