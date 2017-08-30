package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;
import java.util.*;

public class MatchFrame implements Serializable
{
    private long                               timestamp;
    private Map<String, MatchParticipantFrame> participantFrames;
    private List<MatchEvent>                   events;
    
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
     * @return long
     */
    public long getTimestamp()
    {
        return this.timestamp;
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
        
        MatchFrame that = (MatchFrame) o;
        
        if (timestamp != that.timestamp)
        {
            return false;
        }
        if ((participantFrames != null) ? !participantFrames.equals(that.participantFrames) : (that.participantFrames != null))
        {
            return false;
        }
        return (events != null) ? events.equals(that.events) : (that.events == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (participantFrames != null ? participantFrames.hashCode() : 0);
        result = 31 * result + (events != null ? events.hashCode() : 0);
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
