package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.*;

public class GameEvent
{
    protected int    EventID;
    protected String EventName;
    protected float  EventTime;
    
    public int getEventID()
    {
        return EventID;
    }
    
    public String getEventName()
    {
        return EventName;
    }
    
    public float getEventTime()
    {
        return EventTime;
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
        GameEvent gameEvent = (GameEvent) o;
        return EventID == gameEvent.EventID &&
               Float.compare(gameEvent.EventTime, EventTime) == 0 &&
               Objects.equals(EventName, gameEvent.EventName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(EventID, EventName, EventTime);
    }
    
    @Override
    public String toString()
    {
        return "GameEvent{" +
               "EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}
