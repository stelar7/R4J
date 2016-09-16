package no.stelar7.api.l4j8.pojo.currentgame;

class Rune
{
    private Integer count;
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
        if (this.count == null)
        {
            if (other.count != null)
            {
                return false;
            }
        } else if (!this.count.equals(other.count))
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
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.count == null) ? 0 : this.count.hashCode());
        result = (prime * result) + ((this.runeId == null) ? 0 : this.runeId.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Rune [count=" + this.count + ", runeId=" + this.runeId + "]";
    }

}
