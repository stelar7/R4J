package no.stelar7.api.r4j.pojo.lol.championmastery;

import java.io.Serializable;
import java.util.*;

public class NextSeasonMilestone implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> requireGradeCounts;
    private int                  rewardMarks;
    private boolean              bonus;
    private RewardConfig         rewardConfig;

    public Map<String, Integer> getRequireGradeCounts()
    {
        return requireGradeCounts;
    }
    
    public int getRewardMarks()
    {
        return rewardMarks;
    }
    
    public boolean isBonus()
    {
        return bonus;
    }
    
    public RewardConfig getRewardConfig()
    {
        return rewardConfig;
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
        NextSeasonMilestone that = (NextSeasonMilestone) o;
        return Objects.equals(requireGradeCounts, that.requireGradeCounts) &&
               rewardMarks == that.rewardMarks &&
               bonus == that.bonus &&
               Objects.equals(rewardConfig, that.rewardConfig);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(requireGradeCounts, rewardMarks, bonus, rewardConfig);
    }

    @Override
    public String toString()
    {
        return "NextSeasonMilestone{" +
               "requireGradeCounts=" + requireGradeCounts +
               ", rewardMarks=" + rewardMarks +
               ", bonus=" + bonus +
               ", rewardConfig=" + rewardConfig +
               '}';
    }
}
