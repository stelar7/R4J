package no.stelar7.api.l4j8.basic.ratelimiting;

import java.util.concurrent.TimeUnit;

public class RateLimit
{
    private int  permits;
    private long delayInMs;
    
    public RateLimit(final int permits, final int time, final TimeUnit unit)
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
    public String toString()
    {
        return "RateLimit{" +
               "permits=" + permits +
               ", delayInMs=" + delayInMs +
               '}';
    }
}
