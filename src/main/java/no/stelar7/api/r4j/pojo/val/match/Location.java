package no.stelar7.api.r4j.pojo.val.match;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable
{
    private static final long serialVersionUID = -2841290000843430590L;
    
    private int x;
    private int y;
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
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
        Location location = (Location) o;
        return x == location.x &&
               y == location.y;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString()
    {
        return "Location{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
}
