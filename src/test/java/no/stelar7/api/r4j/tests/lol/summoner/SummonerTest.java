package no.stelar7.api.r4j.tests.lol.summoner;


import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.SummonerCrawler;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.function.Consumer;

public class SummonerTest
{
    
    private final Consumer<Summoner> doAssertions = (final Summoner value) ->
    {
        Assertions.assertNotNull(value.getName(), "Summoner name is NULL");
        Assertions.assertNotEquals(value.getProfileIconId(), 0, "Summoner profile icon is NULL");
        Assertions.assertNotEquals(value.getRevisionDate(), 0L, "Summoner revision date is NULL");
        Assertions.assertNotEquals(value.getSummonerLevel(), 0, "Summoner level is NULL");
        Assertions.assertNotNull(value.getRevisionDateAsDate(), "Summoner revison date DATE is NULL");
        Assertions.assertNotNull(value.getPUUID(), "Summoner PUUID is null");
    };
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    @BeforeAll
    static void before()
    {
        DataCall.setCacheProvider(new TieredCacheProvider(new MemoryCacheProvider(), new FileSystemCacheProvider()));
    }
    
    @Test
    public void testById()
    {
        /*
        Summoner ids have been removed from the spectator endpoint, so this test wont work anymore...
        
        String   id = new SpectatorBuilder().withPlatform(Platform.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerId();
        Summoner s  = new SummonerBuilder().withPlatform(Platform.EUW1).withSummonerId(id).get();
         */
        
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        doAssertions.accept(s);
    }
    
    @Test
    public void testByName()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        Summoner optional = new SummonerBuilder().withPlatform(s.getPlatform()).withName(s.getNormalizedName()).get();
        doAssertions.accept(optional);
    }
    
    @Test
    public void testByAccount()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        Summoner optional = new SummonerBuilder().withPlatform(s.getPlatform()).withAccountId(s.getAccountId()).get();
        doAssertions.accept(optional);
    }
    
    @Test
    public void testByPUUID()
    {
        String   id = new SpectatorBuilder().withPlatform(LeagueShard.EUW1).getFeaturedGames().get(0).getParticipants().get(0).getSummonerName();
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUW1).withName(id).get();
        
        Summoner optional = new SummonerBuilder().withPlatform(s.getPlatform()).withPUUID(s.getPUUID()).get();
        doAssertions.accept(optional);
    }
    
    @Test
    public void testWadas()
    {
        DataCall.setCacheProvider(null);
        Summoner s  = new SummonerBuilder().withPlatform(LeagueShard.EUN1).withName("GindenEU").get();
        Summoner s2 = new SummonerBuilder().withPlatform(LeagueShard.EUN1).withPUUID(s.getPUUID()).get();
        
        System.out.println(s);
        System.out.println(s2);
    }
    
    @Test
    @Disabled
    public void testCrawler()
    {
        SummonerCrawler crawler = new SummonerCrawler(Summoner.byName(LeagueShard.EUW1, "stelar7"));
        System.out.println(crawler.get().size());
        crawler.crawlLeague();
        System.out.println(crawler.get().size());
        
        crawler = new SummonerCrawler(Summoner.byName(LeagueShard.EUW1, "stelar7"));
        System.out.println(crawler.get().size());
        crawler.crawlGames();
        System.out.println(crawler.get().size());
    }
}
