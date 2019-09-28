package no.stelar7.api.l4j8.impl.raw;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.utils.*;
import no.stelar7.api.l4j8.pojo.match.*;

import java.util.*;

@SuppressWarnings("unchecked")
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
     * Returns a list of the accounts games
     * <p>
     * A number of optional parameters are provided for filtering.
     * It is up to the caller to ensure that the combination of filter parameters provided is valid for the requested account, otherwise, no matches may be returned.
     * If beginIndex is specified, but not endIndex, then endIndex defaults to beginIndex+100.
     * If endIndex is specified, but not beginIndex, then beginIndex defaults to 0.
     * If both are specified, then endIndex must be greater than beginIndex.
     * The maximum range allowed is 100, otherwise a 400 error code is returned.
     * If beginTime is specified, but not endTime, then these parameters are ignored.
     * If endTime is specified, but not beginTime, then beginTime defaults to the start of the account's match history.
     * If both are specified, then endTime should be greater than beginTime.
     * The maximum time range allowed is one week, otherwise a 400 error code is returned.
     *
     * @param server      the platform the account is on
     * @param accountId   the account to check
     * @param beginTime   optional filter the games started after this time
     * @param endTime     optional filter for games started before this time
     * @param beginIndex  optional filter for skipping the first x games
     * @param endIndex    optional filter for skipping only showing x games
     * @param rankedQueue optional filter for selecting the queue
     * @param season      optional filter for selecting the season
     * @param championId  optional filter for selecting the champion played
     * @return MatchList
     */
    public List<MatchReference> getMatchList(Platform server, String accountId,
                                             Long beginTime, Long endTime,
                                             Integer beginIndex, Integer endIndex,
                                             Set<GameQueueType> rankedQueue,
                                             Set<SeasonType> season,
                                             Set<Integer> championId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, accountId)
                                                       .withEndpoint(URLEndpoint.V3_MATCHLIST)
                                                       .withPlatform(server);
        
        
        if (beginIndex != null)
        {
            builder.withURLData(Constants.BEGININDEX_PLACEHOLDER_DATA, beginIndex.toString());
        }
        if (endIndex != null)
        {
            if ((beginIndex != null ? beginIndex : 0) + 100 - endIndex < 0)
            {
                throw new IllegalArgumentException("begin-endindex out of range! (difference between beginIndex and endIndex is more than 100)");
            }
            
            builder.withURLData(Constants.ENDINDEX_PLACEHOLDER_DATA, endIndex.toString());
        }
        
        if (beginTime != null)
        {
            builder.withURLData(Constants.BEGINTIME_PLACEHOLDER_DATA, beginTime.toString());
        }
        if (endTime != null)
        {
            
            if ((beginTime != null ? beginTime : 0) + 604800000 - endTime < 0)
            {
                throw new IllegalArgumentException("begin-endtime out of range! (difference between beginTime and endTime is more than one week)");
            }
            
            builder.withURLData(Constants.ENDTIME_PLACEHOLDER_DATA, endTime.toString());
        }
        if (rankedQueue != null)
        {
            rankedQueue.forEach(queue -> builder.withURLDataAsSet(Constants.QUEUE_PLACEHOLDER_DATA, String.valueOf(queue.getValues()[0])));
        }
        if (season != null)
        {
            season.forEach(sea -> builder.withURLDataAsSet(Constants.SEASON_PLACEHOLDER_DATA, String.valueOf(sea.getValue())));
        }
        if (championId != null)
        {
            championId.forEach(id -> builder.withURLDataAsSet(Constants.CHAMPION_PLACEHOLDER_DATA, String.valueOf(id)));
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_MATCHLIST, server, accountId, beginTime, endTime, beginIndex, endIndex, rankedQueue, season, championId);
        if (chl.isPresent())
        {
            return ((MatchList) chl.get()).getMatches();
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return Collections.emptyList();
        }
        
        MatchList match = (MatchList) matchObj;
        DataCall.getCacheProvider().store(URLEndpoint.V3_MATCHLIST, match, server, accountId, beginTime, endTime, beginIndex, endIndex, rankedQueue, season, championId);
        return match.getMatches();
    }
    
    public List<String> getGAMHSMatchList(Platform server, String PUUID)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                                                       .withEndpoint(URLEndpoint.V4_GAMHS_MATCHLIST)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_GAMHS_MATCHLIST, server, PUUID);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return Collections.emptyList();
        }
        
        List<String> matchList = (List<String>) matchObj;
        DataCall.getCacheProvider().store(URLEndpoint.V4_GAMHS_MATCHLIST, matchList, server, PUUID);
        return matchList;
    }
    
    /**
     * Returns a list of all games avaliable in the api
     * This list is updated lazily!
     * To get all the items, either iterate the list once, or get(Integer.MAX_VALUE)
     *
     * @param server    the platform the account is on
     * @param accountId the account to check
     * @return {@code List<MatchReference>}
     */
    public LazyList<MatchReference> getMatchList(Platform server, String accountId)
    {
        int increment = 100;
        return new LazyList<>(increment, prevValue -> getMatchList(server, accountId, null, null, prevValue, prevValue + increment, null, null, null));
    }
    
    /**
     * Returns an iterator that transforms all {@code MatchReference} to {@code Match}
     *
     * @param server    the platform the account is on
     * @param accountId the account to check
     * @return {@code MatchIterator}
     */
    public MatchIterator getMatchIterator(Platform server, String accountId)
    {
        return new MatchIterator(getMatchList(server, accountId));
    }
    
    public GAMHSMatch getGAMHSMatch(String gameId)
    {
        String   region   = gameId.split("_")[0].split("-")[1];
        Platform platform = Platform.fromString(region).get();
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                       .withEndpoint(URLEndpoint.V4_GAMHS_MATCH)
                                                       .withPlatform(platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_GAMHS_MATCH, platform, gameId);
        if (chl.isPresent())
        {
            return (GAMHSMatch) chl.get();
        }
        
        try
        {
            GAMHSMatch match = (GAMHSMatch) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V4_GAMHS_MATCH, match, platform, gameId);
            return match;
        } catch (ClassCastException e)
        {
            return null;
        }
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
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_MATCH, server, matchId);
        if (chl.isPresent())
        {
            return (Match) chl.get();
        }
        
        try
        {
            Match match = (Match) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_MATCH, match, server, matchId);
            return match;
        } catch (ClassCastException e)
        {
            return null;
        }
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
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_TIMELINE, server, matchId);
        if (chl.isPresent())
        {
            return (MatchTimeline) chl.get();
        }
        
        try
        {
            MatchTimeline timeline = (MatchTimeline) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_TIMELINE, timeline, server, matchId);
            return timeline;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
}
