package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.flags.ChampDataFlags;
import no.stelar7.api.l4j8.impl.StaticAPI;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;

import java.util.*;

public final class StaticCache
{
    private static final StaticCache INSTANCE = new StaticCache();
    
    public static StaticCache getInstance()
    {
        return StaticCache.INSTANCE;
    }
    
    private StaticCache()
    {
        // Hide public constructor
    }
    
    
    private final Map<Integer, StaticChampion> championCache = new HashMap<>();
    
    public StaticChampion getChampion(int champion)
    {
        if (championCache.isEmpty())
        {
            if (DataCall.VERBOSE_DEBUGGING)
            {
                System.out.println("Champion Cache is empty, pulling data");
            }
            
            championCache.putAll(StaticAPI.getInstance().getChampions(Platform.EUW1, EnumSet.of(ChampDataFlags.ALL), null, null));
            
            if (DataCall.VERBOSE_DEBUGGING)
            {
                System.out.format("Data pulled, now contains %s items%n", championCache.size());
            }
        }
        
        if (DataCall.VERBOSE_DEBUGGING)
        {
            System.out.println("Loading data from champion cache: " + champion);
        }
        return championCache.computeIfAbsent(champion, id -> StaticAPI.getInstance().getChampion(Platform.EUW1, id, EnumSet.of(ChampDataFlags.ALL), null, null));
    }
}
