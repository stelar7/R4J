package no.stelar7.api.r4j.pojo.val.matchlist;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class RecentMatchList implements Serializable
{
    private static final long serialVersionUID = 8470832975846854255L;
    
    private Long         currentTime;
    private List<String> matchIds;
    
    public Long getGeneratedAt()
    {
        return currentTime;
    }
    
    public ZonedDateTime getGeneratedAtAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.currentTime), ZoneOffset.UTC);
    }
    
    
    public List<String> getMatchIds()
    {
        return matchIds;
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
        RecentMatchList that = (RecentMatchList) o;
        return Objects.equals(currentTime, that.currentTime) &&
               Objects.equals(matchIds, that.matchIds);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(currentTime, matchIds);
    }
    
    @Override
    public String toString()
    {
        return "RecentMatchList{" +
               "currentTime=" + currentTime +
               ", matchIds=" + matchIds +
               '}';
    }
}
