package no.stelar7.api.l4j8.pojo.match;

import java.util.*;

public class MatchFrame
{
    private Long                               timestamp;
    private Map<String, MatchParticipantFrame> participantFrames;
    private List<MatchEvent>                   events;
    
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
        final MatchFrame other = (MatchFrame) obj;
        if (this.events == null)
        {
            if (other.events != null)
            {
                return false;
            }
        } else if (!this.events.equals(other.events))
        {
            return false;
        }
        if (this.participantFrames == null)
        {
            if (other.participantFrames != null)
            {
                return false;
            }
        } else if (!this.participantFrames.equals(other.participantFrames))
        {
            return false;
        }
        if (this.timestamp == null)
        {
            if (other.timestamp != null)
            {
                return false;
            }
        } else if (!this.timestamp.equals(other.timestamp))
        {
            return false;
        }
        return true;
    }
    
    /**
     * List of events for this frame.
     *
     * @return {@code List<MatchEvent>}
     */
    public List<MatchEvent> getEvents()
    {
        return this.events;
    }
    
    /**
     * Map of each participant ID to the participant's information for the frame.
     *
     * @return {@code Map<String, MatchParticipantFrame> }
     */
    public Map<String, MatchParticipantFrame> getParticipantFrames()
    {
        return this.participantFrames;
    }
    
    /**
     * Represents how many milliseconds into the game the frame occurred.
     *
     * @return Long
     */
    public Long getTimestamp()
    {
        return this.timestamp;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.events == null) ? 0 : this.events.hashCode());
        result = (prime * result) + ((this.participantFrames == null) ? 0 : this.participantFrames.hashCode());
        result = (prime * result) + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchFrame{" +
               "events=" + events +
               ", participantFrames=" + participantFrames +
               ", timestamp=" + timestamp +
               '}';
    }
}
