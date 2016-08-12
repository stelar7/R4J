package no.stelar7.api.l4j8.tests.champion;

import java.util.*;

import org.junit.*;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.champion.*;
import no.stelar7.api.l4j8.tests.*;

public class ChampionByIdTest extends TestBase
{

    @Before
    public void initForTest()
    {
        TestBase.init();
        TestBase.builder.withEndpoint(URLEndpoint.CHAMPIONS);
    }

    private List<Champion> objectToList(final ChampionList cl)
    {
        return cl.getChampions();
    }

    @Test
    public void testMany()
    {
        TestBase.builder.withURLData(Constants.CHAMPION_ID_PLACEHOLDER, "");
        final List<Champion> champs = this.objectToList((ChampionList) TestBase.builder.build());

        Assert.assertTrue("Less than 100 champions?", champs.size() > 100);
    }

    @Test
    public void testManyWithParam()
    {
        TestBase.builder.withURLData(Constants.CHAMPION_ID_PLACEHOLDER, "");
        TestBase.builder.withURLParameter("freeToPlay", "true");
        final List<Champion> champs = this.objectToList((ChampionList) TestBase.builder.build());

        champs.forEach(c -> Assert.assertTrue("Free to play is false?!", c.isFreeToPlay()));
    }

    @Test
    public void testSingle()
    {
        TestBase.builder.withURLData(Constants.CHAMPION_ID_PLACEHOLDER, "266");
        TestBase.builder.withEndpoint(URLEndpoint.CHAMPION_BY_ID);
        final Champion champs = (Champion) TestBase.builder.build();

        Assert.assertTrue("Champion id is not 266?", champs.getId().equals(266));
    }
}
