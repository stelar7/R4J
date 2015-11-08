package no.stelar7.api.l4j8.pojo.match;

import java.util.List;
import java.util.Map;

public class Frame
{
    private List<Event>                   events;
    private Map<String, ParticipantFrame> participantFrames;
    private Long                          timestamp;

    /**
     * List of events for this frame.
     *
     * @return {@code List<Event>}
     */
    public List<Event> getEvents()
    {
        return events;
    }

    /**
     * Map of each participant ID to the participant's information for the frame.
     *
     * @return {@code Map<String, ParticipantFrame> }
     */
    public Map<String, ParticipantFrame> getParticipantFrames()
    {
        return participantFrames;
    }

    /**
     * Represents how many milliseconds into the game the frame occurred.
     *
     * @return Long
     */
    public Long getTimestamp()
    {
        return timestamp;
    }
}
