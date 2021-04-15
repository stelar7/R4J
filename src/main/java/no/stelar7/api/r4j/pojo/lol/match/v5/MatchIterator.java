package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.impl.lol.raw.MatchV5API;

import java.util.Iterator;

public class MatchIterator implements Iterable<LOLMatch>
{
    private final Iterator<String> refs;
    
    public MatchIterator(LazyList<String> lazy)
    {
        this.refs = lazy.iterator();
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
            
            return MatchV5API.getInstance().getMatch(region, id);
        }
    }
}
