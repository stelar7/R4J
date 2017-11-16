package no.stelar7.api.l4j8.tests.league;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.impl.builders.league.LeagueBuilder;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;

public class LeagueTest
{
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    LeagueBuilder lb = new LeagueBuilder().withPlatform(Constants.TEST_PLATFORM[0]);
    
    @Test
    public void testMasterLeague()
    {
        LeagueList data = lb.withQueue(GameQueueType.RANKED_SOLO_5X5).getMasterLeague();
        assert data != null;
    }
    
    @Test
    public void testChallengerLeague()
    {
        LeagueList data = lb.withQueue(GameQueueType.RANKED_FLEX_SR).getChallengerLeague();
        assert data != null;
    }
    
    
    @Test
    public void testLeagueEntry()
    {
        List<LeaguePosition> data = lb.withSummonerId(Constants.TEST_SUMMONER_IDS[0]).getLeaguePosition();
        assert data != null;
    }
    
}
