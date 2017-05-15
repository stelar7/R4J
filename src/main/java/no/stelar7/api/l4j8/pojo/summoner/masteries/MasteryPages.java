package no.stelar7.api.l4j8.pojo.summoner.masteries;

import java.util.*;

public class MasteryPages
{
    private List<MasteryPage> pages;
    private long              summonerId;
    
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
    public long getSummonerId()
    {
        return this.summonerId;
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
        
        MasteryPages that = (MasteryPages) o;
        
        if (summonerId != that.summonerId)
        {
            return false;
        }
        return (pages != null) ? pages.equals(that.pages) : (that.pages == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = pages != null ? pages.hashCode() : 0;
        result = 31 * result + (int) (summonerId ^ (summonerId >>> 32));
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MasteryPages [pages=" + this.pages + ", summonerId=" + this.summonerId + "]";
    }
    
}
