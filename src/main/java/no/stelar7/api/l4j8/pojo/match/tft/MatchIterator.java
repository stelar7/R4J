package no.stelar7.api.l4j8.pojo.match.tft;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.tft.TFTMatchAPI;

import java.util.*;

public class MatchIterator implements Iterable<TFTMatch>
{
    private Iterator<String> refs;
    
    public MatchIterator(List<String> lazy)
    {
        this.refs = lazy.iterator();
    }
    
    @Override
    public Iterator<TFTMatch> iterator()
    {
        return new LazyListIterator();
    }
    
    private class LazyListIterator implements Iterator<TFTMatch>
    {
        @Override
        public boolean hasNext()
        {
            return refs.hasNext();
        }
        
        @Override
        public TFTMatch next()
        {
            String          id     = refs.next();
            ServicePlatform region = Platform.fromString(id.split("_")[0]).get().toRegionalEnum();
            
            return TFTMatchAPI.getInstance().getMatch(region, id);
        }
    }
}
