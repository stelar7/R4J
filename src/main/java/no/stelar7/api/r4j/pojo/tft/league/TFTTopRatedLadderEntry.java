package no.stelar7.api.r4j.pojo.tft.league;

import no.stelar7.api.r4j.basic.constants.types.tft.TFTTier;

import java.io.Serializable;
import java.util.*;

public class TFTTopRatedLadderEntry implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String  puuid;
    private TFTTier ratedTier;
    private int     ratedRating;
    private int     wins;
    private int     previousUpdateLadderPosition;

    public String getPuuid()
    {
        return puuid;
    }
    
    public TFTTier getRatedTier()
    {
        return ratedTier;
    }
    
    public int getRatedRating()
    {
        return ratedRating;
    }
    
    public int getWins()
    {
        return wins;
    }
    
    public int getPreviousUpdateLadderPosition()
    {
        return previousUpdateLadderPosition;
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
        TFTTopRatedLadderEntry that = (TFTTopRatedLadderEntry) o;
        return Objects.equals(puuid, that.puuid) &&
               Objects.equals(ratedTier, that.ratedTier) &&
               ratedRating == that.ratedRating &&
               wins == that.wins &&
               previousUpdateLadderPosition == that.previousUpdateLadderPosition;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, ratedTier, ratedRating, wins, previousUpdateLadderPosition);
    }

    @Override
    public String toString()
    {
        return "TFTTopRatedLadderEntry{" +
               "puuid=" + puuid +
               ", ratedTier=" + ratedTier +
               ", ratedRating=" + ratedRating +
               ", wins=" + wins +
               ", previousUpdateLadderPosition=" + previousUpdateLadderPosition +
               '}';
    }
}
