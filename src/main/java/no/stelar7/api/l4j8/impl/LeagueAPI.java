package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.GameQueueType;
import no.stelar7.api.l4j8.pojo.league.*;

import java.util.*;

@SuppressWarnings("unchecked")
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
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_LEAGUE_MASTER, server, queue);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        LeagueList list = (LeagueList) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_LEAGUE_MASTER, list, server, queue);
        return list;
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
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_LEAGUE_CHALLENGER, server, queue);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        LeagueList list = (LeagueList) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_LEAGUE_CHALLENGER, list, server, queue);
        return list;
        
    }
    
    
    /**
     * Get league entries for a summonerId
     * Empty if unranked
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
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_LEAGUE_ENTRY, summonerId, server);
        if (chl.isPresent())
        {
            return (List<LeaguePosition>) chl.get();
        }
        
        List<LeaguePosition> list = (List<LeaguePosition>) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_LEAGUE_ENTRY, list, summonerId, server);
        return list;
    }
    
    
    /**
     * Get league from its ID.
     *
     * @param server   region to get data from
     * @param leagueId league to get data for
     * @return LeagueList
     */
    public LeagueList getLeague(Platform server, String leagueId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.LEAGUE_ID_PLACEHOLDER, String.valueOf(leagueId))
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_LEAGUE, leagueId, server);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        LeagueList list = (LeagueList) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_LEAGUE, list, leagueId, server);
        return list;
    }
}
