package no.stelar7.api.r4j.impl.lol.raw;

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
    
    public ChallengeConfigInfoList getChallengesList(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_CONFIG)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        return builder.buildCached(ChallengeConfigInfoList.class, data);
    }
    
    public MultiChallengePercentileMap getAllChallengePercentiles(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_PERCENTILES)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        return builder.buildCached(MultiChallengePercentileMap.class, data);
    }
    
    public ChallengeConfigInfo getChallengeInfo(LeagueShard server, Long challengeId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_CONFIG_BY_ID)
                                                       .withURLParameter(Constants.CHALLENGE_ID_PLACEHOLDER, String.valueOf(challengeId))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("challenge", challengeId);
        
        return builder.buildCached(ChallengeConfigInfo.class, data);
    }
    
    public ChallengeLeaderboardInfoList getChallengeLeaderboard(LeagueShard server, Long challengeId, ChallengeTierType level, Integer limit)
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
        
        return builder.buildCached(ChallengeLeaderboardInfoList.class, data);
    }
    
    public MultiChallengePercentileMap getChallengePercentiles(LeagueShard server, Long challengeId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_PERCENTILES)
                                                       .withURLParameter(Constants.CHALLENGE_ID_PLACEHOLDER, String.valueOf(challengeId))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("challenge", challengeId);
        
        return builder.buildCached(MultiChallengePercentileMap.class, data);
    }
    
    public ChallengePlayerInfo getChallengePlayerInfo(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CHALLENGES_BY_PLAYER)
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        
        return builder.buildCached(ChallengePlayerInfo.class, data);
    }
}
