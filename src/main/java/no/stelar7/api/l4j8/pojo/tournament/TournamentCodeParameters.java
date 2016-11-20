package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.*;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
		// GSON needs an empty constructor to initialize.. i think..?
	}

	public TournamentCodeParameters(final TournamentCodeUpdateParameters updateParams, final String metadata, final Integer teamSize)
	{
		this.allowedSummonerIds = new SummonerIdParams(Pattern.compile(",")
		                                                      .splitAsStream(updateParams.allowedParticipants)
		                                                      .map(Long::parseLong)
		                                                      .collect(Collectors.toSet()));
		this.mapType = updateParams.mapType;
		this.metadata = metadata;
		this.pickType = updateParams.pickType;
		this.spectatorType = updateParams.spectatorType;
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

	public SummonerIdParams getAllowedSummonerIds()
	{
		return this.allowedSummonerIds;
	}

	public TournamentMapType getMapType()
	{
		return this.mapType;
	}

	public String getMetadata()
	{
		return this.metadata;
	}

	public TournamentPickType getPickType()
	{
		return this.pickType;
	}

	public TournamentSpectatorType getSpectatorType()
	{
		return this.spectatorType;
	}

	public Integer getTeamSize()
	{
		return this.teamSize;
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

	public void setAllowedSummonerIds(final SummonerIdParams allowedSummonerIds)
	{
		this.allowedSummonerIds = allowedSummonerIds;
	}

	public void setMapType(final TournamentMapType mapType)
	{
		this.mapType = mapType;
	}

	public void setMetadata(final String metadata)
	{
		this.metadata = metadata;
	}

	public void setPickType(final TournamentPickType pickType)
	{
		this.pickType = pickType;
	}

	public void setSpectatorType(final TournamentSpectatorType spectatorType)
	{
		this.spectatorType = spectatorType;
	}

	public void setTeamSize(final Integer teamSize)
	{
		this.teamSize = teamSize;
	}

	@Override
	public String toString()
	{
		return "TournamentCodeParameters [allowedSummonerIds=" + this.allowedSummonerIds + ", mapType=" + this.mapType + ", metadata=" + this.metadata + ", pickType=" + this.pickType + ", spectatorType=" + this.spectatorType + ", teamSize=" + this.teamSize + "]";
	}

}
