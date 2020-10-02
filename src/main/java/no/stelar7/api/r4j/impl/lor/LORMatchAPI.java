package no.stelar7.api.r4j.impl.lor;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.RuneterraShard;
import no.stelar7.api.r4j.pojo.lor.match.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;

import java.util.*;

public class LORMatchAPI
{
    private static final LORMatchAPI INSTANCE = new LORMatchAPI();
    
    private LORMatchAPI()
    {
    }
    
    public static LORMatchAPI getInstance()
    {
        return LORMatchAPI.INSTANCE;
    }
    
    public List<String> getMatchList(RuneterraShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getLORAPIKey())
                .withEndpoint(URLEndpoint.V1_LOR_MATCHES_BY_PUUID)
                .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_LOR_MATCHES_BY_PUUID, data);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        
        try
        {
            List<String> list = (List<String>) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_LOR_MATCHES_BY_PUUID, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public LORMatch getMatch(RuneterraShard platform, String gameId)
    {
        return getMatchRAW(platform, gameId).toLORMatch();
    }
    
    public LORMetadata getMetadata(RuneterraShard platform, String gameId)
    {
        return getMatchRAW(platform, gameId).toLORMetadata();
    }
    
    public GAMHSMatch getMatchRAW(RuneterraShard platform, String gameId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_LOR_MATCH)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", platform);
        data.put("gameid", gameId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_LOR_MATCH, data);
        if (chl.isPresent())
        {
            return (GAMHSMatch) chl.get();
        }
        
        try
        {
            GAMHSMatch match = (GAMHSMatch) builder.build();
            
            data.put("value", match);
            DataCall.getCacheProvider().store(URLEndpoint.V1_LOR_MATCH, data);
            
            return match;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
