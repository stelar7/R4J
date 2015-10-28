package no.stelar7.api.l4j8.tests.champion;

import java.util.List;

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
import no.stelar7.api.l4j8.pojo.champion.Champion;
import no.stelar7.api.l4j8.tests.SecretFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChampionByIdTest
{

    DataCallBuilder builder = DataCall.builder();

    @Before
    public void init()
    {
        System.err.println("TESTING CHAMPION BY ID");
        this.builder.withAPIKey(SecretFile.API_KEY);
        this.builder.withServer(Server.EUW);
        this.builder.withEndpoint(URLEndpoint.CHAMPION_BY_ID);
    }

    @Test
    public void testMany()
    {
        this.builder.withURLData("{championId}", "");
        final Pair<ResponseType, Object> dataCall = this.builder.build();
        List<Champion> champs = (List<Champion>) dataCall.getValue();

        Assert.assertTrue("Less than 100 champions?", champs.size() > 100);
    }

    @Test
    public void testManyWithParam()
    {
        this.builder.withURLData("{championId}", "");
        this.builder.withURLParameter("freeToPlay", "true");
        final Pair<ResponseType, Object> dataCall = this.builder.build();
        List<Champion> champs = (List<Champion>) dataCall.getValue();

        champs.forEach(c -> Assert.assertTrue("Free to play is false?!", c.isFreeToPlay()));
    }

    @Test
    public void testSingle()
    {
        this.builder.withURLData("{championId}", "266");
        final Pair<ResponseType, Object> dataCall = this.builder.build();
        List<Champion> champs = (List<Champion>) dataCall.getValue();

        Assert.assertTrue("More than 1 champion with id 266?", champs.size() == 1);
        Assert.assertTrue("Champion id is not 266?", champs.get(0).getId().equals(266));
    }
}
