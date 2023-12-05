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
    private       String      summonerId;
    private       String      puuid;
    private final Integer  championId;
    
    private ChampionMasteryBuilder(LeagueShard platform, String summonerId, Integer championId, String puuid)
    {
        this.platform = platform;
        this.summonerId = summonerId;
        this.championId = championId;
        this.puuid = puuid;
    }
    
    public ChampionMasteryBuilder()
    {
        this.platform = LeagueShard.UNKNOWN;
        this.summonerId = null;
        this.championId = null;
        this.puuid = null;
    }
    
    
    /**
     * @deprecated Champion mastery endpoints using the summoner id will be removed on 15 December 2023.
     * Please use {@code ChampionMasteryBuilder#withPUUID(String)} instead.
     */
    @Deprecated
    public ChampionMasteryBuilder withSummonerId(String id)
    {
        return new ChampionMasteryBuilder(this.platform, id, this.championId, this.puuid);
    }
    
    public ChampionMasteryBuilder withPUUID(String puuid)
    {
        return new ChampionMasteryBuilder(this.platform, this.summonerId, this.championId, puuid);
    }
    
    public ChampionMasteryBuilder withChampionId(Integer id)
    {
        return new ChampionMasteryBuilder(this.platform, this.summonerId, id, this.puuid);
    }
    
    public ChampionMasteryBuilder withPlatform(LeagueShard platform)
    {
        return new ChampionMasteryBuilder(platform, this.summonerId, this.championId, this.puuid);
    }
    
    public Integer getMasteryScore()
    {
        if (this.summonerId == null || this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.summonerId)
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_SCORE)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", this.platform);
        data.put("id", summonerId);
        
        
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
        if (this.summonerId == null || this.platform == LeagueShard.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V4_MASTERY_BY_ID)
                                                       .withPlatform(this.platform);
        
        if(this.puuid != null)
        {
          builder = builder.withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid);
        }
        else
        {
          builder = builder.withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.summonerId);
        }
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("id", summonerId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_MASTERY_BY_ID, data);
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
            DataCall.getCacheProvider().store(URLEndpoint.V4_MASTERY_BY_ID, data);
            
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
        if (this.championId == null || !(this.summonerId != null || this.puuid != null) || this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.CHAMPION_ID_PLACEHOLDER, String.valueOf(this.championId))
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_BY_CHAMPION)
                                                       .withPlatform(this.platform);
        
        
        String id;
        if(this.puuid != null)
        {
          builder = builder.withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid);
          id = this.puuid;
        } 
        else 
        {
          builder = builder.withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.summonerId);
          id = this.summonerId;
        }
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("id", id);
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
                player.set(mastery, this.summonerId);
                
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
