package no.stelar7.api.r4j.tests.tft;

import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.basic.constants.types.TierDivisionType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.lol.league.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;

import java.util.List;

public class TestTFTLeague
{
    
    final R4J l4j8 = new R4J(SecretFile.CREDS);
    
    @Test
    public void testSilver()
    {
        List<LeagueEntry> tierDiv  = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(Platform.EUW1, TierDivisionType.SILVER_I, 1);
        List<LeagueEntry> tierDiv2 = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(Platform.EUW1, TierDivisionType.SILVER_I, 5);
        System.out.println();
    }
    
    @Test
    public void testChallenger()
    {
        List<LeagueEntry> tierDiv = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(Platform.EUW1, TierDivisionType.CHALLENGER_I, 1);
        List<LeagueEntry> tierDiv2 = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(Platform.EUW1, TierDivisionType.CHALLENGER_I, 5);
        System.out.println();
    }
    
    @Test
    public void testEntries()
    {
        List<LeagueEntry> entries = l4j8.getTFTAPI().getLeagueAPI().getLeagueEntries(Platform.EUW1, Summoner.byName(Platform.EUW1, "stelar7").getSummonerId());
        System.out.println();
    }
    
    @Test
    public void testList()
    {
        LeagueList entries = l4j8.getTFTAPI().getLeagueAPI().getLeague(Platform.EUW1, "f72c0910-aa73-11e9-93ca-c81f66dacb22");
        System.out.println();
    }
}
