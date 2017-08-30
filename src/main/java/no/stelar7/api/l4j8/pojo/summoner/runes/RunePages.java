package no.stelar7.api.l4j8.pojo.summoner.runes;

import java.io.Serializable;
import java.util.List;

public class RunePages  implements Serializable
{
    private List<RunePage> pages;
    private long           summonerId;
    
    
    /**
     * Collection of rune pages associated with the summoner.
     *
     * @return the pages
     */
    public List<RunePage> getPages()
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
        
        RunePages runePages = (RunePages) o;
        
        if (summonerId != runePages.summonerId)
        {
            return false;
        }
        return (pages != null) ? pages.equals(runePages.pages) : (runePages.pages == null);
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
        return "RunePages{" +
               "pages=" + pages +
               ", summonerId=" + summonerId +
               '}';
    }
}
