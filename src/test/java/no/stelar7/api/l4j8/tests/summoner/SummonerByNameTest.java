package no.stelar7.api.l4j8.tests.summoner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javafx.util.Pair;
import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.DataCall.ResponseType;
import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.URLEndpoint;
import no.stelar7.api.l4j8.basic.Utils;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SummonerByNameTest
{

    DataCallBuilder builder = DataCall.builder();

    @Before
    public void init()
    {
        System.err.println("TESTING SUMMONER BY NAME");
        this.builder.withAPIKey(SecretFile.API_KEY);
        this.builder.withServer(Server.EUW);
        this.builder.withEndpoint(URLEndpoint.SUMMONERS_BY_NAME);
    }

    @Test
    public void doTest()
    {
        // Generate list of summoner IDs
        List<String> keys = Arrays.asList("stelar7", "henriko950", "vibbsen", "Tàylor Swíft");

        // Add them as a parameter to the URL
        keys.forEach((String k) -> this.builder.withURLData("{summonerName}", Utils.prepareForURL(k)));

        // Get the response
        final Pair<ResponseType, Object> dataCall = this.builder.build();

        // Map it to the correct return value
        Map<String, Summoner> data = (Map<String, Summoner>) dataCall.getValue();
        
        // Make sure all the data is returned as expected
        data.forEach(doAssertions);
    }

    private BiConsumer<String, Summoner> doAssertions = (String key, Summoner value) -> {
        Assert.assertNotNull("Summoner name is NULL", value.getName());
        Assert.assertNotEquals("Summoner id is NULL", value.getId(), (Long) 0L);
        Assert.assertNotEquals("Summoner profile icon is NULL", value.getProfileIconId(), (Integer) 0);
        Assert.assertNotEquals("Summoner revision date is NULL", value.getRevisionDate(), (Long) 0L);
        Assert.assertNotEquals("Summoner level is NULL", value.getSummonerLevel(), (Integer) 0);
        Assert.assertNotNull("Summoner revison date DATE is NULL", value.getRevisionDateAsDate());
        Assert.assertEquals("Summoner revison date and DATE do not match", value.getRevisionDate(), (Long) value.getRevisionDateAsDate().toInstant().toEpochMilli());
    };
}
