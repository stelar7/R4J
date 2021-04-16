package no.stelar7.api.r4j.impl.lor;

import no.stelar7.api.r4j.basic.calling.DataCallBuilder;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.lor.oauth.*;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRDeck;

public class LoROAuthAPI
{
    private static final LoROAuthAPI INSTANCE = new LoROAuthAPI();
    
    private LoROAuthAPI()
    {
    }
    
    public static LoROAuthAPI getInstance()
    {
        return LoROAuthAPI.INSTANCE;
    }
    
    private OAuthAuthorizeData requestObject;
    private OAuthTokenData     responseObject;
    
    private String getBearerHeaderValue()
    {
        return "Bearer " + responseObject.getAccessToken();
    }
    
    /**
     * Gets a list of all cards in this players collection
     *
     */
    public LoRCardList getInventoryCards()
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.AUTHORIZATION, this.getBearerHeaderValue())
                .withEndpoint(URLEndpoint.V1_LOR_INVENTORY_CARDS_ME)
                .withPlatform(requestObject.getPlatform());
        
        return (LoRCardList) builder.build();
    }
    
    /**
     * Gets a list of all cards in this players collection
     *
     */
    public LoRDeckList getDecks()
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.AUTHORIZATION, this.getBearerHeaderValue())
                .withEndpoint(URLEndpoint.V1_LOR_DECKS_ME_GET)
                .withPlatform(requestObject.getPlatform());
        
        return (LoRDeckList) builder.build();
    }
    
    /**
     * Creates a new deck with the given name and cards
     */
    public String createDeck(String name, LoRDeck deck)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.AUTHORIZATION, this.getBearerHeaderValue())
                .withEndpoint(URLEndpoint.V1_LOR_DECKS_ME_POST)
                .withPlatform(requestObject.getPlatform())
                .withRequestMethod("POST")
                .withPostData(Utils.getGson().toJson(deck.toRequestDeck(name)));
        
        return (String) builder.build();
    }
}
