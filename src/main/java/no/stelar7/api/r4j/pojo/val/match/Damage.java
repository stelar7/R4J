package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.utils.sql.SQLForeignMap;

import java.io.Serializable;
import java.util.*;

public class Damage implements Serializable
{
    private static final long serialVersionUID = -1161514106876666671L;
    
    private String receiver;
    private int    damage;
    private int    legshots;
    private int    bodyshots;
    private int    headshots;
    
    public String getReceiver()
    {
        return receiver;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public int getLegshots()
    {
        return legshots;
    }
    
    public int getBodyshots()
    {
        return bodyshots;
    }
    
    public int getHeadshots()
    {
        return headshots;
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
        Damage damage1 = (Damage) o;
        return Objects.equals(receiver, damage1.receiver) &&
               Objects.equals(damage, damage1.damage) &&
               Objects.equals(legshots, damage1.legshots) &&
               Objects.equals(bodyshots, damage1.bodyshots) &&
               Objects.equals(headshots, damage1.headshots);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(receiver, damage, legshots, bodyshots, headshots);
    }
    
    @Override
    public String toString()
    {
        return "Damage{" +
               "receiver='" + receiver + '\'' +
               ", damage=" + damage +
               ", legshots=" + legshots +
               ", bodyshots=" + bodyshots +
               ", headshots=" + headshots +
               '}';
    }
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(Match.class, "id");
        returnMap.put(RoundResult.class, "roundNum");
        returnMap.put(PlayerRoundStats.class, "puuid");
        
        return returnMap;
    }
}
