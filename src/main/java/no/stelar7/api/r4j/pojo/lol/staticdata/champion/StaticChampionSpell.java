package no.stelar7.api.r4j.pojo.lol.staticdata.champion;

import no.stelar7.api.r4j.pojo.lol.shared.BaseSpellData;
import no.stelar7.api.r4j.pojo.lol.staticdata.shared.Image;

import java.util.*;

public class StaticChampionSpell extends BaseSpellData
{
    private static final long serialVersionUID = 3332883769496451613L;
    
    private List<Image> altimages;
    
    public List<Image> getAltImages()
    {
        return altimages;
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
        if (!super.equals(o))
        {
            return false;
        }
        
        StaticChampionSpell that = (StaticChampionSpell) o;
        
        return Objects.equals(altimages, that.altimages);
    }
    
    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (altimages != null ? altimages.hashCode() : 0);
        return result;
    }
}
