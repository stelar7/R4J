package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;

public class MatchRune implements Serializable
{
    private int rank;
    private int runeId;
    
    
    /**
     * Gets the rank.
     *
     * @return the rank
     */
    public int getRank()
    {
        return this.rank;
    }
    
    /**
     * Gets the rune id.
     *
     * @return the rune id
     */
    public int getRuneId()
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
        int result = rank;
        result = 31 * result + runeId;
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
