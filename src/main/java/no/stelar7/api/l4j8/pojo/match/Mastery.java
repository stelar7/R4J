package no.stelar7.api.l4j8.pojo.match;

public class Mastery
{
    private Long masteryId;
    private Long rank;

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
        final Mastery other = (Mastery) obj;
        if (this.masteryId == null)
        {
            if (other.masteryId != null)
            {
                return false;
            }
        } else if (!this.masteryId.equals(other.masteryId))
        {
            return false;
        }
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
        return true;
    }

    /**
     * Mastery ID
     *
     * @return Long
     */
    public Long getMasteryId()
    {
        return this.masteryId;
    }

    /**
     * Mastery Rank
     *
     * @return Long
     */
    public Long getRank()
    {
        return this.rank;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.masteryId == null) ? 0 : this.masteryId.hashCode());
        result = (prime * result) + ((this.rank == null) ? 0 : this.rank.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Mastery [masteryId=" + this.masteryId + ", rank=" + this.rank + "]";
    }

}
