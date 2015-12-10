package no.stelar7.api.l4j8.pojo.tournament;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

import no.stelar7.api.l4j8.basic.constants.TournamentMapType;
import no.stelar7.api.l4j8.basic.constants.TournamentPickType;
import no.stelar7.api.l4j8.basic.constants.TournamentSpectatorType;

public class TournamentCodeParameters
{
    SummonerIdParams        allowedSummonerIds;
    TournamentMapType       mapType;
    String                  metadata;
    TournamentPickType      pickType;
    TournamentSpectatorType spectatorType;
    Integer                 teamSize;

    public SummonerIdParams getAllowedSummonerIds()
    {
        return allowedSummonerIds;
    }

    public TournamentMapType getMapType()
    {
        return mapType;
    }

    public String getMetadata()
    {
        return metadata;
    }

    public TournamentPickType getPickType()
    {
        return pickType;
    }

    public TournamentSpectatorType getSpectatorType()
    {
        return spectatorType;
    }

    public Integer getTeamSize()
    {
        return teamSize;
    }

    public TournamentCodeParameters()
    {

    }

    public TournamentCodeParameters(TournamentCodeUpdateParameters updateParams, String metadata, Integer teamSize)
    {
        this.allowedSummonerIds = new SummonerIdParams(Pattern.compile(",").splitAsStream(updateParams.allowedParticipants).map(Long::parseLong).collect(Collectors.toSet()));
        this.mapType = updateParams.mapType;
        this.metadata = metadata;
        this.pickType = updateParams.pickType;
        this.spectatorType = updateParams.spectatorType;
        this.teamSize = teamSize;
    }

    public void setAllowedSummonerIds(SummonerIdParams allowedSummonerIds)
    {
        this.allowedSummonerIds = allowedSummonerIds;
    }

    public void setMapType(TournamentMapType mapType)
    {
        this.mapType = mapType;
    }

    public void setMetadata(String metadata)
    {
        this.metadata = metadata;
    }

    public void setPickType(TournamentPickType pickType)
    {
        this.pickType = pickType;
    }

    public void setSpectatorType(TournamentSpectatorType spectatorType)
    {
        this.spectatorType = spectatorType;
    }

    public void setTeamSize(Integer teamSize)
    {
        this.teamSize = teamSize;
    }

    @Override
    public String toString()
    {
        return "TournamentCodeParameters [allowedSummonerIds=" + allowedSummonerIds + ", mapType=" + mapType + ", metadata=" + metadata + ", pickType=" + pickType + ", spectatorType=" + spectatorType + ", teamSize=" + teamSize + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allowedSummonerIds == null) ? 0 : allowedSummonerIds.hashCode());
        result = prime * result + ((mapType == null) ? 0 : mapType.hashCode());
        result = prime * result + ((metadata == null) ? 0 : metadata.hashCode());
        result = prime * result + ((pickType == null) ? 0 : pickType.hashCode());
        result = prime * result + ((spectatorType == null) ? 0 : spectatorType.hashCode());
        result = prime * result + ((teamSize == null) ? 0 : teamSize.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TournamentCodeParameters other = (TournamentCodeParameters) obj;
        if (allowedSummonerIds == null)
        {
            if (other.allowedSummonerIds != null)
                return false;
        } else if (!allowedSummonerIds.equals(other.allowedSummonerIds))
            return false;
        if (mapType == null)
        {
            if (other.mapType != null)
                return false;
        } else if (!mapType.equals(other.mapType))
            return false;
        if (metadata == null)
        {
            if (other.metadata != null)
                return false;
        } else if (!metadata.equals(other.metadata))
            return false;
        if (pickType == null)
        {
            if (other.pickType != null)
                return false;
        } else if (!pickType.equals(other.pickType))
            return false;
        if (spectatorType == null)
        {
            if (other.spectatorType != null)
                return false;
        } else if (!spectatorType.equals(other.spectatorType))
            return false;
        if (teamSize == null)
        {
            if (other.teamSize != null)
                return false;
        } else if (!teamSize.equals(other.teamSize))
            return false;
        return true;
    }

}
