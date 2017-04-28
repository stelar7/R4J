package no.stelar7.api.l4j8.tests.match;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.matchlist.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.function.Consumer;

public class MatchListTest
{
    private final Consumer<MatchReference> doAssertions = (final MatchReference match) ->
    {
        Assert.assertNotNull("matchId is null", match.getGameId());
        Assert.assertNotNull("timestamp is null", match.getTimestamp());
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
    MatchAPI api   = l4j8.getMatchAPI();
    Optional empty = Optional.empty();
    
    @Test
    public void testMatchAndMatchList()
    {
        Optional<EnumSet<GameQueueType>> queue     = Optional.of(EnumSet.of(GameQueueType.TEAM_BUILDER_RANKED_SOLO));
        Optional<EnumSet<SeasonType>>    season    = Optional.of(EnumSet.of(SeasonType.PRE_SEASON_2017));
        Optional<List<Integer>>          champs    = Optional.of(Arrays.asList(Constants.TEST_CHAMPION_IDS[0], 99));
        Optional<Long>                   beginTime = Optional.of(1481108400000L);
        
        // use begintime instead of season because its broken ATM
        MatchList all = api.getMatchList(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0], beginTime, empty, empty, empty, empty, empty, empty);
        
        for (MatchReference reference : all.getMatches())
        {
            Match                   detail   = api.getMatch(reference.getPlatform(), reference.getGameId());
            Optional<MatchTimeline> timeline = api.getTimeline(reference.getPlatform(), reference.getGameId());
            System.out.println(detail.getParticipants().get(1).getTimeline());
            System.out.println();
        }
    }
}
