package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;

import java.util.List;

public final class MatchV5API
{
    private static final MatchV5API INSTANCE = new MatchV5API();
    
    public static MatchV5API getInstance()
    {
        return MatchV5API.INSTANCE;
    }
    
    private MatchV5API()
    {
        // Hide public constructor
    }
    
    public List<String> getMatchList(RegionShard server, String puuid, GameQueueType queue, MatchlistMatchType type, Integer beginIndex, Integer endIndex)
    {
        return new MatchListBuilder(server, puuid, queue, type, beginIndex, endIndex).get();
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
        return new LazyList<>(increment, prevValue -> getMatchList(server, puuid, null, null, prevValue, prevValue + increment));
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
        return new MatchIterator(getMatchList(server, puuid));
    }
    
    /**
     * Returns the match data from a match id
     *
     * @param server  the platform the match was played on
     * @param matchId the id to check
     * @return Match
     */
    public LOLMatch getMatch(RegionShard server, String matchId)
    {
        return new MatchBuilder(server, matchId).getMatch();
    }
    
    /**
     * Returns the timeline relating to a match.
     * Not avaliable for matches older than a year
     *
     * @param server  the platform to find the match on
     * @param matchId the matchId to find timeline for
     * @return MatchTimeline if avaliable
     */
    public LOLTimeline getTimeline(RegionShard server, String matchId)
    {
        return new TimelineBuilder(server, matchId).getTimeline();
    }
}
