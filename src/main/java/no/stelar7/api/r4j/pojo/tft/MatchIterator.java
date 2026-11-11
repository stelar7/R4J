package no.stelar7.api.r4j.pojo.tft;

import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.impl.tft.TFTMatchAPI;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;

import java.util.*;

public class MatchIterator implements Iterable<GAMHSMatch>
{
    private Iterator<String> refs;
    
    private final R4JContext       context;

    public MatchIterator(List<String> lazy)
    {
        this(lazy, R4JContext.getDefault());
    }

    /**
     * @param lazy    the ids of the matches
     * @param context the context used to fetch the matches
     */
    public MatchIterator(List<String> lazy, R4JContext context)
    {
        this.refs = lazy.iterator();
        this.context = context;
    }
    
    @Override
    public Iterator<GAMHSMatch> iterator()
    {
        return new LazyListIterator();
    }
    
    private class LazyListIterator implements Iterator<GAMHSMatch>
    {
        @Override
        public boolean hasNext()
        {
            return refs.hasNext();
        }
        
        @Override
        public GAMHSMatch next()
        {
            String      id     = refs.next();
            RegionShard region = LeagueShard.fromString(id.split("_")[0]).get().toRegionShard();
            
            return TFTMatchAPI.getInstance(context).getMatchRAW(region, id);
        }
    }
}
