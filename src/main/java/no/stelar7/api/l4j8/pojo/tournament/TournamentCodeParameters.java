package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.types.*;

public class TournamentCodeParameters
{
    private SummonerIdParams        allowedSummonerIds;
    private TournamentMapType       mapType;
    private String                  metadata;
    private TournamentPickType      pickType;
    private TournamentSpectatorType spectatorType;
    private Integer                 teamSize;
    
    public TournamentCodeParameters()
    {
        // GSON needs an empty constructor to initialize..
    }
    
    public TournamentCodeParameters(final TournamentCodeUpdateParameters updateParams, final String metadata, final Integer teamSize)
    {
        this.allowedSummonerIds = new SummonerIdParams(updateParams.getAllowedParticipants());
        this.spectatorType = updateParams.getSpectatorType();
        this.pickType = updateParams.getPickType();
        this.mapType = updateParams.getMapType();
        this.metadata = metadata;
        this.teamSize = teamSize;
    }
    
    /**
     * Optional list of participants in order to validate the players eligible to join the lobby.
     * NOTE: We currently do not enforce participants at the team level, but rather the aggregate of teamOne and teamTwo.
     * We may add the ability to enforce at the team level in the future.
     *
     * @return SummonerIdParams
     */
    public SummonerIdParams getAllowedSummonerIds()
    {
        return this.allowedSummonerIds;
    }
    
    public void setAllowedSummonerIds(final SummonerIdParams allowedSummonerIds)
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
    public Integer getTeamSize()
    {
        return this.teamSize;
    }
    
    public void setTeamSize(final Integer teamSize)
    {
        this.teamSize = teamSize;
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
        final TournamentCodeParameters other = (TournamentCodeParameters) obj;
        if (this.allowedSummonerIds == null)
        {
            if (other.allowedSummonerIds != null)
            {
                return false;
            }
        } else if (!this.allowedSummonerIds.equals(other.allowedSummonerIds))
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
        if (this.metadata == null)
        {
            if (other.metadata != null)
            {
                return false;
            }
        } else if (!this.metadata.equals(other.metadata))
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
        if (this.teamSize == null)
        {
            if (other.teamSize != null)
            {
                return false;
            }
        } else if (!this.teamSize.equals(other.teamSize))
        {
            return false;
        }
        return true;
    }
    
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.allowedSummonerIds == null) ? 0 : this.allowedSummonerIds.hashCode());
        result = (prime * result) + ((this.mapType == null) ? 0 : this.mapType.hashCode());
        result = (prime * result) + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = (prime * result) + ((this.pickType == null) ? 0 : this.pickType.hashCode());
        result = (prime * result) + ((this.spectatorType == null) ? 0 : this.spectatorType.hashCode());
        result = (prime * result) + ((this.teamSize == null) ? 0 : this.teamSize.hashCode());
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
