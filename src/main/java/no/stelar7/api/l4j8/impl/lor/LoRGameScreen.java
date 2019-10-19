package no.stelar7.api.l4j8.impl.lor;

import java.util.Objects;

public class LoRGameScreen
{
    private int ScreenWidth;
    private int ScreenHeight;
    
    public int getWidth()
    {
        return ScreenWidth;
    }
    
    public int getHeight()
    {
        return ScreenHeight;
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
        LoRGameScreen that = (LoRGameScreen) o;
        return ScreenWidth == that.ScreenWidth &&
               ScreenHeight == that.ScreenHeight;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(ScreenWidth, ScreenHeight);
    }
    
    @Override
    public String toString()
    {
        return "LoRGameScreen{" +
               "ScreenWidth=" + ScreenWidth +
               ", ScreenHeight=" + ScreenHeight +
               '}';
    }
}
