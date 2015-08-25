package no.stelar7.api.l4j8.dto.summoner.masteries;

import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class MasteryPage extends APIObject
{
    private boolean       current;
    private long          id;
    private List<Mastery> masteries;
    private String        name;

    public boolean isCurrent()
    {
        return current;
    }

    public long getId()
    {
        return id;
    }

    public List<Mastery> getMasteries()
    {
        return Collections.unmodifiableList(masteries);
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "MasteryPage [current=" + current + ", id=" + id + ", masteries=" + masteries + ", name=" + name + "]";
    }

}
