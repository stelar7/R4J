package no.stelar7.api.r4j.impl.lol.builders.matchv5.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;

import java.util.*;

public class TimelineBuilder
{
    private final String      id;
    private final RegionShard platform;
    
    public TimelineBuilder()
    {
        this(RegionShard.UNKNOWN);
    }
    
    public TimelineBuilder(RegionShard shard)
    {
        this(shard, null);
    }
    
    public TimelineBuilder(LeagueShard shard)
    {
        this(shard.toRegionShard(), null);
    }
    
    public TimelineBuilder(LeagueShard platform, String id)
    {
        this(platform.toRegionShard(), id);
    }
    
    public TimelineBuilder(RegionShard platform, String id)
    {
        this.id = id;
        this.platform = platform;
    }
    
    
    public TimelineBuilder withId(String id)
    {
        return new TimelineBuilder(this.platform, id);
    }
    
    public TimelineBuilder withPlatform(RegionShard platform)
    {
        return new TimelineBuilder(platform, this.id);
    }
    
    public LOLTimeline getTimeline()
    {
        GAMHSMatch rawMatch = getTimelineRAW();
    
        if (rawMatch == null) {
            return null;
        }
    
        return rawMatch.asLOLTimeline();
    }
    
    public LOLMetadata getMetadata()
    {
        GAMHSMatch rawMatch = getTimelineRAW();
    
        if (rawMatch == null) {
            return null;
        }
        
        return rawMatch.asLOLMatchMetadata();
    }
    
    public GAMHSMatch getTimelineRAW()
    {
        if (this.id.isEmpty() || this.platform == RegionShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, this.id)
                                                       .withEndpoint(URLEndpoint.V5_TIMELINE)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("matchId", this.id);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V5_TIMELINE, data);
        if (chl.isPresent())
        {
            return (GAMHSMatch) chl.get();
        }
        
        try
        {
            GAMHSMatch timeline = (GAMHSMatch) builder.build();
            
            data.put("value", timeline);
            DataCall.getCacheProvider().store(URLEndpoint.V5_TIMELINE, data);
            
            return timeline;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    public RegionShard getPlatform()
    {
        return this.platform;
    }
    
    public String getID()
    {
        return this.id;
    }
}
