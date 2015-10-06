package no.stelar7.api.l4j8.pojo.summoner.masteries;

import no.stelar7.api.l4j8.basic.APIObject;

public class Mastery extends APIObject
{
    private Integer id;
    private Integer rank;

    /**
     * The mastery ID. For static information correlating to masteries, please refer to the LoL Static Data API.
     *
     * @return Integer
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * The mastery rank (i.e., the number of points put into this mastery).
     *
     * @return Integer
     */
    public Integer getRank()
    {
        return this.rank;
    }

    @Override
    public String toString()
    {
        return "Mastery [id=" + this.id + ", rank=" + this.rank + "]";
    }
}
