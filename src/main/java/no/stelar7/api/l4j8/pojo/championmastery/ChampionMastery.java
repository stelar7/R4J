package no.stelar7.api.l4j8.pojo.championmastery;

import java.time.*;

public class ChampionMastery
{
    private Integer championLevel;
    private Boolean chestGranted;
    private Integer championPoints;
    private Long    championPointsSinceLastLevel;
    private Long    playerId;
    private Long    championPointsUntilNextLevel;
    private Integer tokensEarned;
    private Integer championId;
    private Long    lastPlayTime;
    
    
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
    
    /**
     * The tokens earned for this entry.
     *
     * @return the token count
     */
    public Integer getTokensEarned()
    {
        return tokensEarned;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        ChampionMastery that = (ChampionMastery) o;
        
        if ((championLevel != null) ? !championLevel.equals(that.championLevel) : (that.championLevel != null))
        {
            return false;
        }
        if ((chestGranted != null) ? !chestGranted.equals(that.chestGranted) : (that.chestGranted != null))
        {
            return false;
        }
        if ((championPoints != null) ? !championPoints.equals(that.championPoints) : (that.championPoints != null))
        {
            return false;
        }
        if ((championPointsSinceLastLevel != null) ? !championPointsSinceLastLevel.equals(that.championPointsSinceLastLevel) : (that.championPointsSinceLastLevel != null))
        {
            return false;
        }
        if ((playerId != null) ? !playerId.equals(that.playerId) : (that.playerId != null))
        {
            return false;
        }
        if ((championPointsUntilNextLevel != null) ? !championPointsUntilNextLevel.equals(that.championPointsUntilNextLevel) : (that.championPointsUntilNextLevel != null))
        {
            return false;
        }
        if ((tokensEarned != null) ? !tokensEarned.equals(that.tokensEarned) : (that.tokensEarned != null))
        {
            return false;
        }
        if ((championId != null) ? !championId.equals(that.championId) : (that.championId != null))
        {
            return false;
        }
        return (lastPlayTime != null) ? lastPlayTime.equals(that.lastPlayTime) : (that.lastPlayTime == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = championLevel != null ? championLevel.hashCode() : 0;
        result = 31 * result + (chestGranted != null ? chestGranted.hashCode() : 0);
        result = 31 * result + (championPoints != null ? championPoints.hashCode() : 0);
        result = 31 * result + (championPointsSinceLastLevel != null ? championPointsSinceLastLevel.hashCode() : 0);
        result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
        result = 31 * result + (championPointsUntilNextLevel != null ? championPointsUntilNextLevel.hashCode() : 0);
        result = 31 * result + (tokensEarned != null ? tokensEarned.hashCode() : 0);
        result = 31 * result + (championId != null ? championId.hashCode() : 0);
        result = 31 * result + (lastPlayTime != null ? lastPlayTime.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "ChampionMastery{" +
               "championLevel=" + championLevel +
               ", chestGranted=" + chestGranted +
               ", championPoints=" + championPoints +
               ", championPointsSinceLastLevel=" + championPointsSinceLastLevel +
               ", playerId=" + playerId +
               ", championPointsUntilNextLevel=" + championPointsUntilNextLevel +
               ", tokensEarned=" + tokensEarned +
               ", championId=" + championId +
               ", lastPlayTime=" + lastPlayTime +
               '}';
    }
}
