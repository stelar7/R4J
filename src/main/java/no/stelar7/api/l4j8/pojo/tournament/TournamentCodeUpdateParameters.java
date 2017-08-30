package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.io.Serializable;
import java.util.*;

public class TournamentCodeUpdateParameters  implements Serializable
{
    private Set<Long>               allowedParticipants;
    private TournamentMapType       mapType;
    private TournamentPickType      pickType;
    private TournamentSpectatorType spectatorType;
    
    public TournamentCodeUpdateParameters(final List<Long> allowedParticipants, final TournamentMapType map, final TournamentPickType pickType, final TournamentSpectatorType specType)
    {
        super();
        this.mapType = map;
        this.pickType = pickType;
        this.spectatorType = specType;
        this.allowedParticipants = new HashSet<>(allowedParticipants);
    }
    
    /**
     * Optional list of participants in order to validate the players eligible to join the lobby.
     * NOTE: We currently do not enforce participants at the team level, but rather the aggregate of teamOne and teamTwo.
     * We may add the ability to enforce at the team level in the future.
     *
     * @return set
     */
    public Set<Long> getAllowedParticipants()
    {
        return this.allowedParticipants;
    }
    
    public void setAllowedParticipants(final Set<Long> allowedParticipants)
    {
        this.allowedParticipants = new HashSet<>(allowedParticipants);
    }
    
    /**
     * The map type of the game. Valid values are SUMMONERS_RIFT, TWISTED_TREELINE and HOWLING_ABYSS.
     *
     * @return TournamentMapType
     */
    public TournamentMapType getMapType()
    {
        return this.mapType;
    }
    
    public void setMapType(final TournamentMapType mapType)
    {
        this.mapType = mapType;
    }
    
    /**
     * The pick type of the game. Valid values are BLIND_PICK, DRAFT_MODE, ALL_RANDOM, TOURNAMENT_DRAFT.
     *
     * @return TournamentPickType
     */
    public TournamentPickType getPickType()
    {
        return this.pickType;
    }
    
    public void setPickType(final TournamentPickType pickType)
    {
        this.pickType = pickType;
    }
    
    /**
     * The spectator type of the game. Valid values are NONE, LOBBYONLY, ALL.
     *
     * @return TournamentSpectatorType
     */
    public TournamentSpectatorType getSpectatorType()
    {
        return this.spectatorType;
    }
    
    public void setSpectatorType(final TournamentSpectatorType spectatorType)
    {
        this.spectatorType = spectatorType;
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
        
        TournamentCodeUpdateParameters that = (TournamentCodeUpdateParameters) o;
        
        if ((allowedParticipants != null) ? !allowedParticipants.equals(that.allowedParticipants) : (that.allowedParticipants != null))
        {
            return false;
        }
        if (mapType != that.mapType)
        {
            return false;
        }
        if (pickType != that.pickType)
        {
            return false;
        }
        return spectatorType == that.spectatorType;
    }
    
    @Override
    public int hashCode()
    {
        int result = allowedParticipants != null ? allowedParticipants.hashCode() : 0;
        result = 31 * result + (mapType != null ? mapType.hashCode() : 0);
        result = 31 * result + (pickType != null ? pickType.hashCode() : 0);
        result = 31 * result + (spectatorType != null ? spectatorType.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "TournamentCodeUpdateParameters{" +
               "allowedParticipants='" + allowedParticipants + '\'' +
               ", mapType=" + mapType +
               ", pickType=" + pickType +
               ", spectatorType=" + spectatorType +
               '}';
    }
}
