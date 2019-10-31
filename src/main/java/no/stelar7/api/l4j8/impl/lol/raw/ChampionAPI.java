package no.stelar7.api.l4j8.impl.lol.raw;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.champion.*;

import java.util.*;

@SuppressWarnings("unchecked")
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
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPION_ROTATIONS, server);
        if (chl.isPresent())
        {
            return (ChampionRotationInfo) chl.get();
        }
        
        try
        {
            ChampionRotationInfo cl = (ChampionRotationInfo) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPION_ROTATIONS, cl, server);
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
