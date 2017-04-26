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
        Optional<EnumSet<RankedQueueType>> queue  = Optional.of(EnumSet.of(RankedQueueType.RANKED_SOLO_5X5));
        Optional<EnumSet<SeasonType>>      season = Optional.of(EnumSet.of(SeasonType.SEASON_2014));
        Optional<List<Integer>>            champs = Optional.of(Collections.singletonList(Constants.TEST_CHAMPION_IDS[0]));
        //Optional<MatchList>                list   = api.getMatchList(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0], empty, empty, queue, season, empty, empty, champs);
        MatchList all    = api.getMatchList(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0], empty, empty, empty, empty, empty, empty, empty);
        MatchList recent = api.getRecentMatches(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        
        MatchReference          ref         = all.getMatches().get(all.getMatches().size() - 1);
        Optional<MatchTimeline> oldtimeline = api.getMatch(ref.getPlatform(), ref.getGameId()).getTimeline();
        
        oldtimeline.ifPresent(a -> System.out.println("HUH? this shouldnt be here..."));
        
        for (MatchReference reference : all.getMatches())
        {
            Match                   detail   = api.getMatch(reference.getPlatform(), reference.getGameId());
            Optional<MatchTimeline> timeline = api.getTimeline(reference.getPlatform(), reference.getGameId());
        }
    }
}
