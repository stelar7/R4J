package no.stelar7.api.l4j8.pojo.match.tft;

import java.io.Serializable;
import java.util.*;

public class TFTUnit implements Serializable
{
    private String       name;
    private int          tier;
    private List<Object> items;
    
    public String getName()
    {
        return name;
    }
    
    public int getTier()
    {
        return tier;
    }
    
    public List<Object> getItems()
    {
        return items;
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
               Objects.equals(name, tftUnit.name) &&
               Objects.equals(items, tftUnit.items);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, tier, items);
    }
    
    @Override
    public String toString()
    {
        return "TFTUnit{" +
               "name='" + name + '\'' +
               ", tier=" + tier +
               ", items=" + items +
               '}';
    }
}
