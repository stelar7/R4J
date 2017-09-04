package no.stelar7.api.l4j8.pojo.tournament;

import java.io.Serializable;

public class LobbyEvent implements Serializable
{
    private static final long serialVersionUID = -3513933527957781405L;
    
    private String eventType;
    private String summonerId;
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
    public String getSummonerId()
    {
        return this.summonerId;
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
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.eventType == null) ? 0 : this.eventType.hashCode());
        result = (prime * result) + ((this.summonerId == null) ? 0 : this.summonerId.hashCode());
        result = (prime * result) + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        return result;
    }
    
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
    
    @Override
    public String toString()
    {
        return "LobbyEvent [eventType=" + this.eventType + ", summonerId=" + this.summonerId + ", timestamp=" + this.timestamp + "]";
    }
}
