package no.stelar7.api.l4j8.tests.tft;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.tft.TFTMatchAPI;
import no.stelar7.api.l4j8.pojo.match.GAMHSMatch;
import no.stelar7.api.l4j8.pojo.match.tft.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.List;

public class TestTFTMatch
{
    final L4J8        l4j8 = new L4J8(SecretFile.CREDS);
    final TFTMatchAPI api  = l4j8.getTFTAPI().getMatchAPI();
    
    
    @Test
    public void testFetchList()
    {
        List<String> stelar7 = api.getMatchList(ServicePlatform.EUROPE, Summoner.byName(Platform.EUW1, "stelar7").getPUUID());
        System.out.println();
    }
    
    @Test
    public void testFetchMatch()
    {
        TFTMatch stelar7 = api.getMatch(ServicePlatform.EUROPE, "EUW1_4240052361");
        System.out.println();
    }
    
    @Test
    public void testFetchMatchRAW()
    {
        GAMHSMatch stelar7 = api.getMatchRAW(ServicePlatform.EUROPE, "EUW1_4240052361");
        System.out.println();
    }
    
    @Test
    public void testMatchIterator()
    {
        MatchIterator stelar7 = api.getMatchIterator(ServicePlatform.EUROPE, Summoner.byName(Platform.EUW1, "stelar7").getPUUID());
        for (TFTMatch m : stelar7)
        {
            System.out.println(m);
        }
        System.out.println();
    }
}
