package no.stelar7.api.r4j.pojo.lol.challenges;

import java.io.Serializable;
import java.util.Objects;

public class ChallengeLeaderboardInfo implements Serializable
{
    private static final long serialVersionUID = 2937410263633953686L;
    
    private String puuid;
    private Double  value;
    private Integer position;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public Double getValue()
    {
        return value;
    }
    
    public Integer getPosition()
    {
        return position;
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
        ChallengeLeaderboardInfo that = (ChallengeLeaderboardInfo) o;
        return Objects.equals(puuid, that.puuid) && Objects.equals(value, that.value) && Objects.equals(position, that.position);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, value, position);
    }
    
    @Override
    public String toString()
    {
        return "ChallengePlayerInfo{" +
               "puuid='" + puuid + '\'' +
               ", value=" + value +
               ", position=" + position +
               '}';
    }
}
