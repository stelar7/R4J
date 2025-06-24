package no.stelar7.api.r4j.impl.tft;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.*;

public class TFTSummonerAPI
{
    
    
    private static final TFTSummonerAPI INSTANCE = new TFTSummonerAPI();
    
    public static TFTSummonerAPI getInstance()
    {
        return TFTSummonerAPI.INSTANCE;
    }
    
    private TFTSummonerAPI()
    {
        // Hide public constructor
    }
    
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server the region to execute against
     * @param PUUID  puuid associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerByPUUID(final LeagueShard server, String PUUID)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_PUUID)
                .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", PUUID);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_SUMMONER_BY_PUUID, data);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        try
        {
            Summoner summoner = (Summoner) builder.build();
            
            data.put("value", summoner);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_SUMMONER_BY_PUUID, data);
            
            return summoner;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
}
