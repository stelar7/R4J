package no.stelar7.api.r4j.tests.ratelimit;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.*;

@ExtendWith(TimingExtension.class)
public class RatelimitTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    @Test
    @Disabled
    public void testRateLimitThreaded()
    {
        try
        {
            String          name   = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
            Summoner        s    = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(name).get();
            ExecutorService pool = Executors.newFixedThreadPool(8);
            for (int i2 = 0; i2 < 130; i2++)
            {
                pool.execute(() -> new SummonerBuilder().withPlatform(LeagueShard.EUW1).withSummonerId(s.getSummonerId()).get());
            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    
    @Test
    @Disabled
    public void testRateLimit()
    {
        final R4J test = new R4J(SecretFile.CREDS);
        String    id   = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner   s    = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        for (int i2 = 0; i2 < 130; i2++)
        {
            new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        }
    }
    
    @Test
    @Disabled
    public void testRateLimitWithSleep() throws InterruptedException
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withSummonerId(id).get();
        TimeUnit.SECONDS.sleep(10);
        new SummonerBuilder().withPlatform(LeagueShard.EUW1).withSummonerId(id).get();
    }
    
    @Test
    @Disabled
    public void testRateLimitStatic()
    {
        long start = System.currentTimeMillis();
        LeagueShard plat = LeagueShard.values()[1];
        for (int i = 0; i < 30; i++)
        {
            r4J.getDDragonAPI().getRealm();
            r4J.getDDragonAPI().getVersions();
            r4J.getDDragonAPI().getLanguages();
            if (i % 9 == 0)
            {
                plat = LeagueShard.values()[plat.ordinal() + 1];
            }
            System.out.format("call no. %s Total time: %sms%n", i + 1, System.currentTimeMillis() - start);
        }
    }
}
