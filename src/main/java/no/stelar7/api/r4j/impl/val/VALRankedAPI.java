package no.stelar7.api.r4j.impl.val;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.pojo.val.ranked.Leaderboard;

import java.util.*;

public class VALRankedAPI
{
    private static final VALRankedAPI INSTANCE = new VALRankedAPI(R4JContext.getDefault());
    
    public static VALRankedAPI getInstance()
    {
        return VALRankedAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static VALRankedAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(VALRankedAPI.class, VALRankedAPI::new);
    }

    private final R4JContext context;

    private VALRankedAPI(R4JContext context)
    {
        this.context = context;
    }
    
    @Nullable
    public Leaderboard getLeaderboard(ValorantShard platform, String actId, int startIndex, int size)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.ACT_ID_PLACEHOLDER, actId)
                                                       .withQueryParameter(Constants.STARTINDEX_PLACEHOLDER_DATA, String.valueOf(startIndex))
                                                       .withQueryParameter(Constants.SIZE_PLACEHOLDER_DATA, String.valueOf(size))
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_LEADERBOARD_BY_ACT)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("actId", actId);
        data.put("startIndex", startIndex);
        data.put("size", size);
        
        return builder.buildCached(Leaderboard.class, data);
    }
    
    /**
     * Like {@link #getLeaderboard}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Leaderboard> getLeaderboardAsync(ValorantShard platform, String actId, int startIndex, int size)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.ACT_ID_PLACEHOLDER, actId)
                                                           .withQueryParameter(Constants.STARTINDEX_PLACEHOLDER_DATA, String.valueOf(startIndex))
                                                           .withQueryParameter(Constants.SIZE_PLACEHOLDER_DATA, String.valueOf(size))
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                           .withEndpoint(URLEndpoint.V1_VAL_LEADERBOARD_BY_ACT)
                                                           .withPlatform(platform);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", platform);
            data.put("actId", actId);
            data.put("startIndex", startIndex);
            data.put("size", size);
        
            return builder.buildCachedAsync(Leaderboard.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
