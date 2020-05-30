package no.stelar7.api.r4j.impl.lol.async;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.champion.ChampionRotationInfo;

import java.util.*;
import java.util.concurrent.*;

public class AsyncChampionAPI
{
    private static final AsyncChampionAPI INSTANCE = new AsyncChampionAPI();
    
    public static AsyncChampionAPI getInstance()
    {
        return AsyncChampionAPI.INSTANCE;
    }
    
    private static final Map<LeagueShard, ExecutorService> threadPool = new EnumMap(LeagueShard.class)
    {{
        for (LeagueShard platform : LeagueShard.values())
        {
            put(platform, Executors.newFixedThreadPool(1));
        }
    }};
    
    
    private AsyncChampionAPI()
    {
        // Hide public constructor
    }
    
    public CompletableFuture<ChampionRotationInfo> getFreeToPlayRotation(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPION_ROTATIONS)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        return DataCall.getCacheProvider()
                       .get(URLEndpoint.V3_CHAMPION_ROTATIONS, data)
                       .map(o -> CompletableFuture.completedFuture((ChampionRotationInfo) o))
                       .orElseGet(() -> CompletableFuture.supplyAsync(() -> {
                           try
                           {
                               ChampionRotationInfo cl = (ChampionRotationInfo) builder.build();
                
                               data.put("value", cl);
                               DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPION_ROTATIONS, data);
                
                               return cl;
                           } catch (ClassCastException e)
                           {
                               return null;
                           }
                       }, threadPool.get(server)));
        
    }
}
