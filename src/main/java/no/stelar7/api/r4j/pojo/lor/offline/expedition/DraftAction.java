package no.stelar7.api.r4j.pojo.lor.offline.expedition;

import no.stelar7.api.r4j.pojo.lor.offline.card.LoRCard;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class DraftAction implements Serializable
{
    private static final long serialVersionUID = 1801267320768200999L;
    private boolean IsSwap;
    private List<String> Picks;
    private List<String> SwappedIn;
    private List<String> SwappedOut;
    
    private List<LoRCard> realPicks;
    private List<LoRCard> realSwapIn;
    private List<LoRCard> realSwapOut;
    
    public List<LoRCard> getPicksAsCards()
    {
        if (realPicks == null)
        {
            realPicks = Picks.stream().map(LoRCard::create).collect(Collectors.toList());
        }
        
        return realPicks;
    }
    
    public List<LoRCard> getSwapInAsCards()
    {
        if (realSwapIn == null)
        {
            realSwapIn = SwappedIn.stream().map(LoRCard::create).collect(Collectors.toList());
        }
        
        return realSwapIn;
    }
    
    public List<LoRCard> getSwapOutAsCards()
    {
        if (realSwapOut == null)
        {
            realSwapOut = SwappedOut.stream().map(LoRCard::create).collect(Collectors.toList());
        }
        
        return realSwapOut;
    }
    
    
    public boolean isSwap()
    {
        return IsSwap;
    }
    
    public List<String> getPicks()
    {
        return Picks;
    }
    
    public List<String> getSwappedIn()
    {
        return SwappedIn;
    }
    
    public List<String> getSwappedOut()
    {
        return SwappedOut;
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
        DraftAction that = (DraftAction) o;
        return IsSwap == that.IsSwap &&
               Objects.equals(Picks, that.Picks) &&
               Objects.equals(SwappedIn, that.SwappedIn) &&
               Objects.equals(SwappedOut, that.SwappedOut);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(IsSwap, Picks, SwappedIn, SwappedOut);
    }
    
    @Override
    public String toString()
    {
        return "DraftAction{" +
               "isSwap=" + IsSwap +
               ", picks=" + Picks +
               ", swappedIn=" + SwappedIn +
               ", swappedOut=" + SwappedOut +
               '}';
    }
}
