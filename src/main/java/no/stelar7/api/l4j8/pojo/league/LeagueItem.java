package no.stelar7.api.l4j8.pojo.league;

public class LeagueItem
{
    private String     rank;
    private Boolean    hotStreak;
    private MiniSeries miniSeries;
    private Integer    wins;
    private Boolean    veteran;
    private Integer    losses;
    private Long       playerOrTeamId;
    private String     playerOrTeamName;
    private Boolean    inactive;
    private Boolean    freshBlood;
    private Integer    leaguePoints;
    
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
        final LeagueItem other = (LeagueItem) obj;
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
        if (this.freshBlood == null)
        {
            if (other.freshBlood != null)
            {
                return false;
            }
        } else if (!this.freshBlood.equals(other.freshBlood))
        {
            return false;
        }
        if (this.hotStreak == null)
        {
            if (other.hotStreak != null)
            {
                return false;
            }
        } else if (!this.hotStreak.equals(other.hotStreak))
        {
            return false;
        }
        if (this.inactive == null)
        {
            if (other.inactive != null)
            {
                return false;
            }
        } else if (!this.inactive.equals(other.inactive))
        {
            return false;
        }
        if (this.veteran == null)
        {
            if (other.veteran != null)
            {
                return false;
            }
        } else if (!this.veteran.equals(other.veteran))
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
     * The league rank of the participant.
     *
     * @return the rank
     */
    public String getRank()
    {
        return this.rank;
    }
    
    /**
     * Specifies if the participant is fresh blood.
     *
     * @return the checks if is fresh blood
     */
    public Boolean getIsFreshBlood()
    {
        return this.freshBlood;
    }
    
    /**
     * Specifies if the participant is on a hot streak.
     *
     * @return the checks if is hot streak
     */
    public Boolean getIsHotStreak()
    {
        return this.hotStreak;
    }
    
    /**
     * Specifies if the participant is inactive.
     *
     * @return the checks if is inactive
     */
    public Boolean getIsInactive()
    {
        return this.inactive;
    }
    
    /**
     * Specifies if the participant is a veteran
     *
     * @return the checks if is veteran
     */
    public Boolean getIsVeteran()
    {
        return this.veteran;
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
    
    public Boolean isInPromos()
    {
        return miniSeries != null;
    }
    
    /**
     * The ID of the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player or team id
     */
    public Long getSummonerId()
    {
        return this.playerOrTeamId;
    }
    
    /**
     * The name of the the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player or team name
     */
    public String getSummonerName()
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
        result = (prime * result) + ((this.rank == null) ? 0 : this.rank.hashCode());
        result = (prime * result) + ((this.freshBlood == null) ? 0 : this.freshBlood.hashCode());
        result = (prime * result) + ((this.hotStreak == null) ? 0 : this.hotStreak.hashCode());
        result = (prime * result) + ((this.inactive == null) ? 0 : this.inactive.hashCode());
        result = (prime * result) + ((this.veteran == null) ? 0 : this.veteran.hashCode());
        result = (prime * result) + ((this.leaguePoints == null) ? 0 : this.leaguePoints.hashCode());
        result = (prime * result) + ((this.losses == null) ? 0 : this.losses.hashCode());
        result = (prime * result) + ((this.miniSeries == null) ? 0 : this.miniSeries.hashCode());
        result = (prime * result) + ((this.playerOrTeamId == null) ? 0 : this.playerOrTeamId.hashCode());
        result = (prime * result) + ((this.playerOrTeamName == null) ? 0 : this.playerOrTeamName.hashCode());
        result = (prime * result) + ((this.wins == null) ? 0 : this.wins.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "LeagueItem{" +
               "rank='" + rank + '\'' +
               ", freshBlood=" + freshBlood +
               ", hotStreak=" + hotStreak +
               ", inactive=" + inactive +
               ", veteran=" + veteran +
               ", leaguePoints=" + leaguePoints +
               ", losses=" + losses +
               ", miniSeries=" + miniSeries +
               ", playerOrTeamId='" + playerOrTeamId + '\'' +
               ", playerOrTeamName='" + playerOrTeamName + '\'' +
               ", wins=" + wins +
               '}';
    }
}
