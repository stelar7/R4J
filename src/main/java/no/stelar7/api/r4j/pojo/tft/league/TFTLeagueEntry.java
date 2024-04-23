package no.stelar7.api.r4j.pojo.tft.league;

import no.stelar7.api.r4j.basic.constants.types.tft.TFTTier;
import no.stelar7.api.r4j.pojo.lol.league.LeagueEntry;

import java.io.Serializable;
import java.util.Objects;

public class TFTLeagueEntry extends LeagueEntry implements Serializable
{
    private static final long serialVersionUID = -2296555611009869807L;
    
    private TFTTier ratedTier;
    private int     ratedRating;
    
    public TFTTier getRatedTier()
    {
        return ratedTier;
    }
    
    public int getRatedRating()
    {
        return ratedRating;
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
        TFTLeagueEntry that = (TFTLeagueEntry) o;
        return ratedRating == that.ratedRating && ratedTier == that.ratedTier;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), ratedTier, ratedRating);
    }
    
    @Override
    public String toString()
    {
        return "TFTLeagueEntry{" +
               "rank='" + rank + '\'' +
               ", hotStreak=" + hotStreak +
               ", miniSeries=" + miniSeries +
               ", wins=" + wins +
               ", veteran=" + veteran +
               ", losses=" + losses +
               ", summonerId='" + summonerId + '\'' +
               ", inactive=" + inactive +
               ", freshBlood=" + freshBlood +
               ", leaguePoints=" + leaguePoints +
               ", ratedTier=" + ratedTier +
               ", ratedRating=" + ratedRating +
               '}';
    }
}
