package no.stelar7.api.l4j8.tests.summoner;

import java.util.*;
import java.util.function.*;

import org.junit.*;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.summoner.*;
import no.stelar7.api.l4j8.tests.*;

public class SummonerByNameTest extends TestBase
{

    private final BiConsumer<String, Summoner> doAssertions = (final String key, final Summoner value) -> {
        Assert.assertNotNull("Summoner name is NULL", value.getName());
        Assert.assertNotEquals("Summoner id is NULL", value.getId(), (Long) 0L);
        Assert.assertNotEquals("Summoner profile icon is NULL", value.getProfileIconId(), (Integer) 0);
        Assert.assertNotEquals("Summoner revision date is NULL", value.getRevisionDate(), (Long) 0L);
        Assert.assertNotEquals("Summoner level is NULL", value.getSummonerLevel(), (Integer) 0);
        Assert.assertNotNull("Summoner revison date DATE is NULL", value.getRevisionDateAsDate());
        Assert.assertEquals("Summoner revison date and DATE do not match", value.getRevisionDate(), (Long) value.getRevisionDateAsDate().toInstant().toEpochMilli());
    };

    @Test
    public void doTest()
    {
        // Generate list of summoner IDs
        final List<String> keys = Arrays.asList("stelar7", "henriko950", "vibbsen", "Tàylor Swíft");

        // Add them as a parameter to the URL
        keys.forEach((final String k) -> TestBase.builder.withURLData("{summonerName}", Utils.prepareForURL(k)));

        // Get the response
        final Map<String, Summoner> data = (Map<String, Summoner>) TestBase.builder.build();

        // Make sure all the data is returned as expected
        data.forEach(this.doAssertions);
    }

    @Before
    public void initBeforeTest()
    {
        TestBase.builder.withServer(Server.EUW);
        TestBase.builder.withEndpoint(URLEndpoint.SUMMONER_BY_NAME);
    }
}
