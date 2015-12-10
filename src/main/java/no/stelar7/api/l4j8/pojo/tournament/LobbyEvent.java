package no.stelar7.api.l4j8.pojo.tournament;

public class LobbyEvent
{
    String eventType;
    String summonerId;
    String timestamp;

    @Override
    public String toString()
    {
        return "LobbyEvent [eventType=" + eventType + ", summonerId=" + summonerId + ", timestamp=" + timestamp + "]";
    }

    public String getEventType()
    {
        return eventType;
    }

    public String getSummonerId()
    {
        return summonerId;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
        result = prime * result + ((summonerId == null) ? 0 : summonerId.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LobbyEvent other = (LobbyEvent) obj;
        if (eventType == null)
        {
            if (other.eventType != null)
                return false;
        } else if (!eventType.equals(other.eventType))
            return false;
        if (summonerId == null)
        {
            if (other.summonerId != null)
                return false;
        } else if (!summonerId.equals(other.summonerId))
            return false;
        if (timestamp == null)
        {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }
}
