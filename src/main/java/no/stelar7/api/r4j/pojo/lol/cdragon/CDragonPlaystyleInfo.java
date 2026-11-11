package no.stelar7.api.r4j.pojo.lol.cdragon;

import java.io.Serializable;
import java.util.*;

public class CDragonPlaystyleInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int damage;
    private int durability;
    private int crowdControl;
    private int mobility;
    private int utility;

    public int getDamage()
    {
        return damage;
    }
    
    public int getDurability()
    {
        return durability;
    }
    
    public int getCrowdControl()
    {
        return crowdControl;
    }
    
    public int getMobility()
    {
        return mobility;
    }
    
    public int getUtility()
    {
        return utility;
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
        CDragonPlaystyleInfo that = (CDragonPlaystyleInfo) o;
        return damage == that.damage &&
               durability == that.durability &&
               crowdControl == that.crowdControl &&
               mobility == that.mobility &&
               utility == that.utility;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(damage, durability, crowdControl, mobility, utility);
    }

    @Override
    public String toString()
    {
        return "CDragonPlaystyleInfo{" +
               "damage=" + damage +
               ", durability=" + durability +
               ", crowdControl=" + crowdControl +
               ", mobility=" + mobility +
               ", utility=" + utility +
               '}';
    }
}
