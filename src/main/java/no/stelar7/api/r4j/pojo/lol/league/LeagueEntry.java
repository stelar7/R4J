package no.stelar7.api.r4j.pojo.lol.league;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.Objects;

public class LeagueEntry extends LeagueItem implements Serializable
{
    private static final long serialVersionUID = -895051155625969402L;
    
    private GameQueueType queueType;
    private String        tier;
    private String        leagueId;
    
    public GameQueueType getQueueType()
    {
        return queueType;
    }
    
    public String getTier()
    {
        return tier;
    }
    
    public TierDivisionType getTierDivisionType()
    {
        return TierDivisionType.getFromCodes(tier, rank);
    }
    
    public String getLeagueId()
    {
        return leagueId;
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
        if (!super.equals(o))
        {
            return false;
        }
        LeagueEntry that = (LeagueEntry) o;
        return queueType == that.queueType &&
               Objects.equals(tier, that.tier) &&
               Objects.equals(leagueId, that.leagueId);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), queueType, tier, leagueId);
    }
    
    @Override
    public String toString()
    {
        return "LeagueEntry{" +
               "queueType=" + queueType +
               ", tier='" + tier + '\'' +
               ", leagueId='" + leagueId + '\'' +
               ", rank='" + rank + '\'' +
               ", hotStreak=" + hotStreak +
               ", miniSeries=" + miniSeries +
               ", wins=" + wins +
               ", veteran=" + veteran +
               ", losses=" + losses +
               ", summonerId='" + summonerId + '\'' +
               ", puuid='" + puuid + '\'' +
               ", inactive=" + inactive +
               ", freshBlood=" + freshBlood +
               ", leaguePoints=" + leaguePoints +
               '}';
    }
}
