package no.stelar7.api.l4j8.impl.lor;

import java.util.Objects;

public class LoRGameResult
{
    private String  GameId;
    private boolean LocalPlayerWon;
    
    public String getGameId()
    {
        return GameId;
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
               Objects.equals(GameId, that.GameId);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(GameId, LocalPlayerWon);
    }
    
    @Override
    public String toString()
    {
        return "LoRGameResult{" +
               "GameId='" + GameId + '\'' +
               ", LocalPlayerWon=" + LocalPlayerWon +
               '}';
    }
}
