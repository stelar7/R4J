package no.stelar7.api.r4j.impl.lol.builders.matchv5.match;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;

import java.util.*;

public class TimelineBuilder
{
    private R4JContext context = R4JContext.getDefault();

    /**
     * Uses the credentials, cache and limiters of the context instead of the default ones
     *
     * @param context the context to use
     * @return this
     */
    public TimelineBuilder withContext(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
        return this;
    }

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
        return new TimelineBuilder(this.platform, id).withContext(this.context);
    }
    
    public TimelineBuilder withPlatform(RegionShard platform)
    {
        return new TimelineBuilder(platform, this.id).withContext(this.context);
    }
    
    @Nullable
    public LOLTimeline getTimeline()
    {
        GAMHSMatch rawMatch = getTimelineRAW();
    
        if (rawMatch == null) {
            return null;
        }
    
        return context.bind(rawMatch.asLOLTimeline());
    }
    
    @Nullable
    public LOLMetadata getMetadata()
    {
        GAMHSMatch rawMatch = getTimelineRAW();
    
        if (rawMatch == null) {
            return null;
        }
        
        return rawMatch.asLOLMatchMetadata();
    }
    
    @Nullable
    public GAMHSMatch getTimelineRAW()
    {
        if (this.id.isEmpty() || this.platform == RegionShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, this.id)
                                                       .withEndpoint(URLEndpoint.V5_TIMELINE)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("matchId", this.id);
        
        return builder.buildCached(GAMHSMatch.class, data);
    }
    
    public RegionShard getPlatform()
    {
        return this.platform;
    }
    
    public String getID()
    {
        return this.id;
    }
    
    /**
     * Like {@link #getTimeline}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<LOLTimeline> getTimelineAsync()
    {
        return getTimelineRAWAsync().thenApply(raw -> raw == null ? null : context.bind(raw.asLOLTimeline()));
    }

    /**
     * Like {@link #getTimelineRAW}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<GAMHSMatch> getTimelineRAWAsync()
    {
        try
        {
            if (this.id.isEmpty() || this.platform == RegionShard.UNKNOWN)
            {
                return CompletableFuture.completedFuture(null);
            }

            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, this.id)
                                                           .withEndpoint(URLEndpoint.V5_TIMELINE)
                                                           .withPlatform(this.platform);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", this.platform);
            data.put("matchId", this.id);

            return builder.buildCachedAsync(GAMHSMatch.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
