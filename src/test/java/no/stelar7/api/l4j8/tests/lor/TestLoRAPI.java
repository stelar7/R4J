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
            LoRApi.getCurrentGameState();
            LoRApi.getLastGameResult();
            LoRApi.getActiveDeck();
            
            Thread.sleep(1000);
        }
    }
    
    
    @Test
    public void testActiveDeck()
    {
        LoRDeck deck = LoRApi.getActiveDeck();
        System.out.println();
    }
    
    @Test
    public void testGameResult()
    {
        LoRGameResult result = LoRApi.getLastGameResult();
        System.out.println();
    }
    
    @Test
    public void testGameState()
    {
        LoRGameInfo state = LoRApi.getCurrentGameState();
        System.out.println();
    }
}
