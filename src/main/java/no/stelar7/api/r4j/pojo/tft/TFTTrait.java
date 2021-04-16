package no.stelar7.api.r4j.pojo.tft;

import java.io.Serializable;
import java.util.Objects;

public class TFTTrait implements Serializable
{
    private static final long serialVersionUID = -5245574494616768077L;
    
    private String name;
    private int    num_units;
    private int    tier_current;
    private int    tier_total;
    private int    style;
    
    public String getName()
    {
        return name;
    }
    
    public int getNumUnits()
    {
        return num_units;
    }
    
    public int getStyle()
    {
        return style;
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
               style == tftTrait.style &&
               Objects.equals(name, tftTrait.name);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, num_units, tier_current, tier_total, style);
    }
    
    @Override
    public String toString()
    {
        return "TFTTrait{" +
               "name='" + name + '\'' +
               ", num_units=" + num_units +
               ", tier_current=" + tier_current +
               ", tier_total=" + tier_total +
               ", style=" + style +
               '}';
    }
}
