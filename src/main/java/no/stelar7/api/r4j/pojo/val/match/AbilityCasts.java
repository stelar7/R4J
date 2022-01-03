package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.utils.sql.SQLForeignMap;

import java.io.Serializable;
import java.util.*;

public class AbilityCasts implements Serializable
{
    private static final long serialVersionUID = -872236422642919060L;
    
    private int grenadeCasts;
    private int ability1Casts;
    private int ability2Casts;
    private int ultimateCasts;
    
    public int getGrenadeCasts()
    {
        return grenadeCasts;
    }
    
    public int getAbility1Casts()
    {
        return ability1Casts;
    }
    
    public int getAbility2Casts()
    {
        return ability2Casts;
    }
    
    public int getUltimateCasts()
    {
        return ultimateCasts;
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
        AbilityCasts that = (AbilityCasts) o;
        return grenadeCasts == that.grenadeCasts && ability1Casts == that.ability1Casts && ability2Casts == that.ability2Casts && ultimateCasts == that.ultimateCasts;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(grenadeCasts, ability1Casts, ability2Casts, ultimateCasts);
    }
    
    @Override
    public String toString()
    {
        return "Ability{" +
               "grenadeCasts='" + grenadeCasts + '\'' +
               ", ability1Casts='" + ability1Casts + '\'' +
               ", ability2Casts='" + ability2Casts + '\'' +
               ", ultimateCasts='" + ultimateCasts + '\'' +
               '}';
    }
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(VALMatch.class, "id");
        returnMap.put(RoundResult.class, "roundNum");
        returnMap.put(PlayerRoundStats.class, "puuid");
        
        return returnMap;
    }
}
