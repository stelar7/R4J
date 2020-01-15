package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.*;

public class KillEvent extends GameEvent
{
    protected List<String> Assisters;
    protected String       KillerName;
    
    public List<String> getAssisters()
    {
        return Assisters;
    }
    
    public String getKillerName()
    {
        return KillerName;
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
        KillEvent killEvent = (KillEvent) o;
        return Objects.equals(Assisters, killEvent.Assisters) &&
               Objects.equals(KillerName, killEvent.KillerName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), Assisters, KillerName);
    }
    
    @Override
    public String toString()
    {
        return "KillEvent{" +
               "Assisters=" + Assisters +
               ", KillerName='" + KillerName + '\'' +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}
