package no.stelar7.api.r4j.impl.lol.builders.league;

import org.jetbrains.annotations.Nullable;
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
    private R4JContext context = R4JContext.getDefault();

    /**
     * Uses the credentials, cache and limiters of the context instead of the default ones
     *
     * @param context the context to use
     * @return this
     */
    public LeagueBuilder withContext(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
        return this;
    }

    
    private static final Logger logger = LoggerFactory.getLogger(LeagueBuilder.class);
    
    private final LeagueShard   platform;
    private final GameQueueType queue;
    private final String        puuid;
    
    public LeagueBuilder()
    {
        this.platform = LeagueShard.UNKNOWN;
        this.queue = GameQueueType.RANKED_SOLO_5X5;
        this.puuid = null;
    }
    
    private LeagueBuilder(LeagueShard platform, GameQueueType queue, String puuid)
    {
        this.platform = platform;
        this.queue = queue;
        this.puuid = puuid;
    }
    
    public LeagueBuilder withPlatform(LeagueShard platform)
    {
        return new LeagueBuilder(platform, this.queue, this.puuid).withContext(this.context);
    }
    
    public LeagueBuilder withQueue(GameQueueType queue)
    {
        return new LeagueBuilder(this.platform, queue, this.puuid).withContext(this.context);
    }
    
    public LeagueBuilder withPuuid(String puuid) {
      return new LeagueBuilder(this.platform, this.queue, puuid).withContext(this.context);
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
        builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, this.platform.name())
                                                     .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                     .withEndpoint(endpoint)
                                                     .withPlatform(this.platform);
        data.put("id", puuid);

        List<LeagueEntry> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }
    
}
