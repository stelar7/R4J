package no.stelar7.api.l4j8.pojo.championmastery;

import java.time.*;

public class ChampionMastery
{
    Integer championId;
    Integer championLevel;
    Integer championPoints;
    Long    championPointsSinceLastLevel;
    Long    championPointsUntilNextLevel;
    Boolean chestGranted;
    Long    lastPlayTime;
    Long    playerId;

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
        final ChampionMastery other = (ChampionMastery) obj;
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
        if (this.championLevel == null)
        {
            if (other.championLevel != null)
            {
                return false;
            }
        } else if (!this.championLevel.equals(other.championLevel))
        {
            return false;
        }
        if (this.championPoints == null)
        {
            if (other.championPoints != null)
            {
                return false;
            }
        } else if (!this.championPoints.equals(other.championPoints))
        {
            return false;
        }
        if (this.championPointsSinceLastLevel == null)
        {
            if (other.championPointsSinceLastLevel != null)
            {
                return false;
            }
        } else if (!this.championPointsSinceLastLevel.equals(other.championPointsSinceLastLevel))
        {
            return false;
        }
        if (this.championPointsUntilNextLevel == null)
        {
            if (other.championPointsUntilNextLevel != null)
            {
                return false;
            }
        } else if (!this.championPointsUntilNextLevel.equals(other.championPointsUntilNextLevel))
        {
            return false;
        }
        if (this.chestGranted == null)
        {
            if (other.chestGranted != null)
            {
                return false;
            }
        } else if (!this.chestGranted.equals(other.chestGranted))
        {
            return false;
        }
        if (this.lastPlayTime == null)
        {
            if (other.lastPlayTime != null)
            {
                return false;
            }
        } else if (!this.lastPlayTime.equals(other.lastPlayTime))
        {
            return false;
        }
        if (this.playerId == null)
        {
            if (other.playerId != null)
            {
                return false;
            }
        } else if (!this.playerId.equals(other.playerId))
        {
            return false;
        }
        return true;
    }

    /**
     * Champion ID for this entry.
     *
     * @return the champion id
     */
    public Integer getChampionId()
    {
        return this.championId;
    }

    /**
     * Champion level for specified player and champion combination.
     *
     * @return the champion level
     */
    public Integer getChampionLevel()
    {
        return this.championLevel;
    }

    /**
     * Total number of champion points for this player and champion combination - they are used to determine championLevel.
     *
     * @return the champion points
     */
    public Integer getChampionPoints()
    {
        return this.championPoints;
    }

    /**
     * Number of points earned since current level has been achieved. Zero if player reached maximum champion level for this champion.
     *
     * @return the champion points since last level
     */
    public Long getChampionPointsSinceLastLevel()
    {
        return this.championPointsSinceLastLevel;
    }

    /**
     * Number of points needed to achieve next level. Zero if player reached maximum champion level for this champion.
     *
     * @return the champion points until next level
     */
    public Long getChampionPointsUntilNextLevel()
    {
        return this.championPointsUntilNextLevel;
    }

    /**
     * Is chest granted for this champion or not in current season.
     *
     * @return the chest granted
     */
    public Boolean getChestGranted()
    {
        return this.chestGranted;
    }

    /**
     * Last time this champion was played by this player - in Unix milliseconds time format.
     *
     * @return the last play time
     */
    public Long getLastPlayTime()
    {
        return this.lastPlayTime;
    }

    /**
     * A ZonedDateTime of {@code getLastPlayTime()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getLastPlayTimeAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.lastPlayTime), ZoneOffset.UTC);
    }

    /**
     * Player ID for this entry.
     *
     * @return the player id
     */
    public Long getPlayerId()
    {
        return this.playerId;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.championLevel == null) ? 0 : this.championLevel.hashCode());
        result = (prime * result) + ((this.championPoints == null) ? 0 : this.championPoints.hashCode());
        result = (prime * result) + ((this.championPointsSinceLastLevel == null) ? 0 : this.championPointsSinceLastLevel.hashCode());
        result = (prime * result) + ((this.championPointsUntilNextLevel == null) ? 0 : this.championPointsUntilNextLevel.hashCode());
        result = (prime * result) + ((this.chestGranted == null) ? 0 : this.chestGranted.hashCode());
        result = (prime * result) + ((this.lastPlayTime == null) ? 0 : this.lastPlayTime.hashCode());
        result = (prime * result) + ((this.playerId == null) ? 0 : this.playerId.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ChampionMastery [championId=" + this.championId + ", championLevel=" + this.championLevel + ", championPoints=" + this.championPoints + ", championPointsSinceLastLevel=" + this.championPointsSinceLastLevel + ", championPointsUntilNextLevel=" + this.championPointsUntilNextLevel + ", chestGranted=" + this.chestGranted + ", lastPlayTime=" + this.lastPlayTime + ", playerId=" + this.playerId + "]";
    }

}
