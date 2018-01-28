package no.stelar7.api.l4j8.tests.summoner;


import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.function.Consumer;

public class SummonerTest
{
    
    private final Consumer<Summoner> doAssertions = (final Summoner value) ->
    {
        Assert.assertNotNull("Summoner name is NULL", value.getName());
        Assert.assertNotEquals("Summoner profile icon is NULL", value.getProfileIconId(), 0);
        Assert.assertNotEquals("Summoner revision date is NULL", value.getRevisionDate(), 0L);
        Assert.assertNotEquals("Summoner level is NULL", value.getSummonerLevel(), 0);
        Assert.assertNotNull("Summoner revison date DATE is NULL", value.getRevisionDateAsDate());
    };
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Before
    public void before()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
    }
    
    @Test
    public void testById()
    {
        for (int i = 0; i < Constants.TEST_SUMMONER_IDS.length; i++)
        {
            Summoner optional = new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[i]).withSummonerId(Constants.TEST_SUMMONER_IDS[i]).get();
            doAssertions.accept(optional);
        }
    }
    
    @Test
    public void testByName()
    {
        for (int i = 0; i < Constants.TEST_SUMMONER_NAMES.length; i++)
        {
            Summoner optional = new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[i]).withName(Constants.TEST_SUMMONER_NAMES[i]).get();
            doAssertions.accept(optional);
        }
    }
    
    @Test
    public void testByAccount()
    {
        for (int i = 0; i < Constants.TEST_ACCOUNT_IDS.length; i++)
        {
            Summoner optional = new SummonerBuilder().withPlatform(Constants.TEST_PLATFORM[i]).withAccountId(Constants.TEST_ACCOUNT_IDS[i]).get();
            doAssertions.accept(optional);
        }
    }
}
