package no.stelar7.api.r4j.impl.lol.builders.matchv5.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchIterator;

import java.util.*;

public class MatchListBuilder
{
    
    private final RegionShard platform;
    private final String      puuid;
    private final Integer     start;
    private final Integer     count;
    
    public MatchListBuilder()
    {
        this.platform = RegionShard.UNKNOWN;
        this.puuid = null;
        this.start = null;
        this.count = null;
    }
    
    public MatchListBuilder(RegionShard platform, String puuid, Integer start, Integer count)
    {
        this.platform = platform;
        this.puuid = puuid;
        this.start = start;
        this.count = count;
    }
    
    public MatchListBuilder(LeagueShard platform, String puuid, Integer start, Integer count)
    {
        this.platform = platform.toRegionShard();
        this.puuid = puuid;
        this.start = start;
        this.count = count;
    }
    
    public MatchListBuilder withPlatform(LeagueShard platform)
    {
        return new MatchListBuilder(platform, this.puuid, this.start, this.count);
    }
    
    public MatchListBuilder withPuuid(String puuid)
    {
        return new MatchListBuilder(this.platform, puuid, this.start, this.count);
    }
    
    public MatchListBuilder withBeginIndex(Integer start)
    {
        return new MatchListBuilder(this.platform, this.puuid, start, this.count);
    }
    
    public MatchListBuilder withCount(Integer count)
    {
        return new MatchListBuilder(this.platform, this.puuid, this.start, count);
    }
    
    public List<String> get()
    {
        if (this.puuid.length() < 0 || this.platform == RegionShard.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                       .withEndpoint(URLEndpoint.V5_MATCHLIST)
                                                       .withPlatform(this.platform);
        
        
        if (this.start != null)
        {
            builder.withQueryParameter(Constants.START_PLACEHOLDER_DATA, String.valueOf(this.start));
        }
        if (this.count != null)
        {
            builder.withQueryParameter(Constants.COUNT_PLACEHOLDER_DATA, String.valueOf(this.count));
        }
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", platform);
        data.put("puuid", puuid);
        data.put("start", start);
        data.put("count", count);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V5_MATCHLIST, data);
        if (chl.isPresent())
        {
            return ((List<String>) chl.get());
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return Collections.emptyList();
        }
        
        List<String> match = (List<String>) matchObj;
        
        data.put("value", match);
        DataCall.getCacheProvider().store(URLEndpoint.V5_MATCHLIST, data);
        
        return match;
    }
    
    public LazyList<String> getLazy()
    {
        int increment = 100;
        
        return new LazyList<>(increment, prevValue -> {
            Integer          start = (this.start != null ? this.start : 0) + prevValue;
            MatchListBuilder local = new MatchListBuilder(this.platform, this.puuid, start, increment);
            return local.get();
        });
        
    }
    
    public MatchIterator getMatchIterator()
    {
        return new MatchIterator(getLazy());
    }
    
}
