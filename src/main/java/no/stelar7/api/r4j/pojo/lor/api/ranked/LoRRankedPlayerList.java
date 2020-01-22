package no.stelar7.api.r4j.pojo.lor.api.ranked;

import java.util.*;

public class LoRRankedPlayerList
{
    private List<LoRPlayerRank> players;
    
    public List<LoRPlayerRank> getPlayers()
    {
        return players;
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
        LoRRankedPlayerList that = (LoRRankedPlayerList) o;
        return Objects.equals(players, that.players);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(players);
    }
    
    @Override
    public String toString()
    {
        return "LoRRankedPlayerList{" +
               "players=" + players +
               '}';
    }
}
