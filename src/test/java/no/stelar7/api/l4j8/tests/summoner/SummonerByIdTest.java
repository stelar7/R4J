package no.stelar7.api.l4j8.tests.summoner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.URLEndpoint;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import no.stelar7.api.l4j8.tests.TestBase;

public class SummonerByIdTest extends TestBase
{

    private final BiConsumer<String, Summoner> doAssertions = (final String key, final Summoner value) -> {
        Assert.assertEquals("Summoner id does not match!?", key, String.valueOf(value.getId()));
        Assert.assertNotNull("Summoner name is NULL", value.getName());
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
        final List<String> keys = Arrays.asList("19613950", "22291359", "33540589");

        // Add them as a parameter to the URL
        keys.forEach((final String k) -> TestBase.builder.withURLData("{summonerId}", k));

        // Get the response
        final Map<String, Summoner> data = (Map<String, Summoner>) TestBase.builder.build();

        // Make sure all the data is returned as expected
        data.forEach(this.doAssertions);
    }

    @Before
    public void initBeforeClass()
    {
        TestBase.builder.withAPIKey(SecretFile.API_KEY);
        TestBase.builder.withServer(Server.EUW);
        TestBase.builder.withEndpoint(URLEndpoint.SUMMONER_BY_ID);
    }
}
