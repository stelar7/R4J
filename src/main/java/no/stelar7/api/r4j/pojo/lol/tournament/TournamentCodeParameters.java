package no.stelar7.api.r4j.pojo.lol.tournament;

import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.exceptions.APIUnsupportedActionException;

import java.io.Serializable;
import java.util.*;

public class TournamentCodeParameters implements Serializable
{
    private static final long serialVersionUID = 5408302200176552245L;
    
    
    private Set<String>             allowedParticipants;
    private TournamentMapType       mapType;
    private String                  metadata;
    private TournamentPickType      pickType;
    private TournamentSpectatorType spectatorType;
    private int                     teamSize;
    private boolean                 enoughPlayers;
    
    public TournamentCodeParameters()
    {
        // GSON needs an empty constructor to initialize..
    }
    
    public TournamentCodeParameters(final TournamentCodeUpdateParameters updateParams, final String metadata, final int teamSize)
    {
        this.allowedParticipants = updateParams.getAllowedParticipants();
        this.spectatorType = updateParams.getSpectatorType();
        this.pickType = updateParams.getPickType();
        this.mapType = updateParams.getMapType();
        this.metadata = metadata;
        this.teamSize = teamSize;
        
        
        if (getTeamSize() < 1 || getTeamSize() > 5)
        {
            String error = String.format("Team size: %s (min/max) (%s/%s), allowedSummonerIds.size = %s", getTeamSize(), 1, 5, allowedParticipants.size());
            throw new APIUnsupportedActionException(error);
        }
        
        if (allowedParticipants != null && getTeamSize() * 2 != allowedParticipants.size())
        {
            if (spectatorType == TournamentSpectatorType.NONE)
            {
                String error = String.format("Team size: %s (min/max) (%s/%s), allowedSummonerIds.size = %s", getTeamSize(), 1, 5, allowedParticipants.size());
                throw new APIUnsupportedActionException(error);
            } else if (getTeamSize() * 2 + 4 < allowedParticipants.size())
            {
                String error = String.format("Team size: %s (min/max) (%s/%s), allowedSummonerIds.size = %s, Allowed spectators: 4", getTeamSize(), 1, 5, allowedParticipants.size());
                throw new APIUnsupportedActionException(error);
            }
        }
    }
    
    /**
     * Optional list of participants in order to validate the players eligible to join the lobby.
     * NOTE: We currently do not enforce participants at the team level, but rather the aggregate of teamOne and teamTwo.
     * We may add the ability to enforce at the team level in the future.
     *
     * @return SummonerIdParams
     */
    public Set<String> getAllowedSummonerIds()
    {
        return this.allowedParticipants;
    }
    
    public void setAllowedSummonerIds(final Set<String> allowedSummonerIds)
    {
        this.allowedParticipants = allowedSummonerIds;
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
        return teamSize == that.teamSize && enoughPlayers == that.enoughPlayers && Objects.equals(allowedParticipants, that.allowedParticipants) && mapType == that.mapType && Objects.equals(metadata, that.metadata) && pickType == that.pickType && spectatorType == that.spectatorType;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(allowedParticipants, mapType, metadata, pickType, spectatorType, teamSize, enoughPlayers);
    }
    
    @Override
    public String toString()
    {
        return "TournamentCodeParameters{" +
               "allowedParticipants=" + allowedParticipants +
               ", mapType=" + mapType +
               ", metadata='" + metadata + '\'' +
               ", pickType=" + pickType +
               ", spectatorType=" + spectatorType +
               ", teamSize=" + teamSize +
               ", enoughPlayers=" + enoughPlayers +
               '}';
    }
}
