package no.stelar7.api.l4j8.tests.champion;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.stelar7.api.l4j8.basic.URLEndpoint;
import no.stelar7.api.l4j8.pojo.champion.Champion;
import no.stelar7.api.l4j8.pojo.champion.ChampionList;
import no.stelar7.api.l4j8.tests.TestBase;

public class ChampionByIdTest extends TestBase
{

    @Before
    public void initForTest()
    {
        init();
        TestBase.builder.withEndpoint(URLEndpoint.CHAMPION_BY_ID_MULTIPLE);
    }

    private List<Champion> objectToList(ChampionList cl)
    {
        return cl.getChampions();
    }

    @Test
    public void testMany()
    {
        TestBase.builder.withURLData("{championId}", "");
        List<Champion> champs = objectToList((ChampionList) TestBase.builder.build());

        Assert.assertTrue("Less than 100 champions?", champs.size() > 100);
    }

    @Test
    public void testManyWithParam()
    {
        TestBase.builder.withURLData("{championId}", "");
        TestBase.builder.withURLParameter("freeToPlay", "true");
        List<Champion> champs = objectToList((ChampionList) TestBase.builder.build());

        champs.forEach(c -> Assert.assertTrue("Free to play is false?!", c.isFreeToPlay()));
    }

    @Test
    public void testSingle()
    {
        TestBase.builder.withURLData("{championId}", "266");
        TestBase.builder.withEndpoint(URLEndpoint.CHAMPION_BY_ID_SINGLE);
        Champion champs = ((Champion) TestBase.builder.build());

        Assert.assertTrue("Champion id is not 266?", champs.getId().equals(266));
    }
}
