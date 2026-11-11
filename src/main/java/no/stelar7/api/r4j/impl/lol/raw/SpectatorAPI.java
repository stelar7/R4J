package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.spectator.*;

import java.util.*;

public final class SpectatorAPI
{
    
    private static final SpectatorAPI INSTANCE = new SpectatorAPI(R4JContext.getDefault());
    
    public static SpectatorAPI getInstance()
    {
        return SpectatorAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static SpectatorAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(SpectatorAPI.class, SpectatorAPI::new);
    }

    private final R4JContext context;

    private SpectatorAPI(R4JContext context)
    {
        this.context = context;
    }
    
    
    /**
     * The response object contains the FeaturedGames on the platform.
     *
     * @param server the region to execute against
     * @return Optional FeaturedGames
     * @deprecated featured-games is no longer listed in the Riot api documentation for spectator-v5
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public List<SpectatorGameInfo> getFeaturedGames(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V5_SPECTATOR_FEATURED)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new HashMap<>();
        data.put("platform", server);
        
        FeaturedGames fg = builder.buildCached(FeaturedGames.class, data);
        return fg != null ? fg.getGameList() : Collections.emptyList();
    }
    
    /**
     * The response object contains the CurrentGame of the summoner.
     * <p>
     * returns null if not in game
     *
     * @param server the region to execute against
     * @param puuid  the puuid
     * @return SpectatorGameInfo
     */
    @Nullable
    public SpectatorGameInfo getCurrentGame(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withEndpoint(URLEndpoint.V5_SPECTATOR_CURRENT)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("summoner", puuid);
        
        return builder.buildCached(SpectatorGameInfo.class, data);
    }
    
    /**
     * Like {@link #getCurrentGame}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<SpectatorGameInfo> getCurrentGameAsync(LeagueShard server, String puuid)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                           .withEndpoint(URLEndpoint.V5_SPECTATOR_CURRENT)
                                                           .withPlatform(server);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("summoner", puuid);
        
            return builder.buildCachedAsync(SpectatorGameInfo.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
