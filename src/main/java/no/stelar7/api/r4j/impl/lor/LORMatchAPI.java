package no.stelar7.api.r4j.impl.lor;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.RuneterraShard;
import no.stelar7.api.r4j.pojo.lor.match.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;

import java.util.*;

public class LORMatchAPI
{
    private static final LORMatchAPI INSTANCE = new LORMatchAPI(R4JContext.getDefault());
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static LORMatchAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(LORMatchAPI.class, LORMatchAPI::new);
    }

    private final R4JContext context;

    private LORMatchAPI(R4JContext context)
    {
        this.context = context;
    }
    
    public static LORMatchAPI getInstance()
    {
        return LORMatchAPI.INSTANCE;
    }
    
    public List<String> getMatchList(RuneterraShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getLORAPIKey())
                .withEndpoint(URLEndpoint.V1_LOR_MATCHES_BY_PUUID)
                .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        
        Optional<?> chl = context.getCacheProvider().get(URLEndpoint.V1_LOR_MATCHES_BY_PUUID, data);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        
        try
        {
            List<String> list = (List<String>) builder.build();
            
            data.put("value", list);
            context.getCacheProvider().store(URLEndpoint.V1_LOR_MATCHES_BY_PUUID, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public LORMatch getMatch(RuneterraShard platform, String gameId)
    {
        return getMatchRAW(platform, gameId).asLORMatch();
    }
    
    public LORMetadata getMetadata(RuneterraShard platform, String gameId)
    {
        return getMatchRAW(platform, gameId).asLORMetadata();
    }
    
    public GAMHSMatch getMatchRAW(RuneterraShard platform, String gameId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getLORAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_LOR_MATCH)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("gameid", gameId);
        
        return builder.buildCached(GAMHSMatch.class, data);
    }
}
