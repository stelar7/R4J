package no.stelar7.api.l4j8.tests.summoner;

import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javafx.util.Pair;
import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.DataCall.ResponseType;
import no.stelar7.api.l4j8.tests.SecretFile;
import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.URLEndpoint;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SummonerNameById
{

    DataCallBuilder builder = DataCall.builder();

    @Before
    public void init()
    {
        builder.withAPIKey(SecretFile.API_KEY);
        builder.withServer(Server.EUW);
        builder.withEndpoint(URLEndpoint.SUMMONER_NAMES_BY_ID);
    }

    public void parseResult(Pair<ResponseType, Object> result)
    {
        switch (result.getKey())
        {
            case OK:
            {
                if (result.getValue() instanceof Map)
                {
                    ((Map<?, ?>) result.getValue()).keySet().forEach(k -> {
                        System.out.println(k);
                        System.out.println("\t" + ((Map<?, ?>) result.getValue()).get(k));
                    });
                } else
                {
                    System.out.println(result.getValue());
                }
                break;
            }
            default:
            {
                System.out.println(result.getKey());
                System.out.println(result.getValue());
                break;
            }
        }
    }

    @Test
    public void testSingle()
    {
        System.err.println("TESTING SINGLE");
        builder.withURLData("{summonerId}", "19613950");
        Pair<ResponseType, Object> summonerCall = builder.build();
        parseResult(summonerCall);
    }

    @Test
    public void testMany()
    {
        System.err.println("TESTING MANY");
        builder.withURLData("{summonerId}", "19613950");
        builder.withURLData("{summonerId}", "22291359");
        builder.withURLData("{summonerId}", "33540589");

        Pair<ResponseType, Object> summonerCall = builder.build();
        parseResult(summonerCall);
    }
}
