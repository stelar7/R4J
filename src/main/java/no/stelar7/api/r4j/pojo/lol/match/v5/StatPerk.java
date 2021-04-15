package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.Objects;

public class StatPerk implements Serializable
{
    private static final long serialVersionUID = 5341158852307028886L;
    
    private int defense;
    private int flex;
    private int offense;
    
    public int getDefense()
    {
        return defense;
    }
    
    public int getFlex()
    {
        return flex;
    }
    
    public int getOffense()
    {
        return offense;
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
        StatPerk statPerk = (StatPerk) o;
        return defense == statPerk.defense && flex == statPerk.flex && offense == statPerk.offense;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(defense, flex, offense);
    }
    
    @Override
    public String toString()
    {
        return "StatPerk{" +
               "defense=" + defense +
               ", flex=" + flex +
               ", offense=" + offense +
               '}';
    }
}
