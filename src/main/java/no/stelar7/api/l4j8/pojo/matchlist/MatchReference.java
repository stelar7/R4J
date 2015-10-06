package no.stelar7.api.l4j8.pojo.matchlist;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import no.stelar7.api.l4j8.basic.APIObject;

public class MatchReference extends APIObject
{
    private Long   champion;
    private String lane;
    private Long   matchId;
    private String platformId;
    private String queue;
    private String region;
    private String role;
    private String season;
    private Long   timestamp;

    public Long getChampion()
    {
        return this.champion;
    }

    public String getLane()
    {
        return this.lane;
    }

    public Long getMatchId()
    {
        return this.matchId;
    }

    public String getPlatformId()
    {
        return this.platformId;
    }

    public String getQueue()
    {
        return this.queue;
    }

    public String getRegion()
    {
        return this.region;
    }

    public String getRole()
    {
        return this.role;
    }

    public String getSeason()
    {
        return this.season;
    }

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
