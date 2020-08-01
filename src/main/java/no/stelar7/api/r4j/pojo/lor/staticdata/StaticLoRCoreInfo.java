package no.stelar7.api.r4j.pojo.lor.staticdata;

import java.io.Serializable;
import java.util.*;

public class StaticLoRCoreInfo implements Serializable
{
    private static final long serialVersionUID = -755283493769025055L;
    private List<StaticLoRKeyword> keywords;
    private List<StaticLoRRegion>     regions;
    private List<StaticLoRSpellSpeed> spellSpeeds;
    private List<StaticLoRRarity>     rarities;
    
    public List<StaticLoRKeyword> getKeywords()
    {
        
        return keywords;
    }
    
    public List<StaticLoRRegion> getRegions()
    {
        return regions;
    }
    
    public List<StaticLoRSpellSpeed> getSpellSpeeds()
    {
        return spellSpeeds;
    }
    
    public List<StaticLoRRarity> getRarities()
    {
        return rarities;
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
        StaticLoRCoreInfo that = (StaticLoRCoreInfo) o;
        return Objects.equals(keywords, that.keywords) &&
               Objects.equals(regions, that.regions) &&
               Objects.equals(spellSpeeds, that.spellSpeeds) &&
               Objects.equals(rarities, that.rarities);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(keywords, regions, spellSpeeds, rarities);
    }
    
    @Override
    public String toString()
    {
        return "StaticLoRCoreInfo{" +
               "keywords=" + keywords +
               ", regions=" + regions +
               ", spellSpeeds=" + spellSpeeds +
               ", rarities=" + rarities +
               '}';
    }
}
