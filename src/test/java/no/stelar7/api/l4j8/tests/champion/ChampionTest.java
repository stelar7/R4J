package no.stelar7.api.l4j8.tests.champion;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.champion.Champion;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;

public class ChampionTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    public void testSingleChampion()
    {
        Champion champ = l4j8.getChampion().withPlatform(Platform.EUW1).withId(Constants.TEST_CHAMPION_IDS[0]).get();
        
        Assert.assertTrue("Championid is not leona?", champ.getId() == Constants.TEST_CHAMPION_IDS[0]);
        
    }
    
    @Test
    public void testFreeToPlay()
    {
        List<Champion> champ = l4j8.getChampion().withPlatform(Platform.EUW1).getFreeToPlay();
        Assert.assertTrue("count greater than 20?", champ.size() < 20);
        
        champ.sort(Comparator.comparing(Champion::getId));
        for (Champion champion : champ)
        {
            System.out.format("%s%n", champion.getId());
        }
    }
    
    @Test
    public void testChampionList()
    {
        List<Champion> champ = l4j8.getChampion().withPlatform(Platform.EUW1).getAll();
        Assert.assertTrue("count less than 100?", champ.size() > 100);
    }
}
