package no.stelar7.api.l4j8.pojo.shared;

import no.stelar7.api.l4j8.basic.cache.StaticCache;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;

public class BannedChampion
{
    private int championId;
    private int pickTurn;
    
    
    /**
     * The ID of the banned champion
     *
     * @return long
     */
    public StaticChampion getChampion()
    {
        return StaticCache.getInstance().getChampion(this.championId);
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
