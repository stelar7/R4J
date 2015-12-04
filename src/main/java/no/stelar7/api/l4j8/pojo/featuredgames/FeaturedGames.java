package no.stelar7.api.l4j8.pojo.featuredgames;

import java.util.List;

public class FeaturedGames
{
    Long                   clientRefreshInterval;
    List<FeaturedGameInfo> gameList;

    /**
     * The suggested interval to wait before requesting FeaturedGames again
     *
     * @return the client refresh interval
     */
    public Long getClientRefreshInterval()
    {
        return clientRefreshInterval;
    }

    /**
     * The list of featured games
     *
     * @return the game list
     */
    public List<FeaturedGameInfo> getGameList()
    {
        return gameList;
    }

    @Override
    public String toString()
    {
        return "FeaturedGames [clientRefreshInterval=" + clientRefreshInterval + ", gameList=" + gameList + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clientRefreshInterval == null) ? 0 : clientRefreshInterval.hashCode());
        result = prime * result + ((gameList == null) ? 0 : gameList.hashCode());
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
        FeaturedGames other = (FeaturedGames) obj;
        if (clientRefreshInterval == null)
        {
            if (other.clientRefreshInterval != null)
                return false;
        } else if (!clientRefreshInterval.equals(other.clientRefreshInterval))
            return false;
        if (gameList == null)
        {
            if (other.gameList != null)
                return false;
        } else if (!gameList.equals(other.gameList))
            return false;
        return true;
    }

}
