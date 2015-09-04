package no.stelar7.api.l4j8.dto.summoner.masteries;

import no.stelar7.api.l4j8.basic.APIObject;

public class Mastery extends APIObject
{
    private int id;
    private int rank;

    public int getId()
    {
        return this.id;
    }

    public int getRank()
    {
        return this.rank;
    }

    @Override
    public String toString()
    {
        return "Mastery [id=" + this.id + ", rank=" + this.rank + "]";
    }
}
