package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.RankedQueueType;
import no.stelar7.api.l4j8.pojo.league.League;

import java.util.*;

public final class LeagueAPI
{
    private static final LeagueAPI INSTANCE = new LeagueAPI();
    
    static LeagueAPI getInstance()
    {
        return LeagueAPI.INSTANCE;
    }
    
    private LeagueAPI()
    {
        // Hide public constructor
    }
    
    /**
     * Get master tier leagues.
     *
     * @param server region to get data from
     * @param queue  queueType to get data for
     * @return League
     * @deprecated will be replaced with V3 sometime in the future
     */
    @Deprecated
    public Optional<League> getOldMasterLeague(Server server, RankedQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder().withURL("https://{server}.api.riotgames.com/{game}/{service}/{version}/{resource}")
                                                       .withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLData(Constants.TYPE_PLACEHOLDER_DATA, queue.getValue())
                                                       .withEndpoint(URLEndpoint.OLD_LEAGUE_MASTER)
                                                       .withServer(server);
        
        
        return Optional.ofNullable((League) builder.build());
    }
    
    /**
     * Get master tier leagues.
     *
     * @param server region to get data from
     * @param queue  queueType to get data for
     * @return League
     */
    public Optional<League> getMasterLeague(Platform server, RankedQueueType queue)
    {
       /*
       DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLData(Constants.TYPE_PLACEHOLDER_DATA, queue.getValue())
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE_MASTER)
                                                       .withPlatform(server);
        */
        throw new UnsupportedOperationException("Use the old version untill V3 works");
        
        //return Optional.ofNullable((League) builder.build());
    }
    
    /**
     * Get challenger tier leagues.
     *
     * @param server region to get data from
     * @param queue  queueType to get data for
     * @return League
     * @deprecated will be replaced with V3 sometime in the future
     */
    @Deprecated
    public Optional<League> getOldChallengerLeague(Server server, RankedQueueType queue)
    {
        DataCallBuilder builder = new DataCallBuilder().withURL("https://{server}.api.riotgames.com/{game}/{service}/{version}/{resource}")
                                                       .withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLData(Constants.TYPE_PLACEHOLDER_DATA, queue.getValue())
                                                       .withEndpoint(URLEndpoint.OLD_LEAGUE_CHALLENGER)
                                                       .withServer(server);
        
        
        return Optional.ofNullable((League) builder.build());
    }
    
    /**
     * Get challenger tier leagues.
     *
     * @param server region to get data from
     * @param queue  queueType to get data for
     * @return League
     */
    public Optional<League> getChallengerLeague(Platform server, RankedQueueType queue)
    {
        /*
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLData(Constants.TYPE_PLACEHOLDER_DATA, queue.getValue())
                                                       .withEndpoint(URLEndpoint.OLD_LEAGUE_CHALLENGER)
                                                       .withPlatform(server);
        */
        throw new UnsupportedOperationException("Use the old version untill V3 works");
        
        //return Optional.ofNullable((League) builder.build());
        
    }
    
    
    /**
     * Get league entries mapped by summoner ID for a given list of summoner IDs.
     * (This does not take a list given that V3 is on the way)
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return League
     * @deprecated will be replaced with V3 sometime in the future
     */
    @Deprecated
    public Optional<Map<String, List<League>>> getOldLeagueEntry(Server server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURL("https://{server}.api.riotgames.com/{game}/{service}/{version}/{resource}")
                                                       .withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_IDS_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.OLD_LEAGUE_ENTRY)
                                                       .withServer(server);
        
        
        return Optional.ofNullable((Map<String, List<League>>) builder.build());
    }
    
    /**
     * Get league entries for a summonerId
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return League
     */
    public Optional<List<League>> getLeagueEntry(Platform server, long summonerId)
    {
        /*
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_IDS_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.OLD_LEAGUE_ENTRY)
                                                       .withPlatform(server);
        */
        throw new UnsupportedOperationException("Use the old version untill V3 works");
        
        
        //return Optional.ofNullable((List<League>) builder.build());
    }
    
    /**
     * Get leagues mapped by summoner ID for a given list of summoner IDs.
     * (This does not take a list given that V3 is on the way)
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return League
     * @deprecated will be replaced with V3 sometime in the future
     */
    @Deprecated
    public Optional<Map<String, List<League>>> getOldLeague(Server server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURL("https://{server}.api.riotgames.com/{game}/{service}/{version}/{resource}")
                                                       .withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_IDS_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.OLD_LEAGUE)
                                                       .withServer(server);
        
        
        return Optional.ofNullable((Map<String, List<League>>) builder.build());
    }
    
    /**
     * Get leagues mapped by summoner ID for a given list of summoner IDs.
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return League
     */
    public Optional<List<League>> getLeague(Platform server, long summonerId)
    {
        /*
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_IDS_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.OLD_LEAGUE)
                                                       .withPlatform(server);
        */
        throw new UnsupportedOperationException("Use the old version untill V3 works");
        
        //return Optional.ofNullable((List<League>) builder.build());
    }
}
