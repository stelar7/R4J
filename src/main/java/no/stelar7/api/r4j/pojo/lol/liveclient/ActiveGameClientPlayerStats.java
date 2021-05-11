package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGameClientPlayerStats
{
    private float  abilityPower;
    private float  armor;
    private float  armorPenetrationFlat;
    private float  armorPenetrationPercent;
    private float  attackDamage;
    private float  attackRange;
    private float  attackSpeed;
    private float  bonusArmorPenetrationPercent;
    private float  bonusMagicPenetrationPercent;
    private float  critChance;
    private float  critDamage;
    private float  currentHealth;
    private float  healShieldPower;
    private float  healthRegenRate;
    private float  lifeSteal;
    private float  magicLethality;
    private float  magicPenetrationFlat;
    private float  magicPenetrationPercent;
    private float  magicResist;
    private float  maxHealth;
    private float  moveSpeed;
    private float  omnivamp;
    private float  physicalLethality;
    private float  physicalVamp;
    private float  resourceMax;
    private float  resourceRegenRate;
    private String resourceType;
    private float  resourceValue;
    private float  spellVamp;
    private float  tenacity;
    
    public float getAbilityPower()
    {
        return abilityPower;
    }
    
    public float getArmor()
    {
        return armor;
    }
    
    public float getArmorPenetrationFlat()
    {
        return armorPenetrationFlat;
    }
    
    public float getArmorPenetrationPercent()
    {
        return armorPenetrationPercent;
    }
    
    public float getAttackDamage()
    {
        return attackDamage;
    }
    
    public float getAttackRange()
    {
        return attackRange;
    }
    
    public float getAttackSpeed()
    {
        return attackSpeed;
    }
    
    public float getBonusArmorPenetrationPercent()
    {
        return bonusArmorPenetrationPercent;
    }
    
    public float getBonusMagicPenetrationPercent()
    {
        return bonusMagicPenetrationPercent;
    }
    
    public float getCritChance()
    {
        return critChance;
    }
    
    public float getCritDamage()
    {
        return critDamage;
    }
    
    public float getCurrentHealth()
    {
        return currentHealth;
    }
    
    public float getHealShieldPower()
    {
        return healShieldPower;
    }
    
    public float getHealthRegenRate()
    {
        return healthRegenRate;
    }
    
    public float getLifeSteal()
    {
        return lifeSteal;
    }
    
    public float getMagicLethality()
    {
        return magicLethality;
    }
    
    public float getMagicPenetrationFlat()
    {
        return magicPenetrationFlat;
    }
    
    public float getMagicPenetrationPercent()
    {
        return magicPenetrationPercent;
    }
    
    public float getMagicResist()
    {
        return magicResist;
    }
    
    public float getMaxHealth()
    {
        return maxHealth;
    }
    
    public float getMoveSpeed()
    {
        return moveSpeed;
    }
    
    public float getOmnivamp()
    {
        return omnivamp;
    }
    
    public float getPhysicalLethality()
    {
        return physicalLethality;
    }
    
    public float getPhysicalVamp()
    {
        return physicalVamp;
    }
    
    public float getResourceMax()
    {
        return resourceMax;
    }
    
    public float getResourceRegenRate()
    {
        return resourceRegenRate;
    }
    
    public String getResourceType()
    {
        return resourceType;
    }
    
    public float getResourceValue()
    {
        return resourceValue;
    }
    
    public float getSpellVamp()
    {
        return spellVamp;
    }
    
    public float getTenacity()
    {
        return tenacity;
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
        ActiveGameClientPlayerStats that = (ActiveGameClientPlayerStats) o;
        return Float.compare(that.abilityPower, abilityPower) == 0 && Float.compare(that.armor, armor) == 0 && Float.compare(that.armorPenetrationFlat, armorPenetrationFlat) == 0 && Float.compare(that.armorPenetrationPercent, armorPenetrationPercent) == 0 && Float.compare(that.attackDamage, attackDamage) == 0 && Float.compare(that.attackRange, attackRange) == 0 && Float.compare(that.attackSpeed, attackSpeed) == 0 && Float.compare(that.bonusArmorPenetrationPercent, bonusArmorPenetrationPercent) == 0 && Float.compare(that.bonusMagicPenetrationPercent, bonusMagicPenetrationPercent) == 0 && Float.compare(that.critChance, critChance) == 0 && Float.compare(that.critDamage, critDamage) == 0 && Float.compare(that.currentHealth, currentHealth) == 0 && Float.compare(that.healShieldPower, healShieldPower) == 0 && Float.compare(that.healthRegenRate, healthRegenRate) == 0 && Float.compare(that.lifeSteal, lifeSteal) == 0 && Float.compare(that.magicLethality, magicLethality) == 0 && Float.compare(that.magicPenetrationFlat, magicPenetrationFlat) == 0 && Float.compare(that.magicPenetrationPercent, magicPenetrationPercent) == 0 && Float.compare(that.magicResist, magicResist) == 0 && Float.compare(that.maxHealth, maxHealth) == 0 && Float.compare(that.moveSpeed, moveSpeed) == 0 && Float.compare(that.omnivamp, omnivamp) == 0 && Float.compare(that.physicalLethality, physicalLethality) == 0 && Float.compare(that.physicalVamp, physicalVamp) == 0 && Float.compare(that.resourceMax, resourceMax) == 0 && Float.compare(that.resourceRegenRate, resourceRegenRate) == 0 && Float.compare(that.resourceValue, resourceValue) == 0 && Float.compare(that.spellVamp, spellVamp) == 0 && Float.compare(that.tenacity, tenacity) == 0 && Objects.equals(resourceType, that.resourceType);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(abilityPower, armor, armorPenetrationFlat, armorPenetrationPercent, attackDamage, attackRange, attackSpeed, bonusArmorPenetrationPercent, bonusMagicPenetrationPercent, critChance, critDamage, currentHealth, healShieldPower, healthRegenRate, lifeSteal, magicLethality, magicPenetrationFlat, magicPenetrationPercent, magicResist, maxHealth, moveSpeed, omnivamp, physicalLethality, physicalVamp, resourceMax, resourceRegenRate, resourceType, resourceValue, spellVamp, tenacity);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGameClientPlayerStats{" +
               "abilityPower=" + abilityPower +
               ", armor=" + armor +
               ", armorPenetrationFlat=" + armorPenetrationFlat +
               ", armorPenetrationPercent=" + armorPenetrationPercent +
               ", attackDamage=" + attackDamage +
               ", attackRange=" + attackRange +
               ", attackSpeed=" + attackSpeed +
               ", bonusArmorPenetrationPercent=" + bonusArmorPenetrationPercent +
               ", bonusMagicPenetrationPercent=" + bonusMagicPenetrationPercent +
               ", critChance=" + critChance +
               ", critDamage=" + critDamage +
               ", currentHealth=" + currentHealth +
               ", healShieldPower=" + healShieldPower +
               ", healthRegenRate=" + healthRegenRate +
               ", lifeSteal=" + lifeSteal +
               ", magicLethality=" + magicLethality +
               ", magicPenetrationFlat=" + magicPenetrationFlat +
               ", magicPenetrationPercent=" + magicPenetrationPercent +
               ", magicResist=" + magicResist +
               ", maxHealth=" + maxHealth +
               ", moveSpeed=" + moveSpeed +
               ", omnivamp=" + omnivamp +
               ", physicalLethality=" + physicalLethality +
               ", physicalVamp=" + physicalVamp +
               ", resourceMax=" + resourceMax +
               ", resourceRegenRate=" + resourceRegenRate +
               ", resourceType='" + resourceType + '\'' +
               ", resourceValue=" + resourceValue +
               ", spellVamp=" + spellVamp +
               ", tenacity=" + tenacity +
               '}';
    }
}
