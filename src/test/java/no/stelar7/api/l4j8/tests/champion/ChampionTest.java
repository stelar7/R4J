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
    ChampionAPI api = l4j8.getChampionAPI();
    
    @Test
    public void testSingleChampion()
    {
        Champion champ = api.getChampion(Platform.EUW1, Constants.TEST_CHAMPION_IDS[0]);
        
        Assert.assertTrue("Championid is not leona?", champ.getId() == Constants.TEST_CHAMPION_IDS[0]);
        
    }
    
    @Test
    public void testFreeToPlay()
    {
        List<Champion> champ = api.getChampions(Platform.EUW1, true);
        Assert.assertTrue("count greater than 20?", champ.size() < 20);
        
        champ.sort(Comparator.comparing(Champion::getId));
        for (Champion champion : champ)
        {
            System.out.format("%s: %s%n", champion.getChampion().getId(), champion.getChampion().getName());
        }
    }
    
    @Test
    public void testChampionList()
    {
        List<Champion> champ = api.getChampions(Platform.EUW1, false);
        Assert.assertTrue("count less than 100?", champ.size() > 100);
    }
}
