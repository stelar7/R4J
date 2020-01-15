package no.stelar7.api.r4j.tests.ratelimit;

import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
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
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    @Test
    @Ignore
    public void testRateLimitThreaded()
    {
        try
        {
            String          name   = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
            Summoner        s    = new SummonerBuilder().withPlatform(Platform.EUW1).withName(name).get();
            ExecutorService pool = Executors.newFixedThreadPool(8);
            for (int i2 = 0; i2 < 130; i2++)
            {
                pool.execute(() -> new SummonerBuilder().withPlatform(Platform.EUW1).withSummonerId(s.getSummonerId()).get());
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
        final R4J test = new R4J(SecretFile.CREDS);
        String    id   = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner   s    = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        for (int i2 = 0; i2 < 130; i2++)
        {
            new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        }
    }
    
    @Test
    @Ignore
    public void testRateLimitWithSleep() throws InterruptedException
    {
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withName(id).get();
        
        new SummonerBuilder().withPlatform(Platform.EUW1).withSummonerId(id).get();
        TimeUnit.SECONDS.sleep(10);
        new SummonerBuilder().withPlatform(Platform.EUW1).withSummonerId(id).get();
    }
    
    @Test
    @Ignore
    public void testRateLimitStatic()
    {
        Platform plat = Platform.values()[1];
        for (int i = 0; i < 30; i++)
        {
            r4J.getDDragonAPI().getRealm();
            r4J.getDDragonAPI().getVersions();
            r4J.getDDragonAPI().getLanguages();
            if (i % 9 == 0)
            {
                plat = Platform.values()[plat.ordinal() + 1];
            }
            System.out.format("call no. %s Total time: %sms%n", i + 1, stopwatch.runtime(TimeUnit.MILLISECONDS));
        }
    }
}
