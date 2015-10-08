package no.stelar7.api.l4j8.pojo.currentgame;

import no.stelar7.api.l4j8.basic.APIObject;

public class Rune implements APIObject
{
    private Integer count;
    private Long    runeId;

    /**
     * The count of this rune used by the participant
     *
     * @return Integer
     */
    public Integer getCount()
    {
        return this.count;
    }

    /**
     * The ID of the rune
     *
     * @return Long
     */
    public Long getRuneId()
    {
        return this.runeId;
    }

    @Override
    public String toString()
    {
        return "Rune [count=" + this.count + ", runeId=" + this.runeId + "]";
    }

}
