package no.stelar7.api.l4j8.pojo.champion;

import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class ChampionList implements APIObject
{
    List<Champion> champions;

    /**
     * The collection of champion information.
     *
     * @return the champions
     */
    public List<Champion> getChampions()
    {
        return champions;
    }

    @Override
    public String toString()
    {
        return "ChampionList [champions=" + champions + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((champions == null) ? 0 : champions.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChampionList other = (ChampionList) obj;
        if (champions == null)
        {
            if (other.champions != null)
                return false;
        } else if (!champions.equals(other.champions))
            return false;
        return true;
    }
}
