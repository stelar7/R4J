package no.stelar7.api.r4j.impl.lol.builders.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.match.MatchTimeline;

import java.util.*;

public class TimelineBuilder
{
    private final long        id;
    private final LeagueShard platform;
    
    public TimelineBuilder()
    {
        id = -1;
        platform = LeagueShard.UNKNOWN;
    }
    
    public TimelineBuilder(LeagueShard platform, long id)
    {
        this.id = id;
        this.platform = platform;
    }
    
    public TimelineBuilder withId(long id)
    {
        return new TimelineBuilder(this.platform, id);
    }
    
    public TimelineBuilder withPlatform(LeagueShard platform)
    {
        return new TimelineBuilder(platform, this.id);
    }
    
    /**
     * Returns the timeline relating to a match.
     * Not avaliable for matches older than a year
     *
     * @return MatchTimeline if avaliable
     */
    public MatchTimeline get()
    {
        if (this.id == -1 || this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(this.id))
                                                       .withEndpoint(URLEndpoint.V4_TIMELINE)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", platform);
        data.put("gameid", this.id);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_TIMELINE, data);
        if (chl.isPresent())
        {
            return (MatchTimeline) chl.get();
        }
        
        try
        {
            MatchTimeline timeline = (MatchTimeline) builder.build();
            
            data.put("value", timeline);
            DataCall.getCacheProvider().store(URLEndpoint.V4_TIMELINE, data);
            
            return timeline;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
}
