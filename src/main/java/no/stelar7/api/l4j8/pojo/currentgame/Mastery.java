package no.stelar7.api.l4j8.pojo.currentgame;

public class Mastery
{
    private Long    masteryId;
    private Integer rank;
    
    
    /**
     * The ID of the mastery
     *
     * @return Long
     */
    public Long getMasteryId()
    {
        return this.masteryId;
    }
    
    /**
     * The number of points put into this mastery by the user
     *
     * @return Integer
     */
    public Integer getRank()
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
        
        if ((masteryId != null) ? !masteryId.equals(mastery.masteryId) : (mastery.masteryId != null))
        {
            return false;
        }
        return (rank != null) ? rank.equals(mastery.rank) : (mastery.rank == null);
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
        return "Mastery{" +
               "masteryId=" + masteryId +
               ", rank=" + rank +
               '}';
    }
}
