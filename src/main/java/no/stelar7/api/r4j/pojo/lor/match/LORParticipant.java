package no.stelar7.api.r4j.pojo.lor.match;

import no.stelar7.api.r4j.impl.lor.LoRDeckCode;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRDeck;

import java.util.*;

public class LORParticipant
{
    private String       puuid;
    private String       deck_id;
    private String       deck_code;
    private List<String> factions;
    private String       game_outcome;
    private Integer      order_of_play;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public String getDeckID()
    {
        return deck_id;
    }
    
    public String getDeckCode()
    {
        return deck_code;
    }
    
    public LoRDeck getDeck()
    {
        return LoRDeckCode.decode(deck_code);
    }
    
    public List<String> getFactions()
    {
        return factions;
    }
    
    public String getGameOutcome()
    {
        return game_outcome;
    }
    
    public boolean didWin() {
        return game_outcome.equalsIgnoreCase("win");
    }
    
    public Integer getOrderOfPlay()
    {
        return order_of_play;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        LORParticipant that = (LORParticipant) o;
        return Objects.equals(puuid, that.puuid) &&
               Objects.equals(deck_id, that.deck_id) &&
               Objects.equals(deck_code, that.deck_code) &&
               Objects.equals(factions, that.factions) &&
               Objects.equals(game_outcome, that.game_outcome) &&
               Objects.equals(order_of_play, that.order_of_play);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, deck_id, deck_code, factions, game_outcome, order_of_play);
    }
    
    @Override
    public String toString()
    {
        return "LORParticipant{" +
               "puuid='" + puuid + '\'' +
               ", deck_id='" + deck_id + '\'' +
               ", deck_code='" + deck_code + '\'' +
               ", factions=" + factions +
               ", game_outcome='" + game_outcome + '\'' +
               ", order_of_play=" + order_of_play +
               '}';
    }
}
