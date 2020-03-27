package no.stelar7.api.r4j.pojo.lol.clash;

import java.io.Serializable;
import java.time.*;
import java.util.Objects;

public class ClashTournamentPhase implements Serializable
{
    private static final long serialVersionUID = 3958976342406532433L;
    private int id;
    private long    registrationTime;
    private long    startTime;
    private boolean cancelled;
    
    public int getId()
    {
        return id;
    }
    
    public long getRegistrationTime()
    {
        return registrationTime;
    }
    
    public ZonedDateTime getRegistrationTimeAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.registrationTime), ZoneOffset.UTC);
    }
    
    public long getStartTime()
    {
        return startTime;
    }
    
    public ZonedDateTime getStartTimeAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.startTime), ZoneOffset.UTC);
    }
    
    public boolean isCancelled()
    {
        return cancelled;
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
        ClashTournamentPhase that = (ClashTournamentPhase) o;
        return id == that.id &&
               registrationTime == that.registrationTime &&
               startTime == that.startTime &&
               cancelled == that.cancelled;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, registrationTime, startTime, cancelled);
    }
    
    @Override
    public String toString()
    {
        return "ClashTournamentPhase{" +
               "id=" + id +
               ", registrationTime=" + registrationTime +
               ", startTime=" + startTime +
               ", cancelled=" + cancelled +
               '}';
    }
}
