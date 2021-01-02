package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.basic.constants.types.val.*;
import no.stelar7.api.r4j.basic.utils.sql.SQLForeignMap;
import no.stelar7.api.r4j.impl.val.VALContentAPI;
import no.stelar7.api.r4j.pojo.val.content.ContentItem;

import java.io.Serializable;
import java.util.*;

public class Economy implements Serializable
{
    private static final long serialVersionUID = 8795392679461398269L;
    
    private int    loadoutValue;
    private String weapon;
    private Armor  armor;
    private int    remaining;
    private int    spent;
    
    public int getLoadoutValue()
    {
        return loadoutValue;
    }
    
    public String getWeapon()
    {
        return weapon;
    }
    
    public Optional<ContentItem> getWeaponAsContent()
    {
        return VALContentAPI.getInstance()
                            .getContent(ValorantShard.EU, Optional.empty())
                            .getEquips()
                            .stream()
                            .filter(c -> c.getId().equalsIgnoreCase(this.weapon))
                            .findFirst();
    }
    
    public Armor getArmor()
    {
        return armor;
    }
    
    public int getRemaining()
    {
        return remaining;
    }
    
    public int getSpent()
    {
        return spent;
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
        Economy economy = (Economy) o;
        return loadoutValue == economy.loadoutValue &&
               remaining == economy.remaining &&
               spent == economy.spent &&
               Objects.equals(weapon, economy.weapon) &&
               Objects.equals(armor, economy.armor);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(loadoutValue, weapon, armor, remaining, spent);
    }
    
    @Override
    public String toString()
    {
        return "Economy{" +
               "loadoutValue=" + loadoutValue +
               ", weapon='" + weapon + '\'' +
               ", armor='" + armor + '\'' +
               ", remaining=" + remaining +
               ", spent=" + spent +
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
