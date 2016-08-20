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

    @SuppressWarnings("ConstantConditions")
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
        if (this.FlatArmorMod == null)
        {
            if (other.FlatArmorMod != null)
            {
                return false;
            }
        } else if (!this.FlatArmorMod.equals(other.FlatArmorMod))
        {
            return false;
        }
        if (this.FlatAttackSpeedMod == null)
        {
            if (other.FlatAttackSpeedMod != null)
            {
                return false;
            }
        } else if (!this.FlatAttackSpeedMod.equals(other.FlatAttackSpeedMod))
        {
            return false;
        }
        if (this.FlatBlockMod == null)
        {
            if (other.FlatBlockMod != null)
            {
                return false;
            }
        } else if (!this.FlatBlockMod.equals(other.FlatBlockMod))
        {
            return false;
        }
        if (this.FlatCritChanceMod == null)
        {
            if (other.FlatCritChanceMod != null)
            {
                return false;
            }
        } else if (!this.FlatCritChanceMod.equals(other.FlatCritChanceMod))
        {
            return false;
        }
        if (this.FlatCritDamageMod == null)
        {
            if (other.FlatCritDamageMod != null)
            {
                return false;
            }
        } else if (!this.FlatCritDamageMod.equals(other.FlatCritDamageMod))
        {
            return false;
        }
        if (this.FlatEXPBonus == null)
        {
            if (other.FlatEXPBonus != null)
            {
                return false;
            }
        } else if (!this.FlatEXPBonus.equals(other.FlatEXPBonus))
        {
            return false;
        }
        if (this.FlatEnergyPoolMod == null)
        {
            if (other.FlatEnergyPoolMod != null)
            {
                return false;
            }
        } else if (!this.FlatEnergyPoolMod.equals(other.FlatEnergyPoolMod))
        {
            return false;
        }
        if (this.FlatEnergyRegenMod == null)
        {
            if (other.FlatEnergyRegenMod != null)
            {
                return false;
            }
        } else if (!this.FlatEnergyRegenMod.equals(other.FlatEnergyRegenMod))
        {
            return false;
        }
        if (this.FlatHPPoolMod == null)
        {
            if (other.FlatHPPoolMod != null)
            {
                return false;
            }
        } else if (!this.FlatHPPoolMod.equals(other.FlatHPPoolMod))
        {
            return false;
        }
        if (this.FlatHPRegenMod == null)
        {
            if (other.FlatHPRegenMod != null)
            {
                return false;
            }
        } else if (!this.FlatHPRegenMod.equals(other.FlatHPRegenMod))
        {
            return false;
        }
        if (this.FlatMPPoolMod == null)
        {
            if (other.FlatMPPoolMod != null)
            {
                return false;
            }
        } else if (!this.FlatMPPoolMod.equals(other.FlatMPPoolMod))
        {
            return false;
        }
        if (this.FlatMPRegenMod == null)
        {
            if (other.FlatMPRegenMod != null)
            {
                return false;
            }
        } else if (!this.FlatMPRegenMod.equals(other.FlatMPRegenMod))
        {
            return false;
        }
        if (this.FlatMagicDamageMod == null)
        {
            if (other.FlatMagicDamageMod != null)
            {
                return false;
            }
        } else if (!this.FlatMagicDamageMod.equals(other.FlatMagicDamageMod))
        {
            return false;
        }
        if (this.FlatMovementSpeedMod == null)
        {
            if (other.FlatMovementSpeedMod != null)
            {
                return false;
            }
        } else if (!this.FlatMovementSpeedMod.equals(other.FlatMovementSpeedMod))
        {
            return false;
        }
        if (this.FlatPhysicalDamageMod == null)
        {
            if (other.FlatPhysicalDamageMod != null)
            {
                return false;
            }
        } else if (!this.FlatPhysicalDamageMod.equals(other.FlatPhysicalDamageMod))
        {
            return false;
        }
        if (this.FlatSpellBlockMod == null)
        {
            if (other.FlatSpellBlockMod != null)
            {
                return false;
            }
        } else if (!this.FlatSpellBlockMod.equals(other.FlatSpellBlockMod))
        {
            return false;
        }
        if (this.PercentArmorMod == null)
        {
            if (other.PercentArmorMod != null)
            {
                return false;
            }
        } else if (!this.PercentArmorMod.equals(other.PercentArmorMod))
        {
            return false;
        }
        if (this.PercentAttackSpeedMod == null)
        {
            if (other.PercentAttackSpeedMod != null)
            {
                return false;
            }
        } else if (!this.PercentAttackSpeedMod.equals(other.PercentAttackSpeedMod))
        {
            return false;
        }
        if (this.PercentBlockMod == null)
        {
            if (other.PercentBlockMod != null)
            {
                return false;
            }
        } else if (!this.PercentBlockMod.equals(other.PercentBlockMod))
        {
            return false;
        }
        if (this.PercentCritChanceMod == null)
        {
            if (other.PercentCritChanceMod != null)
            {
                return false;
            }
        } else if (!this.PercentCritChanceMod.equals(other.PercentCritChanceMod))
        {
            return false;
        }
        if (this.PercentCritDamageMod == null)
        {
            if (other.PercentCritDamageMod != null)
            {
                return false;
            }
        } else if (!this.PercentCritDamageMod.equals(other.PercentCritDamageMod))
        {
            return false;
        }
        if (this.PercentDodgeMod == null)
        {
            if (other.PercentDodgeMod != null)
            {
                return false;
            }
        } else if (!this.PercentDodgeMod.equals(other.PercentDodgeMod))
        {
            return false;
        }
        if (this.PercentEXPBonus == null)
        {
            if (other.PercentEXPBonus != null)
            {
                return false;
            }
        } else if (!this.PercentEXPBonus.equals(other.PercentEXPBonus))
        {
            return false;
        }
        if (this.PercentHPPoolMod == null)
        {
            if (other.PercentHPPoolMod != null)
            {
                return false;
            }
        } else if (!this.PercentHPPoolMod.equals(other.PercentHPPoolMod))
        {
            return false;
        }
        if (this.PercentHPRegenMod == null)
        {
            if (other.PercentHPRegenMod != null)
            {
                return false;
            }
        } else if (!this.PercentHPRegenMod.equals(other.PercentHPRegenMod))
        {
            return false;
        }
        if (this.PercentLifeStealMod == null)
        {
            if (other.PercentLifeStealMod != null)
            {
                return false;
            }
        } else if (!this.PercentLifeStealMod.equals(other.PercentLifeStealMod))
        {
            return false;
        }
        if (this.PercentMPPoolMod == null)
        {
            if (other.PercentMPPoolMod != null)
            {
                return false;
            }
        } else if (!this.PercentMPPoolMod.equals(other.PercentMPPoolMod))
        {
            return false;
        }
        if (this.PercentMPRegenMod == null)
        {
            if (other.PercentMPRegenMod != null)
            {
                return false;
            }
        } else if (!this.PercentMPRegenMod.equals(other.PercentMPRegenMod))
        {
            return false;
        }
        if (this.PercentMagicDamageMod == null)
        {
            if (other.PercentMagicDamageMod != null)
            {
                return false;
            }
        } else if (!this.PercentMagicDamageMod.equals(other.PercentMagicDamageMod))
        {
            return false;
        }
        if (this.PercentMovementSpeedMod == null)
        {
            if (other.PercentMovementSpeedMod != null)
            {
                return false;
            }
        } else if (!this.PercentMovementSpeedMod.equals(other.PercentMovementSpeedMod))
        {
            return false;
        }
        if (this.PercentPhysicalDamageMod == null)
        {
            if (other.PercentPhysicalDamageMod != null)
            {
                return false;
            }
        } else if (!this.PercentPhysicalDamageMod.equals(other.PercentPhysicalDamageMod))
        {
            return false;
        }
        if (this.PercentSpellBlockMod == null)
        {
            if (other.PercentSpellBlockMod != null)
            {
                return false;
            }
        } else if (!this.PercentSpellBlockMod.equals(other.PercentSpellBlockMod))
        {
            return false;
        }
        if (this.PercentSpellVampMod == null)
        {
            if (other.PercentSpellVampMod != null)
            {
                return false;
            }
        } else if (!this.PercentSpellVampMod.equals(other.PercentSpellVampMod))
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
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.FlatArmorMod == null) ? 0 : this.FlatArmorMod.hashCode());
        result = (prime * result) + ((this.FlatAttackSpeedMod == null) ? 0 : this.FlatAttackSpeedMod.hashCode());
        result = (prime * result) + ((this.FlatBlockMod == null) ? 0 : this.FlatBlockMod.hashCode());
        result = (prime * result) + ((this.FlatCritChanceMod == null) ? 0 : this.FlatCritChanceMod.hashCode());
        result = (prime * result) + ((this.FlatCritDamageMod == null) ? 0 : this.FlatCritDamageMod.hashCode());
        result = (prime * result) + ((this.FlatEXPBonus == null) ? 0 : this.FlatEXPBonus.hashCode());
        result = (prime * result) + ((this.FlatEnergyPoolMod == null) ? 0 : this.FlatEnergyPoolMod.hashCode());
        result = (prime * result) + ((this.FlatEnergyRegenMod == null) ? 0 : this.FlatEnergyRegenMod.hashCode());
        result = (prime * result) + ((this.FlatHPPoolMod == null) ? 0 : this.FlatHPPoolMod.hashCode());
        result = (prime * result) + ((this.FlatHPRegenMod == null) ? 0 : this.FlatHPRegenMod.hashCode());
        result = (prime * result) + ((this.FlatMPPoolMod == null) ? 0 : this.FlatMPPoolMod.hashCode());
        result = (prime * result) + ((this.FlatMPRegenMod == null) ? 0 : this.FlatMPRegenMod.hashCode());
        result = (prime * result) + ((this.FlatMagicDamageMod == null) ? 0 : this.FlatMagicDamageMod.hashCode());
        result = (prime * result) + ((this.FlatMovementSpeedMod == null) ? 0 : this.FlatMovementSpeedMod.hashCode());
        result = (prime * result) + ((this.FlatPhysicalDamageMod == null) ? 0 : this.FlatPhysicalDamageMod.hashCode());
        result = (prime * result) + ((this.FlatSpellBlockMod == null) ? 0 : this.FlatSpellBlockMod.hashCode());
        result = (prime * result) + ((this.PercentArmorMod == null) ? 0 : this.PercentArmorMod.hashCode());
        result = (prime * result) + ((this.PercentAttackSpeedMod == null) ? 0 : this.PercentAttackSpeedMod.hashCode());
        result = (prime * result) + ((this.PercentBlockMod == null) ? 0 : this.PercentBlockMod.hashCode());
        result = (prime * result) + ((this.PercentCritChanceMod == null) ? 0 : this.PercentCritChanceMod.hashCode());
        result = (prime * result) + ((this.PercentCritDamageMod == null) ? 0 : this.PercentCritDamageMod.hashCode());
        result = (prime * result) + ((this.PercentDodgeMod == null) ? 0 : this.PercentDodgeMod.hashCode());
        result = (prime * result) + ((this.PercentEXPBonus == null) ? 0 : this.PercentEXPBonus.hashCode());
        result = (prime * result) + ((this.PercentHPPoolMod == null) ? 0 : this.PercentHPPoolMod.hashCode());
        result = (prime * result) + ((this.PercentHPRegenMod == null) ? 0 : this.PercentHPRegenMod.hashCode());
        result = (prime * result) + ((this.PercentLifeStealMod == null) ? 0 : this.PercentLifeStealMod.hashCode());
        result = (prime * result) + ((this.PercentMPPoolMod == null) ? 0 : this.PercentMPPoolMod.hashCode());
        result = (prime * result) + ((this.PercentMPRegenMod == null) ? 0 : this.PercentMPRegenMod.hashCode());
        result = (prime * result) + ((this.PercentMagicDamageMod == null) ? 0 : this.PercentMagicDamageMod.hashCode());
        result = (prime * result) + ((this.PercentMovementSpeedMod == null) ? 0 : this.PercentMovementSpeedMod.hashCode());
        result = (prime * result) + ((this.PercentPhysicalDamageMod == null) ? 0 : this.PercentPhysicalDamageMod.hashCode());
        result = (prime * result) + ((this.PercentSpellBlockMod == null) ? 0 : this.PercentSpellBlockMod.hashCode());
        result = (prime * result) + ((this.PercentSpellVampMod == null) ? 0 : this.PercentSpellVampMod.hashCode());
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
        result = (prime * result) + ((this.rPercentArmorPenetrationModPerLevel == null) ? 0 : this.rPercentArmorPenetrationModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentAttackSpeedModPerLevel == null) ? 0 : this.rPercentAttackSpeedModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentCooldownMod == null) ? 0 : this.rPercentCooldownMod.hashCode());
        result = (prime * result) + ((this.rPercentCooldownModPerLevel == null) ? 0 : this.rPercentCooldownModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentMagicPenetrationMod == null) ? 0 : this.rPercentMagicPenetrationMod.hashCode());
        result = (prime * result) + ((this.rPercentMagicPenetrationModPerLevel == null) ? 0 : this.rPercentMagicPenetrationModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentMovementSpeedModPerLevel == null) ? 0 : this.rPercentMovementSpeedModPerLevel.hashCode());
        result = (prime * result) + ((this.rPercentTimeDeadMod == null) ? 0 : this.rPercentTimeDeadMod.hashCode());
        result = (prime * result) + ((this.rPercentTimeDeadModPerLevel == null) ? 0 : this.rPercentTimeDeadModPerLevel.hashCode());
        return result;
    }

}
