package no.stelar7.api.l4j8.pojo.staticdata.champion;

public class Stats
{
    private double armor;
    private double armorperlevel;
    private double attackdamage;
    private double attackdamageperlevel;
    private double attackrange;
    private double attackspeedoffset;
    private double attackspeedperlevel;
    private double crit;
    private double critperlevel;
    private double hp;
    private double hpperlevel;
    private double hpregen;
    private double hpregenperlevel;
    private double movespeed;
    private double mp;
    private double mpperlevel;
    private double mpregen;
    private double mpregenperlevel;
    private double spellblock;
    private double spellblockperlevel;
    
    
    /**
     * Gets the armor.
     *
     * @return the armor
     */
    public double getArmor()
    {
        return this.armor;
    }
    
    /**
     * Gets the armorperlevel.
     *
     * @return the armorperlevel
     */
    public double getArmorperlevel()
    {
        return this.armorperlevel;
    }
    
    /**
     * Gets the attackdamage.
     *
     * @return the attackdamage
     */
    public double getAttackdamage()
    {
        return this.attackdamage;
    }
    
    /**
     * Gets the attackdamageperlevel.
     *
     * @return the attackdamageperlevel
     */
    public double getAttackdamageperlevel()
    {
        return this.attackdamageperlevel;
    }
    
    /**
     * Gets the attackrange.
     *
     * @return the attackrange
     */
    public double getAttackrange()
    {
        return this.attackrange;
    }
    
    /**
     * Gets the attackspeedoffset.
     *
     * @return the attackspeedoffset
     */
    public double getAttackspeedoffset()
    {
        return this.attackspeedoffset;
    }
    
    /**
     * Gets the attackspeedperlevel.
     *
     * @return the attackspeedperlevel
     */
    public double getAttackspeedperlevel()
    {
        return this.attackspeedperlevel;
    }
    
    /**
     * Gets the crit.
     *
     * @return the crit
     */
    public double getCrit()
    {
        return this.crit;
    }
    
    /**
     * Gets the critperlevel.
     *
     * @return the critperlevel
     */
    public double getCritperlevel()
    {
        return this.critperlevel;
    }
    
    /**
     * Gets the hp.
     *
     * @return the hp
     */
    public double getHp()
    {
        return this.hp;
    }
    
    /**
     * Gets the hpperlevel.
     *
     * @return the hpperlevel
     */
    public double getHpperlevel()
    {
        return this.hpperlevel;
    }
    
    /**
     * Gets the hpregen.
     *
     * @return the hpregen
     */
    public double getHpregen()
    {
        return this.hpregen;
    }
    
    /**
     * Gets the hpregenperlevel.
     *
     * @return the hpregenperlevel
     */
    public double getHpregenperlevel()
    {
        return this.hpregenperlevel;
    }
    
    /**
     * Gets the movespeed.
     *
     * @return the movespeed
     */
    public double getMovespeed()
    {
        return this.movespeed;
    }
    
    /**
     * Gets the mp.
     *
     * @return the mp
     */
    public double getMp()
    {
        return this.mp;
    }
    
    /**
     * Gets the mpperlevel.
     *
     * @return the mpperlevel
     */
    public double getMpperlevel()
    {
        return this.mpperlevel;
    }
    
    /**
     * Gets the mpregen.
     *
     * @return the mpregen
     */
    public double getMpregen()
    {
        return this.mpregen;
    }
    
    /**
     * Gets the mpregenperlevel.
     *
     * @return the mpregenperlevel
     */
    public double getMpregenperlevel()
    {
        return this.mpregenperlevel;
    }
    
    /**
     * Gets the spellblock.
     *
     * @return the spellblock
     */
    public double getSpellblock()
    {
        return this.spellblock;
    }
    
    /**
     * Gets the spellblockperlevel.
     *
     * @return the spellblockperlevel
     */
    public double getSpellblockperlevel()
    {
        return this.spellblockperlevel;
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
        
        Stats stats = (Stats) o;
        
        if (Double.compare(stats.armor, armor) != 0)
        {
            return false;
        }
        if (Double.compare(stats.armorperlevel, armorperlevel) != 0)
        {
            return false;
        }
        if (Double.compare(stats.attackdamage, attackdamage) != 0)
        {
            return false;
        }
        if (Double.compare(stats.attackdamageperlevel, attackdamageperlevel) != 0)
        {
            return false;
        }
        if (Double.compare(stats.attackrange, attackrange) != 0)
        {
            return false;
        }
        if (Double.compare(stats.attackspeedoffset, attackspeedoffset) != 0)
        {
            return false;
        }
        if (Double.compare(stats.attackspeedperlevel, attackspeedperlevel) != 0)
        {
            return false;
        }
        if (Double.compare(stats.crit, crit) != 0)
        {
            return false;
        }
        if (Double.compare(stats.critperlevel, critperlevel) != 0)
        {
            return false;
        }
        if (Double.compare(stats.hp, hp) != 0)
        {
            return false;
        }
        if (Double.compare(stats.hpperlevel, hpperlevel) != 0)
        {
            return false;
        }
        if (Double.compare(stats.hpregen, hpregen) != 0)
        {
            return false;
        }
        if (Double.compare(stats.hpregenperlevel, hpregenperlevel) != 0)
        {
            return false;
        }
        if (Double.compare(stats.movespeed, movespeed) != 0)
        {
            return false;
        }
        if (Double.compare(stats.mp, mp) != 0)
        {
            return false;
        }
        if (Double.compare(stats.mpperlevel, mpperlevel) != 0)
        {
            return false;
        }
        if (Double.compare(stats.mpregen, mpregen) != 0)
        {
            return false;
        }
        if (Double.compare(stats.mpregenperlevel, mpregenperlevel) != 0)
        {
            return false;
        }
        if (Double.compare(stats.spellblock, spellblock) != 0)
        {
            return false;
        }
        return Double.compare(stats.spellblockperlevel, spellblockperlevel) == 0;
    }
    
    @Override
    public int hashCode()
    {
        int  result;
        long temp;
        temp = Double.doubleToLongBits(armor);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(armorperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(attackdamage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(attackdamageperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(attackrange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(attackspeedoffset);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(attackspeedperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(crit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(critperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hpperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hpregen);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hpregenperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(movespeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(mp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(mpperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(mpregen);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(mpregenperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(spellblock);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(spellblockperlevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Stats{" +
               "armor=" + armor +
               ", armorperlevel=" + armorperlevel +
               ", attackdamage=" + attackdamage +
               ", attackdamageperlevel=" + attackdamageperlevel +
               ", attackrange=" + attackrange +
               ", attackspeedoffset=" + attackspeedoffset +
               ", attackspeedperlevel=" + attackspeedperlevel +
               ", crit=" + crit +
               ", critperlevel=" + critperlevel +
               ", hp=" + hp +
               ", hpperlevel=" + hpperlevel +
               ", hpregen=" + hpregen +
               ", hpregenperlevel=" + hpregenperlevel +
               ", movespeed=" + movespeed +
               ", mp=" + mp +
               ", mpperlevel=" + mpperlevel +
               ", mpregen=" + mpregen +
               ", mpregenperlevel=" + mpregenperlevel +
               ", spellblock=" + spellblock +
               ", spellblockperlevel=" + spellblockperlevel +
               '}';
    }
}
