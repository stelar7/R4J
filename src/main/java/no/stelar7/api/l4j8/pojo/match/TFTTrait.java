package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;
import java.util.Objects;

public class TFTTrait implements Serializable
{
    private String name;
    private int    numUnits;
    private int    tierCurrent;
    private int    tierTotal;
    
    public String getName()
    {
        return name;
    }
    
    public int getNumUnits()
    {
        return numUnits;
    }
    
    public int getTierCurrent()
    {
        return tierCurrent;
    }
    
    public int getTierTotal()
    {
        return tierTotal;
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
        return numUnits == tftTrait.numUnits &&
               tierCurrent == tftTrait.tierCurrent &&
               tierTotal == tftTrait.tierTotal &&
               Objects.equals(name, tftTrait.name);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, numUnits, tierCurrent, tierTotal);
    }
    
    @Override
    public String toString()
    {
        return "TFTTrait{" +
               "name='" + name + '\'' +
               ", numUnits=" + numUnits +
               ", tierCurrent=" + tierCurrent +
               ", tierTotal=" + tierTotal +
               '}';
    }
}
