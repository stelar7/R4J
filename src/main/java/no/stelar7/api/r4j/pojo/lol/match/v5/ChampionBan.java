package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.Objects;

public class ChampionBan implements Serializable
{
    private static final long serialVersionUID = 9036737379955122323L;
    
    private int championId;
    private int pickTurn;
    
    public int getChampionId()
    {
        return championId;
    }
    
    public int getPickTurn()
    {
        return pickTurn;
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
        ChampionBan that = (ChampionBan) o;
        return championId == that.championId && pickTurn == that.pickTurn;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championId, pickTurn);
    }
    
    @Override
    public String toString()
    {
        return "ChampionBan{" +
               "championId=" + championId +
               ", pickTurn=" + pickTurn +
               '}';
    }
}
