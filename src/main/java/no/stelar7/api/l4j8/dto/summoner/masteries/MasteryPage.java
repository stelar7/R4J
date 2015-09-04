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

    public long getId()
    {
        return this.id;
    }

    public List<Mastery> getMasteries()
    {
        return Collections.unmodifiableList(this.masteries);
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isCurrent()
    {
        return this.current;
    }

    @Override
    public String toString()
    {
        return "MasteryPage [current=" + this.current + ", id=" + this.id + ", masteries=" + this.masteries + ", name=" + this.name + "]";
    }

}
