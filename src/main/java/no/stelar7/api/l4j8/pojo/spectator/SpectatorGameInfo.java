package no.stelar7.api.l4j8.pojo.spectator;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.pojo.shared.BannedChampion;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class SpectatorGameInfo implements Serializable
{
    private static final long serialVersionUID = -5466430258379033702L;
    
    private List<BannedChampion>       bannedChampions;
    private long                       gameId;
    private long                       gameLength;
    private GameModeType               gameMode;
    private GameQueueType              gameQueueConfigId;
    private long                       gameStartTime;
    private GameType                   gameType;
    private MapType                    mapId;
    private Observer                   observers;
    private List<SpectatorParticipant> participants;
    private Platform                   platformId;
    
    
    /**
     * Banned champion information
     *
     * @return {@code List<BannedChampion>}
     */
    public List<BannedChampion> getBannedChampions()
    {
        return this.bannedChampions == null ? Collections.emptyList() : Collections.unmodifiableList(this.bannedChampions);
    }
    
    /**
     * The ID of the game
     *
     * @return long
     */
    public long getGameId()
    {
        return this.gameId;
    }
    
    /**
     * The amount of time in seconds that has passed since the game started
     *
     * @return long
     */
    public long getGameLength()
    {
        return this.gameLength;
    }
    
    
    /**
     * The game mode
     *
     * @return String
     */
    public GameModeType getGameMode()
    {
        return this.gameMode;
    }
    
    /**
     * The queue type
     *
     * @return long
     */
    public GameQueueType getGameQueueConfig()
    {
        return this.gameQueueConfigId;
    }
    
    /**
     * A ZonedDateTime of {@code getGameStartTime()}
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime getGameStartTimeAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameStartTime), ZoneOffset.UTC);
    }
    
    /**
     * The game type
     *
     * @return String
     */
    public GameType getGameType()
    {
        return this.gameType;
    }
    
    /**
     * The ID of the map
     *
     * @return long
     */
    public MapType getMap()
    {
        return this.mapId;
    }
    
    /**
     * The observer information
     *
     * @return Observer
     */
    public Observer getObservers()
    {
        return this.observers;
    }
    
    /**
     * The ID of the platform on which the game belongs
     *
     * @return String
     */
    public Platform getPlatform()
    {
        return this.platformId;
    }
    
    
    /**
     * Gets participants.
     *
     * @return the participants
     */
    public List<SpectatorParticipant> getParticipants()
    {
        return participants;
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
        
        SpectatorGameInfo that = (SpectatorGameInfo) o;
        
        if (gameId != that.gameId)
        {
            return false;
        }
        if (gameLength != that.gameLength)
        {
            return false;
        }
        if (gameStartTime != that.gameStartTime)
        {
            return false;
        }
        if ((bannedChampions != null) ? !bannedChampions.equals(that.bannedChampions) : (that.bannedChampions != null))
        {
            return false;
        }
        if (gameMode != that.gameMode)
        {
            return false;
        }
        if (gameQueueConfigId != that.gameQueueConfigId)
        {
            return false;
        }
        if (gameType != that.gameType)
        {
            return false;
        }
        if (mapId != that.mapId)
        {
            return false;
        }
        if ((observers != null) ? !observers.equals(that.observers) : (that.observers != null))
        {
            return false;
        }
        if ((participants != null) ? !participants.equals(that.participants) : (that.participants != null))
        {
            return false;
        }
        return platformId == that.platformId;
    }
    
    @Override
    public int hashCode()
    {
        int result = bannedChampions != null ? bannedChampions.hashCode() : 0;
        result = 31 * result + (int) (gameId ^ (gameId >>> 32));
        result = 31 * result + (int) (gameLength ^ (gameLength >>> 32));
        result = 31 * result + (gameMode != null ? gameMode.hashCode() : 0);
        result = 31 * result + (gameQueueConfigId != null ? gameQueueConfigId.hashCode() : 0);
        result = 31 * result + (int) (gameStartTime ^ (gameStartTime >>> 32));
        result = 31 * result + (gameType != null ? gameType.hashCode() : 0);
        result = 31 * result + (mapId != null ? mapId.hashCode() : 0);
        result = 31 * result + (observers != null ? observers.hashCode() : 0);
        result = 31 * result + (participants != null ? participants.hashCode() : 0);
        result = 31 * result + (platformId != null ? platformId.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "SpectatorGameInfo{" +
               "bannedChampions=" + bannedChampions +
               ", gameId=" + gameId +
               ", gameLength=" + gameLength +
               ", gameMode=" + gameMode +
               ", gameQueueConfigId=" + gameQueueConfigId +
               ", gameStartTime=" + gameStartTime +
               ", gameType=" + gameType +
               ", mapId=" + mapId +
               ", observers=" + observers +
               ", participants=" + participants +
               ", platformId=" + platformId +
               '}';
    }
}
