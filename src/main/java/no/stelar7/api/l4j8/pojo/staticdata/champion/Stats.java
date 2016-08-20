package no.stelar7.api.l4j8.pojo.staticdata.champion;

public class Stats
{
    private Double armor;
    private Double armorperlevel;
    private Double attackdamage;
    private Double attackdamageperlevel;
    private Double attackrange;
    private Double attackspeedoffset;
    private Double attackspeedperlevel;
    private Double crit;
    private Double critperlevel;
    private Double hp;
    private Double hpperlevel;
    private Double hpregen;
    private Double hpregenperlevel;
    private Double movespeed;
    private Double mp;
    private Double mpperlevel;
    private Double mpregen;
    private Double mpregenperlevel;
    private Double spellblock;
    private Double spellblockperlevel;

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
        final Stats other = (Stats) obj;
        if (this.armor == null)
        {
            if (other.armor != null)
            {
                return false;
            }
        } else if (!this.armor.equals(other.armor))
        {
            return false;
        }
        if (this.armorperlevel == null)
        {
            if (other.armorperlevel != null)
            {
                return false;
            }
        } else if (!this.armorperlevel.equals(other.armorperlevel))
        {
            return false;
        }
        if (this.attackdamage == null)
        {
            if (other.attackdamage != null)
            {
                return false;
            }
        } else if (!this.attackdamage.equals(other.attackdamage))
        {
            return false;
        }
        if (this.attackdamageperlevel == null)
        {
            if (other.attackdamageperlevel != null)
            {
                return false;
            }
        } else if (!this.attackdamageperlevel.equals(other.attackdamageperlevel))
        {
            return false;
        }
        if (this.attackrange == null)
        {
            if (other.attackrange != null)
            {
                return false;
            }
        } else if (!this.attackrange.equals(other.attackrange))
        {
            return false;
        }
        if (this.attackspeedoffset == null)
        {
            if (other.attackspeedoffset != null)
            {
                return false;
            }
        } else if (!this.attackspeedoffset.equals(other.attackspeedoffset))
        {
            return false;
        }
        if (this.attackspeedperlevel == null)
        {
            if (other.attackspeedperlevel != null)
            {
                return false;
            }
        } else if (!this.attackspeedperlevel.equals(other.attackspeedperlevel))
        {
            return false;
        }
        if (this.crit == null)
        {
            if (other.crit != null)
            {
                return false;
            }
        } else if (!this.crit.equals(other.crit))
        {
            return false;
        }
        if (this.critperlevel == null)
        {
            if (other.critperlevel != null)
            {
                return false;
            }
        } else if (!this.critperlevel.equals(other.critperlevel))
        {
            return false;
        }
        if (this.hp == null)
        {
            if (other.hp != null)
            {
                return false;
            }
        } else if (!this.hp.equals(other.hp))
        {
            return false;
        }
        if (this.hpperlevel == null)
        {
            if (other.hpperlevel != null)
            {
                return false;
            }
        } else if (!this.hpperlevel.equals(other.hpperlevel))
        {
            return false;
        }
        if (this.hpregen == null)
        {
            if (other.hpregen != null)
            {
                return false;
            }
        } else if (!this.hpregen.equals(other.hpregen))
        {
            return false;
        }
        if (this.hpregenperlevel == null)
        {
            if (other.hpregenperlevel != null)
            {
                return false;
            }
        } else if (!this.hpregenperlevel.equals(other.hpregenperlevel))
        {
            return false;
        }
        if (this.movespeed == null)
        {
            if (other.movespeed != null)
            {
                return false;
            }
        } else if (!this.movespeed.equals(other.movespeed))
        {
            return false;
        }
        if (this.mp == null)
        {
            if (other.mp != null)
            {
                return false;
            }
        } else if (!this.mp.equals(other.mp))
        {
            return false;
        }
        if (this.mpperlevel == null)
        {
            if (other.mpperlevel != null)
            {
                return false;
            }
        } else if (!this.mpperlevel.equals(other.mpperlevel))
        {
            return false;
        }
        if (this.mpregen == null)
        {
            if (other.mpregen != null)
            {
                return false;
            }
        } else if (!this.mpregen.equals(other.mpregen))
        {
            return false;
        }
        if (this.mpregenperlevel == null)
        {
            if (other.mpregenperlevel != null)
            {
                return false;
            }
        } else if (!this.mpregenperlevel.equals(other.mpregenperlevel))
        {
            return false;
        }
        if (this.spellblock == null)
        {
            if (other.spellblock != null)
            {
                return false;
            }
        } else if (!this.spellblock.equals(other.spellblock))
        {
            return false;
        }
        if (this.spellblockperlevel == null)
        {
            if (other.spellblockperlevel != null)
            {
                return false;
            }
        } else if (!this.spellblockperlevel.equals(other.spellblockperlevel))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the armor.
     *
     * @return the armor
     */
    public Double getArmor()
    {
        return this.armor;
    }

    /**
     * Gets the armorperlevel.
     *
     * @return the armorperlevel
     */
    public Double getArmorperlevel()
    {
        return this.armorperlevel;
    }

    /**
     * Gets the attackdamage.
     *
     * @return the attackdamage
     */
    public Double getAttackdamage()
    {
        return this.attackdamage;
    }

    /**
     * Gets the attackdamageperlevel.
     *
     * @return the attackdamageperlevel
     */
    public Double getAttackdamageperlevel()
    {
        return this.attackdamageperlevel;
    }

    /**
     * Gets the attackrange.
     *
     * @return the attackrange
     */
    public Double getAttackrange()
    {
        return this.attackrange;
    }

    /**
     * Gets the attackspeedoffset.
     *
     * @return the attackspeedoffset
     */
    public Double getAttackspeedoffset()
    {
        return this.attackspeedoffset;
    }

    /**
     * Gets the attackspeedperlevel.
     *
     * @return the attackspeedperlevel
     */
    public Double getAttackspeedperlevel()
    {
        return this.attackspeedperlevel;
    }

    /**
     * Gets the crit.
     *
     * @return the crit
     */
    public Double getCrit()
    {
        return this.crit;
    }

    /**
     * Gets the critperlevel.
     *
     * @return the critperlevel
     */
    public Double getCritperlevel()
    {
        return this.critperlevel;
    }

    /**
     * Gets the hp.
     *
     * @return the hp
     */
    public Double getHp()
    {
        return this.hp;
    }

    /**
     * Gets the hpperlevel.
     *
     * @return the hpperlevel
     */
    public Double getHpperlevel()
    {
        return this.hpperlevel;
    }

    /**
     * Gets the hpregen.
     *
     * @return the hpregen
     */
    public Double getHpregen()
    {
        return this.hpregen;
    }

    /**
     * Gets the hpregenperlevel.
     *
     * @return the hpregenperlevel
     */
    public Double getHpregenperlevel()
    {
        return this.hpregenperlevel;
    }

    /**
     * Gets the movespeed.
     *
     * @return the movespeed
     */
    public Double getMovespeed()
    {
        return this.movespeed;
    }

    /**
     * Gets the mp.
     *
     * @return the mp
     */
    public Double getMp()
    {
        return this.mp;
    }

    /**
     * Gets the mpperlevel.
     *
     * @return the mpperlevel
     */
    public Double getMpperlevel()
    {
        return this.mpperlevel;
    }

    /**
     * Gets the mpregen.
     *
     * @return the mpregen
     */
    public Double getMpregen()
    {
        return this.mpregen;
    }

    /**
     * Gets the mpregenperlevel.
     *
     * @return the mpregenperlevel
     */
    public Double getMpregenperlevel()
    {
        return this.mpregenperlevel;
    }

    /**
     * Gets the spellblock.
     *
     * @return the spellblock
     */
    public Double getSpellblock()
    {
        return this.spellblock;
    }

    /**
     * Gets the spellblockperlevel.
     *
     * @return the spellblockperlevel
     */
    public Double getSpellblockperlevel()
    {
        return this.spellblockperlevel;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.armor == null) ? 0 : this.armor.hashCode());
        result = (prime * result) + ((this.armorperlevel == null) ? 0 : this.armorperlevel.hashCode());
        result = (prime * result) + ((this.attackdamage == null) ? 0 : this.attackdamage.hashCode());
        result = (prime * result) + ((this.attackdamageperlevel == null) ? 0 : this.attackdamageperlevel.hashCode());
        result = (prime * result) + ((this.attackrange == null) ? 0 : this.attackrange.hashCode());
        result = (prime * result) + ((this.attackspeedoffset == null) ? 0 : this.attackspeedoffset.hashCode());
        result = (prime * result) + ((this.attackspeedperlevel == null) ? 0 : this.attackspeedperlevel.hashCode());
        result = (prime * result) + ((this.crit == null) ? 0 : this.crit.hashCode());
        result = (prime * result) + ((this.critperlevel == null) ? 0 : this.critperlevel.hashCode());
        result = (prime * result) + ((this.hp == null) ? 0 : this.hp.hashCode());
        result = (prime * result) + ((this.hpperlevel == null) ? 0 : this.hpperlevel.hashCode());
        result = (prime * result) + ((this.hpregen == null) ? 0 : this.hpregen.hashCode());
        result = (prime * result) + ((this.hpregenperlevel == null) ? 0 : this.hpregenperlevel.hashCode());
        result = (prime * result) + ((this.movespeed == null) ? 0 : this.movespeed.hashCode());
        result = (prime * result) + ((this.mp == null) ? 0 : this.mp.hashCode());
        result = (prime * result) + ((this.mpperlevel == null) ? 0 : this.mpperlevel.hashCode());
        result = (prime * result) + ((this.mpregen == null) ? 0 : this.mpregen.hashCode());
        result = (prime * result) + ((this.mpregenperlevel == null) ? 0 : this.mpregenperlevel.hashCode());
        result = (prime * result) + ((this.spellblock == null) ? 0 : this.spellblock.hashCode());
        result = (prime * result) + ((this.spellblockperlevel == null) ? 0 : this.spellblockperlevel.hashCode());
        return result;
    }
}
