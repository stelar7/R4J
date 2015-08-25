package no.stelar7.api.l4j8.dto.summoner.runes;

import no.stelar7.api.l4j8.basic.APIObject;

public class Rune extends APIObject
{

    private int runeId;
    private int runeSlotId;

    public int getRuneSlotId()
    {
        return runeSlotId;
    }

    public int getRuneId()
    {
        return runeId;
    }

    @Override
    public String toString()
    {
        return "Rune [runeId=" + runeId + ", runeSlotId=" + runeSlotId + "]";
    }

}
