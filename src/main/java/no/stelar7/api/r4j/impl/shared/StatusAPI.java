package no.stelar7.api.r4j.impl.shared;

import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;

import java.util.*;

public final class StatusAPI
{
    private static final StatusAPI INSTANCE = new StatusAPI(R4JContext.getDefault());
    
    public static StatusAPI getInstance()
    {
        return StatusAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static StatusAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(StatusAPI.class, StatusAPI::new);
    }

    private final R4JContext context;

    private StatusAPI(R4JContext context)
    {
        this.context = context;
    }
    
    @Nullable
    public PlatformData getShardStatus(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V4_STATUS_LOL)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        return builder.buildCached(PlatformData.class, data);
    }
    
    /**
     * The status of Teamfight Tactics on the platform (uses the TFT api key)
     *
     * @param server the platform to get the status of
     * @return PlatformData
     */
    @Nullable
    public PlatformData getTFTShardStatus(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_STATUS_TFT)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);

        return builder.buildCached(PlatformData.class, data);
    }

    @Nullable
    public PlatformData getShardStatus(RuneterraShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_STATUS_LOR)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        return builder.buildCached(PlatformData.class, data);
    }
    
    @Nullable
    public PlatformData getShardStatus(ValorantShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_STATUS_VAL)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        return builder.buildCached(PlatformData.class, data);
    }
    
}
