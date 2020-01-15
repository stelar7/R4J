package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGameState
{
    private String gameMode;
    private float  gameTime;
    private String mapName;
    private int    mapNumber;
    
    public String getGameMode()
    {
        return gameMode;
    }
    
    public float getGameTime()
    {
        return gameTime;
    }
    
    public String getMapName()
    {
        return mapName;
    }
    
    public int getMapNumber()
    {
        return mapNumber;
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
        ActiveGameState that = (ActiveGameState) o;
        return Float.compare(that.gameTime, gameTime) == 0 &&
               mapNumber == that.mapNumber &&
               Objects.equals(gameMode, that.gameMode) &&
               Objects.equals(mapName, that.mapName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(gameMode, gameTime, mapName, mapNumber);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGameState{" +
               "gameMode='" + gameMode + '\'' +
               ", gameTime=" + gameTime +
               ", mapName='" + mapName + '\'' +
               ", mapNumber=" + mapNumber +
               '}';
    }
}
