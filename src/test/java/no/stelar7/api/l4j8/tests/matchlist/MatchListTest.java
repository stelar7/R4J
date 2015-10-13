package no.stelar7.api.l4j8.tests.matchlist;

import java.util.List;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javafx.util.Pair;
import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.DataCall.ResponseType;
import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.URLEndpoint;
import no.stelar7.api.l4j8.basic.constants.Champion;
import no.stelar7.api.l4j8.basic.constants.RankedQueue;
import no.stelar7.api.l4j8.basic.constants.Season;
import no.stelar7.api.l4j8.pojo.matchlist.MatchReference;
import no.stelar7.api.l4j8.tests.SecretFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatchListTest
{

    DataCallBuilder builder = DataCall.builder();

    @Before
    public void init()
    {
        System.err.println("TESTING MATCH LIST");
        this.builder.withAPIKey(SecretFile.API_KEY);
        this.builder.withServer(Server.EUW);
        this.builder.withEndpoint(URLEndpoint.MATCHLIST);
    }

    @Test
    public void doTest()
    {
        this.builder.withURLData("{summonerId}", "22291359");
        this.builder.withURLParameter("championIds", String.valueOf(Champion.LEONA.getId()));
        this.builder.withURLParameter("rankedQueues", RankedQueue.RANKED_SOLO_5x5.getCode());
        this.builder.withURLParameter("seasons", Season.SEASON_2014.getCode());
        final Pair<ResponseType, Object> dataCall = this.builder.build();

        List<MatchReference> matches = (List<MatchReference>) dataCall.getValue();

        // I played 47 ranked solo games as leona in 2014
        Assert.assertEquals("Unexpected amount of games returned", matches.size(), 47);

        matches.forEach(doAssertions);
    }

    private Consumer<MatchReference> doAssertions = (MatchReference match) -> {
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
        Assert.assertNotNull("LANE is null", match.getLaneAsLane());
        Assert.assertNotNull("QUEUE is null", match.getQueueAsRankedQueue());
        Assert.assertNotNull("ROLE is null", match.getRoleAsRole());
        Assert.assertNotNull("SEASON is null", match.getSeasonAsSeason());
        Assert.assertNotNull("REGION is null", match.getRegionAsServer());

        Assert.assertEquals("Unexpected Champion Id", Champion.LEONA.getId(), match.getChampion());
        Assert.assertEquals("Unexpected Queue", RankedQueue.RANKED_SOLO_5x5.getCode(), match.getQueue());
        Assert.assertEquals("Timestamp doesnt match TIMESTAMP", match.getTimestamp(), (Long) match.getTimestampAsDate().toInstant().toEpochMilli());
        Assert.assertEquals("lane doesnt match LANE", match.getLane(), match.getLaneAsLane().getCode());
        Assert.assertEquals("queue doesnt match QUEUE", match.getQueue(), match.getQueueAsRankedQueue().getCode());
        Assert.assertEquals("role doesnt match ROLE", match.getRole(), match.getRoleAsRole().getCode());
        Assert.assertEquals("season doesnt match SEASON", match.getSeason(), match.getSeasonAsSeason().getCode());
        Assert.assertEquals("region doesnt match REGION", match.getRegion(), match.getRegionAsServer().getCode());
    };
}
