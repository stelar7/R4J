package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.*;

public class MatchPerks implements Serializable
{
    private static final long serialVersionUID = -8424250372683028377L;
    
    private StatPerk        statPerks;
    private List<PerkStyle> styles;
    
    public StatPerk getStatPerks()
    {
        return statPerks;
    }
    
    public List<PerkStyle> getPerkStyles()
    {
        return styles;
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
        return Objects.equals(statPerks, that.statPerks) && Objects.equals(styles, that.styles);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(statPerks, styles);
    }
    
    @Override
    public String toString()
    {
        return "MatchPerks{" +
               "statPerks=" + statPerks +
               ", perkStyles=" + styles +
               '}';
    }
}
