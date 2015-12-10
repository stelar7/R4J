package no.stelar7.api.l4j8.pojo.tournament;

import java.util.Set;

public class TournamentCode
{
    String    code;
    Integer   id;
    String    lobbyName;
    String    map;
    String    metaData;
    Set<Long> participants;
    String    password;
    String    pickType;
    Integer   providerId;
    String    region;
    String    spectators;
    Integer   teamSize;
    Integer   tournamentId;

    @Override
    public String toString()
    {
        return "TournamentCode [code=" + code + ", id=" + id + ", lobbyName=" + lobbyName + ", map=" + map + ", metaData=" + metaData + ", participants=" + participants + ", password=" + password + ", pickType=" + pickType + ", providerId=" + providerId + ", region=" + region + ", spectators=" + spectators + ", teamSize=" + teamSize + ", tournamentId=" + tournamentId + "]";
    }

    public String getCode()
    {
        return code;
    }

    public Integer getId()
    {
        return id;
    }

    public String getLobbyName()
    {
        return lobbyName;
    }

    public String getMap()
    {
        return map;
    }

    public String getMetaData()
    {
        return metaData;
    }

    public Set<Long> getParticipants()
    {
        return participants;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPickType()
    {
        return pickType;
    }

    public Integer getProviderId()
    {
        return providerId;
    }

    public String getRegion()
    {
        return region;
    }

    public String getSpectators()
    {
        return spectators;
    }

    public Integer getTeamSize()
    {
        return teamSize;
    }

    public Integer getTournamentId()
    {
        return tournamentId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lobbyName == null) ? 0 : lobbyName.hashCode());
        result = prime * result + ((map == null) ? 0 : map.hashCode());
        result = prime * result + ((metaData == null) ? 0 : metaData.hashCode());
        result = prime * result + ((participants == null) ? 0 : participants.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((pickType == null) ? 0 : pickType.hashCode());
        result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
        result = prime * result + ((spectators == null) ? 0 : spectators.hashCode());
        result = prime * result + ((teamSize == null) ? 0 : teamSize.hashCode());
        result = prime * result + ((tournamentId == null) ? 0 : tournamentId.hashCode());
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
        TournamentCode other = (TournamentCode) obj;
        if (code == null)
        {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lobbyName == null)
        {
            if (other.lobbyName != null)
                return false;
        } else if (!lobbyName.equals(other.lobbyName))
            return false;
        if (map == null)
        {
            if (other.map != null)
                return false;
        } else if (!map.equals(other.map))
            return false;
        if (metaData == null)
        {
            if (other.metaData != null)
                return false;
        } else if (!metaData.equals(other.metaData))
            return false;
        if (participants == null)
        {
            if (other.participants != null)
                return false;
        } else if (!participants.equals(other.participants))
            return false;
        if (password == null)
        {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (pickType == null)
        {
            if (other.pickType != null)
                return false;
        } else if (!pickType.equals(other.pickType))
            return false;
        if (providerId == null)
        {
            if (other.providerId != null)
                return false;
        } else if (!providerId.equals(other.providerId))
            return false;
        if (region == null)
        {
            if (other.region != null)
                return false;
        } else if (!region.equals(other.region))
            return false;
        if (spectators == null)
        {
            if (other.spectators != null)
                return false;
        } else if (!spectators.equals(other.spectators))
            return false;
        if (teamSize == null)
        {
            if (other.teamSize != null)
                return false;
        } else if (!teamSize.equals(other.teamSize))
            return false;
        if (tournamentId == null)
        {
            if (other.tournamentId != null)
                return false;
        } else if (!tournamentId.equals(other.tournamentId))
            return false;
        return true;
    }
}
