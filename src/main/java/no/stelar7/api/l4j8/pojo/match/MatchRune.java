package no.stelar7.api.l4j8.pojo.match;

public class MatchRune
{
    private long rank;
    private long runeId;
    
    
    /**
     * Gets the rank.
     *
     * @return the rank
     */
    public long getRank()
    {
        return this.rank;
    }
    
    /**
     * Gets the rune id.
     *
     * @return the rune id
     */
    public long getRuneId()
    {
        return this.runeId;
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
        
        MatchRune matchRune = (MatchRune) o;
        
        if (rank != matchRune.rank)
        {
            return false;
        }
        return runeId == matchRune.runeId;
    }
    
    @Override
    public int hashCode()
    {
        int result = (int) (rank ^ (rank >>> 32));
        result = 31 * result + (int) (runeId ^ (runeId >>> 32));
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchRune{" +
               "rank=" + rank +
               ", runeId=" + runeId +
               '}';
    }
}
