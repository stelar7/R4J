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
	
	public String getCode()
	{
		return this.code;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public String getLobbyName()
	{
		return this.lobbyName;
	}
	
	public String getMap()
	{
		return this.map;
	}
	
	public String getMetaData()
	{
		return this.metaData;
	}
	
	public Set<Long> getParticipants()
	{
		return this.participants;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getPickType()
	{
		return this.pickType;
	}
	
	public Integer getProviderId()
	{
		return this.providerId;
	}
	
	public String getRegion()
	{
		return this.region;
	}
	
	public String getSpectators()
	{
		return this.spectators;
	}
	
	public Integer getTeamSize()
	{
		return this.teamSize;
	}
	
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
	public String toString()
	{
		return "TournamentCode [code=" + this.code + ", id=" + this.id + ", lobbyName=" + this.lobbyName + ", map=" + this.map + ", metaData=" + this.metaData + ", participants=" + this.participants + ", password=" + this.password + ", pickType=" + this.pickType + ", providerId=" + this.providerId + ", region=" + this.region + ", spectators=" + this.spectators + ", teamSize=" + this.teamSize + ", tournamentId=" + this.tournamentId + "]";
	}
}
