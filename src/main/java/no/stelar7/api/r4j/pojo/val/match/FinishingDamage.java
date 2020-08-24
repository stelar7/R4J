package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.constants.types.val.*;

import java.io.Serializable;
import java.util.Objects;

public class FinishingDamage implements Serializable
{
    private static final long serialVersionUID = -2055229548751293072L;
    
    private FinishingDamageType damageType;
    private String              damageItem;
    private Boolean             isSecondaryFireMode;
    
    public FinishingDamageType getDamageType()
    {
        return damageType;
    }
    
    public Weapon getDamageItemAsWeapon()
    {
        return Weapon.valueOf(damageItem);
    }
    
    public Skill getDamageItemAsSkill()
    {
        return Skill.valueOf(damageItem);
    }
    
    public Boolean getSecondaryFireMode()
    {
        return isSecondaryFireMode;
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
        FinishingDamage that = (FinishingDamage) o;
        return Objects.equals(damageType, that.damageType) &&
               Objects.equals(damageItem, that.damageItem) &&
               Objects.equals(isSecondaryFireMode, that.isSecondaryFireMode);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(damageType, damageItem, isSecondaryFireMode);
    }
    
    @Override
    public String toString()
    {
        return "FinishingDamage{" +
               "damageType='" + damageType + '\'' +
               ", damageItem='" + damageItem + '\'' +
               ", isSecondaryFireMode=" + isSecondaryFireMode +
               '}';
    }
}
