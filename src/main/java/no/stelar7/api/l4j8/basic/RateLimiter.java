package no.stelar7.api.l4j8.basic;


public class RateLimiter
{
    private final double permitsPerSec;
    
    public RateLimiter(final double permitsPerSecond)
    {
        this.permitsPerSec = permitsPerSecond;
    }
    
    public void acquire()
    {
        try
        {
            // Thread.sleep((long) permitsPerSec);
            // Sleep for 1sec between calls so we NEVER go over the limit
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}