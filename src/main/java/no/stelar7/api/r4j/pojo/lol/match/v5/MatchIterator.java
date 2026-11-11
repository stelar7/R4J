package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.impl.lol.raw.MatchV5API;

import java.util.Iterator;

public class MatchIterator implements Iterable<LOLMatch>
{
    private final Iterator<String> refs;
    
    private final R4JContext       context;

    public MatchIterator(LazyList<String> lazy)
    {
        this(lazy, R4JContext.getDefault());
    }

    /**
     * @param lazy    the ids of the matches
     * @param context the context used to fetch the matches
     */
    public MatchIterator(LazyList<String> lazy, R4JContext context)
    {
        this.refs = lazy.iterator();
        this.context = context;
    }
    
    @Override
    public Iterator<LOLMatch> iterator()
    {
        return new LazyListIterator();
    }
    
    private class LazyListIterator implements Iterator<LOLMatch>
    {
        @Override
        public boolean hasNext()
        {
            return refs.hasNext();
        }
        
        @Override
        public LOLMatch next()
        {
            String      id     = refs.next();
            RegionShard region = LeagueShard.fromString(id.split("_")[0]).get().toRegionShard();
            
            return MatchV5API.getInstance(context).getMatch(region, id);
        }
    }
}
