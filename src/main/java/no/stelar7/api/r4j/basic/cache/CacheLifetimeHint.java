package no.stelar7.api.r4j.basic.cache;

import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CacheLifetimeHint
{
    private static final long YEARS_1    = TimeUnit.DAYS.toMillis(365);
    private static final long DAYS_7     = TimeUnit.DAYS.toMillis(7);
    private static final long DAYS_1     = TimeUnit.DAYS.toMillis(1);
    private static final long HOURS_12   = TimeUnit.HOURS.toMillis(12);
    private static final long HOURS_6    = TimeUnit.HOURS.toMillis(6);
    private static final long HOURS_1    = TimeUnit.HOURS.toMillis(1);
    private static final long MINUTES_30 = TimeUnit.MINUTES.toMillis(30);
    private static final long MINUTES_10 = TimeUnit.MINUTES.toMillis(10);
    private static final long MINUTES_1  = TimeUnit.MINUTES.toMillis(1);
    private static final long SECONDS_5  = TimeUnit.SECONDS.toMillis(5);
    
    private final Map<URLEndpoint, Long> timeToLiveMS = new EnumMap<>(URLEndpoint.class);
    
    public void add(URLEndpoint endpoint, long time, TimeUnit unit)
    {
        timeToLiveMS.put(endpoint, unit.toMillis(time));
    }
    
    public void add(URLEndpoint endpoint, long time)
    {
        timeToLiveMS.put(endpoint, time);
    }
    
    public Long get(URLEndpoint endpoint)
    {
        return timeToLiveMS.getOrDefault(endpoint, CacheLifetimeHint.YEARS_1);
    }
    
    public static final CacheLifetimeHint DEFAULTS = new CacheLifetimeHint();
    
    static
    {
        for (URLEndpoint endpoint : URLEndpoint.values())
        {
            DEFAULTS.add(endpoint, YEARS_1);
        }
        
        DEFAULTS.add(URLEndpoint.DDRAGON_REALMS, DAYS_1);
        DEFAULTS.add(URLEndpoint.DDRAGON_VERSIONS, DAYS_1);
        
        DEFAULTS.add(URLEndpoint.V4_THIRD_PARTY_CODE, MINUTES_10);
        
        DEFAULTS.add(URLEndpoint.V4_MATCHLIST, MINUTES_10);
        DEFAULTS.add(URLEndpoint.V1_TFT_MATCHLIST, MINUTES_10);
        
        DEFAULTS.add(URLEndpoint.V4_SPECTATOR_CURRENT, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V4_SPECTATOR_FEATURED, MINUTES_30);
        
        DEFAULTS.add(URLEndpoint.V4_MASTERY_BY_CHAMPION, MINUTES_10);
        DEFAULTS.add(URLEndpoint.V4_MASTERY_BY_ID, MINUTES_10);
        DEFAULTS.add(URLEndpoint.V4_MASTERY_SCORE, MINUTES_10);
        
        DEFAULTS.add(URLEndpoint.V3_CHAMPION_ROTATIONS, DAYS_1);
        
        DEFAULTS.add(URLEndpoint.V1_TFT_LEAGUE, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V1_TFT_LEAGUE_ENTRY, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V1_TFT_LEAGUE_MASTER, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V1_TFT_LEAGUE_RANK, MINUTES_30);
        
        DEFAULTS.add(URLEndpoint.V4_LEAGUE, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V4_LEAGUE_ENTRY, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V4_LEAGUE_RANK, MINUTES_30);
    
        DEFAULTS.add(URLEndpoint.V1_VAL_MATCHLIST_BY_PUUID, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V1_LOR_RANKED_LEADERBOARD, MINUTES_30);
        DEFAULTS.add(URLEndpoint.V1_VAL_RECENT_MATCH_BY_QUEUE, MINUTES_10);
    }
}
