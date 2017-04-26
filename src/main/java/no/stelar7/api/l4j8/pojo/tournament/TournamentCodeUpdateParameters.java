package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.util.*;

public class TournamentCodeUpdateParameters
{
    private Set<Long>               allowedParticipants;
    private TournamentMapType       mapType;
    private TournamentPickType      pickType;
    private TournamentSpectatorType spectatorType;
    
    public TournamentCodeUpdateParameters(final Set<Long> allowedParticipants, final TournamentMapType map, final TournamentPickType pickType, final TournamentSpectatorType specType)
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
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.allowedParticipants == null) ? 0 : this.allowedParticipants.hashCode());
        result = (prime * result) + ((this.mapType == null) ? 0 : this.mapType.hashCode());
        result = (prime * result) + ((this.pickType == null) ? 0 : this.pickType.hashCode());
        result = (prime * result) + ((this.spectatorType == null) ? 0 : this.spectatorType.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final TournamentCodeUpdateParameters other = (TournamentCodeUpdateParameters) obj;
        if (this.allowedParticipants == null)
        {
            if (other.allowedParticipants != null)
            {
                return false;
            }
        } else if (!this.allowedParticipants.equals(other.allowedParticipants))
        {
            return false;
        }
        if (this.mapType == null)
        {
            if (other.mapType != null)
            {
                return false;
            }
        } else if (!this.mapType.equals(other.mapType))
        {
            return false;
        }
        if (this.pickType == null)
        {
            if (other.pickType != null)
            {
                return false;
            }
        } else if (!this.pickType.equals(other.pickType))
        {
            return false;
        }
        if (this.spectatorType == null)
        {
            if (other.spectatorType != null)
            {
                return false;
            }
        } else if (!this.spectatorType.equals(other.spectatorType))
        {
            return false;
        }
        return true;
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
