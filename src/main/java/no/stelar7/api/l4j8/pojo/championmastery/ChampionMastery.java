package no.stelar7.api.l4j8.pojo.championmastery;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.StaticAPI;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;

import java.time.*;

public class ChampionMastery
{
    private int     championLevel;
    private boolean chestGranted;
    private int     championPoints;
    private long    championPointsSinceLastLevel;
    private long    playerId;
    private long    championPointsUntilNextLevel;
    private int     tokensEarned;
    private int     championId;
    private long    lastPlayTime;
    
    
    /**
     * StaticChampion for this entry.
     *
     * @return the champion id
     */
    public StaticChampion getChampion()
    {
        return StaticAPI.getInstance().getChampion(Platform.EUW1, this.championId, null, null, null);
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
    public long getSummonerId()
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
        
        if (championLevel != that.championLevel)
        {
            return false;
        }
        if (chestGranted != that.chestGranted)
        {
            return false;
        }
        if (championPoints != that.championPoints)
        {
            return false;
        }
        if (championPointsSinceLastLevel != that.championPointsSinceLastLevel)
        {
            return false;
        }
        if (playerId != that.playerId)
        {
            return false;
        }
        if (championPointsUntilNextLevel != that.championPointsUntilNextLevel)
        {
            return false;
        }
        if (tokensEarned != that.tokensEarned)
        {
            return false;
        }
        if (lastPlayTime != that.lastPlayTime)
        {
            return false;
        }
        return championId == that.championId;
    }
    
    @Override
    public int hashCode()
    {
        int result = championLevel;
        result = 31 * result + (chestGranted ? 1 : 0);
        result = 31 * result + championPoints;
        result = 31 * result + (int) (championPointsSinceLastLevel ^ (championPointsSinceLastLevel >>> 32));
        result = 31 * result + (int) (playerId ^ (playerId >>> 32));
        result = 31 * result + (int) (championPointsUntilNextLevel ^ (championPointsUntilNextLevel >>> 32));
        result = 31 * result + tokensEarned;
        result = 31 * result + championId;
        result = 31 * result + (int) (lastPlayTime ^ (lastPlayTime >>> 32));
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
