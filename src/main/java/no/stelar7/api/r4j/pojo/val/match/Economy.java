package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.constants.types.val.*;

import java.io.Serializable;
import java.util.Objects;

public class Economy implements Serializable
{
    private static final long serialVersionUID = 8795392679461398269L;
    
    private int    loadoutValue;
    private Weapon weapon;
    private Armor  armor;
    private int    remaining;
    private int    spent;
    
    public int getLoadoutValue()
    {
        return loadoutValue;
    }
    
    public Weapon getWeapon()
    {
        return weapon;
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
}
