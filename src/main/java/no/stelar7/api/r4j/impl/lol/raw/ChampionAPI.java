package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
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
    
    @Nullable
    public ChampionRotationInfo getFreeToPlayRotation(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V3_CHAMPION_ROTATIONS)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        return builder.buildCached(ChampionRotationInfo.class, data);
    }
    
    /**
     * Like {@link #getFreeToPlayRotation}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<ChampionRotationInfo> getFreeToPlayRotationAsync(LeagueShard server)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V3_CHAMPION_ROTATIONS)
                                                           .withPlatform(server);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
        
            return builder.buildCachedAsync(ChampionRotationInfo.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
