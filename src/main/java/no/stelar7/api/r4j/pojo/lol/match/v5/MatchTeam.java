package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.TeamType;

import java.io.Serializable;
import java.util.*;

public class MatchTeam implements Serializable
{
    private static final long serialVersionUID = 2387712867150164023L;
    
    private List<ChampionBan>           bans;
    private Map<String, ObjectiveStats> objectives;
    private TeamType                    teamId;
    private boolean                     win;
    
    public List<ChampionBan> getBans()
    {
        return bans;
    }
    
    public Map<String, ObjectiveStats> getObjectives()
    {
        return objectives;
    }
    
    public TeamType getTeamId()
    {
        return teamId;
    }
    
    public boolean didWin()
    {
        return win;
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
        MatchTeam matchTeam = (MatchTeam) o;
        return win == matchTeam.win && Objects.equals(bans, matchTeam.bans) && Objects.equals(objectives, matchTeam.objectives) && teamId == matchTeam.teamId;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(bans, objectives, teamId, win);
    }
    
    @Override
    public String toString()
    {
        return "MatchTeam{" +
               "bans=" + bans +
               ", objectives=" + objectives +
               ", teamId=" + teamId +
               ", win=" + win +
               '}';
    }
}
