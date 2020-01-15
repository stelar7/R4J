package no.stelar7.api.r4j.pojo.lol.spectator;

import java.io.Serializable;

public class SpectatorMastery implements Serializable
{
    private static final long serialVersionUID = -659069564329942304L;
    
    private long masteryId;
    private int  rank;
    
    
    /**
     * The ID of the mastery
     *
     * @return long
     */
    public long getMasteryId()
    {
        return this.masteryId;
    }
    
    /**
     * The number of points put into this mastery by the user
     *
     * @return int
     */
    public int getRank()
    {
        return this.rank;
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
        
        SpectatorMastery that = (SpectatorMastery) o;
        
        if (masteryId != that.masteryId)
        {
            return false;
        }
        return rank == that.rank;
    }
    
    @Override
    public int hashCode()
    {
        int result = (int) (masteryId ^ (masteryId >>> 32));
        result = 31 * result + rank;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "SpectatorMastery{" +
               "masteryId=" + masteryId +
               ", rank=" + rank +
               '}';
    }
}
