package no.stelar7.api.l4j8.pojo.league;

public class LeagueEntry
{
    private String     division;
    private Boolean    isFreshBlood;
    private Boolean    isHotStreak;
    private Boolean    isInactive;
    private Boolean    isVeteran;
    private Integer    leaguePoints;
    private Integer    losses;
    private MiniSeries miniSeries;
    private String     playerOrTeamId;
    private String     playerOrTeamName;
    private Integer    wins;

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
        final LeagueEntry other = (LeagueEntry) obj;
        if (this.division == null)
        {
            if (other.division != null)
            {
                return false;
            }
        } else if (!this.division.equals(other.division))
        {
            return false;
        }
        if (this.isFreshBlood == null)
        {
            if (other.isFreshBlood != null)
            {
                return false;
            }
        } else if (!this.isFreshBlood.equals(other.isFreshBlood))
        {
            return false;
        }
        if (this.isHotStreak == null)
        {
            if (other.isHotStreak != null)
            {
                return false;
            }
        } else if (!this.isHotStreak.equals(other.isHotStreak))
        {
            return false;
        }
        if (this.isInactive == null)
        {
            if (other.isInactive != null)
            {
                return false;
            }
        } else if (!this.isInactive.equals(other.isInactive))
        {
            return false;
        }
        if (this.isVeteran == null)
        {
            if (other.isVeteran != null)
            {
                return false;
            }
        } else if (!this.isVeteran.equals(other.isVeteran))
        {
            return false;
        }
        if (this.leaguePoints == null)
        {
            if (other.leaguePoints != null)
            {
                return false;
            }
        } else if (!this.leaguePoints.equals(other.leaguePoints))
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
        if (this.miniSeries == null)
        {
            if (other.miniSeries != null)
            {
                return false;
            }
        } else if (!this.miniSeries.equals(other.miniSeries))
        {
            return false;
        }
        if (this.playerOrTeamId == null)
        {
            if (other.playerOrTeamId != null)
            {
                return false;
            }
        } else if (!this.playerOrTeamId.equals(other.playerOrTeamId))
        {
            return false;
        }
        if (this.playerOrTeamName == null)
        {
            if (other.playerOrTeamName != null)
            {
                return false;
            }
        } else if (!this.playerOrTeamName.equals(other.playerOrTeamName))
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
     * The league division of the participant.
     *
     * @return the division
     */
    public String getDivision()
    {
        return this.division;
    }

    /**
     * Specifies if the participant is fresh blood.
     *
     * @return the checks if is fresh blood
     */
    public Boolean getIsFreshBlood()
    {
        return this.isFreshBlood;
    }

    /**
     * Specifies if the participant is on a hot streak.
     *
     * @return the checks if is hot streak
     */
    public Boolean getIsHotStreak()
    {
        return this.isHotStreak;
    }

    /**
     * Specifies if the participant is inactive.
     *
     * @return the checks if is inactive
     */
    public Boolean getIsInactive()
    {
        return this.isInactive;
    }

    /**
     * Specifies if the participant is a veteran
     *
     * @return the checks if is veteran
     */
    public Boolean getIsVeteran()
    {
        return this.isVeteran;
    }

    /**
     * The league points of the participant.
     *
     * @return the league points
     */
    public Integer getLeaguePoints()
    {
        return this.leaguePoints;
    }

    /**
     * The number of losses for the participant.
     *
     * @return the losses
     */
    public Integer getLosses()
    {
        return this.losses;
    }

    /**
     * Mini series data for the participant. Only present if the participant is currently in a mini series.
     *
     * @return the mini series
     */
    public MiniSeries getMiniSeries()
    {
        return this.miniSeries;
    }

    /**
     * The ID of the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player or team id
     */
    public String getPlayerOrTeamId()
    {
        return this.playerOrTeamId;
    }

    /**
     * The name of the the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player or team name
     */
    public String getPlayerOrTeamName()
    {
        return this.playerOrTeamName;
    }

    /**
     * The number of wins for the participant.
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
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.division == null) ? 0 : this.division.hashCode());
        result = (prime * result) + ((this.isFreshBlood == null) ? 0 : this.isFreshBlood.hashCode());
        result = (prime * result) + ((this.isHotStreak == null) ? 0 : this.isHotStreak.hashCode());
        result = (prime * result) + ((this.isInactive == null) ? 0 : this.isInactive.hashCode());
        result = (prime * result) + ((this.isVeteran == null) ? 0 : this.isVeteran.hashCode());
        result = (prime * result) + ((this.leaguePoints == null) ? 0 : this.leaguePoints.hashCode());
        result = (prime * result) + ((this.losses == null) ? 0 : this.losses.hashCode());
        result = (prime * result) + ((this.miniSeries == null) ? 0 : this.miniSeries.hashCode());
        result = (prime * result) + ((this.playerOrTeamId == null) ? 0 : this.playerOrTeamId.hashCode());
        result = (prime * result) + ((this.playerOrTeamName == null) ? 0 : this.playerOrTeamName.hashCode());
        result = (prime * result) + ((this.wins == null) ? 0 : this.wins.hashCode());
        return result;
    }

}
