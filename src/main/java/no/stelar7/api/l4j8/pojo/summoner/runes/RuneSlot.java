package no.stelar7.api.l4j8.pojo.summoner.runes;

import java.io.Serializable;

public class RuneSlot implements Serializable
{
    private static final long serialVersionUID = -4856044178332521435L;
    
    private int runeId;
    private int runeSlotId;
    
    
    /**
     * SpectatorRune ID associated with the rune slot. For static information correlating to rune IDs, please refer to the LoL Static Data API.
     *
     * @return int
     */
    public int getRuneId()
    {
        return this.runeId;
    }
    
    /**
     * The SpectatorRune slot ID.
     *
     * @return int
     */
    public int getRuneSlotId()
    {
        return this.runeSlotId;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        RuneSlot runeSlot = (RuneSlot) o;
        
        if (runeId != runeSlot.runeId)
        {
            return false;
        }
        return runeSlotId == runeSlot.runeSlotId;
    }
    
    @Override
    public int hashCode()
    {
        int result = runeId;
        result = 31 * result + runeSlotId;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "SpectatorRune [runeId=" + this.runeId + ", runeSlotId=" + this.runeSlotId + "]";
    }
    
}
