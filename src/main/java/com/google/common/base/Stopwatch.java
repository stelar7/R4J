/*
 * Copyright (C) 2008 The Guava Authors
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

package com.google.common.base;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.TimeUnit;

public final class Stopwatch
{
    private final Ticker ticker;
    private boolean      isRunning;
    private long         elapsedNanos;
    private long         startTick;

    @Deprecated
    Stopwatch()
    {
        this(Ticker.systemTicker());
    }

    @Deprecated
    Stopwatch(final Ticker ticker)
    {
        this.ticker = Preconditions.checkNotNull(ticker, "ticker");
    }

    private static String abbreviate(final TimeUnit unit)
    {
        switch (unit)
        {
            case NANOSECONDS:
                return "ns";
            case MICROSECONDS:
                return "\u03bcs"; // μs
            case MILLISECONDS:
                return "ms";
            case SECONDS:
                return "s";
            case MINUTES:
                return "min";
            case HOURS:
                return "h";
            case DAYS:
                return "d";
            default:
                throw new AssertionError();
        }
    }

    private static TimeUnit chooseUnit(final long nanos)
    {
        if (DAYS.convert(nanos, NANOSECONDS) > 0)
        {
            return DAYS;
        }
        if (HOURS.convert(nanos, NANOSECONDS) > 0)
        {
            return HOURS;
        }
        if (MINUTES.convert(nanos, NANOSECONDS) > 0)
        {
            return MINUTES;
        }
        if (SECONDS.convert(nanos, NANOSECONDS) > 0)
        {
            return SECONDS;
        }
        if (MILLISECONDS.convert(nanos, NANOSECONDS) > 0)
        {
            return MILLISECONDS;
        }
        if (MICROSECONDS.convert(nanos, NANOSECONDS) > 0)
        {
            return MICROSECONDS;
        }
        return NANOSECONDS;
    }

    public static Stopwatch createStarted()
    {
        return new Stopwatch().start();
    }

    public static Stopwatch createStarted(final Ticker ticker)
    {
        return new Stopwatch(ticker).start();
    }

    public static Stopwatch createUnstarted()
    {
        return new Stopwatch();
    }

    public static Stopwatch createUnstarted(final Ticker ticker)
    {
        return new Stopwatch(ticker);
    }

    public long elapsed(final TimeUnit desiredUnit)
    {
        return desiredUnit.convert(this.elapsedNanos(), NANOSECONDS);
    }

    private long elapsedNanos()
    {
        return this.isRunning ? (this.ticker.read() - this.startTick) + this.elapsedNanos : this.elapsedNanos;
    }

    public boolean isRunning()
    {
        return this.isRunning;
    }

    public Stopwatch reset()
    {
        this.elapsedNanos = 0;
        this.isRunning = false;
        return this;
    }

    public Stopwatch start()
    {
        Preconditions.checkState(!this.isRunning, "This stopwatch is already running.");
        this.isRunning = true;
        this.startTick = this.ticker.read();
        return this;
    }

    public Stopwatch stop()
    {
        final long tick = this.ticker.read();
        Preconditions.checkState(this.isRunning, "This stopwatch is already stopped.");
        this.isRunning = false;
        this.elapsedNanos += tick - this.startTick;
        return this;
    }

    @Override
    public String toString()
    {
        final long nanos = this.elapsedNanos();

        final TimeUnit unit = Stopwatch.chooseUnit(nanos);
        final double value = (double) nanos / NANOSECONDS.convert(1, unit);

        return String.format("%.4g %s", value, Stopwatch.abbreviate(unit));
    }
}