package no.stelar7.api.r4j.pojo.lor.offline.expedition;

import no.stelar7.api.r4j.pojo.lor.offline.card.LoRCard;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class LoRExpeditionInfo implements Serializable
{
    private static final long serialVersionUID = -4021370386876229136L;
    private boolean IsActive;
    private ExpeditionState   State;
    private List<DraftAction> DraftPicks;
    private List<String>      Record;
    private List<LoRCard>     deckReal;
    private List<String>      Deck;
    private int               Games;
    private int               Wins;
    private int               Losses;
    
    public boolean isActive()
    {
        return IsActive;
    }
    
    public ExpeditionState getState()
    {
        return State;
    }
    
    /**
     * List of either "win" or "loss"
     * @return the list
     */
    public List<String> getRecord()
    {
        return Record;
    }
    
    public List<String> getDeckAsCodes()
    {
        return Deck;
    }
    
    public List<LoRCard> getDeck()
    {
        if (deckReal == null)
        {
            deckReal = Deck.stream().map(LoRCard::create).collect(Collectors.toList());
        }
        
        return deckReal;
    }
    
    public int getGames()
    {
        return Games;
    }
    
    public int getWins()
    {
        return Wins;
    }
    
    public int getLosses()
    {
        return Losses;
    }
    
    public List<DraftAction> getDraftPicks()
    {
        return DraftPicks;
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
        return IsActive == that.IsActive &&
               Games == that.Games &&
               Wins == that.Wins &&
               Losses == that.Losses &&
               State == that.State &&
               Objects.equals(DraftPicks, that.DraftPicks) &&
               Objects.equals(Record, that.Record) &&
               Objects.equals(deckReal, that.deckReal) &&
               Objects.equals(Deck, that.Deck);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(IsActive, State, DraftPicks, Record, deckReal, Deck, Games, Wins, Losses);
    }
    
    @Override
    public String toString()
    {
        return "LoRExpeditionInfo{" +
               "isActive=" + IsActive +
               ", state=" + State +
               ", draftPicks=" + DraftPicks +
               ", record=" + Record +
               ", deckReal=" + deckReal +
               ", deck=" + Deck +
               ", games=" + Games +
               ", wins=" + Wins +
               ", losses=" + Losses +
               '}';
    }
}
