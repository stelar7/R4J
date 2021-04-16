package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.utils.sql.*;

import java.io.Serializable;
import java.util.*;

public class PlayerTotalStats implements Serializable
{
    private static final long serialVersionUID = -2515335096414775653L;
    
    private int  score;
    private int  roundsPlayed;
    private int  kills;
    private int  deaths;
    private int  assists;
    private long playtimeMillis;
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getRoundsPlayed()
    {
        return roundsPlayed;
    }
    
    public int getKills()
    {
        return kills;
    }
    
    public int getDeaths()
    {
        return deaths;
    }
    
    public int getAssists()
    {
        return assists;
    }
    
    public long getPlaytimeMillis()
    {
        return playtimeMillis;
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
        PlayerTotalStats that = (PlayerTotalStats) o;
        return Objects.equals(score, that.score) &&
               Objects.equals(roundsPlayed, that.roundsPlayed) &&
               Objects.equals(kills, that.kills) &&
               Objects.equals(deaths, that.deaths) &&
               Objects.equals(assists, that.assists) &&
               Objects.equals(playtimeMillis, that.playtimeMillis);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(score, roundsPlayed, kills, deaths, assists, playtimeMillis);
    }
    
    @Override
    public String toString()
    {
        return "PlayerStats{" +
               "score=" + score +
               ", roundsPlayed=" + roundsPlayed +
               ", kills=" + kills +
               ", deaths=" + deaths +
               ", assists=" + assists +
               ", playtimeMillis=" + playtimeMillis +
               '}';
    }
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(Match.class, "id");
        returnMap.put(Player.class, "puuid");
        
        return returnMap;
    }
}
