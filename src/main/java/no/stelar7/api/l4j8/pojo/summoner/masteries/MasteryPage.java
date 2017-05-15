package no.stelar7.api.l4j8.pojo.summoner.masteries;

import java.util.*;

public class MasteryPage
{
    
    private boolean       current;
    private long          id;
    private List<Mastery> masteries;
    private String        name;
    
    /**
     * The mastery page ID
     *
     * @return long
     */
    public long getId()
    {
        return this.id;
    }
    
    /**
     * Collection of masteries associated with the mastery page, if there are no masteries, it returns an empty list
     *
     * @return {@code List<SpectatorMastery>}
     */
    public List<Mastery> getMasteries()
    {
        return (this.masteries == null) ? Collections.emptyList() : Collections.unmodifiableList(this.masteries);
    }
    
    /**
     * The mastery page name
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }
    
    
    /**
     * Indicates if the mastery page is the current mastery page
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
        
        MasteryPage that = (MasteryPage) o;
        
        if (current != that.current)
        {
            return false;
        }
        if (id != that.id)
        {
            return false;
        }
        if ((masteries != null) ? !masteries.equals(that.masteries) : (that.masteries != null))
        {
            return false;
        }
        return (name != null) ? name.equals(that.name) : (that.name == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = (current ? 1 : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (masteries != null ? masteries.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MasteryPage [current=" + this.current + ", id=" + this.id + ", masteries=" + this.masteries + ", name=" + this.name + "]";
    }
    
}
