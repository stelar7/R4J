package no.stelar7.api.r4j.tests.lor;

import no.stelar7.api.r4j.basic.constants.api.*;
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
            System.out.println(LORClientAPI.getActiveDeck());
            long end = System.currentTimeMillis();
            System.out.println("active deck: " + (end - start));
            
            start = System.currentTimeMillis();
            System.out.println(LORClientAPI.getCardPositions());
            end = System.currentTimeMillis();
            System.out.println("card pos: " + (end - start));
            
            start = System.currentTimeMillis();
            System.out.println(LORClientAPI.getLastGameResult());
            end = System.currentTimeMillis();
            System.out.println("last game: " + (end - start));
            
            start = System.currentTimeMillis();
            System.out.println(LORClientAPI.getExpeditionsState());
            end = System.currentTimeMillis();
            System.out.println("exp state: " + (end - start));
            
            System.out.println(LORClientAPI.getActiveDeck());
            System.out.println(LORClientAPI.getCardPositions());
            System.out.println(LORClientAPI.getLastGameResult());
            System.out.println(LORClientAPI.getExpeditionsState());
            
            Thread.sleep(1000);
        }
    }
    
    
    @Test
    @Ignore
    public void testLeaderboard()
    {
        LORRankedAPI api = new R4J(SecretFile.CREDS).getLORAPI().getRankedAPI();
        api.getLeaderboard(ServicePlatform.EUROPE);
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testActiveDeck()
    {
        LoRDeck deck = LORClientAPI.getActiveDeck();
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testGameResult()
    {
        LoRGameResult result = LORClientAPI.getLastGameResult();
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testGameState()
    {
        LoRGameInfo state = LORClientAPI.getCardPositions();
        System.out.println();
    }
}
