package no.stelar7.api.l4j8.pojo.matchlist;

import java.util.List;

public class MatchList
{
    Integer              endIndex;
    Integer              startIndex;
    Integer              totalGames;
    List<MatchReference> matches;

    /**
     * Gets the end index.
     *
     * @return the end index
     */
    public Integer getEndIndex()
    {
        return endIndex;
    }

    /**
     * Gets the start index.
     *
     * @return the start index
     */
    public Integer getStartIndex()
    {
        return startIndex;
    }

    /**
     * Gets the total games.
     *
     * @return the total games
     */
    public Integer getTotalGames()
    {
        return totalGames;
    }

    /**
     * Gets the matches.
     *
     * @return the matches
     */
    public List<MatchReference> getMatches()
    {
        return matches;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endIndex == null) ? 0 : endIndex.hashCode());
        result = prime * result + ((matches == null) ? 0 : matches.hashCode());
        result = prime * result + ((startIndex == null) ? 0 : startIndex.hashCode());
        result = prime * result + ((totalGames == null) ? 0 : totalGames.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MatchList other = (MatchList) obj;
        if (endIndex == null)
        {
            if (other.endIndex != null)
                return false;
        } else if (!endIndex.equals(other.endIndex))
            return false;
        if (matches == null)
        {
            if (other.matches != null)
                return false;
        } else if (!matches.equals(other.matches))
            return false;
        if (startIndex == null)
        {
            if (other.startIndex != null)
                return false;
        } else if (!startIndex.equals(other.startIndex))
            return false;
        if (totalGames == null)
        {
            if (other.totalGames != null)
                return false;
        } else if (!totalGames.equals(other.totalGames))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "MatchList [endIndex=" + endIndex + ", startIndex=" + startIndex + ", totalGames=" + totalGames + ", matches=" + matches + "]";
    }

}
