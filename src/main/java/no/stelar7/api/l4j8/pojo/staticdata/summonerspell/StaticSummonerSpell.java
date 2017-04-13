package no.stelar7.api.l4j8.pojo.staticdata.summonerspell;

import no.stelar7.api.l4j8.pojo.shared.*;

import java.util.*;

public class StaticSummonerSpell extends BaseSpellData
{
    private Integer      id;
    private List<String> modes;
    private Integer      summonerLevel;
    
    
    
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
        final StaticSummonerSpell other = (StaticSummonerSpell) obj;
        
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        
        if (this.modes == null)
        {
            if (other.modes != null)
            {
                return false;
            }
        } else if (!this.modes.equals(other.modes))
        {
            return false;
        }
        
        if (this.summonerLevel == null)
        {
            if (other.summonerLevel != null)
            {
                return false;
            }
        } else if (!this.summonerLevel.equals(other.summonerLevel))
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return this.id;
    }
    
    /**
     * Gets the modes.
     *
     * @return the modes
     */
    public List<String> getModes()
    {
        return this.modes;
    }
    
    /**
     * Gets the summoner level.
     *
     * @return the summoner level
     */
    public Integer getSummonerLevel()
    {
        return this.summonerLevel;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.modes == null) ? 0 : this.modes.hashCode());
        result = (prime * result) + ((this.summonerLevel == null) ? 0 : this.summonerLevel.hashCode());
        result = (prime * result) + super.hashCode();
        return result;
    }
    
    @Override
    public String toString()
    {
        return "StaticSummonerSpell{" +
               "id=" + id +
               ", modes=" + modes +
               ", summonerLevel=" + summonerLevel +
               '}';
    }
}
