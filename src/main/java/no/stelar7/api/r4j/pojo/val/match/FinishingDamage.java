package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.basic.constants.types.val.*;
import no.stelar7.api.r4j.impl.val.VALContentAPI;
import no.stelar7.api.r4j.pojo.val.content.ContentItem;

import java.io.Serializable;
import java.util.*;

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
    
    public String getDamageItem()
    {
        return damageItem;
    }
    
    public Optional<ContentItem> getDamageItemAsWeaponContent()
    {
        return VALContentAPI.getInstance()
                            .getContent(ValorantShard.EU, Optional.empty())
                            .getEquips()
                            .stream()
                            .filter(c -> c.getId().equals(this.damageItem))
                            .findFirst();
    }
    
    public Skill getDamageItemAsSkill()
    {
        return Skill.fromCode(damageItem);
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
