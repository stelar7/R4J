package no.stelar7.api.r4j.basic.utils;


import java.util.*;
import java.util.function.*;

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
        loadFully();
        return super.spliterator();
    }
    
    @Override
    public void forEach(Consumer<? super T> action)
    {
        int startIndex = 0;
        do
        {
            LazyListIterator it = new LazyListIterator(startIndex);
            while (it.hasNext())
            {
                action.accept(it.next());
            }
            
            startIndex = it.index;
            hasMore = loadMoreData();
        } while (hasMore);
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
        private int size = size();
        
        public LazyListIterator(int startIndex)
        {
            this.index = startIndex;
            
            while (startIndex >= size && (hasMore = LazyList.this.loadMoreData()))
            {
                size = size();
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
                    size = size();
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
               "hasMore=" + hasMore +
               ", currentValue=" + lastValue +
               ", increment=" + increment +
               '}';
    }
}