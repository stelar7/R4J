package no.stelar7.api.l4j8.dto.summoner.masteries;

import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class MasteryPage extends APIObject
{
    private Boolean       current;
    private Long          id;
    private List<Mastery> masteries;
    private String        name;

    public Long getId()
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
