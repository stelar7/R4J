package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.pojo.lol.league.*;

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
    private LeagueList getMasterLeague(LeagueShard server, GameQueueType queue)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_MASTER)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_MASTER, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_MASTER, data);
            
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
    private LeagueList getGrandmasterLeague(LeagueShard server, GameQueueType queue)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_GRANDMASTER)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);
        
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_GRANDMASTER, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_GRANDMASTER, data);
            
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
    private LeagueList getChallengerLeague(LeagueShard server, GameQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_CHALLENGER)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_CHALLENGER, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_CHALLENGER, data);
            
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
    public LeagueList getLeague(LeagueShard server, String leagueId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.LEAGUE_ID_PLACEHOLDER, String.valueOf(leagueId))
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("leagueid", leagueId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE, data);
            
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
    public List<LeagueEntry> getLeagueEntries(LeagueShard server, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_ENTRY)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("id", summonerId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_ENTRY, data);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_ENTRY, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
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
    public List<LeagueEntry> getLeagueEntriesByPUUID(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_ENTRY_BY_PUUID)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("id", puuid);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_ENTRY_BY_PUUID, data);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_ENTRY_BY_PUUID, data);
            
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
    public List<LeagueEntry> getLeagueByTierDivision(LeagueShard server, GameQueueType queue, TierDivisionType tierdiv, int page)
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
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);
        data.put("tierdiv", tierdiv);
        data.put("page", page);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE_RANK, data);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE_RANK, data);
            
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
    public LazyList<LeagueEntry> getLeagueByTierDivisionLazy(LeagueShard server, GameQueueType queue, TierDivisionType tierdiv)
    {
        return new LazyList<>(1, prevValue -> getLeagueByTierDivision(server, queue, tierdiv, prevValue + 1));
    }
}
