package no.stelar7.api.l4j8.impl.lor;

import com.google.gson.JsonObject;
import no.stelar7.api.l4j8.basic.calling.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.lor.LoRDeckCode;
import no.stelar7.lor.types.LoRDeck;

public class LoRApi
{
    /**
     * Gets the players active deck, this deck is static and does not change during a game.
     * <p>
     * Returns null of not in a game
     */
    public static LoRDeck getActiveDeck()
    {
        JsonObject obj = (JsonObject) new DataCallBuilder()
                .withLimiters(false)
                .withProxy(LoRConnection.getConnectionString() + Constants.GSVR)
                .withEndpoint(URLEndpoint.LOR_STATIC_ACTIVE_DECK)
                .build();
        
        try
        {
            return LoRDeckCode.decode(obj.get("DeckCode").getAsString());
        } catch (Exception e)
        {
            return null;
        }
    }
    
    
    /**
     * Gets the result from the last played game
     */
    public static LoRGameResult getLastGameResult()
    {
        return (LoRGameResult) new DataCallBuilder()
                .withLimiters(false)
                .withProxy(LoRConnection.getConnectionString() + Constants.GSVR)
                .withEndpoint(URLEndpoint.LOR_GAME_RESULT)
                .build();
    }
    
    public static LoRGameInfo getCardPositions()
    {
        return (LoRGameInfo) new DataCallBuilder()
                .withLimiters(false)
                .withProxy(LoRConnection.getConnectionString() + Constants.GSVR)
                .withEndpoint(URLEndpoint.LOR_CARD_POSITIONS)
                .build();
    }
    
    public static LoRExpeditionInfo getExpeditionsState()
    {
        return (LoRExpeditionInfo) new DataCallBuilder()
                .withLimiters(false)
                .withProxy(LoRConnection.getConnectionString() + Constants.GSVR)
                .withEndpoint(URLEndpoint.LOR_EXPEDITIONS_STATE)
                .build();
    }
    
}
