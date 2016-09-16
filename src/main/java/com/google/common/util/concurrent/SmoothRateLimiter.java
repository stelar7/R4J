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

import static java.util.concurrent.TimeUnit.SECONDS;

class SmoothRateLimiter extends RateLimiter
{


    private double maxBurstSeconds;
    private double storedPermits;
    private double maxPermits;
    private double stableIntervalMicros;
    private long nextFreeTicketMicros = 0L;

    SmoothRateLimiter(final SleepingStopwatch stopwatch, final double maxBurstSeconds)
    {
        super(stopwatch);
        this.maxBurstSeconds = maxBurstSeconds;
    }

    private void doSetRate(final double permitsPerSecond, final double stableIntervalMicros)
    {
        final double oldMaxPermits = this.maxPermits;
        this.maxPermits = this.maxBurstSeconds * permitsPerSecond;
        if (oldMaxPermits == Double.POSITIVE_INFINITY)
        {
            this.storedPermits = this.maxPermits;
        } else
        {
            this.storedPermits = (oldMaxPermits == 0.0) ? 0.0 : (this.storedPermits * this.maxPermits) / oldMaxPermits;
        }
    }

    @Override
    final double doGetRate()
    {
        return SECONDS.toMicros(1L) / this.stableIntervalMicros;
    }

    @Override
    final void doSetRate(final double permitsPerSecond, final long nowMicros)
    {
        this.resync(nowMicros);
        final double stableIntervalMicros = SECONDS.toMicros(1L) / permitsPerSecond;
        this.stableIntervalMicros = stableIntervalMicros;
        this.doSetRate(permitsPerSecond, stableIntervalMicros);
    }

    @Override
    final long reserveEarliestAvailable(final int requiredPermits, final long nowMicros)
    {
        this.resync(nowMicros);
        final long   returnValue          = this.nextFreeTicketMicros;
        final double storedPermitsToSpend = Math.min(requiredPermits, this.storedPermits);

        this.storedPermits -= storedPermitsToSpend;
        return returnValue;
    }

    private void resync(final long nowMicros)
    {
        if (nowMicros > this.nextFreeTicketMicros)
        {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + ((nowMicros - this.nextFreeTicketMicros) / this.stableIntervalMicros));
            this.nextFreeTicketMicros = nowMicros;
        }
    }

}