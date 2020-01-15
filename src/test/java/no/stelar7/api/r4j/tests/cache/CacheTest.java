package no.stelar7.api.r4j.tests.cache;

import ch.qos.logback.classic.*;
import no.stelar7.api.r4j.basic.cache.CacheLifetimeHint;
import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.match.lol.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;
import org.junit.rules.Stopwatch;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CacheTest
{
    
    final R4J                r4J      = new R4J(SecretFile.CREDS);
    final MySQLCacheProvider sqlCache = new MySQLCacheProvider("localhost", 3306, "root", "");
    final FileSystemCacheProvider fileCache   = new FileSystemCacheProvider(5);
    final MemoryCacheProvider     memCache    = new MemoryCacheProvider(5);
    final TieredCacheProvider     tieredCache = new TieredCacheProvider(memCache, fileCache, sqlCache);
    
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
    public void testSQLCache() throws InterruptedException
    {
        DataCall.setCacheProvider(sqlCache);
        
        Summoner s = Summoner.byName(Platform.EUW1, "stelar7");
        System.out.println(s);
        s = Summoner.byName(Platform.EUW1, "stelar7");
        System.out.println(s);
        
        //    doCacheStuff();
    }
    
    
    @Test
    public void testFileSystemCache() throws InterruptedException
    {
        DataCall.setCacheProvider(fileCache);
        
        doCacheStuff();
    }
    
    
    @Test
    @Ignore
    public void testTieredMemoryCache() throws InterruptedException
    {
        DataCall.setCacheProvider(tieredCache);
        
        doCacheStuff();
    }
    
    @Test
    public void testStaticDataCache()
    {
        
        DataCall.setCacheProvider(fileCache);
        r4J.getDDragonAPI().getChampions();
        r4J.getDDragonAPI().getChampions(null, null);
    }
    
    @Test
    public void testCacheStuff() throws InterruptedException
    {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        
        System.out.println("Fetching summoner for the first time");
        DataCall.setCacheProvider(fileCache);
        String id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        Thread.sleep(6000);
        System.out.println("Fetching summoner after cache timeout");
        new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        DataCall.getCacheProvider().clear(URLEndpoint.V4_SUMMONER_BY_NAME, Platform.EUW1, id);
        
        System.out.println("Fetching summoner after deleting entry");
        new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        CacheLifetimeHint defaults = CacheLifetimeHint.DEFAULTS;
        defaults.add(URLEndpoint.V4_SUMMONER_BY_NAME, TimeUnit.SECONDS.toMillis(1));
        DataCall.getCacheProvider().setTimeToLive(defaults);
        Thread.sleep(1000);
        
        System.out.println("Fetching summoner after setting lifetime to a lower limit");
        new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
    }
    
    @After
    public void clearCacheProvider()
    {
        DataCall.setCacheProvider(EmptyCacheProvider.INSTANCE);
    }
    
    
    private void doCacheStuff() throws InterruptedException
    {
        // DataCall.getCacheProvider().clear(URLEndpoint.V3_MATCH);
        String               id      = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner             s       = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        List<MatchReference> recents = new MatchListBuilder().withPlatform(Platform.EUW1).withAccountId(s.getAccountId()).get();
        
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
        DataCall.getCacheProvider().clear(URLEndpoint.V4_MATCH);
        
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        ref.getFullMatch();
        System.out.printf("1x url fetch time: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.printf("10x cache fetch time: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        System.out.println();
        
        System.out.println("Fetching 3 aditional matches");
        recents.get(1).getFullMatch();
        recents.get(2).getFullMatch();
        recents.get(3).getFullMatch();
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH));
        
        System.out.println("Waiting for cache timeout");
        TimeUnit.SECONDS.sleep(6);
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH));
        
        System.out.println("Re-fetching cached items");
        start = stopwatch.runtime(TimeUnit.NANOSECONDS);
        recents.get(0).getFullMatch();
        recents.get(1).getFullMatch();
        recents.get(2).getFullMatch();
        recents.get(3).getFullMatch();
        System.out.printf("4x fetches took: %dns%n", stopwatch.runtime(TimeUnit.NANOSECONDS) - start);
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize(URLEndpoint.V4_MATCH));
        System.out.println();
    }
    
}
