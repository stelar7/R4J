package no.stelar7.api.r4j.pojo.lol.match.v4;

import java.io.Serializable;

public class MatchMastery implements Serializable
{
    private static final long serialVersionUID = -361609237123048297L;
    
    private int masteryId;
    private int rank;
    
    
    /**
     * MatchMastery ID
     *
     * @return long
     */
    public int getMasteryId()
    {
        return this.masteryId;
    }
    
    /**
     * MatchMastery Rank
     *
     * @return long
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
        
        MatchMastery that = (MatchMastery) o;
        
        if (masteryId != that.masteryId)
        {
            return false;
        }
        return rank == that.rank;
    }
    
    @Override
    public int hashCode()
    {
        int result = masteryId;
        result = 31 * result + rank;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchMastery{" +
               "masteryId=" + masteryId +
               ", rank=" + rank +
               '}';
    }
}
