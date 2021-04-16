package no.stelar7.api.r4j.pojo.lor.offline.game;

import java.io.Serializable;
import java.util.Objects;

public class LoRGameResult implements Serializable
{
    private static final long    serialVersionUID = -7123352486444349580L;
    private              String  GameID;
    private              boolean LocalPlayerWon;
    
    public String getGameId()
    {
        return GameID;
    }
    
    public boolean isLocalPlayerWon()
    {
        return LocalPlayerWon;
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
        LoRGameResult that = (LoRGameResult) o;
        return LocalPlayerWon == that.LocalPlayerWon &&
               Objects.equals(GameID, that.GameID);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(GameID, LocalPlayerWon);
    }
    
    @Override
    public String toString()
    {
        return "LoRGameResult{" +
               "GameId='" + GameID + '\'' +
               ", LocalPlayerWon=" + LocalPlayerWon +
               '}';
    }
}
