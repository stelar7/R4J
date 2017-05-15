package no.stelar7.api.l4j8.pojo.match;

public class MatchMastery
{
    private long masteryId;
    private long rank;
    
    
    /**
     * MatchMastery ID
     *
     * @return long
     */
    public long getMasteryId()
    {
        return this.masteryId;
    }
    
    /**
     * MatchMastery Rank
     *
     * @return long
     */
    public long getRank()
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
        int result = (int) (masteryId ^ (masteryId >>> 32));
        result = 31 * result + (int) (rank ^ (rank >>> 32));
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
