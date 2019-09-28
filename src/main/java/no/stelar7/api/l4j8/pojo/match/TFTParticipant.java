package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;
import java.util.*;

public class TFTParticipant implements Serializable
{
    private String         PUUID;
    private int            level;
    private int            placement;
    private int            lastRound;
    private List<TFTTrait> traits;
    private List<TFTUnit>  units;
    
    public String getPUUID()
    {
        return PUUID;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public int getPlacement()
    {
        return placement;
    }
    
    public int getLastRound()
    {
        return lastRound;
    }
    
    public List<TFTTrait> getTraits()
    {
        return traits;
    }
    
    public List<TFTUnit> getUnits()
    {
        return units;
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
        TFTParticipant that = (TFTParticipant) o;
        return level == that.level &&
               placement == that.placement &&
               lastRound == that.lastRound &&
               Objects.equals(PUUID, that.PUUID) &&
               Objects.equals(traits, that.traits) &&
               Objects.equals(units, that.units);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(PUUID, level, placement, lastRound, traits, units);
    }
    
    @Override
    public String toString()
    {
        return "TFTParticipant{" +
               "PUUID='" + PUUID + '\'' +
               ", level=" + level +
               ", placement=" + placement +
               ", lastRound=" + lastRound +
               ", traits=" + traits +
               ", units=" + units +
               '}';
    }
}
