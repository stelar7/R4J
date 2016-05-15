/*
 * Copyright (C) 2012 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.util.concurrent;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.*;

import com.google.common.base.*;
import com.google.common.util.concurrent.SmoothRateLimiter.*;

public abstract class RateLimiter
{
    abstract static class SleepingStopwatch
    {
        static final SleepingStopwatch createFromSystemTimer()
        {
            return new SleepingStopwatch()
            {
                final Stopwatch stopwatch = Stopwatch.createStarted();

                @Override
                long readMicros()
                {
                    return this.stopwatch.elapsed(MICROSECONDS);
                }

                @Override
                void sleepMicrosUninterruptibly(final long micros)
                {
                    if (micros > 0)
                    {
                        Uninterruptibles.sleepUninterruptibly(micros, MICROSECONDS);
                    }
                }
            };
        }

        abstract long readMicros();

        abstract void sleepMicrosUninterruptibly(final long micros);
    }

    public static RateLimiter create(final double permitsPerSecond)
    {
        return RateLimiter.create(SleepingStopwatch.createFromSystemTimer(), permitsPerSecond);
    }

    static RateLimiter create(final SleepingStopwatch stopwatch, final double permitsPerSecond)
    {
        final RateLimiter rateLimiter = new SmoothBursty(stopwatch, 1.0);
        rateLimiter.setRate(permitsPerSecond);
        return rateLimiter;
    }

    private final SleepingStopwatch stopwatch;

    private volatile Object         mutexDoNotUseDirectly;

    RateLimiter(final SleepingStopwatch stopwatch)
    {
        this.stopwatch = stopwatch;
    }

    public double acquire()
    {
        return this.acquire(1);
    }

    public double acquire(final int permits)
    {
        final long microsToWait = this.reserve(permits);
        this.stopwatch.sleepMicrosUninterruptibly(microsToWait);
        return (1.0 * microsToWait) / SECONDS.toMicros(1L);
    }

    private boolean canAcquire(final long nowMicros, final long timeoutMicros)
    {
        return (this.queryEarliestAvailable(nowMicros) - timeoutMicros) <= nowMicros;
    }

    abstract double doGetRate();

    abstract void doSetRate(final double permitsPerSecond, final long nowMicros);

    public final double getRate()
    {
        synchronized (this.mutex())
        {
            return this.doGetRate();
        }
    }

    private Object mutex()
    {
        Object mutex = this.mutexDoNotUseDirectly;
        if (mutex == null)
        {
            synchronized (this)
            {
                mutex = this.mutexDoNotUseDirectly;
                if (mutex == null)
                {
                    this.mutexDoNotUseDirectly = mutex = new Object();
                }
            }
        }
        return mutex;
    }

    abstract long queryEarliestAvailable(final long nowMicros);

    final long reserve(final int permits)
    {
        synchronized (this.mutex())
        {
            return this.reserveAndGetWaitLength(permits, this.stopwatch.readMicros());
        }
    }

    final long reserveAndGetWaitLength(final int permits, final long nowMicros)
    {
        final long momentAvailable = this.reserveEarliestAvailable(permits, nowMicros);
        return Math.max(momentAvailable - nowMicros, 0);
    }

    abstract long reserveEarliestAvailable(final int permits, final long nowMicros);

    public final void setRate(final double permitsPerSecond)
    {
        synchronized (this.mutex())
        {
            this.doSetRate(permitsPerSecond, this.stopwatch.readMicros());
        }
    }

    @Override
    public String toString()
    {
        return String.format("RateLimiter[stableRate=%3.1fqps]", this.getRate());
    }

    public boolean tryAcquire()
    {
        return this.tryAcquire(1, 0, MICROSECONDS);
    }

    public boolean tryAcquire(final int permits, final long timeout, final TimeUnit unit)
    {
        final long timeoutMicros = Math.max(unit.toMicros(timeout), 0);
        long microsToWait;
        synchronized (this.mutex())
        {
            final long nowMicros = this.stopwatch.readMicros();
            if (!this.canAcquire(nowMicros, timeoutMicros))
            {
                return false;
            } else
            {
                microsToWait = this.reserveAndGetWaitLength(permits, nowMicros);
            }
        }
        this.stopwatch.sleepMicrosUninterruptibly(microsToWait);
        return true;
    }
}