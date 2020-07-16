package no.stelar7.api.r4j.tests.lol.champion;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.champion.ChampionBuilder;
import no.stelar7.api.r4j.impl.lol.raw.ChampionAPI;
import no.stelar7.api.r4j.pojo.lol.champion.ChampionRotationInfo;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

public class ChampionTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    
    @Test
    public void testFreeToPlay()
    {
        ChampionRotationInfo champ = new ChampionBuilder().withPlatform(LeagueShard.EUW1).getFreeToPlayRotation();
        
        for (Integer champion : champ.getFreeChampionIds())
        {
            System.out.format("%s%n", champion);
        }
    }
    
    @Test
    public void testFreeToPlay2()
    {
        ChampionRotationInfo champ2 = ChampionAPI.getInstance().getFreeToPlayRotation(LeagueShard.EUW1);
        for (StaticChampion free : champ2.getFreeChampions())
        {
            System.out.format("%s%n", free.toString());
        }
    }
}
