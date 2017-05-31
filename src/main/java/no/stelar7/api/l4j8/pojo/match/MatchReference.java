package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;

import java.time.*;

public class MatchReference
{
    private LaneType      lane;
    private long          gameId;
    private int           champion;
    private Platform      platformId;
    private long          timestamp;
    private GameQueueType queue;
    private RoleType      role;
    private SeasonType    season;
    
    
    public Match getFullMatch()
    {
        return MatchAPI.getInstance().getMatch(platformId, gameId);
    }
    
    public MatchTimeline getTimeline()
    {
        return MatchAPI.getInstance().getTimeline(platformId, gameId);
    }
    
    /**
     * champion associated with game.
     *
     * @return StaticChampion
     */
    public StaticChampion getChampion()
    {
        return StaticAPI.getCache().getChampion(this.champion);
    }
    
    /**
     * LaneType associated with game
     *
     * @return String
     */
    public LaneType getLane()
    {
        return this.lane;
    }
    
    /**
     * Match ID.
     *
     * @return long
     */
    public long getGameId()
    {
        return this.gameId;
    }
    
    /**
     * Platform ID.
     *
     * @return String
     */
    public Platform getPlatform()
    {
        return this.platformId;
    }
    
    /**
     * Queue.
     *
     * @return String
     */
    public GameQueueType getQueue()
    {
        return this.queue;
    }
    
    
    /**
     * RoleType
     *
     * @return String
     */
    public RoleType getRole()
    {
        return this.role;
    }
    
    /**
     * SeasonType
     *
     * @return String
     */
    public SeasonType getSeason()
    {
        return this.season;
    }
    
    /**
     * Timestamp
     *
     * @return long
     */
    public long getTimestamp()
    {
        return this.timestamp;
    }
    
    public ZonedDateTime getTimestampAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.timestamp), ZoneOffset.UTC);
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
        
        MatchReference that = (MatchReference) o;
        
        if (gameId != that.gameId)
        {
            return false;
        }
        if (timestamp != that.timestamp)
        {
            return false;
        }
        if (lane != that.lane)
        {
            return false;
        }
        if (champion != that.champion)
        {
            return false;
        }
        if (platformId != that.platformId)
        {
            return false;
        }
        if (queue != that.queue)
        {
            return false;
        }
        if (role != that.role)
        {
            return false;
        }
        return season == that.season;
    }
    
    @Override
    public int hashCode()
    {
        int result = lane != null ? lane.hashCode() : 0;
        result = 31 * result + (int) (gameId ^ (gameId >>> 32));
        result = 31 * result + champion;
        result = 31 * result + (platformId != null ? platformId.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (queue != null ? queue.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchReference{" +
               "champion=" + champion +
               ", gameId=" + gameId +
               ", timestamp=" + timestamp +
               ", lane='" + lane + '\'' +
               ", platformId='" + platformId + '\'' +
               ", queue='" + queue + '\'' +
               ", role='" + role + '\'' +
               ", season='" + season + '\'' +
               '}';
    }
}
