package no.stelar7.api.l4j8.pojo.staticdata.summonerspell;

import java.util.*;

public class SummonerSpellList
{
    Map<String, SummonerSpell> data;

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
        final SummonerSpellList other = (SummonerSpellList) obj;
        if (this.data == null)
        {
            if (other.data != null)
            {
                return false;
            }
        } else if (!this.data.equals(other.data))
        {
            return false;
        }

        return true;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, SummonerSpell> getData()
    {
        return this.data;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.data == null) ? 0 : this.data.hashCode());
        result = (prime * result) + super.hashCode();
        return result;
    }
}
