package no.stelar7.api.r4j.impl.lol.async;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
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
    
    private static final Map<Platform, ExecutorService> threadPool = new EnumMap(Platform.class)
    {{
        for (Platform platform : Platform.values())
        {
            put(platform, Executors.newFixedThreadPool(1));
        }
    }};
    
    
    private AsyncChampionAPI()
    {
        // Hide public constructor
    }
    
    public CompletableFuture<ChampionRotationInfo> getFreeToPlayRotation(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPION_ROTATIONS)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPION_ROTATIONS, server);
        if (chl.isPresent())
        {
            return CompletableFuture.completedFuture((ChampionRotationInfo) chl.get());
        }
        
        return CompletableFuture.supplyAsync(() -> {
            try
            {
                ChampionRotationInfo cl = (ChampionRotationInfo) builder.build();
                DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPION_ROTATIONS, cl, server);
                return cl;
            } catch (ClassCastException e)
            {
                return null;
            }
        }, threadPool.get(server));
    }
}
