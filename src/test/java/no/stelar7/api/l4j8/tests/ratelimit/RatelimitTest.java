package no.stelar7.api.l4j8.tests.ratelimit;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.*;

public class RatelimitTest
{
    
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
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    public void testRateLimit() throws InterruptedException
    {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(() ->
                    {
                        for (int i2 = 0; i2 < 700; i2++)
                        {
                            Summoner ignore = l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
                        }
                    });
        
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }
    
    @Test
    public void testRateLimitWithSleep() throws InterruptedException
    {
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        TimeUnit.SECONDS.sleep(3);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        TimeUnit.SECONDS.sleep(7);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
        l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]);
    }
    
    @Test
    public void testRateLimitStatic()
    {
        for (int i = 0; i < 30; i++)
        {
            Realm ignore = l4j8.getStaticAPI().getRealm(Platform.EUW1);
            System.out.format("call no. %s Total time: %sms%n", i + 1, stopwatch.runtime(TimeUnit.MILLISECONDS));
        }
        
        Assert.assertTrue("limited?", 10 >= stopwatch.runtime(TimeUnit.SECONDS));
    }
}
