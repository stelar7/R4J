package no.stelar7.api.l4j8.pojo.staticdata.summonerspell;

import no.stelar7.api.l4j8.pojo.shared.BaseSpellData;

import java.util.List;

public class StaticSummonerSpell extends BaseSpellData
{
    private static final long serialVersionUID = 6564283228389342174L;
    
    private int          id;
    private List<String> modes;
    private int          summonerLevel;
    
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId()
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
    public int getSummonerLevel()
    {
        return this.summonerLevel;
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
        
        StaticSummonerSpell that = (StaticSummonerSpell) o;
        
        if (id != that.id)
        {
            return false;
        }
        if (summonerLevel != that.summonerLevel)
        {
            return false;
        }
        return (modes != null) ? modes.equals(that.modes) : (that.modes == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (modes != null ? modes.hashCode() : 0);
        result = 31 * result + summonerLevel;
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
