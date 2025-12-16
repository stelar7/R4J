package no.stelar7.api.r4j.impl.lol.builders.matchv5.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;

import java.util.*;

public class ReplayBuilder
{
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
        return new ReplayBuilder(this.platform, id);
    }
    
    public ReplayBuilder withPlatform(RegionShard platform)
    {
        return new ReplayBuilder(platform, this.id);
    }
    
    public LOLMatchReplayList get()
    {
        if (this.id.isEmpty() || this.platform == RegionShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.id)
                                                       .withEndpoint(URLEndpoint.V5_MATCHLIST_REPLAYS)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("puuid", this.id);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V5_MATCHLIST_REPLAYS, data);
        if (chl.isPresent())
        {
            return (LOLMatchReplayList) chl.get();
        }
        
        try
        {
            LOLMatchReplayList replays = (LOLMatchReplayList) builder.build();
            
            data.put("value", replays);
            DataCall.getCacheProvider().store(URLEndpoint.V5_MATCHLIST_REPLAYS, data);
            
            return replays;
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
