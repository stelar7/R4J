package no.stelar7.api.l4j8.pojo.champion;

import java.io.Serializable;
import java.util.List;

public class ChampionList implements Serializable
{
    private static final long serialVersionUID = -8590769549392641157L;
    
    private List<Champion> champions;
    
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
        final ChampionList other = (ChampionList) obj;
        if (this.champions == null)
        {
            if (other.champions != null)
            {
                return false;
            }
        } else if (!this.champions.equals(other.champions))
        {
            return false;
        }
        return true;
    }
    
    /**
     * The collection of champion information.
     *
     * @return the champions
     */
    public List<Champion> getChampions()
    {
        return this.champions;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.champions == null) ? 0 : this.champions.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ChampionList{" +
               "champions=" + champions +
               '}';
    }
}
