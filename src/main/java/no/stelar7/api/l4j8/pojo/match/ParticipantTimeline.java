package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.io.Serializable;
import java.util.*;

public class ParticipantTimeline implements Serializable
{
    private static final long serialVersionUID = -3577088635621988014L;
    
    private LaneType                       lane;
    private long                           participantId;
    private ParticipantTimelineDeltaMapMap csDiffPerMinDeltas;
    private ParticipantTimelineDeltaMapMap goldPerMinDeltas;
    private ParticipantTimelineDeltaMapMap xpDiffPerMinDeltas;
    private ParticipantTimelineDeltaMapMap creepsPerMinDeltas;
    private ParticipantTimelineDeltaMapMap xpPerMinDeltas;
    private RoleType                       role;
    private ParticipantTimelineDeltaMapMap damageTakenDiffPerMinDeltas;
    private ParticipantTimelineDeltaMapMap damageTakenPerMinDeltas;
    
    /**
     * the lane the player was in
     *
     * @return lane
     */
    public LaneType getLane()
    {
        return lane;
    }
    
    
    /**
     * the participant id
     *
     * @return participant id
     */
    public long getParticipantId()
    {
        return participantId;
    }
    
    /**
     * @return cs diff per min deltas
     */
    public ParticipantTimelineDeltaMapMap getCsDiffPerMinDeltas()
    {
        return csDiffPerMinDeltas;
    }
    
    
    /**
     * @return gold per min deltas
     */
    public ParticipantTimelineDeltaMapMap getGoldPerMinDeltas()
    {
        return goldPerMinDeltas;
    }
    
    /**
     * @return xp diff per min deltas
     */
    public ParticipantTimelineDeltaMapMap getXpDiffPerMinDeltas()
    {
        return xpDiffPerMinDeltas;
    }
    
    /**
     * @return creeps per min deltas
     */
    public ParticipantTimelineDeltaMapMap getCreepsPerMinDeltas()
    {
        return creepsPerMinDeltas;
    }
    
    /**
     * @return xp per min deltas
     */
    public ParticipantTimelineDeltaMapMap getXpPerMinDeltas()
    {
        return xpPerMinDeltas;
    }
    
    /**
     * the role of the player
     *
     * @return role
     */
    public RoleType getRole()
    {
        return role;
    }
    
    /**
     * @return damage taken diff per min deltas
     */
    public ParticipantTimelineDeltaMapMap getDamageTakenDiffPerMinDeltas()
    {
        return damageTakenDiffPerMinDeltas;
    }
    
    /**
     * @return damage taken per min deltas
     */
    public ParticipantTimelineDeltaMapMap getDamageTakenPerMinDeltas()
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
        return participantId == that.participantId &&
               lane == that.lane &&
               Objects.equals(csDiffPerMinDeltas, that.csDiffPerMinDeltas) &&
               Objects.equals(goldPerMinDeltas, that.goldPerMinDeltas) &&
               Objects.equals(xpDiffPerMinDeltas, that.xpDiffPerMinDeltas) &&
               Objects.equals(creepsPerMinDeltas, that.creepsPerMinDeltas) &&
               Objects.equals(xpPerMinDeltas, that.xpPerMinDeltas) &&
               role == that.role &&
               Objects.equals(damageTakenDiffPerMinDeltas, that.damageTakenDiffPerMinDeltas) &&
               Objects.equals(damageTakenPerMinDeltas, that.damageTakenPerMinDeltas);
    }
    
    @Override
    public int hashCode()
    {
        
        return Objects.hash(lane, participantId, csDiffPerMinDeltas, goldPerMinDeltas, xpDiffPerMinDeltas, creepsPerMinDeltas, xpPerMinDeltas, role, damageTakenDiffPerMinDeltas, damageTakenPerMinDeltas);
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
