package no.stelar7.api.l4j8.pojo.tournament;

public class LobbyEvent
{
    private String eventType;
    private String summonerId;
    private String timestamp;

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
        final LobbyEvent other = (LobbyEvent) obj;
        if (this.eventType == null)
        {
            if (other.eventType != null)
            {
                return false;
            }
        } else if (!this.eventType.equals(other.eventType))
        {
            return false;
        }
        if (this.summonerId == null)
        {
            if (other.summonerId != null)
            {
                return false;
            }
        } else if (!this.summonerId.equals(other.summonerId))
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

    public String getEventType()
    {
        return this.eventType;
    }

    public String getSummonerId()
    {
        return this.summonerId;
    }

    public String getTimestamp()
    {
        return this.timestamp;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.eventType == null) ? 0 : this.eventType.hashCode());
        result = (prime * result) + ((this.summonerId == null) ? 0 : this.summonerId.hashCode());
        result = (prime * result) + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "LobbyEvent [eventType=" + this.eventType + ", summonerId=" + this.summonerId + ", timestamp=" + this.timestamp + "]";
    }
}
