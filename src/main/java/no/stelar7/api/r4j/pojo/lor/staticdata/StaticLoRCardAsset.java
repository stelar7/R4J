package no.stelar7.api.r4j.pojo.lor.staticdata;

import java.io.Serializable;
import java.util.Objects;

public class StaticLoRCardAsset implements Serializable
{
    private static final long serialVersionUID = -1066662532087610840L;
    private String gameAbsolutePath;
    private String fullAbsolutePath;
    
    public String getGameAbsolutePath()
    {
        return gameAbsolutePath;
    }
    
    public String getFullAbsolutePath()
    {
        return fullAbsolutePath;
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
        StaticLoRCardAsset that = (StaticLoRCardAsset) o;
        return Objects.equals(gameAbsolutePath, that.gameAbsolutePath) &&
               Objects.equals(fullAbsolutePath, that.fullAbsolutePath);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(gameAbsolutePath, fullAbsolutePath);
    }
    
    @Override
    public String toString()
    {
        return "StaticLoRCardAsset{" +
               "gameAbsolutePath='" + gameAbsolutePath + '\'' +
               ", fullAbsolutePath='" + fullAbsolutePath + '\'' +
               '}';
    }
}
