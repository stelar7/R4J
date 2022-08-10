package no.stelar7.api.r4j.tests.tft;

import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.tft.TFTSummonerAPI;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.function.Consumer;

// assume that byName works, since we cant get any summoner ids otherwise..
public class TestTFTSummoner
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
    
    final R4J l4j8 = new R4J(SecretFile.CREDS);
    TFTSummonerAPI api = l4j8.getTFTAPI().getSummonerAPI();
    
    @BeforeAll
    static void before()
    {
        DataCall.setCacheProvider(new TieredCacheProvider(new MemoryCacheProvider(), new FileSystemCacheProvider()));
    }
    
    @Test
    public void testById()
    {
        Summoner s  = api.getSummonerByName(LeagueShard.EUW1, "stelar7");
        Summoner s2 = api.getSummonerById(LeagueShard.EUW1, s.getSummonerId());
        doAssertions.accept(s2);
    }
    
    @Test
    public void testByName()
    {
        Summoner s = api.getSummonerByName(LeagueShard.EUW1, "stelar7");
        doAssertions.accept(s);
    }
    
    @Test
    public void testByAccount()
    {
        Summoner s = api.getSummonerByName(LeagueShard.EUW1, "stelar7");
        
        Summoner optional = api.getSummonerByAccount(LeagueShard.EUW1, s.getAccountId());
        doAssertions.accept(optional);
    }
    
    @Test
    public void testByPUUID()
    {
        Summoner s = api.getSummonerByName(LeagueShard.EUW1, "stelar7");
        
        Summoner optional = api.getSummonerByPUUID(LeagueShard.EUW1, s.getPUUID());
        doAssertions.accept(optional);
    }
    
    @Test
    public void testRandomDude() {
        Summoner s = api.getSummonerByName(LeagueShard.TR1, "Ginggg");
        s.getChampionMasteries();
    }
}
