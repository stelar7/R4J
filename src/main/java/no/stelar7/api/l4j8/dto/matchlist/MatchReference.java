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
        return champion;
    }

    public String getLane()
    {
        return lane;
    }

    public long getMatchId()
    {
        return matchId;
    }

    public String getPlatformId()
    {
        return platformId;
    }

    public String getQueue()
    {
        return queue;
    }

    public String getRole()
    {
        return role;
    }

    public String getSeason()
    {
        return season;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    @Override
    public String toString()
    {
        return "MatchReference [champion=" + champion + ", lane=" + lane + ", matchId=" + matchId + ", platformId=" + platformId + ", queue=" + queue + ", role=" + role + ", season=" + season + ", timestamp=" + timestamp + "]";
    }
}
