package no.stelar7.api.l4j8.pojo.summoner.runes;

import java.util.*;

public class RunePage
{
    
    private boolean        current;
    private long           id;
    private List<RuneSlot> slots;
    private String         name;
    
    
    /**
     * The rune page ID
     *
     * @return long
     */
    public long getId()
    {
        return this.id;
    }
    
    /**
     * The rune page name
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Collection of rune slots associated with the rune page, if there are no pages, it returns an empty list
     *
     * @return {@code List<RuneSlot>}
     */
    public List<RuneSlot> getSlots()
    {
        return (this.slots == null) ? Collections.emptyList() : Collections.unmodifiableList(this.slots);
    }
    
    
    /**
     * Indicates if the page is the current page.
     *
     * @return boolean
     */
    public boolean isCurrent()
    {
        return this.current;
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
        
        RunePage runePage = (RunePage) o;
        
        if (current != runePage.current)
        {
            return false;
        }
        if (id != runePage.id)
        {
            return false;
        }
        if ((slots != null) ? !slots.equals(runePage.slots) : (runePage.slots != null))
        {
            return false;
        }
        return (name != null) ? name.equals(runePage.name) : (runePage.name == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = current ? 1 : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (slots != null ? slots.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "RunePage [current=" + this.current + ", id=" + this.id + ", slots=" + this.slots + ", name=" + this.name + "]";
    }
}
