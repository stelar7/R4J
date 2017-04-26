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
    
    public MatchList getRecentMatches(Platform server, long accountId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, String.valueOf(accountId))
                                                       .withEndpoint(URLEndpoint.V3_MATCHLIST_RECENT)
                                                       .withPlatform(server);
        
        return (MatchList) builder.build();
    }
    
    public Match getMatch(Platform server, long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withEndpoint(URLEndpoint.V3_MATCH)
                                                       .withPlatform(server);
        
        return (Match) builder.build();
    }
    
    public MatchTimeline getTimeline(Platform server, long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withEndpoint(URLEndpoint.V3_TIMELINE)
                                                       .withPlatform(server);
        
        return (MatchTimeline) builder.build();
    }
}
