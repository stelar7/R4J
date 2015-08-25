package no.stelar7.api.l4j8.basic;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RateLimiter extends TimerTask
{

    private final Semaphore s;
    private final long      time;
    private final int       count;
    Timer                   t = new Timer(true);

    public RateLimiter()
    {
        this(9, TimeUnit.SECONDS.toMillis(10));
    }

    public RateLimiter(int calls, long millis)
    {
        s = new Semaphore(calls);
        time = millis;
        count = calls;
        t.scheduleAtFixedRate(this, 0, time);
    }

    public void run()
    {
        s.release(count - s.availablePermits());
    }

    public Optional<Exception> acquire()
    {
        try
        {
            s.acquire();
            return Optional.empty();
        } catch (Exception e)
        {
            e.printStackTrace();
            return Optional.of(e);
        }
    }
}
