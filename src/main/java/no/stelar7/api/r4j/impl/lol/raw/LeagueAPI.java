package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
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
    @Deprecated(since = "3.0.0", forRemoval = true)
    @Nullable
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
     * @param puuid      the player to get data for
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
        if (tierdiv == TierDivisionType.UNRANKED)
        {
            // there is no league of unranked players
            return Collections.emptyList();
        }

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
                    return entriesOf(getMasterLeague(server, queue));
                case GRANDMASTER_I:
                    return entriesOf(getGrandmasterLeague(server, queue));
                case CHALLENGER_I:
                    return entriesOf(getChallengerLeague(server, queue));
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
        if (tierdiv == TierDivisionType.UNRANKED)
        {
            // there is no league of unranked players
            return Collections.emptyList();
        }

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
    
    private static List<LeagueEntry> entriesOf(LeagueList league)
    {
        return league != null ? league.getEntries() : Collections.emptyList();
    }
    
    /**
     * Like {@link #getLeagueEntriesByPUUID}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<LeagueEntry>> getLeagueEntriesByPUUIDAsync(LeagueShard server, String puuid)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                           .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                           .withEndpoint(URLEndpoint.V4_LEAGUE_ENTRY_BY_PUUID)
                                                           .withPlatform(server);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("id", puuid);
        
            return builder.<List<LeagueEntry>>buildCachedAsync(data).thenApply(list -> list != null ? list : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getLeagueByTierDivision}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<LeagueEntry>> getLeagueByTierDivisionAsync(LeagueShard server, GameQueueType queue, TierDivisionType tierdiv, int page)
    {
        try
        {
            if (tierdiv == TierDivisionType.UNRANKED)
            {
                // there is no league of unranked players
                return CompletableFuture.completedFuture(Collections.emptyList());
            }

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
                    return CompletableFuture.completedFuture(Collections.emptyList());
                }

                URLEndpoint apex = tierdiv == TierDivisionType.MASTER_I ? URLEndpoint.V4_LEAGUE_MASTER
                                                                        : tierdiv == TierDivisionType.GRANDMASTER_I ? URLEndpoint.V4_LEAGUE_GRANDMASTER : URLEndpoint.V4_LEAGUE_CHALLENGER;
                return apexLeagueAsync(apex, server, queue).thenApply(LeagueAPI::entriesOf);
            }

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("queue", queue);
            data.put("tierdiv", tierdiv);
            data.put("page", page);

            return builder.<List<LeagueEntry>>buildCachedAsync(data).thenApply(list -> list != null ? list : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getLeagueExpByTierDivision}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<LeagueEntry>> getLeagueExpByTierDivisionAsync(LeagueShard server, GameQueueType queue, TierDivisionType tierdiv, int page)
    {
        try
        {
            if (tierdiv == TierDivisionType.UNRANKED)
            {
                // there is no league of unranked players
                return CompletableFuture.completedFuture(Collections.emptyList());
            }

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

            return builder.buildCachedAsync(LeagueEntryList.class, data).<List<LeagueEntry>>thenApply(entries -> entries != null ? entries : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * The master, grandmaster or challenger league of a queue, like the blocking methods for them fetch it
     */
    private CompletableFuture<LeagueList> apexLeagueAsync(URLEndpoint endpoint, LeagueShard server, GameQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withEndpoint(endpoint)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);

        return builder.buildCachedAsync(LeagueList.class, data);
    }
}
