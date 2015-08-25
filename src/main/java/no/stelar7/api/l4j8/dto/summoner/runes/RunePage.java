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

    public boolean isCurrent()
    {
        return current;
    }

    public long getId()
    {
        return id;
    }

    public List<Rune> getSlots()
    {
        return Collections.unmodifiableList(slots);
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "RunePage [current=" + current + ", id=" + id + ", slots=" + slots + ", name=" + name + "]";
    }
}
