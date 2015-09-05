package no.stelar7.api.l4j8.dto.summoner.runes;

import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class RunePage extends APIObject
{

    private Boolean    current;
    private Long       id;
    private List<Rune> slots;
    private String     name;

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public List<Rune> getSlots()
    {
        return Collections.unmodifiableList(this.slots);
    }

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
