package no.stelar7.api.r4j.pojo.lol.challenges;

import no.stelar7.api.r4j.basic.constants.types.lol.ChallengeTierType;

import java.io.Serializable;
import java.time.*;
import java.util.Objects;

public class ChallengeInfo implements Serializable
{
    private static final long serialVersionUID = 5987175174063730532L;
    
    private Integer           challengeId;
    private Double            percentile;
    private ChallengeTierType level;
    private Integer           value;
    private Long              achievedTime;
    private Long              position;
    private Long              playersInLevel;
    
    public Integer getChallengeId()
    {
        return challengeId;
    }
    
    public Double getPercentile()
    {
        return percentile;
    }
    
    public ChallengeTierType getLevel()
    {
        return level;
    }
    
    public Integer getValue()
    {
        return value;
    }
    
    public Long getAchievedTime()
    {
        return achievedTime;
    }
    
    public ZonedDateTime getAchievedTimeAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.achievedTime), ZoneOffset.UTC);
    }
    
    public Long getPosition()
    {
        return position;
    }
    
    public Long getPlayersInLevel()
    {
        return playersInLevel;
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
        ChallengeInfo that = (ChallengeInfo) o;
        return Objects.equals(challengeId, that.challengeId) && Objects.equals(percentile, that.percentile) && level == that.level && Objects.equals(value, that.value) && Objects.equals(achievedTime, that.achievedTime) && Objects.equals(position, that.position) && Objects.equals(playersInLevel, that.playersInLevel);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(challengeId, percentile, level, value, achievedTime, position, playersInLevel);
    }
    
    @Override
    public String toString()
    {
        return "ChallengeInfo{" +
               "challengeId=" + challengeId +
               ", percentile=" + percentile +
               ", level=" + level +
               ", value=" + value +
               ", achievedTime=" + achievedTime +
               ", position=" + position +
               ", playersInLevel=" + playersInLevel +
               '}';
    }
}
