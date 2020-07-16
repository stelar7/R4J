package no.stelar7.api.r4j.pojo.val.match;

import java.io.Serializable;
import java.util.*;

public class Match implements Serializable
{
    private static final long serialVersionUID = 8170523652285088932L;
    
    private List<MatchInfo> matchInfo;
    private List<Player>      players;
    private List<Team>        teams;
    private List<RoundResult> roundResults;
    
    public List<MatchInfo> getMatchInfo()
    {
        return matchInfo;
    }
    
    public List<Player> getPlayers()
    {
        return players;
    }
    
    public List<Team> getTeams()
    {
        return teams;
    }
    
    public List<RoundResult> getRoundResults()
    {
        return roundResults;
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
        Match match = (Match) o;
        return Objects.equals(matchInfo, match.matchInfo) &&
               Objects.equals(players, match.players) &&
               Objects.equals(teams, match.teams) &&
               Objects.equals(roundResults, match.roundResults);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(matchInfo, players, teams, roundResults);
    }
    
    @Override
    public String toString()
    {
        return "Match{" +
               "matchInfo=" + matchInfo +
               ", players=" + players +
               ", teams=" + teams +
               ", roundResults=" + roundResults +
               '}';
    }
}
