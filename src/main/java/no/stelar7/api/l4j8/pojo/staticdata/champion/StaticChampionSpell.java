package no.stelar7.api.l4j8.pojo.staticdata.champion;

import no.stelar7.api.l4j8.pojo.shared.*;
import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.util.*;

public class StaticChampionSpell extends BaseSpellData
{
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
    
        return (altimages != null) ? altimages.equals(that.altimages) : (that.altimages == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (altimages != null ? altimages.hashCode() : 0);
        return result;
    }
}
