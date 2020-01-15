package no.stelar7.api.r4j.impl.lol.builders.champion;

import no.stelar7.api.r4j.pojo.lol.champion.ChampionRotationInfo;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;

import java.util.Optional;

@SuppressWarnings("unchecked")
public class ChampionBuilder
{
    private final Platform platform;
    
    public ChampionBuilder()
    {
        this.platform = Platform.UNKNOWN;
    }
    
    private ChampionBuilder(Platform platform)
    {
        this.platform = platform;
    }
    
    public ChampionBuilder withPlatform(Platform platform)
    {
        return new ChampionBuilder(platform);
    }
    
    public ChampionRotationInfo getFreeToPlayRotation()
    {
        if (this.platform == Platform.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPION_ROTATIONS)
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPION_ROTATIONS, this.platform);
        if (chl.isPresent())
        {
            return (ChampionRotationInfo) chl.get();
        }
        
        try
        {
            ChampionRotationInfo cl = (ChampionRotationInfo) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPION_ROTATIONS, cl, this.platform);
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
}
