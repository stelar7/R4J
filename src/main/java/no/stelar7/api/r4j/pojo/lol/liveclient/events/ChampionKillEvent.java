package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class ChampionKillEvent extends KillEvent
{
    private String VictimName;
    
    public String getVictimName()
    {
        return VictimName;
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
        ChampionKillEvent that = (ChampionKillEvent) o;
        return Objects.equals(VictimName, that.VictimName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), VictimName);
    }
    
    @Override
    public String toString()
    {
        return "ChampionKillEvent{" +
               "EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               ", VictimName='" + VictimName + '\'' +
               ", Assisters=" + Assisters +
               ", KillerName='" + KillerName + '\'' +
               '}';
    }
}
