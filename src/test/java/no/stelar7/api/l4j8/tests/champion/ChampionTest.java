package no.stelar7.api.l4j8.tests.champion;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.impl.builders.champion.ChampionBuilder;
import no.stelar7.api.l4j8.impl.raw.ChampionAPI;
import no.stelar7.api.l4j8.pojo.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;

public class ChampionTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    
    @Test
    public void testFreeToPlay()
    {
        ChampionRotationInfo champ = new ChampionBuilder().withPlatform(Platform.EUW1).getFreeToPlayRotation();
        
        for (Integer champion : champ.getFreeChampionIds())
        {
            System.out.format("%s%n", champion);
        }
    }
    
    @Test
    public void testFreeToPlay2()
    {
        ChampionRotationInfo champ2 = ChampionAPI.getInstance().getFreeToPlayRotation(Platform.EUW1);
        for (StaticChampion free : champ2.getFreeChampions())
        {
            System.out.format("%s%n", free.toString());
        }
    }
}
