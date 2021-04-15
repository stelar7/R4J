package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.Objects;

public class ChampionStats implements Serializable
{
    private static final long serialVersionUID = 4957994144440809843L;
    
    private int abilityPower;
    private int armor;
    private int armorPen;
    private int armorPenPercent;
    private int attackDamage;
    private int attackSpeed;
    private int bonusArmorPenPercent;
    private int bonusMagicPenPercent;
    private int ccReduction;
    private int cooldownReduction;
    private int health;
    private int healthMax;
    private int healthRegen;
    private int lifesteal;
    private int magicPen;
    private int magicPenPercent;
    private int magicResist;
    private int movementSpeed;
    private int power;
    private int powerMax;
    private int powerRegen;
    private int spellVamp;
    
    public int getAbilityPower()
    {
        return abilityPower;
    }
    
    public int getArmor()
    {
        return armor;
    }
    
    public int getArmorPen()
    {
        return armorPen;
    }
    
    public int getArmorPenPercent()
    {
        return armorPenPercent;
    }
    
    public int getAttackDamage()
    {
        return attackDamage;
    }
    
    public int getAttackSpeed()
    {
        return attackSpeed;
    }
    
    public int getBonusArmorPenPercent()
    {
        return bonusArmorPenPercent;
    }
    
    public int getBonusMagicPenPercent()
    {
        return bonusMagicPenPercent;
    }
    
    public int getCcReduction()
    {
        return ccReduction;
    }
    
    public int getCooldownReduction()
    {
        return cooldownReduction;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getHealthMax()
    {
        return healthMax;
    }
    
    public int getHealthRegen()
    {
        return healthRegen;
    }
    
    public int getLifesteal()
    {
        return lifesteal;
    }
    
    public int getMagicPen()
    {
        return magicPen;
    }
    
    public int getMagicPenPercent()
    {
        return magicPenPercent;
    }
    
    public int getMagicResist()
    {
        return magicResist;
    }
    
    public int getMovementSpeed()
    {
        return movementSpeed;
    }
    
    public int getPower()
    {
        return power;
    }
    
    public int getPowerMax()
    {
        return powerMax;
    }
    
    public int getPowerRegen()
    {
        return powerRegen;
    }
    
    public int getSpellVamp()
    {
        return spellVamp;
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
        ChampionStats that = (ChampionStats) o;
        return abilityPower == that.abilityPower && armor == that.armor && armorPen == that.armorPen && armorPenPercent == that.armorPenPercent && attackDamage == that.attackDamage && attackSpeed == that.attackSpeed && bonusArmorPenPercent == that.bonusArmorPenPercent && bonusMagicPenPercent == that.bonusMagicPenPercent && ccReduction == that.ccReduction && cooldownReduction == that.cooldownReduction && health == that.health && healthMax == that.healthMax && healthRegen == that.healthRegen && lifesteal == that.lifesteal && magicPen == that.magicPen && magicPenPercent == that.magicPenPercent && magicResist == that.magicResist && movementSpeed == that.movementSpeed && power == that.power && powerMax == that.powerMax && powerRegen == that.powerRegen && spellVamp == that.spellVamp;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(abilityPower, armor, armorPen, armorPenPercent, attackDamage, attackSpeed, bonusArmorPenPercent, bonusMagicPenPercent, ccReduction, cooldownReduction, health, healthMax, healthRegen, lifesteal, magicPen, magicPenPercent, magicResist, movementSpeed, power, powerMax, powerRegen, spellVamp);
    }
    
    @Override
    public String toString()
    {
        return "ChampionStats{" +
               "abilityPower=" + abilityPower +
               ", armor=" + armor +
               ", armorPen=" + armorPen +
               ", armorPenPercent=" + armorPenPercent +
               ", attackDamage=" + attackDamage +
               ", attackSpeed=" + attackSpeed +
               ", bonusArmorPenPercent=" + bonusArmorPenPercent +
               ", bonusMagicPenPercent=" + bonusMagicPenPercent +
               ", ccReduction=" + ccReduction +
               ", cooldownReduction=" + cooldownReduction +
               ", health=" + health +
               ", healthMax=" + healthMax +
               ", healthRegen=" + healthRegen +
               ", lifesteal=" + lifesteal +
               ", magicPen=" + magicPen +
               ", magicPenPercent=" + magicPenPercent +
               ", magicResist=" + magicResist +
               ", movementSpeed=" + movementSpeed +
               ", power=" + power +
               ", powerMax=" + powerMax +
               ", powerRegen=" + powerRegen +
               ", spellVamp=" + spellVamp +
               '}';
    }
}
