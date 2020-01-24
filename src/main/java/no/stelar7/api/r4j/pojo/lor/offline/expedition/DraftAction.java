package no.stelar7.api.r4j.pojo.lor.offline.expedition;

import no.stelar7.api.r4j.pojo.lor.offline.card.LoRCard;

import java.util.*;
import java.util.stream.Collectors;

public class DraftAction
{
    private boolean      IsSwap;
    private List<String> Picks;
    private String       SwappedIn;
    private String       SwappedOut;
    
    private List<LoRCard> realPicks;
    
    public List<LoRCard> getPicksAsCards()
    {
        if (realPicks == null)
        {
            realPicks = Picks.stream().map(LoRCard::create).collect(Collectors.toList());
        }
        
        return realPicks;
    }
    
    public boolean isSwap()
    {
        return IsSwap;
    }
    
    public List<String> getPicks()
    {
        return Picks;
    }
    
    public String getSwappedIn()
    {
        return SwappedIn;
    }
    
    public String getSwappedOut()
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
               ", swappedIn='" + SwappedIn + '\'' +
               ", swappedOut='" + SwappedOut + '\'' +
               '}';
    }
}
