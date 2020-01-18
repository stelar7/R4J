package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class MultikillEvent extends GameEvent
{
    private String KillerName;
    private int    KillStreak;
    
    public String getKillerName()
    {
        return KillerName;
    }
    
    public int getKillStreak()
    {
        return KillStreak;
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
        MultikillEvent that = (MultikillEvent) o;
        return KillStreak == that.KillStreak &&
               Objects.equals(KillerName, that.KillerName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), KillerName, KillStreak);
    }
    
    @Override
    public String toString()
    {
        return "MultikillEvent{" +
               "KillerName='" + KillerName + '\'' +
               ", KillStreak=" + KillStreak +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}
