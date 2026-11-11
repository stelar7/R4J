package no.stelar7.api.r4j.impl.val;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.basic.constants.types.val.GameQueueType;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.val.match.VALMatch;
import no.stelar7.api.r4j.pojo.val.matchlist.*;

import java.util.*;

public class VALMatchAPI
{
    private static final VALMatchAPI INSTANCE = new VALMatchAPI(R4JContext.getDefault());
    
    public static VALMatchAPI getInstance()
    {
        return VALMatchAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static VALMatchAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(VALMatchAPI.class, VALMatchAPI::new);
    }

    private final R4JContext context;

    private VALMatchAPI(R4JContext context)
    {
        this.context = context;
    }
    
    public List<MatchReference> getMatchList(ValorantShard server, String PUUID)
    {
        DataCallBuilder builder = getMatchListCall(server, PUUID);
        Map<String, Object> data = builder.getCacheKey();
        
        MatchList matchList = builder.buildCached(MatchList.class, data);
        return matchList != null ? matchList.getHistory() : Collections.emptyList();
    }

    // the call getMatchList and its twin make
    private DataCallBuilder getMatchListCall(ValorantShard server, String PUUID)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_MATCHLIST_BY_PUUID)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", PUUID);

        return builder.withCacheKey(data);
    }
    
    
    @Nullable
    public RecentMatchList getRecentMatches(ValorantShard platform, GameQueueType queue)
    {
        DataCallBuilder builder = getRecentMatchesCall(platform, queue);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(RecentMatchList.class, data);
    }

    // the call getRecentMatches and its twin make
    private DataCallBuilder getRecentMatchesCall(ValorantShard platform, GameQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getValue())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_RECENT_MATCH_BY_QUEUE)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("queue", queue);

        return builder.withCacheKey(data);
    }
    
    
    @Nullable
    public VALMatch getMatch(ValorantShard platform, String gameId)
    {
        DataCallBuilder builder = getMatchCall(platform, gameId);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(VALMatch.class, data);
    }

    // the call getMatch and its twin make
    private DataCallBuilder getMatchCall(ValorantShard platform, String gameId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_MATCH_BY_ID)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("gameid", gameId);

        return builder.withCacheKey(data);
    }
    
    /**
     * Like {@link #getMatchList}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<MatchReference>> getMatchListAsync(ValorantShard server, String PUUID)
    {
        try
        {
            DataCallBuilder builder = getMatchListCall(server, PUUID);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(MatchList.class, data).thenApply(matchList -> matchList != null ? matchList.getHistory() : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getRecentMatches}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RecentMatchList> getRecentMatchesAsync(ValorantShard platform, GameQueueType queue)
    {
        try
        {
            DataCallBuilder builder = getRecentMatchesCall(platform, queue);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(RecentMatchList.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getMatch}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<VALMatch> getMatchAsync(ValorantShard platform, String gameId)
    {
        try
        {
            DataCallBuilder builder = getMatchCall(platform, gameId);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(VALMatch.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
