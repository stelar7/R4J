package no.stelar7.api.l4j8.pojo.league;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.io.Serializable;

public class LeaguePosition implements Serializable
{
    private String        rank;
    private GameQueueType queueType;
    private boolean       hotStreak;
    private MiniSeries    miniSeries;
    private int           wins;
    private boolean       veteran;
    private int           losses;
    private String        playerOrTeamId;
    private String        leagueName;
    private String        playerOrTeamName;
    private boolean       inactive;
    private boolean       freshBlood;
    private String        tier;
    private int           leaguePoints;
    
    public String getRank()
    {
        return rank;
    }
    
    public GameQueueType getQueueType()
    {
        return queueType;
    }
    
    public boolean isHotStreak()
    {
        return hotStreak;
    }
    
    public MiniSeries getMiniSeries()
    {
        return miniSeries;
    }
    
    public boolean isInPromos()
    {
        return miniSeries != null;
    }
    
    public int getWins()
    {
        return wins;
    }
    
    public boolean isVeteran()
    {
        return veteran;
    }
    
    public int getLosses()
    {
        return losses;
    }
    
    public String getPlayerOrTeamId()
    {
        return playerOrTeamId;
    }
    
    public String getLeagueName()
    {
        return leagueName;
    }
    
    public String getPlayerOrTeamName()
    {
        return playerOrTeamName;
    }
    
    public boolean isInactive()
    {
        return inactive;
    }
    
    public boolean isFreshBlood()
    {
        return freshBlood;
    }
    
    public String getTier()
    {
        return tier;
    }
    
    public TierDivisionType getTierDivisionType()
    {
        return TierDivisionType.getFromCodes(tier, rank);
    }
    
    public int getLeaguePoints()
    {
        return leaguePoints;
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
        
        LeaguePosition that = (LeaguePosition) o;
        
        if (hotStreak != that.hotStreak)
        {
            return false;
        }
        if (wins != that.wins)
        {
            return false;
        }
        if (veteran != that.veteran)
        {
            return false;
        }
        if (losses != that.losses)
        {
            return false;
        }
        if (inactive != that.inactive)
        {
            return false;
        }
        if (freshBlood != that.freshBlood)
        {
            return false;
        }
        if (leaguePoints != that.leaguePoints)
        {
            return false;
        }
        if ((rank != null) ? !rank.equals(that.rank) : (that.rank != null))
        {
            return false;
        }
        if (queueType != that.queueType)
        {
            return false;
        }
        if ((miniSeries != null) ? !miniSeries.equals(that.miniSeries) : (that.miniSeries != null))
        {
            return false;
        }
        if ((playerOrTeamId != null) ? !playerOrTeamId.equals(that.playerOrTeamId) : (that.playerOrTeamId != null))
        {
            return false;
        }
        if ((leagueName != null) ? !leagueName.equals(that.leagueName) : (that.leagueName != null))
        {
            return false;
        }
        if ((playerOrTeamName != null) ? !playerOrTeamName.equals(that.playerOrTeamName) : (that.playerOrTeamName != null))
        {
            return false;
        }
        return (tier != null) ? tier.equals(that.tier) : (that.tier == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (queueType != null ? queueType.hashCode() : 0);
        result = 31 * result + (hotStreak ? 1 : 0);
        result = 31 * result + (miniSeries != null ? miniSeries.hashCode() : 0);
        result = 31 * result + wins;
        result = 31 * result + (veteran ? 1 : 0);
        result = 31 * result + losses;
        result = 31 * result + (playerOrTeamId != null ? playerOrTeamId.hashCode() : 0);
        result = 31 * result + (leagueName != null ? leagueName.hashCode() : 0);
        result = 31 * result + (playerOrTeamName != null ? playerOrTeamName.hashCode() : 0);
        result = 31 * result + (inactive ? 1 : 0);
        result = 31 * result + (freshBlood ? 1 : 0);
        result = 31 * result + (tier != null ? tier.hashCode() : 0);
        result = 31 * result + leaguePoints;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "LeaguePosition{" +
               "rank='" + rank + '\'' +
               ", queueType=" + queueType +
               ", hotStreak=" + hotStreak +
               ", miniSeries=" + miniSeries +
               ", wins=" + wins +
               ", veteran=" + veteran +
               ", losses=" + losses +
               ", playerOrTeamId='" + playerOrTeamId + '\'' +
               ", leagueName='" + leagueName + '\'' +
               ", playerOrTeamName='" + playerOrTeamName + '\'' +
               ", inactive=" + inactive +
               ", freshBlood=" + freshBlood +
               ", tier='" + tier + '\'' +
               ", leaguePoints=" + leaguePoints +
               '}';
    }
    
}
