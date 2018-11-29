package no.stelar7.api.l4j8.impl.builders.league;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.GameQueueType;
import no.stelar7.api.l4j8.pojo.league.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class LeagueBuilder
{
    
    private final Platform      platform;
    private final GameQueueType queue;
    private final String        summonerId;
    private final String        leagueId;
    
    public LeagueBuilder()
    {
        this.platform = Platform.UNKNOWN;
        this.queue = GameQueueType.RANKED_SOLO_5X5;
        this.summonerId = null;
        this.leagueId = null;
    }
    
    private LeagueBuilder(Platform platform, GameQueueType queue, String summonerId, String leagueId)
    {
        this.platform = platform;
        this.queue = queue;
        this.summonerId = summonerId;
        this.leagueId = leagueId;
    }
    
    public LeagueBuilder withPlatform(Platform platform)
    {
        return new LeagueBuilder(platform, this.queue, this.summonerId, this.leagueId);
    }
    
    public LeagueBuilder withQueue(GameQueueType queue)
    {
        return new LeagueBuilder(this.platform, queue, this.summonerId, this.leagueId);
    }
    
    public LeagueBuilder withSummonerId(String summonerId)
    {
        return new LeagueBuilder(this.platform, this.queue, summonerId, this.leagueId);
    }
    
    public LeagueBuilder withLeagueId(String leagueId)
    {
        return new LeagueBuilder(this.platform, this.queue, this.summonerId, leagueId);
    }
    
    
    public LeagueList getMasterLeague()
    {
        if (this.platform == Platform.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, this.platform.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, this.queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE_MASTER)
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_LEAGUE_MASTER, this.platform, this.queue);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_LEAGUE_MASTER, list, this.platform, this.queue);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    
    public LeagueList getChallengerLeague()
    {
        if (this.platform == Platform.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, this.platform.name())
                                                       .withURLParameter(Constants.QUEUE_PLACEHOLDER, this.queue.getApiName())
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE_CHALLENGER)
                                                       .withPlatform(this.platform);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_LEAGUE_CHALLENGER, this.platform, this.queue);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_LEAGUE_CHALLENGER, list, this.platform, this.queue);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
        
    }
    
    public List<LeaguePosition> getLeaguePosition()
    {
        if (this.platform == Platform.UNKNOWN || this.summonerId == null)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, this.platform.name())
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(this.summonerId))
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE_ENTRY)
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_LEAGUE_ENTRY, this.platform, this.summonerId);
        if (chl.isPresent())
        {
            return (List<LeaguePosition>) chl.get();
        }
        
        try
        {
            List<LeaguePosition> list = (List<LeaguePosition>) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_LEAGUE_ENTRY, list, this.platform, this.summonerId);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    public LeagueList getLeague()
    {
        
        if (this.platform == Platform.UNKNOWN || this.leagueId == null)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, this.platform.name())
                                                       .withURLParameter(Constants.LEAGUE_ID_PLACEHOLDER, String.valueOf(this.leagueId))
                                                       .withEndpoint(URLEndpoint.V3_LEAGUE)
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_LEAGUE, this.platform, this.leagueId);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_LEAGUE, list, this.platform, this.leagueId);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
}
