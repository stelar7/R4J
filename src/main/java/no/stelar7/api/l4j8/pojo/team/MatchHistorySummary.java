package no.stelar7.api.l4j8.pojo.team;

import java.time.*;

class MatchHistorySummary
{
	private Integer assists;
	private Integer deaths;
	private Integer kills;
	private Integer mapId;
	private Integer opposingTeamKills;
	private Long    date;
	private Boolean invalid;
	private String  gameMode;
	private Long    gameId;
	private String  opposingTeamName;
	private Boolean win;

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
		final MatchHistorySummary other = (MatchHistorySummary) obj;
		if (this.assists == null)
		{
			if (other.assists != null)
			{
				return false;
			}
		} else if (!this.assists.equals(other.assists))
		{
			return false;
		}
		if (this.date == null)
		{
			if (other.date != null)
			{
				return false;
			}
		} else if (!this.date.equals(other.date))
		{
			return false;
		}
		if (this.deaths == null)
		{
			if (other.deaths != null)
			{
				return false;
			}
		} else if (!this.deaths.equals(other.deaths))
		{
			return false;
		}
		if (this.gameId == null)
		{
			if (other.gameId != null)
			{
				return false;
			}
		} else if (!this.gameId.equals(other.gameId))
		{
			return false;
		}
		if (this.gameMode == null)
		{
			if (other.gameMode != null)
			{
				return false;
			}
		} else if (!this.gameMode.equals(other.gameMode))
		{
			return false;
		}
		if (this.invalid == null)
		{
			if (other.invalid != null)
			{
				return false;
			}
		} else if (!this.invalid.equals(other.invalid))
		{
			return false;
		}
		if (this.kills == null)
		{
			if (other.kills != null)
			{
				return false;
			}
		} else if (!this.kills.equals(other.kills))
		{
			return false;
		}
		if (this.mapId == null)
		{
			if (other.mapId != null)
			{
				return false;
			}
		} else if (!this.mapId.equals(other.mapId))
		{
			return false;
		}
		if (this.opposingTeamKills == null)
		{
			if (other.opposingTeamKills != null)
			{
				return false;
			}
		} else if (!this.opposingTeamKills.equals(other.opposingTeamKills))
		{
			return false;
		}
		if (this.opposingTeamName == null)
		{
			if (other.opposingTeamName != null)
			{
				return false;
			}
		} else if (!this.opposingTeamName.equals(other.opposingTeamName))
		{
			return false;
		}
		if (this.win == null)
		{
			if (other.win != null)
			{
				return false;
			}
		} else if (!this.win.equals(other.win))
		{
			return false;
		}
		return true;
	}

	/**
	 * Gets the assists.
	 *
	 * @return the assists
	 */
	public Integer getAssists()
	{
		return this.assists;
	}

	/**
	 * Date that match was completed specified as epoch milliseconds.
	 *
	 * @return the date
	 */
	public Long getDate()
	{
		return this.date;
	}

	/**
	 * A ZonedDateTime of {@code getDate()}
	 * <p>
	 * * @return ZonedDateTime
	 */
	public ZonedDateTime getDateAsDate()
	{
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.date), ZoneOffset.UTC);
	}

	/**
	 * Gets the deaths.
	 *
	 * @return the deaths
	 */
	public Integer getDeaths()
	{
		return this.deaths;
	}

	/**
	 * Gets the game id.
	 *
	 * @return the game id
	 */
	public Long getGameId()
	{
		return this.gameId;
	}

	/**
	 * Gets the game mode.
	 *
	 * @return the game mode
	 */
	public String getGameMode()
	{
		return this.gameMode;
	}

	/**
	 * Gets the invalid.
	 *
	 * @return the invalid
	 */
	public Boolean getInvalid()
	{
		return this.invalid;
	}

	/**
	 * Gets the kills.
	 *
	 * @return the kills
	 */
	public Integer getKills()
	{
		return this.kills;
	}

	/**
	 * Gets the map id.
	 *
	 * @return the map id
	 */
	public Integer getMapId()
	{
		return this.mapId;
	}

	/**
	 * Gets the opposing team kills.
	 *
	 * @return the opposing team kills
	 */
	public Integer getOpposingTeamKills()
	{
		return this.opposingTeamKills;
	}

	/**
	 * Gets the opposing team name.
	 *
	 * @return the opposing team name
	 */
	public String getOpposingTeamName()
	{
		return this.opposingTeamName;
	}

	/**
	 * Gets the win.
	 *
	 * @return the win
	 */
	public Boolean getWin()
	{
		return this.win;
	}

	@Override
	public int hashCode()
	{
		final int prime  = 31;
		int       result = 1;
		result = (prime * result) + ((this.assists == null) ? 0 : this.assists.hashCode());
		result = (prime * result) + ((this.date == null) ? 0 : this.date.hashCode());
		result = (prime * result) + ((this.deaths == null) ? 0 : this.deaths.hashCode());
		result = (prime * result) + ((this.gameId == null) ? 0 : this.gameId.hashCode());
		result = (prime * result) + ((this.gameMode == null) ? 0 : this.gameMode.hashCode());
		result = (prime * result) + ((this.invalid == null) ? 0 : this.invalid.hashCode());
		result = (prime * result) + ((this.kills == null) ? 0 : this.kills.hashCode());
		result = (prime * result) + ((this.mapId == null) ? 0 : this.mapId.hashCode());
		result = (prime * result) + ((this.opposingTeamKills == null) ? 0 : this.opposingTeamKills.hashCode());
		result = (prime * result) + ((this.opposingTeamName == null) ? 0 : this.opposingTeamName.hashCode());
		result = (prime * result) + ((this.win == null) ? 0 : this.win.hashCode());
		return result;
	}

}
