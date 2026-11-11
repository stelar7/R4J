package no.stelar7.api.r4j.basic.utils;


import java.util.*;
import java.util.function.*;

/**
 * A list that fetches its content page by page, when it is needed.
 * <p>
 * Iterating, streaming and {@link #get(int)} only load the pages they reach, and {@link #isEmpty()} loads at most the first page.
 * Anything that needs to know the whole list ({@link #size()}, {@link #contains(Object)}, {@link #toArray()}, {@link #subList(int, int)}, ...)
 * loads all of it first. Use {@link #loadedSize()} to see how much has been loaded without loading more.
 */
public class LazyList<T> extends ArrayList<T>
{
    private static final long serialVersionUID = -1691718732059707951L;
    
    private           int                        lastValue;
    private           int                        increment;
    private transient Function<Integer, List<T>> makerOfMoreData;
    private           boolean                    hasMore = true;
    
    public LazyList(int increment, Function<Integer, List<T>> maker)
    {
        this.makerOfMoreData = maker;
        this.increment = increment;
    }
    
    public void loadFully()
    {
        while (hasMore)
        {
            hasMore = loadMoreData();
        }
    }
    
    /**
     * @return true if has more data
     */
    private boolean loadMoreData()
    {
        if (makerOfMoreData == null)
        {
            // a list that was serialized has what it had loaded, but can not load more
            return false;
        }

        List<T> more = makerOfMoreData.apply(lastValue);
        lastValue += increment;
        
        // we should return false here if the list doesnt contain 100 games, but that depends on the api always returning 100 games,
        // so we instead make another call to make sure we dont miss any data.
        if (more.isEmpty())
        {
            return false;
        }
        
        this.addAll(more);
        return true;
    }
    
    /**
     * @return the amount of items loaded so far; never loads more
     */
    public int loadedSize()
    {
        return super.size();
    }

    /**
     * @return the amount of items in the list. This has to load the whole list, as there is no other way to know.
     */
    @Override
    public int size()
    {
        loadFully();
        return super.size();
    }

    /**
     * @return true if there is nothing in the list. Loads the first page if nothing has been loaded yet.
     */
    @Override
    public boolean isEmpty()
    {
        if (super.size() == 0 && hasMore)
        {
            hasMore = loadMoreData();
        }

        return super.size() == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        loadFully();
        return super.contains(o);
    }

    @Override
    public int indexOf(Object o)
    {
        loadFully();
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        loadFully();
        return super.lastIndexOf(o);
    }

    @Override
    public Object[] toArray()
    {
        loadFully();
        return super.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a)
    {
        loadFully();
        return super.toArray(a);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex)
    {
        loadFully();
        return super.subList(fromIndex, toIndex);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new LazyListIterator(-1);
    }
    
    @Override
    public ListIterator<T> listIterator()
    {
        return new LazyListIterator(-1);
    }
    
    @Override
    public ListIterator<T> listIterator(int index)
    {
        return new LazyListIterator(index);
    }
    
    @Override
    public Spliterator<T> spliterator()
    {
        return Spliterators.spliteratorUnknownSize(this.iterator(), Spliterator.ORDERED);
    }
    
    @Override
    public void forEach(Consumer<? super T> action)
    {
        // this is easier than implementing it myself lol
        //noinspection SimplifyStreamApiCallChains
        this.stream().forEach(action);
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        
        LazyList<?> lazyList = (LazyList<?>) o;
        
        if (lastValue != lazyList.lastValue)
        {
            return false;
        }
        if (increment != lazyList.increment)
        {
            return false;
        }
        return hasMore == lazyList.hasMore;
    }
    
    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + lastValue;
        result = 31 * result + increment;
        result = 31 * result + (hasMore ? 1 : 0);
        return result;
    }
    
    @Override
    public T get(int index)
    {
        return listIterator(index - 1).next();
    }
    
    
    private class LazyListIterator implements ListIterator<T>
    {
        private int index;
        private int size = loadedSize();
        
        public LazyListIterator(int startIndex)
        {
            this.index = startIndex;
            
            while (startIndex >= size && (hasMore = LazyList.this.loadMoreData()))
            {
                size = loadedSize();
            }
        }
        
        @Override
        public boolean hasNext()
        {
            if (index + 1 < size)
            {
                return true;
            }
            
            // if the last call had more data
            if (hasMore)
            {
                
                // check if this call has more data, and return true if it does
                hasMore = loadMoreData();
                if (hasMore)
                {
                    size = loadedSize();
                    return true;
                }
            }
            
            return false;
        }
        
        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            
            return LazyList.super.get(++index);
        }
        
        @Override
        public boolean hasPrevious()
        {
            return index >= 1;
        }
        
        @Override
        public T previous()
        {
            if (!hasPrevious())
            {
                throw new NoSuchElementException();
            }
            
            return LazyList.super.get(--index);
        }
        
        @Override
        public int nextIndex()
        {
            return index + 1;
        }
        
        @Override
        public int previousIndex()
        {
            return index - 1;
        }
        
        @Override
        public void remove()
        {
            if (index > 0)
            {
                LazyList.this.remove(index);
            }
        }
        
        @Override
        public void set(T t)
        {
            if (index > 0)
            {
                LazyList.this.set(index, t);
            }
        }
        
        @Override
        public void add(T t)
        {
            LazyList.this.add(index, t);
        }
    }
    
    @Override
    public String toString()
    {
        return "LazyList{" +
               "loaded=" + loadedSize() +
               ", hasMore=" + hasMore +
               ", currentValue=" + lastValue +
               ", increment=" + increment +
               '}';
    }
}