package no.stelar7.api.r4j.pojo.lol.challenges;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.*;

public class ChallengeConfigInfo implements Serializable
{
    private static final long serialVersionUID = -2297516439669685637L;
    
    private Long id;
    private Map<String, Map<String, String>> localizedNames;
    private ChallengeStateType               state;
    private ChallengeTrackingType            tracking;
    private Long                             startTimestamp;
    private Long                             endTimestamp;
    private boolean                          leaderboard;
    private Map<String, Double>              thresholds;
    
    public Long getId()
    {
        return id;
    }
    
    public Map<String, Map<String, String>> getLocalizedNames()
    {
        return localizedNames;
    }
    
    public ChallengeStateType getState()
    {
        return state;
    }
    
    public ChallengeTrackingType getTracking()
    {
        return tracking;
    }
    
    public Long getStartTimestamp()
    {
        return startTimestamp;
    }
    
    public Long getEndTimestamp()
    {
        return endTimestamp;
    }
    
    public boolean isLeaderboard()
    {
        return leaderboard;
    }
    
    public Map<String, Double> getThresholds()
    {
        return thresholds;
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
        ChallengeConfigInfo that = (ChallengeConfigInfo) o;
        return leaderboard == that.leaderboard && Objects.equals(id, that.id) && Objects.equals(localizedNames, that.localizedNames) && state == that.state && tracking == that.tracking && Objects.equals(startTimestamp, that.startTimestamp) && Objects.equals(endTimestamp, that.endTimestamp) && Objects.equals(thresholds, that.thresholds);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, localizedNames, state, tracking, startTimestamp, endTimestamp, leaderboard, thresholds);
    }
    
    @Override
    public String toString()
    {
        return "ChallengeConfigInfo{" +
               "id=" + id +
               ", localizedNames=" + localizedNames +
               ", state=" + state +
               ", tracking=" + tracking +
               ", startTimestamp=" + startTimestamp +
               ", endTimestamp=" + endTimestamp +
               ", leaderboard=" + leaderboard +
               ", thresholds=" + thresholds +
               '}';
    }
}
