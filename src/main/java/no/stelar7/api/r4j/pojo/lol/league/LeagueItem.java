package no.stelar7.api.r4j.pojo.lol.league;


import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.Objects;

public class LeagueItem implements Serializable
{
    private static final long serialVersionUID = -6639741183354914597L;
    
    protected String     rank;
    protected boolean    hotStreak;
    protected MiniSeries miniSeries;
    protected int        wins;
    protected boolean    veteran;
    protected int        losses;
    protected String     summonerId;
    protected String     puuid;
    protected boolean    inactive;
    protected boolean    freshBlood;
    protected int        leaguePoints;
    
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
     * The summoner ID of the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player summoner id
     */
    public String getSummonerId()
    {
        return this.summonerId;
    }
    
    /**
     * The puuid of the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player puuid
     */
    public String getPuuid() {
      return this.puuid;
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
        return hotStreak == that.hotStreak &&
               wins == that.wins &&
               veteran == that.veteran &&
               losses == that.losses &&
               inactive == that.inactive &&
               freshBlood == that.freshBlood &&
               leaguePoints == that.leaguePoints &&
               Objects.equals(rank, that.rank) &&
               Objects.equals(miniSeries, that.miniSeries) &&
               Objects.equals(summonerId, that.summonerId);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(rank, hotStreak, miniSeries, wins, veteran, losses, summonerId, inactive, freshBlood, leaguePoints);
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
               ", summonerId='" + summonerId + '\'' +
               ", puuid='" + puuid + '\'' +
               ", wins=" + wins +
               '}';
    }
}
