package no.stelar7.api.l4j8.dto.summoner.masteries;

import no.stelar7.api.l4j8.basic.APIObject;

public class Mastery extends APIObject
{
    private Integer id;
    private Integer rank;

    public Integer getId()
    {
        return this.id;
    }

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
