package no.stelar7.api.r4j.tests.tft;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.TierDivisionType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.lol.league.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestTFTLeague
{
    
    final R4J l4j8 = new R4J(SecretFile.CREDS);
    
    @Test
    public void testSilver()
    {
        List<LeagueEntry> tierDiv  = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.SILVER_I, 1);
        List<LeagueEntry> tierDiv2 = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.SILVER_I, 5);
        System.out.println();
    }
    
    @Test
    public void testChallenger()
    {
        List<LeagueEntry> tierDiv = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.CHALLENGER_I, 1);
        List<LeagueEntry> tierDiv2 = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(LeagueShard.EUW1, TierDivisionType.CHALLENGER_I, 5);
        System.out.println();
    }
    
    @Test
    public void testEntries()
    {
        List<LeagueEntry> entries = l4j8.getTFTAPI().getLeagueAPI().getLeagueEntries(LeagueShard.EUW1, Summoner.byName(LeagueShard.EUW1, "stelar7").getSummonerId());
        System.out.println();
    }
    
    @Test
    public void testList()
    {
        LeagueList entries = l4j8.getTFTAPI().getLeagueAPI().getLeague(LeagueShard.EUW1, "f72c0910-aa73-11e9-93ca-c81f66dacb22");
        System.out.println();
    }
}
