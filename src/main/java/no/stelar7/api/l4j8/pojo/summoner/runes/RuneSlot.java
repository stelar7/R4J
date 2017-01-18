package no.stelar7.api.l4j8.pojo.summoner.runes;

public class RuneSlot
{
    
    private Integer runeId;
    private Integer runeSlotId;
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final RuneSlot other = (RuneSlot) obj;
        if (this.runeId == null)
        {
            if (other.runeId != null)
            {
                return false;
            }
        } else if (!this.runeId.equals(other.runeId))
        {
            return false;
        }
        if (this.runeSlotId == null)
        {
            if (other.runeSlotId != null)
            {
                return false;
            }
        } else if (!this.runeSlotId.equals(other.runeSlotId))
        {
            return false;
        }
        return true;
    }
    
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
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.runeId == null) ? 0 : this.runeId.hashCode());
        result = (prime * result) + ((this.runeSlotId == null) ? 0 : this.runeSlotId.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Rune [runeId=" + this.runeId + ", runeSlotId=" + this.runeSlotId + "]";
    }
    
}
