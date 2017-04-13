package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.pojo.match.MatchDetail;
import no.stelar7.api.l4j8.pojo.matchlist.MatchList;

import java.util.*;

public final class MatchAPI
{
    private static final MatchAPI INSTANCE = new MatchAPI();
    
    static MatchAPI getInstance()
    {
        return MatchAPI.INSTANCE;
    }
    
    private MatchAPI()
    {
        // Hide public constructor
    }
    
    
    public Optional<MatchList> getMatchList(Platform server, long summonerId, Optional<Long> endTime, Optional<Integer> endIndex,
                                            Optional<EnumSet<RankedQueueType>> rankedQueues, Optional<EnumSet<SeasonType>> seasons,
                                            Optional<Long> beginTime, Optional<Integer> beginIndex, Optional<List<Integer>> championIds)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_MATCHLIST)
                                                       .withPlatform(server);
        
        endTime.ifPresent(value -> builder.withURLData(Constants.ENDTIME_PLACEHOLDER_DATA, String.valueOf(value)));
        endIndex.ifPresent(value -> builder.withURLData(Constants.ENDINDEX_PLACEHOLDER_DATA, String.valueOf(value)));
        
        rankedQueues.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.SEASON_PLACEHOLDER_DATA, flag.getValue())));
        seasons.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RANKEDQUEUE_PLACEHOLDER_DATA, flag.getValue())));
        
        beginTime.ifPresent(value -> builder.withURLData(Constants.BEGINTIME_PLACEHOLDER_DATA, String.valueOf(value)));
        beginIndex.ifPresent(value -> builder.withURLData(Constants.BEGININDEX_PLACEHOLDER_DATA, String.valueOf(value)));
        
        championIds.ifPresent(value -> value.forEach(id -> builder.withURLData(Constants.CHAMPIDS_PLACEHOLDER_DATA, String.valueOf(id))));
        
        return Optional.of((MatchList) builder.build());
    }
    
    public Optional<MatchDetail> getMatch(Platform server, long matchId, Optional<Boolean> includeTimeline)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withEndpoint(URLEndpoint.V3_MATCH)
                                                       .withPlatform(server);
        
        includeTimeline.ifPresent(value -> builder.withURLData(Constants.TIMELINE_PLACEHOLDER_DATA, String.valueOf(value)));
        
        return Optional.of((MatchDetail) builder.build());
    }
}
