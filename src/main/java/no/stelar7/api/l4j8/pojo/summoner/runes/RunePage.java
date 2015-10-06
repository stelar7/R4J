package no.stelar7.api.l4j8.pojo.summoner.runes;

import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class RunePage extends APIObject
{

    private Boolean        current;
    private Long           id;
    private List<RuneSlot> slots;
    private String         name;

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
     * Collection of rune slots associated with the rune page.
     *
     * @return {@code List<RuneSlot>}
     */
    public List<RuneSlot> getSlots()
    {
        return Collections.unmodifiableList(this.slots);
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
