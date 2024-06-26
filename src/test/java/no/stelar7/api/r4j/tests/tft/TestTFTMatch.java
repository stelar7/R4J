package no.stelar7.api.r4j.tests.tft;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.tft.TFTMatchAPI;
import no.stelar7.api.r4j.pojo.shared.*;
import no.stelar7.api.r4j.pojo.tft.*;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestTFTMatch
{
    final R4J         l4j8 = new R4J(SecretFile.CREDS);
    final TFTMatchAPI api  = l4j8.getTFTAPI().getMatchAPI();
    
    
    @Test
    public void testFetchList()
    {
        RiotAccount  account = l4j8.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7");
        List<String> stelar7 = api.getMatchList(RegionShard.EUROPE, account.getPUUID(), 0, 20, null, null);
        System.out.println();
    }
    
    @Test
    public void testFetchMatch()
    {
        TFTMatch stelar7 = api.getMatch(RegionShard.EUROPE, "EUW1_4240052361");
        System.out.println();
    }
    
    @Test
    public void testFetchMatchRAW()
    {
        GAMHSMatch stelar7 = api.getMatchRAW(RegionShard.EUROPE, "EUW1_4240052361");
        System.out.println();
    }
    
    @Test
    public void testMatchIterator()
    {
        RiotAccount   acc     = l4j8.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "Yisus", "yisus");
        MatchIterator stelar7 = api.getMatchIterator(RegionShard.EUROPE, acc.getPUUID(), 0, 20, null, null);
        for (GAMHSMatch m : stelar7)
        {
            System.out.println(m.asTFTMatch());
            System.out.println(m.asTFTMetadata());
        }
        System.out.println();
    }
}
