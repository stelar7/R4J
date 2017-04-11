package no.stelar7.api.l4j8.tests.champion;

import no.stelar7.api.l4j8.basic.constants.Champions;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.champion.*;
import no.stelar7.api.l4j8.tests.*;
import org.junit.*;

import java.util.*;

public class ChampionTest extends TestBase
{
    
    @Test
    public void testSingleChampion()
    {
        final L4J8  l4j8 = new L4J8(SecretFile.CREDS);
        ChampionAPI api  = l4j8.getChampionAPI();
        
        Optional<Champion> champ = api.getChampion(Platform.EUW1, Champions.LEONA.getId());
        Assert.assertTrue("no data?", champ.isPresent());
        Assert.assertTrue("Champion id is not leona?", champ.get().getId().equals(Champions.LEONA.getId()));
    }
    
    @Test
    public void testFreeToPlay()
    {
        final L4J8  l4j8 = new L4J8(SecretFile.CREDS);
        ChampionAPI api  = l4j8.getChampionAPI();
        
        Optional<ChampionList> champ = api.getChampions(Platform.EUW1, Optional.of(true));
        Assert.assertTrue("no data?", champ.isPresent());
        Assert.assertTrue("count greater than 20?", champ.get().getChampions().size() < 20);
    }
    
    @Test
    public void testChampionList()
    {
        final L4J8  l4j8 = new L4J8(SecretFile.CREDS);
        ChampionAPI api  = l4j8.getChampionAPI();
        
        Optional<ChampionList> champ = api.getChampions(Platform.EUW1, Optional.empty());
        Assert.assertTrue("no data?", champ.isPresent());
        Assert.assertTrue("count less than 100?", champ.get().getChampions().size() > 100);
    }
}
