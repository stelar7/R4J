package no.stelar7.api.r4j.impl.lol.builders.matchv5.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchIterator;
import org.slf4j.*;

import java.util.*;

public class MatchListBuilder
{
    private static final Logger logger = LoggerFactory.getLogger(MatchListBuilder.class);
    
    private final RegionShard        platform;
    private final String             puuid;
    private final MatchlistMatchType type;
    private final GameQueueType      queue;
    private final Integer            start;
    private final Integer            count;
    private final Long               startTime;
    private final Long               endTime;
    
    public MatchListBuilder()
    {
        this((RegionShard) null, null, null, null, null, null, null, null);
    }
    
    public MatchListBuilder(LeagueShard platform, String puuid, GameQueueType queue, MatchlistMatchType type, Integer start, Integer count, Long startTime, Long endTime)
    {
        this(platform.toRegionShard(), puuid, queue, type, start, count, startTime, endTime);
    }
    
    public MatchListBuilder(RegionShard platform, String puuid, GameQueueType queue, MatchlistMatchType type, Integer start, Integer count, Long startTime, Long endTime)
    {
        this.platform = platform;
        this.puuid = puuid;
        this.queue = queue;
        this.type = type;
        this.start = start;
        this.count = count;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public MatchListBuilder withPlatform(LeagueShard platform)
    {
        return new MatchListBuilder(platform, this.puuid, this.queue, this.type, this.start, this.count, this.startTime, this.endTime);
    }
    
    public MatchListBuilder withPuuid(String puuid)
    {
        return new MatchListBuilder(this.platform, puuid, this.queue, this.type, this.start, this.count, this.startTime, this.endTime);
    }
    
    public MatchListBuilder withQueue(GameQueueType queue)
    {
        return new MatchListBuilder(this.platform, this.puuid, queue, this.type, this.start, this.count, this.startTime, this.endTime);
    }
    
    public MatchListBuilder withType(MatchlistMatchType type)
    {
        return new MatchListBuilder(this.platform, this.puuid, this.queue, type, this.start, this.count, this.startTime, this.endTime);
    }
    
    public MatchListBuilder withBeginIndex(Integer start)
    {
        return new MatchListBuilder(this.platform, this.puuid, this.queue, this.type, start, this.count, this.startTime, this.endTime);
    }
    
    public MatchListBuilder withCount(Integer count)
    {
        return new MatchListBuilder(this.platform, this.puuid, this.queue, this.type, this.start, count, this.startTime, this.endTime);
    }
    
    public MatchListBuilder withStartTime(Long startTime)
    {
        return new MatchListBuilder(this.platform, this.puuid, this.queue, this.type, this.start, this.count, startTime, this.endTime);
    }
    
    public MatchListBuilder withEndTime(Long endTime)
    {
        return new MatchListBuilder(this.platform, this.puuid, this.queue, this.type, this.start, this.count, this.startTime, endTime);
    }
    
    public List<String> get()
    {
        if (this.puuid == null || this.platform == RegionShard.UNKNOWN)
        {
            logger.error("PUUID or Platform not present, returning empty list");
            return Collections.emptyList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                       .withEndpoint(URLEndpoint.V5_MATCHLIST)
                                                       .withPlatform(this.platform);
        
        if (this.queue != null)
        {
            builder.withQueryParameter(Constants.QUEUE_PLACEHOLDER_DATA, this.queue.getValues()[0].toString());
        }
        
        if (this.type != null)
        {
            builder.withQueryParameter(Constants.TYPE_PLACEHOLDER_DATA, this.type.getValue());
        }
        
        if (this.start != null)
        {
            builder.withQueryParameter(Constants.START_PLACEHOLDER_DATA, String.valueOf(this.start));
        }
        if (this.count != null)
        {
            builder.withQueryParameter(Constants.COUNT_PLACEHOLDER_DATA, String.valueOf(this.count));
        }
        if (this.startTime != null)
        {
            builder.withQueryParameter(Constants.STARTTIME_PLACEHOLDER_DATA, String.valueOf(this.startTime));
        }
        if (this.endTime != null)
        {
            builder.withQueryParameter(Constants.ENDTIME_PLACEHOLDER_DATA, String.valueOf(this.endTime));
        }
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("puuid", puuid);
        data.put("queue", queue);
        data.put("type", type);
        data.put("start", start);
        data.put("count", count);
        data.put("startTime", startTime);
        data.put("endTime", endTime);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V5_MATCHLIST, data);
        if (chl.isPresent())
        {
            return ((List<String>) chl.get());
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return Collections.emptyList();
        }
        
        List<String> match = (List<String>) matchObj;
        
        data.put("value", match);
        DataCall.getCacheProvider().store(URLEndpoint.V5_MATCHLIST, data);
        
        return match;
    }
    
    public LazyList<String> getLazy()
    {
        int increment = 100;
        
        return new LazyList<>(increment, prevValue -> {
            Integer          start = (this.start != null ? this.start : 0) + prevValue;
            MatchListBuilder local = new MatchListBuilder(this.platform, this.puuid, this.queue, this.type, start, increment, startTime, endTime);
            return local.get();
        });
        
    }
    
    public MatchIterator getMatchIterator()
    {
        return new MatchIterator(getLazy());
    }
    
}
