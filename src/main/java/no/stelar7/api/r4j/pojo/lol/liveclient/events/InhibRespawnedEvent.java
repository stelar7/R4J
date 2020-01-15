package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class InhibRespawnedEvent extends GameEvent
{
    private String InhibRespawned;
    
    public String getInhibRespawned()
    {
        return InhibRespawned;
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
        InhibRespawnedEvent that = (InhibRespawnedEvent) o;
        return Objects.equals(InhibRespawned, that.InhibRespawned);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), InhibRespawned);
    }
    
    @Override
    public String toString()
    {
        return "InhibRespawnedEvent{" +
               "InhibRespawned='" + InhibRespawned + '\'' +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}
