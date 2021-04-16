package no.stelar7.api.r4j.pojo.lor.ranked;

import java.io.Serializable;
import java.util.*;

public class LoRRankedPlayerList implements Serializable
{
    private static final long serialVersionUID = 5861021754393622973L;
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
