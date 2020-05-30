package no.stelar7.api.r4j.impl.lol.builders.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.lol.match.*;

import java.util.*;

public class MatchListBuilder
{
    
    private final LeagueShard platform;
    private       String      id;
    private final Integer  beginIndex;
    private final Integer  endIndex;
    
    public MatchListBuilder()
    {
        this.platform = LeagueShard.UNKNOWN;
        this.id = null;
        this.beginIndex = null;
        this.endIndex = null;
    }
    
    public MatchListBuilder(LeagueShard platform, String id, Integer beginIndex, Integer endIndex)
    {
        this.platform = platform;
        this.id = id;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }
    
    public MatchListBuilder withPlatform(LeagueShard platform)
    {
        return new MatchListBuilder(platform, this.id, this.beginIndex, this.endIndex);
    }
    
    public MatchListBuilder withAccountId(String accountId)
    {
        return new MatchListBuilder(this.platform, accountId, this.beginIndex, this.endIndex);
    }
    
    public MatchListBuilder withBeginIndex(Integer index)
    {
        return new MatchListBuilder(this.platform, this.id, index, this.endIndex);
    }
    
    public MatchListBuilder withEndIndex(Integer index)
    {
        return new MatchListBuilder(this.platform, this.id, this.beginIndex, index);
    }
    
    
    /**
     * Returns a list of the games played on this account with the given filters<br>
     * Returns an empty list if {@code id < 0} or platform is not set<br>
     * A number of optional parameters are provided for filtering.<br>
     * <br>
     * It is up to the caller to ensure that the combination of filter parameters provided is valid for the requested account, otherwise, no matches may be returned.<br>
     * If beginIndex is specified, but not endIndex, then endIndex defaults to beginIndex+100.<br>
     * If endIndex is specified, but not beginIndex, then beginIndex defaults to 0.<br>
     * If both are specified, then endIndex must be greater than beginIndex.<br>
     * The maximum range allowed is 100, otherwise a 400 error code is returned.<br>
     * If beginTime is specified, but not endTime, then these parameters are ignored.<br>
     * If endTime is specified, but not beginTime, then beginTime defaults to the start of the account's match history.<br>
     * If both are specified, then endTime should be greater than beginTime.<br>
     * The maximum time range allowed is one week, otherwise a 400 error code is returned.<br>
     *
     * @return MatchList
     */
    public List<MatchReference> get()
    {
        if (this.id.length() < 0 || this.platform == LeagueShard.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, this.id)
                                                       .withEndpoint(URLEndpoint.V4_MATCHLIST)
                                                       .withPlatform(this.platform);
        
        
        if (this.beginIndex != null)
        {
            builder.withQueryParameter(Constants.BEGININDEX_PLACEHOLDER_DATA, String.valueOf(this.beginIndex));
        }
        if (this.endIndex != null)
        {
            if ((this.beginIndex != null ? this.beginIndex : 0) + 100 - this.endIndex < 0)
            {
                throw new IllegalArgumentException("begin-endindex out of range! (difference between beginIndex and endIndex is more than 100)");
            }
            
            builder.withQueryParameter(Constants.ENDINDEX_PLACEHOLDER_DATA, String.valueOf(this.endIndex));
        }
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", platform);
        data.put("accountid", id);
        data.put("beginindex", beginIndex);
        data.put("endindex", endIndex);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_MATCHLIST, data);
        if (chl.isPresent())
        {
            return ((MatchList) chl.get()).getMatches();
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return Collections.emptyList();
        }
        
        MatchList match = (MatchList) matchObj;
        
        data.put("value", match);
        DataCall.getCacheProvider().store(URLEndpoint.V4_MATCHLIST, data);
        
        return match.getMatches();
    }
    
    /**
     * Returns a list of the games played on this account with the given filters <br>
     * This list is updated lazily!<br>
     * Returns an empty list if {@code id < 0} or platform is not set<br>
     * <br>
     * <br>
     * A number of optional parameters are provided for filtering.<br>
     * It is up to the caller to ensure that the combination of filter parameters provided is valid for the requested account, otherwise, no matches may be returned.<br>
     * If beginIndex is specified, but not endIndex, then endIndex defaults to beginIndex+100.<br>
     * If endIndex is specified, but not beginIndex, then beginIndex defaults to 0.<br>
     * If both are specified, then endIndex must be greater than beginIndex.<br>
     * The maximum range allowed is 100, otherwise a 400 error code is returned.<br>
     * If beginTime is specified, but not endTime, then these parameters are ignored.<br>
     * If endTime is specified, but not beginTime, then beginTime defaults to the start of the account's match history.<br>
     * If both are specified, then endTime should be greater than beginTime.<br>
     *
     * @return MatchList
     */
    public LazyList<MatchReference> getLazy()
    {
        int increment = 100;
        return new LazyList<>(increment, prevValue -> {
            Integer begin = (this.beginIndex != null ? this.beginIndex : 0) + prevValue;
            Integer end   = (this.endIndex != null ? this.endIndex : 0) + increment + prevValue;
            
            MatchListBuilder local = new MatchListBuilder(this.platform, this.id, begin, end);
            return local.get();
        });
        
    }
    
    /**
     * Returns an iterator that transforms all {@code MatchReference} to {@code Match}
     *
     * @return {@code MatchIterator}
     */
    public MatchIterator getMatchIterator()
    {
        return new MatchIterator(getLazy());
    }
    
}
