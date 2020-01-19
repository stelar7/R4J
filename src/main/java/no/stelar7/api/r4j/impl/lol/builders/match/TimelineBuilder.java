package no.stelar7.api.r4j.impl.lol.builders.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.pojo.lol.match.MatchTimeline;

import java.util.Optional;

public class TimelineBuilder
{
    private final long     id;
    private final Platform platform;
    
    public TimelineBuilder()
    {
        id = -1;
        platform = Platform.UNKNOWN;
    }
    
    private TimelineBuilder(Platform platform, long id)
    {
        this.id = id;
        this.platform = platform;
    }
    
    public TimelineBuilder withId(long id)
    {
        return new TimelineBuilder(this.platform, id);
    }
    
    public TimelineBuilder withPlatform(Platform platform)
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
        if (this.id == -1 || this.platform == Platform.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(this.id))
                                                       .withEndpoint(URLEndpoint.V4_TIMELINE)
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_TIMELINE, this.platform, this.id);
        if (chl.isPresent())
        {
            return (MatchTimeline) chl.get();
        }
        
        try
        {
            MatchTimeline timeline = (MatchTimeline) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V4_TIMELINE, timeline, this.platform, this.id);
            return timeline;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
}
