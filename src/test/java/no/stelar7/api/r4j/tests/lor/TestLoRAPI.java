package no.stelar7.api.r4j.tests.lor;

import no.stelar7.api.r4j.basic.constants.api.regions.RegionalName;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lor.*;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRDeck;
import no.stelar7.api.r4j.pojo.lor.offline.game.*;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;

public class TestLoRAPI
{
    @Test
    @Ignore
    public void testStuff() throws InterruptedException
    {
        while (true)
        {
            long start = System.currentTimeMillis();
            System.out.println(LoRClientAPI.getActiveDeck());
            long end = System.currentTimeMillis();
            System.out.println("active deck: " + (end - start));
            
            start = System.currentTimeMillis();
            System.out.println(LoRClientAPI.getCardPositions());
            end = System.currentTimeMillis();
            System.out.println("card pos: " + (end - start));
            
            start = System.currentTimeMillis();
            System.out.println(LoRClientAPI.getLastGameResult());
            end = System.currentTimeMillis();
            System.out.println("last game: " + (end - start));
            
            start = System.currentTimeMillis();
            System.out.println(LoRClientAPI.getExpeditionsState());
            end = System.currentTimeMillis();
            System.out.println("exp state: " + (end - start));
            
            System.out.println(LoRClientAPI.getActiveDeck());
            System.out.println(LoRClientAPI.getCardPositions());
            System.out.println(LoRClientAPI.getLastGameResult());
            System.out.println(LoRClientAPI.getExpeditionsState());
            
            Thread.sleep(1000);
        }
    }
    
    
    @Test
    @Ignore
    public void testLeaderboard()
    {
        LORRankedAPI api = new R4J(SecretFile.CREDS).getLORAPI().getRankedAPI();
        api.getLeaderboard(RegionalName.EUROPE);
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testActiveDeck()
    {
        LoRDeck deck = LoRClientAPI.getActiveDeck();
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testGameResult()
    {
        LoRGameResult result = LoRClientAPI.getLastGameResult();
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testGameState()
    {
        LoRGameInfo state = LoRClientAPI.getCardPositions();
        System.out.println();
    }
}
