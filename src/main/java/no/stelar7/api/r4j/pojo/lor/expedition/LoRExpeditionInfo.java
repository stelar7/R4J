package no.stelar7.api.r4j.pojo.lor.expedition;

import no.stelar7.api.r4j.pojo.lor.card.LoRCard;

import java.util.*;
import java.util.stream.Collectors;

public class LoRExpeditionInfo
{
    private boolean         isActive;
    private ExpeditionState state;
    private List<String>    record;
    private List<LoRCard>   deckReal;
    private List<String>    deck;
    private int             games;
    private int             wins;
    private int             losses;
    
    public boolean isActive()
    {
        return isActive;
    }
    
    public ExpeditionState getState()
    {
        return state;
    }
    
    public List<String> getRecord()
    {
        return record;
    }
    
    public List<String> getDeckAsCodes()
    {
        return deck;
    }
    
    public List<LoRCard> getDeck()
    {
        if (deckReal == null)
        {
            deckReal = deck.stream().map(LoRCard::create).collect(Collectors.toList());
        }
        
        return deckReal;
    }
    
    public int getGames()
    {
        return games;
    }
    
    public int getWins()
    {
        return wins;
    }
    
    public int getLosses()
    {
        return losses;
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
        LoRExpeditionInfo that = (LoRExpeditionInfo) o;
        return isActive == that.isActive &&
               games == that.games &&
               wins == that.wins &&
               losses == that.losses &&
               state == that.state &&
               Objects.equals(record, that.record) &&
               Objects.equals(deck, that.deck);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(isActive, state, record, deck, games, wins, losses);
    }
    
    @Override
    public String toString()
    {
        return "LoRExpeditionInfo{" +
               "isActive=" + isActive +
               ", state=" + state +
               ", record=" + record +
               ", deck=" + deck +
               ", games=" + games +
               ", wins=" + wins +
               ", losses=" + losses +
               '}';
    }
}
