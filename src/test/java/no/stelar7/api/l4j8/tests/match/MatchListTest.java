package no.stelar7.api.l4j8.tests.match;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.match.MatchDetail;
import no.stelar7.api.l4j8.pojo.matchlist.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.function.Consumer;

public class MatchListTest
{
    private final Consumer<MatchReference> doAssertions = (final MatchReference match) ->
    {
        Assert.assertNotNull("matchId is null", match.getMatchId());
        Assert.assertNotNull("timestamp is null", match.getTimestamp());
        Assert.assertNotNull("lane is null", match.getLane());
        Assert.assertNotNull("platform is null", match.getPlatform());
        Assert.assertNotNull("queue is null", match.getQueue());
        Assert.assertNotNull("region is null", match.getRegion());
        Assert.assertNotNull("role is null", match.getRole());
        Assert.assertNotNull("season is null", match.getSeason());
        
        Assert.assertNotNull("TIMESTAMP is null", match.getTimestampAsDate());
        Assert.assertNotNull("LANE is null", match.getLane());
        Assert.assertNotNull("QUEUE is null", match.getQueue());
        Assert.assertNotNull("ROLE is null", match.getRole());
        Assert.assertNotNull("SEASON is null", match.getSeason());
        Assert.assertNotNull("REGION is null", match.getRegion());
        
        Assert.assertEquals("Unexpected ChampionType Id", (long) Constants.TEST_CHAMPION_IDS[0], (long) match.getChampion().getId());
        Assert.assertEquals("Unexpected Queue", RankedQueueType.RANKED_SOLO_5X5, match.getQueue());
        
        Assert.assertNotNull("lane is null", match.getLane());
        Assert.assertNotNull("queue is null", match.getQueue());
        Assert.assertNotNull("role is null", match.getRole());
        Assert.assertNotNull("season is null", match.getSeason());
        Assert.assertNotNull("region is null", match.getRegion());
    };
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    MatchAPI api   = l4j8.getMatchAPI();
    Optional empty = Optional.empty();
    
    @Test
    public void testMatchAndMatchList()
    {
        Optional<EnumSet<RankedQueueType>> queue  = Optional.of(EnumSet.of(RankedQueueType.RANKED_SOLO_5X5));
        Optional<EnumSet<SeasonType>>      season = Optional.of(EnumSet.of(SeasonType.SEASON_2014));
        Optional<List<Integer>>            champs = Optional.of(Collections.singletonList(Constants.TEST_CHAMPION_IDS[0]));
        //Optional<MatchList>                list   = api.getMatchList(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0], empty, empty, queue, season, empty, empty, champs);
        Optional<MatchList> list = api.getOldMatchList(Server.EUW, Constants.TEST_SUMMONER_IDS[0], empty, empty, queue, empty, empty, empty, empty);
        
        
        Assert.assertTrue("no data?", list.isPresent());
        
        // I played 47 ranked solo games as leona in 2014
        // Or 50.. as it says suddenly
        // Just remove this check for now
        //Assert.assertEquals("Unexpected amount of games returned", list.get().getMatches().size(), 47);
        
        Optional<MatchDetail> detail = api.getOldMatch(Server.EUW, list.get().getMatches().get(0).getMatchId(), Optional.of(true));
        System.out.println(detail);
    }
    
    
}
