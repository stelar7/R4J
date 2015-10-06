package no.stelar7.api.l4j8.pojo.summoner.runes;

import no.stelar7.api.l4j8.basic.APIObject;

public class RuneSlot extends APIObject
{

    private Integer runeId;
    private Integer runeSlotId;

    /**
     * Rune ID associated with the rune slot. For static information correlating to rune IDs, please refer to the LoL Static Data API.
     * 
     * @return Integer
     */
    public Integer getRuneId()
    {
        return this.runeId;
    }

    /**
     * The Rune slot ID.
     * 
     * @return Integer
     */
    public Integer getRuneSlotId()
    {
        return this.runeSlotId;
    }

    @Override
    public String toString()
    {
        return "Rune [runeId=" + this.runeId + ", runeSlotId=" + this.runeSlotId + "]";
    }

}
