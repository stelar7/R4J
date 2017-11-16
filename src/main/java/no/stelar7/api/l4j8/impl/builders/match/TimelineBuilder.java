package no.stelar7.api.l4j8.impl.builders.match;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.match.MatchTimeline;

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
                                                       .withEndpoint(URLEndpoint.V3_TIMELINE)
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_TIMELINE, this.id, this.platform);
        if (chl.isPresent())
        {
            return (MatchTimeline) chl.get();
        }
        
        MatchTimeline timeline = (MatchTimeline) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_TIMELINE, timeline, this.id, this.platform);
        return timeline;
    }
}
