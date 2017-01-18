package no.stelar7.api.l4j8.pojo.match;

public class Rune
{
    private Long rank;
    private Long runeId;
    
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
        final Rune other = (Rune) obj;
        if (this.rank == null)
        {
            if (other.rank != null)
            {
                return false;
            }
        } else if (!this.rank.equals(other.rank))
        {
            return false;
        }
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
        return true;
    }
    
    /**
     * Gets the rank.
     *
     * @return the rank
     */
    public Long getRank()
    {
        return this.rank;
    }
    
    /**
     * Gets the rune id.
     *
     * @return the rune id
     */
    public Long getRuneId()
    {
        return this.runeId;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.rank == null) ? 0 : this.rank.hashCode());
        result = (prime * result) + ((this.runeId == null) ? 0 : this.runeId.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Rune [rank=" + this.rank + ", runeId=" + this.runeId + "]";
    }
    
}
