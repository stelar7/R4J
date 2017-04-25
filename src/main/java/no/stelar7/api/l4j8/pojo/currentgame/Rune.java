package no.stelar7.api.l4j8.pojo.currentgame;

public class Rune
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
        
        Rune rune = (Rune) o;
        
        if ((count != null) ? !count.equals(rune.count) : (rune.count != null))
        {
            return false;
        }
        return (runeId != null) ? runeId.equals(rune.runeId) : (rune.runeId == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = count != null ? count.hashCode() : 0;
        result = 31 * result + (runeId != null ? runeId.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Rune{" +
               "count=" + count +
               ", runeId=" + runeId +
               '}';
    }
}
