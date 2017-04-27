package no.stelar7.api.l4j8.basic.ratelimiting;

import java.util.concurrent.*;

public class RateLimit
{
    private int  requests;
    private long delayInMs;
    
    public RateLimit(final int permits, final int time, final TimeUnit unit)
    {
        requests = permits;
        delayInMs = unit.toMillis(time);
    }
    
    public int getRequests()
    {
        return requests;
    }
    
    public long getTimeframeInMS()
    {
        return delayInMs;
    }
    
    
    @Override
    public String toString()
    {
        return "RateLimit{" +
               "requests=" + requests +
               ", delayInMs=" + delayInMs +
               '}';
    }
}
