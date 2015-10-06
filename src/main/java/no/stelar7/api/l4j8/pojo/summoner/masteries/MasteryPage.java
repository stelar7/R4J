package no.stelar7.api.l4j8.pojo.summoner.masteries;

import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class MasteryPage extends APIObject
{
    private Boolean       current;
    private Long          id;
    private List<Mastery> masteries;
    private String        name;

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
     * Collection of masteries associated with the mastery page
     *
     * @return {@code List<Mastery>}
     */
    public List<Mastery> getMasteries()
    {
        return Collections.unmodifiableList(this.masteries);
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
