package no.stelar7.api.l4j8.pojo.match.tft;

import java.io.Serializable;
import java.util.Objects;

public class TFTTrait implements Serializable
{
    private String name;
    private int    num_units;
    private int    tier_current;
    private int    tier_total;
    
    public String getName()
    {
        return name;
    }
    
    public int getNumUnits()
    {
        return num_units;
    }
    
    public int getTierCurrent()
    {
        return tier_current;
    }
    
    public int getTierTotal()
    {
        return tier_total;
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
        TFTTrait tftTrait = (TFTTrait) o;
        return num_units == tftTrait.num_units &&
               tier_current == tftTrait.tier_current &&
               tier_total == tftTrait.tier_total &&
               Objects.equals(name, tftTrait.name);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, num_units, tier_current, tier_total);
    }
    
    @Override
    public String toString()
    {
        return "TFTTrait{" +
               "name='" + name + '\'' +
               ", num_units=" + num_units +
               ", tier_current=" + tier_current +
               ", tier_total=" + tier_total +
               '}';
    }
}
