package no.stelar7.api.r4j.pojo.riftbound;

import java.io.Serializable;
import java.util.*;

public class RiftboundContent implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String             game;
    private String             version;
    private String             lastUpdated;
    private List<RiftboundSet> sets;

    public String getGame()
    {
        return game;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public String getLastUpdated()
    {
        return lastUpdated;
    }
    
    public List<RiftboundSet> getSets()
    {
        return sets;
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
        RiftboundContent that = (RiftboundContent) o;
        return Objects.equals(game, that.game) &&
               Objects.equals(version, that.version) &&
               Objects.equals(lastUpdated, that.lastUpdated) &&
               Objects.equals(sets, that.sets);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(game, version, lastUpdated, sets);
    }

    @Override
    public String toString()
    {
        return "RiftboundContent{" +
               "game=" + game +
               ", version=" + version +
               ", lastUpdated=" + lastUpdated +
               ", sets=" + sets +
               '}';
    }
}
