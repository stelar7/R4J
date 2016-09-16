package no.stelar7.api.l4j8.pojo.summoner.masteries;

import java.util.List;

public class MasteryPages
{
    private List<MasteryPage> pages;
    private Long summonerId;

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
        final MasteryPages other = (MasteryPages) obj;
        if (this.pages == null)
        {
            if (other.pages != null)
            {
                return false;
            }
        } else if (!this.pages.equals(other.pages))
        {
            return false;
        }
        if (this.summonerId == null)
        {
            if (other.summonerId != null)
            {
                return false;
            }
        } else if (!this.summonerId.equals(other.summonerId))
        {
            return false;
        }
        return true;
    }

    /**
     * Collection of mastery pages associated with the summoner.
     *
     * @return the pages
     */
    public List<MasteryPage> getPages()
    {
        return this.pages;
    }

    /**
     * Gets the summoner id.
     *
     * @return the summoner id
     */
    public Long getSummonerId()
    {
        return this.summonerId;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.pages == null) ? 0 : this.pages.hashCode());
        result = (prime * result) + ((this.summonerId == null) ? 0 : this.summonerId.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "MasteryPages [pages=" + this.pages + ", summonerId=" + this.summonerId + "]";
    }

}
