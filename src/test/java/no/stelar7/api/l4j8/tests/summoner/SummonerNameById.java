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
import no.stelar7.api.l4j8.tests.SecretFile;
import no.stelar7.api.l4j8.tests.TestBase;

public class SummonerNameById extends TestBase
{

    @Before
    public void initBeforeTest()
    {
        TestBase.builder.withAPIKey(SecretFile.API_KEY);
        TestBase.builder.withServer(Server.EUW);
        TestBase.builder.withEndpoint(URLEndpoint.SUMMONER_NAME_BY_ID);
    }
    
    
    @Test
    public void doTest()
    {
        // Generate list of String IDs
        List<String> keys = Arrays.asList("19613950", "22291359", "33540589");

        // Add them as a parameter to the URL
        keys.forEach((String k) -> TestBase.builder.withURLData("{summonerId}", k));

        // Get the response
        Map<String, String> data = (Map<String, String>) TestBase.builder.build();

        // Make sure all the data is returned as expected
        data.forEach(doAssertions);
    }

    private BiConsumer<String, String> doAssertions = (String key, String value) -> {
        Assert.assertNotNull("No returned value for id ", value);
    };

}
