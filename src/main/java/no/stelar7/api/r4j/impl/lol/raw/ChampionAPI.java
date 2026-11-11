package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.champion.ChampionRotationInfo;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;

import java.util.*;

public final class ChampionAPI
{
    private static final ChampionAPI INSTANCE = new ChampionAPI(R4JContext.getDefault());
    
    public static ChampionAPI getInstance()
    {
        return ChampionAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static ChampionAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(ChampionAPI.class, ChampionAPI::new);
    }

    private final R4JContext context;

    private ChampionAPI(R4JContext context)
    {
        this.context = context;
    }
    
    public ChampionRotationInfo getFreeToPlayRotation(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V3_CHAMPION_ROTATIONS)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        Optional<?> chl = context.getCacheProvider().get(URLEndpoint.V3_CHAMPION_ROTATIONS, data);
        if (chl.isPresent())
        {
            return (ChampionRotationInfo) chl.get();
        }
        
        try
        {
            ChampionRotationInfo cl = (ChampionRotationInfo) builder.build();
            
            data.put("value", cl);
            context.getCacheProvider().store(URLEndpoint.V3_CHAMPION_ROTATIONS, data);
            
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
