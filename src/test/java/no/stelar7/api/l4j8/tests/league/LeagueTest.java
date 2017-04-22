package no.stelar7.api.l4j8.tests.league;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.RankedQueueType;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.league.League;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;

public class LeagueTest
{
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    LeagueAPI api = l4j8.getLeagueAPI();
    
    @Test
    public void testMasterLeague()
    {
        Optional<League> data = api.getOldMasterLeague(Server.EUW, RankedQueueType.RANKED_FLEX_SR);
        Assert.assertTrue("no data?", data.isPresent());
    }
    
    @Test
    public void testChallengerLeague()
    {
        Optional<League> data = api.getOldChallengerLeague(Server.EUW, RankedQueueType.RANKED_FLEX_SR);
        Assert.assertTrue("no data?", data.isPresent());
    }
    
    
    @Test
    public void testLeagueEntry()
    {
        Optional<Map<String, List<League>>> data = api.getOldLeagueEntry(Server.EUW, Constants.TEST_SUMMONER_IDS[0]);
        Assert.assertTrue("no data?", data.isPresent());
    }
    
    @Test
    public void testLeague()
    {
        Optional<Map<String, List<League>>> data = api.getOldLeague(Server.EUW, Constants.TEST_SUMMONER_IDS[0]);
        Assert.assertTrue("no data?", data.isPresent());
    }
}
