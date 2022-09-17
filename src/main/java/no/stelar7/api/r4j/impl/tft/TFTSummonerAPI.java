package no.stelar7.api.r4j.impl.tft;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Utils;
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
     * The response object contains the summoner objects mapped by their user id.
     *
     * @param server     the region to execute against
     * @param summonerId summonerId associated with summoners to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerById(final LeagueShard server, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_ID)
                .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("id", summonerId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_SUMMONER_BY_ID, data);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        try
        {
            Summoner summoner = (Summoner) builder.build();
            
            data.put("value", summoner);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_SUMMONER_BY_ID, data);
            
            return summoner;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server       the region to execute against
     * @param summonerName summoner name  associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerByName(final LeagueShard server, String summonerName)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                .withURLParameter(Constants.SUMMONER_NAME_PLACEHOLDER, Utils.normalizeString(summonerName))
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_NAME)
                .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("name", summonerName);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_SUMMONER_BY_NAME, data);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        try
        {
            Summoner summoner = (Summoner) builder.build();
            
            data.put("value", summoner);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_SUMMONER_BY_NAME, data);
            
            return summoner;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server    the region to execute against
     * @param accountId accountId associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerByAccount(final LeagueShard server, String accountId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                .withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, accountId)
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_ACCOUNT)
                .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("accountid", accountId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_SUMMONER_BY_ACCOUNT, data);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        try
        {
            Summoner summoner = (Summoner) builder.build();
            
            data.put("value", summoner);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_SUMMONER_BY_ACCOUNT, data);
            
            return summoner;
        } catch (ClassCastException e)
        {
            
            return null;
        }
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
