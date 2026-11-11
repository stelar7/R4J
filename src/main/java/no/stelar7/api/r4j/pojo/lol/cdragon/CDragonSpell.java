package no.stelar7.api.r4j.pojo.lol.cdragon;

import java.io.Serializable;
import java.util.*;

public class CDragonSpell implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String              spellKey;
    private String              name;
    private String              abilityIconPath;
    private String              abilityVideoPath;
    private String              abilityVideoImagePath;
    private String              cost;
    private String              cooldown;
    private String              description;
    private String              dynamicDescription;
    private List<Double>        range;
    private List<Double>        costCoefficients;
    private List<Double>        cooldownCoefficients;
    private Map<String, Object> coefficients;
    private Map<String, Object> effectAmounts;
    private Map<String, Object> ammo;
    private int                 maxLevel;

    public String getSpellKey()
    {
        return spellKey;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getAbilityIconPath()
    {
        return abilityIconPath;
    }
    
    public String getAbilityVideoPath()
    {
        return abilityVideoPath;
    }
    
    public String getAbilityVideoImagePath()
    {
        return abilityVideoImagePath;
    }
    
    public String getCost()
    {
        return cost;
    }
    
    public String getCooldown()
    {
        return cooldown;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getDynamicDescription()
    {
        return dynamicDescription;
    }
    
    public List<Double> getRange()
    {
        return range;
    }
    
    public List<Double> getCostCoefficients()
    {
        return costCoefficients;
    }
    
    public List<Double> getCooldownCoefficients()
    {
        return cooldownCoefficients;
    }
    
    public Map<String, Object> getCoefficients()
    {
        return coefficients;
    }
    
    public Map<String, Object> getEffectAmounts()
    {
        return effectAmounts;
    }
    
    public Map<String, Object> getAmmo()
    {
        return ammo;
    }
    
    public int getMaxLevel()
    {
        return maxLevel;
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
        CDragonSpell that = (CDragonSpell) o;
        return Objects.equals(spellKey, that.spellKey) &&
               Objects.equals(name, that.name) &&
               Objects.equals(abilityIconPath, that.abilityIconPath) &&
               Objects.equals(abilityVideoPath, that.abilityVideoPath) &&
               Objects.equals(abilityVideoImagePath, that.abilityVideoImagePath) &&
               Objects.equals(cost, that.cost) &&
               Objects.equals(cooldown, that.cooldown) &&
               Objects.equals(description, that.description) &&
               Objects.equals(dynamicDescription, that.dynamicDescription) &&
               Objects.equals(range, that.range) &&
               Objects.equals(costCoefficients, that.costCoefficients) &&
               Objects.equals(cooldownCoefficients, that.cooldownCoefficients) &&
               Objects.equals(coefficients, that.coefficients) &&
               Objects.equals(effectAmounts, that.effectAmounts) &&
               Objects.equals(ammo, that.ammo) &&
               maxLevel == that.maxLevel;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(spellKey, name, abilityIconPath, abilityVideoPath, abilityVideoImagePath, cost, cooldown, description, dynamicDescription, range, costCoefficients, cooldownCoefficients, coefficients, effectAmounts, ammo, maxLevel);
    }

    @Override
    public String toString()
    {
        return "CDragonSpell{" +
               "spellKey=" + spellKey +
               ", name=" + name +
               ", abilityIconPath=" + abilityIconPath +
               ", abilityVideoPath=" + abilityVideoPath +
               ", abilityVideoImagePath=" + abilityVideoImagePath +
               ", cost=" + cost +
               ", cooldown=" + cooldown +
               ", description=" + description +
               ", dynamicDescription=" + dynamicDescription +
               ", range=" + range +
               ", costCoefficients=" + costCoefficients +
               ", cooldownCoefficients=" + cooldownCoefficients +
               ", coefficients=" + coefficients +
               ", effectAmounts=" + effectAmounts +
               ", ammo=" + ammo +
               ", maxLevel=" + maxLevel +
               '}';
    }
}
