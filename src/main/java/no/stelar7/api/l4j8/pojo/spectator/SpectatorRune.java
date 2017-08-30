package no.stelar7.api.l4j8.pojo.spectator;

import java.io.Serializable;

public class SpectatorRune implements Serializable
{
    private int count;
    private long    runeId;
    
    
    /**
     * The count of this rune used by the participant
     *
     * @return int
     */
    public int getCount()
    {
        return this.count;
    }
    
    /**
     * The ID of the rune
     *
     * @return long
     */
    public long getRuneId()
    {
        return this.runeId;
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
        
        SpectatorRune that = (SpectatorRune) o;
        
        if (count != that.count)
        {
            return false;
        }
        return runeId == that.runeId;
    }
    
    @Override
    public int hashCode()
    {
        int result = count;
        result = 31 * result + (int) (runeId ^ (runeId >>> 32));
        return result;
    }
    
    @Override
    public String toString()
    {
        return "SpectatorRune{" +
               "count=" + count +
               ", runeId=" + runeId +
               '}';
    }
}
