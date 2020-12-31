package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.utils.sql.*;

import java.io.Serializable;
import java.util.*;

public class Team implements Serializable
{
    private static final long serialVersionUID = -1562243042489225119L;
    
    private String  teamId;
    private boolean won;
    private int     roundsPlayed;
    private int     roundsWon;
    private int     numPoints;
    
    public int getNumPoints()
    {
        return numPoints;
    }
    
    public String getTeamId()
    {
        return teamId;
    }
    
    public boolean getWon()
    {
        return won;
    }
    
    public int getRoundsPlayed()
    {
        return roundsPlayed;
    }
    
    public int getRoundsWon()
    {
        return roundsWon;
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
        Team team = (Team) o;
        return Objects.equals(teamId, team.teamId) &&
               Objects.equals(won, team.won) &&
               Objects.equals(roundsPlayed, team.roundsPlayed) &&
               Objects.equals(roundsWon, team.roundsWon) &&
               Objects.equals(numPoints, team.numPoints);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(teamId, won, roundsPlayed, roundsWon, numPoints);
    }
    
    @Override
    public String toString()
    {
        return "Team{" +
               "teamId='" + teamId + '\'' +
               ", won=" + won +
               ", roundsPlayed=" + roundsPlayed +
               ", roundsWon=" + roundsWon +
               ", numPoints=" + numPoints +
               '}';
    }
    
    @SQLTypeMap
    private static Map<String, String> getTypeMap()
    {
        Map<String, String> returnMap = new HashMap<>();
        
        returnMap.put("teamId", "text");
        returnMap.put("won", "boolean");
        returnMap.put("roundsPlayed", "int8");
        returnMap.put("roundsWon", "int8");
        returnMap.put("numPoints", "int8");
        
        return returnMap;
    }
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(Match.class, "id");
        
        return returnMap;
    }
}
