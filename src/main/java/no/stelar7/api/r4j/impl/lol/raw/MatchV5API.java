package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.calling.DataCallBuilder;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.pojo.shared.*;
import no.stelar7.api.r4j.basic.utils.StringList;
import java.util.*;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;

import java.util.List;

public final class MatchV5API
{
    private static final MatchV5API INSTANCE = new MatchV5API(R4JContext.getDefault());
    
    public static MatchV5API getInstance()
    {
        return MatchV5API.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static MatchV5API getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(MatchV5API.class, MatchV5API::new);
    }

    private final R4JContext context;

    private MatchV5API(R4JContext context)
    {
        this.context = context;
    }
    
    public List<String> getMatchList(RegionShard server, String puuid, GameQueueType queue, MatchlistMatchType type, Integer beginIndex, Integer count, Long startTime, Long endTime)
    {
        return new MatchListBuilder(server, puuid, queue, type, beginIndex, count, startTime, endTime).withContext(context).get();
    }
    
    /**
     * Returns a list of all games avaliable in the api
     * This list is updated lazily!
     * To get all the items, either iterate the list once, or get(Integer.MAX_VALUE)
     *
     * @param server the platform the account is on
     * @param puuid  the account to check
     * @return {@code List<MatchReference>}
     */
    public LazyList<String> getMatchList(RegionShard server, String puuid)
    {
        int increment = 100;
        return new LazyList<>(increment, prevValue -> getMatchList(server, puuid, null, null, prevValue + increment, increment, null, null));
    }
    
    /**
     * Returns a list of all replays avaliable in the api
     *
     * @param server the platform the account is on
     * @param puuid  the account to check
     * @deprecated the Riot api documentation only lists /matches/replays, which needs the access token of the player; use {@link #getMatchReplaysByAccessToken(RegionShard, String)}
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    @SuppressWarnings("removal")
    @Nullable
    public LOLMatchReplayList getMatchListReplays(RegionShard server, String puuid)
    {
        return new ReplayBuilder(server, puuid).withContext(context).get();
    }
    
    /**
     * Returns an iterator that transforms all {@code MatchReference} to {@code Match}
     *
     * @param server the platform the account is on
     * @param puuid  the account to check
     * @return {@code MatchIterator}
     */
    public MatchIterator getMatchIterator(RegionShard server, String puuid)
    {
        return new MatchIterator(getMatchList(server, puuid), context);
    }
    
    /**
     * Returns the match data from a match id
     *
     * @param server  the platform the match was played on
     * @param matchId the id to check
     * @return Match
     */
    @Nullable
    public LOLMatch getMatch(RegionShard server, String matchId)
    {
        return new MatchBuilder(server, matchId).withContext(context).getMatch();
    }
    
    /**
     * Returns the timeline relating to a match.
     * Not avaliable for matches older than a year
     *
     * @param server  the platform to find the match on
     * @param matchId the matchId to find timeline for
     * @return MatchTimeline if avaliable
     */
    @Nullable
    public LOLTimeline getTimeline(RegionShard server, String matchId)
    {
        return new TimelineBuilder(server, matchId).withContext(context).getTimeline();
    }
    
    /**
     * The replays of the recent matches of a player that signed in with RSO (GET /lol/match/v5/matches/replays)
     *
     * @param server      the region the account is on
     * @param accessToken the access token of the player
     * @return the replay urls, or null if nothing was found
     */
    @Nullable
    public LOLMatchReplayList getMatchReplaysByAccessToken(RegionShard server, String accessToken)
    {
        // the calls using an access token are not cached, as the result depends on who the token belongs to
        return new DataCallBuilder(context).withAccessToken(accessToken)
                                    .withEndpoint(URLEndpoint.V5_MATCH_REPLAYS)
                                    .withPlatform(server)
                                    .buildAs(LOLMatchReplayList.class);
    }

    /**
     * The match ids of a player that signed in with RSO (lol-rso-match-v1).
     * All the filters are optional, pass null to leave one out.
     *
     * @param server      the region the account is on
     * @param accessToken the access token of the player
     * @param queue       only return matches from this queue
     * @param type        only return matches of this type
     * @param beginIndex  the index of the first match to return (defaults to 0)
     * @param count       the amount of matches to return (defaults to 20, valid values are 0 to 100)
     * @param startTime   only return matches after this time (epoch seconds)
     * @param endTime     only return matches before this time (epoch seconds)
     * @return the match ids, or an empty list if there are none
     */
    public List<String> getMatchListByAccessToken(RegionShard server, String accessToken, GameQueueType queue, MatchlistMatchType type, Integer beginIndex, Integer count, Long startTime, Long endTime)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withAccessToken(accessToken)
                                                       .withEndpoint(URLEndpoint.V1_RSO_MATCHLIST)
                                                       .withPlatform(server);

        if (queue != null)
        {
            builder.withQueryParameter(Constants.QUEUE_PLACEHOLDER_DATA, queue.getValues()[0].toString());
        }
        if (type != null)
        {
            builder.withQueryParameter(Constants.TYPE_PLACEHOLDER_DATA, type.getValue());
        }
        if (beginIndex != null)
        {
            builder.withQueryParameter(Constants.START_PLACEHOLDER_DATA, String.valueOf(beginIndex));
        }
        if (count != null)
        {
            builder.withQueryParameter(Constants.COUNT_PLACEHOLDER_DATA, String.valueOf(count));
        }
        if (startTime != null)
        {
            builder.withQueryParameter(Constants.STARTTIME_PLACEHOLDER_DATA, String.valueOf(startTime));
        }
        if (endTime != null)
        {
            builder.withQueryParameter(Constants.ENDTIME_PLACEHOLDER_DATA, String.valueOf(endTime));
        }

        List<String> matches = builder.buildAs(StringList.class);
        return matches != null ? matches : Collections.emptyList();
    }

    /**
     * A match of a player that signed in with RSO (lol-rso-match-v1)
     *
     * @param server      the region the match was played in
     * @param accessToken the access token of the player
     * @param matchId     the id of the match
     * @return the match, or null if it was not found
     */
    @Nullable
    public LOLMatch getMatchByAccessToken(RegionShard server, String accessToken, String matchId)
    {
        GAMHSMatch match = new DataCallBuilder(context).withAccessToken(accessToken)
                                                .withURLParameter(Constants.MATCH_ID_PLACEHOLDER, matchId)
                                                .withEndpoint(URLEndpoint.V1_RSO_MATCH)
                                                .withPlatform(server)
                                                .buildAs(GAMHSMatch.class);

        return match != null ? context.bind(match.asLOLMatch()) : null;
    }

    /**
     * The timeline of a match of a player that signed in with RSO (lol-rso-match-v1)
     *
     * @param server      the region the match was played in
     * @param accessToken the access token of the player
     * @param matchId     the id of the match
     * @return the timeline, or null if it was not found
     */
    @Nullable
    public LOLTimeline getTimelineByAccessToken(RegionShard server, String accessToken, String matchId)
    {
        GAMHSMatch timeline = new DataCallBuilder(context).withAccessToken(accessToken)
                                                   .withURLParameter(Constants.MATCH_ID_PLACEHOLDER, matchId)
                                                   .withEndpoint(URLEndpoint.V1_RSO_TIMELINE)
                                                   .withPlatform(server)
                                                   .buildAs(GAMHSMatch.class);

        return timeline != null ? context.bind(timeline.asLOLTimeline()) : null;
    }
    
    /**
     * Runs {@link #getMatchList} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> getMatchListAsync(RegionShard server, String puuid, GameQueueType queue, MatchlistMatchType type, Integer beginIndex, Integer count, Long startTime, Long endTime)
    {
        return context.callAsync(() -> getMatchList(server, puuid, queue, type, beginIndex, count, startTime, endTime));
    }
    
    /**
     * Runs {@link #getMatch} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<LOLMatch> getMatchAsync(RegionShard server, String matchId)
    {
        return context.callAsync(() -> getMatch(server, matchId));
    }
    
    /**
     * Runs {@link #getTimeline} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<LOLTimeline> getTimelineAsync(RegionShard server, String matchId)
    {
        return context.callAsync(() -> getTimeline(server, matchId));
    }
    
    /**
     * Like {@link #getMatchReplaysByAccessToken}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<LOLMatchReplayList> getMatchReplaysByAccessTokenAsync(RegionShard server, String accessToken)
    {
        try
        {
            // the calls using an access token are not cached, as the result depends on who the token belongs to
            return new DataCallBuilder(context).withAccessToken(accessToken)
                                        .withEndpoint(URLEndpoint.V5_MATCH_REPLAYS)
                                        .withPlatform(server)
                                        .buildAsAsync(LOLMatchReplayList.class);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getMatchListByAccessToken} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> getMatchListByAccessTokenAsync(RegionShard server, String accessToken, GameQueueType queue, MatchlistMatchType type, Integer beginIndex, Integer count, Long startTime, Long endTime)
    {
        return context.callAsync(() -> getMatchListByAccessToken(server, accessToken, queue, type, beginIndex, count, startTime, endTime));
    }
    
    /**
     * Runs {@link #getMatchByAccessToken} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<LOLMatch> getMatchByAccessTokenAsync(RegionShard server, String accessToken, String matchId)
    {
        return context.callAsync(() -> getMatchByAccessToken(server, accessToken, matchId));
    }
    
    /**
     * Runs {@link #getTimelineByAccessToken} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<LOLTimeline> getTimelineByAccessTokenAsync(RegionShard server, String accessToken, String matchId)
    {
        return context.callAsync(() -> getTimelineByAccessToken(server, accessToken, matchId));
    }
}
