package no.stelar7.api.l4j8.tests.cache;

import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.match.MatchReference;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

public class CacheTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    public void doMemoryTest()
    {
        DataCall.setCacheProvider(CacheProvider.MEMORY);
        MatchReference ref = l4j8.getMatchAPI().getRecentMatches(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]).get(0);
    
        System.out.println("Starting timer");
        
        long start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        ref.getFullMatch();
        System.out.println("1x url fetch time: " + (stopwatch.runtime(TimeUnit.MICROSECONDS) - start) + "µs");
        System.out.println();
        
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        ref.getFullMatch();
        System.out.println("1x memory fetch time: " + (stopwatch.runtime(TimeUnit.MICROSECONDS) - start) + "µs");
        System.out.println();
        
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.println("10x memory fetch time: " + (stopwatch.runtime(TimeUnit.MICROSECONDS) - start) + "µs");
        System.out.println();
    
        System.out.println("clearing cache");
        System.out.println();
        DataCall.getCacheProvider().clear(URLEndpoint.V3_MATCH);
    
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.println("10x memory fetch time: " + (stopwatch.runtime(TimeUnit.MICROSECONDS) - start) + "µs");
        System.out.println();
        
        
        
        DataCall.setCacheProvider(CacheProvider.EMPTY);
    }
    
    @Test
    public void doSQLTest()
    {
        DataCall.setCacheProvider(MySQLCache.create("", 3306, "l4j8test", "root", ""));
        MatchReference ref = l4j8.getMatchAPI().getRecentMatches(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]).get(0);
    
        System.out.println("Starting timer");
        
        long start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        ref.getFullMatch();
        System.out.println("1x url fetch time: " + (stopwatch.runtime(TimeUnit.MICROSECONDS) - start) + "µs");
        System.out.println();
        
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        ref.getFullMatch();
        System.out.println("1x memory fetch time: " + (stopwatch.runtime(TimeUnit.MICROSECONDS) - start) + "µs");
        System.out.println();
        
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.println("10x memory fetch time: " + (stopwatch.runtime(TimeUnit.MILLISECONDS) - start) + "µs");
        System.out.println();
    
    
        System.out.println("clearing cache");
        System.out.println();
        DataCall.getCacheProvider().clear(URLEndpoint.V3_MATCH);
    
        start = stopwatch.runtime(TimeUnit.MICROSECONDS);
        for (int i = 0; i < 10; i++)
        {
            ref.getFullMatch();
        }
        System.out.println("10x memory fetch time: " + (stopwatch.runtime(TimeUnit.MICROSECONDS) - start) + "µs");
        System.out.println();
    
    
        DataCall.setCacheProvider(CacheProvider.EMPTY);
    }
    
    
    @After
    public void clearCacheProvider()
    {
        System.out.println("emptying cache provider");
        DataCall.setCacheProvider(CacheProvider.EMPTY);
    }
    
    private static void logInfo(Description description, String status, long nanos)
    {
        String testName = description.getMethodName();
        System.out.println(String.format("Test %s %s, spent %d milliseconds", testName, status, TimeUnit.NANOSECONDS.toMillis(nanos)));
    }
    
    @Rule
    public Stopwatch stopwatch = new Stopwatch()
    {
        @Override
        protected void succeeded(long nanos, Description description)
        {
            logInfo(description, "succeeded", nanos);
        }
        
        @Override
        protected void failed(long nanos, Throwable e, Description description)
        {
            logInfo(description, "failed", nanos);
        }
        
        @Override
        protected void skipped(long nanos, AssumptionViolatedException e, Description description)
        {
            logInfo(description, "skipped", nanos);
        }
        
        @Override
        protected void finished(long nanos, Description description)
        {
            logInfo(description, "finished", nanos);
        }
    };
    
}
