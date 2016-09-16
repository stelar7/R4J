package no.stelar7.api.l4j8.pojo.summoner.masteries;

public class Mastery
{
    private Integer id;
    private Integer rank;

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
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
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
     * The mastery ID. For static information correlating to masteries, please refer to the LoL Static Data API.
     *
     * @return Integer
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * The mastery rank (i.e., the number of points put into this mastery).
     *
     * @return Integer
     */
    public Integer getRank()
    {
        return this.rank;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.rank == null) ? 0 : this.rank.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Mastery [id=" + this.id + ", rank=" + this.rank + "]";
    }
}
