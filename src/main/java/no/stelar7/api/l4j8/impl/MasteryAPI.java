package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;

import java.util.*;

public final class MasteryAPI
{
    
    private static final MasteryAPI INSTANCE = new MasteryAPI();
    
    static MasteryAPI getInstance()
    {
        return MasteryAPI.INSTANCE;
    }
    
    private MasteryAPI()
    {
        // Hide public constructor
    }
    
    
    /**
     * The response object contains the summoners masteryscore.
     *
     * @param server     the region to execute against
     * @param summonerId the summonerId
     * @return Optional FeaturedGames
     */
    public Optional<Integer> getMasteryScore(Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_MASTERY_SCORE)
                                                       .withPlatform(server);
        
        return Optional.of((Integer) builder.build());
    }
    
    
    /**
     * The response object contains the summoners mastery of a champion.
     *
     * @param server     the region to execute against
     * @param summonerId the summonerId
     * @param championId the championId
     * @return Optional ChampionMastery
     */
    public Optional<ChampionMastery> getChampionMastery(Platform server, long summonerId, int championId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withURLParameter(Constants.CHAMPION_ID_PLACEHOLDER, String.valueOf(championId))
                                                       .withEndpoint(URLEndpoint.V3_MASTERY_BY_CHAMPION)
                                                       .withPlatform(server);
        
        return Optional.of((ChampionMastery) builder.build());
    }
    
    
    /**
     * The response object contains a list of the summoners mastery of champions.
     *
     * @param server     the region to execute against
     * @param summonerId the summonerId
     * @return Optional ChampionMastery
     */
    public Optional<List<ChampionMastery>> getMasteries(Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_MASTERY_BY_ID)
                                                       .withPlatform(server);
        
        return Optional.of((List<ChampionMastery>) builder.build());
    }
    
}
