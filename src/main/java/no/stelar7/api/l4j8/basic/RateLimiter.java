package no.stelar7.api.l4j8.basic;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RateLimiter extends TimerTask
{

    private final Semaphore s;
    private final Long      time;
    private final Integer   count;
    Timer                   t = new Timer(true);

    public RateLimiter()
    {
        this(9, TimeUnit.SECONDS.toMillis(10));
    }

    public RateLimiter(final int calls, final long millis)
    {
        this.s = new Semaphore(calls);
        this.time = millis;
        this.count = calls;
        this.t.scheduleAtFixedRate(this, 0, this.time);
    }

    public Optional<Exception> acquire()
    {
        try
        {
            this.s.acquire();
            return Optional.empty();
        } catch (final Exception e)
        {
            e.printStackTrace();
            return Optional.of(e);
        }
    }

    @Override
    public void run()
    {
        this.s.release(this.count - this.s.availablePermits());
    }
}
