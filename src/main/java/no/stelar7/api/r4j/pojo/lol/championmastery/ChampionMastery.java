package no.stelar7.api.r4j.pojo.lol.championmastery;

import java.io.Serializable;
import java.time.*;
import java.util.Objects;

public class ChampionMastery implements Serializable
{
    private static final long serialVersionUID = -5579938213777731266L;
    
    private int     championLevel;
    private boolean chestGranted;
    private int     championPoints;
    private long    championPointsSinceLastLevel;
    private String  playerId;
    private long    championPointsUntilNextLevel;
    private int     tokensEarned;
    private int     championId;
    private long    lastPlayTime;
    
    
    /**
     * The id of the champion
     *
     * @return int
     */
    public int getChampionId()
    {
        return championId;
    }
    
    /**
     * Level for specified player and champion combination.
     *
     * @return the champion level
     */
    public int getChampionLevel()
    {
        return this.championLevel;
    }
    
    /**
     * Total number of champion points for this player and champion combination - they are used to determine championLevel.
     *
     * @return the champion points
     */
    public int getChampionPoints()
    {
        return this.championPoints;
    }
    
    /**
     * Number of points earned since current level has been achieved. Zero if player reached maximum champion level for this champion.
     *
     * @return the champion points since last level
     */
    public long getChampionPointsSinceLastLevel()
    {
        return this.championPointsSinceLastLevel;
    }
    
    /**
     * Number of points needed to achieve next level. Zero if player reached maximum champion level for this champion.
     *
     * @return the champion points until next level
     */
    public long getChampionPointsUntilNextLevel()
    {
        return this.championPointsUntilNextLevel;
    }
    
    /**
     * Is chest granted for this champion or not in current season.
     *
     * @return the chest granted
     */
    public boolean getChestGranted()
    {
        return this.chestGranted;
    }
    
    /**
     * Last time this champion was played by this player - in Unix milliseconds time format.
     *
     * @return the last play time
     */
    public long getLastPlayTime()
    {
        return this.lastPlayTime;
    }
    
    /**
     * A ZonedDateTime of {@code getLastPlayTime()}
     *
     * @return ZonedDateTime
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
    public String getSummonerId()
    {
        return this.playerId;
    }
    
    /**
     * The tokens earned for this entry.
     *
     * @return the token count
     */
    public int getTokensEarned()
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
        return championLevel == that.championLevel &&
               chestGranted == that.chestGranted &&
               championPoints == that.championPoints &&
               championPointsSinceLastLevel == that.championPointsSinceLastLevel &&
               championPointsUntilNextLevel == that.championPointsUntilNextLevel &&
               tokensEarned == that.tokensEarned &&
               championId == that.championId &&
               lastPlayTime == that.lastPlayTime &&
               Objects.equals(playerId, that.playerId);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championLevel, chestGranted, championPoints, championPointsSinceLastLevel, playerId, championPointsUntilNextLevel, tokensEarned, championId, lastPlayTime);
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
