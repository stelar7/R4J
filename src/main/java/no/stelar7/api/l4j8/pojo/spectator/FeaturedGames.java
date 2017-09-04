package no.stelar7.api.l4j8.pojo.spectator;

import java.io.Serializable;
import java.util.List;

public class FeaturedGames implements Serializable
{
    private static final long serialVersionUID = -4396115183530070664L;
    
    private long                    clientRefreshInterval;
    private List<SpectatorGameInfo> gameList;
    
    public long getClientRefreshInterval()
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
        
        if (clientRefreshInterval != that.clientRefreshInterval)
        {
            return false;
        }
        return (gameList != null) ? gameList.equals(that.gameList) : (that.gameList == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = (int) (clientRefreshInterval ^ (clientRefreshInterval >>> 32));
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
