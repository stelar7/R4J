package no.stelar7.api.l4j8.pojo.league;

import no.stelar7.api.l4j8.basic.constants.RankedQueue;
import no.stelar7.api.l4j8.basic.constants.Tier;

import java.util.List;
import java.util.Optional;

class League
{

	private List<LeagueEntry> entries;
	private String            name;
	private String            participantId;
	private String            queue;
	private String            tier;

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
		final League other = (League) obj;
		if (this.entries == null)
		{
			if (other.entries != null)
			{
				return false;
			}
		} else if (!this.entries.equals(other.entries))
		{
			return false;
		}
		if (this.name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		} else if (!this.name.equals(other.name))
		{
			return false;
		}
		if (this.participantId == null)
		{
			if (other.participantId != null)
			{
				return false;
			}
		} else if (!this.participantId.equals(other.participantId))
		{
			return false;
		}
		if (this.queue == null)
		{
			if (other.queue != null)
			{
				return false;
			}
		} else if (!this.queue.equals(other.queue))
		{
			return false;
		}
		if (this.tier == null)
		{
			if (other.tier != null)
			{
				return false;
			}
		} else if (!this.tier.equals(other.tier))
		{
			return false;
		}
		return true;
	}

	/**
	 * The requested league entries.
	 *
	 * @return the entries
	 */
	public List<LeagueEntry> getEntries()
	{
		return this.entries;
	}

	/**
	 * This name is an internal place-holder name only. Display and localization of names in the game client are handled client-side.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Specifies the relevant participant that is a member of this league (i.e., a requested summoner ID, a requested team ID, or the ID of a team to which one of the requested summoners belongs). Only present when full league is requested so that participant's entry can be identified. Not present when individual entry is requested.
	 *
	 * @return the participant id
	 */
	public String getParticipantId()
	{
		return this.participantId;
	}

	/**
	 * The league's queue type. (Legal values: RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5)
	 *
	 * @return the queue
	 */
	public Optional<RankedQueue> getQueue()
	{
		return RankedQueue.getFromCode(this.queue);
	}

	/**
	 * The league's queue type. (Legal values: RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5)
	 *
	 * @return the queue
	 */
	public String getQueueString()
	{
		return this.queue;
	}

	/**
	 * The league's tier. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE)
	 *
	 * @return the tier
	 */
	public Tier getTier()
	{
		return Tier.getFromCode(this.tier);
	}

	/**
	 * The league's tier. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE)
	 *
	 * @return the tier
	 */
	public String getTierString()
	{
		return this.tier;
	}

	@Override
	public int hashCode()
	{
		final int prime  = 31;
		int       result = 1;
		result = (prime * result) + ((this.entries == null) ? 0 : this.entries.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.participantId == null) ? 0 : this.participantId.hashCode());
		result = (prime * result) + ((this.queue == null) ? 0 : this.queue.hashCode());
		result = (prime * result) + ((this.tier == null) ? 0 : this.tier.hashCode());
		return result;
	}
}
