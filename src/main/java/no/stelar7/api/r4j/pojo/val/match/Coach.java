package no.stelar7.api.r4j.pojo.val.match;

import java.io.Serializable;
import java.util.*;

public class Coach implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String puuid;
    private String teamId;

    public String getPuuid()
    {
        return puuid;
    }
    
    public String getTeamId()
    {
        return teamId;
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
        Coach that = (Coach) o;
        return Objects.equals(puuid, that.puuid) &&
               Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, teamId);
    }

    @Override
    public String toString()
    {
        return "Coach{" +
               "puuid=" + puuid +
               ", teamId=" + teamId +
               '}';
    }
}
