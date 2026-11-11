package no.stelar7.api.r4j.basic.calling;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * Limits how many calls are in flight at the same time (for one api key on one platform).
 * <p>
 * Blocking calls wait for a permit, non-blocking calls are told when they have one. Both stand in the same line and are served in the order
 * they asked, so neither kind can starve the other.
 */
final class CallPermits
{
    private static final class Waiter
    {
        // a blocking caller waits on the latch, a non-blocking one is given the task to run
        private final CountDownLatch latch;
        private final Runnable       task;

        private Waiter(CountDownLatch latch, Runnable task)
        {
            this.latch = latch;
            this.task = task;
        }
    }

    private final Deque<Waiter>      waiters = new ArrayDeque<>();
    private final Consumer<Runnable> dispatcher;
    private       int                available;

    /**
     * @param permits    how many calls can be in flight at the same time
     * @param dispatcher runs the task of a non-blocking caller that was waiting. The task is not run by the thread that releases the permit,
     *                   so a long line of waiters does not end up as a deep call stack.
     */
    CallPermits(int permits, Consumer<Runnable> dispatcher)
    {
        this.available = permits;
        this.dispatcher = dispatcher;
    }

    /**
     * Waits for a permit.
     *
     * @throws InterruptedException if the thread is interrupted while waiting; no permit is held then
     */
    void acquire() throws InterruptedException
    {
        Waiter waiter;
        synchronized (this)
        {
            if (available > 0 && waiters.isEmpty())
            {
                available--;
                return;
            }

            waiter = new Waiter(new CountDownLatch(1), null);
            waiters.add(waiter);
        }

        try
        {
            waiter.latch.await();
        } catch (InterruptedException e)
        {
            boolean stillWaiting;
            synchronized (this)
            {
                stillWaiting = waiters.remove(waiter);
            }

            if (!stillWaiting)
            {
                // the permit was handed to us just as we were interrupted
                release();
            }

            throw e;
        }
    }

    /**
     * Runs the task once a permit is held: right away if one is free and nobody is waiting, otherwise when it is this caller's turn.
     * The task has to make sure {@link #release()} is called.
     */
    void acquireAsync(Runnable task)
    {
        synchronized (this)
        {
            if (available == 0 || !waiters.isEmpty())
            {
                waiters.add(new Waiter(null, task));
                return;
            }

            available--;
        }

        task.run();
    }

    /**
     * Gives a permit back. If someone is waiting, the permit goes to whoever has waited the longest.
     */
    void release()
    {
        Waiter next;
        synchronized (this)
        {
            next = waiters.poll();
            if (next == null)
            {
                available++;
                return;
            }
        }

        if (next.latch != null)
        {
            next.latch.countDown();
        } else
        {
            dispatcher.accept(next.task);
        }
    }
}
