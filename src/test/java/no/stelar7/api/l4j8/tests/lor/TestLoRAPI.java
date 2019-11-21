package no.stelar7.api.l4j8.tests.lor;

import no.stelar7.api.l4j8.impl.lor.*;
import no.stelar7.lor.types.LoRDeck;
import org.junit.*;

public class TestLoRAPI
{
    @Test
    @Ignore
    public void testStuff() throws InterruptedException
    {
        while (true)
        {
            LoRApi.getCardPositions();
            LoRApi.getLastGameResult();
            LoRApi.getActiveDeck();
            LoRApi.getExpeditionsState();
            
            Thread.sleep(1000);
        }
    }
    
    
    @Test
    @Ignore
    public void testActiveDeck()
    {
        LoRDeck deck = LoRApi.getActiveDeck();
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testGameResult()
    {
        LoRGameResult result = LoRApi.getLastGameResult();
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testGameState()
    {
        LoRGameInfo state = LoRApi.getCardPositions();
        System.out.println();
    }
}
