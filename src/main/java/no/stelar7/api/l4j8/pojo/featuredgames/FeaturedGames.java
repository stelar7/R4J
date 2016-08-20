package no.stelar7.api.l4j8.pojo.featuredgames;

import java.util.List;

public class FeaturedGames
{
    private Long clientRefreshInterval;
    private List<FeaturedGameInfo> gameList;

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final FeaturedGames other = (FeaturedGames) obj;
        if (this.clientRefreshInterval == null)
        {
            if (other.clientRefreshInterval != null)
            {
                return false;
            }
        } else if (!this.clientRefreshInterval.equals(other.clientRefreshInterval))
        {
            return false;
        }
        if (this.gameList == null)
        {
            if (other.gameList != null)
            {
                return false;
            }
        } else if (!this.gameList.equals(other.gameList))
        {
            return false;
        }
        return true;
    }

    /**
     * The suggested interval to wait before requesting FeaturedGames again
     *
     * @return the client refresh interval
     */
    public Long getClientRefreshInterval()
    {
        return this.clientRefreshInterval;
    }

    /**
     * The list of featured games
     *
     * @return the game list
     */
    public List<FeaturedGameInfo> getGameList()
    {
        return this.gameList;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.clientRefreshInterval == null) ? 0 : this.clientRefreshInterval.hashCode());
        result = (prime * result) + ((this.gameList == null) ? 0 : this.gameList.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "FeaturedGames [clientRefreshInterval=" + this.clientRefreshInterval + ", gameList=" + this.gameList + "]";
    }

}
