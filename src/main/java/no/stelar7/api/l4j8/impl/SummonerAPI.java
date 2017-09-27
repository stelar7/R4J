package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.utils.Utils;
import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.pojo.summoner.masteries.*;
import no.stelar7.api.l4j8.pojo.summoner.runes.*;

import java.util.*;

@SuppressWarnings("unchecked")
public final class SummonerAPI
{
    
    
    private static final SummonerAPI INSTANCE = new SummonerAPI();
    
    public static SummonerAPI getInstance()
    {
        return SummonerAPI.INSTANCE;
    }
    
    private SummonerAPI()
    {
        // Hide public constructor
    }
    
    /**
     * The response object contains the MasteryPages objects mapped by their userId.
     *
     * @param server     the region to execute against
     * @param summonerId summonerId associated with summoners to retrieve.
     * @return Optional MasteryPages
     */
    public List<MasteryPage> getMasteries(final Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_MASTERIES_BY_ID)
                                                       .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_MASTERIES_BY_ID, server, summonerId);
        if (chl.isPresent())
        {
            return (List<MasteryPage>) chl.get();
        }
        
        MasteryPages list = (MasteryPages) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_MASTERIES_BY_ID, list.getPages(), server, summonerId);
        return list.getPages();
    }
    
    /**
     * The response object contains the RunePages objects mapped by their userId.
     *
     * @param server     the region to execute against
     * @param summonerId summonerId associated with summoners to retrieve.
     * @return Optional RunePages
     */
    public List<RunePage> getRunes(final Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                .withEndpoint(URLEndpoint.V3_RUNES_BY_ID)
                .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_RUNES_BY_ID, server, summonerId);
        if (chl.isPresent())
        {
            return (List<RunePage>) chl.get();
        }
        
        RunePages list = (RunePages) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_RUNES_BY_ID, list.getPages(), server, summonerId);
        return list.getPages();
    }
    
    /**
     * The response object contains the summoner objects mapped by their user id.
     *
     * @param server     the region to execute against
     * @param summonerId summonerId associated with summoners to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerById(final Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                .withEndpoint(URLEndpoint.V3_SUMMONER_BY_ID)
                .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_SUMMONER_BY_ID, summonerId, server);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        Summoner summoner = (Summoner) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_SUMMONER_BY_ID, summoner, summonerId, server);
        return summoner;
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
                .withURLParameter(Constants.SUMMONER_NAME_PLACEHOLDER, Utils.normalizeSummonerName(summonerName))
                .withEndpoint(URLEndpoint.V3_SUMMONER_BY_NAME)
                .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_SUMMONER_BY_NAME, summonerName, server);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        Summoner summoner = (Summoner) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_SUMMONER_BY_NAME, summoner, summonerName, server);
        return summoner;
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server    the region to execute against
     * @param accountId accountId associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerByAccount(final Platform server, long accountId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, String.valueOf(accountId))
                .withEndpoint(URLEndpoint.V3_SUMMONER_BY_ACCOUNT)
                .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_SUMMONER_BY_ACCOUNT, accountId, server);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        Summoner summoner = (Summoner) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_SUMMONER_BY_ACCOUNT, summoner, accountId, server);
        return summoner;
    }
}
