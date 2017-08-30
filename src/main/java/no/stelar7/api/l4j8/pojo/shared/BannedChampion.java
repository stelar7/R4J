package no.stelar7.api.l4j8.pojo.shared;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.StaticAPI;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;

import java.io.Serializable;

public class BannedChampion implements Serializable
{
    private int championId;
    private int pickTurn;
    
    
    /**
     * The banned champion
     *
     * @return StaticChampion
     */
    public StaticChampion getChampion()
    {
        return StaticAPI.getInstance().getChampion(Platform.EUW1, this.championId, null, null, null);
    }
    
    /**
     * The ID of the banned champion
     * -1 if they did not ban
     *
     * @return long
     */
    public int getChampionId()
    {
        return this.championId;
    }
    
    /**
     * The turn during which the champion was banned
     *
     * @return int
     */
    public int getPickTurn()
    {
        return this.pickTurn;
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
        
        BannedChampion that = (BannedChampion) o;
        
        if (pickTurn != that.pickTurn)
        {
            return false;
        }
        return championId == that.championId;
    }
    
    @Override
    public int hashCode()
    {
        int result = championId;
        result = 31 * result + pickTurn;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "BannedChampion{" +
               "championId=" + championId +
               ", pickTurn=" + pickTurn +
               '}';
    }
}
