package no.stelar7.api.r4j.impl.tft;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.Optional;

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
    public Summoner getSummonerById(final Platform server, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_ID)
                .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_SUMMONER_BY_ID, server, summonerId);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        try
        {
            Summoner summoner = (Summoner) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_SUMMONER_BY_ID, summoner, server, summonerId);
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
    public Summoner getSummonerByName(final Platform server, String summonerName)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                .withURLParameter(Constants.SUMMONER_NAME_PLACEHOLDER, Utils.normalizeString(summonerName))
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_NAME)
                .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_SUMMONER_BY_NAME, server, summonerName);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        try
        {
            Summoner summoner = (Summoner) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_SUMMONER_BY_NAME, summoner, server, summonerName);
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
    public Summoner getSummonerByAccount(final Platform server, String accountId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                .withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, accountId)
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_ACCOUNT)
                .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_SUMMONER_BY_ACCOUNT, server, accountId);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        try
        {
            Summoner summoner = (Summoner) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_SUMMONER_BY_ACCOUNT, summoner, server, accountId);
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
    public Summoner getSummonerByPUUID(final Platform server, String PUUID)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_PUUID)
                .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_SUMMONER_BY_PUUID, server, PUUID);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        try
        {
            Summoner summoner = (Summoner) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_SUMMONER_BY_PUUID, summoner, server, PUUID);
            return summoner;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
}
