package no.stelar7.api.l4j8.pojo.match;

public class MatchRune
{
    private Long rank;
    private Long runeId;
    
    
    /**
     * Gets the rank.
     *
     * @return the rank
     */
    public Long getRank()
    {
        return this.rank;
    }
    
    /**
     * Gets the rune id.
     *
     * @return the rune id
     */
    public Long getRuneId()
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
        
        MatchRune rune = (MatchRune) o;
        
        if ((rank != null) ? !rank.equals(rune.rank) : (rune.rank != null))
        {
            return false;
        }
        return (runeId != null) ? runeId.equals(rune.runeId) : (rune.runeId == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (runeId != null ? runeId.hashCode() : 0);
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
