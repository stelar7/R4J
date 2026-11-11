package no.stelar7.api.r4j.impl.tft;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.tft.league.*;

import java.util.*;

public class TFTLeagueAPI
{
    private static final TFTLeagueAPI INSTANCE = new TFTLeagueAPI(R4JContext.getDefault());

    public static TFTLeagueAPI getInstance()
    {
        return TFTLeagueAPI.INSTANCE;
    }

    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static TFTLeagueAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(TFTLeagueAPI.class, TFTLeagueAPI::new);
    }

    private final R4JContext context;

    private TFTLeagueAPI(R4JContext context)
    {
        this.context = context;
    }

    /**
     * Get master tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @return LeagueList
     */
    private TFTLeagueList getMasterLeague(LeagueShard server)
    {

        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_MASTER)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);

        return builder.buildCached(TFTLeagueList.class, data);
    }

    /**
     * Get grandmaster tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @return LeagueList
     */
    private TFTLeagueList getGrandmasterLeague(LeagueShard server)
    {

        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER)
                                                       .withPlatform(server);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);

        return builder.buildCached(TFTLeagueList.class, data);
    }


    /**
     * Get challenger tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @return LeagueList
     */
    private TFTLeagueList getChallengerLeague(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);

        return builder.buildCached(TFTLeagueList.class, data);
    }


    /**
     * Get league entries for a summonerId
     * Empty if unranked
     *
     * @param server region to get data from
     * @param puuid  summoner to get data for
     * @return List of LeagueEntry
     */
    public List<TFTLeagueEntry> getLeagueEntriesByPUUID(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = getLeagueEntriesByPUUIDCall(server, puuid);
        Map<String, Object> data = builder.getCacheKey();

        List<TFTLeagueEntry> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }

    // the call getLeagueEntriesByPUUID and its twin make
    private DataCallBuilder getLeagueEntriesByPUUIDCall(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_BY_PUUID)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);

        return builder.withCacheKey(data);
    }

    /**
     * @param server  the region to query
     * @param tierdiv the tier and division to query
     * @param page    the page to query (first page is 1)
     * @return LeagueList
     */
    public List<TFTLeagueEntry> getLeagueByTierDivision(LeagueShard server, TierDivisionType tierdiv, int page)
    {
        if (tierdiv == TierDivisionType.UNRANKED)
        {
            // there is no league of unranked players
            return Collections.emptyList();
        }

        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.TIER_PLACEHOLDER, tierdiv.getTier())
                                                       .withURLParameter(Constants.DIVISION_PLACEHOLDER, tierdiv.getDivision())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withQueryParameter(Constants.PAGE_PLACEHOLDER_DATA, String.valueOf(page))
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_RANK)
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
                    return entriesOf(getMasterLeague(server));
                case GRANDMASTER_I:
                    return entriesOf(getGrandmasterLeague(server));
                case CHALLENGER_I:
                    return entriesOf(getChallengerLeague(server));
            }
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("tierdiv", tierdiv);
        data.put("page", page);

        List<TFTLeagueEntry> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }

    /**
     * @param server  the region to query
     * @param tierdiv the tier and division to query
     * @return LeagueList
     */
    public LazyList<TFTLeagueEntry> getLeagueByTierDivisionLazy(LeagueShard server, TierDivisionType tierdiv)
    {
        return new LazyList<>(1, prevValue -> getLeagueByTierDivision(server, tierdiv, prevValue + 1));
    }
    
    /**
     * The master league of a specific queue
     *
     * @param server the region to query
     * @param queue  the queue to query (RANKED_TFT or RANKED_TFT_DOUBLE_UP)
     * @return LeagueList
     */
    public TFTLeagueList getMasterLeague(LeagueShard server, GameQueueType queue)
    {
        return getApexLeague(URLEndpoint.V1_TFT_LEAGUE_MASTER, server, queue);
    }

    /**
     * The grandmaster league of a specific queue
     *
     * @param server the region to query
     * @param queue  the queue to query (RANKED_TFT or RANKED_TFT_DOUBLE_UP)
     * @return LeagueList
     */
    public TFTLeagueList getGrandmasterLeague(LeagueShard server, GameQueueType queue)
    {
        return getApexLeague(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER, server, queue);
    }

    /**
     * The challenger league of a specific queue
     *
     * @param server the region to query
     * @param queue  the queue to query (RANKED_TFT or RANKED_TFT_DOUBLE_UP)
     * @return LeagueList
     */
    public TFTLeagueList getChallengerLeague(LeagueShard server, GameQueueType queue)
    {
        return getApexLeague(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER, server, queue);
    }

    private TFTLeagueList getApexLeague(URLEndpoint endpoint, LeagueShard server, GameQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withQueryParameter(Constants.QUEUE_PLACEHOLDER_DATA, queue.getApiName())
                                                       .withEndpoint(endpoint)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);

        return builder.buildCached(TFTLeagueList.class, data);
    }

    /**
     * @param server  the region to query
     * @param queue   the queue to query (RANKED_TFT or RANKED_TFT_DOUBLE_UP)
     * @param tierdiv the tier and division to query
     * @param page    the page to query (first page is 1)
     * @return the entries, or an empty list if there are none
     */
    public List<TFTLeagueEntry> getLeagueByTierDivision(LeagueShard server, GameQueueType queue, TierDivisionType tierdiv, int page)
    {
        if (tierdiv == TierDivisionType.UNRANKED)
        {
            // there is no league of unranked players
            return Collections.emptyList();
        }

        if (Arrays.asList(TierDivisionType.MASTER_I, TierDivisionType.GRANDMASTER_I, TierDivisionType.CHALLENGER_I).contains(tierdiv))
        {
            if (page > 1)
            {
                return Collections.emptyList();
            }

            TFTLeagueList apex;
            if (tierdiv == TierDivisionType.MASTER_I)
            {
                apex = getMasterLeague(server, queue);
            } else if (tierdiv == TierDivisionType.GRANDMASTER_I)
            {
                apex = getGrandmasterLeague(server, queue);
            } else
            {
                apex = getChallengerLeague(server, queue);
            }

            return apex != null ? apex.getEntries() : Collections.emptyList();
        }

        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.TIER_PLACEHOLDER, tierdiv.getTier())
                                                       .withURLParameter(Constants.DIVISION_PLACEHOLDER, tierdiv.getDivision())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withQueryParameter(Constants.QUEUE_PLACEHOLDER_DATA, queue.getApiName())
                                                       .withQueryParameter(Constants.PAGE_PLACEHOLDER_DATA, String.valueOf(page))
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_RANK)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("tierdiv", tierdiv);
        data.put("page", page);
        data.put("queue", queue);

        List<TFTLeagueEntry> entries = builder.buildCached(TFTLeagueEntryList.class, data);
        return entries != null ? entries : Collections.emptyList();
    }

    /**
     * The top of the rated ladder for a queue (Hyper Roll)
     *
     * @param server the region to query
     * @param queue  the queue to query (RANKED_TFT_TURBO)
     * @return the entries, or an empty list if there are none
     */
    public List<TFTTopRatedLadderEntry> getTopRatedLadder(LeagueShard server, GameQueueType queue)
    {
        DataCallBuilder builder = getTopRatedLadderCall(server, queue);
        Map<String, Object> data = builder.getCacheKey();

        List<TFTTopRatedLadderEntry> entries = builder.buildCached(TFTTopRatedLadderEntryList.class, data);
        return entries != null ? entries : Collections.emptyList();
    }

    // the call getTopRatedLadder and its twin make
    private DataCallBuilder getTopRatedLadderCall(LeagueShard server, GameQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.QUEUE_PLACEHOLDER, queue.getApiName())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_RATED_LADDER_TOP)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("queue", queue);

        return builder.withCacheKey(data);
    }
    
    private static List<TFTLeagueEntry> entriesOf(TFTLeagueList league)
    {
        return league != null ? league.getEntries() : Collections.emptyList();
    }
    
    /**
     * Like {@link #getLeagueEntriesByPUUID}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<TFTLeagueEntry>> getLeagueEntriesByPUUIDAsync(LeagueShard server, String puuid)
    {
        try
        {
            DataCallBuilder builder = getLeagueEntriesByPUUIDCall(server, puuid);
            Map<String, Object> data = builder.getCacheKey();

            return builder.<List<TFTLeagueEntry>>buildCachedAsync(data).thenApply(list -> list != null ? list : Collections.emptyList());
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
    public CompletableFuture<List<TFTLeagueEntry>> getLeagueByTierDivisionAsync(LeagueShard server, TierDivisionType tierdiv, int page)
    {
        try
        {
            if (tierdiv == TierDivisionType.UNRANKED)
            {
                // there is no league of unranked players
                return CompletableFuture.completedFuture(Collections.emptyList());
            }

            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                           .withURLParameter(Constants.TIER_PLACEHOLDER, tierdiv.getTier())
                                                           .withURLParameter(Constants.DIVISION_PLACEHOLDER, tierdiv.getDivision())
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                           .withQueryParameter(Constants.PAGE_PLACEHOLDER_DATA, String.valueOf(page))
                                                           .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_RANK)
                                                           .withPlatform(server);

            if (Arrays.asList(TierDivisionType.MASTER_I, TierDivisionType.GRANDMASTER_I, TierDivisionType.CHALLENGER_I).contains(tierdiv))
            {
                if (page > 1)
                {
                    return CompletableFuture.completedFuture(Collections.emptyList());
                }

                URLEndpoint apex = tierdiv == TierDivisionType.MASTER_I ? URLEndpoint.V1_TFT_LEAGUE_MASTER
                                                                        : tierdiv == TierDivisionType.GRANDMASTER_I ? URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER : URLEndpoint.V1_TFT_LEAGUE_CHALLENGER;
                return apexLeagueAsync(apex, server).thenApply(TFTLeagueAPI::entriesOf);
            }

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("tierdiv", tierdiv);
            data.put("page", page);

            return builder.<List<TFTLeagueEntry>>buildCachedAsync(data).thenApply(list -> list != null ? list : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getMasterLeague}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<TFTLeagueList> getMasterLeagueAsync(LeagueShard server, GameQueueType queue)
    {
        return getApexLeagueAsync(URLEndpoint.V1_TFT_LEAGUE_MASTER, server, queue);
    }
    
    /**
     * Like {@link #getGrandmasterLeague}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<TFTLeagueList> getGrandmasterLeagueAsync(LeagueShard server, GameQueueType queue)
    {
        return getApexLeagueAsync(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER, server, queue);
    }
    
    /**
     * Like {@link #getChallengerLeague}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<TFTLeagueList> getChallengerLeagueAsync(LeagueShard server, GameQueueType queue)
    {
        return getApexLeagueAsync(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER, server, queue);
    }
    
    /**
     * Like {@link #getLeagueByTierDivision}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<TFTLeagueEntry>> getLeagueByTierDivisionAsync(LeagueShard server, GameQueueType queue, TierDivisionType tierdiv, int page)
    {
        try
        {
            if (tierdiv == TierDivisionType.UNRANKED)
            {
                // there is no league of unranked players
                return CompletableFuture.completedFuture(Collections.emptyList());
            }

            if (Arrays.asList(TierDivisionType.MASTER_I, TierDivisionType.GRANDMASTER_I, TierDivisionType.CHALLENGER_I).contains(tierdiv))
            {
                if (page > 1)
                {
                    return CompletableFuture.completedFuture(Collections.emptyList());
                }

                URLEndpoint apex = tierdiv == TierDivisionType.MASTER_I ? URLEndpoint.V1_TFT_LEAGUE_MASTER
                                                                        : tierdiv == TierDivisionType.GRANDMASTER_I ? URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER : URLEndpoint.V1_TFT_LEAGUE_CHALLENGER;
                return getApexLeagueAsync(apex, server, queue).thenApply(TFTLeagueAPI::entriesOf);
            }

            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.TIER_PLACEHOLDER, tierdiv.getTier())
                                                           .withURLParameter(Constants.DIVISION_PLACEHOLDER, tierdiv.getDivision())
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                           .withQueryParameter(Constants.QUEUE_PLACEHOLDER_DATA, queue.getApiName())
                                                           .withQueryParameter(Constants.PAGE_PLACEHOLDER_DATA, String.valueOf(page))
                                                           .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_RANK)
                                                           .withPlatform(server);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("tierdiv", tierdiv);
            data.put("page", page);
            data.put("queue", queue);

            return builder.buildCachedAsync(TFTLeagueEntryList.class, data).<List<TFTLeagueEntry>>thenApply(entries -> entries != null ? entries : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getTopRatedLadder}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<TFTTopRatedLadderEntry>> getTopRatedLadderAsync(LeagueShard server, GameQueueType queue)
    {
        try
        {
            DataCallBuilder builder = getTopRatedLadderCall(server, queue);
            Map<String, Object> data = builder.getCacheKey();

            return builder.buildCachedAsync(TFTTopRatedLadderEntryList.class, data).<List<TFTTopRatedLadderEntry>>thenApply(entries -> entries != null ? entries : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * The master, grandmaster or challenger league without a queue, like the blocking methods for them fetch it
     */
    private CompletableFuture<TFTLeagueList> apexLeagueAsync(URLEndpoint endpoint, LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(endpoint)
                                                       .withPlatform(server);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);

        return builder.buildCachedAsync(TFTLeagueList.class, data);
    }

    /**
     * Like getApexLeague, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     */
    private CompletableFuture<TFTLeagueList> getApexLeagueAsync(URLEndpoint endpoint, LeagueShard server, GameQueueType queue)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                           .withQueryParameter(Constants.QUEUE_PLACEHOLDER_DATA, queue.getApiName())
                                                           .withEndpoint(endpoint)
                                                           .withPlatform(server);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("queue", queue);

            return builder.buildCachedAsync(TFTLeagueList.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
