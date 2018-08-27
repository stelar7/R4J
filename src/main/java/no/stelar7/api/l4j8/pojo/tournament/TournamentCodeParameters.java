package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.exceptions.APIUnsupportedActionException;

import java.io.Serializable;
import java.util.Set;

public class TournamentCodeParameters implements Serializable
{
    private static final long serialVersionUID = 6366533149651958130L;
    
    private Set<Long>               allowedSummonerIds;
    private TournamentMapType       mapType;
    private String                  metadata;
    private TournamentPickType      pickType;
    private TournamentSpectatorType spectatorType;
    private int                     teamSize;
    
    public TournamentCodeParameters()
    {
        // GSON needs an empty constructor to initialize..
    }
    
    public TournamentCodeParameters(final TournamentCodeUpdateParameters updateParams, final String metadata, final int teamSize)
    {
        this.allowedSummonerIds = updateParams.getAllowedParticipants();
        this.spectatorType = updateParams.getSpectatorType();
        this.pickType = updateParams.getPickType();
        this.mapType = updateParams.getMapType();
        this.metadata = metadata;
        this.teamSize = teamSize;
        
        
        if (getTeamSize() < 1 || getTeamSize() > 5 || getTeamSize() * 2 != allowedSummonerIds.size())
        {
            String error = String.format("Team size: %s (min/max) (%s/%s), allowedSummonerIds.size = %s", getTeamSize(), 1, 5, allowedSummonerIds.size());
            throw new APIUnsupportedActionException(error);
        }
    }
    
    /**
     * Optional list of participants in order to validate the players eligible to join the lobby.
     * NOTE: We currently do not enforce participants at the team level, but rather the aggregate of teamOne and teamTwo.
     * We may add the ability to enforce at the team level in the future.
     *
     * @return SummonerIdParams
     */
    public Set<Long> getAllowedSummonerIds()
    {
        return this.allowedSummonerIds;
    }
    
    public void setAllowedSummonerIds(final Set<Long> allowedSummonerIds)
    {
        this.allowedSummonerIds = allowedSummonerIds;
    }
    
    /**
     * The map type of the game. Valid values are SUMMONERS_RIFT, TWISTED_TREELINE, and HOWLING_ABYSS.
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
     * Optional string that may contain any data in any format, if specified at all.
     * Used to denote any custom information about the game.
     *
     * @return string
     */
    public String getMetadata()
    {
        return this.metadata;
    }
    
    public void setMetadata(final String metadata)
    {
        this.metadata = metadata;
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
    
    /**
     * The team size of the game. Valid values are 1-5.
     *
     * @return int
     */
    public int getTeamSize()
    {
        return this.teamSize;
    }
    
    public void setTeamSize(final int teamSize)
    {
        this.teamSize = teamSize;
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
        
        TournamentCodeParameters that = (TournamentCodeParameters) o;
        
        if (teamSize != that.teamSize)
        {
            return false;
        }
        if ((allowedSummonerIds != null) ? !allowedSummonerIds.equals(that.allowedSummonerIds) : (that.allowedSummonerIds != null))
        {
            return false;
        }
        if (mapType != that.mapType)
        {
            return false;
        }
        if ((metadata != null) ? !metadata.equals(that.metadata) : (that.metadata != null))
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
        int result = allowedSummonerIds != null ? allowedSummonerIds.hashCode() : 0;
        result = 31 * result + (mapType != null ? mapType.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        result = 31 * result + (pickType != null ? pickType.hashCode() : 0);
        result = 31 * result + (spectatorType != null ? spectatorType.hashCode() : 0);
        result = 31 * result + teamSize;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "TournamentCodeParameters{" +
               "allowedSummonerIds=" + allowedSummonerIds +
               ", mapType=" + mapType +
               ", metadata='" + metadata + '\'' +
               ", pickType=" + pickType +
               ", spectatorType=" + spectatorType +
               ", teamSize=" + teamSize +
               '}';
    }
}
