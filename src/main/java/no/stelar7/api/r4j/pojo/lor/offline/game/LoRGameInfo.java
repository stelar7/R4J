package no.stelar7.api.r4j.pojo.lor.offline.game;

import no.stelar7.api.r4j.pojo.lor.offline.game.LoRGameScreen.LoRCardRectangle;

import java.io.Serializable;
import java.util.*;

public class LoRGameInfo implements Serializable
{
    private static final long serialVersionUID = 6387985743594804427L;
    private String PlayerName;
    private String                 OpponentName;
    private LoRGameState           GameState;
    private LoRGameScreen          Screen;
    private List<LoRCardRectangle> Rectangles;
    
    public String getPlayerName()
    {
        return PlayerName;
    }
    
    public String getOpponentName()
    {
        return OpponentName;
    }
    
    public LoRGameState getGameState()
    {
        return GameState;
    }
    
    public LoRGameScreen getScreen()
    {
        return Screen;
    }
    
    public List<LoRCardRectangle> getRectangles()
    {
        return Rectangles;
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
        LoRGameInfo that = (LoRGameInfo) o;
        return Objects.equals(PlayerName, that.PlayerName) &&
               Objects.equals(OpponentName, that.OpponentName) &&
               GameState == that.GameState &&
               Objects.equals(Screen, that.Screen) &&
               Objects.equals(Rectangles, that.Rectangles);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(PlayerName, OpponentName, GameState, Screen, Rectangles);
    }
    
    @Override
    public String toString()
    {
        return "LoRGameInfo{" +
               "PlayerName='" + PlayerName + '\'' +
               ", OpponentName='" + OpponentName + '\'' +
               ", GameState=" + GameState +
               ", Screen=" + Screen +
               ", Rectangles=" + Rectangles +
               '}';
    }
}
