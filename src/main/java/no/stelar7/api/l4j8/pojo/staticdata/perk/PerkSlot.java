package no.stelar7.api.l4j8.pojo.staticdata.perk;

import java.io.Serializable;
import java.util.List;

public class PerkSlot implements Serializable
{
    private static final long             serialVersionUID = 7714828182065312379L;
    private              List<StaticPerk> runes;
    
    public List<StaticPerk> getRunes()
    {
        return runes;
    }
    
    @Override
    public String toString()
    {
        return "PerkSlot{" +
               "runes=" + runes +
               '}';
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
        
        PerkSlot perkSlot = (PerkSlot) o;
        
        return (runes != null) ? runes.equals(perkSlot.runes) : (perkSlot.runes == null);
    }
    
    @Override
    public int hashCode()
    {
        return runes != null ? runes.hashCode() : 0;
    }
}
