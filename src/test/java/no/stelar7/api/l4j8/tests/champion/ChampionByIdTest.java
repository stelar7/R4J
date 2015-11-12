package no.stelar7.api.l4j8.tests.champion;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javafx.util.Pair;
import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.URLEndpoint;
import no.stelar7.api.l4j8.basic.DataCall.ResponseType;
import no.stelar7.api.l4j8.pojo.champion.Champion;
import no.stelar7.api.l4j8.tests.SecretFile;
import no.stelar7.api.l4j8.tests.TestBase;

public class ChampionByIdTest extends TestBase
{
    
    @Before
    public void init()
    {
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
