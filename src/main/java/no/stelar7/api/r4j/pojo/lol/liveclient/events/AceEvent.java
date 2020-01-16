package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class AceEvent extends GameEvent
{
    private String Acer;
    private String AcingTeam;
    
    public String getAcer()
    {
        return Acer;
    }
    
    public String getAcingTeam()
    {
        return AcingTeam;
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
        AceEvent aceEvent = (AceEvent) o;
        return Objects.equals(Acer, aceEvent.Acer) &&
               Objects.equals(AcingTeam, aceEvent.AcingTeam);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), Acer, AcingTeam);
    }
    
    @Override
    public String toString()
    {
        return "AceEvent{" +
               "Acer='" + Acer + '\'' +
               ", AcingTeam='" + AcingTeam + '\'' +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}
