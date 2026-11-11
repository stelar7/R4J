package no.stelar7.api.r4j.impl.lor;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.RuneterraShard;
import no.stelar7.api.r4j.pojo.lor.ranked.*;

import java.util.*;

public class LORRankedAPI
{
    private static final LORRankedAPI INSTANCE = new LORRankedAPI(R4JContext.getDefault());
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static LORRankedAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(LORRankedAPI.class, LORRankedAPI::new);
    }

    private final R4JContext context;

    private LORRankedAPI(R4JContext context)
    {
        this.context = context;
    }
    
    public static LORRankedAPI getInstance()
    {
        return LORRankedAPI.INSTANCE;
    }
    
    /**
     * Gets a list of all ranked players
     *
     * @param server region to get data from
     * @return LeagueList
     */
    @Nullable
    public List<LoRPlayerRank> getLeaderboard(RuneterraShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getLORAPIKey())
                .withEndpoint(URLEndpoint.V1_LOR_RANKED_LEADERBOARD)
                .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        LoRRankedPlayerList list = builder.buildCached(LoRRankedPlayerList.class, data);
        return list != null ? list.getPlayers() : null;
    }
    
    /**
     * Like {@link #getLeaderboard}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<LoRPlayerRank>> getLeaderboardAsync(RuneterraShard server)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getLORAPIKey())
                    .withEndpoint(URLEndpoint.V1_LOR_RANKED_LEADERBOARD)
                    .withPlatform(server);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
        
            return builder.buildCachedAsync(LoRRankedPlayerList.class, data).thenApply(list -> list != null ? list.getPlayers() : null);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
