package no.stelar7.api.r4j.impl.lol.builders.championmastery;

import org.jetbrains.annotations.Nullable;
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
    private R4JContext context = R4JContext.getDefault();

    /**
     * Uses the credentials, cache and limiters of the context instead of the default ones
     *
     * @param context the context to use
     * @return this
     */
    public ChampionMasteryBuilder withContext(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
        return this;
    }

    
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
        return new ChampionMasteryBuilder(this.platform, id, this.championId).withContext(this.context);
    }
    
    public ChampionMasteryBuilder withChampionId(Integer id)
    {
        return new ChampionMasteryBuilder(this.platform, this.puuid, id).withContext(this.context);
    }
    
    public ChampionMasteryBuilder withPlatform(LeagueShard platform)
    {
        return new ChampionMasteryBuilder(platform, this.puuid, this.championId).withContext(this.context);
    }
    
    @Nullable
    public Integer getMasteryScore()
    {
        if (this.puuid == null || this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_SCORE)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", this.platform);
        data.put("puuid", puuid);
        
        
        return builder.buildCached(Integer.class, data);
    }
    
    public List<ChampionMastery> getChampionMasteries()
    {
        if (this.puuid == null || this.platform == LeagueShard.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_BY_PUUID)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("puuid", puuid);
        
        List<ChampionMastery> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }
    
    public List<ChampionMastery> getTopChampions(Integer count)
    {
        List<ChampionMastery> list = getChampionMasteries();
        
        return list.stream().sorted(Comparator.comparing(ChampionMastery::getChampionPoints).reversed())
                   .limit(count != null ? count : 3)
                   .collect(Collectors.toList());
        
    }
    
    
    @Nullable
    public ChampionMastery getChampionMastery()
    {
        if (this.championId == null || this.puuid == null || this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                       .withURLParameter(Constants.CHAMPION_ID_PLACEHOLDER, String.valueOf(this.championId))
                                                       .withEndpoint(URLEndpoint.V4_MASTERY_BY_CHAMPION)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("puuid", puuid);
        data.put("champion", championId);
        
        
        Optional<?> chl = context.getCacheProvider().get(URLEndpoint.V4_MASTERY_BY_CHAMPION, data);
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
                context.getCacheProvider().store(URLEndpoint.V4_MASTERY_BY_CHAMPION, data);
                
                return mastery;
                
            } catch (NoSuchFieldException | IllegalAccessException e)
            {
                Logger.getGlobal().warning("Class has changed, please fix me");
            }
        }
        
        data.put("value", masterObj);
        context.getCacheProvider().store(URLEndpoint.V4_MASTERY_BY_CHAMPION, data);
        
        return (ChampionMastery) masterObj;
    }
}
