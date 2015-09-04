package no.stelar7.api.l4j8.dto.summoner.runes;

import no.stelar7.api.l4j8.basic.APIObject;

public class Rune extends APIObject
{

    private int runeId;
    private int runeSlotId;

    public int getRuneId()
    {
        return this.runeId;
    }

    public int getRuneSlotId()
    {
        return this.runeSlotId;
    }

    @Override
    public String toString()
    {
        return "Rune [runeId=" + this.runeId + ", runeSlotId=" + this.runeSlotId + "]";
    }

}
