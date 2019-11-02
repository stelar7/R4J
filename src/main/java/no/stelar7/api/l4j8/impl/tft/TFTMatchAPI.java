package no.stelar7.api.l4j8.impl.tft;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.Pair;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.match.tft.*;

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
    
    public List<String> getMatchList(ServicePlatform server, String PUUID)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                                                       .withEndpoint(URLEndpoint.V1_TFT_MATCHLIST)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_MATCHLIST, server, PUUID);
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
        DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_MATCHLIST, matchList, server, PUUID);
        return matchList;
    }
    
    
    /**
     * Returns an iterator that transforms all {@code MatchReference} to {@code Match}
     *
     * @param server the platform the account is on
     * @param puuid  the account to check
     * @return {@code MatchIterator}
     */
    public MatchIterator getMatchIterator(ServicePlatform server, String puuid)
    {
        return new MatchIterator(getMatchList(server, puuid));
    }
    
    public TFTMatch getMatch(ServicePlatform platform, String gameId)
    {
        return getMatchRAW(platform, gameId).toTFTMatch();
    }
    
    public TFTMetadata getMetadata(ServicePlatform platform, String gameId)
    {
        return getMatchRAW(platform, gameId).toTFTMetadata();
    }
    
    public GAMHSMatch getMatchRAW(ServicePlatform platform, String gameId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                       .withEndpoint(URLEndpoint.V1_TFT_MATCH)
                                                       .withPlatform(platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_MATCH, platform, gameId);
        if (chl.isPresent())
        {
            return (GAMHSMatch) chl.get();
        }
        
        try
        {
            GAMHSMatch match = (GAMHSMatch) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_MATCH, match, platform, gameId);
            return match;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
