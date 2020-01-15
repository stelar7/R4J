package no.stelar7.api.r4j.pojo.lol.summoner.masteries;

import java.io.Serializable;

public class Mastery implements Serializable
{
    private static final long serialVersionUID = 4080793113482631140L;
    
    private int id;
    private int rank;
    
    
    /**
     * The mastery ID. For static information correlating to masteries, please refer to the LoL Static Data API.
     *
     * @return int
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * The mastery rank (i.e., the number of points put into this mastery).
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
        
        Mastery mastery = (Mastery) o;
        
        if (id != mastery.id)
        {
            return false;
        }
        return rank == mastery.rank;
    }
    
    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + rank;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "SpectatorMastery [id=" + this.id + ", rank=" + this.rank + "]";
    }
}
