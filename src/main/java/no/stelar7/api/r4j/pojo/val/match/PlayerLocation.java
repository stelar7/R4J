package no.stelar7.api.r4j.pojo.val.match;

import java.io.Serializable;
import java.util.Objects;

public class PlayerLocation implements Serializable
{
    private static final long serialVersionUID = -722332664254204236L;
    
    private String puuid;
    private float    viewRadians;
    private Location location;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public float getViewRadians()
    {
        return viewRadians;
    }
    
    public Location getLocation()
    {
        return location;
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
        PlayerLocation that = (PlayerLocation) o;
        return Float.compare(that.viewRadians, viewRadians) == 0 &&
               Objects.equals(puuid, that.puuid) &&
               Objects.equals(location, that.location);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, viewRadians, location);
    }
    
    @Override
    public String toString()
    {
        return "PlayerLocation{" +
               "puuid='" + puuid + '\'' +
               ", viewRadians=" + viewRadians +
               ", location=" + location +
               '}';
    }
}
