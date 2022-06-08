package no.stelar7.api.r4j.pojo.lol.challenges;

import no.stelar7.api.r4j.basic.constants.types.lol.ChallengeTierType;

import java.io.Serializable;
import java.util.Objects;

public class ChallengePoints implements Serializable
{
    private static final long serialVersionUID = 800768434827605034L;
    
    private ChallengeTierType level;
    private Integer           current;
    private Integer           max;
    private Double            percentile;
    
    public ChallengeTierType getLevel()
    {
        return level;
    }
    
    public Integer getCurrent()
    {
        return current;
    }
    
    public Integer getMax()
    {
        return max;
    }
    
    public Double getPercentile()
    {
        return percentile;
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
        ChallengePoints that = (ChallengePoints) o;
        return level == that.level && Objects.equals(current, that.current) && Objects.equals(max, that.max) && Objects.equals(percentile, that.percentile);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(level, current, max, percentile);
    }
    
    @Override
    public String toString()
    {
        return "ChallengePoints{" +
               "level=" + level +
               ", current=" + current +
               ", max=" + max +
               ", percentile=" + percentile +
               '}';
    }
}
