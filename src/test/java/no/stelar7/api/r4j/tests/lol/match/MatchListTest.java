package no.stelar7.api.r4j.tests.lol.match;

import ch.qos.logback.classic.*;
import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
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
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.*;

public class MatchListTest
{
    private final Consumer<MatchReference> doAssertions = (final MatchReference match) ->
    {
        Assertions.assertNotNull(match.getLane(), "lane is null");
        Assertions.assertNotNull(match.getPlatform(), "platform is null");
        Assertions.assertNotNull(match.getQueue(), "queue is null");
        Assertions.assertNotNull(match.getRole(), "role is null");
        Assertions.assertNotNull(match.getSeason(), "season is null");
        
        Assertions.assertNotNull(match.getTimestampAsDate(), "TIMESTAMP is null");
        Assertions.assertNotNull(match.getLane(), "LANE is null");
        Assertions.assertNotNull(match.getQueue(), "QUEUE is null");
        Assertions.assertNotNull(match.getRole(), "ROLE is null");
        Assertions.assertNotNull(match.getSeason(), "SEASON is null");
        
        Assertions.assertNotNull(match.getLane(), "Lane is null");
        Assertions.assertNotNull(match.getQueue(), "queue is null");
        Assertions.assertNotNull(match.getRole(), "role is null");
        Assertions.assertNotNull(match.getSeason(), "season is null");
    };
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    
    @Test
    @Disabled
    public void testMatchAndMatchList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        MatchListBuilder builder = new MatchListBuilder();
        Summoner         sum     = Summoner.byName(LeagueShard.EUW1, "stelar7");
        
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
    @Disabled
    public void testMatchlistIterator()
    {
        Summoner      s     = Summoner.byName(LeagueShard.EUW1, "stelar7");
        MatchIterator games = s.getLeagueGames().getMatchIterator();
        for (Match m : games)
        {
            System.out.println(m.getGameQueueType().prettyName());
        }
    }
    
    
    @Test
    public void testMatchlistAll()
    {
        String               id   = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner             s    = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        MatchListBuilder     mlb  = new MatchListBuilder();
        List<MatchReference> list = mlb.withAccountId(s.getAccountId()).withPlatform(s.getPlatform()).get();
        Assertions.assertFalse(list.isEmpty(), "api didnt load data?!");
    }
    
    @Test
    public void testMatchlistTrump()
    {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger("no.stelar7.api.r4j.basic.calling.DataCallBuilder").setLevel(Level.OFF);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.ratelimiting.BurstRateLimiter").setLevel(Level.OFF);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider").setLevel(Level.OFF);
        
        Summoner             summoner = Summoner.byName(LeagueShard.EUW1, "stelar7");
        List<MatchReference> refs     = summoner.getLeagueGames().get();
        for (int i = 0; i < 20; i++)
        {
            Match       m           = refs.get(i).getFullMatch();
            Participant participant = m.getParticipant(summoner).get();
            boolean     didWinShort = m.didWin(participant);
            boolean     didWinLong  = participant.getStats().isWinner();
            assert (didWinShort == didWinLong);
        }
    }
    
    @Test
    @Disabled
    public void testMatchlistCrawler()
    {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger("no.stelar7.api.r4j.basic.calling.DataCallBuilder").setLevel(Level.OFF);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.ratelimiting.BurstRateLimiter").setLevel(Level.OFF);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider").setLevel(Level.OFF);
        
        Summoner s = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName("stelar7").get();
        
        SummonerCrawler crawler = new SummonerCrawler(s, 1000);
        crawler.crawlLeague();
        crawler.crawlGames();
        
        MatchListBuilder mlb = new MatchListBuilder();
        crawler.get().forEach(summoner -> {
            List<MatchReference> list = mlb.withAccountId(summoner.getAccountId()).withPlatform(summoner.getPlatform()).get();
            for (MatchReference ref : list)
            {
                System.out.println(ref.getFullMatch().getTeamStats(TeamType.BLUE).get(0).getWinString());
                //ref.getTimeline();
            }
        });
    }
    
    @Test
    @Disabled
    public void proveThatDartWasInting()
    {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger("no.stelar7.api.r4j.basic.calling.DataCallBuilder").setLevel(Level.INFO);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.ratelimiting.BurstRateLimiter").setLevel(Level.OFF);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider").setLevel(Level.OFF);
        
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        Summoner stelar = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName("stelar7").get();
        Summoner dart   = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName("FrankenDaemon").get();
        Summoner dart2  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName("Dr Rick Report").get();
        Summoner bipai  = new SummonerBuilder().withPlatform(LeagueShard.EUN1).withName("TLP Bipai").get();
        
        Summoner target = stelar;
        
        LazyList<MatchReference> dgames = target.getLeagueGames().getLazy();
        dgames.loadFully();
        
        int timeOffset = 15;
        
        dgames.stream()
              .filter(g -> g.getQueue() == GameQueueType.getFromId(420).get())
              .map(MatchReference::getFullMatch)
              .limit(100)
              .forEach(g -> {
                  MatchTimeline t                 = g.getTimeline();
                  Participant   targetParticipant = g.getParticipant(target).get();
                  long deathsPreTenMin = t.getFrames()
                                          .stream()
                                          .flatMap(f -> f.getEvents().stream())
                                          .filter(e -> e.getEventType() == EventType.CHAMPION_KILL)
                                          .filter(e -> e.getVictimId() == targetParticipant.getParticipantId())
                                          .filter(e -> e.getTimestamp() < timeOffset * 60 * 1000)
                                          .count();
            
                  long deathsTotal = targetParticipant.getStats().getDeaths();
            
                  StringBuilder output = new StringBuilder()
                          .append("GameID: ").append(g.getMatchId())
                          .append("; Played at: ").append(g.getMatchCreationAsDate())
                          .append("; Deaths before ").append(timeOffset).append("min: ").append(deathsPreTenMin)
                          .append("; Deaths total: ").append(deathsTotal)
                          .append("; Game duration: ").append(g.getMatchDuration())
                          .append("; Mode: ").append(g.getGameQueueType().commonName());
            
                  System.out.println(output.toString());
            
            
              });
    }
    
    @Test
    @Disabled
    public void testSomeStatsAboutInting()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        Summoner                 stelar = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName("FrankenDaemon").get();
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
    @Disabled
    public void testBloop()
    {
        Summoner                 summoner = Summoner.byName(LeagueShard.EUN1, "coust");
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
        LazyList<MatchReference> list = new SummonerBuilder().withPlatform(LeagueShard.EUN1).withName("coust").get().getLeagueGames().getLazy();
        Set<MatchReference>      ref  = new HashSet<>();
        list.forEach(reference -> System.out.print("a"));
    }
    
    @Test
    public void testLazyStream()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        LazyList<MatchReference> list = new SummonerBuilder().withPlatform(LeagueShard.EUN1).withName("coust").get().getLeagueGames().getLazy();
        Set<MatchReference>      ref  = new HashSet<>();
        //noinspection StreamToLoop,SimplifyStreamApiCallChains,SimplifyStreamApiCallChains
        list.stream().forEach(reference -> System.out.print("a"));
    }
    
    
    @Test
    public void testNoDuplicates()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        LazyList<MatchReference> list = new SummonerBuilder().withPlatform(LeagueShard.EUN1).withName("coust").get().getLeagueGames().getLazy();
        Set<MatchReference>      ref  = new HashSet<>();
        for (MatchReference reference : list)
        {
            if (!ref.add(reference))
            {
                Assertions.fail("Same game is collected when looping matchlist");
            }
        }
    }
    
    @Test
    public void testMatchlistError()
    {
        r4J.getDDragonAPI().getRealm();
        
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        LeagueShard region    = s.getPlatform();
        String      accountId = s.getAccountId();
        
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
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        List<MatchReference> list = new MatchListBuilder().withAccountId(s.getAccountId()).withPlatform(s.getPlatform()).getLazy();
        Assertions.assertTrue(list.isEmpty(), "LazyList loaded data?!");
        list.get(51);
        Assertions.assertFalse(list.isEmpty(), "LazyList didnt load data?!");
    }
    
    @Test
    public void testMatchlistSeasons()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        LazyList<MatchReference> lazy = new MatchListBuilder().withAccountId(s.getAccountId()).withPlatform(s.getPlatform()).getLazy();
        lazy.loadFully();
        
        List<MatchReference> season2016 = lazy.stream().filter(m -> m.getSeason() == SeasonType.SEASON_2016).collect(Collectors.toList());
    }
    
    
    @Test
    public void testMatch()
    {
        String                   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner                 s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        LazyList<MatchReference> m  = s.getLeagueGames().getLazy();
        
        Match detail  = new MatchBuilder().withId(m.get(0).getGameId()).withPlatform(m.get(0).getPlatform()).get();
        Match detail2 = new MatchBuilder().withId(m.get(1).getGameId()).withPlatform(m.get(1).getPlatform()).get();
    }
    
    @Test
    public void testMatchDart()
    {
        Match detail = new MatchBuilder().withId(3984304842L).withPlatform(LeagueShard.EUW1).get();
        System.out.println();
    }
    
    @Test
    public void testMatchDartCauseStelarDidntLikeOdyssey()
    {
        Match detail = new MatchBuilder().withId(3768089306L).withPlatform(LeagueShard.EUW1).get();
        System.out.println(detail.getParticipants().get(0).getSpell1().getApiName());
        System.out.println(detail.getParticipants().get(0).getSpell2().getApiName());
        System.out.println();
    }
    
    
    @Test
    public void testMatchTolkiIssue()
    {
        Match detail = Match.byId(LeagueShard.EUW1, 3961977905L);
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
    @Disabled
    public void testLazyList()
    {
        LazyList<MatchReference> refs = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName("stelar7").get().getLeagueGames().getLazy();
        Assertions.assertTrue(refs.isEmpty(), "LazyList is populated?");
        refs.loadFully();
        Assertions.assertFalse(refs.isEmpty(), "LazyList is not populated?");
    }
    
    @Test
    public void testTutorialModules()
    {
        DataCall.setCacheProvider(EmptyCacheProvider.INSTANCE);
        Match m = new MatchBuilder().withId(4002917402L).withPlatform(LeagueShard.EUW1).get();
        System.out.println();
    }
    
    @Test
    public void testLunatic()
    {
        Match m = new MatchBuilder().withPlatform(LeagueShard.NA1).withId(3042295790L).get();
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


