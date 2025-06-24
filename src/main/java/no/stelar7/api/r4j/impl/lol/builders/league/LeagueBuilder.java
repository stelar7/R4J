package no.stelar7.api.r4j.impl.lol.builders.league;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.GameQueueType;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.league.*;
import org.slf4j.*;

import java.util.*;

public class LeagueBuilder
{
    
    private static final Logger logger = LoggerFactory.getLogger(LeagueBuilder.class);
    
    private final LeagueShard   platform;
    private final GameQueueType queue;
    private final String        puuid;
    private final String        leagueId;
    
    public LeagueBuilder()
    {
        this.platform = LeagueShard.UNKNOWN;
        this.queue = GameQueueType.RANKED_SOLO_5X5;
        this.puuid = null;
        this.leagueId = null;
    }
    
    private LeagueBuilder(LeagueShard platform, GameQueueType queue, String puuid, String leagueId)
    {
        this.platform = platform;
        this.queue = queue;
        this.puuid = puuid;
        this.leagueId = leagueId;
    }
    
    public LeagueBuilder withPlatform(LeagueShard platform)
    {
        return new LeagueBuilder(platform, this.queue, this.puuid, this.leagueId);
    }
    
    public LeagueBuilder withQueue(GameQueueType queue)
    {
        return new LeagueBuilder(this.platform, queue, this.puuid, this.leagueId);
    }
    
    public LeagueBuilder withPuuid(String puuid) {
      return new LeagueBuilder(this.platform, this.queue, puuid, this.leagueId);
    }
    
    public LeagueBuilder withLeagueId(String leagueId)
    {
        return new LeagueBuilder(this.platform, this.queue, this.puuid, leagueId);
    }
    
    public List<LeagueEntry> getLeagueEntries()
    {
        if (this.platform == LeagueShard.UNKNOWN || this.puuid == null)
        {
            logger.warn("GET called with invalid platform or summonerId");
            return Collections.emptyList();
        }
        
        DataCallBuilder builder;
        URLEndpoint endpoint;        
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        
        endpoint = URLEndpoint.V4_LEAGUE_ENTRY_BY_PUUID;
        builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, this.platform.name())
                                                     .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                     .withEndpoint(endpoint)
                                                     .withPlatform(this.platform);
        data.put("id", puuid);

        Optional<?> chl = DataCall.getCacheProvider().get(endpoint, data);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(endpoint, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public LeagueList getLeague()
    {
        
        if (this.platform == LeagueShard.UNKNOWN || this.leagueId == null)
        {
            logger.warn("GET called with invalid platform or leagueId");
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, this.platform.name())
                                                       .withURLParameter(Constants.LEAGUE_ID_PLACEHOLDER, this.leagueId)
                                                       .withEndpoint(URLEndpoint.V4_LEAGUE)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("id", leagueId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_LEAGUE, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_LEAGUE, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
