package no.stelar7.api.r4j.pojo.lol.cdragon;

import java.io.Serializable;
import java.util.*;

public class CDragonTacticalInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int    style;
    private int    difficulty;
    private String damageType;
    private String attackType;

    public int getStyle()
    {
        return style;
    }
    
    public int getDifficulty()
    {
        return difficulty;
    }
    
    public String getDamageType()
    {
        return damageType;
    }
    
    public String getAttackType()
    {
        return attackType;
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
        CDragonTacticalInfo that = (CDragonTacticalInfo) o;
        return style == that.style &&
               difficulty == that.difficulty &&
               Objects.equals(damageType, that.damageType) &&
               Objects.equals(attackType, that.attackType);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(style, difficulty, damageType, attackType);
    }

    @Override
    public String toString()
    {
        return "CDragonTacticalInfo{" +
               "style=" + style +
               ", difficulty=" + difficulty +
               ", damageType=" + damageType +
               ", attackType=" + attackType +
               '}';
    }
}
