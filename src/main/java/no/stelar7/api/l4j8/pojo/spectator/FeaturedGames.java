package no.stelar7.api.l4j8.pojo.spectator;

import java.util.List;

public class FeaturedGames
{
    private Long                    clientRefreshInterval;
    private List<SpectatorGameInfo> gameList;
    
    public Long getClientRefreshInterval()
    {
        return clientRefreshInterval;
    }
    
    public List<SpectatorGameInfo> getGameList()
    {
        return gameList;
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
        
        FeaturedGames that = (FeaturedGames) o;
        
        if ((clientRefreshInterval != null) ? !clientRefreshInterval.equals(that.clientRefreshInterval) : (that.clientRefreshInterval != null))
        {
            return false;
        }
        return (gameList != null) ? gameList.equals(that.gameList) : (that.gameList == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = clientRefreshInterval != null ? clientRefreshInterval.hashCode() : 0;
        result = 31 * result + (gameList != null ? gameList.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "FeaturedGames{" +
               "clientRefreshInterval=" + clientRefreshInterval +
               ", gameList=" + gameList +
               '}';
    }
}
