package no.stelar7.api.l4j8.tests.matchlist;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import org.junit.*;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.matchlist.*;
import no.stelar7.api.l4j8.tests.*;

public class MatchListTest extends TestBase
{
    private final Consumer<MatchReference> doAssertions = (final MatchReference match) -> {
        Assert.assertNotNull("champion is null", match.getChampion());
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

        Assert.assertEquals("Unexpected Champion Id", Champion.LEONA, match.getChampion().get());
        Assert.assertEquals("Unexpected Queue", RankedQueue.RANKED_SOLO_5X5, match.getQueue().get());
        Assert.assertEquals("Timestamp doesnt match TIMESTAMP", match.getTimestamp(), (Long) match.getTimestampAsDate().toInstant().toEpochMilli());
        Assert.assertTrue("lane doesnt match LANE", Stream.of(match.getLane().get().getCodes()).anyMatch(s -> s.equalsIgnoreCase(match.getLaneId())));
        Assert.assertEquals("queue doesnt match QUEUE", match.getQueueId(), match.getQueue().get().getCode());
        Assert.assertEquals("role doesnt match ROLE", match.getRoleId(), match.getRole().get().getCode());
        Assert.assertEquals("season doesnt match SEASON", match.getSeasonId(), match.getSeason().get().getCode());
        Assert.assertEquals("region doesnt match REGION", match.getRegionId(), match.getRegion().get().getCode());
    };

    @Test
    public void doTest()
    {
        TestBase.builder.withURLData("{summonerId}", "22291359");
        TestBase.builder.withURLParameter("championIds", String.valueOf(Champion.LEONA.getId()));
        TestBase.builder.withURLParameter("rankedQueues", RankedQueue.RANKED_SOLO_5X5.getCode());
        TestBase.builder.withURLParameter("seasons", Season.SEASON_2014.getCode());

        final List<MatchReference> matches = ((MatchList) TestBase.builder.build()).getMatches();

        // I played 47 ranked solo games as leona in 2014
        Assert.assertEquals("Unexpected amount of games returned", matches.size(), 47);

        matches.forEach(this.doAssertions);
    }

    @Before
    public void initBeforeTest()
    {
        TestBase.builder.withEndpoint(URLEndpoint.MATCHLIST);
    }
}
