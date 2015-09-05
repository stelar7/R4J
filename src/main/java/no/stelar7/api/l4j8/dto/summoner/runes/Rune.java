package no.stelar7.api.l4j8.dto.summoner.runes;

import no.stelar7.api.l4j8.basic.APIObject;

public class Rune extends APIObject
{

    private Integer runeId;
    private Integer runeSlotId;

    public Integer getRuneId()
    {
        return this.runeId;
    }

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
