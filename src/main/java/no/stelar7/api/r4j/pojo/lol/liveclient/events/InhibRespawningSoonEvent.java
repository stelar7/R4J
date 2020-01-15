package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class InhibRespawningSoonEvent extends GameEvent
{
    private String InhibRespawningSoon;
    
    public String getInhibRespawningSoon()
    {
        return InhibRespawningSoon;
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
        InhibRespawningSoonEvent that = (InhibRespawningSoonEvent) o;
        return Objects.equals(InhibRespawningSoon, that.InhibRespawningSoon);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), InhibRespawningSoon);
    }
    
    @Override
    public String toString()
    {
        return "InhibRespawningSoonEvent{" +
               "InhibRespawningSoon='" + InhibRespawningSoon + '\'' +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}