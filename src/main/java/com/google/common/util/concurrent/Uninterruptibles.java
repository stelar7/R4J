/*
 * Copyright (C) 2011 The Guava Authors
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

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

final class Uninterruptibles
{

    static void sleepUninterruptibly(final long sleepFor, final TimeUnit unit)
    {
        boolean interrupted = false;
        try
        {
            long       remainingNanos = unit.toNanos(sleepFor);
            final long end            = System.nanoTime() + remainingNanos;
            while (true)
            {
                try
                {
                    NANOSECONDS.sleep(remainingNanos);
                    return;
                } catch (final InterruptedException e)
                {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                }
            }
        } finally
        {
            if (interrupted)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    private Uninterruptibles()
    {
    }
}
