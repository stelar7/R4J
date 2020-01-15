package no.stelar7.api.r4j.pojo.lol.replay;

import java.util.Objects;

public class ReplayGameClientProcessInfo
{
    /**
     * Process identifier for this gam
     */
    int processID;
    
    public int getProcessID()
    {
        return processID;
    }
    
    @Override
    public String toString()
    {
        return "ReplayGameClientProcessInfo{" +
               "processID=" + processID +
               '}';
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
        ReplayGameClientProcessInfo that = (ReplayGameClientProcessInfo) o;
        return processID == that.processID;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(processID);
    }
}
