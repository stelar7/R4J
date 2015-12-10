package no.stelar7.api.l4j8.pojo.tournament;

import java.util.List;

public class LobbyEventWrapper
{
    List<LobbyEvent> eventList;

    public List<LobbyEvent> getEventList()
    {
        return eventList;
    }

    @Override
    public String toString()
    {
        return "LobbyEventWrapper [eventList=" + eventList + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventList == null) ? 0 : eventList.hashCode());
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
        LobbyEventWrapper other = (LobbyEventWrapper) obj;
        if (eventList == null)
        {
            if (other.eventList != null)
                return false;
        } else if (!eventList.equals(other.eventList))
            return false;
        return true;
    }
}
