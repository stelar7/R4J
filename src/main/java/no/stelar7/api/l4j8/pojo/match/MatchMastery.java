package no.stelar7.api.l4j8.pojo.match;

public class MatchMastery
{
    private Long masteryId;
    private Long rank;
    
    
    /**
     * MatchMastery ID
     *
     * @return Long
     */
    public Long getMasteryId()
    {
        return this.masteryId;
    }
    
    /**
     * MatchMastery Rank
     *
     * @return Long
     */
    public Long getRank()
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
        
        if ((masteryId != null) ? !masteryId.equals(that.masteryId) : (that.masteryId != null))
        {
            return false;
        }
        return (rank != null) ? rank.equals(that.rank) : (that.rank == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = masteryId != null ? masteryId.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
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
