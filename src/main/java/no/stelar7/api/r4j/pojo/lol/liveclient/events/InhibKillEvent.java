package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class InhibKillEvent extends KillEvent
{
    private String InhibKilled;
    
    public String getInhibKilled()
    {
        return InhibKilled;
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
        if (!super.equals(o))
        {
            return false;
        }
        InhibKillEvent that = (InhibKillEvent) o;
        return Objects.equals(InhibKilled, that.InhibKilled);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), InhibKilled);
    }
    
    @Override
    public String toString()
    {
        return "InhibKillEvent{" +
               "InhibKilled='" + InhibKilled + '\'' +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}
