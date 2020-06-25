package no.stelar7.api.r4j.pojo.lor.ranked;

import java.io.Serializable;
import java.util.Objects;

public class LoRPlayerRank implements Serializable
{
    private static final long   serialVersionUID = 1648942165181218107L;
    private              String name;
    private              int    rank;
    private              int    lp;
    
    public String getName()
    {
        return name;
    }
    
    public int getRank()
    {
        return rank;
    }
    
    public int getLP()
    {
        return lp;
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
        LoRPlayerRank that = (LoRPlayerRank) o;
        return rank == that.rank &&
               lp == that.lp &&
               Objects.equals(name, that.name);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, rank, lp);
    }
    
    @Override
    public String toString()
    {
        return "LoRPlayerRank{" +
               "name='" + name + '\'' +
               ", rank=" + rank +
               ", lp=" + lp +
               '}';
    }
}
