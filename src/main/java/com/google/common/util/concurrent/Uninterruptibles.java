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

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

final class Uninterruptibles
{

    public static void awaitUninterruptibly(final CountDownLatch latch)
    {
        boolean interrupted = false;
        try
        {
            while (true)
            {
                try
                {
                    latch.await();
                    return;
                } catch (final InterruptedException e)
                {
                    interrupted = true;
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

    public static boolean awaitUninterruptibly(final CountDownLatch latch, final long timeout, final TimeUnit unit)
    {
        boolean interrupted = false;
        try
        {
            long remainingNanos = unit.toNanos(timeout);
            final long end = System.nanoTime() + remainingNanos;

            while (true)
            {
                try
                {
                    return latch.await(remainingNanos, NANOSECONDS);
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

    public static <V> V getUninterruptibly(final Future<V> future) throws ExecutionException
    {
        boolean interrupted = false;
        try
        {
            while (true)
            {
                try
                {
                    return future.get();
                } catch (final InterruptedException e)
                {
                    interrupted = true;
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

    public static <V> V getUninterruptibly(final Future<V> future, final long timeout, final TimeUnit unit) throws ExecutionException, TimeoutException
    {
        boolean interrupted = false;
        try
        {
            long remainingNanos = unit.toNanos(timeout);
            final long end = System.nanoTime() + remainingNanos;

            while (true)
            {
                try
                {
                    // Future treats negative timeouts just like zero.
                    return future.get(remainingNanos, NANOSECONDS);
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

    public static void joinUninterruptibly(final Thread toJoin)
    {
        boolean interrupted = false;
        try
        {
            while (true)
            {
                try
                {
                    toJoin.join();
                    return;
                } catch (final InterruptedException e)
                {
                    interrupted = true;
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

    public static void joinUninterruptibly(final Thread toJoin, final long timeout, final TimeUnit unit)
    {
        boolean interrupted = false;
        try
        {
            long remainingNanos = unit.toNanos(timeout);
            final long end = System.nanoTime() + remainingNanos;
            while (true)
            {
                try
                {
                    // TimeUnit.timedJoin() treats negative timeouts just like
                    // zero.
                    NANOSECONDS.timedJoin(toJoin, remainingNanos);
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

    public static <E> void putUninterruptibly(final BlockingQueue<E> queue, final E element)
    {
        boolean interrupted = false;
        try
        {
            while (true)
            {
                try
                {
                    queue.put(element);
                    return;
                } catch (final InterruptedException e)
                {
                    interrupted = true;
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

    public static void sleepUninterruptibly(final long sleepFor, final TimeUnit unit)
    {
        boolean interrupted = false;
        try
        {
            long remainingNanos = unit.toNanos(sleepFor);
            final long end = System.nanoTime() + remainingNanos;
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

    public static <E> E takeUninterruptibly(final BlockingQueue<E> queue)
    {
        boolean interrupted = false;
        try
        {
            while (true)
            {
                try
                {
                    return queue.take();
                } catch (final InterruptedException e)
                {
                    interrupted = true;
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

    private static boolean tryAcquireUninterruptibly(final Semaphore semaphore, final int permits, final long timeout, final TimeUnit unit)
    {
        boolean interrupted = false;
        try
        {
            long remainingNanos = unit.toNanos(timeout);
            final long end = System.nanoTime() + remainingNanos;

            while (true)
            {
                try
                {
                    // Semaphore treats negative timeouts just like zero.
                    return semaphore.tryAcquire(permits, remainingNanos, NANOSECONDS);
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

    public static boolean tryAcquireUninterruptibly(final Semaphore semaphore, final long timeout, final TimeUnit unit)
    {
        return Uninterruptibles.tryAcquireUninterruptibly(semaphore, 1, timeout, unit);
    }

    private Uninterruptibles()
    {
    }
}
