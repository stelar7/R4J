package no.stelar7.api.r4j.pojo.lol.challenges;

import java.io.Serializable;
import java.util.*;

public class PlayerClientPreferences implements Serializable
{
    private static final long serialVersionUID = 3390524887471224888L;
    
    private String     bannerAccent;
    private Double     percentile;
    private List<Long> challengeIds;
    private String  title;
    private String  crestBorder;
    private Integer prestigeCrestBorderLevel;
    
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
        return Objects.equals(bannerAccent, that.bannerAccent) && Objects.equals(percentile, that.percentile) && Objects.equals(challengeIds, that.challengeIds) &&
               Objects.equals(title, that.title) &&
               Objects.equals(crestBorder, that.crestBorder) &&
               Objects.equals(prestigeCrestBorderLevel, that.prestigeCrestBorderLevel);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(bannerAccent, percentile, challengeIds, title, crestBorder, prestigeCrestBorderLevel);
    }
    
    @Override
    public String toString()
    {
        return "PlayerClientPreferences{" +
               "bannerAccent='" + bannerAccent + '\'' +
               ", percentile=" + percentile +
               ", challengeIds=" + challengeIds +
               ", title=" + title +
               ", crestBorder=" + crestBorder +
               ", prestigeCrestBorderLevel=" + prestigeCrestBorderLevel +
               '}';
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getCrestBorder()
    {
        return crestBorder;
    }
    
    public Integer getPrestigeCrestBorderLevel()
    {
        return prestigeCrestBorderLevel;
    }
}
