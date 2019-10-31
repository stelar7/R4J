package no.stelar7.api.l4j8.tests.tft;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.TierDivisionType;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Test;

import java.util.List;

public class TestTFTLeague
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    public void testSilver()
    {
        List<? extends LeagueItem> tierDiv = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(Platform.EUW1, TierDivisionType.SILVER_I, 1);
        System.out.println();
    }
    
    @Test
    public void testChallenger()
    {
        List<? extends LeagueItem> tierDiv = l4j8.getTFTAPI().getLeagueAPI().getLeagueByTierDivision(Platform.EUW1, TierDivisionType.CHALLENGER_I, 1);
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
