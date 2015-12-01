package no.stelar7.api.l4j8.pojo.team;

public class TeamStatDetail
{
    private Integer averageGamesPlayed;
    private Integer losses;
    private String  teamStatType;
    private Integer wins;

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
        final TeamStatDetail other = (TeamStatDetail) obj;
        if (this.averageGamesPlayed == null)
        {
            if (other.averageGamesPlayed != null)
            {
                return false;
            }
        } else if (!this.averageGamesPlayed.equals(other.averageGamesPlayed))
        {
            return false;
        }
        if (this.losses == null)
        {
            if (other.losses != null)
            {
                return false;
            }
        } else if (!this.losses.equals(other.losses))
        {
            return false;
        }
        if (this.teamStatType == null)
        {
            if (other.teamStatType != null)
            {
                return false;
            }
        } else if (!this.teamStatType.equals(other.teamStatType))
        {
            return false;
        }
        if (this.wins == null)
        {
            if (other.wins != null)
            {
                return false;
            }
        } else if (!this.wins.equals(other.wins))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the average games played.
     *
     * @return the average games played
     */
    public Integer getAverageGamesPlayed()
    {
        return this.averageGamesPlayed;
    }

    /**
     * Gets the losses.
     *
     * @return the losses
     */
    public Integer getLosses()
    {
        return this.losses;
    }

    /**
     * Gets the team stat type.
     *
     * @return the team stat type
     */
    public String getTeamStatType()
    {
        return this.teamStatType;
    }

    /**
     * Gets the wins.
     *
     * @return the wins
     */
    public Integer getWins()
    {
        return this.wins;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.averageGamesPlayed == null) ? 0 : this.averageGamesPlayed.hashCode());
        result = (prime * result) + ((this.losses == null) ? 0 : this.losses.hashCode());
        result = (prime * result) + ((this.teamStatType == null) ? 0 : this.teamStatType.hashCode());
        result = (prime * result) + ((this.wins == null) ? 0 : this.wins.hashCode());
        return result;
    }

}
