package no.stelar7.api.r4j.tests.cache;

import ch.qos.logback.classic.*;
import no.stelar7.api.r4j.basic.cache.impl.MongoDBCacheProvider;
import no.stelar7.api.r4j.basic.cache.CacheLifetimeHint;
import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.match.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;
import org.junit.rules.Stopwatch;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CacheTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    Supplier<MySQLCacheProvider>      sqlCache    = () -> new MySQLCacheProvider("localhost", 3306, "root", "");
    Supplier<MongoDBCacheProvider>    mongoCache  = () -> new MongoDBCacheProvider("mongodb://localhost:27017");
    Supplier<FileSystemCacheProvider> fileCache   = () -> new FileSystemCacheProvider(5);
    Supplier<MemoryCacheProvider>     memCache    = () -> new MemoryCacheProvider(5);
    Supplier<TieredCacheProvider>     tieredCache = () -> new TieredCacheProvider(memCache.get(), fileCache.get(), sqlCache.get());
    
    @Rule
    public Stopwatch stopwatch = new Stopwatch() {};
    
    @Test
    public void testMemoryCache() throws InterruptedException
    {
        DataCall.setCacheProvider(new MemoryCacheProvider(5));
        
        doCacheStuff();
    }
    
    @Test
    @Ignore
    public void testSQLCache()
    {
        DataCall.setCacheProvider(sqlCache.get());
        
        Summoner s = Summoner.byName(LeagueShard.EUW1, "stelar7");
        System.out.println(s);
        s = Summoner.byName(LeagueShard.EUW1, "stelar7");
        System.out.println(s);
        
        //    doCacheStuff();
    }
    
    @Test
    @Ignore
    public void testMongoCache() throws InterruptedException
    {
        DataCall.setCacheProvider(mongoCache.get());
        
        doCacheStuff();
    }
    
    
    @Test
    public void testFileSystemCache() throws InterruptedException
    {
        DataCall.setCacheProvider(fileCache.get());
        
        doCacheStuff();
    }
    
    
    @Test
    @Ignore
    public void testTieredMemoryCache() throws InterruptedException
    {
        DataCall.setCacheProvider(tieredCache.get());
        
        doCacheStuff();
    }
    
    @Test
    public void testStaticDataCache()
    {
        
        DataCall.setCacheProvider(fileCache.get());
        r4J.getDDragonAPI().getChampions();
        r4J.getDDragonAPI().getChampions(null, null);
    }
    
    @Test
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
    
    @After
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
        
        String               id      = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner             s       = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        List<MatchReference> recents = new MatchListBuilder().withPlatform(LeagueShard.EUW1).withAccountId(s.getAccountId()).get();
        
        if (recents.isEmpty())
        {
            return;
        }
        
        MatchReference ref = recents.get(0);
        
        System.out.println("Starting timer");
        
        long  start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        Match url   = ref.getFullMatch();
        System.out.printf("1x url fetch time: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        Match cached = ref.getFullMatch();
        System.out.printf("1x cache fetch time: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        
        if (!url.equals(cached))
        {
            throw new RuntimeException("CACHE IS BROKEN!!!!");
        }
        
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.printf("10x cache fetch time: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        System.out.println();
        
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        ref.getFullMatch();
        System.out.printf("1x cache fetch time: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        System.out.println();
        
        System.out.println("clearing cache");
        System.out.println();
        DataCall.getCacheProvider().clear(URLEndpoint.V4_MATCH, Collections.emptyMap());
        
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        ref.getFullMatch();
        System.out.printf("1x url fetch time: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.printf("10x cache fetch same item time: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        System.out.println();
        
        System.out.println("Fetching 3 aditional matches");
        recents.get(1).getFullMatch();
        recents.get(2).getFullMatch();
        recents.get(3).getFullMatch();
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH, Collections.emptyMap()));
        
        System.out.println("Waiting for cache timeout");
        TimeUnit.SECONDS.sleep(6);
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH, Collections.emptyMap()));
        
        System.out.println("Re-fetching cached items");
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        recents.get(0).getFullMatch();
        recents.get(1).getFullMatch();
        recents.get(2).getFullMatch();
        recents.get(3).getFullMatch();
        System.out.printf("4x fetches took: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH, Collections.emptyMap()));
        System.out.println();
    }
    
}
