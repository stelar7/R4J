package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.utils.LazyList;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LazyListTest
{
    private final AtomicInteger pagesLoaded = new AtomicInteger();

    /**
     * 25 items in pages of 10, like a match list
     */
    private LazyList<Integer> list()
    {
        pagesLoaded.set(0);
        return new LazyList<>(10, start -> {
            pagesLoaded.incrementAndGet();
            List<Integer> page = new ArrayList<>();
            for (int i = start; i < Math.min(start + 10, 25); i++)
            {
                page.add(i);
            }
            return page;
        });
    }

    @Test
    public void nothingIsLoadedUntilItIsNeeded()
    {
        LazyList<Integer> list = list();
        Assertions.assertEquals(0, pagesLoaded.get());
        Assertions.assertEquals(0, list.loadedSize());
    }

    @Test
    public void sizeAndIsEmptyTellTheTruthWithoutIteratingFirst()
    {
        // this used to say 0 and true until something iterated the list (ie. a debugger showing it)
        Assertions.assertEquals(25, list().size());

        LazyList<Integer> list = list();
        Assertions.assertFalse(list.isEmpty());
        // only the first page was needed to know that
        Assertions.assertEquals(1, pagesLoaded.get());
        Assertions.assertEquals(10, list.loadedSize());

        Assertions.assertTrue(new LazyList<Integer>(10, start -> Collections.emptyList()).isEmpty());
        Assertions.assertEquals(0, new LazyList<Integer>(10, start -> Collections.emptyList()).size());
    }

    @Test
    public void getOnlyLoadsThePagesItNeeds()
    {
        LazyList<Integer> list = list();
        Assertions.assertEquals(3, list.get(3));
        Assertions.assertEquals(1, pagesLoaded.get());

        Assertions.assertEquals(14, list.get(14));
        Assertions.assertEquals(2, pagesLoaded.get());
    }

    @Test
    public void iterationAndStreamsAreLazy()
    {
        LazyList<Integer> list = list();
        Iterator<Integer> it   = list.iterator();
        for (int i = 0; i < 5; i++)
        {
            Assertions.assertEquals(i, it.next());
        }
        Assertions.assertEquals(1, pagesLoaded.get());

        list = list();
        Assertions.assertEquals(Arrays.asList(0, 1, 2), list.stream().limit(3).collect(Collectors.toList()));
        Assertions.assertEquals(1, pagesLoaded.get());

        // and going all the way gives everything, in order
        List<Integer> all = new ArrayList<>();
        for (Integer i : list())
        {
            all.add(i);
        }
        Assertions.assertEquals(25, all.size());
        Assertions.assertEquals(24, all.get(24));
    }

    @Test
    public void operationsOnTheWholeListLoadIt()
    {
        Assertions.assertTrue(list().contains(24));
        Assertions.assertEquals(24, list().indexOf(24));
        Assertions.assertEquals(25, list().toArray().length);
        Assertions.assertEquals(Arrays.asList(20, 21, 22, 23, 24), list().subList(20, 25));
        Assertions.assertEquals(25, new ArrayList<>(list()).size());
    }
}
