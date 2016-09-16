package no.stelar7.api.l4j8.pojo.matchlist;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.api.Server;

import java.time.*;
import java.util.Optional;

public class MatchReference
{
    private Integer champion;
    private Long    matchId;
    private Long    timestamp;
    private String  lane;
    private String  platformId;
    private String  queue;
    private String  region;
    private String  role;
    private String  season;

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
     * The played champion as a Champion
     *
     * @return Champion
     */
    public Optional<Champion> getChampion()
    {
        return Champion.getFromId(this.champion);
    }

    /**
     * Champion ID associated with game.
     *
     * @return Long
     */
    public Integer getChampionId()
    {
        return this.champion;
    }

    /**
     * the lane represented as a Lane
     *
     * @return Lane
     */
    public Optional<Lane> getLane()
    {
        return Lane.getFromCode(this.lane);
    }

    /**
     * Lane associated with game
     *
     * @return String
     */
    public String getLaneId()
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
     * the platformId represented as a Platform
     *
     * @return Platform
     */
    public Optional<Platform> getPlatform()
    {
        return Platform.getFromCode(this.platformId);
    }

    /**
     * Platform ID.
     *
     * @return String
     */
    public String getPlatformId()
    {
        return this.platformId;
    }

    /**
     * the queue represented as a RankedQueue
     *
     * @return RankedQueue
     */
    public Optional<RankedQueue> getQueue()
    {
        return RankedQueue.getFromCode(this.queue);
    }

    /**
     * Queue.
     *
     * @return String
     */
    public String getQueueId()
    {
        return this.queue;
    }

    /**
     * the region represented as a Server
     *
     * @return Server
     */
    public Optional<Server> getRegion()
    {
        return Server.getFromCode(this.region);
    }

    /**
     * Region
     *
     * @return String
     */
    public String getRegionId()
    {
        return this.region;
    }

    /**
     * the role represented as a Role
     *
     * @return Role
     */
    public Optional<Role> getRole()
    {
        return Role.getFromCode(this.role);
    }

    /**
     * Role
     *
     * @return String
     */
    public String getRoleId()
    {
        return this.role;
    }

    /**
     * the season represented as a Season
     *
     * @return Season
     */
    public Optional<Season> getSeason()
    {
        return Season.getFromCode(this.season);
    }

    /**
     * Season
     *
     * @return String
     */
    public String getSeasonId()
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
        return "MatchReference [champion=" + this.champion + ", lane=" + this.lane + ", matchId=" + this.matchId + ", platformId=" + this.platformId + ", queue=" + this.queue + ", role=" + this.role + ", season=" + this.season + ", timestamp=" + this.timestamp + "]";
    }
}
