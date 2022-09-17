package no.stelar7.api.r4j.impl.lol.builders.champion;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.champion.ChampionRotationInfo;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class ChampionBuilder
{
    private final LeagueShard platform;
    
    public ChampionBuilder()
    {
        this.platform = LeagueShard.UNKNOWN;
    }
    
    private ChampionBuilder(LeagueShard platform)
    {
        this.platform = platform;
    }
    
    public ChampionBuilder withPlatform(LeagueShard platform)
    {
        return new ChampionBuilder(platform);
    }
    
    public ChampionRotationInfo getFreeToPlayRotation()
    {
        if (this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPION_ROTATIONS)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", this.platform);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPION_ROTATIONS, data);
        if (chl.isPresent())
        {
            return (ChampionRotationInfo) chl.get();
        }
        
        try
        {
            ChampionRotationInfo cl = (ChampionRotationInfo) builder.build();
            
            data.put("value", cl);
            DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPION_ROTATIONS, data);
            
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
}
