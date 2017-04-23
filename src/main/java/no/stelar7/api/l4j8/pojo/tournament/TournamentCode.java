package no.stelar7.api.l4j8.pojo.tournament;

import java.util.*;

public class TournamentCode
{
    private String    code;
    private Long      id;
    private String    lobbyName;
    private String    map;
    private String    metaData;
    private Set<Long> participants;
    private String    password;
    private String    pickType;
    private Integer   providerId;
    private String    region;
    private String    spectators;
    private Integer   teamSize;
    private Integer   tournamentId;
    
    
    /**
     * The tournament code.
     */
    public String getCode()
    {
        return this.code;
    }
    
    /**
     * The tournament code's ID.
     */
    public Long getId()
    {
        return this.id;
    }
    
    /**
     * The lobby name for the tournament code game.
     */
    public String getLobbyName()
    {
        return this.lobbyName;
    }
    
    /**
     * The game map for the tournament code game
     */
    public String getMap()
    {
        return this.map;
    }
    
    /**
     * The metadata for tournament code.
     */
    public String getMetaData()
    {
        return this.metaData;
    }
    
    /**
     * The participants for tournament code.
     */
    public Set<Long> getParticipants()
    {
        return this.participants;
    }
    
    /**
     * The password for the tournament code game.
     */
    public String getPassword()
    {
        return this.password;
    }
    
    /**
     * The pick mode for tournament code game.
     */
    public String getPickType()
    {
        return this.pickType;
    }
    
    /**
     * The provider's ID.
     */
    public Integer getProviderId()
    {
        return this.providerId;
    }
    
    /**
     * The tournament code's region.
     */
    public String getRegion()
    {
        return this.region;
    }
    
    /**
     * The spectator mode for the tournament code game.
     */
    public String getSpectators()
    {
        return this.spectators;
    }
    
    /**
     * The team size for the tournament code game.
     */
    public Integer getTeamSize()
    {
        return this.teamSize;
    }
    
    /**
     * The tournament's ID.
     */
    public Integer getTournamentId()
    {
        return this.tournamentId;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.code == null) ? 0 : this.code.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.lobbyName == null) ? 0 : this.lobbyName.hashCode());
        result = (prime * result) + ((this.map == null) ? 0 : this.map.hashCode());
        result = (prime * result) + ((this.metaData == null) ? 0 : this.metaData.hashCode());
        result = (prime * result) + ((this.participants == null) ? 0 : this.participants.hashCode());
        result = (prime * result) + ((this.password == null) ? 0 : this.password.hashCode());
        result = (prime * result) + ((this.pickType == null) ? 0 : this.pickType.hashCode());
        result = (prime * result) + ((this.providerId == null) ? 0 : this.providerId.hashCode());
        result = (prime * result) + ((this.region == null) ? 0 : this.region.hashCode());
        result = (prime * result) + ((this.spectators == null) ? 0 : this.spectators.hashCode());
        result = (prime * result) + ((this.teamSize == null) ? 0 : this.teamSize.hashCode());
        result = (prime * result) + ((this.tournamentId == null) ? 0 : this.tournamentId.hashCode());
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
        final TournamentCode other = (TournamentCode) obj;
        if (this.code == null)
        {
            if (other.code != null)
            {
                return false;
            }
        } else if (!this.code.equals(other.code))
        {
            return false;
        }
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        if (this.lobbyName == null)
        {
            if (other.lobbyName != null)
            {
                return false;
            }
        } else if (!this.lobbyName.equals(other.lobbyName))
        {
            return false;
        }
        if (this.map == null)
        {
            if (other.map != null)
            {
                return false;
            }
        } else if (!this.map.equals(other.map))
        {
            return false;
        }
        if (this.metaData == null)
        {
            if (other.metaData != null)
            {
                return false;
            }
        } else if (!this.metaData.equals(other.metaData))
        {
            return false;
        }
        if (this.participants == null)
        {
            if (other.participants != null)
            {
                return false;
            }
        } else if (!this.participants.equals(other.participants))
        {
            return false;
        }
        if (this.password == null)
        {
            if (other.password != null)
            {
                return false;
            }
        } else if (!this.password.equals(other.password))
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
        if (this.providerId == null)
        {
            if (other.providerId != null)
            {
                return false;
            }
        } else if (!this.providerId.equals(other.providerId))
        {
            return false;
        }
        if (this.region == null)
        {
            if (other.region != null)
            {
                return false;
            }
        } else if (!this.region.equals(other.region))
        {
            return false;
        }
        if (this.spectators == null)
        {
            if (other.spectators != null)
            {
                return false;
            }
        } else if (!this.spectators.equals(other.spectators))
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
        if (this.tournamentId == null)
        {
            if (other.tournamentId != null)
            {
                return false;
            }
        } else if (!this.tournamentId.equals(other.tournamentId))
        {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return "TournamentCode{" +
               "code='" + code + '\'' +
               ", id=" + id +
               ", lobbyName='" + lobbyName + '\'' +
               ", map='" + map + '\'' +
               ", metaData='" + metaData + '\'' +
               ", participants=" + participants +
               ", password='" + password + '\'' +
               ", pickType='" + pickType + '\'' +
               ", providerId=" + providerId +
               ", region='" + region + '\'' +
               ", spectators='" + spectators + '\'' +
               ", teamSize=" + teamSize +
               ", tournamentId=" + tournamentId +
               '}';
    }
}
