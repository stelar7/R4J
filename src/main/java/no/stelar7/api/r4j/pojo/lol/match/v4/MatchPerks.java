package no.stelar7.api.r4j.pojo.lol.match.v4;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

public class MatchPerks implements Serializable
{
    private static final long serialVersionUID = 3983457177783676301L;
    
    @SerializedName("perks")
    private List<MatchPerk> perkList;
    private int             perkPrimaryStyle;
    private int             perkSubStyle;
    private int             statPerk0;
    private int             statPerk1;
    private int             statPerk2;
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    
    public List<MatchPerk> getPerkList()
    {
        return perkList;
    }
    
    public int getPerkPrimaryStyle()
    {
        return perkPrimaryStyle;
    }
    
    public int getPerkSubStyle()
    {
        return perkSubStyle;
    }
    
    public int getStatPerk0()
    {
        return statPerk0;
    }
    
    public int getStatPerk1()
    {
        return statPerk1;
    }
    
    public int getStatPerk2()
    {
        return statPerk2;
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
        return perkPrimaryStyle == that.perkPrimaryStyle &&
               perkSubStyle == that.perkSubStyle &&
               statPerk0 == that.statPerk0 &&
               statPerk1 == that.statPerk1 &&
               statPerk2 == that.statPerk2 &&
               Objects.equals(perkList, that.perkList);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(perkList, perkPrimaryStyle, perkSubStyle, statPerk0, statPerk1, statPerk2);
    }
    
    @Override
    public String toString()
    {
        return "MatchPerks{" +
               "perkList=" + perkList +
               ", perkPrimaryStyle=" + perkPrimaryStyle +
               ", perkSubStyle=" + perkSubStyle +
               ", statPerk1=" + statPerk0 +
               ", statPerk2=" + statPerk1 +
               ", statPerk3=" + statPerk2 +
               '}';
    }
}
