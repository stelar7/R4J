package no.stelar7.api.l4j8.pojo.matchlist;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;

import java.time.*;

public class MatchReference
{
    private ChampionType    champion;
    private Long            matchId;
    private Long            timestamp;
    private LaneType        lane;
    private Platform        platformId;
    private RankedQueueType queue;
    private Server          region;
    private RoleType        role;
    private SeasonType      season;
    
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
        final MatchReference other = (MatchReference) obj;
        if (this.champion == null)
        {
            if (other.champion != null)
            {
                return false;
            }
        } else if (!this.champion.equals(other.champion))
        {
            return false;
        }
        if (this.lane == null)
        {
            if (other.lane != null)
            {
                return false;
            }
        } else if (!this.lane.equals(other.lane))
        {
            return false;
        }
        if (this.matchId == null)
        {
            if (other.matchId != null)
            {
                return false;
            }
        } else if (!this.matchId.equals(other.matchId))
        {
            return false;
        }
        if (this.platformId == null)
        {
            if (other.platformId != null)
            {
                return false;
            }
        } else if (!this.platformId.equals(other.platformId))
        {
            return false;
        }
        if (this.queue == null)
        {
            if (other.queue != null)
            {
                return false;
            }
        } else if (!this.queue.equals(other.queue))
        {
            return false;
        }
        if (this.region == null)
        {
            if (other.region != null)
            {
                return false;
            }
        } else if (!this.region.equals(other.region))
        {
            return false;
        }
        if (this.role == null)
        {
            if (other.role != null)
            {
                return false;
            }
        } else if (!this.role.equals(other.role))
        {
            return false;
        }
        if (this.season == null)
        {
            if (other.season != null)
            {
                return false;
            }
        } else if (!this.season.equals(other.season))
        {
            return false;
        }
        if (this.timestamp == null)
        {
            if (other.timestamp != null)
            {
                return false;
            }
        } else if (!this.timestamp.equals(other.timestamp))
        {
            return false;
        }
        return true;
    }
    
    
    /**
     * ChampionType ID associated with game.
     *
     * @return Long
     */
    public ChampionType getChampion()
    {
        return this.champion;
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
     * @return Long
     */
    public Long getMatchId()
    {
        return this.matchId;
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
    public RankedQueueType getQueue()
    {
        return this.queue;
    }
    
    /**
     * Region
     *
     * @return String
     */
    public Server getRegion()
    {
        return this.region;
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
     * @return Long
     */
    public Long getTimestamp()
    {
        return this.timestamp;
    }
    
    public ZonedDateTime getTimestampAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.timestamp), ZoneOffset.UTC);
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.champion == null) ? 0 : this.champion.hashCode());
        result = (prime * result) + ((this.lane == null) ? 0 : this.lane.hashCode());
        result = (prime * result) + ((this.matchId == null) ? 0 : this.matchId.hashCode());
        result = (prime * result) + ((this.platformId == null) ? 0 : this.platformId.hashCode());
        result = (prime * result) + ((this.queue == null) ? 0 : this.queue.hashCode());
        result = (prime * result) + ((this.region == null) ? 0 : this.region.hashCode());
        result = (prime * result) + ((this.role == null) ? 0 : this.role.hashCode());
        result = (prime * result) + ((this.season == null) ? 0 : this.season.hashCode());
        result = (prime * result) + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchReference{" +
               "champion=" + champion +
               ", matchId=" + matchId +
               ", timestamp=" + timestamp +
               ", lane='" + lane + '\'' +
               ", platformId='" + platformId + '\'' +
               ", queue='" + queue + '\'' +
               ", region='" + region + '\'' +
               ", role='" + role + '\'' +
               ", season='" + season + '\'' +
               '}';
    }
}
