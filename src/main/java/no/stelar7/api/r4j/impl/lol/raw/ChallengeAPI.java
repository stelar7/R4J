package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.ChallengeTierType;
import no.stelar7.api.r4j.pojo.lol.challenges.*;

import java.util.*;

public final class ChallengeAPI
{
    private static final ChallengeAPI INSTANCE = new ChallengeAPI();
    
    public static ChallengeAPI getInstance()
    {
        return ChallengeAPI.INSTANCE;
    }
    
    private ChallengeAPI()
    {
        // Hide public constructor
    }
    
    public ChallengeConfigInfoList getChallengesList(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V1_CHALLENGES_CONFIG)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_CHALLENGES_CONFIG, data);
        if (chl.isPresent())
        {
            return (ChallengeConfigInfoList) chl.get();
        }
        
        try
        {
            ChallengeConfigInfoList cl = (ChallengeConfigInfoList) builder.build();
            
            data.put("value", cl);
            DataCall.getCacheProvider().store(URLEndpoint.V1_CHALLENGES_CONFIG, data);
            
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public MultiChallengePercentileMap getAllChallengePercentiles(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V1_CHALLENGES_PERCENTILES)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_CHALLENGES_PERCENTILES, data);
        if (chl.isPresent())
        {
            return (MultiChallengePercentileMap) chl.get();
        }
        
        try
        {
            MultiChallengePercentileMap cl = (MultiChallengePercentileMap) builder.build();
            
            data.put("value", cl);
            DataCall.getCacheProvider().store(URLEndpoint.V1_CHALLENGES_PERCENTILES, data);
            
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public ChallengeConfigInfo getChallengeInfo(LeagueShard server, Long challengeId)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V1_CHALLENGES_CONFIG_BY_ID)
                                                       .withURLParameter(Constants.CHALLENGE_ID_PLACEHOLDER, String.valueOf(challengeId))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("challenge", challengeId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_CHALLENGES_CONFIG_BY_ID, data);
        if (chl.isPresent())
        {
            return (ChallengeConfigInfo) chl.get();
        }
        
        try
        {
            ChallengeConfigInfo cl = (ChallengeConfigInfo) builder.build();
            
            data.put("value", cl);
            DataCall.getCacheProvider().store(URLEndpoint.V1_CHALLENGES_CONFIG_BY_ID, data);
            
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public ChallengeLeaderboardInfoList getChallengeLeaderboard(LeagueShard server, Long challengeId, ChallengeTierType level, Integer limit)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V1_CHALLENGES_LEADERBOARD_BY_LEVEL)
                                                       .withURLParameter(Constants.CHALLENGE_ID_PLACEHOLDER, String.valueOf(challengeId))
                                                       .withURLParameter(Constants.LEVEL_PLACEHOLDER, level.name())
                                                       .withPlatform(server);
        
        if (limit != null)
        {
            builder = builder.withQueryParameter(Constants.LIMIT_PLACEHOLDER_DATA, String.valueOf(limit));
        }
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("challenge", challengeId);
        data.put("level", level);
        data.put("limit", limit);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_CHALLENGES_LEADERBOARD_BY_LEVEL, data);
        if (chl.isPresent())
        {
            return (ChallengeLeaderboardInfoList) chl.get();
        }
        
        try
        {
            ChallengeLeaderboardInfoList cl = (ChallengeLeaderboardInfoList) builder.build();
            
            data.put("value", cl);
            DataCall.getCacheProvider().store(URLEndpoint.V1_CHALLENGES_LEADERBOARD_BY_LEVEL, data);
            
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public MultiChallengePercentileMap getChallengePercentiles(LeagueShard server, Long challengeId)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V1_CHALLENGES_PERCENTILES)
                                                       .withURLParameter(Constants.CHALLENGE_ID_PLACEHOLDER, String.valueOf(challengeId))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("challenge", challengeId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_CHALLENGES_PERCENTILES, data);
        if (chl.isPresent())
        {
            return (MultiChallengePercentileMap) chl.get();
        }
        
        try
        {
            MultiChallengePercentileMap cl = (MultiChallengePercentileMap) builder.build();
            
            data.put("value", cl);
            DataCall.getCacheProvider().store(URLEndpoint.V1_CHALLENGES_PERCENTILES, data);
            
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public ChallengePlayerInfo getChallengePlayerInfo(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V1_CHALLENGES_BY_PLAYER)
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_CHALLENGES_BY_PLAYER, data);
        if (chl.isPresent())
        {
            return (ChallengePlayerInfo) chl.get();
        }
        
        try
        {
            ChallengePlayerInfo cl = (ChallengePlayerInfo) builder.build();
            
            data.put("value", cl);
            DataCall.getCacheProvider().store(URLEndpoint.V1_CHALLENGES_BY_PLAYER, data);
            
            return cl;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
