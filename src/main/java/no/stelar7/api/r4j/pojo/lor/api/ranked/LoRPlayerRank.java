package no.stelar7.api.r4j.pojo.lor.api.ranked;

import java.util.Objects;

public class LoRPlayerRank
{
    private String name;
    private int    rank;
    
    public String getName()
    {
        return name;
    }
    
    public int getRank()
    {
        return rank;
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
               Objects.equals(name, that.name);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, rank);
    }
    
    @Override
    public String toString()
    {
        return "LoRPlayerRank{" +
               "name='" + name + '\'' +
               ", rank=" + rank +
               '}';
    }
}
