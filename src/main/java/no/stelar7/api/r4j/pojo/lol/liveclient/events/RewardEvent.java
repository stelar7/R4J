package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.Objects;

public class RewardEvent extends GameEvent
{
    private String Recipient;
    
    public String getRecipient()
    {
        return Recipient;
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
        RewardEvent that = (RewardEvent) o;
        return Objects.equals(Recipient, that.Recipient);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), Recipient);
    }
    
    @Override
    public String toString()
    {
        return "RewardEvent{" +
               "Recipient='" + Recipient + '\'' +
               ", EventID=" + EventID +
               ", EventName='" + EventName + '\'' +
               ", EventTime=" + EventTime +
               '}';
    }
}
