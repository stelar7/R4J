package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.*;

public class TournamentCodeUpdateParameters
{
    String                  allowedParticipants;
    TournamentMapType       mapType;
    TournamentPickType      pickType;
    TournamentSpectatorType spectatorType;

    public TournamentCodeUpdateParameters(final String string, final TournamentMapType summonersRift, final TournamentPickType tournamentDraft, final TournamentSpectatorType all)
    {
        super();
        this.allowedParticipants = string;
        this.mapType = summonersRift;
        this.pickType = tournamentDraft;
        this.spectatorType = all;
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

    public String getAllowedParticipants()
    {
        return this.allowedParticipants;
    }

    public TournamentMapType getMapType()
    {
        return this.mapType;
    }

    public TournamentPickType getPickType()
    {
        return this.pickType;
    }

    public TournamentSpectatorType getSpectatorType()
    {
        return this.spectatorType;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.allowedParticipants == null) ? 0 : this.allowedParticipants.hashCode());
        result = (prime * result) + ((this.mapType == null) ? 0 : this.mapType.hashCode());
        result = (prime * result) + ((this.pickType == null) ? 0 : this.pickType.hashCode());
        result = (prime * result) + ((this.spectatorType == null) ? 0 : this.spectatorType.hashCode());
        return result;
    }

    public void setAllowedParticipants(final String allowedParticipants)
    {
        this.allowedParticipants = allowedParticipants;
    }

    public void setMapType(final TournamentMapType mapType)
    {
        this.mapType = mapType;
    }

    public void setPickType(final TournamentPickType pickType)
    {
        this.pickType = pickType;
    }

    public void setSpectatorType(final TournamentSpectatorType spectatorType)
    {
        this.spectatorType = spectatorType;
    }

    @Override
    public String toString()
    {
        return "TournamentCodeUpdateParameters [allowedParticipants=" + this.allowedParticipants + ", mapType=" + this.mapType + ", pickType=" + this.pickType + ", spectatorType=" + this.spectatorType + "]";
    }
}
