package no.stelar7.api.r4j.pojo.lol.match.lol;

import no.stelar7.api.r4j.basic.utils.LazyList;

import java.util.Iterator;

public class MatchIterator implements Iterable<Match>
{
    private Iterator<MatchReference> refs;
    
    public MatchIterator(LazyList<MatchReference> lazy)
    {
        this.refs = lazy.iterator();
    }
    
    @Override
    public Iterator<Match> iterator()
    {
        return new LazyListIterator();
    }
    
    private class LazyListIterator implements Iterator<Match>
    {
        @Override
        public boolean hasNext()
        {
            return refs.hasNext();
        }
        
        @Override
        public Match next()
        {
            return refs.next().getFullMatch();
        }
    }
}
