package no.stelar7.api.r4j.tests.lol.match;

import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.types.*;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.match.*;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import no.stelar7.api.r4j.pojo.lol.match.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.*;

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
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    
    @Test
    @Ignore
    public void testMatchAndMatchList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        MatchListBuilder builder = new MatchListBuilder();
        Summoner         sum     = Summoner.byName(Platform.EUW1, "stelar7");
        
        LazyList<MatchReference> all = sum.getLeagueGames().getLazy();
        all.loadFully();
        
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
    @Ignore
    public void testMatchlistIterator()
    {
        Summoner      s     = Summoner.byName(Platform.EUW1, "stelar7");
        MatchIterator games = s.getLeagueGames().getMatchIterator();
        for (Match m : games)
        {
            System.out.println(m.getGameQueueType().prettyName());
        }
    }
    
    
    @Test
    public void testMatchlistAll()
    {
        String               id   = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner             s    = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        MatchListBuilder     mlb  = new MatchListBuilder();
        List<MatchReference> list = mlb.withAccountId(s.getAccountId()).withPlatform(s.getPlatform()).get();
        Assert.assertFalse("api didnt load data?!", list.isEmpty());
    }
    
    @Test
    @Ignore
    public void testMatchlistCrawler()
    {
        Summoner s = new SummonerBuilder().withPlatform(Platform.EUW1).withName("stelar7").get();
        
        SummonerCrawler crawler = new SummonerCrawler(s);
        crawler.crawlLeague();
        crawler.crawlGames();
        
        MatchListBuilder mlb = new MatchListBuilder();
        crawler.get().forEach(summoner -> {
            List<MatchReference> list = mlb.withAccountId(summoner.getAccountId()).withPlatform(summoner.getPlatform()).get();
            for (MatchReference ref : list)
            {
                ref.getFullMatch();
                ref.getTimeline();
            }
        });
    }
    
    @Test
    @Ignore
    public void proveThatDartWasInting()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        Summoner stelar = new SummonerBuilder().withPlatform(Platform.EUW1).withName("stelar7").get();
        Summoner dart   = new SummonerBuilder().withPlatform(Platform.EUW1).withName("FrankenDaemon").get();
        
        LazyList<MatchReference> dgames = dart.getLeagueGames().getLazy();
        dgames.loadFully();
        
        int timeOffset = 15;
        
        dgames.stream()
              .map(MatchReference::getFullMatch)
              .filter(g -> g.getGameQueueType() == GameQueueType.getFromId(420).get())
              .limit(100)
              .forEach(g -> {
                  MatchTimeline t           = g.getTimeline();
                  Participant   participant = g.getParticipant(dart).get();
                  long deathsPreTenMin = t.getFrames()
                                          .stream()
                                          .flatMap(f -> f.getEvents().stream())
                                          .filter(e -> e.getEventType() == EventType.CHAMPION_KILL)
                                          .filter(e -> e.getVictimId() == participant.getParticipantId())
                                          .filter(e -> e.getTimestamp() < timeOffset * 60 * 1000)
                                          .count();
            
                  long deathsTotal = participant.getStats().getDeaths();
            
                  System.out.println("GameID: " + g.getMatchId() + "; Played at: " + g.getMatchCreationAsDate() + "; Deaths before " + timeOffset + "min: " + deathsPreTenMin + "; Deaths total: " + deathsTotal + "; Game duration: " + g.getMatchDuration());
            
            
              });
    }
    
    @Test
    @Ignore
    public void testSomeStatsAboutInting()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        Summoner                 stelar = new SummonerBuilder().withPlatform(Platform.EUW1).withName("FrankenDaemon").get();
        LazyList<MatchReference> dgames = stelar.getLeagueGames().getLazy();
        dgames.loadFully();
        
        int timeOffset = 15;
        
        StaticChampion champion = DDragonAPI.getInstance()
                                            .getChampions()
                                            .values()
                                            .stream()
                                            .filter(c -> c.getName().equalsIgnoreCase("Master Yi"))
                                            .findFirst()
                                            .get();
        
        List<Match> rankedGames = dgames.stream()
                                        .map(MatchReference::getFullMatch)
                                        .filter(g -> g.getGameQueueType() == GameQueueType.getFromId(420).get())
                                        .collect(Collectors.toList());
        
        AtomicInteger intCounter = new AtomicInteger();
        
        rankedGames.forEach(g -> {
            Participant myPid = g.getParticipant(stelar).get();
            
            // ignore games where the champ is not on my team
            boolean hasChampion = g.getParticipants(myPid.getTeam()).stream().anyMatch(p -> p.getChampionId() == champion.getId());
            if (!hasChampion)
            {
                return;
            }
            
            MatchTimeline t = g.getTimeline();
            long deathsPreTenMin = t.getFrames()
                                    .stream()
                                    .flatMap(f -> f.getEvents().stream())
                                    .filter(e -> e.getTimestamp() < timeOffset * 60 * 1000)
                                    .filter(e -> e.getEventType() == EventType.CHAMPION_KILL)
                                    .map(e -> g.getParticipant(e.getVictimId()).get())
                                    .filter(e -> e.getTeam() == myPid.getTeam())
                                    .filter(e -> e.getChampionId() == champion.getId())
                                    .count();
            
            if (deathsPreTenMin >= 5)
            {
                long deathsTotal = myPid.getStats().getDeaths();
                System.out.println("GameID: " + g.getMatchId() + "; Played at: " + g.getMatchCreationAsDate() + "; Deaths before " + timeOffset + "min: " + deathsPreTenMin + "; Deaths total: " + deathsTotal);
                intCounter.getAndIncrement();
            }
        });
        System.out.println(intCounter.get() + "/" + rankedGames.size());
    }
    
    @Test
    public void testBloop()
    {
        Summoner                 summoner = Summoner.byName(Platform.EUN1, "coust");
        LazyList<MatchReference> matches  = summoner.getLeagueGames().getLazy();
        
        // ensure that the list atleast has 100 elements
        int historyLength = 100;
        matches.get(historyLength);
        
        int inted = matches
                .stream()
                .limit(historyLength)
                .map(MatchReference::getFullMatch)
                .map(m -> m.getParticipant(summoner.getSummonerId()))
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .map(Participant::getStats)
                .mapToInt(stat -> {
                    float ka = stat.getKills() + stat.getAssists();
                    float d  = stat.getDeaths();
                    
                    float kda = d > 0 ? ka / d : 1_000_000f;
                    return (kda > 1) ? 0 : 1;
                })
                .sum();
        
        System.out.println("Inted in " + inted + " games of 100 checked");
    }
    
    @Test
    public void testLazyForEach()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        LazyList<MatchReference> list = new SummonerBuilder().withPlatform(Platform.EUN1).withName("coust").get().getLeagueGames().getLazy();
        Set<MatchReference>      ref  = new HashSet<>();
        list.forEach(reference -> System.out.print("a"));
    }
    
    @Test
    public void testLazyStream()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        LazyList<MatchReference> list = new SummonerBuilder().withPlatform(Platform.EUN1).withName("coust").get().getLeagueGames().getLazy();
        Set<MatchReference>      ref  = new HashSet<>();
        list.stream().peek(reference -> System.out.print("a"));
    }
    
    
    @Test
    public void testNoDuplicates()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        LazyList<MatchReference> list = new SummonerBuilder().withPlatform(Platform.EUN1).withName("coust").get().getLeagueGames().getLazy();
        Set<MatchReference>      ref  = new HashSet<>();
        for (MatchReference reference : list)
        {
            if (!ref.add(reference))
            {
                Assert.fail("Same game is collected when looping matchlist");
            }
        }
    }
    
    @Test
    public void testMatchlistError()
    {
        r4J.getDDragonAPI().getRealm();
        
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        Platform region    = s.getPlatform();
        String   accountId = s.getAccountId();
        
        Set<GameQueueType> queueTypes = new HashSet<>();
        queueTypes.add(GameQueueType.TEAM_BUILDER_RANKED_SOLO);
        
        LazyList<MatchReference> refs = r4J.getLoLAPI().getMatchAPI().getMatchList(region, accountId);
        System.out.println(refs);
    }
    
    
    @Test
    public void testMatchlistAllLazy()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.getCacheProvider().clear(URLEndpoint.V4_MATCHLIST, Collections.emptyMap());
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        List<MatchReference> list = new MatchListBuilder().withAccountId(s.getAccountId()).withPlatform(s.getPlatform()).getLazy();
        Assert.assertTrue("LazyList loaded data?!", list.isEmpty());
        list.get(51);
        Assert.assertFalse("LazyList didnt load data?!", list.isEmpty());
    }
    
    @Test
    public void testMatchlistSeasons()
    {
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        LazyList<MatchReference> lazy = new MatchListBuilder().withAccountId(s.getAccountId()).withPlatform(s.getPlatform()).getLazy();
        lazy.loadFully();
        
        List<MatchReference> season2016 = lazy.stream().filter(m -> m.getSeason() == SeasonType.SEASON_2016).collect(Collectors.toList());
    }
    
    
    @Test
    public void testMatch()
    {
        String                   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner                 s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        LazyList<MatchReference> m  = s.getLeagueGames().getLazy();
        
        Match detail  = new MatchBuilder().withId(m.get(0).getGameId()).withPlatform(m.get(0).getPlatform()).get();
        Match detail2 = new MatchBuilder().withId(m.get(1).getGameId()).withPlatform(m.get(1).getPlatform()).get();
    }
    
    @Test
    public void testMatchDart()
    {
        Match detail = new MatchBuilder().withId(3984304842L).withPlatform(Platform.EUW1).get();
        System.out.println();
    }
    
    @Test
    public void testMatchDartCauseStelarDidntLikeOdyssey()
    {
        Match detail = new MatchBuilder().withId(3768089306L).withPlatform(Platform.EUW1).get();
        System.out.println(detail.getParticipants().get(0).getSpell1().getApiName());
        System.out.println(detail.getParticipants().get(0).getSpell2().getApiName());
        System.out.println();
    }
    
    
    @Test
    public void testMatchTolkiIssue()
    {
        Match detail = Match.byId(Platform.EUW1, 3961977905L);
        detail.getParticipantIdentities().forEach(p -> {
            Summoner olda = new SummonerBuilder().withPlatform(p.getPlatform()).withAccountId(p.getAccountId()).get();
            Summoner newa = new SummonerBuilder().withPlatform(p.getCurrentPlatform()).withAccountId(p.getCurrentAccountId()).get();
            
            System.out.println(newa.getName() + ":");
            System.out.println(newa.getPlatform() + ":" + newa.getName());
            System.out.println(newa.getPUUID());
            System.out.println(olda.getPlatform() + ":" + olda.getName());
            System.out.println(olda.getPUUID());
            
            
        });
    }
    
    @Test
    @Ignore
    public void testLazyList()
    {
        LazyList<MatchReference> refs = new SummonerBuilder().withPlatform(Platform.EUW1).withName("stelar7").get().getLeagueGames().getLazy();
        Assert.assertTrue("LazyList is populated?", refs.isEmpty());
        refs.loadFully();
        Assert.assertFalse("LazyList is not populated?", refs.isEmpty());
    }
    
    @Test
    public void testTutorialModules()
    {
        DataCall.setCacheProvider(EmptyCacheProvider.INSTANCE);
        Match m = new MatchBuilder().withId(4002917402L).withPlatform(Platform.EUW1).get();
        System.out.println();
    }
    
    @Test
    public void testLunatic()
    {
        Match m = new MatchBuilder().withPlatform(Platform.NA1).withId(3042295790L).get();
        System.out.println(m.getParticipantIdentities().get(2));
        
        m.getTimeline().getFrames().stream()
         .filter(f -> f.getEvents().stream().anyMatch(e -> e.getParticipantId() == 3))
         .forEach(f -> f.getEvents().stream()
                        .filter(e -> e.getParticipantId() == 3)
                        .filter(e -> e.getEventType() == EventType.ITEM_PURCHASED || e.getEventType() == EventType.ITEM_UNDO)
                        .forEach(e -> {
                            String before = DDragonAPI.getInstance().getItem(e.getItemBefore()) != null ? DDragonAPI.getInstance().getItem(e.getItemBefore()).getName() : "";
                            String after  = DDragonAPI.getInstance().getItem(e.getItemAfter()) != null ? DDragonAPI.getInstance().getItem(e.getItemAfter()).getName() : "";
                            String item   = DDragonAPI.getInstance().getItem(e.getItemId()) != null ? DDragonAPI.getInstance().getItem(e.getItemId()).getName() : "";
                            String output = String.format("%14s %8s { before: %s after: %s itemid: %s }", e.getEventType(), e.getTimestamp(), before, after, item);
                            System.out.println(output);
                        }));
    }
    
}


