package no.stelar7.api.r4j.pojo.lol.championmastery;

import java.io.Serializable;
import java.util.*;

public class RewardConfig implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String rewardValue;
    private String rewardType;
    private int    maximumReward;

    public String getRewardValue()
    {
        return rewardValue;
    }
    
    public String getRewardType()
    {
        return rewardType;
    }
    
    public int getMaximumReward()
    {
        return maximumReward;
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
        RewardConfig that = (RewardConfig) o;
        return Objects.equals(rewardValue, that.rewardValue) &&
               Objects.equals(rewardType, that.rewardType) &&
               maximumReward == that.maximumReward;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(rewardValue, rewardType, maximumReward);
    }

    @Override
    public String toString()
    {
        return "RewardConfig{" +
               "rewardValue=" + rewardValue +
               ", rewardType=" + rewardType +
               ", maximumReward=" + maximumReward +
               '}';
    }
}
