package no.stelar7.api.r4j.impl.lol.builders.matchv5.match;

import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;

import java.util.*;

public class MatchBuilder
{
    private R4JContext context = R4JContext.getDefault();

    /**
     * Uses the credentials, cache and limiters of the context instead of the default ones
     *
     * @param context the context to use
     * @return this
     */
    public MatchBuilder withContext(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
        return this;
    }

    
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
        return new MatchBuilder(this.platform, id).withContext(this.context);
    }
    
    public MatchBuilder withPlatform(RegionShard platform)
    {
        return new MatchBuilder(platform, this.id).withContext(this.context);
    }
    
    @Nullable
    public LOLMatch getMatch()
    {
        GAMHSMatch rawMatch = getMatchRAW();
        
        if (rawMatch == null) {
            return null;
        }
        
        return context.bind(rawMatch.asLOLMatch());
    }
    
    @Nullable
    public LOLMetadata getMetadata()
    {
        GAMHSMatch rawMatch = getMatchRAW();
    
        if (rawMatch == null) {
            return null;
        }
        
        return rawMatch.asLOLMatchMetadata();
    }
    
    @Nullable
    public GAMHSMatch getMatchRAW()
    {
        if (this.id.isEmpty() || this.platform == RegionShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, this.id)
                                                       .withEndpoint(URLEndpoint.V5_MATCH)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", this.platform);
        data.put("gameid", this.id);
        
        return builder.buildCached(GAMHSMatch.class, data);
    }
}
