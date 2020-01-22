package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.pojo.lol.champion.ChampionRotationInfo;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;

import java.util.*;

public final class ChampionAPI
{
    private static final ChampionAPI INSTANCE = new ChampionAPI();
    
    public static ChampionAPI getInstance()
    {
        return ChampionAPI.INSTANCE;
    }
    
    private ChampionAPI()
    {
        // Hide public constructor
    }
    
    public ChampionRotationInfo getFreeToPlayRotation(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPION_ROTATIONS)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
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
