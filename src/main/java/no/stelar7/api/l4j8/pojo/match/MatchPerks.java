package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;
import java.util.List;

public class MatchPerks implements Serializable
{
    private static final long serialVersionUID = 3983457177783676301L;
    
    private List<MatchPerk> perks;
    private int             perkPrimaryStyle;
    private int             perkSubStyle;
    
    public List<MatchPerk> getPerks()
    {
        return perks;
    }
    
    public int getPerkPrimaryStyle()
    {
        return perkPrimaryStyle;
    }
    
    public int getPerkSubStyle()
    {
        return perkSubStyle;
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
        
        MatchPerks that = (MatchPerks) o;
        
        if (perkPrimaryStyle != that.perkPrimaryStyle)
        {
            return false;
        }
        if (perkSubStyle != that.perkSubStyle)
        {
            return false;
        }
        return (perks != null) ? perks.equals(that.perks) : (that.perks == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = perks != null ? perks.hashCode() : 0;
        result = 31 * result + perkPrimaryStyle;
        result = 31 * result + perkSubStyle;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchPerks{" +
               "perks=" + perks +
               ", perkPrimaryStyle=" + perkPrimaryStyle +
               ", perkSubStyle=" + perkSubStyle +
               '}';
    }
}
