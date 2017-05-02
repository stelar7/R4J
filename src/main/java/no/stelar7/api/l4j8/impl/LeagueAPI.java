package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.pojo.league.*;

import java.util.*;

public final class LeagueAPI
{
    private static final LeagueAPI INSTANCE = new LeagueAPI();
    
    public static LeagueAPI getInstance()
    {
        return LeagueAPI.INSTANCE;
    }
    
    private LeagueAPI()
    {
        // Hide public constructor
    }
    
    
    /**
     * Get master tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @param queue  queueType to get data for
     * @return LeagueList
     */
    public LeagueList getMasterLeague(Platform server, GameQueueType queue)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE_MASTER)
                                                       .withPlatform(server);
        
        
        return (LeagueList) builder.build();
    }
    
    
    /**
     * Get challenger tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @param queue  queueType to get data for
     * @return LeagueList
     */
    public LeagueList getChallengerLeague(Platform server, GameQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE_CHALLENGER)
                                                       .withPlatform(server);
        
        return (LeagueList) builder.build();
        
    }
    
    
    /**
     * Get league entries for a summonerId
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return LeagueList
     */
    public List<LeaguePosition> getLeaguePosition(Platform server, long summonerId)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE_ENTRY)
                                                       .withPlatform(server);
        
        
        return (List<LeaguePosition>) builder.build();
    }
    
    
    /**
     * Get leagues mapped by summoner ID for a given list of summoner IDs.
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return LeagueList
     */
    public List<LeagueList> getLeague(Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE)
                                                       .withPlatform(server);
        
        return (List<LeagueList>) builder.build();
    }
}
