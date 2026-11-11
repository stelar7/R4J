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
    private static final LeagueAPI INSTANCE = new LeagueAPI(R4JContext.getDefault());
    
    public static LeagueAPI getInstance()
    {
        return LeagueAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static LeagueAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(LeagueAPI.class, LeagueAPI::new);
    }

    private final R4JContext context;

    private LeagueAPI(R4JContext context)
    {
        this.context = context;
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
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_MASTER)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);
        
        return builder.buildCached(LeagueList.class, data);
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
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_GRANDMASTER)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);
        
        
        return builder.buildCached(LeagueList.class, data);
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
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_CHALLENGER)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);
        
        return builder.buildCached(LeagueList.class, data);
    }
    
    
    /**
     * Get league from its ID.
     *
     * @param server   region to get data from
     * @param leagueId league to get data for
     * @return LeagueList
     * @deprecated Riot announced (2025-05-29) that leagueId is going away and that this endpoint is retired; it is no longer in the league-v4 documentation
     */
    @Deprecated
    public LeagueList getLeague(LeagueShard server, String leagueId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.LEAGUE_ID_PLACEHOLDER, String.valueOf(leagueId))
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("leagueid", leagueId);
        
        return builder.buildCached(LeagueList.class, data);
    }
    
    /**
     * Get league entries for a puuid
     * Empty if unranked
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return List of LeagueEntry
     */
    public List<LeagueEntry> getLeagueEntriesByPUUID(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_ENTRY_BY_PUUID)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("id", puuid);
        
        List<LeagueEntry> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
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
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
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
        
        List<LeagueEntry> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
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
    
    /**
     * The experimental league endpoint (league-exp-v4).
     * Unlike {@link #getLeagueByTierDivision(LeagueShard, GameQueueType, TierDivisionType, int)}, this endpoint also serves the apex tiers (master, grandmaster, challenger) in pages.
     *
     * @param server  the region to query
     * @param queue   the queue to fetch data from (RANKED_SOLO_5x5, RANKED_TFT, RANKED_FLEX_SR or RANKED_FLEX_TT)
     * @param tierdiv the tier and division to query
     * @param page    the page to query (first page is 1)
     * @return the entries, or an empty list if there are none
     */
    public List<LeagueEntry> getLeagueExpByTierDivision(LeagueShard server, GameQueueType queue, TierDivisionType tierdiv, int page)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.POSITIONAL_QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withURLParameter(Constants.TIER_PLACEHOLDER, tierdiv.getTier())
                                                       .withURLParameter(Constants.DIVISION_PLACEHOLDER, tierdiv.getDivision())
                                                       .withQueryParameter(Constants.PAGE_PLACEHOLDER_DATA, String.valueOf(page))
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE_EXP_RANK)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);
        data.put("tierdiv", tierdiv);
        data.put("page", page);

        List<LeagueEntry> entries = builder.buildCached(LeagueEntryList.class, data);
        return entries != null ? entries : Collections.emptyList();
    }

    /**
     * @param server  the region to query
     * @param queue   the queue to fetch data from (RANKED_SOLO_5x5, RANKED_TFT, RANKED_FLEX_SR or RANKED_FLEX_TT)
     * @param tierdiv the tier and division to query
     * @return a list that loads the next page when it is needed
     */
    public LazyList<LeagueEntry> getLeagueExpByTierDivisionLazy(LeagueShard server, GameQueueType queue, TierDivisionType tierdiv)
    {
        return new LazyList<>(1, prevValue -> getLeagueExpByTierDivision(server, queue, tierdiv, prevValue + 1));
    }
}
