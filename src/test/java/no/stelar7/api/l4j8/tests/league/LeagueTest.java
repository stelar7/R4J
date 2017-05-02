package no.stelar7.api.l4j8.tests.league;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.league.*;
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
        LeagueList data = api.getMasterLeague(Platform.EUW1, GameQueueType.RANKED_FLEX_SR);
    }
    
    @Test
    public void testChallengerLeague()
    {
        LeagueList data = api.getChallengerLeague(Platform.EUW1, GameQueueType.RANKED_FLEX_SR);
    }
    
    
    @Test
    public void testLeagueEntry()
    {
        List<LeaguePosition> data = api.getLeaguePosition(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
    }
    
    @Test
    public void testLeague()
    {
        List<LeagueList> data = api.getLeague(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
    }
}
