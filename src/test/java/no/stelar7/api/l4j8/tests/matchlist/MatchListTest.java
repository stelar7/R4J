package no.stelar7.api.l4j8.tests.matchlist;

import java.util.Map;

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
import no.stelar7.api.l4j8.basic.constants.RankedQueue;
import no.stelar7.api.l4j8.basic.constants.Season;
import no.stelar7.api.l4j8.tests.SecretFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatchListTest
{

    DataCallBuilder builder = DataCall.builder();

    @Before
    public void init()
    {
        System.err.println("TESTING MATCH LIST");
        this.builder.withAPIKey(SecretFile.API_KEY);
        this.builder.withServer(Server.EUW);
        this.builder.withEndpoint(URLEndpoint.MATCHLIST);
    }

    public void parseResult(final Pair<ResponseType, Object> result)
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
    public void testMany()
    {
        System.err.println("TESTING MANY");

        this.builder.withURLData("{summonerId}", "22291359");

        final Pair<ResponseType, Object> summonerCall = this.builder.build();
        this.parseResult(summonerCall);
    }

    @Test
    public void testWithParam()
    {
        System.err.println("TESTING MANY WITH PARAMS");

        this.builder.withURLData("{summonerId}", "22291359");

        this.builder.withURLParameter("championIds", "266");
        this.builder.withURLParameter("rankedQueues", RankedQueue.RANKED_SOLO_5x5.getCode());
        this.builder.withURLParameter("seasons", Season.SEASON_2015.getCode());
        this.builder.withVerbose(true);

        final Pair<ResponseType, Object> summonerCall = this.builder.build();
        this.parseResult(summonerCall);
    }
}
