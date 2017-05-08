package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.pojo.match.*;

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
     * @param server      the platform the account is on
     * @param accountId   the account to check
     * @param beginTime   optional filter the games started after this time
     * @param endTime     optional filter for games started before this time
     * @param beginIndex  optional filter for skipping the first x games
     * @param endIndex    optional filter for skipping only showing x games
     * @param rankedQueue optional filter for selecting the queue (Only ranked queues allowed)
     * @param season      optional filter for selecting the season
     * @param championId  optional filter for selecting the champion played
     * @return MatchList
     */
    public List<MatchReference> getMatchList(Platform server, long accountId,
                                             Long beginTime, Long endTime,
                                             Integer beginIndex, Integer endIndex,
                                             GameQueueType rankedQueue,
                                             SeasonType season,
                                             Integer championId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, String.valueOf(accountId))
                                                       .withEndpoint(URLEndpoint.V3_MATCHLIST)
                                                       .withPlatform(server);
        
        if (beginIndex != null)
        {
            builder.withURLData(Constants.BEGININDEX_PLACEHOLDER_DATA, beginIndex.toString());
        }
        if (endIndex != null)
        {
            builder.withURLData(Constants.ENDINDEX_PLACEHOLDER_DATA, endIndex.toString());
        }
        
        if (beginTime != null)
        {
            builder.withURLData(Constants.BEGINTIME_PLACEHOLDER_DATA, beginTime.toString());
        }
        if (endTime != null)
        {
            builder.withURLData(Constants.ENDTIME_PLACEHOLDER_DATA, endTime.toString());
        }
        if (rankedQueue != null)
        {
            builder.withURLDataAsSet(Constants.QUEUE_PLACEHOLDER_DATA, String.valueOf(rankedQueue.getValue()));
        }
        if (season != null)
        {
            builder.withURLDataAsSet(Constants.SEASON_PLACEHOLDER_DATA, String.valueOf(season.getValue()));
        }
        if (championId != null)
        {
            builder.withURLDataAsSet(Constants.CHAMPION_PLACEHOLDER_DATA, String.valueOf(championId));
        }
        
        
        MatchList list = (MatchList) builder.build();
        return list.getMatches();
    }
    
    /**
     * A list of the accounts most recent games (includes normal games)
     *
     * @param server    the platform the account is on ATM
     * @param accountId the account to check
     * @return MatchList
     */
    public List<MatchReference> getRecentMatches(Platform server, long accountId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, String.valueOf(accountId))
                                                       .withEndpoint(URLEndpoint.V3_MATCHLIST_RECENT)
                                                       .withPlatform(server);
        
        MatchList list = (MatchList) builder.build();
        return list.getMatches();
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
    public MatchTimeline getTimeline(Platform server, long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withEndpoint(URLEndpoint.V3_TIMELINE)
                                                       .withPlatform(server);
        
        return (MatchTimeline) builder.build();
    }
}
