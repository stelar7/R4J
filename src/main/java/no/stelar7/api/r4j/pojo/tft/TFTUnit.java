package no.stelar7.api.r4j.pojo.tft;

import java.io.Serializable;
import java.util.*;

public class TFTUnit implements Serializable
{
    private String        name;
    private int           tier;
    private int           rarity;
    private String        character_id;
    private List<Integer> items;
    
    public String getName()
    {
        return name;
    }
    
    public int getTier()
    {
        return tier;
    }
    
    public int getRarity()
    {
        return rarity;
    }
    
    public String getCharacterId()
    {
        return character_id;
    }
    
    public List<Integer> getItems()
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
               rarity == tftUnit.rarity &&
               Objects.equals(name, tftUnit.name) &&
               Objects.equals(character_id, tftUnit.character_id) &&
               Objects.equals(items, tftUnit.items);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, tier, rarity, character_id, items);
    }
    
    @Override
    public String toString()
    {
        return "TFTUnit{" +
               "name='" + name + '\'' +
               ", tier=" + tier +
               ", rarity=" + rarity +
               ", character_id='" + character_id + '\'' +
               ", items=" + items +
               '}';
    }
}
