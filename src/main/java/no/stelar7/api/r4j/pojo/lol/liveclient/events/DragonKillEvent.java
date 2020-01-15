package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import no.stelar7.api.r4j.pojo.lol.liveclient.events.GameEvent.EpicUnitKillEvent;

import java.util.Objects;

public class DragonKillEvent extends EpicUnitKillEvent
{
    private String DragonType;
    
    public String getDragonType()
    {
        return DragonType;
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
        DragonKillEvent that = (DragonKillEvent) o;
        return Objects.equals(DragonType, that.DragonType);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), DragonType);
    }
    
    @Override
    public String toString()
    {
        return "DragonKillEvent{" +
               "DragonType='" + DragonType + '\'' +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               ", Stolen=" + Stolen +
               '}';
    }
}
