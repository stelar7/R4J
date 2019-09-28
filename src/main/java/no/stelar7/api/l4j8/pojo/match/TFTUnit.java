package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;
import java.util.Objects;

public class TFTUnit implements Serializable
{
    private String name;
    private int    tier;
    
    public String getName()
    {
        return name;
    }
    
    public int getTier()
    {
        return tier;
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
        TFTUnit tftUnit = (TFTUnit) o;
        return tier == tftUnit.tier &&
               Objects.equals(name, tftUnit.name);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, tier);
    }
    
    @Override
    public String toString()
    {
        return "TFTUnit{" +
               "name='" + name + '\'' +
               ", tier=" + tier +
               '}';
    }
}
