package no.stelar7.api.l4j8.basic.utils;

import java.util.*;
import java.util.stream.*;

public final class Permutations
{
    
    private Permutations()
    {
    }
    
    public static <T> Stream<Stream<T>> of(final List<T> items)
    {
        return IntStream.range(0, factorial(items.size())).mapToObj(i -> permutation(i, items).stream());
    }
    
    private static int factorial(final int num)
    {
        return IntStream.rangeClosed(2, num).reduce(1, (x, y) -> x * y);
    }
    
    private static <T> List<T> permutation(final int count, final LinkedList<T> input, final List<T> output)
    {
        if (input.isEmpty())
        {
            return output;
        }
        
        final int factorial = factorial(input.size() - 1);
        output.add(input.remove(count / factorial));
        return permutation(count % factorial, input, output);
    }
    
    private static <T> List<T> permutation(final int count, final List<T> items)
    {
        return permutation(count, new LinkedList<>(items), new ArrayList<>());
    }
    
}
