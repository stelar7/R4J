package no.stelar7.api.r4j.impl.val;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.basic.constants.types.val.ConsolePlatformType;
import no.stelar7.api.r4j.pojo.val.match.VALMatch;
import no.stelar7.api.r4j.pojo.val.matchlist.*;
import no.stelar7.api.r4j.pojo.val.ranked.Leaderboard;

import java.util.*;

/**
 * The console variants of the Valorant match and ranked apis (val-console-match-v1, val-console-ranked-v1)
 */
public final class VALConsoleAPI
{
    private static final VALConsoleAPI INSTANCE = new VALConsoleAPI(R4JContext.getDefault());

    public static VALConsoleAPI getInstance()
    {
        return VALConsoleAPI.INSTANCE;
    }

    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static VALConsoleAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(VALConsoleAPI.class, VALConsoleAPI::new);
    }

    private final R4JContext context;

    private VALConsoleAPI(R4JContext context)
    {
        this.context = context;
    }

    /**
     * @param platform the shard the match was played on
     * @param matchId  the id of the match
     * @return the match, or null if it does not exist
     */
    @Nullable
    public VALMatch getMatch(ValorantShard platform, String matchId)
    {
        DataCallBuilder builder = getMatchCall(platform, matchId);
        Map<String, Object> data = builder.getCacheKey();

        return builder.buildCached(VALMatch.class, data);
    }

    // the call getMatch and its twin make
    private DataCallBuilder getMatchCall(ValorantShard platform, String matchId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, matchId)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_CONSOLE_MATCH_BY_ID)
                                                       .withPlatform(platform);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("gameid", matchId);

        return builder.withCacheKey(data);
    }

    /**
     * @param platform     the shard the player plays on
     * @param puuid        the puuid of the player
     * @param platformType the console to get the matches for
     * @return the matches, or an empty list if there are none
     */
    public List<MatchReference> getMatchList(ValorantShard platform, String puuid, ConsolePlatformType platformType)
    {
        DataCallBuilder builder = getMatchListCall(platform, puuid, platformType);
        Map<String, Object> data = builder.getCacheKey();

        MatchList list = builder.buildCached(MatchList.class, data);
        return list != null ? list.getHistory() : Collections.emptyList();
    }

    // the call getMatchList and its twin make
    private DataCallBuilder getMatchListCall(ValorantShard platform, String puuid, ConsolePlatformType platformType)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withQueryParameter(Constants.PLATFORM_TYPE_PLACEHOLDER_DATA, platformType.getValue())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_CONSOLE_MATCHLIST_BY_PUUID)
                                                       .withPlatform(platform);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("puuid", puuid);
        data.put("platformType", platformType);

        return builder.withCacheKey(data);
    }

    /**
     * @param platform the shard to query
     * @param queue    the console queue (ie. console_unrated, console_competitive, console_swiftplay, console_deathmatch)
     * @return the ids of recently finished matches, or null if the queue does not exist
     */
    @Nullable
    public RecentMatchList getRecentMatches(ValorantShard platform, String queue)
    {
        DataCallBuilder builder = getRecentMatchesCall(platform, queue);
        Map<String, Object> data = builder.getCacheKey();

        return builder.buildCached(RecentMatchList.class, data);
    }

    // the call getRecentMatches and its twin make
    private DataCallBuilder getRecentMatchesCall(ValorantShard platform, String queue)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.QUEUE_PLACEHOLDER, queue)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_CONSOLE_RECENT_MATCH_BY_QUEUE)
                                                       .withPlatform(platform);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("queue", queue);

        return builder.withCacheKey(data);
    }

    /**
     * @param platform     the shard to query
     * @param actId        the act to get the leaderboard for
     * @param platformType the console to get the leaderboard for
     * @param startIndex   the index of the first player to return (defaults to 0)
     * @param size         the amount of players to return (defaults to 200, valid values are 1 to 200)
     * @return the leaderboard, or null if the act does not exist
     */
    @Nullable
    public Leaderboard getLeaderboard(ValorantShard platform, String actId, ConsolePlatformType platformType, int startIndex, int size)
    {
        DataCallBuilder builder = getLeaderboardCall(platform, actId, platformType, startIndex, size);
        Map<String, Object> data = builder.getCacheKey();

        return builder.buildCached(Leaderboard.class, data);
    }

    // the call getLeaderboard and its twin make
    private DataCallBuilder getLeaderboardCall(ValorantShard platform, String actId, ConsolePlatformType platformType, int startIndex, int size)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.ACT_ID_PLACEHOLDER, actId)
                                                       .withQueryParameter(Constants.PLATFORM_TYPE_PLACEHOLDER_DATA, platformType.getValue())
                                                       .withQueryParameter(Constants.STARTINDEX_PLACEHOLDER_DATA, String.valueOf(startIndex))
                                                       .withQueryParameter(Constants.SIZE_PLACEHOLDER_DATA, String.valueOf(size))
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_CONSOLE_LEADERBOARD_BY_ACT)
                                                       .withPlatform(platform);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("actId", actId);
        data.put("platformType", platformType);
        data.put("startIndex", startIndex);
        data.put("size", size);

        return builder.withCacheKey(data);
    }
    
    /**
     * Like {@link #getMatch}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<VALMatch> getMatchAsync(ValorantShard platform, String matchId)
    {
        try
        {
            DataCallBuilder builder = getMatchCall(platform, matchId);
            Map<String, Object> data = builder.getCacheKey();

            return builder.buildCachedAsync(VALMatch.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getMatchList}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<MatchReference>> getMatchListAsync(ValorantShard platform, String puuid, ConsolePlatformType platformType)
    {
        try
        {
            DataCallBuilder builder = getMatchListCall(platform, puuid, platformType);
            Map<String, Object> data = builder.getCacheKey();

            return builder.buildCachedAsync(MatchList.class, data).thenApply(list -> list != null ? list.getHistory() : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getRecentMatches}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RecentMatchList> getRecentMatchesAsync(ValorantShard platform, String queue)
    {
        try
        {
            DataCallBuilder builder = getRecentMatchesCall(platform, queue);
            Map<String, Object> data = builder.getCacheKey();

            return builder.buildCachedAsync(RecentMatchList.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getLeaderboard}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Leaderboard> getLeaderboardAsync(ValorantShard platform, String actId, ConsolePlatformType platformType, int startIndex, int size)
    {
        try
        {
            DataCallBuilder builder = getLeaderboardCall(platform, actId, platformType, startIndex, size);
            Map<String, Object> data = builder.getCacheKey();

            return builder.buildCachedAsync(Leaderboard.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
