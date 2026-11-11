package no.stelar7.api.r4j.pojo.riftbound;

import java.io.Serializable;
import java.util.*;

public class RiftboundCardStats implements Serializable
{
    private static final long serialVersionUID = 1L;

    private long energy;
    private long might;
    private long cost;
    private long power;

    public long getEnergy()
    {
        return energy;
    }
    
    public long getMight()
    {
        return might;
    }
    
    public long getCost()
    {
        return cost;
    }
    
    public long getPower()
    {
        return power;
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
        RiftboundCardStats that = (RiftboundCardStats) o;
        return energy == that.energy &&
               might == that.might &&
               cost == that.cost &&
               power == that.power;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(energy, might, cost, power);
    }

    @Override
    public String toString()
    {
        return "RiftboundCardStats{" +
               "energy=" + energy +
               ", might=" + might +
               ", cost=" + cost +
               ", power=" + power +
               '}';
    }
}
