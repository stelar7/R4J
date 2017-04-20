package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.ChampionType;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.*;

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
        
        return Optional.ofNullable((Integer) builder.build());
    }
    
    /**
     * Gets the champions with the most masteryscore from the summoner
     *
     * @param server     the server the summoner plays on
     * @param summonerId the summonerid of the summoner
     * @param count      the amount of champions to get
     * @return A sorted list of ChampionMastery
     */
    public Optional<List<ChampionMastery>> getTopChampions(Platform server, long summonerId, Optional<Integer> count)
    {
        Optional<List<ChampionMastery>> list = getChampionMasteries(server, summonerId);
        
        if (!list.isPresent())
        {
            return Optional.empty();
        }
        
        List<ChampionMastery> data = list.get()
                                         .stream()
                                         .sorted(Comparator.comparing(ChampionMastery::getChampionPoints))
                                         .limit(count.orElseGet(() -> 3))
                                         .collect(Collectors.toList());
        
        return Optional.ofNullable(data);
    }
    
    
    /**
     * The response object contains the summoners mastery of a champion.
     * Only championid and summonerid is present if the level == 0
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
        
        ChampionMastery mastery = (ChampionMastery) builder.build();
        
        if (mastery == null)
        {
            try
            {
                mastery = new ChampionMastery();
                
                Field player = mastery.getClass().getDeclaredField("playerId");
                player.setAccessible(true);
                player.set(mastery, summonerId);
                
                Field champ = mastery.getClass().getDeclaredField("championId");
                champ.setAccessible(true);
                champ.set(mastery, ChampionType.getFromCode(String.valueOf(championId)).get());
                
                Field level = mastery.getClass().getDeclaredField("championLevel");
                level.setAccessible(true);
                level.set(mastery, 0);
            } catch (NoSuchFieldException | IllegalAccessException e)
            {
                Logger.getGlobal().warning("Class has changed, please fix me");
            }
        }
        
        return Optional.ofNullable(mastery);
    }
    
    
    /**
     * The response object contains a list of the summoners mastery of champions.
     * Does not return a value for champions with mastery level 0
     *
     * @param server     the region to execute against
     * @param summonerId the summonerId
     * @return Optional ChampionMastery
     */
    public Optional<List<ChampionMastery>> getChampionMasteries(Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_MASTERY_BY_ID)
                                                       .withPlatform(server);
        
        return Optional.ofNullable((List<ChampionMastery>) builder.build());
    }
    
}
