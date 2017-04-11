package no.stelar7.api.l4j8.pojo.staticdata.champion;

import no.stelar7.api.l4j8.pojo.shared.*;
import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.util.*;

public class StaticChampionSpell extends BaseSpellData
{
    private List<Image> altimages;
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final StaticChampionSpell other = (StaticChampionSpell) obj;
        if (super.equals(other))
        {
            return false;
        }
        if (this.altimages == null)
        {
            if (other.altimages != null)
            {
                return false;
            }
        } else if (!this.altimages.equals(other.altimages))
        {
            return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.altimages == null) ? 0 : this.altimages.hashCode());
        result = (prime * result) + super.hashCode();
        return result;
    }
    
}
