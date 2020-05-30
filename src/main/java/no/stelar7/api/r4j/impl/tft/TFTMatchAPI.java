package no.stelar7.api.r4j.impl.tft;

import no.stelar7.api.r4j.basic.constants.api.regions.RegionalName;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;
import no.stelar7.api.r4j.pojo.tft.*;

import java.util.*;

public class TFTMatchAPI
{
    private static final TFTMatchAPI INSTANCE = new TFTMatchAPI();
    
    public static TFTMatchAPI getInstance()
    {
        return TFTMatchAPI.INSTANCE;
    }
    
    private TFTMatchAPI()
    {
        // Hide public constructor
    }
    
    public List<String> getMatchList(RegionalName server, String PUUID, int count)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_MATCHLIST)
                                                       .withQueryParameter(Constants.COUNT_PLACEHOLDER_DATA, String.valueOf(count))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("puuid", PUUID);
        data.put("count", count);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_MATCHLIST, data);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return Collections.emptyList();
        }
        
        List<String> matchList = (List<String>) matchObj;
        
        data.put("value", matchList);
        DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_MATCHLIST, data);
        
        return matchList;
    }
    
    
    /**
     * Returns an iterator that transforms all {@code MatchReference} to {@code Match}
     *
     * @param server the platform the account is on
     * @param puuid  the account to check
     * @param count the amount of games to fetch
     * @return {@code MatchIterator}
     */
    public MatchIterator getMatchIterator(RegionalName server, String puuid, int count)
    {
        return new MatchIterator(getMatchList(server, puuid, count));
    }
    
    public TFTMatch getMatch(RegionalName platform, String gameId)
    {
        return getMatchRAW(platform, gameId).toTFTMatch();
    }
    
    public TFTMetadata getMetadata(RegionalName platform, String gameId)
    {
        return getMatchRAW(platform, gameId).toTFTMetadata();
    }
    
    public GAMHSMatch getMatchRAW(RegionalName platform, String gameId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_MATCH)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", platform);
        data.put("gameid", gameId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_MATCH, data);
        if (chl.isPresent())
        {
            return (GAMHSMatch) chl.get();
        }
        
        try
        {
            GAMHSMatch match = (GAMHSMatch) builder.build();
            
            data.put("value", match);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_MATCH, data);
            
            return match;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
