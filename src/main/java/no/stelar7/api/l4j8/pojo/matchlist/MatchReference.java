package no.stelar7.api.l4j8.pojo.matchlist;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.basic.Platform;
import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.constants.Champion;
import no.stelar7.api.l4j8.basic.constants.Lane;
import no.stelar7.api.l4j8.basic.constants.RankedQueue;
import no.stelar7.api.l4j8.basic.constants.Role;
import no.stelar7.api.l4j8.basic.constants.Season;

public class MatchReference implements APIObject
{
    public static List<MatchReference> createFromString(final String json) throws Exception
    {
        final JsonNode node = APIObject.getDefaultMapper().readTree(json);
        final List<MatchReference> matches = new ArrayList<>();

        if (node.get("totalGames").asLong() == 0)
        {
            return matches;
        }

        node.get("matches").forEach(n -> {
            try
            {
                final MatchReference match = APIObject.getDefaultMapper().readValue(n.toString(), MatchReference.class);
                matches.add(match);
            } catch (final Exception e)
            {
                e.printStackTrace();
            }
        });
        return matches;
    }

    private Long   champion;
    private Long   matchId;
    private Long   timestamp;
    private String lane;
    private String platformId;
    private String queue;
    private String region;
    private String role;
    private String season;

    /**
     * Champion ID associated with game.
     *
     * @return Long
     */
    public Long getChampionId()
    {
        return this.champion;
    }

    /**
     * The played champion as a Champion
     * 
     * @return Champion
     */
    public Champion getChampion()
    {
        return Champion.getFromId(champion);
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
     * the lane represented as a Lane
     *
     * @return Lane
     */
    public Lane getLane()
    {
        return Lane.getFromCode(this.lane);
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
    public Platform getPlatform()
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
     * Queue.
     *
     * @return String
     */
    public String getQueueId()
    {
        return this.queue;
    }

    /**
     * the queue represented as a RankedQueue
     *
     * @return RankedQueue
     */
    public RankedQueue getQueue()
    {
        return RankedQueue.getFromCode(this.queue);
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
     * the region represented as a Server
     *
     * @return Server
     */
    public Server getRegion()
    {
        return Server.getFromCode(this.region);
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
     * the role represented as a Role
     *
     * @return Role
     */
    public Role getRole()
    {
        return Role.getFromCode(this.role);
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
     * the season represented as a Season
     *
     * @return Season
     */
    public Season getSeason()
    {
        return Season.getFromCode(this.season);
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
    public String toString()
    {
        return "MatchReference [champion=" + this.champion + ", lane=" + this.lane + ", matchId=" + this.matchId + ", platformId=" + this.platformId + ", queue=" + this.queue + ", role=" + this.role + ", season=" + this.season + ", timestamp=" + this.timestamp + "]";
    }
}
