package no.stelar7.api.l4j8.pojo.summoner.masteries;

import java.util.List;

public class MasteryPages
{
    List<MasteryPage> pages;
    Long              summonerId;

    /**
     * Collection of mastery pages associated with the summoner.
     *
     * @return the pages
     */
    public List<MasteryPage> getPages()
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
    public String toString()
    {
        return "MasteryPages [pages=" + pages + ", summonerId=" + summonerId + "]";
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
        MasteryPages other = (MasteryPages) obj;
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
