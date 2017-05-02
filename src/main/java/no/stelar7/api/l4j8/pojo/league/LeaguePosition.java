package no.stelar7.api.l4j8.pojo.league;

import no.stelar7.api.l4j8.basic.constants.types.GameQueueType;

public class LeaguePosition
{
    private String        rank;
    private GameQueueType queueType;
    private Boolean       hotStreak;
    private MiniSeries    miniSeries;
    private Integer       wins;
    private Boolean       veteran;
    private Integer       losses;
    private String        playerOrTeamId;
    private String        leagueName;
    private String        playerOrTeamName;
    private Boolean       inactive;
    private Boolean       freshBlood;
    private String        tier;
    private Integer       leaguePoints;
    
    public String getRank()
    {
        return rank;
    }
    
    public GameQueueType getQueueType()
    {
        return queueType;
    }
    
    public Boolean isHotStreak()
    {
        return hotStreak;
    }
    
    public MiniSeries getMiniSeries()
    {
        return miniSeries;
    }
    
    public Integer getWins()
    {
        return wins;
    }
    
    public Boolean isVeteran()
    {
        return veteran;
    }
    
    public Integer getLosses()
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
    
    public Boolean isInactive()
    {
        return inactive;
    }
    
    public Boolean isFreshBlood()
    {
        return freshBlood;
    }
    
    public String getTier()
    {
        return tier;
    }
    
    public Integer getLeaguePoints()
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
        
        if ((rank != null) ? !rank.equals(that.rank) : (that.rank != null))
        {
            return false;
        }
        if (queueType != that.queueType)
        {
            return false;
        }
        if ((hotStreak != null) ? !hotStreak.equals(that.hotStreak) : (that.hotStreak != null))
        {
            return false;
        }
        if ((miniSeries != null) ? !miniSeries.equals(that.miniSeries) : (that.miniSeries != null))
        {
            return false;
        }
        if ((wins != null) ? !wins.equals(that.wins) : (that.wins != null))
        {
            return false;
        }
        if ((veteran != null) ? !veteran.equals(that.veteran) : (that.veteran != null))
        {
            return false;
        }
        if ((losses != null) ? !losses.equals(that.losses) : (that.losses != null))
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
        if ((inactive != null) ? !inactive.equals(that.inactive) : (that.inactive != null))
        {
            return false;
        }
        if ((freshBlood != null) ? !freshBlood.equals(that.freshBlood) : (that.freshBlood != null))
        {
            return false;
        }
        if ((tier != null) ? !tier.equals(that.tier) : (that.tier != null))
        {
            return false;
        }
        return (leaguePoints != null) ? leaguePoints.equals(that.leaguePoints) : (that.leaguePoints == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (queueType != null ? queueType.hashCode() : 0);
        result = 31 * result + (hotStreak != null ? hotStreak.hashCode() : 0);
        result = 31 * result + (miniSeries != null ? miniSeries.hashCode() : 0);
        result = 31 * result + (wins != null ? wins.hashCode() : 0);
        result = 31 * result + (veteran != null ? veteran.hashCode() : 0);
        result = 31 * result + (losses != null ? losses.hashCode() : 0);
        result = 31 * result + (playerOrTeamId != null ? playerOrTeamId.hashCode() : 0);
        result = 31 * result + (leagueName != null ? leagueName.hashCode() : 0);
        result = 31 * result + (playerOrTeamName != null ? playerOrTeamName.hashCode() : 0);
        result = 31 * result + (inactive != null ? inactive.hashCode() : 0);
        result = 31 * result + (freshBlood != null ? freshBlood.hashCode() : 0);
        result = 31 * result + (tier != null ? tier.hashCode() : 0);
        result = 31 * result + (leaguePoints != null ? leaguePoints.hashCode() : 0);
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
