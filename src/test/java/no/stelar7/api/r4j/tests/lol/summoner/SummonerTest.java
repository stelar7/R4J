package no.stelar7.api.r4j.tests.lol.summoner;


import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.SummonerCrawler;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.impl.lol.raw.SpectatorAPI;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
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
        
        String      id      = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        RiotAccount account = r4J.getAccountAPI().getAccountByPUUID(LeagueShard.EUW1.toRegionShard(), id);
        Summoner    s       = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        doAssertions.accept(s);
    }
    
    @Test
    public void testByAccount()
    {
        String      id      = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        RiotAccount account = r4J.getAccountAPI().getAccountByPUUID(LeagueShard.EUW1.toRegionShard(), id);
        Summoner    s       = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        
        Summoner optional = new SummonerBuilder().withPlatform(s.getPlatform()).withAccountId(s.getAccountId()).get();
        doAssertions.accept(optional);
    }
    
    @Test
    public void testByPUUID()
    {
        String      id      = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.EUW1).get(0).getParticipants().get(0).getPuuid();
        RiotAccount account = r4J.getAccountAPI().getAccountByPUUID(LeagueShard.EUW1.toRegionShard(), id);
        Summoner    s       = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        
        Summoner optional = new SummonerBuilder().withPlatform(s.getPlatform()).withPUUID(s.getPUUID()).get();
        doAssertions.accept(optional);
    }
    
    
    @Test
    @Disabled
    public void testCrawler()
    {
        RiotAccount account = r4J.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7");
        Summoner    s       = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        
        SummonerCrawler crawler = new SummonerCrawler(s);
        System.out.println(crawler.get().size());
        crawler.crawlLeague();
        System.out.println(crawler.get().size());
        
        crawler = new SummonerCrawler(s);
        System.out.println(crawler.get().size());
        crawler.crawlGames();
        System.out.println(crawler.get().size());
    }
    
}
