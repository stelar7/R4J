package no.stelar7.api.r4j.impl.tft;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;

import java.util.*;

public final class TFTSpectatorAPI
{
    private static final TFTSpectatorAPI INSTANCE = new TFTSpectatorAPI(R4JContext.getDefault());

    public static TFTSpectatorAPI getInstance()
    {
        return TFTSpectatorAPI.INSTANCE;
    }

    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static TFTSpectatorAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(TFTSpectatorAPI.class, TFTSpectatorAPI::new);
    }

    private final R4JContext context;

    private TFTSpectatorAPI(R4JContext context)
    {
        this.context = context;
    }

    /**
     * The game the player is currently in.
     *
     * @param server the region to execute against
     * @param puuid  the puuid of the player
     * @return SpectatorGameInfo, or null if the player is not in a game
     */
    @Nullable
    public SpectatorGameInfo getCurrentGame(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V5_TFT_SPECTATOR_CURRENT)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);

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
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                           .withEndpoint(URLEndpoint.V5_TFT_SPECTATOR_CURRENT)
                                                           .withPlatform(server);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("puuid", puuid);

            return builder.buildCachedAsync(SpectatorGameInfo.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
