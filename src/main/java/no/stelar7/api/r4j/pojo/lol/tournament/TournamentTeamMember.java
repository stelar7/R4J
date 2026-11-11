package no.stelar7.api.r4j.pojo.lol.tournament;

import java.io.Serializable;
import java.util.*;

public class TournamentTeamMember implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String puuid;

    public String getPuuid()
    {
        return puuid;
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
        TournamentTeamMember that = (TournamentTeamMember) o;
        return Objects.equals(puuid, that.puuid);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(puuid);
    }

    @Override
    public String toString()
    {
        return "TournamentTeamMember{" +
               "puuid=" + puuid +
               '}';
    }
}
