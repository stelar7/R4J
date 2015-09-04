package no.stelar7.api.l4j8.dto.summoner.runes;

import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class RunePage extends APIObject
{

    private boolean    current;
    private long       id;
    private List<Rune> slots;
    private String     name;

    public long getId()
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

    public boolean isCurrent()
    {
        return this.current;
    }

    @Override
    public String toString()
    {
        return "RunePage [current=" + this.current + ", id=" + this.id + ", slots=" + this.slots + ", name=" + this.name + "]";
    }
}
