package no.stelar7.api.l4j8.pojo.championmastery;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ChampionMastery
{
    Long    championId;
    Integer championLevel;
    Integer championPoints;
    Long    championPointsSinceLastLevel;
    Long    championPointsUntilNextLevel;
    Boolean chestGranted;
    String  highestGrade;
    Long    lastPlayTime;
    Long    playerId;

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((championId == null) ? 0 : championId.hashCode());
        result = prime * result + ((championLevel == null) ? 0 : championLevel.hashCode());
        result = prime * result + ((championPoints == null) ? 0 : championPoints.hashCode());
        result = prime * result + ((championPointsSinceLastLevel == null) ? 0 : championPointsSinceLastLevel.hashCode());
        result = prime * result + ((championPointsUntilNextLevel == null) ? 0 : championPointsUntilNextLevel.hashCode());
        result = prime * result + ((chestGranted == null) ? 0 : chestGranted.hashCode());
        result = prime * result + ((highestGrade == null) ? 0 : highestGrade.hashCode());
        result = prime * result + ((lastPlayTime == null) ? 0 : lastPlayTime.hashCode());
        result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChampionMastery other = (ChampionMastery) obj;
        if (championId == null)
        {
            if (other.championId != null)
                return false;
        } else if (!championId.equals(other.championId))
            return false;
        if (championLevel == null)
        {
            if (other.championLevel != null)
                return false;
        } else if (!championLevel.equals(other.championLevel))
            return false;
        if (championPoints == null)
        {
            if (other.championPoints != null)
                return false;
        } else if (!championPoints.equals(other.championPoints))
            return false;
        if (championPointsSinceLastLevel == null)
        {
            if (other.championPointsSinceLastLevel != null)
                return false;
        } else if (!championPointsSinceLastLevel.equals(other.championPointsSinceLastLevel))
            return false;
        if (championPointsUntilNextLevel == null)
        {
            if (other.championPointsUntilNextLevel != null)
                return false;
        } else if (!championPointsUntilNextLevel.equals(other.championPointsUntilNextLevel))
            return false;
        if (chestGranted == null)
        {
            if (other.chestGranted != null)
                return false;
        } else if (!chestGranted.equals(other.chestGranted))
            return false;
        if (highestGrade == null)
        {
            if (other.highestGrade != null)
                return false;
        } else if (!highestGrade.equals(other.highestGrade))
            return false;
        if (lastPlayTime == null)
        {
            if (other.lastPlayTime != null)
                return false;
        } else if (!lastPlayTime.equals(other.lastPlayTime))
            return false;
        if (playerId == null)
        {
            if (other.playerId != null)
                return false;
        } else if (!playerId.equals(other.playerId))
            return false;
        return true;
    }

    /**
     * Champion ID for this entry.
     *
     * @return the champion id
     */
    public Long getChampionId()
    {
        return championId;
    }

    /**
     * Champion level for specified player and champion combination.
     *
     * @return the champion level
     */
    public Integer getChampionLevel()
    {
        return championLevel;
    }

    /**
     * Total number of champion points for this player and champion combination - they are used to determine championLevel.
     *
     * @return the champion points
     */
    public Integer getChampionPoints()
    {
        return championPoints;
    }

    /**
     * Number of points earned since current level has been achieved. Zero if player reached maximum champion level for this champion.
     *
     * @return the champion points since last level
     */
    public Long getChampionPointsSinceLastLevel()
    {
        return championPointsSinceLastLevel;
    }

    /**
     * Number of points needed to achieve next level. Zero if player reached maximum champion level for this champion.
     *
     * @return the champion points until next level
     */
    public Long getChampionPointsUntilNextLevel()
    {
        return championPointsUntilNextLevel;
    }

    /**
     * Is chest granted for this champion or not in current season.
     *
     * @return the chest granted
     */
    public Boolean getChestGranted()
    {
        return chestGranted;
    }

    /**
     * The highest grade of this champion of current season.
     *
     * @return the highest grade
     */
    public String getHighestGrade()
    {
        return highestGrade;
    }

    /**
     * Last time this champion was played by this player - in Unix milliseconds time format.
     *
     * @return the last play time
     */
    public Long getLastPlayTime()
    {
        return lastPlayTime;
    }

    /**
     * A ZonedDateTime of {@code getLastPlayTime()}
     *
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
        return playerId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ChampionMastery [championId=" + championId + ", championLevel=" + championLevel + ", championPoints=" + championPoints + ", championPointsSinceLastLevel=" + championPointsSinceLastLevel + ", championPointsUntilNextLevel=" + championPointsUntilNextLevel + ", chestGranted=" + chestGranted + ", highestGrade=" + highestGrade + ", lastPlayTime=" + lastPlayTime + ", playerId=" + playerId + "]";
    }

}
