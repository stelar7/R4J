package no.stelar7.api.l4j8.pojo.staticdata.shared;

public class BasicDataStats
{
    private Double flatArmorMod;
    private Double flatAttackSpeedMod;
    private Double flatBlockMod;
    private Double flatCritChanceMod;
    private Double flatCritDamageMod;
    private Double flatEXPBonus;
    private Double flatEnergyPoolMod;
    private Double flatEnergyRegenMod;
    private Double flatHPPoolMod;
    private Double flatHPRegenMod;
    private Double flatMPPoolMod;
    private Double flatMPRegenMod;
    private Double flatMagicDamageMod;
    private Double flatMovementSpeedMod;
    private Double flatPhysicalDamageMod;
    private Double flatSpellBlockMod;
    private Double percentArmorMod;
    private Double percentAttackSpeedMod;
    private Double percentBlockMod;
    private Double percentCritChanceMod;
    private Double percentCritDamageMod;
    private Double percentDodgeMod;
    private Double percentEXPBonus;
    private Double percentHPPoolMod;
    private Double percentHPRegenMod;
    private Double percentLifeStealMod;
    private Double percentMPPoolMod;
    private Double percentMPRegenMod;
    private Double percentMagicDamageMod;
    private Double percentMovementSpeedMod;
    private Double percentPhysicalDamageMod;
    private Double percentSpellBlockMod;
    private Double percentSpellVampMod;
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
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final BasicDataStats other = (BasicDataStats) obj;
        if (this.flatArmorMod == null)
        {
            if (other.flatArmorMod != null)
            {
                return false;
            }
        } else if (!this.flatArmorMod.equals(other.flatArmorMod))
        {
            return false;
        }
        if (this.flatAttackSpeedMod == null)
        {
            if (other.flatAttackSpeedMod != null)
            {
                return false;
            }
        } else if (!this.flatAttackSpeedMod.equals(other.flatAttackSpeedMod))
        {
            return false;
        }
        if (this.flatBlockMod == null)
        {
            if (other.flatBlockMod != null)
            {
                return false;
            }
        } else if (!this.flatBlockMod.equals(other.flatBlockMod))
        {
            return false;
        }
        if (this.flatCritChanceMod == null)
        {
            if (other.flatCritChanceMod != null)
            {
                return false;
            }
        } else if (!this.flatCritChanceMod.equals(other.flatCritChanceMod))
        {
            return false;
        }
        if (this.flatCritDamageMod == null)
        {
            if (other.flatCritDamageMod != null)
            {
                return false;
            }
        } else if (!this.flatCritDamageMod.equals(other.flatCritDamageMod))
        {
            return false;
        }
        if (this.flatEXPBonus == null)
        {
            if (other.flatEXPBonus != null)
            {
                return false;
            }
        } else if (!this.flatEXPBonus.equals(other.flatEXPBonus))
        {
            return false;
        }
        if (this.flatEnergyPoolMod == null)
        {
            if (other.flatEnergyPoolMod != null)
            {
                return false;
            }
        } else if (!this.flatEnergyPoolMod.equals(other.flatEnergyPoolMod))
        {
            return false;
        }
        if (this.flatEnergyRegenMod == null)
        {
            if (other.flatEnergyRegenMod != null)
            {
                return false;
            }
        } else if (!this.flatEnergyRegenMod.equals(other.flatEnergyRegenMod))
        {
            return false;
        }
        if (this.flatHPPoolMod == null)
        {
            if (other.flatHPPoolMod != null)
            {
                return false;
            }
        } else if (!this.flatHPPoolMod.equals(other.flatHPPoolMod))
        {
            return false;
        }
        if (this.flatHPRegenMod == null)
        {
            if (other.flatHPRegenMod != null)
            {
                return false;
            }
        } else if (!this.flatHPRegenMod.equals(other.flatHPRegenMod))
        {
            return false;
        }
        if (this.flatMPPoolMod == null)
        {
            if (other.flatMPPoolMod != null)
            {
                return false;
            }
        } else if (!this.flatMPPoolMod.equals(other.flatMPPoolMod))
        {
            return false;
        }
        if (this.flatMPRegenMod == null)
        {
            if (other.flatMPRegenMod != null)
            {
                return false;
            }
        } else if (!this.flatMPRegenMod.equals(other.flatMPRegenMod))
        {
            return false;
        }
        if (this.flatMagicDamageMod == null)
        {
            if (other.flatMagicDamageMod != null)
            {
                return false;
            }
        } else if (!this.flatMagicDamageMod.equals(other.flatMagicDamageMod))
        {
            return false;
        }
        if (this.flatMovementSpeedMod == null)
        {
            if (other.flatMovementSpeedMod != null)
            {
                return false;
            }
        } else if (!this.flatMovementSpeedMod.equals(other.flatMovementSpeedMod))
        {
            return false;
        }
        if (this.flatPhysicalDamageMod == null)
        {
            if (other.flatPhysicalDamageMod != null)
            {
                return false;
            }
        } else if (!this.flatPhysicalDamageMod.equals(other.flatPhysicalDamageMod))
        {
            return false;
        }
        if (this.flatSpellBlockMod == null)
        {
            if (other.flatSpellBlockMod != null)
            {
                return false;
            }
        } else if (!this.flatSpellBlockMod.equals(other.flatSpellBlockMod))
        {
            return false;
        }
        if (this.percentArmorMod == null)
        {
            if (other.percentArmorMod != null)
            {
                return false;
            }
        } else if (!this.percentArmorMod.equals(other.percentArmorMod))
        {
            return false;
        }
        if (this.percentAttackSpeedMod == null)
        {
            if (other.percentAttackSpeedMod != null)
            {
                return false;
            }
        } else if (!this.percentAttackSpeedMod.equals(other.percentAttackSpeedMod))
        {
            return false;
        }
        if (this.percentBlockMod == null)
        {
            if (other.percentBlockMod != null)
            {
                return false;
            }
        } else if (!this.percentBlockMod.equals(other.percentBlockMod))
        {
            return false;
        }
        if (this.percentCritChanceMod == null)
        {
            if (other.percentCritChanceMod != null)
            {
                return false;
            }
        } else if (!this.percentCritChanceMod.equals(other.percentCritChanceMod))
        {
            return false;
        }
        if (this.percentCritDamageMod == null)
        {
            if (other.percentCritDamageMod != null)
            {
                return false;
            }
        } else if (!this.percentCritDamageMod.equals(other.percentCritDamageMod))
        {
            return false;
        }
        if (this.percentDodgeMod == null)
        {
            if (other.percentDodgeMod != null)
            {
                return false;
            }
        } else if (!this.percentDodgeMod.equals(other.percentDodgeMod))
        {
            return false;
        }
        if (this.percentEXPBonus == null)
        {
            if (other.percentEXPBonus != null)
            {
                return false;
            }
        } else if (!this.percentEXPBonus.equals(other.percentEXPBonus))
        {
            return false;
        }
        if (this.percentHPPoolMod == null)
        {
            if (other.percentHPPoolMod != null)
            {
                return false;
            }
        } else if (!this.percentHPPoolMod.equals(other.percentHPPoolMod))
        {
            return false;
        }
        if (this.percentHPRegenMod == null)
        {
            if (other.percentHPRegenMod != null)
            {
                return false;
            }
        } else if (!this.percentHPRegenMod.equals(other.percentHPRegenMod))
        {
            return false;
        }
        if (this.percentLifeStealMod == null)
        {
            if (other.percentLifeStealMod != null)
            {
                return false;
            }
        } else if (!this.percentLifeStealMod.equals(other.percentLifeStealMod))
        {
            return false;
        }
        if (this.percentMPPoolMod == null)
        {
            if (other.percentMPPoolMod != null)
            {
                return false;
            }
        } else if (!this.percentMPPoolMod.equals(other.percentMPPoolMod))
        {
            return false;
        }
        if (this.percentMPRegenMod == null)
        {
            if (other.percentMPRegenMod != null)
            {
                return false;
            }
        } else if (!this.percentMPRegenMod.equals(other.percentMPRegenMod))
        {
            return false;
        }
        if (this.percentMagicDamageMod == null)
        {
            if (other.percentMagicDamageMod != null)
            {
                return false;
            }
        } else if (!this.percentMagicDamageMod.equals(other.percentMagicDamageMod))
        {
            return false;
        }
        if (this.percentMovementSpeedMod == null)
        {
            if (other.percentMovementSpeedMod != null)
            {
                return false;
            }
        } else if (!this.percentMovementSpeedMod.equals(other.percentMovementSpeedMod))
        {
            return false;
        }
        if (this.percentPhysicalDamageMod == null)
        {
            if (other.percentPhysicalDamageMod != null)
            {
                return false;
            }
        } else if (!this.percentPhysicalDamageMod.equals(other.percentPhysicalDamageMod))
        {
            return false;
        }
        if (this.percentSpellBlockMod == null)
        {
            if (other.percentSpellBlockMod != null)
            {
                return false;
            }
        } else if (!this.percentSpellBlockMod.equals(other.percentSpellBlockMod))
        {
            return false;
        }
        if (this.percentSpellVampMod == null)
        {
            if (other.percentSpellVampMod != null)
            {
                return false;
            }
        } else if (!this.percentSpellVampMod.equals(other.percentSpellVampMod))
        {
            return false;
        }
        if (this.rFlatArmorModPerLevel == null)
        {
            if (other.rFlatArmorModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatArmorModPerLevel.equals(other.rFlatArmorModPerLevel))
        {
            return false;
        }
        if (this.rFlatArmorPenetrationMod == null)
        {
            if (other.rFlatArmorPenetrationMod != null)
            {
                return false;
            }
        } else if (!this.rFlatArmorPenetrationMod.equals(other.rFlatArmorPenetrationMod))
        {
            return false;
        }
        if (this.rFlatArmorPenetrationModPerLevel == null)
        {
            if (other.rFlatArmorPenetrationModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatArmorPenetrationModPerLevel.equals(other.rFlatArmorPenetrationModPerLevel))
        {
            return false;
        }
        if (this.rFlatCritChanceModPerLevel == null)
        {
            if (other.rFlatCritChanceModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatCritChanceModPerLevel.equals(other.rFlatCritChanceModPerLevel))
        {
            return false;
        }
        if (this.rFlatCritDamageModPerLevel == null)
        {
            if (other.rFlatCritDamageModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatCritDamageModPerLevel.equals(other.rFlatCritDamageModPerLevel))
        {
            return false;
        }
        if (this.rFlatDodgeMod == null)
        {
            if (other.rFlatDodgeMod != null)
            {
                return false;
            }
        } else if (!this.rFlatDodgeMod.equals(other.rFlatDodgeMod))
        {
            return false;
        }
        if (this.rFlatDodgeModPerLevel == null)
        {
            if (other.rFlatDodgeModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatDodgeModPerLevel.equals(other.rFlatDodgeModPerLevel))
        {
            return false;
        }
        if (this.rFlatEnergyModPerLevel == null)
        {
            if (other.rFlatEnergyModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatEnergyModPerLevel.equals(other.rFlatEnergyModPerLevel))
        {
            return false;
        }
        if (this.rFlatEnergyRegenModPerLevel == null)
        {
            if (other.rFlatEnergyRegenModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatEnergyRegenModPerLevel.equals(other.rFlatEnergyRegenModPerLevel))
        {
            return false;
        }
        if (this.rFlatGoldPer10Mod == null)
        {
            if (other.rFlatGoldPer10Mod != null)
            {
                return false;
            }
        } else if (!this.rFlatGoldPer10Mod.equals(other.rFlatGoldPer10Mod))
        {
            return false;
        }
        if (this.rFlatHPModPerLevel == null)
        {
            if (other.rFlatHPModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatHPModPerLevel.equals(other.rFlatHPModPerLevel))
        {
            return false;
        }
        if (this.rFlatHPRegenModPerLevel == null)
        {
            if (other.rFlatHPRegenModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatHPRegenModPerLevel.equals(other.rFlatHPRegenModPerLevel))
        {
            return false;
        }
        if (this.rFlatMPModPerLevel == null)
        {
            if (other.rFlatMPModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatMPModPerLevel.equals(other.rFlatMPModPerLevel))
        {
            return false;
        }
        if (this.rFlatMPRegenModPerLevel == null)
        {
            if (other.rFlatMPRegenModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatMPRegenModPerLevel.equals(other.rFlatMPRegenModPerLevel))
        {
            return false;
        }
        if (this.rFlatMagicDamageModPerLevel == null)
        {
            if (other.rFlatMagicDamageModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatMagicDamageModPerLevel.equals(other.rFlatMagicDamageModPerLevel))
        {
            return false;
        }
        if (this.rFlatMagicPenetrationMod == null)
        {
            if (other.rFlatMagicPenetrationMod != null)
            {
                return false;
            }
        } else if (!this.rFlatMagicPenetrationMod.equals(other.rFlatMagicPenetrationMod))
        {
            return false;
        }
        if (this.rFlatMagicPenetrationModPerLevel == null)
        {
            if (other.rFlatMagicPenetrationModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatMagicPenetrationModPerLevel.equals(other.rFlatMagicPenetrationModPerLevel))
        {
            return false;
        }
        if (this.rFlatMovementSpeedModPerLevel == null)
        {
            if (other.rFlatMovementSpeedModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatMovementSpeedModPerLevel.equals(other.rFlatMovementSpeedModPerLevel))
        {
            return false;
        }
        if (this.rFlatPhysicalDamageModPerLevel == null)
        {
            if (other.rFlatPhysicalDamageModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatPhysicalDamageModPerLevel.equals(other.rFlatPhysicalDamageModPerLevel))
        {
            return false;
        }
        if (this.rFlatSpellBlockModPerLevel == null)
        {
            if (other.rFlatSpellBlockModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatSpellBlockModPerLevel.equals(other.rFlatSpellBlockModPerLevel))
        {
            return false;
        }
        if (this.rFlatTimeDeadMod == null)
        {
            if (other.rFlatTimeDeadMod != null)
            {
                return false;
            }
        } else if (!this.rFlatTimeDeadMod.equals(other.rFlatTimeDeadMod))
        {
            return false;
        }
        if (this.rFlatTimeDeadModPerLevel == null)
        {
            if (other.rFlatTimeDeadModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rFlatTimeDeadModPerLevel.equals(other.rFlatTimeDeadModPerLevel))
        {
            return false;
        }
        if (this.rPercentArmorPenetrationMod == null)
        {
            if (other.rPercentArmorPenetrationMod != null)
            {
                return false;
            }
        } else if (!this.rPercentArmorPenetrationMod.equals(other.rPercentArmorPenetrationMod))
        {
            return false;
        }
        if (this.rPercentArmorPenetrationModPerLevel == null)
        {
            if (other.rPercentArmorPenetrationModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rPercentArmorPenetrationModPerLevel.equals(other.rPercentArmorPenetrationModPerLevel))
        {
            return false;
        }
        if (this.rPercentAttackSpeedModPerLevel == null)
        {
            if (other.rPercentAttackSpeedModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rPercentAttackSpeedModPerLevel.equals(other.rPercentAttackSpeedModPerLevel))
        {
            return false;
        }
        if (this.rPercentCooldownMod == null)
        {
            if (other.rPercentCooldownMod != null)
            {
                return false;
            }
        } else if (!this.rPercentCooldownMod.equals(other.rPercentCooldownMod))
        {
            return false;
        }
        if (this.rPercentCooldownModPerLevel == null)
        {
            if (other.rPercentCooldownModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rPercentCooldownModPerLevel.equals(other.rPercentCooldownModPerLevel))
        {
            return false;
        }
        if (this.rPercentMagicPenetrationMod == null)
        {
            if (other.rPercentMagicPenetrationMod != null)
            {
                return false;
            }
        } else if (!this.rPercentMagicPenetrationMod.equals(other.rPercentMagicPenetrationMod))
        {
            return false;
        }
        if (this.rPercentMagicPenetrationModPerLevel == null)
        {
            if (other.rPercentMagicPenetrationModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rPercentMagicPenetrationModPerLevel.equals(other.rPercentMagicPenetrationModPerLevel))
        {
            return false;
        }
        if (this.rPercentMovementSpeedModPerLevel == null)
        {
            if (other.rPercentMovementSpeedModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rPercentMovementSpeedModPerLevel.equals(other.rPercentMovementSpeedModPerLevel))
        {
            return false;
        }
        if (this.rPercentTimeDeadMod == null)
        {
            if (other.rPercentTimeDeadMod != null)
            {
                return false;
            }
        } else if (!this.rPercentTimeDeadMod.equals(other.rPercentTimeDeadMod))
        {
            return false;
        }
        if (this.rPercentTimeDeadModPerLevel == null)
        {
            if (other.rPercentTimeDeadModPerLevel != null)
            {
                return false;
            }
        } else if (!this.rPercentTimeDeadModPerLevel.equals(other.rPercentTimeDeadModPerLevel))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the flat armor mod.
     *
     * @return the flat armor mod
     */
    public Double getFlatArmorMod()
    {
        return this.flatArmorMod;
    }
    
    /**
     * Gets the flat attack speed mod.
     *
     * @return the flat attack speed mod
     */
    public Double getFlatAttackSpeedMod()
    {
        return this.flatAttackSpeedMod;
    }
    
    /**
     * Gets the flat block mod.
     *
     * @return the flat block mod
     */
    public Double getFlatBlockMod()
    {
        return this.flatBlockMod;
    }
    
    /**
     * Gets the flat crit chance mod.
     *
     * @return the flat crit chance mod
     */
    public Double getFlatCritChanceMod()
    {
        return this.flatCritChanceMod;
    }
    
    /**
     * Gets the flat crit damage mod.
     *
     * @return the flat crit damage mod
     */
    public Double getFlatCritDamageMod()
    {
        return this.flatCritDamageMod;
    }
    
    /**
     * Gets the flat energy pool mod.
     *
     * @return the flat energy pool mod
     */
    public Double getFlatEnergyPoolMod()
    {
        return this.flatEnergyPoolMod;
    }
    
    /**
     * Gets the flat energy regen mod.
     *
     * @return the flat energy regen mod
     */
    public Double getFlatEnergyRegenMod()
    {
        return this.flatEnergyRegenMod;
    }
    
    /**
     * Gets the flat exp bonus.
     *
     * @return the flat exp bonus
     */
    public Double getFlatEXPBonus()
    {
        return this.flatEXPBonus;
    }
    
    /**
     * Gets the flat hp pool mod.
     *
     * @return the flat hp pool mod
     */
    public Double getFlatHPPoolMod()
    {
        return this.flatHPPoolMod;
    }
    
    /**
     * Gets the flat hp regen mod.
     *
     * @return the flat hp regen mod
     */
    public Double getFlatHPRegenMod()
    {
        return this.flatHPRegenMod;
    }
    
    /**
     * Gets the flat magic damage mod.
     *
     * @return the flat magic damage mod
     */
    public Double getFlatMagicDamageMod()
    {
        return this.flatMagicDamageMod;
    }
    
    /**
     * Gets the flat movement speed mod.
     *
     * @return the flat movement speed mod
     */
    public Double getFlatMovementSpeedMod()
    {
        return this.flatMovementSpeedMod;
    }
    
    /**
     * Gets the flat mp pool mod.
     *
     * @return the flat mp pool mod
     */
    public Double getFlatMPPoolMod()
    {
        return this.flatMPPoolMod;
    }
    
    /**
     * Gets the flat mp regen mod.
     *
     * @return the flat mp regen mod
     */
    public Double getFlatMPRegenMod()
    {
        return this.flatMPRegenMod;
    }
    
    /**
     * Gets the flat physical damage mod.
     *
     * @return the flat physical damage mod
     */
    public Double getFlatPhysicalDamageMod()
    {
        return this.flatPhysicalDamageMod;
    }
    
    /**
     * Gets the flat spell block mod.
     *
     * @return the flat spell block mod
     */
    public Double getFlatSpellBlockMod()
    {
        return this.flatSpellBlockMod;
    }
    
    /**
     * Gets the percent armor mod.
     *
     * @return the percent armor mod
     */
    public Double getPercentArmorMod()
    {
        return this.percentArmorMod;
    }
    
    /**
     * Gets the percent attack speed mod.
     *
     * @return the percent attack speed mod
     */
    public Double getPercentAttackSpeedMod()
    {
        return this.percentAttackSpeedMod;
    }
    
    /**
     * Gets the percent block mod.
     *
     * @return the percent block mod
     */
    public Double getPercentBlockMod()
    {
        return this.percentBlockMod;
    }
    
    /**
     * Gets the percent crit chance mod.
     *
     * @return the percent crit chance mod
     */
    public Double getPercentCritChanceMod()
    {
        return this.percentCritChanceMod;
    }
    
    /**
     * Gets the percent crit damage mod.
     *
     * @return the percent crit damage mod
     */
    public Double getPercentCritDamageMod()
    {
        return this.percentCritDamageMod;
    }
    
    /**
     * Gets the percent dodge mod.
     *
     * @return the percent dodge mod
     */
    public Double getPercentDodgeMod()
    {
        return this.percentDodgeMod;
    }
    
    /**
     * Gets the percent exp bonus.
     *
     * @return the percent exp bonus
     */
    public Double getPercentEXPBonus()
    {
        return this.percentEXPBonus;
    }
    
    /**
     * Gets the percent hp pool mod.
     *
     * @return the percent hp pool mod
     */
    public Double getPercentHPPoolMod()
    {
        return this.percentHPPoolMod;
    }
    
    /**
     * Gets the percent hp regen mod.
     *
     * @return the percent hp regen mod
     */
    public Double getPercentHPRegenMod()
    {
        return this.percentHPRegenMod;
    }
    
    /**
     * Gets the percent life steal mod.
     *
     * @return the percent life steal mod
     */
    public Double getPercentLifeStealMod()
    {
        return this.percentLifeStealMod;
    }
    
    /**
     * Gets the percent magic damage mod.
     *
     * @return the percent magic damage mod
     */
    public Double getPercentMagicDamageMod()
    {
        return this.percentMagicDamageMod;
    }
    
    /**
     * Gets the percent movement speed mod.
     *
     * @return the percent movement speed mod
     */
    public Double getPercentMovementSpeedMod()
    {
        return this.percentMovementSpeedMod;
    }
    
    /**
     * Gets the percent mp pool mod.
     *
     * @return the percent mp pool mod
     */
    public Double getPercentMPPoolMod()
    {
        return this.percentMPPoolMod;
    }
    
    /**
     * Gets the percent mp regen mod.
     *
     * @return the percent mp regen mod
     */
    public Double getPercentMPRegenMod()
    {
        return this.percentMPRegenMod;
    }
    
    /**
     * Gets the percent physical damage mod.
     *
     * @return the percent physical damage mod
     */
    public Double getPercentPhysicalDamageMod()
    {
        return this.percentPhysicalDamageMod;
    }
    
    /**
     * Gets the percent spell block mod.
     *
     * @return the percent spell block mod
     */
    public Double getPercentSpellBlockMod()
    {
        return this.percentSpellBlockMod;
    }
    
    /**
     * Gets the percent spell vamp mod.
     *
     * @return the percent spell vamp mod
     */
    public Double getPercentSpellVampMod()
    {
        return this.percentSpellVampMod;
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
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.flatArmorMod == null) ? 0 : this.flatArmorMod.hashCode());
        result = (prime * result) + ((this.flatAttackSpeedMod == null) ? 0 : this.flatAttackSpeedMod.hashCode());
        result = (prime * result) + ((this.flatBlockMod == null) ? 0 : this.flatBlockMod.hashCode());
        result = (prime * result) + ((this.flatCritChanceMod == null) ? 0 : this.flatCritChanceMod.hashCode());
        result = (prime * result) + ((this.flatCritDamageMod == null) ? 0 : this.flatCritDamageMod.hashCode());
        result = (prime * result) + ((this.flatEXPBonus == null) ? 0 : this.flatEXPBonus.hashCode());
        result = (prime * result) + ((this.flatEnergyPoolMod == null) ? 0 : this.flatEnergyPoolMod.hashCode());
        result = (prime * result) + ((this.flatEnergyRegenMod == null) ? 0 : this.flatEnergyRegenMod.hashCode());
        result = (prime * result) + ((this.flatHPPoolMod == null) ? 0 : this.flatHPPoolMod.hashCode());
        result = (prime * result) + ((this.flatHPRegenMod == null) ? 0 : this.flatHPRegenMod.hashCode());
        result = (prime * result) + ((this.flatMPPoolMod == null) ? 0 : this.flatMPPoolMod.hashCode());
        result = (prime * result) + ((this.flatMPRegenMod == null) ? 0 : this.flatMPRegenMod.hashCode());
        result = (prime * result) + ((this.flatMagicDamageMod == null) ? 0 : this.flatMagicDamageMod.hashCode());
        result = (prime * result) + ((this.flatMovementSpeedMod == null) ? 0 : this.flatMovementSpeedMod.hashCode());
        result = (prime * result) + ((this.flatPhysicalDamageMod == null) ? 0 : this.flatPhysicalDamageMod.hashCode());
        result = (prime * result) + ((this.flatSpellBlockMod == null) ? 0 : this.flatSpellBlockMod.hashCode());
        result = (prime * result) + ((this.percentArmorMod == null) ? 0 : this.percentArmorMod.hashCode());
        result = (prime * result) + ((this.percentAttackSpeedMod == null) ? 0 : this.percentAttackSpeedMod.hashCode());
        result = (prime * result) + ((this.percentBlockMod == null) ? 0 : this.percentBlockMod.hashCode());
        result = (prime * result) + ((this.percentCritChanceMod == null) ? 0 : this.percentCritChanceMod.hashCode());
        result = (prime * result) + ((this.percentCritDamageMod == null) ? 0 : this.percentCritDamageMod.hashCode());
        result = (prime * result) + ((this.percentDodgeMod == null) ? 0 : this.percentDodgeMod.hashCode());
        result = (prime * result) + ((this.percentEXPBonus == null) ? 0 : this.percentEXPBonus.hashCode());
        result = (prime * result) + ((this.percentHPPoolMod == null) ? 0 : this.percentHPPoolMod.hashCode());
        result = (prime * result) + ((this.percentHPRegenMod == null) ? 0 : this.percentHPRegenMod.hashCode());
        result = (prime * result) + ((this.percentLifeStealMod == null) ? 0 : this.percentLifeStealMod.hashCode());
        result = (prime * result) + ((this.percentMPPoolMod == null) ? 0 : this.percentMPPoolMod.hashCode());
        result = (prime * result) + ((this.percentMPRegenMod == null) ? 0 : this.percentMPRegenMod.hashCode());
        result = (prime * result) + ((this.percentMagicDamageMod == null) ? 0 : this.percentMagicDamageMod.hashCode());
        result = (prime * result) + ((this.percentMovementSpeedMod == null) ? 0 : this.percentMovementSpeedMod.hashCode());
        result = (prime * result) + ((this.percentPhysicalDamageMod == null) ? 0 : this.percentPhysicalDamageMod.hashCode());
        result = (prime * result) + ((this.percentSpellBlockMod == null) ? 0 : this.percentSpellBlockMod.hashCode());
        result = (prime * result) + ((this.percentSpellVampMod == null) ? 0 : this.percentSpellVampMod.hashCode());
        result = (prime * result) + ((this.rFlatArmorModPerLevel == null) ? 0 : this.rFlatArmorModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatArmorPenetrationMod == null) ? 0 : this.rFlatArmorPenetrationMod.hashCode());
        result = (prime * result) + ((this.rFlatArmorPenetrationModPerLevel == null) ? 0 : this.rFlatArmorPenetrationModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatCritChanceModPerLevel == null) ? 0 : this.rFlatCritChanceModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatCritDamageModPerLevel == null) ? 0 : this.rFlatCritDamageModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatDodgeMod == null) ? 0 : this.rFlatDodgeMod.hashCode());
        result = (prime * result) + ((this.rFlatDodgeModPerLevel == null) ? 0 : this.rFlatDodgeModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatEnergyModPerLevel == null) ? 0 : this.rFlatEnergyModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatEnergyRegenModPerLevel == null) ? 0 : this.rFlatEnergyRegenModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatGoldPer10Mod == null) ? 0 : this.rFlatGoldPer10Mod.hashCode());
        result = (prime * result) + ((this.rFlatHPModPerLevel == null) ? 0 : this.rFlatHPModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatHPRegenModPerLevel == null) ? 0 : this.rFlatHPRegenModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatMPModPerLevel == null) ? 0 : this.rFlatMPModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatMPRegenModPerLevel == null) ? 0 : this.rFlatMPRegenModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatMagicDamageModPerLevel == null) ? 0 : this.rFlatMagicDamageModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatMagicPenetrationMod == null) ? 0 : this.rFlatMagicPenetrationMod.hashCode());
        result = (prime * result) + ((this.rFlatMagicPenetrationModPerLevel == null) ? 0 : this.rFlatMagicPenetrationModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatMovementSpeedModPerLevel == null) ? 0 : this.rFlatMovementSpeedModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatPhysicalDamageModPerLevel == null) ? 0 : this.rFlatPhysicalDamageModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatSpellBlockModPerLevel == null) ? 0 : this.rFlatSpellBlockModPerLevel.hashCode());
        result = (prime * result) + ((this.rFlatTimeDeadMod == null) ? 0 : this.rFlatTimeDeadMod.hashCode());
        result = (prime * result) + ((this.rFlatTimeDeadModPerLevel == null) ? 0 : this.rFlatTimeDeadModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentArmorPenetrationMod == null) ? 0 : this.rPercentArmorPenetrationMod.hashCode());
        result = (prime * result) + ((this.rPercentArmorPenetrationModPerLevel == null) ? 0 : this.rPercentArmorPenetrationModPerLevel
                                                                                                  .hashCode());
        result = (prime * result) + ((this.rPercentAttackSpeedModPerLevel == null) ? 0 : this.rPercentAttackSpeedModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentCooldownMod == null) ? 0 : this.rPercentCooldownMod.hashCode());
        result = (prime * result) + ((this.rPercentCooldownModPerLevel == null) ? 0 : this.rPercentCooldownModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentMagicPenetrationMod == null) ? 0 : this.rPercentMagicPenetrationMod.hashCode());
        result = (prime * result) + ((this.rPercentMagicPenetrationModPerLevel == null) ? 0 : this.rPercentMagicPenetrationModPerLevel
                                                                                                  .hashCode());
        result = (prime * result) + ((this.rPercentMovementSpeedModPerLevel == null) ? 0 : this.rPercentMovementSpeedModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentTimeDeadMod == null) ? 0 : this.rPercentTimeDeadMod.hashCode());
        result = (prime * result) + ((this.rPercentTimeDeadModPerLevel == null) ? 0 : this.rPercentTimeDeadModPerLevel.hashCode());
        return result;
    }
    
}
