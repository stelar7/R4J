package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class TurretKillEvent extends KillEvent
{
    private String TurretKilled;
    
    public String getTurretKilled()
    {
        return TurretKilled;
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
        TurretKillEvent that = (TurretKillEvent) o;
        return Objects.equals(TurretKilled, that.TurretKilled);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), TurretKilled);
    }
    
    @Override
    public String toString()
    {
        return "TurretKillEvent{" +
               "TurretKilled='" + TurretKilled + '\'' +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}
