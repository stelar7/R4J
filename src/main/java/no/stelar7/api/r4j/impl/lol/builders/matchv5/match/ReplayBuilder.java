package no.stelar7.api.r4j.impl.lol.builders.matchv5.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;

import java.util.*;

/**
 * @deprecated the replays of a player by puuid are no longer listed in the Riot api documentation;
 * use {@link no.stelar7.api.r4j.impl.lol.raw.MatchV5API#getMatchReplaysByAccessToken(RegionShard, String)}
 */
@Deprecated(since = "3.0.0", forRemoval = true)
public class ReplayBuilder
{
    private R4JContext context = R4JContext.getDefault();

    /**
     * Uses the credentials, cache and limiters of the context instead of the default ones
     *
     * @param context the context to use
     * @return this
     */
    public ReplayBuilder withContext(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
        return this;
    }

    private final String      id;
    private final RegionShard platform;
    
    public ReplayBuilder()
    {
        this(RegionShard.UNKNOWN);
    }
    
    public ReplayBuilder(RegionShard shard)
    {
        this(shard, null);
    }
    
    public ReplayBuilder(LeagueShard shard)
    {
        this(shard.toRegionShard(), null);
    }
    
    public ReplayBuilder(LeagueShard platform, String id)
    {
        this(platform.toRegionShard(), id);
    }
    
    public ReplayBuilder(RegionShard platform, String id)
    {
        this.id = id;
        this.platform = platform;
    }
    
    
    public ReplayBuilder withPuuid(String id)
    {
        return new ReplayBuilder(this.platform, id).withContext(this.context);
    }
    
    public ReplayBuilder withPlatform(RegionShard platform)
    {
        return new ReplayBuilder(platform, this.id).withContext(this.context);
    }
    
    public LOLMatchReplayList get()
    {
        if (this.id.isEmpty() || this.platform == RegionShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.id)
                                                       .withEndpoint(URLEndpoint.V5_MATCHLIST_REPLAYS)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("puuid", this.id);
        
        return builder.buildCached(LOLMatchReplayList.class, data);
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
