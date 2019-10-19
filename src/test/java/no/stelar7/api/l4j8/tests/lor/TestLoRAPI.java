package no.stelar7.api.l4j8.tests.lor;

import no.stelar7.api.l4j8.impl.lor.*;
import no.stelar7.lor.types.LoRDeck;
import org.junit.Test;

public class TestLoRAPI
{
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
