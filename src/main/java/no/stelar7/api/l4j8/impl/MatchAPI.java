package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.matchlist.MatchList;

import java.util.*;

public final class MatchAPI
{
    private static final MatchAPI INSTANCE = new MatchAPI();
    
    public static MatchAPI getInstance()
    {
        return MatchAPI.INSTANCE;
    }
    
    private MatchAPI()
    {
        // Hide public constructor
    }
    
    /**
     * Returns a list of the accounts ranked games
     * <p>
     * A number of optional parameters are provided for filtering.
     * It is up to the caller to ensure that the combination of filter parameters provided is valid for the requested account, otherwise, no matches may be returned.
     * Note that if either beginIndex or endIndex are specified, then both must be specified and endIndex must be greater than beginIndex.
     * If endTime is specified, but not beginTime, then beginTime is effectively the start of the account's match history.
     * If beginTime is specified, but not endTime, then endTime is effectively the current time.
     * Note that endTime should be greater than beginTime if both are specified, although there is no maximum limit on their range.
     *
     * @param server       the platform the account is on
     * @param accountId    the account to check
     * @param beginTime    optional filter the games started after this time
     * @param endTime      optional filter for games started before this time
     * @param beginIndex   optional filter for skipping the first x games
     * @param endIndex     optional filter for skipping only showing x games
     * @param rankedQueues optional filter for selecting the queue (Only ranked queues allowed)
     * @param seasons      optional filter for selecting the season
     * @param championIds  optional filter for selecting the champion played
     * @return MatchList
     */
    public MatchList getMatchList(Platform server, long accountId,
                                  Optional<Long> beginTime, Optional<Long> endTime,
                                  Optional<Integer> beginIndex, Optional<Integer> endIndex,
                                  Optional<EnumSet<GameQueueType>> rankedQueues, Optional<EnumSet<SeasonType>> seasons, Optional<List<Integer>> championIds)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, String.valueOf(accountId))
                                                       .withEndpoint(URLEndpoint.V3_MATCHLIST)
                                                       .withPlatform(server);
        
        endTime.ifPresent(value -> builder.withURLData(Constants.ENDTIME_PLACEHOLDER_DATA, String.valueOf(value)));
        endIndex.ifPresent(value -> builder.withURLData(Constants.ENDINDEX_PLACEHOLDER_DATA, String.valueOf(value)));
        
        rankedQueues.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RANKEDQUEUE_PLACEHOLDER_DATA, String.valueOf(flag.getValue()))));
        seasons.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.SEASON_PLACEHOLDER_DATA, String.valueOf(flag.getValue()))));
        
        beginTime.ifPresent(value -> builder.withURLData(Constants.BEGINTIME_PLACEHOLDER_DATA, String.valueOf(value)));
        beginIndex.ifPresent(value -> builder.withURLData(Constants.BEGININDEX_PLACEHOLDER_DATA, String.valueOf(value)));
        
        championIds.ifPresent(value -> value.forEach(id -> builder.withURLData(Constants.CHAMPIDS_PLACEHOLDER_DATA, String.valueOf(id))));
        
        return (MatchList) builder.build();
    }
    
    /**
     * A list of the accounts most recent games (includes normal games)
     *
     * @param server    the platform the account is on ATM
     * @param accountId the account to check
     * @return MatchList
     */
    public MatchList getRecentMatches(Platform server, long accountId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, String.valueOf(accountId))
                                                       .withEndpoint(URLEndpoint.V3_MATCHLIST_RECENT)
                                                       .withPlatform(server);
        
        return (MatchList) builder.build();
    }
    
    /**
     * Returns the match data from a match id
     *
     * @param server  the platform the match was played on
     * @param matchId the id to check
     * @return Match
     */
    public Match getMatch(Platform server, long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withEndpoint(URLEndpoint.V3_MATCH)
                                                       .withPlatform(server);
        
        return (Match) builder.build();
    }
    
    /**
     * Returns the timeline relating to a match.
     * Not avaliable for matches older than a year
     *
     * @param server  the platform to find the match on
     * @param matchId the matchId to find timeline for
     * @return MatchTimeline if avaliable
     */
    public Optional<MatchTimeline> getTimeline(Platform server, long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withEndpoint(URLEndpoint.V3_TIMELINE)
                                                       .withPlatform(server);
        
        return Optional.ofNullable((MatchTimeline) builder.build());
    }
}
