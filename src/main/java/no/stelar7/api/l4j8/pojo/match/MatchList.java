package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;
import java.util.List;

public class MatchList implements Serializable
{
    private static final long serialVersionUID = -7351268441697208067L;
    
    private int                  endIndex;
    private int                  startIndex;
    private int                  totalGames;
    private List<MatchReference> matches;
    
    
    /**
     * Gets the end index.
     *
     * @return the end index
     */
    public int getEndIndex()
    {
        return this.endIndex;
    }
    
    /**
     * Gets the matches.
     *
     * @return the matches
     */
    public List<MatchReference> getMatches()
    {
        return this.matches;
    }
    
    /**
     * Gets the start index.
     *
     * @return the start index
     */
    public int getStartIndex()
    {
        return this.startIndex;
    }
    
    /**
     * Gets the total games.
     *
     * @return the total games
     */
    public int getTotalGames()
    {
        return this.totalGames;
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
        
        MatchList matchList = (MatchList) o;
        
        if (endIndex != matchList.endIndex)
        {
            return false;
        }
        if (startIndex != matchList.startIndex)
        {
            return false;
        }
        if (totalGames != matchList.totalGames)
        {
            return false;
        }
        return (matches != null) ? matches.equals(matchList.matches) : (matchList.matches == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = endIndex;
        result = 31 * result + startIndex;
        result = 31 * result + totalGames;
        result = 31 * result + (matches != null ? matches.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchList{" +
               "endIndex=" + endIndex +
               ", startIndex=" + startIndex +
               ", totalGames=" + totalGames +
               ", matches=" + matches +
               '}';
    }
}
