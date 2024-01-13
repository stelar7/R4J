package no.stelar7.api.r4j.pojo.lol.spectator.v5;

import java.io.Serializable;
import java.util.Objects;

public class SpectatorConnectionInformation implements Serializable
{
    private static final long serialVersionUID = -1844778129823642717L;
    
    private long              reconnectDelay;
    private String            gameName;
    private Game              game;
    private PlayerCredentials playerCredentials;
    
    public long getReconnectDelay()
    {
        return reconnectDelay;
    }
    
    public String getGameName()
    {
        return gameName;
    }
    
    public Game getGame()
    {
        return game;
    }
    
    public PlayerCredentials getPlayerCredentials()
    {
        return playerCredentials;
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
        SpectatorConnectionInformation that = (SpectatorConnectionInformation) o;
        return reconnectDelay == that.reconnectDelay && Objects.equals(gameName, that.gameName) && Objects.equals(game, that.game) && Objects.equals(
                playerCredentials,
                that.playerCredentials);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(reconnectDelay, gameName, game, playerCredentials);
    }
    
    @Override
    public String toString()
    {
        return "SpectatorConnectionInformation{" +
               "reconnectDelay=" + reconnectDelay +
               ", gameName='" + gameName + '\'' +
               ", game=" + game +
               ", playerCredentials=" + playerCredentials +
               '}';
    }
}
