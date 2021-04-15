package no.stelar7.api.r4j.impl.lol.builders.matchv5.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;

import java.util.*;

public class MatchBuilder
{
    
    private final String      id;
    private final RegionShard platform;
    
    public MatchBuilder()
    {
        this(RegionShard.UNKNOWN);
    }
    
    public MatchBuilder(RegionShard shard)
    {
        this(shard, null);
    }
    
    public MatchBuilder(LeagueShard shard)
    {
        this(shard.toRegionShard(), null);
    }
    
    public MatchBuilder(LeagueShard platform, String id)
    {
        this(platform.toRegionShard(), id);
    }
    
    public MatchBuilder(RegionShard platform, String id)
    {
        this.id = id;
        this.platform = platform;
    }
    
    public MatchBuilder withId(String id)
    {
        return new MatchBuilder(this.platform, id);
    }
    
    public MatchBuilder withPlatform(RegionShard platform)
    {
        return new MatchBuilder(platform, this.id);
    }
    
    public LOLMatch getMatch()
    {
        return getMatchRAW().asLOLMatch();
    }
    
    public LOLMetadata getMetadata()
    {
        return getMatchRAW().asLOLMatchMetadata();
    }
    
    public GAMHSMatch getMatchRAW()
    {
        if (this.id.isEmpty() || this.platform == RegionShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, this.id)
                                                       .withEndpoint(URLEndpoint.V5_MATCH)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", this.platform);
        data.put("gameid", this.id);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V5_MATCH, data);
        if (chl.isPresent())
        {
            return (GAMHSMatch) chl.get();
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return null;
        }
        
        GAMHSMatch match = (GAMHSMatch) matchObj;
        
        data.put("value", match);
        DataCall.getCacheProvider().store(URLEndpoint.V5_MATCH, data);
        
        return match;
    }
}
