package no.stelar7.api.l4j8.pojo.summoner.runes;

import java.util.List;

public class RunePages
{
    List<RunePage> pages;
    Long           summonerId;

    /**
     * Collection of rune pages associated with the summoner.
     *
     * @return the pages
     */
    public List<RunePage> getPages()
    {
        return pages;
    }

    /**
     * Gets the summoner id.
     *
     * @return the summoner id
     */
    public Long getSummonerId()
    {
        return summonerId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pages == null) ? 0 : pages.hashCode());
        result = prime * result + ((summonerId == null) ? 0 : summonerId.hashCode());
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
        RunePages other = (RunePages) obj;
        if (pages == null)
        {
            if (other.pages != null)
                return false;
        } else if (!pages.equals(other.pages))
            return false;
        if (summonerId == null)
        {
            if (other.summonerId != null)
                return false;
        } else if (!summonerId.equals(other.summonerId))
            return false;
        return true;
    }
}
