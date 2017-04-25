package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.*;

public class ParticipantTimeline
{
    private LaneType                lane;
    private Long                    participantId;
    private ParticipantTimelineData csDiffPerMinDeltas;
    private ParticipantTimelineData goldPerMinDeltas;
    private ParticipantTimelineData xpDiffPerMinDeltas;
    private ParticipantTimelineData creepsPerMinDeltas;
    private ParticipantTimelineData xpPerMinDeltas;
    private RoleType                role;
    private ParticipantTimelineData damageTakenDiffPerMinDeltas;
    private ParticipantTimelineData damageTakenPerMinDeltas;
    
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
     * Gets the gold per min deltas.
     *
     * @return the gold per min deltas
     */
    public ParticipantTimelineData getGoldPerMinDeltas()
    {
        return this.goldPerMinDeltas;
    }
    
    
    /**
     * Gets the lane.
     *
     * @return the lane
     */
    public LaneType getLane()
    {
        return this.lane;
    }
    
    
    /**
     * Gets the role.
     *
     * @return the role
     */
    public RoleType getRole()
    {
        return this.role;
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
        result = (prime * result) + ((this.creepsPerMinDeltas == null) ? 0 : this.creepsPerMinDeltas.hashCode());
        result = (prime * result) + ((this.csDiffPerMinDeltas == null) ? 0 : this.csDiffPerMinDeltas.hashCode());
        result = (prime * result) + ((this.damageTakenDiffPerMinDeltas == null) ? 0 : this.damageTakenDiffPerMinDeltas.hashCode());
        result = (prime * result) + ((this.damageTakenPerMinDeltas == null) ? 0 : this.damageTakenPerMinDeltas.hashCode());
        result = (prime * result) + ((this.goldPerMinDeltas == null) ? 0 : this.goldPerMinDeltas.hashCode());
        result = (prime * result) + ((this.lane == null) ? 0 : this.lane.hashCode());
        result = (prime * result) + ((this.role == null) ? 0 : this.role.hashCode());
        result = (prime * result) + ((this.xpDiffPerMinDeltas == null) ? 0 : this.xpDiffPerMinDeltas.hashCode());
        result = (prime * result) + ((this.xpPerMinDeltas == null) ? 0 : this.xpPerMinDeltas.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ParticipantTimeline{" +
               ", creepsPerMinDeltas=" + creepsPerMinDeltas +
               ", csDiffPerMinDeltas=" + csDiffPerMinDeltas +
               ", damageTakenDiffPerMinDeltas=" + damageTakenDiffPerMinDeltas +
               ", damageTakenPerMinDeltas=" + damageTakenPerMinDeltas +
               ", goldPerMinDeltas=" + goldPerMinDeltas +
               ", xpDiffPerMinDeltas=" + xpDiffPerMinDeltas +
               ", xpPerMinDeltas=" + xpPerMinDeltas +
               ", lane='" + lane + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}
