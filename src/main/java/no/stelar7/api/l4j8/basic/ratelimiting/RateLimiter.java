package no.stelar7.api.l4j8.basic.ratelimiting;

import java.util.concurrent.*;

public abstract class RateLimiter
{
    protected final long      permits;
    protected       long      delayInMs;
    protected       Semaphore semaphore;
    
    /**
     * Creates a new RateLimiter
     *
     * @param permits how many calls per time
     * @param time    how long the time is
     * @param unit    what unit the time is
     */
    public RateLimiter(final int permits, final int time, final TimeUnit unit)
    {
        this.permits = permits;
        delayInMs = unit.toMillis(time);
        semaphore = new Semaphore(permits);
    }
    
    public abstract void acquire();
}
