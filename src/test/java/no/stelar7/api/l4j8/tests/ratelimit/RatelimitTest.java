package no.stelar7.api.l4j8.tests.ratelimit;

import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
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
    
    @Before
    public void addDebug()
    {
        DataCall.setLogLevel(LogLevel.DEBUG);
    }
    
    @Test
    @Ignore
    public void testRateLimitThreaded()
    {
        try
        {
            ExecutorService pool = Executors.newFixedThreadPool(8);
            for (int i2 = 0; i2 < 130; i2++)
            {
                pool.execute(() -> new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withAccountId(Constants.TEST_ACCOUNT_IDS[0]).get());
            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    
    @Test
    @Ignore
    public void testRateLimit()
    {
        final L4J8 test = new L4J8(SecretFile.CREDS);
        for (int i2 = 0; i2 < 130; i2++)
        {
            new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withAccountId(Constants.TEST_ACCOUNT_IDS[0]).get();
        }
    }
    
    @Test
    @Ignore
    public void testRateLimitWithSleep() throws InterruptedException
    {
        new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withAccountId(Constants.TEST_ACCOUNT_IDS[0]).get();
        TimeUnit.SECONDS.sleep(10);
        new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[0]).withAccountId(Constants.TEST_ACCOUNT_IDS[0]).get();
    }
    
    @Test
    @Ignore
    public void testRateLimitStatic()
    {
        Platform plat = Platform.values()[1];
        for (int i = 0; i < 30; i++)
        {
            l4j8.getDDragonAPI().getRealm();
            l4j8.getDDragonAPI().getVersions();
            l4j8.getDDragonAPI().getLanguages();
            if (i % 9 == 0)
            {
                plat = Platform.values()[plat.ordinal() + 1];
            }
            System.out.format("call no. %s Total time: %sms%n", i + 1, stopwatch.runtime(TimeUnit.MILLISECONDS));
        }
    }
}
