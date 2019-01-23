package no.stelar7.api.l4j8.tests.match;

import no.stelar7.api.l4j8.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.utils.LazyList;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.match.*;
import no.stelar7.api.l4j8.impl.builders.spectator.SpectatorBuilder;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
            Match         detail   = reference.getFullMatch();
            MatchTimeline timeline = detail.getTimeline();
            System.out.println();
        }
    }
    
    
    @Test
    public void testMatchlistAll()
    {
        String               id   = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner             s    = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        MatchListBuilder     mlb  = new MatchListBuilder();
        List<MatchReference> list = mlb.withAccountId(s.getAccountId()).withPlatform(s.getPlatform()).get();
        Assert.assertTrue("api didnt load data?!", !list.isEmpty());
    }
    
    @Test
    public void testMatchlistSummoner() throws InterruptedException
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.INFO);
        LazyList<MatchReference> list = new SummonerBuilder().withPlatform(Platform.EUN1).withName("coust").get().getGames().getLazy();
        list.loadFully();
        
        List<MatchReference> filtered = list.stream()
                                            .filter(r -> r.getSeason() == SeasonType.SEASON_2018)
                                            .filter(r -> r.getQueue() == GameQueueType.TEAM_BUILDER_RANKED_SOLO)
                                            .collect(Collectors.toList());
        
        Thread.sleep(500);
        System.out.println("Total game count:" + list.size());
        System.out.println(filtered.size());
    }
    
    @Test
    public void testCallsCoUsT()
    {
        DataCall.setLogLevel(LogLevel.INFO);
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        LazyList<MatchReference> list = new SummonerBuilder().withPlatform(Platform.EUN1).withName("coust").get().getGames().getLazy();
        for (Iterator<MatchReference> matchReferenceIterator = list.iterator(); matchReferenceIterator.hasNext(); )
        {
            MatchReference matchReference = matchReferenceIterator.next();
            if (matchReferenceIterator.hasNext())
            {
                matchReferenceIterator.hasNext();
            }
        }
    }
    
    @Test
    public void testMatchlistError()
    {
        l4j8.getDDragonAPI().getRealm();
        
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        Platform region    = s.getPlatform();
        String   accountId = s.getAccountId();
        
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
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        List<MatchReference> list = new MatchListBuilder().withAccountId(s.getAccountId()).withPlatform(s.getPlatform()).getLazy();
        Assert.assertTrue("LazyList loaded data?!", list.isEmpty());
        list.get(51);
        Assert.assertTrue("LazyList didnt load data?!", !list.isEmpty());
    }
    
    @Test
    public void testMatchlistSeasons()
    {
        Set<SeasonType> sixList      = EnumSet.of(SeasonType.SEASON_2016);
        Set<SeasonType> preSevenList = EnumSet.of(SeasonType.PRE_SEASON_2017);
        Set<SeasonType> sevenList    = EnumSet.of(SeasonType.SEASON_2017);
        String          id           = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner        s            = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        MatchListBuilder mlb = new MatchListBuilder().withAccountId(s.getAccountId()).withPlatform(s.getPlatform());
        
        List<MatchReference> twosix   = mlb.withSeasons(sixList).get();
        List<MatchReference> preseven = mlb.withSeasons(preSevenList).get();
        List<MatchReference> twoseven = mlb.withSeasons(sevenList).get();
    }
    
    
    @Test
    public void testMatch()
    {
        String                   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner                 s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        LazyList<MatchReference> m  = s.getGames().getLazy();
        
        Match detail = new MatchBuilder().withId(m.get(0).getGameId()).withPlatform(m.get(0).getPlatform()).get();
        System.out.println();
        Match detail2 = new MatchBuilder().withId(m.get(1).getGameId()).withPlatform(m.get(1).getPlatform()).get();
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


