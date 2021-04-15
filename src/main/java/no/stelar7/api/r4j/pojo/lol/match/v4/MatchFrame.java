package no.stelar7.api.r4j.pojo.lol.match.v4;

import java.io.Serializable;
import java.util.*;

public class MatchFrame implements Serializable
{
    private static final long serialVersionUID = 9214624443412276664L;
    
    private long                                timestamp;
    private Map<Integer, MatchParticipantFrame> participantFrames;
    private List<MatchEvent>                    events;
    
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
     * Map of integer keys (1-10) to the participant's information for the frame.
     * Keys have no relation to the participantid, use {@code getFrameFromParticipantId} for that.
     *
     * @return {@code Map<String, MatchParticipantFrame> }
     */
    public Map<Integer, MatchParticipantFrame> getParticipantFrames()
    {
        return this.participantFrames;
    }
    
    public MatchParticipantFrame getFrameFromParticipantId(int participantId)
    {
        for (MatchParticipantFrame frame : participantFrames.values())
        {
            if (frame.getParticipantId() == participantId)
            {
                return frame;
            }
        }
        return null;
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
        return timestamp == that.timestamp &&
               Objects.equals(participantFrames, that.participantFrames) &&
               Objects.equals(events, that.events);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(timestamp, participantFrames, events);
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
