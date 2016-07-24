package no.stelar7.api.l4j8.pojo.summoner.runes;

import java.util.*;

public class RunePage
{

    private Boolean        current;
    private Long           id;
    private List<RuneSlot> slots;

    private String         name;

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
        final RunePage other = (RunePage) obj;
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
        if (this.slots == null)
        {
            if (other.slots != null)
            {
                return false;
            }
        } else if (!this.slots.equals(other.slots))
        {
            return false;
        }
        return true;
    }

    /**
     * The rune page ID
     *
     * @return Long
     */
    public Long getId()
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.current == null) ? 0 : this.current.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.slots == null) ? 0 : this.slots.hashCode());
        return result;
    }

    /**
     * Indicates if the page is the current page.
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
        return "RunePage [current=" + this.current + ", id=" + this.id + ", slots=" + this.slots + ", name=" + this.name + "]";
    }
}
