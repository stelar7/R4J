package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.championmastery.ChampionMastery;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class MasteryAPI
{
    
    private static final MasteryAPI INSTANCE = new MasteryAPI();
    
    public static MasteryAPI getInstance()
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
     * @param server the region to execute against
     * @param puuid  the puuid
     * @return Optional FeaturedGames
     */
    public Integer getMasteryScore(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_SCORE)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_MASTERY_SCORE, data);
        if (chl.isPresent())
        {
            return (Integer) chl.get();
        }
        
        try
        {
            Integer list = (Integer) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_MASTERY_SCORE, data);
            
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * Gets the champions with the most masteryscore from the summoner
     *
     * @param server the server the summoner plays on
     * @param puuid  the puuid of the summoner
     * @param count  the amount of champions to get
     * @return A sorted list of ChampionMastery
     */
    public List<ChampionMastery> getTopChampions(LeagueShard server, String puuid, Integer count)
    {
        List<ChampionMastery> list = getChampionMasteries(server, puuid);
        
        return list.stream().sorted(Comparator.comparing(ChampionMastery::getChampionPoints).reversed())
                   .limit(count != null ? count : 3)
                   .collect(Collectors.toList());
        
    }
    
    
    /**
     * The response object contains the summoners mastery of a champion.
     * Only championid and summonerid is present if the level == 0
     *
     * @param server     the region to execute against
     * @param puuid      the summonerId
     * @param championId the championId
     * @return Optional ChampionMastery
     */
    public ChampionMastery getChampionMastery(LeagueShard server, String puuid, int championId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withURLParameter(Constants.CHAMPION_ID_PLACEHOLDER, String.valueOf(championId))
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_BY_CHAMPION)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        data.put("champion", championId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_MASTERY_BY_CHAMPION, data);
        if (chl.isPresent())
        {
            return (ChampionMastery) chl.get();
        }
        
        Object masteryObj = builder.build();
        if (masteryObj instanceof Pair)
        {
            try
            {
                ChampionMastery mastery = new ChampionMastery();
                
                Field player = mastery.getClass().getDeclaredField("playerId");
                player.setAccessible(true);
                player.set(mastery, puuid);
                
                Field champ = mastery.getClass().getDeclaredField("championId");
                champ.setAccessible(true);
                champ.set(mastery, championId);
                
                Field level = mastery.getClass().getDeclaredField("championLevel");
                level.setAccessible(true);
                level.set(mastery, 0);
                
                data.put("value", mastery);
                DataCall.getCacheProvider().store(URLEndpoint.V4_MASTERY_BY_CHAMPION, data);
                
                return mastery;
                
            } catch (NoSuchFieldException | IllegalAccessException e)
            {
                Logger.getGlobal().warning("Class has changed, please fix me");
            }
        }
        
        data.put("value", masteryObj);
        DataCall.getCacheProvider().store(URLEndpoint.V4_MASTERY_BY_CHAMPION, data);
        
        return (ChampionMastery) masteryObj;
    }
    
    
    /**
     * The response object contains a list of the summoners mastery of champions.
     * Does not return a value for champions with mastery level 0
     *
     * @param server the region to execute against
     * @param puuid  the puuid
     * @return Optional ChampionMastery
     */
    public List<ChampionMastery> getChampionMasteries(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_BY_PUUID)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_MASTERY_BY_PUUID, data);
        if (chl.isPresent())
        {
            return (List<ChampionMastery>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<ChampionMastery> list = (List<ChampionMastery>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_MASTERY_BY_PUUID, data);
            
            return list;
        } catch (ClassCastException e)
        {
            
            return Collections.emptyList();
        }
    }
    
}
