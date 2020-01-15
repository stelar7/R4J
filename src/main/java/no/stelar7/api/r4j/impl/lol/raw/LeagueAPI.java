package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.types.*;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.pojo.lol.league.*;

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
    private LeagueList getMasterLeague(Platform server, GameQueueType queue)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_MASTER)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_MASTER, server, queue);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_MASTER, list, server, queue);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * Get grandmaster tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @param queue  queueType to get data for
     * @return LeagueList
     */
    private LeagueList getGrandmasterLeague(Platform server, GameQueueType queue)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_GRANDMASTER)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_GRANDMASTER, server, queue);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_GRANDMASTER, list, server, queue);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    
    /**
     * Get challenger tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @param queue  queueType to get data for
     * @return LeagueList
     */
    private LeagueList getChallengerLeague(Platform server, GameQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_CHALLENGER)
                                                       .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_CHALLENGER, server, queue);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_CHALLENGER, list, server, queue);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
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
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE, server, leagueId);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE, list, server, leagueId);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * Get league entries for a summonerId
     * Empty if unranked
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return List of LeagueEntry
     */
    public List<LeagueEntry> getLeagueEntries(Platform server, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_ENTRY)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_ENTRY, server, summonerId);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object data = builder.build();
            if (data instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) data;
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_ENTRY, list, server, summonerId);
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    /**
     * @param server  the region to query
     * @param queue   the queue to fetch data from (only RANKED_SOLO_5x5, RANKED_FLEX_SR and RANKED_FLEX_TT is allowed)
     * @param tierdiv the tier and division to query
     * @param page    the page to query (first page is 1)
     * @return LeagueList
     */
    public List<LeagueEntry> getLeagueByTierDivision(Platform server, GameQueueType queue, TierDivisionType tierdiv, int page)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.POSITIONAL_QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withURLParameter(Constants.TIER_PLACEHOLDER, tierdiv.getTier())
                                                       .withURLParameter(Constants.DIVISION_PLACEHOLDER, tierdiv.getDivision())
                                                       .withQueryParameter(Constants.PAGE_PLACEHOLDER_DATA, String.valueOf(page))
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_RANK)
                                                       .withPlatform(server);
        
        if (Arrays.asList(TierDivisionType.MASTER_I, TierDivisionType.GRANDMASTER_I, TierDivisionType.CHALLENGER_I).contains(tierdiv))
        {
            
            if (page > 1)
            {
                return Collections.emptyList();
            }
            
            switch (tierdiv)
            {
                case MASTER_I:
                    return getMasterLeague(server, queue).getEntries();
                case GRANDMASTER_I:
                    return getGrandmasterLeague(server, queue).getEntries();
                case CHALLENGER_I:
                    return getChallengerLeague(server, queue).getEntries();
            }
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_RANK, server, queue, tierdiv, page);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object data = builder.build();
            if (data instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) data;
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_RANK, list, server, queue, tierdiv, page);
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    /**
     * @param server  the region to query
     * @param queue   the queue to fetch data from (only RANKED_SOLO_5x5, RANKED_FLEX_SR and RANKED_FLEX_TT is allowed)
     * @param tierdiv the tier and division to query
     * @return LeagueList
     */
    public LazyList<LeagueEntry> getLeagueByTierDivisionLazy(Platform server, GameQueueType queue, TierDivisionType tierdiv)
    {
        return new LazyList<>(1, prevValue -> getLeagueByTierDivision(server, queue, tierdiv, prevValue + 1));
    }
}
