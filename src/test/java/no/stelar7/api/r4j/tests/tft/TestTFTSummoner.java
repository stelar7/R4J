package no.stelar7.api.r4j.tests.tft;

import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.tft.TFTSummonerAPI;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;

import java.util.function.Consumer;

// assume that byName works, since we cant get any summoner ids otherwise..
public class TestTFTSummoner
{
    private final Consumer<Summoner> doAssertions = (final Summoner value) ->
    {
        Assert.assertNotNull("Summoner name is NULL", value.getName());
        Assert.assertNotEquals("Summoner profile icon is NULL", value.getProfileIconId(), 0);
        Assert.assertNotEquals("Summoner revision date is NULL", value.getRevisionDate(), 0L);
        Assert.assertNotEquals("Summoner level is NULL", value.getSummonerLevel(), 0);
        Assert.assertNotNull("Summoner revison date DATE is NULL", value.getRevisionDateAsDate());
        Assert.assertNotNull("Summoner PUUID is null", value.getPUUID());
    };
    
    final R4J l4j8 = new R4J(SecretFile.CREDS);
    TFTSummonerAPI api = l4j8.getTFTAPI().getSummonerAPI();
    
    @Before
    public void before()
    {
        DataCall.setCacheProvider(new TieredCacheProvider(new MemoryCacheProvider(), new FileSystemCacheProvider()));
    }
    
    @Test
    public void testById()
    {
        Summoner s  = api.getSummonerByName(Platform.EUW1, "stelar7");
        Summoner s2 = api.getSummonerById(Platform.EUW1, s.getSummonerId());
        doAssertions.accept(s2);
    }
    
    @Test
    public void testByName()
    {
        Summoner s = api.getSummonerByName(Platform.EUW1, "stelar7");
        doAssertions.accept(s);
    }
    
    @Test
    public void testByAccount()
    {
        Summoner s = api.getSummonerByName(Platform.EUW1, "stelar7");
        
        Summoner optional = api.getSummonerByAccount(Platform.EUW1, s.getAccountId());
        doAssertions.accept(optional);
    }
    
    @Test
    public void testByPUUID()
    {
        Summoner s = api.getSummonerByName(Platform.EUW1, "stelar7");
        
        Summoner optional = api.getSummonerByPUUID(Platform.EUW1, s.getPUUID());
        doAssertions.accept(optional);
    }
}
