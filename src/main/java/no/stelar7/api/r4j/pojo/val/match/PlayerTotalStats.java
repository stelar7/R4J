package no.stelar7.api.r4j.pojo.val.match;

import java.io.Serializable;
import java.util.Objects;

public class PlayerTotalStats implements Serializable
{
    private static final long serialVersionUID = -2515335096414775653L;
    
    private Integer score;
    private Integer roundsPlayed;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Long    playtimeMillis;
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    
    public Integer getScore()
    {
        return score;
    }
    
    public Integer getRoundsPlayed()
    {
        return roundsPlayed;
    }
    
    public Integer getKills()
    {
        return kills;
    }
    
    public Integer getDeaths()
    {
        return deaths;
    }
    
    public Integer getAssists()
    {
        return assists;
    }
    
    public Long getPlaytimeMillis()
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
}
