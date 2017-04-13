package no.stelar7.api.l4j8.pojo.staticdata.shared;

public class BasicDataStats
{
    private Double FlatArmorMod;
    private Double FlatAttackSpeedMod;
    private Double FlatBlockMod;
    private Double FlatCritChanceMod;
    private Double FlatCritDamageMod;
    private Double FlatEXPBonus;
    private Double FlatEnergyPoolMod;
    private Double FlatEnergyRegenMod;
    private Double FlatHPPoolMod;
    private Double FlatHPRegenMod;
    private Double FlatMPPoolMod;
    private Double FlatMPRegenMod;
    private Double FlatMagicDamageMod;
    private Double FlatMovementSpeedMod;
    private Double FlatPhysicalDamageMod;
    private Double FlatSpellBlockMod;
    private Double PercentArmorMod;
    private Double PercentAttackSpeedMod;
    private Double PercentBlockMod;
    private Double PercentCritChanceMod;
    private Double PercentCritDamageMod;
    private Double PercentDodgeMod;
    private Double PercentEXPBonus;
    private Double PercentHPPoolMod;
    private Double PercentHPRegenMod;
    private Double PercentLifeStealMod;
    private Double PercentMPPoolMod;
    private Double PercentMPRegenMod;
    private Double PercentMagicDamageMod;
    private Double PercentMovementSpeedMod;
    private Double PercentPhysicalDamageMod;
    private Double PercentSpellBlockMod;
    private Double PercentSpellVampMod;
    private Double rFlatArmorModPerLevel;
    private Double rFlatArmorPenetrationMod;
    private Double rFlatArmorPenetrationModPerLevel;
    private Double rFlatCritChanceModPerLevel;
    private Double rFlatCritDamageModPerLevel;
    private Double rFlatDodgeMod;
    private Double rFlatDodgeModPerLevel;
    private Double rFlatEnergyModPerLevel;
    private Double rFlatEnergyRegenModPerLevel;
    private Double rFlatGoldPer10Mod;
    private Double rFlatHPModPerLevel;
    private Double rFlatHPRegenModPerLevel;
    private Double rFlatMPModPerLevel;
    private Double rFlatMPRegenModPerLevel;
    private Double rFlatMagicDamageModPerLevel;
    private Double rFlatMagicPenetrationMod;
    private Double rFlatMagicPenetrationModPerLevel;
    private Double rFlatMovementSpeedModPerLevel;
    private Double rFlatPhysicalDamageModPerLevel;
    private Double rFlatSpellBlockModPerLevel;
    private Double rFlatTimeDeadMod;
    private Double rFlatTimeDeadModPerLevel;
    private Double rPercentArmorPenetrationMod;
    private Double rPercentArmorPenetrationModPerLevel;
    private Double rPercentAttackSpeedModPerLevel;
    private Double rPercentCooldownMod;
    private Double rPercentCooldownModPerLevel;
    private Double rPercentMagicPenetrationMod;
    private Double rPercentMagicPenetrationModPerLevel;
    private Double rPercentMovementSpeedModPerLevel;
    private Double rPercentTimeDeadMod;
    private Double rPercentTimeDeadModPerLevel;
    
    
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
        
        BasicDataStats that = (BasicDataStats) o;
        
        if ((FlatArmorMod != null) ? !FlatArmorMod.equals(that.FlatArmorMod) : (that.FlatArmorMod != null))
        {
            return false;
        }
        if ((FlatAttackSpeedMod != null) ? !FlatAttackSpeedMod.equals(that.FlatAttackSpeedMod) : (that.FlatAttackSpeedMod != null))
        {
            return false;
        }
        if ((FlatBlockMod != null) ? !FlatBlockMod.equals(that.FlatBlockMod) : (that.FlatBlockMod != null))
        {
            return false;
        }
        if ((FlatCritChanceMod != null) ? !FlatCritChanceMod.equals(that.FlatCritChanceMod) : (that.FlatCritChanceMod != null))
        {
            return false;
        }
        if ((FlatCritDamageMod != null) ? !FlatCritDamageMod.equals(that.FlatCritDamageMod) : (that.FlatCritDamageMod != null))
        {
            return false;
        }
        if ((FlatEXPBonus != null) ? !FlatEXPBonus.equals(that.FlatEXPBonus) : (that.FlatEXPBonus != null))
        {
            return false;
        }
        if ((FlatEnergyPoolMod != null) ? !FlatEnergyPoolMod.equals(that.FlatEnergyPoolMod) : (that.FlatEnergyPoolMod != null))
        {
            return false;
        }
        if ((FlatEnergyRegenMod != null) ? !FlatEnergyRegenMod.equals(that.FlatEnergyRegenMod) : (that.FlatEnergyRegenMod != null))
        {
            return false;
        }
        if ((FlatHPPoolMod != null) ? !FlatHPPoolMod.equals(that.FlatHPPoolMod) : (that.FlatHPPoolMod != null))
        {
            return false;
        }
        if ((FlatHPRegenMod != null) ? !FlatHPRegenMod.equals(that.FlatHPRegenMod) : (that.FlatHPRegenMod != null))
        {
            return false;
        }
        if ((FlatMPPoolMod != null) ? !FlatMPPoolMod.equals(that.FlatMPPoolMod) : (that.FlatMPPoolMod != null))
        {
            return false;
        }
        if ((FlatMPRegenMod != null) ? !FlatMPRegenMod.equals(that.FlatMPRegenMod) : (that.FlatMPRegenMod != null))
        {
            return false;
        }
        if ((FlatMagicDamageMod != null) ? !FlatMagicDamageMod.equals(that.FlatMagicDamageMod) : (that.FlatMagicDamageMod != null))
        {
            return false;
        }
        if ((FlatMovementSpeedMod != null) ? !FlatMovementSpeedMod.equals(that.FlatMovementSpeedMod) : (that.FlatMovementSpeedMod != null))
        {
            return false;
        }
        if ((FlatPhysicalDamageMod != null) ? !FlatPhysicalDamageMod.equals(that.FlatPhysicalDamageMod) : (that.FlatPhysicalDamageMod != null))
        {
            return false;
        }
        if ((FlatSpellBlockMod != null) ? !FlatSpellBlockMod.equals(that.FlatSpellBlockMod) : (that.FlatSpellBlockMod != null))
        {
            return false;
        }
        if ((PercentArmorMod != null) ? !PercentArmorMod.equals(that.PercentArmorMod) : (that.PercentArmorMod != null))
        {
            return false;
        }
        if ((PercentAttackSpeedMod != null) ? !PercentAttackSpeedMod.equals(that.PercentAttackSpeedMod) : (that.PercentAttackSpeedMod != null))
        {
            return false;
        }
        if ((PercentBlockMod != null) ? !PercentBlockMod.equals(that.PercentBlockMod) : (that.PercentBlockMod != null))
        {
            return false;
        }
        if ((PercentCritChanceMod != null) ? !PercentCritChanceMod.equals(that.PercentCritChanceMod) : (that.PercentCritChanceMod != null))
        {
            return false;
        }
        if ((PercentCritDamageMod != null) ? !PercentCritDamageMod.equals(that.PercentCritDamageMod) : (that.PercentCritDamageMod != null))
        {
            return false;
        }
        if ((PercentDodgeMod != null) ? !PercentDodgeMod.equals(that.PercentDodgeMod) : (that.PercentDodgeMod != null))
        {
            return false;
        }
        if ((PercentEXPBonus != null) ? !PercentEXPBonus.equals(that.PercentEXPBonus) : (that.PercentEXPBonus != null))
        {
            return false;
        }
        if ((PercentHPPoolMod != null) ? !PercentHPPoolMod.equals(that.PercentHPPoolMod) : (that.PercentHPPoolMod != null))
        {
            return false;
        }
        if ((PercentHPRegenMod != null) ? !PercentHPRegenMod.equals(that.PercentHPRegenMod) : (that.PercentHPRegenMod != null))
        {
            return false;
        }
        if ((PercentLifeStealMod != null) ? !PercentLifeStealMod.equals(that.PercentLifeStealMod) : (that.PercentLifeStealMod != null))
        {
            return false;
        }
        if ((PercentMPPoolMod != null) ? !PercentMPPoolMod.equals(that.PercentMPPoolMod) : (that.PercentMPPoolMod != null))
        {
            return false;
        }
        if ((PercentMPRegenMod != null) ? !PercentMPRegenMod.equals(that.PercentMPRegenMod) : (that.PercentMPRegenMod != null))
        {
            return false;
        }
        if ((PercentMagicDamageMod != null) ? !PercentMagicDamageMod.equals(that.PercentMagicDamageMod) : (that.PercentMagicDamageMod != null))
        {
            return false;
        }
        if ((PercentMovementSpeedMod != null) ? !PercentMovementSpeedMod.equals(that.PercentMovementSpeedMod) : (that.PercentMovementSpeedMod != null))
        {
            return false;
        }
        if ((PercentPhysicalDamageMod != null) ? !PercentPhysicalDamageMod.equals(that.PercentPhysicalDamageMod) : (that.PercentPhysicalDamageMod != null))
        {
            return false;
        }
        if ((PercentSpellBlockMod != null) ? !PercentSpellBlockMod.equals(that.PercentSpellBlockMod) : (that.PercentSpellBlockMod != null))
        {
            return false;
        }
        if ((PercentSpellVampMod != null) ? !PercentSpellVampMod.equals(that.PercentSpellVampMod) : (that.PercentSpellVampMod != null))
        {
            return false;
        }
        if ((rFlatArmorModPerLevel != null) ? !rFlatArmorModPerLevel.equals(that.rFlatArmorModPerLevel) : (that.rFlatArmorModPerLevel != null))
        {
            return false;
        }
        if ((rFlatArmorPenetrationMod != null) ? !rFlatArmorPenetrationMod.equals(that.rFlatArmorPenetrationMod) : (that.rFlatArmorPenetrationMod != null))
        {
            return false;
        }
        if ((rFlatArmorPenetrationModPerLevel != null) ? !rFlatArmorPenetrationModPerLevel.equals(that.rFlatArmorPenetrationModPerLevel) : (that.rFlatArmorPenetrationModPerLevel != null))
        {
            return false;
        }
        if ((rFlatCritChanceModPerLevel != null) ? !rFlatCritChanceModPerLevel.equals(that.rFlatCritChanceModPerLevel) : (that.rFlatCritChanceModPerLevel != null))
        {
            return false;
        }
        if ((rFlatCritDamageModPerLevel != null) ? !rFlatCritDamageModPerLevel.equals(that.rFlatCritDamageModPerLevel) : (that.rFlatCritDamageModPerLevel != null))
        {
            return false;
        }
        if ((rFlatDodgeMod != null) ? !rFlatDodgeMod.equals(that.rFlatDodgeMod) : (that.rFlatDodgeMod != null))
        {
            return false;
        }
        if ((rFlatDodgeModPerLevel != null) ? !rFlatDodgeModPerLevel.equals(that.rFlatDodgeModPerLevel) : (that.rFlatDodgeModPerLevel != null))
        {
            return false;
        }
        if ((rFlatEnergyModPerLevel != null) ? !rFlatEnergyModPerLevel.equals(that.rFlatEnergyModPerLevel) : (that.rFlatEnergyModPerLevel != null))
        {
            return false;
        }
        if ((rFlatEnergyRegenModPerLevel != null) ? !rFlatEnergyRegenModPerLevel.equals(that.rFlatEnergyRegenModPerLevel) : (that.rFlatEnergyRegenModPerLevel != null))
        {
            return false;
        }
        if ((rFlatGoldPer10Mod != null) ? !rFlatGoldPer10Mod.equals(that.rFlatGoldPer10Mod) : (that.rFlatGoldPer10Mod != null))
        {
            return false;
        }
        if ((rFlatHPModPerLevel != null) ? !rFlatHPModPerLevel.equals(that.rFlatHPModPerLevel) : (that.rFlatHPModPerLevel != null))
        {
            return false;
        }
        if ((rFlatHPRegenModPerLevel != null) ? !rFlatHPRegenModPerLevel.equals(that.rFlatHPRegenModPerLevel) : (that.rFlatHPRegenModPerLevel != null))
        {
            return false;
        }
        if ((rFlatMPModPerLevel != null) ? !rFlatMPModPerLevel.equals(that.rFlatMPModPerLevel) : (that.rFlatMPModPerLevel != null))
        {
            return false;
        }
        if ((rFlatMPRegenModPerLevel != null) ? !rFlatMPRegenModPerLevel.equals(that.rFlatMPRegenModPerLevel) : (that.rFlatMPRegenModPerLevel != null))
        {
            return false;
        }
        if ((rFlatMagicDamageModPerLevel != null) ? !rFlatMagicDamageModPerLevel.equals(that.rFlatMagicDamageModPerLevel) : (that.rFlatMagicDamageModPerLevel != null))
        {
            return false;
        }
        if ((rFlatMagicPenetrationMod != null) ? !rFlatMagicPenetrationMod.equals(that.rFlatMagicPenetrationMod) : (that.rFlatMagicPenetrationMod != null))
        {
            return false;
        }
        if ((rFlatMagicPenetrationModPerLevel != null) ? !rFlatMagicPenetrationModPerLevel.equals(that.rFlatMagicPenetrationModPerLevel) : (that.rFlatMagicPenetrationModPerLevel != null))
        {
            return false;
        }
        if ((rFlatMovementSpeedModPerLevel != null) ? !rFlatMovementSpeedModPerLevel.equals(that.rFlatMovementSpeedModPerLevel) : (that.rFlatMovementSpeedModPerLevel != null))
        {
            return false;
        }
        if ((rFlatPhysicalDamageModPerLevel != null) ? !rFlatPhysicalDamageModPerLevel.equals(that.rFlatPhysicalDamageModPerLevel) : (that.rFlatPhysicalDamageModPerLevel != null))
        {
            return false;
        }
        if ((rFlatSpellBlockModPerLevel != null) ? !rFlatSpellBlockModPerLevel.equals(that.rFlatSpellBlockModPerLevel) : (that.rFlatSpellBlockModPerLevel != null))
        {
            return false;
        }
        if ((rFlatTimeDeadMod != null) ? !rFlatTimeDeadMod.equals(that.rFlatTimeDeadMod) : (that.rFlatTimeDeadMod != null))
        {
            return false;
        }
        if ((rFlatTimeDeadModPerLevel != null) ? !rFlatTimeDeadModPerLevel.equals(that.rFlatTimeDeadModPerLevel) : (that.rFlatTimeDeadModPerLevel != null))
        {
            return false;
        }
        if ((rPercentArmorPenetrationMod != null) ? !rPercentArmorPenetrationMod.equals(that.rPercentArmorPenetrationMod) : (that.rPercentArmorPenetrationMod != null))
        {
            return false;
        }
        if ((rPercentArmorPenetrationModPerLevel != null) ? !rPercentArmorPenetrationModPerLevel.equals(that.rPercentArmorPenetrationModPerLevel) : (that.rPercentArmorPenetrationModPerLevel != null))
        {
            return false;
        }
        if ((rPercentAttackSpeedModPerLevel != null) ? !rPercentAttackSpeedModPerLevel.equals(that.rPercentAttackSpeedModPerLevel) : (that.rPercentAttackSpeedModPerLevel != null))
        {
            return false;
        }
        if ((rPercentCooldownMod != null) ? !rPercentCooldownMod.equals(that.rPercentCooldownMod) : (that.rPercentCooldownMod != null))
        {
            return false;
        }
        if ((rPercentCooldownModPerLevel != null) ? !rPercentCooldownModPerLevel.equals(that.rPercentCooldownModPerLevel) : (that.rPercentCooldownModPerLevel != null))
        {
            return false;
        }
        if ((rPercentMagicPenetrationMod != null) ? !rPercentMagicPenetrationMod.equals(that.rPercentMagicPenetrationMod) : (that.rPercentMagicPenetrationMod != null))
        {
            return false;
        }
        if ((rPercentMagicPenetrationModPerLevel != null) ? !rPercentMagicPenetrationModPerLevel.equals(that.rPercentMagicPenetrationModPerLevel) : (that.rPercentMagicPenetrationModPerLevel != null))
        {
            return false;
        }
        if ((rPercentMovementSpeedModPerLevel != null) ? !rPercentMovementSpeedModPerLevel.equals(that.rPercentMovementSpeedModPerLevel) : (that.rPercentMovementSpeedModPerLevel != null))
        {
            return false;
        }
        if ((rPercentTimeDeadMod != null) ? !rPercentTimeDeadMod.equals(that.rPercentTimeDeadMod) : (that.rPercentTimeDeadMod != null))
        {
            return false;
        }
        return (rPercentTimeDeadModPerLevel != null) ? rPercentTimeDeadModPerLevel.equals(that.rPercentTimeDeadModPerLevel) : (that.rPercentTimeDeadModPerLevel == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = FlatArmorMod != null ? FlatArmorMod.hashCode() : 0;
        result = 31 * result + (FlatAttackSpeedMod != null ? FlatAttackSpeedMod.hashCode() : 0);
        result = 31 * result + (FlatBlockMod != null ? FlatBlockMod.hashCode() : 0);
        result = 31 * result + (FlatCritChanceMod != null ? FlatCritChanceMod.hashCode() : 0);
        result = 31 * result + (FlatCritDamageMod != null ? FlatCritDamageMod.hashCode() : 0);
        result = 31 * result + (FlatEXPBonus != null ? FlatEXPBonus.hashCode() : 0);
        result = 31 * result + (FlatEnergyPoolMod != null ? FlatEnergyPoolMod.hashCode() : 0);
        result = 31 * result + (FlatEnergyRegenMod != null ? FlatEnergyRegenMod.hashCode() : 0);
        result = 31 * result + (FlatHPPoolMod != null ? FlatHPPoolMod.hashCode() : 0);
        result = 31 * result + (FlatHPRegenMod != null ? FlatHPRegenMod.hashCode() : 0);
        result = 31 * result + (FlatMPPoolMod != null ? FlatMPPoolMod.hashCode() : 0);
        result = 31 * result + (FlatMPRegenMod != null ? FlatMPRegenMod.hashCode() : 0);
        result = 31 * result + (FlatMagicDamageMod != null ? FlatMagicDamageMod.hashCode() : 0);
        result = 31 * result + (FlatMovementSpeedMod != null ? FlatMovementSpeedMod.hashCode() : 0);
        result = 31 * result + (FlatPhysicalDamageMod != null ? FlatPhysicalDamageMod.hashCode() : 0);
        result = 31 * result + (FlatSpellBlockMod != null ? FlatSpellBlockMod.hashCode() : 0);
        result = 31 * result + (PercentArmorMod != null ? PercentArmorMod.hashCode() : 0);
        result = 31 * result + (PercentAttackSpeedMod != null ? PercentAttackSpeedMod.hashCode() : 0);
        result = 31 * result + (PercentBlockMod != null ? PercentBlockMod.hashCode() : 0);
        result = 31 * result + (PercentCritChanceMod != null ? PercentCritChanceMod.hashCode() : 0);
        result = 31 * result + (PercentCritDamageMod != null ? PercentCritDamageMod.hashCode() : 0);
        result = 31 * result + (PercentDodgeMod != null ? PercentDodgeMod.hashCode() : 0);
        result = 31 * result + (PercentEXPBonus != null ? PercentEXPBonus.hashCode() : 0);
        result = 31 * result + (PercentHPPoolMod != null ? PercentHPPoolMod.hashCode() : 0);
        result = 31 * result + (PercentHPRegenMod != null ? PercentHPRegenMod.hashCode() : 0);
        result = 31 * result + (PercentLifeStealMod != null ? PercentLifeStealMod.hashCode() : 0);
        result = 31 * result + (PercentMPPoolMod != null ? PercentMPPoolMod.hashCode() : 0);
        result = 31 * result + (PercentMPRegenMod != null ? PercentMPRegenMod.hashCode() : 0);
        result = 31 * result + (PercentMagicDamageMod != null ? PercentMagicDamageMod.hashCode() : 0);
        result = 31 * result + (PercentMovementSpeedMod != null ? PercentMovementSpeedMod.hashCode() : 0);
        result = 31 * result + (PercentPhysicalDamageMod != null ? PercentPhysicalDamageMod.hashCode() : 0);
        result = 31 * result + (PercentSpellBlockMod != null ? PercentSpellBlockMod.hashCode() : 0);
        result = 31 * result + (PercentSpellVampMod != null ? PercentSpellVampMod.hashCode() : 0);
        result = 31 * result + (rFlatArmorModPerLevel != null ? rFlatArmorModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatArmorPenetrationMod != null ? rFlatArmorPenetrationMod.hashCode() : 0);
        result = 31 * result + (rFlatArmorPenetrationModPerLevel != null ? rFlatArmorPenetrationModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatCritChanceModPerLevel != null ? rFlatCritChanceModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatCritDamageModPerLevel != null ? rFlatCritDamageModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatDodgeMod != null ? rFlatDodgeMod.hashCode() : 0);
        result = 31 * result + (rFlatDodgeModPerLevel != null ? rFlatDodgeModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatEnergyModPerLevel != null ? rFlatEnergyModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatEnergyRegenModPerLevel != null ? rFlatEnergyRegenModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatGoldPer10Mod != null ? rFlatGoldPer10Mod.hashCode() : 0);
        result = 31 * result + (rFlatHPModPerLevel != null ? rFlatHPModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatHPRegenModPerLevel != null ? rFlatHPRegenModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatMPModPerLevel != null ? rFlatMPModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatMPRegenModPerLevel != null ? rFlatMPRegenModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatMagicDamageModPerLevel != null ? rFlatMagicDamageModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatMagicPenetrationMod != null ? rFlatMagicPenetrationMod.hashCode() : 0);
        result = 31 * result + (rFlatMagicPenetrationModPerLevel != null ? rFlatMagicPenetrationModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatMovementSpeedModPerLevel != null ? rFlatMovementSpeedModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatPhysicalDamageModPerLevel != null ? rFlatPhysicalDamageModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatSpellBlockModPerLevel != null ? rFlatSpellBlockModPerLevel.hashCode() : 0);
        result = 31 * result + (rFlatTimeDeadMod != null ? rFlatTimeDeadMod.hashCode() : 0);
        result = 31 * result + (rFlatTimeDeadModPerLevel != null ? rFlatTimeDeadModPerLevel.hashCode() : 0);
        result = 31 * result + (rPercentArmorPenetrationMod != null ? rPercentArmorPenetrationMod.hashCode() : 0);
        result = 31 * result + (rPercentArmorPenetrationModPerLevel != null ? rPercentArmorPenetrationModPerLevel.hashCode() : 0);
        result = 31 * result + (rPercentAttackSpeedModPerLevel != null ? rPercentAttackSpeedModPerLevel.hashCode() : 0);
        result = 31 * result + (rPercentCooldownMod != null ? rPercentCooldownMod.hashCode() : 0);
        result = 31 * result + (rPercentCooldownModPerLevel != null ? rPercentCooldownModPerLevel.hashCode() : 0);
        result = 31 * result + (rPercentMagicPenetrationMod != null ? rPercentMagicPenetrationMod.hashCode() : 0);
        result = 31 * result + (rPercentMagicPenetrationModPerLevel != null ? rPercentMagicPenetrationModPerLevel.hashCode() : 0);
        result = 31 * result + (rPercentMovementSpeedModPerLevel != null ? rPercentMovementSpeedModPerLevel.hashCode() : 0);
        result = 31 * result + (rPercentTimeDeadMod != null ? rPercentTimeDeadMod.hashCode() : 0);
        result = 31 * result + (rPercentTimeDeadModPerLevel != null ? rPercentTimeDeadModPerLevel.hashCode() : 0);
        return result;
    }
    
    /**
     * Gets the flat armor mod.
     *
     * @return the flat armor mod
     */
    public Double getFlatArmorMod()
    {
        return this.FlatArmorMod;
    }
    
    /**
     * Gets the flat attack speed mod.
     *
     * @return the flat attack speed mod
     */
    public Double getFlatAttackSpeedMod()
    {
        return this.FlatAttackSpeedMod;
    }
    
    /**
     * Gets the flat block mod.
     *
     * @return the flat block mod
     */
    public Double getFlatBlockMod()
    {
        return this.FlatBlockMod;
    }
    
    /**
     * Gets the flat crit chance mod.
     *
     * @return the flat crit chance mod
     */
    public Double getFlatCritChanceMod()
    {
        return this.FlatCritChanceMod;
    }
    
    /**
     * Gets the flat crit damage mod.
     *
     * @return the flat crit damage mod
     */
    public Double getFlatCritDamageMod()
    {
        return this.FlatCritDamageMod;
    }
    
    /**
     * Gets the flat energy pool mod.
     *
     * @return the flat energy pool mod
     */
    public Double getFlatEnergyPoolMod()
    {
        return this.FlatEnergyPoolMod;
    }
    
    /**
     * Gets the flat energy regen mod.
     *
     * @return the flat energy regen mod
     */
    public Double getFlatEnergyRegenMod()
    {
        return this.FlatEnergyRegenMod;
    }
    
    /**
     * Gets the flat exp bonus.
     *
     * @return the flat exp bonus
     */
    public Double getFlatEXPBonus()
    {
        return this.FlatEXPBonus;
    }
    
    /**
     * Gets the flat hp pool mod.
     *
     * @return the flat hp pool mod
     */
    public Double getFlatHPPoolMod()
    {
        return this.FlatHPPoolMod;
    }
    
    /**
     * Gets the flat hp regen mod.
     *
     * @return the flat hp regen mod
     */
    public Double getFlatHPRegenMod()
    {
        return this.FlatHPRegenMod;
    }
    
    /**
     * Gets the flat magic damage mod.
     *
     * @return the flat magic damage mod
     */
    public Double getFlatMagicDamageMod()
    {
        return this.FlatMagicDamageMod;
    }
    
    /**
     * Gets the flat movement speed mod.
     *
     * @return the flat movement speed mod
     */
    public Double getFlatMovementSpeedMod()
    {
        return this.FlatMovementSpeedMod;
    }
    
    /**
     * Gets the flat mp pool mod.
     *
     * @return the flat mp pool mod
     */
    public Double getFlatMPPoolMod()
    {
        return this.FlatMPPoolMod;
    }
    
    /**
     * Gets the flat mp regen mod.
     *
     * @return the flat mp regen mod
     */
    public Double getFlatMPRegenMod()
    {
        return this.FlatMPRegenMod;
    }
    
    /**
     * Gets the flat physical damage mod.
     *
     * @return the flat physical damage mod
     */
    public Double getFlatPhysicalDamageMod()
    {
        return this.FlatPhysicalDamageMod;
    }
    
    /**
     * Gets the flat spell block mod.
     *
     * @return the flat spell block mod
     */
    public Double getFlatSpellBlockMod()
    {
        return this.FlatSpellBlockMod;
    }
    
    /**
     * Gets the percent armor mod.
     *
     * @return the percent armor mod
     */
    public Double getPercentArmorMod()
    {
        return this.PercentArmorMod;
    }
    
    /**
     * Gets the percent attack speed mod.
     *
     * @return the percent attack speed mod
     */
    public Double getPercentAttackSpeedMod()
    {
        return this.PercentAttackSpeedMod;
    }
    
    /**
     * Gets the percent block mod.
     *
     * @return the percent block mod
     */
    public Double getPercentBlockMod()
    {
        return this.PercentBlockMod;
    }
    
    /**
     * Gets the percent crit chance mod.
     *
     * @return the percent crit chance mod
     */
    public Double getPercentCritChanceMod()
    {
        return this.PercentCritChanceMod;
    }
    
    /**
     * Gets the percent crit damage mod.
     *
     * @return the percent crit damage mod
     */
    public Double getPercentCritDamageMod()
    {
        return this.PercentCritDamageMod;
    }
    
    /**
     * Gets the percent dodge mod.
     *
     * @return the percent dodge mod
     */
    public Double getPercentDodgeMod()
    {
        return this.PercentDodgeMod;
    }
    
    /**
     * Gets the percent exp bonus.
     *
     * @return the percent exp bonus
     */
    public Double getPercentEXPBonus()
    {
        return this.PercentEXPBonus;
    }
    
    /**
     * Gets the percent hp pool mod.
     *
     * @return the percent hp pool mod
     */
    public Double getPercentHPPoolMod()
    {
        return this.PercentHPPoolMod;
    }
    
    /**
     * Gets the percent hp regen mod.
     *
     * @return the percent hp regen mod
     */
    public Double getPercentHPRegenMod()
    {
        return this.PercentHPRegenMod;
    }
    
    /**
     * Gets the percent life steal mod.
     *
     * @return the percent life steal mod
     */
    public Double getPercentLifeStealMod()
    {
        return this.PercentLifeStealMod;
    }
    
    /**
     * Gets the percent magic damage mod.
     *
     * @return the percent magic damage mod
     */
    public Double getPercentMagicDamageMod()
    {
        return this.PercentMagicDamageMod;
    }
    
    /**
     * Gets the percent movement speed mod.
     *
     * @return the percent movement speed mod
     */
    public Double getPercentMovementSpeedMod()
    {
        return this.PercentMovementSpeedMod;
    }
    
    /**
     * Gets the percent mp pool mod.
     *
     * @return the percent mp pool mod
     */
    public Double getPercentMPPoolMod()
    {
        return this.PercentMPPoolMod;
    }
    
    /**
     * Gets the percent mp regen mod.
     *
     * @return the percent mp regen mod
     */
    public Double getPercentMPRegenMod()
    {
        return this.PercentMPRegenMod;
    }
    
    /**
     * Gets the percent physical damage mod.
     *
     * @return the percent physical damage mod
     */
    public Double getPercentPhysicalDamageMod()
    {
        return this.PercentPhysicalDamageMod;
    }
    
    /**
     * Gets the percent spell block mod.
     *
     * @return the percent spell block mod
     */
    public Double getPercentSpellBlockMod()
    {
        return this.PercentSpellBlockMod;
    }
    
    /**
     * Gets the percent spell vamp mod.
     *
     * @return the percent spell vamp mod
     */
    public Double getPercentSpellVampMod()
    {
        return this.PercentSpellVampMod;
    }
    
    /**
     * Gets the r flat armor mod per level.
     *
     * @return the r flat armor mod per level
     */
    public Double getrFlatArmorModPerLevel()
    {
        return this.rFlatArmorModPerLevel;
    }
    
    /**
     * Gets the r flat armor penetration mod.
     *
     * @return the r flat armor penetration mod
     */
    public Double getrFlatArmorPenetrationMod()
    {
        return this.rFlatArmorPenetrationMod;
    }
    
    /**
     * Gets the r flat armor penetration mod per level.
     *
     * @return the r flat armor penetration mod per level
     */
    public Double getrFlatArmorPenetrationModPerLevel()
    {
        return this.rFlatArmorPenetrationModPerLevel;
    }
    
    /**
     * Gets the r flat crit chance mod per level.
     *
     * @return the r flat crit chance mod per level
     */
    public Double getrFlatCritChanceModPerLevel()
    {
        return this.rFlatCritChanceModPerLevel;
    }
    
    /**
     * Gets the r flat crit damage mod per level.
     *
     * @return the r flat crit damage mod per level
     */
    public Double getrFlatCritDamageModPerLevel()
    {
        return this.rFlatCritDamageModPerLevel;
    }
    
    /**
     * Gets the r flat dodge mod.
     *
     * @return the r flat dodge mod
     */
    public Double getrFlatDodgeMod()
    {
        return this.rFlatDodgeMod;
    }
    
    /**
     * Gets the r flat dodge mod per level.
     *
     * @return the r flat dodge mod per level
     */
    public Double getrFlatDodgeModPerLevel()
    {
        return this.rFlatDodgeModPerLevel;
    }
    
    /**
     * Gets the r flat energy mod per level.
     *
     * @return the r flat energy mod per level
     */
    public Double getrFlatEnergyModPerLevel()
    {
        return this.rFlatEnergyModPerLevel;
    }
    
    /**
     * Gets the r flat energy regen mod per level.
     *
     * @return the r flat energy regen mod per level
     */
    public Double getrFlatEnergyRegenModPerLevel()
    {
        return this.rFlatEnergyRegenModPerLevel;
    }
    
    /**
     * Gets the r flat gold per10 mod.
     *
     * @return the r flat gold per10 mod
     */
    public Double getrFlatGoldPer10Mod()
    {
        return this.rFlatGoldPer10Mod;
    }
    
    /**
     * Gets the r flat hp mod per level.
     *
     * @return the r flat hp mod per level
     */
    public Double getrFlatHPModPerLevel()
    {
        return this.rFlatHPModPerLevel;
    }
    
    /**
     * Gets the r flat hp regen mod per level.
     *
     * @return the r flat hp regen mod per level
     */
    public Double getrFlatHPRegenModPerLevel()
    {
        return this.rFlatHPRegenModPerLevel;
    }
    
    /**
     * Gets the r flat magic damage mod per level.
     *
     * @return the r flat magic damage mod per level
     */
    public Double getrFlatMagicDamageModPerLevel()
    {
        return this.rFlatMagicDamageModPerLevel;
    }
    
    /**
     * Gets the r flat magic penetration mod.
     *
     * @return the r flat magic penetration mod
     */
    public Double getrFlatMagicPenetrationMod()
    {
        return this.rFlatMagicPenetrationMod;
    }
    
    /**
     * Gets the r flat magic penetration mod per level.
     *
     * @return the r flat magic penetration mod per level
     */
    public Double getrFlatMagicPenetrationModPerLevel()
    {
        return this.rFlatMagicPenetrationModPerLevel;
    }
    
    /**
     * Gets the r flat movement speed mod per level.
     *
     * @return the r flat movement speed mod per level
     */
    public Double getrFlatMovementSpeedModPerLevel()
    {
        return this.rFlatMovementSpeedModPerLevel;
    }
    
    /**
     * Gets the r flat mp mod per level.
     *
     * @return the r flat mp mod per level
     */
    public Double getrFlatMPModPerLevel()
    {
        return this.rFlatMPModPerLevel;
    }
    
    /**
     * Gets the r flat mp regen mod per level.
     *
     * @return the r flat mp regen mod per level
     */
    public Double getrFlatMPRegenModPerLevel()
    {
        return this.rFlatMPRegenModPerLevel;
    }
    
    /**
     * Gets the r flat physical damage mod per level.
     *
     * @return the r flat physical damage mod per level
     */
    public Double getrFlatPhysicalDamageModPerLevel()
    {
        return this.rFlatPhysicalDamageModPerLevel;
    }
    
    /**
     * Gets the r flat spell block mod per level.
     *
     * @return the r flat spell block mod per level
     */
    public Double getrFlatSpellBlockModPerLevel()
    {
        return this.rFlatSpellBlockModPerLevel;
    }
    
    /**
     * Gets the r flat time dead mod.
     *
     * @return the r flat time dead mod
     */
    public Double getrFlatTimeDeadMod()
    {
        return this.rFlatTimeDeadMod;
    }
    
    /**
     * Gets the r flat time dead mod per level.
     *
     * @return the r flat time dead mod per level
     */
    public Double getrFlatTimeDeadModPerLevel()
    {
        return this.rFlatTimeDeadModPerLevel;
    }
    
    /**
     * Gets the r percent armor penetration mod.
     *
     * @return the r percent armor penetration mod
     */
    public Double getrPercentArmorPenetrationMod()
    {
        return this.rPercentArmorPenetrationMod;
    }
    
    /**
     * Gets the r percent armor penetration mod per level.
     *
     * @return the r percent armor penetration mod per level
     */
    public Double getrPercentArmorPenetrationModPerLevel()
    {
        return this.rPercentArmorPenetrationModPerLevel;
    }
    
    /**
     * Gets the r percent attack speed mod per level.
     *
     * @return the r percent attack speed mod per level
     */
    public Double getrPercentAttackSpeedModPerLevel()
    {
        return this.rPercentAttackSpeedModPerLevel;
    }
    
    /**
     * Gets the r percent cooldown mod.
     *
     * @return the r percent cooldown mod
     */
    public Double getrPercentCooldownMod()
    {
        return this.rPercentCooldownMod;
    }
    
    /**
     * Gets the r percent cooldown mod per level.
     *
     * @return the r percent cooldown mod per level
     */
    public Double getrPercentCooldownModPerLevel()
    {
        return this.rPercentCooldownModPerLevel;
    }
    
    /**
     * Gets the r percent magic penetration mod.
     *
     * @return the r percent magic penetration mod
     */
    public Double getrPercentMagicPenetrationMod()
    {
        return this.rPercentMagicPenetrationMod;
    }
    
    /**
     * Gets the r percent magic penetration mod per level.
     *
     * @return the r percent magic penetration mod per level
     */
    public Double getrPercentMagicPenetrationModPerLevel()
    {
        return this.rPercentMagicPenetrationModPerLevel;
    }
    
    /**
     * Gets the r percent movement speed mod per level.
     *
     * @return the r percent movement speed mod per level
     */
    public Double getrPercentMovementSpeedModPerLevel()
    {
        return this.rPercentMovementSpeedModPerLevel;
    }
    
    /**
     * Gets the r percent time dead mod.
     *
     * @return the r percent time dead mod
     */
    public Double getrPercentTimeDeadMod()
    {
        return this.rPercentTimeDeadMod;
    }
    
    /**
     * Gets the r percent time dead mod per level.
     *
     * @return the r percent time dead mod per level
     */
    public Double getrPercentTimeDeadModPerLevel()
    {
        return this.rPercentTimeDeadModPerLevel;
    }
    
    @Override
    public String toString()
    {
        return "BasicDataStats{" +
               "FlatArmorMod=" + FlatArmorMod +
               ", FlatAttackSpeedMod=" + FlatAttackSpeedMod +
               ", flatBlockMod=" + FlatBlockMod +
               ", flatCritChanceMod=" + FlatCritChanceMod +
               ", flatCritDamageMod=" + FlatCritDamageMod +
               ", flatEXPBonus=" + FlatEXPBonus +
               ", flatEnergyPoolMod=" + FlatEnergyPoolMod +
               ", flatEnergyRegenMod=" + FlatEnergyRegenMod +
               ", flatHPPoolMod=" + FlatHPPoolMod +
               ", flatHPRegenMod=" + FlatHPRegenMod +
               ", flatMPPoolMod=" + FlatMPPoolMod +
               ", flatMPRegenMod=" + FlatMPRegenMod +
               ", flatMagicDamageMod=" + FlatMagicDamageMod +
               ", flatMovementSpeedMod=" + FlatMovementSpeedMod +
               ", flatPhysicalDamageMod=" + FlatPhysicalDamageMod +
               ", flatSpellBlockMod=" + FlatSpellBlockMod +
               ", percentArmorMod=" + PercentArmorMod +
               ", percentAttackSpeedMod=" + PercentAttackSpeedMod +
               ", percentBlockMod=" + PercentBlockMod +
               ", percentCritChanceMod=" + PercentCritChanceMod +
               ", percentCritDamageMod=" + PercentCritDamageMod +
               ", percentDodgeMod=" + PercentDodgeMod +
               ", percentEXPBonus=" + PercentEXPBonus +
               ", percentHPPoolMod=" + PercentHPPoolMod +
               ", percentHPRegenMod=" + PercentHPRegenMod +
               ", percentLifeStealMod=" + PercentLifeStealMod +
               ", percentMPPoolMod=" + PercentMPPoolMod +
               ", percentMPRegenMod=" + PercentMPRegenMod +
               ", percentMagicDamageMod=" + PercentMagicDamageMod +
               ", percentMovementSpeedMod=" + PercentMovementSpeedMod +
               ", percentPhysicalDamageMod=" + PercentPhysicalDamageMod +
               ", percentSpellBlockMod=" + PercentSpellBlockMod +
               ", percentSpellVampMod=" + PercentSpellVampMod +
               ", rFlatArmorModPerLevel=" + rFlatArmorModPerLevel +
               ", rFlatArmorPenetrationMod=" + rFlatArmorPenetrationMod +
               ", rFlatArmorPenetrationModPerLevel=" + rFlatArmorPenetrationModPerLevel +
               ", rFlatCritChanceModPerLevel=" + rFlatCritChanceModPerLevel +
               ", rFlatCritDamageModPerLevel=" + rFlatCritDamageModPerLevel +
               ", rFlatDodgeMod=" + rFlatDodgeMod +
               ", rFlatDodgeModPerLevel=" + rFlatDodgeModPerLevel +
               ", rFlatEnergyModPerLevel=" + rFlatEnergyModPerLevel +
               ", rFlatEnergyRegenModPerLevel=" + rFlatEnergyRegenModPerLevel +
               ", rFlatGoldPer10Mod=" + rFlatGoldPer10Mod +
               ", rFlatHPModPerLevel=" + rFlatHPModPerLevel +
               ", rFlatHPRegenModPerLevel=" + rFlatHPRegenModPerLevel +
               ", rFlatMPModPerLevel=" + rFlatMPModPerLevel +
               ", rFlatMPRegenModPerLevel=" + rFlatMPRegenModPerLevel +
               ", rFlatMagicDamageModPerLevel=" + rFlatMagicDamageModPerLevel +
               ", rFlatMagicPenetrationMod=" + rFlatMagicPenetrationMod +
               ", rFlatMagicPenetrationModPerLevel=" + rFlatMagicPenetrationModPerLevel +
               ", rFlatMovementSpeedModPerLevel=" + rFlatMovementSpeedModPerLevel +
               ", rFlatPhysicalDamageModPerLevel=" + rFlatPhysicalDamageModPerLevel +
               ", rFlatSpellBlockModPerLevel=" + rFlatSpellBlockModPerLevel +
               ", rFlatTimeDeadMod=" + rFlatTimeDeadMod +
               ", rFlatTimeDeadModPerLevel=" + rFlatTimeDeadModPerLevel +
               ", rPercentArmorPenetrationMod=" + rPercentArmorPenetrationMod +
               ", rPercentArmorPenetrationModPerLevel=" + rPercentArmorPenetrationModPerLevel +
               ", rPercentAttackSpeedModPerLevel=" + rPercentAttackSpeedModPerLevel +
               ", rPercentCooldownMod=" + rPercentCooldownMod +
               ", rPercentCooldownModPerLevel=" + rPercentCooldownModPerLevel +
               ", rPercentMagicPenetrationMod=" + rPercentMagicPenetrationMod +
               ", rPercentMagicPenetrationModPerLevel=" + rPercentMagicPenetrationModPerLevel +
               ", rPercentMovementSpeedModPerLevel=" + rPercentMovementSpeedModPerLevel +
               ", rPercentTimeDeadMod=" + rPercentTimeDeadMod +
               ", rPercentTimeDeadModPerLevel=" + rPercentTimeDeadModPerLevel +
               '}';
    }
}
