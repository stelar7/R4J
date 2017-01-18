package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.*;

import java.util.*;

public class ParticipantTimeline
{
	
	private ParticipantTimelineData ancientGolemAssistsPerMinCounts;
	private ParticipantTimelineData ancientGolemKillsPerMinCounts;
	private ParticipantTimelineData assistedLaneDeathsPerMinDeltas;
	private ParticipantTimelineData assistedLaneKillsPerMinDeltas;
	private ParticipantTimelineData baronAssistsPerMinCounts;
	private ParticipantTimelineData baronKillsPerMinCounts;
	private ParticipantTimelineData creepsPerMinDeltas;
	private ParticipantTimelineData csDiffPerMinDeltas;
	private ParticipantTimelineData damageTakenDiffPerMinDeltas;
	private ParticipantTimelineData damageTakenPerMinDeltas;
	private ParticipantTimelineData dragonAssistsPerMinCounts;
	private ParticipantTimelineData dragonKillsPerMinCounts;
	private ParticipantTimelineData elderLizardAssistsPerMinCounts;
	private ParticipantTimelineData elderLizardKillsPerMinCounts;
	private ParticipantTimelineData goldPerMinDeltas;
	private ParticipantTimelineData inhibitorAssistsPerMinCounts;
	private ParticipantTimelineData inhibitorKillsPerMinCounts;
	private ParticipantTimelineData towerAssistsPerMinCounts;
	private ParticipantTimelineData towerKillsPerMinCounts;
	private ParticipantTimelineData towerKillsPerMinDeltas;
	private ParticipantTimelineData vilemawAssistsPerMinCounts;
	private ParticipantTimelineData vilemawKillsPerMinCounts;
	private ParticipantTimelineData wardsPerMinDeltas;
	private ParticipantTimelineData xpDiffPerMinDeltas;
	private ParticipantTimelineData xpPerMinDeltas;
	private String                  lane;
	private String                  role;
	
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
		final ParticipantTimeline other = (ParticipantTimeline) obj;
		if (this.ancientGolemAssistsPerMinCounts == null)
		{
			if (other.ancientGolemAssistsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.ancientGolemAssistsPerMinCounts.equals(other.ancientGolemAssistsPerMinCounts))
		{
			return false;
		}
		if (this.ancientGolemKillsPerMinCounts == null)
		{
			if (other.ancientGolemKillsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.ancientGolemKillsPerMinCounts.equals(other.ancientGolemKillsPerMinCounts))
		{
			return false;
		}
		if (this.assistedLaneDeathsPerMinDeltas == null)
		{
			if (other.assistedLaneDeathsPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.assistedLaneDeathsPerMinDeltas.equals(other.assistedLaneDeathsPerMinDeltas))
		{
			return false;
		}
		if (this.assistedLaneKillsPerMinDeltas == null)
		{
			if (other.assistedLaneKillsPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.assistedLaneKillsPerMinDeltas.equals(other.assistedLaneKillsPerMinDeltas))
		{
			return false;
		}
		if (this.baronAssistsPerMinCounts == null)
		{
			if (other.baronAssistsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.baronAssistsPerMinCounts.equals(other.baronAssistsPerMinCounts))
		{
			return false;
		}
		if (this.baronKillsPerMinCounts == null)
		{
			if (other.baronKillsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.baronKillsPerMinCounts.equals(other.baronKillsPerMinCounts))
		{
			return false;
		}
		if (this.creepsPerMinDeltas == null)
		{
			if (other.creepsPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.creepsPerMinDeltas.equals(other.creepsPerMinDeltas))
		{
			return false;
		}
		if (this.csDiffPerMinDeltas == null)
		{
			if (other.csDiffPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.csDiffPerMinDeltas.equals(other.csDiffPerMinDeltas))
		{
			return false;
		}
		if (this.damageTakenDiffPerMinDeltas == null)
		{
			if (other.damageTakenDiffPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.damageTakenDiffPerMinDeltas.equals(other.damageTakenDiffPerMinDeltas))
		{
			return false;
		}
		if (this.damageTakenPerMinDeltas == null)
		{
			if (other.damageTakenPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.damageTakenPerMinDeltas.equals(other.damageTakenPerMinDeltas))
		{
			return false;
		}
		if (this.dragonAssistsPerMinCounts == null)
		{
			if (other.dragonAssistsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.dragonAssistsPerMinCounts.equals(other.dragonAssistsPerMinCounts))
		{
			return false;
		}
		if (this.dragonKillsPerMinCounts == null)
		{
			if (other.dragonKillsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.dragonKillsPerMinCounts.equals(other.dragonKillsPerMinCounts))
		{
			return false;
		}
		if (this.elderLizardAssistsPerMinCounts == null)
		{
			if (other.elderLizardAssistsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.elderLizardAssistsPerMinCounts.equals(other.elderLizardAssistsPerMinCounts))
		{
			return false;
		}
		if (this.elderLizardKillsPerMinCounts == null)
		{
			if (other.elderLizardKillsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.elderLizardKillsPerMinCounts.equals(other.elderLizardKillsPerMinCounts))
		{
			return false;
		}
		if (this.goldPerMinDeltas == null)
		{
			if (other.goldPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.goldPerMinDeltas.equals(other.goldPerMinDeltas))
		{
			return false;
		}
		if (this.inhibitorAssistsPerMinCounts == null)
		{
			if (other.inhibitorAssistsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.inhibitorAssistsPerMinCounts.equals(other.inhibitorAssistsPerMinCounts))
		{
			return false;
		}
		if (this.inhibitorKillsPerMinCounts == null)
		{
			if (other.inhibitorKillsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.inhibitorKillsPerMinCounts.equals(other.inhibitorKillsPerMinCounts))
		{
			return false;
		}
		if (this.lane == null)
		{
			if (other.lane != null)
			{
				return false;
			}
		} else if (!this.lane.equals(other.lane))
		{
			return false;
		}
		if (this.role == null)
		{
			if (other.role != null)
			{
				return false;
			}
		} else if (!this.role.equals(other.role))
		{
			return false;
		}
		if (this.towerAssistsPerMinCounts == null)
		{
			if (other.towerAssistsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.towerAssistsPerMinCounts.equals(other.towerAssistsPerMinCounts))
		{
			return false;
		}
		if (this.towerKillsPerMinCounts == null)
		{
			if (other.towerKillsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.towerKillsPerMinCounts.equals(other.towerKillsPerMinCounts))
		{
			return false;
		}
		if (this.towerKillsPerMinDeltas == null)
		{
			if (other.towerKillsPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.towerKillsPerMinDeltas.equals(other.towerKillsPerMinDeltas))
		{
			return false;
		}
		if (this.vilemawAssistsPerMinCounts == null)
		{
			if (other.vilemawAssistsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.vilemawAssistsPerMinCounts.equals(other.vilemawAssistsPerMinCounts))
		{
			return false;
		}
		if (this.vilemawKillsPerMinCounts == null)
		{
			if (other.vilemawKillsPerMinCounts != null)
			{
				return false;
			}
		} else if (!this.vilemawKillsPerMinCounts.equals(other.vilemawKillsPerMinCounts))
		{
			return false;
		}
		if (this.wardsPerMinDeltas == null)
		{
			if (other.wardsPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.wardsPerMinDeltas.equals(other.wardsPerMinDeltas))
		{
			return false;
		}
		if (this.xpDiffPerMinDeltas == null)
		{
			if (other.xpDiffPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.xpDiffPerMinDeltas.equals(other.xpDiffPerMinDeltas))
		{
			return false;
		}
		if (this.xpPerMinDeltas == null)
		{
			if (other.xpPerMinDeltas != null)
			{
				return false;
			}
		} else if (!this.xpPerMinDeltas.equals(other.xpPerMinDeltas))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the ancient golem assists per min counts.
	 *
	 * @return the ancient golem assists per min counts
	 */
	public ParticipantTimelineData getAncientGolemAssistsPerMinCounts()
	{
		return this.ancientGolemAssistsPerMinCounts;
	}
	
	/**
	 * Gets the ancient golem kills per min counts.
	 *
	 * @return the ancient golem kills per min counts
	 */
	public ParticipantTimelineData getAncientGolemKillsPerMinCounts()
	{
		return this.ancientGolemKillsPerMinCounts;
	}
	
	/**
	 * Gets the assisted lane deaths per min deltas.
	 *
	 * @return the assisted lane deaths per min deltas
	 */
	public ParticipantTimelineData getAssistedLaneDeathsPerMinDeltas()
	{
		return this.assistedLaneDeathsPerMinDeltas;
	}
	
	/**
	 * Gets the assisted lane kills per min deltas.
	 *
	 * @return the assisted lane kills per min deltas
	 */
	public ParticipantTimelineData getAssistedLaneKillsPerMinDeltas()
	{
		return this.assistedLaneKillsPerMinDeltas;
	}
	
	/**
	 * Gets the baron assists per min counts.
	 *
	 * @return the baron assists per min counts
	 */
	public ParticipantTimelineData getBaronAssistsPerMinCounts()
	{
		return this.baronAssistsPerMinCounts;
	}
	
	/**
	 * Gets the baron kills per min counts.
	 *
	 * @return the baron kills per min counts
	 */
	public ParticipantTimelineData getBaronKillsPerMinCounts()
	{
		return this.baronKillsPerMinCounts;
	}
	
	/**
	 * Gets the creeps per min deltas.
	 *
	 * @return the creeps per min deltas
	 */
	public ParticipantTimelineData getCreepsPerMinDeltas()
	{
		return this.creepsPerMinDeltas;
	}
	
	/**
	 * Gets the cs diff per min deltas.
	 *
	 * @return the cs diff per min deltas
	 */
	public ParticipantTimelineData getCsDiffPerMinDeltas()
	{
		return this.csDiffPerMinDeltas;
	}
	
	/**
	 * Gets the damage taken diff per min deltas.
	 *
	 * @return the damage taken diff per min deltas
	 */
	public ParticipantTimelineData getDamageTakenDiffPerMinDeltas()
	{
		return this.damageTakenDiffPerMinDeltas;
	}
	
	/**
	 * Gets the damage taken per min deltas.
	 *
	 * @return the damage taken per min deltas
	 */
	public ParticipantTimelineData getDamageTakenPerMinDeltas()
	{
		return this.damageTakenPerMinDeltas;
	}
	
	/**
	 * Gets the dragon assists per min counts.
	 *
	 * @return the dragon assists per min counts
	 */
	public ParticipantTimelineData getDragonAssistsPerMinCounts()
	{
		return this.dragonAssistsPerMinCounts;
	}
	
	/**
	 * Gets the dragon kills per min counts.
	 *
	 * @return the dragon kills per min counts
	 */
	public ParticipantTimelineData getDragonKillsPerMinCounts()
	{
		return this.dragonKillsPerMinCounts;
	}
	
	/**
	 * Gets the elder lizard assists per min counts.
	 *
	 * @return the elder lizard assists per min counts
	 */
	public ParticipantTimelineData getElderLizardAssistsPerMinCounts()
	{
		return this.elderLizardAssistsPerMinCounts;
	}
	
	/**
	 * Gets the elder lizard kills per min counts.
	 *
	 * @return the elder lizard kills per min counts
	 */
	public ParticipantTimelineData getElderLizardKillsPerMinCounts()
	{
		return this.elderLizardKillsPerMinCounts;
	}
	
	/**
	 * Gets the gold per min deltas.
	 *
	 * @return the gold per min deltas
	 */
	public ParticipantTimelineData getGoldPerMinDeltas()
	{
		return this.goldPerMinDeltas;
	}
	
	/**
	 * Gets the inhibitor assists per min counts.
	 *
	 * @return the inhibitor assists per min counts
	 */
	public ParticipantTimelineData getInhibitorAssistsPerMinCounts()
	{
		return this.inhibitorAssistsPerMinCounts;
	}
	
	/**
	 * Gets the inhibitor kills per min counts.
	 *
	 * @return the inhibitor kills per min counts
	 */
	public ParticipantTimelineData getInhibitorKillsPerMinCounts()
	{
		return this.inhibitorKillsPerMinCounts;
	}
	
	/**
	 * Gets the lane.
	 *
	 * @return the lane
	 */
	public Optional<Lane> getLane()
	{
		return Lane.getFromCode(this.lane);
	}
	
	/**
	 * Gets the lane.
	 *
	 * @return the lane
	 */
	public String getLaneId()
	{
		return this.lane;
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Optional<Role> getRole()
	{
		return Role.getFromCode(this.role);
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRoleId()
	{
		return this.role;
	}
	
	/**
	 * Gets the tower assists per min counts.
	 *
	 * @return the tower assists per min counts
	 */
	public ParticipantTimelineData getTowerAssistsPerMinCounts()
	{
		return this.towerAssistsPerMinCounts;
	}
	
	/**
	 * Gets the tower kills per min counts.
	 *
	 * @return the tower kills per min counts
	 */
	public ParticipantTimelineData getTowerKillsPerMinCounts()
	{
		return this.towerKillsPerMinCounts;
	}
	
	/**
	 * Gets the tower kills per min deltas.
	 *
	 * @return the tower kills per min deltas
	 */
	public ParticipantTimelineData getTowerKillsPerMinDeltas()
	{
		return this.towerKillsPerMinDeltas;
	}
	
	/**
	 * Gets the vilemaw assists per min counts.
	 *
	 * @return the vilemaw assists per min counts
	 */
	public ParticipantTimelineData getVilemawAssistsPerMinCounts()
	{
		return this.vilemawAssistsPerMinCounts;
	}
	
	/**
	 * Gets the vilemaw kills per min counts.
	 *
	 * @return the vilemaw kills per min counts
	 */
	public ParticipantTimelineData getVilemawKillsPerMinCounts()
	{
		return this.vilemawKillsPerMinCounts;
	}
	
	/**
	 * Gets the wards per min deltas.
	 *
	 * @return the wards per min deltas
	 */
	public ParticipantTimelineData getWardsPerMinDeltas()
	{
		return this.wardsPerMinDeltas;
	}
	
	/**
	 * Gets the xp diff per min deltas.
	 *
	 * @return the xp diff per min deltas
	 */
	public ParticipantTimelineData getXpDiffPerMinDeltas()
	{
		return this.xpDiffPerMinDeltas;
	}
	
	/**
	 * Gets the xp per min deltas.
	 *
	 * @return the xp per min deltas
	 */
	public ParticipantTimelineData getXpPerMinDeltas()
	{
		return this.xpPerMinDeltas;
	}
	
	@Override
	public int hashCode()
	{
		final int prime  = 31;
		int       result = 1;
		result = (prime * result) + ((this.ancientGolemAssistsPerMinCounts == null) ? 0 : this.ancientGolemAssistsPerMinCounts.hashCode());
		result = (prime * result) + ((this.ancientGolemKillsPerMinCounts == null) ? 0 : this.ancientGolemKillsPerMinCounts.hashCode());
		result = (prime * result) + ((this.assistedLaneDeathsPerMinDeltas == null) ? 0 : this.assistedLaneDeathsPerMinDeltas.hashCode());
		result = (prime * result) + ((this.assistedLaneKillsPerMinDeltas == null) ? 0 : this.assistedLaneKillsPerMinDeltas.hashCode());
		result = (prime * result) + ((this.baronAssistsPerMinCounts == null) ? 0 : this.baronAssistsPerMinCounts.hashCode());
		result = (prime * result) + ((this.baronKillsPerMinCounts == null) ? 0 : this.baronKillsPerMinCounts.hashCode());
		result = (prime * result) + ((this.creepsPerMinDeltas == null) ? 0 : this.creepsPerMinDeltas.hashCode());
		result = (prime * result) + ((this.csDiffPerMinDeltas == null) ? 0 : this.csDiffPerMinDeltas.hashCode());
		result = (prime * result) + ((this.damageTakenDiffPerMinDeltas == null) ? 0 : this.damageTakenDiffPerMinDeltas.hashCode());
		result = (prime * result) + ((this.damageTakenPerMinDeltas == null) ? 0 : this.damageTakenPerMinDeltas.hashCode());
		result = (prime * result) + ((this.dragonAssistsPerMinCounts == null) ? 0 : this.dragonAssistsPerMinCounts.hashCode());
		result = (prime * result) + ((this.dragonKillsPerMinCounts == null) ? 0 : this.dragonKillsPerMinCounts.hashCode());
		result = (prime * result) + ((this.elderLizardAssistsPerMinCounts == null) ? 0 : this.elderLizardAssistsPerMinCounts.hashCode());
		result = (prime * result) + ((this.elderLizardKillsPerMinCounts == null) ? 0 : this.elderLizardKillsPerMinCounts.hashCode());
		result = (prime * result) + ((this.goldPerMinDeltas == null) ? 0 : this.goldPerMinDeltas.hashCode());
		result = (prime * result) + ((this.inhibitorAssistsPerMinCounts == null) ? 0 : this.inhibitorAssistsPerMinCounts.hashCode());
		result = (prime * result) + ((this.inhibitorKillsPerMinCounts == null) ? 0 : this.inhibitorKillsPerMinCounts.hashCode());
		result = (prime * result) + ((this.lane == null) ? 0 : this.lane.hashCode());
		result = (prime * result) + ((this.role == null) ? 0 : this.role.hashCode());
		result = (prime * result) + ((this.towerAssistsPerMinCounts == null) ? 0 : this.towerAssistsPerMinCounts.hashCode());
		result = (prime * result) + ((this.towerKillsPerMinCounts == null) ? 0 : this.towerKillsPerMinCounts.hashCode());
		result = (prime * result) + ((this.towerKillsPerMinDeltas == null) ? 0 : this.towerKillsPerMinDeltas.hashCode());
		result = (prime * result) + ((this.vilemawAssistsPerMinCounts == null) ? 0 : this.vilemawAssistsPerMinCounts.hashCode());
		result = (prime * result) + ((this.vilemawKillsPerMinCounts == null) ? 0 : this.vilemawKillsPerMinCounts.hashCode());
		result = (prime * result) + ((this.wardsPerMinDeltas == null) ? 0 : this.wardsPerMinDeltas.hashCode());
		result = (prime * result) + ((this.xpDiffPerMinDeltas == null) ? 0 : this.xpDiffPerMinDeltas.hashCode());
		result = (prime * result) + ((this.xpPerMinDeltas == null) ? 0 : this.xpPerMinDeltas.hashCode());
		return result;
	}
	
	@Override
	public String toString()
	{
		return "ParticipantTimeline [ancientGolemAssistsPerMinCounts=" + this.ancientGolemAssistsPerMinCounts + ", ancientGolemKillsPerMinCounts=" + this.ancientGolemKillsPerMinCounts + ", assistedLaneDeathsPerMinDeltas=" + this.assistedLaneDeathsPerMinDeltas + ", assistedLaneKillsPerMinDeltas=" + this.assistedLaneKillsPerMinDeltas + ", baronAssistsPerMinCounts=" + this.baronAssistsPerMinCounts + ", baronKillsPerMinCounts=" + this.baronKillsPerMinCounts + ", creepsPerMinDeltas=" + this.creepsPerMinDeltas + ", csDiffPerMinDeltas=" + this.csDiffPerMinDeltas + ", damageTakenDiffPerMinDeltas=" + this.damageTakenDiffPerMinDeltas + ", damageTakenPerMinDeltas=" + this.damageTakenPerMinDeltas + ", dragonAssistsPerMinCounts=" + this.dragonAssistsPerMinCounts + ", dragonKillsPerMinCounts=" + this.dragonKillsPerMinCounts + ", elderLizardAssistsPerMinCounts=" + this.elderLizardAssistsPerMinCounts + ", elderLizardKillsPerMinCounts=" + this.elderLizardKillsPerMinCounts + ", goldPerMinDeltas=" + this.goldPerMinDeltas + ", inhibitorAssistsPerMinCounts=" + this.inhibitorAssistsPerMinCounts + ", inhibitorKillsPerMinCounts=" + this.inhibitorKillsPerMinCounts + ", towerAssistsPerMinCounts=" + this.towerAssistsPerMinCounts + ", towerKillsPerMinCounts=" + this.towerKillsPerMinCounts + ", towerKillsPerMinDeltas=" + this.towerKillsPerMinDeltas + ", vilemawAssistsPerMinCounts=" + this.vilemawAssistsPerMinCounts + ", vilemawKillsPerMinCounts=" + this.vilemawKillsPerMinCounts + ", wardsPerMinDeltas=" + this.wardsPerMinDeltas + ", xpDiffPerMinDeltas=" + this.xpDiffPerMinDeltas + ", xpPerMinDeltas=" + this.xpPerMinDeltas + ", lane=" + this.lane + ", role=" + this.role + "]";
	}
	
}
