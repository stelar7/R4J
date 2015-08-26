package no.stelar7.api.l4j8.tests.matchlist;

import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javafx.util.Pair;
import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.DataCall.ResponseType;
import no.stelar7.api.l4j8.tests.SecretFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatchListTest
{

    DataCallBuilder builder = DataCall.builder();

    @Before
    public void init()
    {
        builder.withAPIKey(SecretFile.API_KEY);
        builder.withServer(Server.EUW);
        builder.withEndpoint(URLEndpoint.MATCHLIST);
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
    public void testManyWithParam()
    {
        System.err.println("TESTING MANY WITH PARAMS");

        builder.withURLData("{summonerId}", "19613950");

        builder.withURLParameter("championIds", "266");
        builder.withURLParameter("rankedQueues", Constants.RankedQueue.RANKED_SOLO_5X5);
        builder.withURLParameter("seasons", Constants.Season.SEASON_2015);

        Pair<ResponseType, Object> summonerCall = builder.build();
        parseResult(summonerCall);
    }

    @Test
    public void testMany()
    {
        System.err.println("TESTING MANY");

        builder.withURLData("{summonerId}", "19613950");

        Pair<ResponseType, Object> summonerCall = builder.build();
        parseResult(summonerCall);
    }
}
