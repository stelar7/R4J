package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.util.Map;

public class ParticipantTimeline
{
    private LaneType            lane;
    private Long                participantId;
    private Map<String, Double> csDiffPerMinDeltas;
    private Map<String, Double> goldPerMinDeltas;
    private Map<String, Double> xpDiffPerMinDeltas;
    private Map<String, Double> creepsPerMinDeltas;
    private Map<String, Double> xpPerMinDeltas;
    private RoleType            role;
    private Map<String, Double> damageTakenDiffPerMinDeltas;
    private Map<String, Double> damageTakenPerMinDeltas;
    
    /**
     * the lane the player was in
     *
     * @return LaneType
     */
    public LaneType getLane()
    {
        return lane;
    }
    
    
    /**
     * the participant id
     *
     * @return LaneType
     */
    public Long getParticipantId()
    {
        return participantId;
    }
    
    /**
     * valid keys are:
     * 0-10
     * 10-20
     * 20-30
     * 30-end
     *
     * @return map
     */
    public Map<String, Double> getCsDiffPerMinDeltas()
    {
        return csDiffPerMinDeltas;
    }
    
    
    /**
     * valid keys are:
     * 0-10
     * 10-20
     * 20-30
     * 30-end
     *
     * @return map
     */
    public Map<String, Double> getGoldPerMinDeltas()
    {
        return goldPerMinDeltas;
    }
    
    /**
     * valid keys are:
     * 0-10
     * 10-20
     * 20-30
     * 30-end
     *
     * @return map
     */
    public Map<String, Double> getXpDiffPerMinDeltas()
    {
        return xpDiffPerMinDeltas;
    }
    
    /**
     * valid keys are:
     * 0-10
     * 10-20
     * 20-30
     * 30-end
     *
     * @return map
     */
    public Map<String, Double> getCreepsPerMinDeltas()
    {
        return creepsPerMinDeltas;
    }
    
    /**
     * valid keys are:
     * 0-10
     * 10-20
     * 20-30
     * 30-end
     *
     * @return map
     */
    public Map<String, Double> getXpPerMinDeltas()
    {
        return xpPerMinDeltas;
    }
    
    /**
     * the role of the player
     *
     * @return RoleType
     */
    public RoleType getRole()
    {
        return role;
    }
    
    /**
     * valid keys are:
     * 0-10
     * 10-20
     * 20-30
     * 30-end
     *
     * @return map
     */
    public Map<String, Double> getDamageTakenDiffPerMinDeltas()
    {
        return damageTakenDiffPerMinDeltas;
    }
    
    /**
     * valid keys are:
     * 0-10
     * 10-20
     * 20-30
     * 30-end
     *
     * @return map
     */
    public Map<String, Double> getDamageTakenPerMinDeltas()
    {
        return damageTakenPerMinDeltas;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        ParticipantTimeline that = (ParticipantTimeline) o;
        
        if (lane != that.lane)
        {
            return false;
        }
        if ((participantId != null) ? !participantId.equals(that.participantId) : (that.participantId != null))
        {
            return false;
        }
        if ((csDiffPerMinDeltas != null) ? !csDiffPerMinDeltas.equals(that.csDiffPerMinDeltas) : (that.csDiffPerMinDeltas != null))
        {
            return false;
        }
        if ((goldPerMinDeltas != null) ? !goldPerMinDeltas.equals(that.goldPerMinDeltas) : (that.goldPerMinDeltas != null))
        {
            return false;
        }
        if ((xpDiffPerMinDeltas != null) ? !xpDiffPerMinDeltas.equals(that.xpDiffPerMinDeltas) : (that.xpDiffPerMinDeltas != null))
        {
            return false;
        }
        if ((creepsPerMinDeltas != null) ? !creepsPerMinDeltas.equals(that.creepsPerMinDeltas) : (that.creepsPerMinDeltas != null))
        {
            return false;
        }
        if ((xpPerMinDeltas != null) ? !xpPerMinDeltas.equals(that.xpPerMinDeltas) : (that.xpPerMinDeltas != null))
        {
            return false;
        }
        if (role != that.role)
        {
            return false;
        }
        if ((damageTakenDiffPerMinDeltas != null) ? !damageTakenDiffPerMinDeltas.equals(that.damageTakenDiffPerMinDeltas) : (that.damageTakenDiffPerMinDeltas != null))
        {
            return false;
        }
        return (damageTakenPerMinDeltas != null) ? damageTakenPerMinDeltas.equals(that.damageTakenPerMinDeltas) : (that.damageTakenPerMinDeltas == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = lane != null ? lane.hashCode() : 0;
        result = 31 * result + (participantId != null ? participantId.hashCode() : 0);
        result = 31 * result + (csDiffPerMinDeltas != null ? csDiffPerMinDeltas.hashCode() : 0);
        result = 31 * result + (goldPerMinDeltas != null ? goldPerMinDeltas.hashCode() : 0);
        result = 31 * result + (xpDiffPerMinDeltas != null ? xpDiffPerMinDeltas.hashCode() : 0);
        result = 31 * result + (creepsPerMinDeltas != null ? creepsPerMinDeltas.hashCode() : 0);
        result = 31 * result + (xpPerMinDeltas != null ? xpPerMinDeltas.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (damageTakenDiffPerMinDeltas != null ? damageTakenDiffPerMinDeltas.hashCode() : 0);
        result = 31 * result + (damageTakenPerMinDeltas != null ? damageTakenPerMinDeltas.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "ParticipantTimeline{" +
               "lane=" + lane +
               ", participantId=" + participantId +
               ", csDiffPerMinDeltas=" + csDiffPerMinDeltas +
               ", goldPerMinDeltas=" + goldPerMinDeltas +
               ", xpDiffPerMinDeltas=" + xpDiffPerMinDeltas +
               ", creepsPerMinDeltas=" + creepsPerMinDeltas +
               ", xpPerMinDeltas=" + xpPerMinDeltas +
               ", role=" + role +
               ", damageTakenDiffPerMinDeltas=" + damageTakenDiffPerMinDeltas +
               ", damageTakenPerMinDeltas=" + damageTakenPerMinDeltas +
               '}';
    }
}
