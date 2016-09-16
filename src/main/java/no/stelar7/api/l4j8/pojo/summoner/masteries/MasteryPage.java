package no.stelar7.api.l4j8.pojo.summoner.masteries;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class MasteryPage
{

    private Boolean       current;
    private Long          id;
    private List<Mastery> masteries;

    private String name;

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
        final MasteryPage other = (MasteryPage) obj;
        if (this.current == null)
        {
            if (other.current != null)
            {
                return false;
            }
        } else if (!this.current.equals(other.current))
        {
            return false;
        }
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        if (this.masteries == null)
        {
            if (other.masteries != null)
            {
                return false;
            }
        } else if (!this.masteries.equals(other.masteries))
        {
            return false;
        }
        if (this.name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        } else if (!this.name.equals(other.name))
        {
            return false;
        }
        return true;
    }

    /**
     * The mastery page ID
     *
     * @return Long
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * Collection of masteries associated with the mastery page, if there are no masteries, it returns an empty list
     *
     * @return {@code List<Mastery>}
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

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.current == null) ? 0 : this.current.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.masteries == null) ? 0 : this.masteries.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    /**
     * Indicates if the mastery page is the current mastery page
     *
     * @return Boolean
     */
    public Boolean isCurrent()
    {
        return this.current;
    }

    @Override
    public String toString()
    {
        return "MasteryPage [current=" + this.current + ", id=" + this.id + ", masteries=" + this.masteries + ", name=" + this.name + "]";
    }

}
