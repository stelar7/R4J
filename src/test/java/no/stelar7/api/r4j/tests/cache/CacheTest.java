package no.stelar7.api.r4j.tests.cache;

import ch.qos.logback.classic.*;
import no.stelar7.api.r4j.basic.cache.CacheLifetimeHint;
import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.val.GameQueueType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.val.matchlist.*;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CacheTest
{
    final R4J r4J = new R4J(SecretFile.CREDS);
    Supplier<MySQLCacheProvider>      sqlCache    = () -> new MySQLCacheProvider("localhost", 3306, "homestead", "secret");
    Supplier<MongoDBCacheProvider>    mongoCache  = () -> new MongoDBCacheProvider("mongodb://localhost:27017");
    Supplier<FileSystemCacheProvider> fileCache   = () -> new FileSystemCacheProvider(5);
    Supplier<MemoryCacheProvider>     memCache    = () -> new MemoryCacheProvider(5);
    Supplier<TieredCacheProvider>     tieredCache = () -> new TieredCacheProvider(memCache.get(), fileCache.get(), sqlCache.get());
    
    @Test
    @Disabled
    public void testMemoryCache() throws InterruptedException
    {
        DataCall.setCacheProvider(new MemoryCacheProvider(5));
        
        doCacheStuff();
    }
    
    @Test
    @Disabled
    public void testSQLCache()
    {
        DataCall.setCacheProvider(sqlCache.get());
        
        RecentMatchList list = r4J.getVALAPI().getMatchAPI().getRecentMatches(ValorantShard.EU, GameQueueType.COMPETITIVE);
        for (String matchId : list.getMatchIds())
        {
            r4J.getVALAPI().getMatchAPI().getMatch(ValorantShard.EU, matchId);
        }
    
        /*
            Summoner s = Summoner.byName(LeagueShard.EUW1, "stelar7");
            System.out.println(s);
            s = Summoner.byName(LeagueShard.EUW1, "stelar7");
            System.out.println(s);
        */
        
        //    doCacheStuff();
    }
    
    @Test
    @Disabled
    public void testMongoCache() throws InterruptedException
    {
        DataCall.setCacheProvider(mongoCache.get());
        
        doCacheStuff();
    }
    
    
    @Test
    @Disabled
    public void testFileSystemCache() throws InterruptedException
    {
        DataCall.setCacheProvider(fileCache.get());
        
        doCacheStuff();
    }
    
    
    @Test
    @Disabled
    public void testTieredMemoryCache() throws InterruptedException
    {
        DataCall.setCacheProvider(tieredCache.get());
        
        doCacheStuff();
    }
    
    @Test
    @Disabled
    public void testStaticDataCache()
    {
        
        DataCall.setCacheProvider(fileCache.get());
        r4J.getDDragonAPI().getChampions();
        r4J.getDDragonAPI().getChampions(null, null);
    }
    
    @Test
    @Disabled
    public void testCacheStuff() throws InterruptedException
    {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        
        System.out.println("Fetching summoner for the first time");
        DataCall.setCacheProvider(fileCache.get());
        String id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        Thread.sleep(6000);
        System.out.println("Fetching summoner after cache timeout");
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", LeagueShard.EUW1);
        data.put("name", id);
        
        DataCall.getCacheProvider().clear(URLEndpoint.V4_SUMMONER_BY_NAME, data);
        
        System.out.println("Fetching summoner after deleting entry");
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        CacheLifetimeHint defaults = CacheLifetimeHint.DEFAULTS;
        defaults.add(URLEndpoint.V4_SUMMONER_BY_NAME, TimeUnit.SECONDS.toMillis(1));
        DataCall.getCacheProvider().setTimeToLive(defaults);
        Thread.sleep(1000);
        
        System.out.println("Fetching summoner after setting lifetime to a lower limit");
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
    }
    
    @AfterEach
    public void clearCacheProvider()
    {
        DataCall.setCacheProvider(EmptyCacheProvider.INSTANCE);
    }
    
    
    private void doCacheStuff() throws InterruptedException
    {
        // DataCall.getCacheProvider().clear(URLEndpoint.V3_MATCH);
        
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger("no.stelar7.api.r4j.basic.calling.DataCallBuilder").setLevel(Level.INFO);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.ratelimiting.BurstRateLimiter").setLevel(Level.OFF);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider").setLevel(Level.OFF);
        
        System.out.println("Fetching a random summoner and their match list");
        
        String       id      = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner     s       = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        List<String> recents = new MatchListBuilder().withPlatform(LeagueShard.EUW1).withPuuid(s.getPUUID()).get();
        
        if (recents.isEmpty())
        {
            return;
        }
        
        String ref = recents.get(0);
        
        System.out.println("Starting timer");
        
        long  start = System.currentTimeMillis();
        LOLMatch url   = LOLMatch.get(LeagueShard.EUW1, ref);
        System.out.printf("1x url fetch time: %dns%n", System.currentTimeMillis() - start);
        
        start = System.currentTimeMillis();
        LOLMatch cached = LOLMatch.get(LeagueShard.EUW1, ref);
        System.out.printf("1x cache fetch time: %dns%n", System.currentTimeMillis() - start);
        
        if (!url.equals(cached))
        {
            throw new RuntimeException("CACHE IS BROKEN!!!!");
        }
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++)
        {
            LOLMatch.get(LeagueShard.EUW1, ref);
        }
        System.out.printf("10x cache fetch time: %dns%n", System.currentTimeMillis() - start);
        System.out.println();
        
        start = System.currentTimeMillis();
        LOLMatch.get(LeagueShard.EUW1, ref);
        System.out.printf("1x cache fetch time: %dns%n", System.currentTimeMillis() - start);
        System.out.println();
        
        System.out.println("clearing cache");
        System.out.println();
        DataCall.getCacheProvider().clear(URLEndpoint.V5_MATCH, Collections.emptyMap());
        
        start = System.currentTimeMillis();
        LOLMatch.get(LeagueShard.EUW1, ref);
        System.out.printf("1x url fetch time: %dns%n", System.currentTimeMillis() - start);
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++)
        {
            LOLMatch.get(LeagueShard.EUW1, ref);
        }
        System.out.printf("10x cache fetch same item time: %dns%n", System.currentTimeMillis() - start);
        System.out.println();
        
        System.out.println("Fetching 3 aditional matches");
        LOLMatch.get(LeagueShard.EUW1, recents.get(1));
        LOLMatch.get(LeagueShard.EUW1, recents.get(2));
        LOLMatch.get(LeagueShard.EUW1, recents.get(3));
    
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V5_MATCH, Collections.emptyMap()));
        
        System.out.println("Waiting for cache timeout");
        TimeUnit.SECONDS.sleep(6);
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V5_MATCH, Collections.emptyMap()));
        
        System.out.println("Re-fetching cached items");
        start = System.currentTimeMillis();
        LOLMatch.get(LeagueShard.EUW1, recents.get(0));
        LOLMatch.get(LeagueShard.EUW1, recents.get(1));
        LOLMatch.get(LeagueShard.EUW1, recents.get(2));
        LOLMatch.get(LeagueShard.EUW1, recents.get(3));
        System.out.printf("4x fetches took: %dns%n", System.currentTimeMillis() - start);
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V5_MATCH, Collections.emptyMap()));
        System.out.println();
    }
    
}
