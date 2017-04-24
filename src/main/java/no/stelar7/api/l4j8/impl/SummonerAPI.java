package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.Utils;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPages;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePages;

import java.util.*;

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
    public MasteryPages getMasteries(final Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_MASTERIES_BY_ID)
                                                       .withPlatform(server);
        
        Map<Long, MasteryPages> data = (Map) builder.build();
        return data.get(summonerId);
    }
    
    /**
     * The response object contains the RunePages objects mapped by their userId.
     *
     * @param server     the region to execute against
     * @param summonerId summonerId associated with summoners to retrieve.
     * @return Optional RunePages
     */
    public RunePages getRunes(final Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                .withEndpoint(URLEndpoint.V3_RUNES_BY_ID)
                .withPlatform(server);
        
        Map<Long, RunePages> data = (Map) builder.build();
        return data.get(summonerId);
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
        
        return (Summoner) builder.build();
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
        
        return (Summoner) builder.build();
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
        
        return (Summoner) builder.build();
    }
}
