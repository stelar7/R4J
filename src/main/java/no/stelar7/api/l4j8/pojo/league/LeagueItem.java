package no.stelar7.api.l4j8.pojo.league;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.io.Serializable;

public class LeagueItem implements Serializable
{
    private String     rank;
    private boolean    hotStreak;
    private MiniSeries miniSeries;
    private int        wins;
    private boolean    veteran;
    private int        losses;
    private long       playerOrTeamId;
    private String     playerOrTeamName;
    private boolean    inactive;
    private boolean    freshBlood;
    private int        leaguePoints;
    
    /**
     * Takes in a Tier, and returns the TierDivisionType created from it.
     *
     * @param tier the Tier to use for construction
     * @return TierDivisionType
     */
    public TierDivisionType getTierDivisionType(TierType tier)
    {
        return TierDivisionType.getFromCodes(tier.getValue(), rank);
    }
    
    /**
     * The league rank of the participant.
     *
     * @return the rank
     */
    public String getRank()
    {
        return this.rank;
    }
    
    /**
     * Specifies if the participant is fresh blood.
     *
     * @return the checks if is fresh blood
     */
    public boolean getIsFreshBlood()
    {
        return this.freshBlood;
    }
    
    /**
     * Specifies if the participant is on a hot streak.
     *
     * @return the checks if is hot streak
     */
    public boolean getIsHotStreak()
    {
        return this.hotStreak;
    }
    
    /**
     * Specifies if the participant is inactive.
     *
     * @return the checks if is inactive
     */
    public boolean getIsInactive()
    {
        return this.inactive;
    }
    
    /**
     * Specifies if the participant is a veteran
     *
     * @return the checks if is veteran
     */
    public boolean getIsVeteran()
    {
        return this.veteran;
    }
    
    /**
     * The league points of the participant.
     *
     * @return the league points
     */
    public int getLeaguePoints()
    {
        return this.leaguePoints;
    }
    
    /**
     * The number of losses for the participant.
     *
     * @return the losses
     */
    public int getLosses()
    {
        return this.losses;
    }
    
    /**
     * Mini series data for the participant. Only present if the participant is currently in a mini series.
     *
     * @return the mini series
     */
    public MiniSeries getMiniSeries()
    {
        return this.miniSeries;
    }
    
    public boolean isInPromos()
    {
        return miniSeries != null;
    }
    
    /**
     * The ID of the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player or team id
     */
    public long getSummonerId()
    {
        return this.playerOrTeamId;
    }
    
    /**
     * The name of the the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player or team name
     */
    public String getSummonerName()
    {
        return this.playerOrTeamName;
    }
    
    /**
     * The number of wins for the participant.
     *
     * @return the wins
     */
    public int getWins()
    {
        return this.wins;
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
        
        LeagueItem that = (LeagueItem) o;
        
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
        if (playerOrTeamId != that.playerOrTeamId)
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
        if ((miniSeries != null) ? !miniSeries.equals(that.miniSeries) : (that.miniSeries != null))
        {
            return false;
        }
        return (playerOrTeamName != null) ? playerOrTeamName.equals(that.playerOrTeamName) : (that.playerOrTeamName == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (hotStreak ? 1 : 0);
        result = 31 * result + (miniSeries != null ? miniSeries.hashCode() : 0);
        result = 31 * result + wins;
        result = 31 * result + (veteran ? 1 : 0);
        result = 31 * result + losses;
        result = 31 * result + (int) (playerOrTeamId ^ (playerOrTeamId >>> 32));
        result = 31 * result + (playerOrTeamName != null ? playerOrTeamName.hashCode() : 0);
        result = 31 * result + (inactive ? 1 : 0);
        result = 31 * result + (freshBlood ? 1 : 0);
        result = 31 * result + leaguePoints;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "LeagueItem{" +
               "rank='" + rank + '\'' +
               ", freshBlood=" + freshBlood +
               ", hotStreak=" + hotStreak +
               ", inactive=" + inactive +
               ", veteran=" + veteran +
               ", leaguePoints=" + leaguePoints +
               ", losses=" + losses +
               ", miniSeries=" + miniSeries +
               ", playerOrTeamId='" + playerOrTeamId + '\'' +
               ", playerOrTeamName='" + playerOrTeamName + '\'' +
               ", wins=" + wins +
               '}';
    }
}
