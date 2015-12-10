package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.TournamentMapType;
import no.stelar7.api.l4j8.basic.constants.TournamentPickType;
import no.stelar7.api.l4j8.basic.constants.TournamentSpectatorType;

public class TournamentCodeUpdateParameters
{
    String                  allowedParticipants;
    TournamentMapType       mapType;
    TournamentPickType      pickType;
    TournamentSpectatorType spectatorType;

    public String getAllowedParticipants()
    {
        return allowedParticipants;
    }

    public TournamentMapType getMapType()
    {
        return mapType;
    }

    public TournamentPickType getPickType()
    {
        return pickType;
    }

    public TournamentSpectatorType getSpectatorType()
    {
        return spectatorType;
    }

    public TournamentCodeUpdateParameters(String string, TournamentMapType summonersRift, TournamentPickType tournamentDraft, TournamentSpectatorType all)
    {
        super();
        this.allowedParticipants = string;
        this.mapType = summonersRift;
        this.pickType = tournamentDraft;
        this.spectatorType = all;
    }

    @Override
    public String toString()
    {
        return "TournamentCodeUpdateParameters [allowedParticipants=" + allowedParticipants + ", mapType=" + mapType + ", pickType=" + pickType + ", spectatorType=" + spectatorType + "]";
    }

    public void setAllowedParticipants(String allowedParticipants)
    {
        this.allowedParticipants = allowedParticipants;
    }

    public void setMapType(TournamentMapType mapType)
    {
        this.mapType = mapType;
    }

    public void setPickType(TournamentPickType pickType)
    {
        this.pickType = pickType;
    }

    public void setSpectatorType(TournamentSpectatorType spectatorType)
    {
        this.spectatorType = spectatorType;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allowedParticipants == null) ? 0 : allowedParticipants.hashCode());
        result = prime * result + ((mapType == null) ? 0 : mapType.hashCode());
        result = prime * result + ((pickType == null) ? 0 : pickType.hashCode());
        result = prime * result + ((spectatorType == null) ? 0 : spectatorType.hashCode());
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
        TournamentCodeUpdateParameters other = (TournamentCodeUpdateParameters) obj;
        if (allowedParticipants == null)
        {
            if (other.allowedParticipants != null)
                return false;
        } else if (!allowedParticipants.equals(other.allowedParticipants))
            return false;
        if (mapType == null)
        {
            if (other.mapType != null)
                return false;
        } else if (!mapType.equals(other.mapType))
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
        return true;
    }
}
