package no.stelar7.api.l4j8.tests.cache;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.flags.ChampDataFlags;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.match.MatchListBuilder;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.pojo.match.MatchReference;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;
import org.junit.rules.Stopwatch;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CacheTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Rule
    public Stopwatch stopwatch = new Stopwatch() {};
    
    @Test
    public void testMemoryCache() throws InterruptedException
    {
        DataCall.setCacheProvider(new MemoryCacheProvider(5));
        doCacheStuff();
    }
    
    
    @Test
    public void testFileSystemCache() throws InterruptedException
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider(null, -1));
        DataCall.setLogLevel(LogLevel.DEBUG);
        doCacheStuff();
    }
    
    @Test
    public void testStaticDataCache() throws InterruptedException
    {
        DataCall.setLogLevel(LogLevel.DEBUG);
        DataCall.setCacheProvider(new FileSystemCacheProvider(null, -1));
        l4j8.getStaticAPI().getChampions(Platform.NA1, EnumSet.allOf(ChampDataFlags.class), null, null);
        l4j8.getStaticAPI().getChampions(Platform.NA1, EnumSet.allOf(ChampDataFlags.class), null, null);
        l4j8.getStaticAPI().getChampions(Platform.EUW1, null, null, null);
    }
    
    @Test
    public void testCacheStuff() throws InterruptedException
    {
        DataCall.setLogLevel(LogLevel.DEBUG);
        DataCall.setCacheProvider(new FileSystemCacheProvider(null, -1));
        new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withSummonerId(Constants.TEST_SUMMONER_IDS[0]).get();
        new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withSummonerId(Constants.TEST_SUMMONER_IDS[0]).get();
        
        Thread.sleep(6000);
        
        new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withSummonerId(Constants.TEST_SUMMONER_IDS[0]).get();
        new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withSummonerId(Constants.TEST_SUMMONER_IDS[0]).get();
    }
    
    @Test
    public void testTieredMemoryCache() throws InterruptedException
    {
        DataCall.setLogLevel(LogLevel.DEBUG);
        DataCall.setCacheProvider(new TieredCacheProvider(new MemoryCacheProvider(1), new FileSystemCacheProvider(null, -1)));
        doCacheStuff();
    }
    
    @After
    public void clearCacheProvider()
    {
        DataCall.setCacheProvider(CacheProvider.EmptyProvider.INSTANCE);
    }
    
    
    private void doCacheStuff() throws InterruptedException
    {
        DataCall.setLogLevel(LogLevel.DEBUG);
        List<MatchReference> recents = new MatchListBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withAccountId(Constants.TEST_ACCOUNT_IDS[0]).get();
        
        if (recents.isEmpty())
        {
            return;
        }
        
        MatchReference ref = recents.get(0);
        
        System.out.println("Starting timer");
        
        long start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        ref.getFullMatch();
        System.out.printf("1x url fetch time: %dµs%n", stopwatch.runtime(TimeUnit.MICROSECONDS) - start);
        
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        ref.getFullMatch();
        System.out.printf("1x cache fetch time: %dµs%n", stopwatch.runtime(TimeUnit.MICROSECONDS) - start);
        
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.printf("10x cache fetch time: %dµs%n", stopwatch.runtime(TimeUnit.MICROSECONDS) - start);
        System.out.println();
        
        System.out.println("clearing cache");
        System.out.println();
        //DataCall.getCacheProvider().clear(URLEndpoint.V3_MATCH);
        
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        ref.getFullMatch();
        System.out.printf("1x url fetch time: %dµs%n", stopwatch.runtime(TimeUnit.MICROSECONDS) - start);
        
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.printf("10x cache fetch time: %dµs%n", stopwatch.runtime(TimeUnit.MICROSECONDS) - start);
        System.out.println();
        
        System.out.println("Fetching 3 aditional matches");
        recents.get(1).getFullMatch();
        recents.get(2).getFullMatch();
        recents.get(3).getFullMatch();
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize());
        
        System.out.println("Waiting for cache timeout");
        TimeUnit.SECONDS.sleep(5);
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize());
        
        System.out.println("Re-fetching cached items");
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        recents.get(0).getFullMatch();
        recents.get(1).getFullMatch();
        recents.get(2).getFullMatch();
        recents.get(3).getFullMatch();
        System.out.printf("4x fetches took: %dµs%n", stopwatch.runtime(TimeUnit.MICROSECONDS) - start);
        
        System.out.printf("Cache size: %d%n", DataCall.getCacheProvider().getSize());
        System.out.println();
    }
    
}
