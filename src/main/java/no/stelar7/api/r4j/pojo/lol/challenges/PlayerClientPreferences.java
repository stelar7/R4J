package no.stelar7.api.r4j.pojo.lol.challenges;

import java.io.Serializable;
import java.util.*;

public class PlayerClientPreferences implements Serializable
{
    private static final long serialVersionUID = 3390524887471224888L;
    
    private String     bannerAccent;
    private Double     percentile;
    private List<Long> challengeIds;
    
    public String getBannerAccent()
    {
        return bannerAccent;
    }
    
    public Double getPercentile()
    {
        return percentile;
    }
    
    public List<Long> getChallengeIds()
    {
        return challengeIds;
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
        PlayerClientPreferences that = (PlayerClientPreferences) o;
        return Objects.equals(bannerAccent, that.bannerAccent) && Objects.equals(percentile, that.percentile) && Objects.equals(challengeIds, that.challengeIds);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(bannerAccent, percentile, challengeIds);
    }
    
    @Override
    public String toString()
    {
        return "PlayerClientPreferences{" +
               "bannerAccent='" + bannerAccent + '\'' +
               ", percentile=" + percentile +
               ", challengeIds=" + challengeIds +
               '}';
    }
}
