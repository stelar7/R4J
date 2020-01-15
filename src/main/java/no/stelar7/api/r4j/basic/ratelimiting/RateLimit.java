package no.stelar7.api.r4j.basic.ratelimiting;

import java.util.concurrent.TimeUnit;

public class RateLimit
{
    private int  permits;
    private long delayInMs;
    
    public RateLimit(final int permits, final long time, final TimeUnit unit)
    {
        this.permits = permits;
        delayInMs = unit.toMillis(time);
    }
    
    public int getPermits()
    {
        return permits;
    }
    
    public long getTimeframeInMS()
    {
        return delayInMs;
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
        
        RateLimit rateLimit = (RateLimit) o;
        
        if (permits != rateLimit.permits)
        {
            return false;
        }
        
        return delayInMs == rateLimit.delayInMs;
    }
    
    @Override
    public int hashCode()
    {
        int result = permits;
        result = 31 * result + (int) (delayInMs ^ (delayInMs >>> 32));
        return result;
    }
    
    @Override
    public String toString()
    {
        return "RateLimit{" +
               "permits=" + permits +
               ", delayInMs=" + delayInMs +
               '}';
    }
}
