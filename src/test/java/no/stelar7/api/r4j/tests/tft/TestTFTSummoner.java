package no.stelar7.api.r4j.tests.tft;

import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.tft.TFTSummonerAPI;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.function.Consumer;

// assume that byName works, since we cant get any summoner ids otherwise..
public class TestTFTSummoner
{
    private final Consumer<Summoner> doAssertions = (final Summoner value) ->
    {
        Assertions.assertNotNull(value.getName(), "Summoner name is NULL");
        Assertions.assertNotEquals(0, value.getProfileIconId(), "Summoner profile icon is NULL");
        Assertions.assertNotEquals(0L, value.getRevisionDate(), "Summoner revision date is NULL");
        Assertions.assertNotEquals(0, value.getSummonerLevel(), "Summoner level is NULL");
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
    public void testByPUUID()
    {
        RiotAccount account = l4j8.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7");
        Summoner    s       = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        
        Summoner optional = api.getSummonerByPUUID(LeagueShard.EUW1, s.getPUUID());
        doAssertions.accept(optional);
    }
}
