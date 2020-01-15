package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGamePlayerScores
{
    private int   assists;
    private int   creepScore;
    private int   deaths;
    private int   kills;
    private float wardScore;
    
    public int getAssists()
    {
        return assists;
    }
    
    public int getCreepScore()
    {
        return creepScore;
    }
    
    public int getDeaths()
    {
        return deaths;
    }
    
    public int getKills()
    {
        return kills;
    }
    
    public float getWardScore()
    {
        return wardScore;
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
        ActiveGamePlayerScores that = (ActiveGamePlayerScores) o;
        return assists == that.assists &&
               creepScore == that.creepScore &&
               deaths == that.deaths &&
               kills == that.kills &&
               Float.compare(that.wardScore, wardScore) == 0;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(assists, creepScore, deaths, kills, wardScore);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGamePlayerScores{" +
               "assists=" + assists +
               ", creepScore=" + creepScore +
               ", deaths=" + deaths +
               ", kills=" + kills +
               ", wardScore=" + wardScore +
               '}';
    }
}
