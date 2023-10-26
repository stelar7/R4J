package no.stelar7.api.r4j.pojo.lol.tournament;

import java.io.Serializable;
import java.util.Objects;

public class LobbyEvent implements Serializable
{
    private static final long serialVersionUID = -4415716937937433096L;
    
    
    private String eventType;
    private String puuid;
    private String timestamp;
    
    
    /**
     * The type of event that was triggered
     *
     * @return event
     */
    public String getEventType()
    {
        return this.eventType;
    }
    
    /**
     * The summoner that triggered the event
     *
     * @return summoner
     */
    public String getPuuid()
    {
        return this.puuid;
    }
    
    /**
     * Timestamp from the event
     *
     * @return timestamp
     */
    public String getTimestamp()
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
        LobbyEvent that = (LobbyEvent) o;
        return Objects.equals(eventType, that.eventType) && Objects.equals(puuid, that.puuid) && Objects.equals(timestamp, that.timestamp);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(eventType, puuid, timestamp);
    }
    
    @Override
    public String toString()
    {
        return "LobbyEvent{" +
               "eventType='" + eventType + '\'' +
               ", puuid='" + puuid + '\'' +
               ", timestamp='" + timestamp + '\'' +
               '}';
    }
}
