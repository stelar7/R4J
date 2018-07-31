package no.stelar7.api.l4j8.tests.match;

import no.stelar7.api.l4j8.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.utils.LazyList;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.match.*;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
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
    
    @Test
    @Ignore
    public void testMatchAndMatchList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.INFO);
        Set<GameQueueType> queue      = null;//EnumSet.of(GameQueueType.TEAM_BUILDER_RANKED_SOLO);
        Set<SeasonType>    season     = null;//EnumSet.of(SeasonType.SEASON_2018);
        Set<Integer>       champ      = null;//new HashSet<>(Collections.singletonList(40));
        Long               beginTime  = null;//LocalDateTime.now().withHour(0).toEpochSecond(ZoneOffset.UTC) * 1000;//1481108400000L; // start of season 2017
        Long               endTime    = null;//LocalDateTime.now().withHour(0).plusWeeks(1).toEpochSecond(ZoneOffset.UTC) * 1000; // 604800000 is one week in ms
        Long               beginIndex = null;//0;
        Long               endIndex   = null;//100;
        
        MatchListBuilder builder = new MatchListBuilder();
        Summoner         sum     = new SummonerBuilder().withPlatform(Platform.EUW1).withName("stelar7").get();
        builder = builder.withPlatform(sum.getPlatform()).withAccountId(sum.getAccountId());
        builder = builder.withBeginTime(beginTime).withEndTime(endTime);
        builder = builder.withBeginIndex(beginIndex).withEndIndex(endIndex);
        builder = builder.withQueues(queue).withSeasons(season).withChampions(champ);
        
        LazyList<MatchReference> all = builder.getLazy();
        
        MatchBuilder    mb = new MatchBuilder();
        TimelineBuilder tb = new TimelineBuilder();
        
        for (MatchReference reference : all)
        {
            Match detail = reference.getFullMatch();
        }
    }
    
    
    @Test
    public void testMatchlistAll()
    {
        MatchListBuilder mlb = new MatchListBuilder();
        for (int i = 0; i < Constants.TEST_ACCOUNT_IDS.length; i++)
        {
            List<MatchReference> list = mlb.withAccountId(Constants.TEST_ACCOUNT_IDS[i]).withPlatform(Constants.TEST_PLATFORM[i]).get();
            Assert.assertTrue("api didnt load data?!", !list.isEmpty());
        }
    }
    
    @Test
    public void testMatchlistError()
    {
        Platform region    = Constants.TEST_PLATFORM[0];
        long     accountId = Constants.TEST_ACCOUNT_IDS[0];
        
        Set<GameQueueType> queueTypes = new HashSet<>();
        queueTypes.add(GameQueueType.TEAM_BUILDER_RANKED_SOLO);
        
        DataCall.setLogLevel(LogLevel.DEBUG);
        List<MatchReference> refs = l4j8.getMatchAPI().getMatchList(region, accountId, null, null, null, null, queueTypes, null, null);
        System.out.println(refs);
    }
    
    
    @Test
    public void testMatchlistAllLazy()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.getCacheProvider().clear(URLEndpoint.V3_MATCHLIST);
        
        for (int i = 0; i < Constants.TEST_ACCOUNT_IDS.length; i++)
        {
            List<MatchReference> list = new MatchListBuilder().withAccountId(Constants.TEST_ACCOUNT_IDS[i]).withPlatform(Constants.TEST_PLATFORM[i]).getLazy();
            Assert.assertTrue("LazyList loaded data?!", list.isEmpty());
            list.get(51);
            Assert.assertTrue("LazyList didnt load data?!", !list.isEmpty());
        }
    }
    
    @Test
    public void testMatchlistSeasons()
    {
        Set<SeasonType> sixList      = EnumSet.of(SeasonType.SEASON_2016);
        Set<SeasonType> preSevenList = EnumSet.of(SeasonType.PRE_SEASON_2017);
        Set<SeasonType> sevenList    = EnumSet.of(SeasonType.SEASON_2017);
        
        MatchListBuilder mlb = new MatchListBuilder().withAccountId(Constants.TEST_ACCOUNT_IDS[0]).withPlatform(Constants.TEST_PLATFORM[0]);
        
        List<MatchReference> twosix   = mlb.withSeasons(sixList).get();
        List<MatchReference> preseven = mlb.withSeasons(preSevenList).get();
        List<MatchReference> twoseven = mlb.withSeasons(sevenList).get();
    }
    
    
    @Test
    public void testMatch()
    {
        Match detail = new MatchBuilder().withId(Constants.TEST_MATCH_ID[0]).withPlatform(Constants.TEST_PLATFORM[0]).get();
        System.out.println();
        Match detail2 = new MatchBuilder().withId(Constants.TEST_MATCH_ID[1]).withPlatform(Constants.TEST_PLATFORM[1]).get();
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testLazyList()
    {
        LazyList<MatchReference> refs = new SummonerBuilder().withPlatform(Platform.EUW1).withName("stelar7").get().getGames().getLazy();
        Assert.assertTrue("LazyList is populated?", refs.isEmpty());
        refs.loadFully();
        Assert.assertTrue("LazyList is not populated?", !refs.isEmpty());
    }
    
}


