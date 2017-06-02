package no.stelar7.api.l4j8.pojo.staticdata.champion;

public class Stats
{
    private float armor;
    private float armorperlevel;
    private float attackdamage;
    private float attackdamageperlevel;
    private float attackrange;
    private float attackspeedoffset;
    private float attackspeedperlevel;
    private float crit;
    private float critperlevel;
    private float hp;
    private float hpperlevel;
    private float hpregen;
    private float hpregenperlevel;
    private float movespeed;
    private float mp;
    private float mpperlevel;
    private float mpregen;
    private float mpregenperlevel;
    private float spellblock;
    private float spellblockperlevel;
    
    
    /**
     * Gets the armor.
     *
     * @return the armor
     */
    public float getArmor()
    {
        return this.armor;
    }
    
    /**
     * Gets the armorperlevel.
     *
     * @return the armorperlevel
     */
    public float getArmorperlevel()
    {
        return this.armorperlevel;
    }
    
    /**
     * Gets the attackdamage.
     *
     * @return the attackdamage
     */
    public float getAttackdamage()
    {
        return this.attackdamage;
    }
    
    /**
     * Gets the attackdamageperlevel.
     *
     * @return the attackdamageperlevel
     */
    public float getAttackdamageperlevel()
    {
        return this.attackdamageperlevel;
    }
    
    /**
     * Gets the attackrange.
     *
     * @return the attackrange
     */
    public float getAttackrange()
    {
        return this.attackrange;
    }
    
    /**
     * Gets the attackspeedoffset.
     *
     * @return the attackspeedoffset
     */
    public float getAttackspeedoffset()
    {
        return this.attackspeedoffset;
    }
    
    /**
     * Gets the attackspeedperlevel.
     *
     * @return the attackspeedperlevel
     */
    public float getAttackspeedperlevel()
    {
        return this.attackspeedperlevel;
    }
    
    /**
     * Gets the crit.
     *
     * @return the crit
     */
    public float getCrit()
    {
        return this.crit;
    }
    
    /**
     * Gets the critperlevel.
     *
     * @return the critperlevel
     */
    public float getCritperlevel()
    {
        return this.critperlevel;
    }
    
    /**
     * Gets the hp.
     *
     * @return the hp
     */
    public float getHp()
    {
        return this.hp;
    }
    
    /**
     * Gets the hpperlevel.
     *
     * @return the hpperlevel
     */
    public float getHpperlevel()
    {
        return this.hpperlevel;
    }
    
    /**
     * Gets the hpregen.
     *
     * @return the hpregen
     */
    public float getHpregen()
    {
        return this.hpregen;
    }
    
    /**
     * Gets the hpregenperlevel.
     *
     * @return the hpregenperlevel
     */
    public float getHpregenperlevel()
    {
        return this.hpregenperlevel;
    }
    
    /**
     * Gets the movespeed.
     *
     * @return the movespeed
     */
    public float getMovespeed()
    {
        return this.movespeed;
    }
    
    /**
     * Gets the mp.
     *
     * @return the mp
     */
    public float getMp()
    {
        return this.mp;
    }
    
    /**
     * Gets the mpperlevel.
     *
     * @return the mpperlevel
     */
    public float getMpperlevel()
    {
        return this.mpperlevel;
    }
    
    /**
     * Gets the mpregen.
     *
     * @return the mpregen
     */
    public float getMpregen()
    {
        return this.mpregen;
    }
    
    /**
     * Gets the mpregenperlevel.
     *
     * @return the mpregenperlevel
     */
    public float getMpregenperlevel()
    {
        return this.mpregenperlevel;
    }
    
    /**
     * Gets the spellblock.
     *
     * @return the spellblock
     */
    public float getSpellblock()
    {
        return this.spellblock;
    }
    
    /**
     * Gets the spellblockperlevel.
     *
     * @return the spellblockperlevel
     */
    public float getSpellblockperlevel()
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
        
        if (Float.compare(stats.armor, armor) != 0)
        {
            return false;
        }
        if (Float.compare(stats.armorperlevel, armorperlevel) != 0)
        {
            return false;
        }
        if (Float.compare(stats.attackdamage, attackdamage) != 0)
        {
            return false;
        }
        if (Float.compare(stats.attackdamageperlevel, attackdamageperlevel) != 0)
        {
            return false;
        }
        if (Float.compare(stats.attackrange, attackrange) != 0)
        {
            return false;
        }
        if (Float.compare(stats.attackspeedoffset, attackspeedoffset) != 0)
        {
            return false;
        }
        if (Float.compare(stats.attackspeedperlevel, attackspeedperlevel) != 0)
        {
            return false;
        }
        if (Float.compare(stats.crit, crit) != 0)
        {
            return false;
        }
        if (Float.compare(stats.critperlevel, critperlevel) != 0)
        {
            return false;
        }
        if (Float.compare(stats.hp, hp) != 0)
        {
            return false;
        }
        if (Float.compare(stats.hpperlevel, hpperlevel) != 0)
        {
            return false;
        }
        if (Float.compare(stats.hpregen, hpregen) != 0)
        {
            return false;
        }
        if (Float.compare(stats.hpregenperlevel, hpregenperlevel) != 0)
        {
            return false;
        }
        if (Float.compare(stats.movespeed, movespeed) != 0)
        {
            return false;
        }
        if (Float.compare(stats.mp, mp) != 0)
        {
            return false;
        }
        if (Float.compare(stats.mpperlevel, mpperlevel) != 0)
        {
            return false;
        }
        if (Float.compare(stats.mpregen, mpregen) != 0)
        {
            return false;
        }
        if (Float.compare(stats.mpregenperlevel, mpregenperlevel) != 0)
        {
            return false;
        }
        if (Float.compare(stats.spellblock, spellblock) != 0)
        {
            return false;
        }
        return Float.compare(stats.spellblockperlevel, spellblockperlevel) == 0;
    }
    
    @Override
    public int hashCode()
    {
        int result = armor != +0.0f ? Float.floatToIntBits(armor) : 0;
        result = 31 * result + (armorperlevel != +0.0f ? Float.floatToIntBits(armorperlevel) : 0);
        result = 31 * result + (attackdamage != +0.0f ? Float.floatToIntBits(attackdamage) : 0);
        result = 31 * result + (attackdamageperlevel != +0.0f ? Float.floatToIntBits(attackdamageperlevel) : 0);
        result = 31 * result + (attackrange != +0.0f ? Float.floatToIntBits(attackrange) : 0);
        result = 31 * result + (attackspeedoffset != +0.0f ? Float.floatToIntBits(attackspeedoffset) : 0);
        result = 31 * result + (attackspeedperlevel != +0.0f ? Float.floatToIntBits(attackspeedperlevel) : 0);
        result = 31 * result + (crit != +0.0f ? Float.floatToIntBits(crit) : 0);
        result = 31 * result + (critperlevel != +0.0f ? Float.floatToIntBits(critperlevel) : 0);
        result = 31 * result + (hp != +0.0f ? Float.floatToIntBits(hp) : 0);
        result = 31 * result + (hpperlevel != +0.0f ? Float.floatToIntBits(hpperlevel) : 0);
        result = 31 * result + (hpregen != +0.0f ? Float.floatToIntBits(hpregen) : 0);
        result = 31 * result + (hpregenperlevel != +0.0f ? Float.floatToIntBits(hpregenperlevel) : 0);
        result = 31 * result + (movespeed != +0.0f ? Float.floatToIntBits(movespeed) : 0);
        result = 31 * result + (mp != +0.0f ? Float.floatToIntBits(mp) : 0);
        result = 31 * result + (mpperlevel != +0.0f ? Float.floatToIntBits(mpperlevel) : 0);
        result = 31 * result + (mpregen != +0.0f ? Float.floatToIntBits(mpregen) : 0);
        result = 31 * result + (mpregenperlevel != +0.0f ? Float.floatToIntBits(mpregenperlevel) : 0);
        result = 31 * result + (spellblock != +0.0f ? Float.floatToIntBits(spellblock) : 0);
        result = 31 * result + (spellblockperlevel != +0.0f ? Float.floatToIntBits(spellblockperlevel) : 0);
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
