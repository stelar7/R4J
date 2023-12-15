package no.stelar7.api.r4j.impl.lol.builders.championmastery;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.championmastery.ChampionMastery;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChampionMasteryBuilder
{
    
    private final LeagueShard platform;
    private       String      puuid;
    private final Integer     championId;
    
    private ChampionMasteryBuilder(LeagueShard platform, String puuid, Integer championId)
    {
        this.platform = platform;
        this.puuid = puuid;
        this.championId = championId;
    }
    
    public ChampionMasteryBuilder()
    {
        this.platform = LeagueShard.UNKNOWN;
        this.puuid = null;
        this.championId = null;
    }
    
    
    public ChampionMasteryBuilder withPUUID(String id)
    {
        return new ChampionMasteryBuilder(this.platform, id, this.championId);
    }
    
    public ChampionMasteryBuilder withChampionId(Integer id)
    {
        return new ChampionMasteryBuilder(this.platform, this.puuid, id);
    }
    
    public ChampionMasteryBuilder withPlatform(LeagueShard platform)
    {
        return new ChampionMasteryBuilder(platform, this.puuid, this.championId);
    }
    
    public Integer getMasteryScore()
    {
        if (this.puuid == null || this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_SCORE)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", this.platform);
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
    
    public List<ChampionMastery> getChampionMasteries()
    {
        if (this.puuid == null || this.platform == LeagueShard.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_BY_PUUID)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
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
    
    public List<ChampionMastery> getTopChampions(Integer count)
    {
        List<ChampionMastery> list = getChampionMasteries();
        
        return list.stream().sorted(Comparator.comparing(ChampionMastery::getChampionPoints).reversed())
                   .limit(count != null ? count : 3)
                   .collect(Collectors.toList());
        
    }
    
    
    public ChampionMastery getChampionMastery()
    {
        if (this.championId == null || this.puuid == null || this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                       .withURLParameter(Constants.CHAMPION_ID_PLACEHOLDER, String.valueOf(this.championId))
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_BY_CHAMPION)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("puuid", puuid);
        data.put("champion", championId);
        
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_MASTERY_BY_CHAMPION, data);
        if (chl.isPresent())
        {
            return (ChampionMastery) chl.get();
        }
        
        Object masterObj = builder.build();
        
        if (masterObj instanceof Pair)
        {
            try
            {
                ChampionMastery mastery = new ChampionMastery();
                
                Field player = mastery.getClass().getDeclaredField("playerId");
                player.setAccessible(true);
                player.set(mastery, this.puuid);
                
                Field champ = mastery.getClass().getDeclaredField("championId");
                champ.setAccessible(true);
                champ.set(mastery, this.championId);
                
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
        
        data.put("value", masterObj);
        DataCall.getCacheProvider().store(URLEndpoint.V4_MASTERY_BY_CHAMPION, data);
        
        return (ChampionMastery) masterObj;
    }
}
