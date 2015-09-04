package no.stelar7.api.l4j8.dto.matchlist;

import no.stelar7.api.l4j8.basic.APIObject;

public class MatchReference extends APIObject
{
    long   champion;
    String lane;
    long   matchId;
    String platformId;
    String queue;
    String role;
    String season;
    long   timestamp;

    public long getChampion()
    {
        return this.champion;
    }

    public String getLane()
    {
        return this.lane;
    }

    public long getMatchId()
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

    public String getRole()
    {
        return this.role;
    }

    public String getSeason()
    {
        return this.season;
    }

    public long getTimestamp()
    {
        return this.timestamp;
    }

    @Override
    public String toString()
    {
        return "MatchReference [champion=" + this.champion + ", lane=" + this.lane + ", matchId=" + this.matchId + ", platformId=" + this.platformId + ", queue=" + this.queue + ", role=" + this.role + ", season=" + this.season + ", timestamp=" + this.timestamp + "]";
    }
}
