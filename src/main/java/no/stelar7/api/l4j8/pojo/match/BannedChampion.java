package no.stelar7.api.l4j8.pojo.match;

public class BannedChampion
{

    private Integer championId;
    private Integer pickTurn;

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
        final BannedChampion other = (BannedChampion) obj;
        if (this.championId == null)
        {
            if (other.championId != null)
            {
                return false;
            }
        } else if (!this.championId.equals(other.championId))
        {
            return false;
        }
        if (this.pickTurn == null)
        {
            if (other.pickTurn != null)
            {
                return false;
            }
        } else if (!this.pickTurn.equals(other.pickTurn))
        {
            return false;
        }
        return true;
    }

    /**
     * Banned champion ID
     *
     * @return Integer
     */
    public Integer getChampionId()
    {
        return this.championId;
    }

    /**
     * Turn during which the champion was banned
     *
     * @return Integer
     */
    public Integer getPickTurn()
    {
        return this.pickTurn;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.pickTurn == null) ? 0 : this.pickTurn.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "BannedChampion [championId=" + this.championId + ", pickTurn=" + this.pickTurn + "]";
    }
}
