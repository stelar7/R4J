package no.stelar7.api.l4j8.tests.match;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.function.Consumer;

public class MatchListTest
{
    private final Consumer<MatchReference> doAssertions = (final MatchReference match) ->
    {
        Assert.assertNotNull("lane is null", match.getLane());
        Assert.assertNotNull("platform is null", match.getPlatform());
        Assert.assertNotNull("queue is null", match.getQueue());
        Assert.assertNotNull("role is null", match.getRole());
        Assert.assertNotNull("season is null", match.getSeason());
        
        Assert.assertNotNull("TIMESTAMP is null", match.getTimestampAsDate());
        Assert.assertNotNull("LANE is null", match.getLane());
        Assert.assertNotNull("QUEUE is null", match.getQueue());
        Assert.assertNotNull("ROLE is null", match.getRole());
        Assert.assertNotNull("SEASON is null", match.getSeason());
        
        Assert.assertNotNull("lane is null", match.getLane());
        Assert.assertNotNull("queue is null", match.getQueue());
        Assert.assertNotNull("role is null", match.getRole());
        Assert.assertNotNull("season is null", match.getSeason());
    };
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    MatchAPI api = l4j8.getMatchAPI();
    
    @Test
    @Ignore
    public void testMatchAndMatchList()
    {
        Set<GameQueueType> queue      = EnumSet.of(GameQueueType.TEAM_BUILDER_RANKED_SOLO);
        Set<SeasonType>    season     = null;//EnumSet.of(SeasonType.SEASON_2017);
        List<Integer>      champ      = null;//Arrays.asList(Constants.TEST_CHAMPION_IDS);
        Long               beginTime  = null;//1481108400000L; // start of season 2017
        Long               endTime    = null;//beginTime + 604800000; // 604800000 is one week in ms
        Integer            beginIndex = null;//0;
        Integer            endIndex   = null;//50;
        
        List<MatchReference> all = api.getMatchList(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0], beginTime, endTime, beginIndex, endIndex, queue, season, champ);
        
        for (MatchReference reference : all)
        {
            Match         detail   = api.getMatch(reference.getPlatform(), reference.getGameId());
            MatchTimeline timeline = api.getTimeline(reference.getPlatform(), reference.getGameId());
        }
    }
    
    @Test
    public void testMatchlistSeasons()
    {
        Set<SeasonType> sixList      = EnumSet.of(SeasonType.SEASON_2016);
        Set<SeasonType> preSevenList = EnumSet.of(SeasonType.PRE_SEASON_2017);
        Set<SeasonType> sevenList    = EnumSet.of(SeasonType.SEASON_2017);
        
        List<MatchReference> twosix   = api.getMatchList(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0], null, null, null, null, null, sixList, null);
        List<MatchReference> preseven = api.getMatchList(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0], null, null, null, null, null, preSevenList, null);
        List<MatchReference> twoseven = api.getMatchList(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0], null, null, null, null, null, sevenList, null);
    }
    
    
    @Test
    public void testMatch()
    {
        Match detail = api.getMatch(Platform.EUW1, Constants.TEST_MATCH_ID[0]);
    }
    
}


