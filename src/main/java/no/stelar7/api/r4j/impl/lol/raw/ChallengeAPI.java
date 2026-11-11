package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.ChallengeTierType;
import no.stelar7.api.r4j.pojo.lol.challenges.*;

import java.util.*;

public final class ChallengeAPI
{
    private static final ChallengeAPI INSTANCE = new ChallengeAPI(R4JContext.getDefault());
    
    public static ChallengeAPI getInstance()
    {
        return ChallengeAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static ChallengeAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(ChallengeAPI.class, ChallengeAPI::new);
    }

    private final R4JContext context;

    private ChallengeAPI(R4JContext context)
    {
        this.context = context;
    }
    
    @Nullable
    public ChallengeConfigInfoList getChallengesList(LeagueShard server)
    {
        DataCallBuilder builder = getChallengesListCall(server);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(ChallengeConfigInfoList.class, data);
    }

    // the call getChallengesList and its twin make
    private DataCallBuilder getChallengesListCall(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_CONFIG)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);

        return builder.withCacheKey(data);
    }
    
    @Nullable
    public MultiChallengePercentileMap getAllChallengePercentiles(LeagueShard server)
    {
        DataCallBuilder builder = getAllChallengePercentilesCall(server);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(MultiChallengePercentileMap.class, data);
    }

    // the call getAllChallengePercentiles and its twin make
    private DataCallBuilder getAllChallengePercentilesCall(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_PERCENTILES)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);

        return builder.withCacheKey(data);
    }
    
    @Nullable
    public ChallengeConfigInfo getChallengeInfo(LeagueShard server, Long challengeId)
    {
        DataCallBuilder builder = getChallengeInfoCall(server, challengeId);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(ChallengeConfigInfo.class, data);
    }

    // the call getChallengeInfo and its twin make
    private DataCallBuilder getChallengeInfoCall(LeagueShard server, Long challengeId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_CONFIG_BY_ID)
                                                       .withURLParameter(Constants.CHALLENGE_ID_PLACEHOLDER, String.valueOf(challengeId))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("challenge", challengeId);

        return builder.withCacheKey(data);
    }
    
    @Nullable
    public ChallengeLeaderboardInfoList getChallengeLeaderboard(LeagueShard server, Long challengeId, ChallengeTierType level, Integer limit)
    {
        DataCallBuilder builder = getChallengeLeaderboardCall(server, challengeId, level, limit);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(ChallengeLeaderboardInfoList.class, data);
    }

    // the call getChallengeLeaderboard and its twin make
    private DataCallBuilder getChallengeLeaderboardCall(LeagueShard server, Long challengeId, ChallengeTierType level, Integer limit)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_LEADERBOARD_BY_LEVEL)
                                                       .withURLParameter(Constants.CHALLENGE_ID_PLACEHOLDER, String.valueOf(challengeId))
                                                       .withURLParameter(Constants.LEVEL_PLACEHOLDER, level.name())
                                                       .withPlatform(server);
        
        if (limit != null)
        {
            builder = builder.withQueryParameter(Constants.LIMIT_PLACEHOLDER_DATA, String.valueOf(limit));
        }
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("challenge", challengeId);
        data.put("level", level);
        data.put("limit", limit);

        return builder.withCacheKey(data);
    }
    
    @Nullable
    public MultiChallengePercentileMap getChallengePercentiles(LeagueShard server, Long challengeId)
    {
        DataCallBuilder builder = getChallengePercentilesCall(server, challengeId);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(MultiChallengePercentileMap.class, data);
    }

    // the call getChallengePercentiles and its twin make
    private DataCallBuilder getChallengePercentilesCall(LeagueShard server, Long challengeId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_PERCENTILES)
                                                       .withURLParameter(Constants.CHALLENGE_ID_PLACEHOLDER, String.valueOf(challengeId))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("challenge", challengeId);

        return builder.withCacheKey(data);
    }
    
    @Nullable
    public ChallengePlayerInfo getChallengePlayerInfo(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = getChallengePlayerInfoCall(server, puuid);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(ChallengePlayerInfo.class, data);
    }

    // the call getChallengePlayerInfo and its twin make
    private DataCallBuilder getChallengePlayerInfoCall(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_BY_PLAYER)
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);

        return builder.withCacheKey(data);
    }
    
    /**
     * Like {@link #getChallengesList}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<ChallengeConfigInfoList> getChallengesListAsync(LeagueShard server)
    {
        try
        {
            DataCallBuilder builder = getChallengesListCall(server);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(ChallengeConfigInfoList.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getAllChallengePercentiles}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<MultiChallengePercentileMap> getAllChallengePercentilesAsync(LeagueShard server)
    {
        try
        {
            DataCallBuilder builder = getAllChallengePercentilesCall(server);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(MultiChallengePercentileMap.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getChallengeInfo}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<ChallengeConfigInfo> getChallengeInfoAsync(LeagueShard server, Long challengeId)
    {
        try
        {
            DataCallBuilder builder = getChallengeInfoCall(server, challengeId);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(ChallengeConfigInfo.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getChallengeLeaderboard}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<ChallengeLeaderboardInfoList> getChallengeLeaderboardAsync(LeagueShard server, Long challengeId, ChallengeTierType level, Integer limit)
    {
        try
        {
            DataCallBuilder builder = getChallengeLeaderboardCall(server, challengeId, level, limit);
            Map<String, Object> data = builder.getCacheKey();

            return builder.buildCachedAsync(ChallengeLeaderboardInfoList.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getChallengePercentiles}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<MultiChallengePercentileMap> getChallengePercentilesAsync(LeagueShard server, Long challengeId)
    {
        try
        {
            DataCallBuilder builder = getChallengePercentilesCall(server, challengeId);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(MultiChallengePercentileMap.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getChallengePlayerInfo}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<ChallengePlayerInfo> getChallengePlayerInfoAsync(LeagueShard server, String puuid)
    {
        try
        {
            DataCallBuilder builder = getChallengePlayerInfoCall(server, puuid);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(ChallengePlayerInfo.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
