package no.stelar7.api.l4j8.dto.matchlist;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import no.stelar7.api.l4j8.basic.APIObject;

public class MatchReference extends APIObject
{
    Long   champion;
    String lane;
    Long   matchId;
    String platformId;
    String queue;
    String role;
    String season;
    Long   timestamp;

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

    public ZonedDateTime getTimestampAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.timestamp), ZoneOffset.UTC);
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

    @Override
    public String toString()
    {
        return "MatchReference [champion=" + this.champion + ", lane=" + this.lane + ", matchId=" + this.matchId + ", platformId=" + this.platformId + ", queue=" + this.queue + ", role=" + this.role + ", season=" + this.season + ", timestamp=" + this.timestamp + "]";
    }
}
