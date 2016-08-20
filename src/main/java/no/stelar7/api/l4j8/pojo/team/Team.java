package no.stelar7.api.l4j8.pojo.team;

import java.time.*;
import java.util.List;

class Team
{
    private Long createDate;
    private String fullId;
    private Long lastGameDate;
    private Long lastJoinDate;
    private Long lastJoinedRankedTeamQueueDate;
    private List<MatchHistorySummary> matchHistory;
    private Long modifyDate;
    private String name;
    private Roster roster;
    private Long secondLastJoinDate;
    private String status;
    private String tag;
    private List<TeamStatDetail> teamStatDetails;
    private Long thirdLastJoinDate;

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
        final Team other = (Team) obj;
        if (this.createDate == null)
        {
            if (other.createDate != null)
            {
                return false;
            }
        } else if (!this.createDate.equals(other.createDate))
        {
            return false;
        }
        if (this.fullId == null)
        {
            if (other.fullId != null)
            {
                return false;
            }
        } else if (!this.fullId.equals(other.fullId))
        {
            return false;
        }
        if (this.lastGameDate == null)
        {
            if (other.lastGameDate != null)
            {
                return false;
            }
        } else if (!this.lastGameDate.equals(other.lastGameDate))
        {
            return false;
        }
        if (this.lastJoinDate == null)
        {
            if (other.lastJoinDate != null)
            {
                return false;
            }
        } else if (!this.lastJoinDate.equals(other.lastJoinDate))
        {
            return false;
        }
        if (this.lastJoinedRankedTeamQueueDate == null)
        {
            if (other.lastJoinedRankedTeamQueueDate != null)
            {
                return false;
            }
        } else if (!this.lastJoinedRankedTeamQueueDate.equals(other.lastJoinedRankedTeamQueueDate))
        {
            return false;
        }
        if (this.matchHistory == null)
        {
            if (other.matchHistory != null)
            {
                return false;
            }
        } else if (!this.matchHistory.equals(other.matchHistory))
        {
            return false;
        }
        if (this.modifyDate == null)
        {
            if (other.modifyDate != null)
            {
                return false;
            }
        } else if (!this.modifyDate.equals(other.modifyDate))
        {
            return false;
        }
        if (this.name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        } else if (!this.name.equals(other.name))
        {
            return false;
        }
        if (this.roster == null)
        {
            if (other.roster != null)
            {
                return false;
            }
        } else if (!this.roster.equals(other.roster))
        {
            return false;
        }
        if (this.secondLastJoinDate == null)
        {
            if (other.secondLastJoinDate != null)
            {
                return false;
            }
        } else if (!this.secondLastJoinDate.equals(other.secondLastJoinDate))
        {
            return false;
        }
        if (this.status == null)
        {
            if (other.status != null)
            {
                return false;
            }
        } else if (!this.status.equals(other.status))
        {
            return false;
        }
        if (this.tag == null)
        {
            if (other.tag != null)
            {
                return false;
            }
        } else if (!this.tag.equals(other.tag))
        {
            return false;
        }
        if (this.teamStatDetails == null)
        {
            if (other.teamStatDetails != null)
            {
                return false;
            }
        } else if (!this.teamStatDetails.equals(other.teamStatDetails))
        {
            return false;
        }
        if (this.thirdLastJoinDate == null)
        {
            if (other.thirdLastJoinDate != null)
            {
                return false;
            }
        } else if (!this.thirdLastJoinDate.equals(other.thirdLastJoinDate))
        {
            return false;
        }
        return true;
    }

    /**
     * Date that team was created specified as epoch milliseconds.
     *
     * @return the creates the date
     */
    public Long getCreateDate()
    {
        return this.createDate;
    }

    /**
     * A ZonedDateTime of {@code getCreateDate()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getCreateDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.createDate), ZoneOffset.UTC);
    }

    /**
     * Gets the full id.
     *
     * @return the full id
     */
    public String getFullId()
    {
        return this.fullId;
    }

    /**
     * Date that last game played by team ended specified as epoch milliseconds.
     *
     * @return the last game date
     */
    public Long getLastGameDate()
    {
        return this.lastGameDate;
    }

    /**
     * A ZonedDateTime of {@code getLastGameDate()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getLastGameDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.lastGameDate), ZoneOffset.UTC);
    }

    /**
     * Date that last member joined specified as epoch milliseconds.
     *
     * @return the last join date
     */
    public Long getLastJoinDate()
    {
        return this.lastJoinDate;
    }

    /**
     * Date that team last joined the ranked team queue specified as epoch milliseconds.
     *
     * @return the last joined ranked team queue date
     */
    public Long getLastJoinedRankedTeamQueueDate()
    {
        return this.lastJoinedRankedTeamQueueDate;
    }

    /**
     * A ZonedDateTime of {@code getLastJoinedRankedTeamQueueDate()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getLastJoinedRankedTeamQueueDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.lastJoinedRankedTeamQueueDate), ZoneOffset.UTC);
    }

    /**
     * Gets the match history.
     *
     * @return the match history
     */
    public List<MatchHistorySummary> getMatchHistory()
    {
        return this.matchHistory;
    }

    /**
     * Date that team was last modified specified as epoch milliseconds.
     *
     * @return the modify date
     */
    public Long getModifyDate()
    {
        return this.modifyDate;
    }

    /**
     * A ZonedDateTime of {@code getModifyDate()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getModifyDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.modifyDate), ZoneOffset.UTC);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Gets the roster.
     *
     * @return the roster
     */
    public Roster getRoster()
    {
        return this.roster;
    }

    /**
     * Date that second to last member joined specified as epoch milliseconds.
     *
     * @return the second last join date
     */
    public Long getSecondLastJoinDate()
    {
        return this.secondLastJoinDate;
    }

    /**
     * A ZonedDateTime of {@code getSecondLastJoinDate()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getSecondLastJoinDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.secondLastJoinDate), ZoneOffset.UTC);
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus()
    {
        return this.status;
    }

    /**
     * Gets the tag.
     *
     * @return the tag
     */
    public String getTag()
    {
        return this.tag;
    }

    /**
     * Gets the team stat details.
     *
     * @return the team stat details
     */
    public List<TeamStatDetail> getTeamStatDetails()
    {
        return this.teamStatDetails;
    }

    /**
     * Date that third to last member joined specified as epoch milliseconds.
     *
     * @return the third last join date
     */
    public Long getThirdLastJoinDate()
    {
        return this.thirdLastJoinDate;
    }

    /**
     * A ZonedDateTime of {@code getThirdLastJoinDate()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getThirdLastJoinDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.thirdLastJoinDate), ZoneOffset.UTC);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.createDate == null) ? 0 : this.createDate.hashCode());
        result = (prime * result) + ((this.fullId == null) ? 0 : this.fullId.hashCode());
        result = (prime * result) + ((this.lastGameDate == null) ? 0 : this.lastGameDate.hashCode());
        result = (prime * result) + ((this.lastJoinDate == null) ? 0 : this.lastJoinDate.hashCode());
        result = (prime * result) + ((this.lastJoinedRankedTeamQueueDate == null) ? 0 : this.lastJoinedRankedTeamQueueDate.hashCode());
        result = (prime * result) + ((this.matchHistory == null) ? 0 : this.matchHistory.hashCode());
        result = (prime * result) + ((this.modifyDate == null) ? 0 : this.modifyDate.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.roster == null) ? 0 : this.roster.hashCode());
        result = (prime * result) + ((this.secondLastJoinDate == null) ? 0 : this.secondLastJoinDate.hashCode());
        result = (prime * result) + ((this.status == null) ? 0 : this.status.hashCode());
        result = (prime * result) + ((this.tag == null) ? 0 : this.tag.hashCode());
        result = (prime * result) + ((this.teamStatDetails == null) ? 0 : this.teamStatDetails.hashCode());
        result = (prime * result) + ((this.thirdLastJoinDate == null) ? 0 : this.thirdLastJoinDate.hashCode());
        return result;
    }

}
