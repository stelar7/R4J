package no.stelar7.api.l4j8.pojo.currentgame;

import no.stelar7.api.l4j8.basic.APIObject;

public class Mastery implements APIObject
{
    private Long    masteryId;
    private Integer rank;

    /**
     * The ID of the mastery
     *
     * @return Long
     */
    public Long getMasteryId()
    {
        return this.masteryId;
    }

    /**
     * The number of points put into this mastery by the user
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
        return "Mastery [masteryId=" + this.masteryId + ", rank=" + this.rank + "]";
    }
}
