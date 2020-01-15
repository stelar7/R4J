package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class EpicUnitKillEvent extends KillEvent
{
    private boolean Stolen;
    
    public boolean isStolen()
    {
        return Stolen;
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
        EpicUnitKillEvent that = (EpicUnitKillEvent) o;
        return Stolen == that.Stolen;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), Stolen);
    }
    
    @Override
    public String toString()
    {
        return "EpicUnitKillEvent{" +
               "Stolen=" + Stolen +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}