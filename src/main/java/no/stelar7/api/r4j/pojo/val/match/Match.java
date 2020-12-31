package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.utils.sql.*;

import java.io.Serializable;
import java.util.*;

public class Match implements Serializable
{
    private static final long serialVersionUID = 8170523652285088932L;
    
    private MatchInfo         matchInfo;
    private List<Player>      players;
    private List<RoundResult> roundResults;
    private List<Team>        teams;
    
    public MatchInfo getMatchInfo()
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
    
    
    @SQLTypeMap
    private static Map<String, String> getTypeMap()
    {
        Map<String, String> returnMap = new HashMap<>();
        
        returnMap.put("id", "int64");
        returnMap.put("gameid", "text");
        returnMap.put("platform", "text");
        
        return returnMap;
    }
    
    @SQLExtraMap
    private static Map<String, String> getExtraMap()
    {
        Map<String, String> returnMap = new HashMap<>();
        
        returnMap.put("id", "primary key auto_increment");
        
        return returnMap;
    }
}
