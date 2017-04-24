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
    
    public static MatchAPI getInstance()
    {
        return MatchAPI.INSTANCE;
    }
    
    private MatchAPI()
    {
        // Hide public constructor
    }
    
    public MatchList getOldMatchList(Server server, long summonerId, Optional<Long> endTime, Optional<Integer> endIndex,
                                               Optional<EnumSet<RankedQueueType>> rankedQueues, Optional<EnumSet<SeasonType>> seasons,
                                               Optional<Long> beginTime, Optional<Integer> beginIndex, Optional<List<Integer>> championIds)
    {
        DataCallBuilder builder = new DataCallBuilder().withURL("https://{server}.api.riotgames.com/{game}/{service}/{version}/{resource}")
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withEndpoint(URLEndpoint.OLD_MATCHLIST)
                                                       .withServer(server);
        
        endTime.ifPresent(value -> builder.withURLData(Constants.ENDTIME_PLACEHOLDER_DATA, String.valueOf(value)));
        endIndex.ifPresent(value -> builder.withURLData(Constants.ENDINDEX_PLACEHOLDER_DATA, String.valueOf(value)));
        
        rankedQueues.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RANKEDQUEUE_PLACEHOLDER_DATA, flag.getValue())));
        seasons.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.SEASON_PLACEHOLDER_DATA, flag.getValue())));
        
        beginTime.ifPresent(value -> builder.withURLData(Constants.BEGINTIME_PLACEHOLDER_DATA, String.valueOf(value)));
        beginIndex.ifPresent(value -> builder.withURLData(Constants.BEGININDEX_PLACEHOLDER_DATA, String.valueOf(value)));
        
        championIds.ifPresent(value -> value.forEach(id -> builder.withURLData(Constants.CHAMPIDS_PLACEHOLDER_DATA, String.valueOf(id))));
        
        return (MatchList) builder.build();
    }
    
    
    public MatchList getMatchList(Platform server, long summonerId, Optional<Long> endTime, Optional<Integer> endIndex,
                                            Optional<EnumSet<RankedQueueType>> rankedQueues, Optional<EnumSet<SeasonType>> seasons,
                                            Optional<Long> beginTime, Optional<Integer> beginIndex, Optional<List<Integer>> championIds)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_MATCHLIST)
                                                       .withPlatform(server);
        
        endTime.ifPresent(value -> builder.withURLData(Constants.ENDTIME_PLACEHOLDER_DATA, String.valueOf(value)));
        endIndex.ifPresent(value -> builder.withURLData(Constants.ENDINDEX_PLACEHOLDER_DATA, String.valueOf(value)));
        
        rankedQueues.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RANKEDQUEUE_PLACEHOLDER_DATA, flag.getValue())));
        seasons.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.SEASON_PLACEHOLDER_DATA, flag.getValue())));
        
        beginTime.ifPresent(value -> builder.withURLData(Constants.BEGINTIME_PLACEHOLDER_DATA, String.valueOf(value)));
        beginIndex.ifPresent(value -> builder.withURLData(Constants.BEGININDEX_PLACEHOLDER_DATA, String.valueOf(value)));
        
        championIds.ifPresent(value -> value.forEach(id -> builder.withURLData(Constants.CHAMPIDS_PLACEHOLDER_DATA, String.valueOf(id))));
        
        throw new UnsupportedOperationException("Use the old version untill V3 works");
        //return (MatchList) builder.build();
    }
    
    public MatchDetail getMatch(Platform server, long matchId, Optional<Boolean> includeTimeline)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withEndpoint(URLEndpoint.V3_MATCH)
                                                       .withPlatform(server);
        
        includeTimeline.ifPresent(value -> builder.withURLData(Constants.TIMELINE_PLACEHOLDER_DATA, String.valueOf(value)));
        
        throw new UnsupportedOperationException("Use the old version untill V3 works");
        
        //return (MatchDetail) builder.build();
    }
    
    
    public MatchDetail getOldMatch(Server server, long matchId, Optional<Boolean> includeTimeline)
    {
        DataCallBuilder builder = new DataCallBuilder().withURL("https://{server}.api.riotgames.com/{game}/{service}/{version}/{resource}")
                                                       .withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withEndpoint(URLEndpoint.OLD_MATCH)
                                                       .withServer(server);
        
        includeTimeline.ifPresent(value -> builder.withURLData(Constants.TIMELINE_PLACEHOLDER_DATA, String.valueOf(value)));
        
        return (MatchDetail) builder.build();
    }
}
