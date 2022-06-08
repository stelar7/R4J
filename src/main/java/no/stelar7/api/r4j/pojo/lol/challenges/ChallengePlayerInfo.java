package no.stelar7.api.r4j.pojo.lol.challenges;

import java.io.Serializable;
import java.util.*;

public class ChallengePlayerInfo implements Serializable
{
    private static final long serialVersionUID = 7484272181750909296L;
    
    private ChallengePoints              totalPoints;
    private Map<String, ChallengePoints> categoryPoints;
    private List<ChallengeInfo>          challenges;
    private PlayerClientPreferences      preferences;
    
    public ChallengePoints getTotalPoints()
    {
        return totalPoints;
    }
    
    public Map<String, ChallengePoints> getCategoryPoints()
    {
        return categoryPoints;
    }
    
    public List<ChallengeInfo> getChallenges()
    {
        return challenges;
    }
    
    public PlayerClientPreferences getPreferences()
    {
        return preferences;
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
        ChallengePlayerInfo that = (ChallengePlayerInfo) o;
        return Objects.equals(totalPoints, that.totalPoints) && Objects.equals(categoryPoints, that.categoryPoints) && Objects.equals(challenges, that.challenges) && Objects.equals(preferences, that.preferences);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(totalPoints, categoryPoints, challenges, preferences);
    }
    
    @Override
    public String toString()
    {
        return "ChallengePlayerInfo{" +
               "totalPoints=" + totalPoints +
               ", categoryPoints=" + categoryPoints +
               ", challenges=" + challenges +
               ", preferences=" + preferences +
               '}';
    }
}
