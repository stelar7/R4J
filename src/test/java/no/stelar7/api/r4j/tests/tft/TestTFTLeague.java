package no.stelar7.api.r4j.tests.tft;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.TierDivisionType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.pojo.tft.league.*;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestTFTLeague
{
    
    final R4J l4j8 = new R4J(SecretFile.CREDS);
    
    @Test
    public void testSilver()
    {
        List<TFTLeagueEntry> tierDiv  = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.SILVER_I, 1);
        List<TFTLeagueEntry> tierDiv2 = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.SILVER_I, 5);
        System.out.println();
    }
    
    @Test
    public void testChallenger()
    {
        List<TFTLeagueEntry> tierDiv  = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.CHALLENGER_I, 1);
        List<TFTLeagueEntry> tierDiv2 = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.CHALLENGER_I, 5);
    }
    
    @Test
    public void testEntries()
    {
        RiotAccount account  = l4j8.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7");
        Summoner    summoner = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        
        List<TFTLeagueEntry> entries = l4j8.getTFTAPI().getLeagueAPI().getLeagueEntries(LeagueShard.EUW1, summoner.getSummonerId());
        System.out.println();
    }
    
    @Test
    public void testList()
    {
        TFTLeagueList entries = l4j8.getTFTAPI().getLeagueAPI().getLeague(LeagueShard.EUW1, "f72c0910-aa73-11e9-93ca-c81f66dacb22");
        System.out.println();
    }
}
