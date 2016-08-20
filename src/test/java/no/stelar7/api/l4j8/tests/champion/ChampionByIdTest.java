package no.stelar7.api.l4j8.tests.champion;

import no.stelar7.api.l4j8.basic.constants.api.ChampionCallFlags;
import no.stelar7.api.l4j8.basic.constants.api.Server;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.champion.Champion;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static no.stelar7.api.l4j8.basic.constants.Champion.LEONA;

public class ChampionByIdTest
{

    private final L4J8 api = new L4J8(SecretFile.CREDS);

    @Test
    public void testMany()
    {

        Optional<List<Champion>> result = api.getChampionById(Server.EUW, null, null);
        if (!result.isPresent())
        {
            Assert.assertTrue("No data returned from API", false);
            return;
        }

        List<Champion> data = result.get();

        Assert.assertTrue("Less than 100 champions?", data.size() > 100);
    }

    @Test
    public void testManyWithParam()
    {

        Optional<List<Champion>> result = api.getChampionById(Server.EUW, null, ChampionCallFlags.FREE_TO_PLAY);
        if (!result.isPresent())
        {
            Assert.assertTrue("No data returned from API", false);
            return;
        }

        List<Champion> data = result.get();
        data.forEach(champion -> Assert.assertTrue("Champion is not free?", champion.isFreeToPlay()));

    }

    @Test
    public void testSingle()
    {

        Optional<List<Champion>> result = api.getChampionById(Server.EUW, LEONA.getId(), null);
        if (!result.isPresent())
        {
            Assert.assertTrue("No data returned from API", false);
            return;
        }

        List<Champion> data = result.get();
        data.forEach(champion -> Assert.assertTrue("Champion id missmatch?", champion.getId().equals(LEONA.getId())));

    }
}
