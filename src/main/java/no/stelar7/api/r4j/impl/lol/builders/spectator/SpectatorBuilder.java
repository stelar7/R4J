package no.stelar7.api.r4j.impl.lol.builders.spectator;


import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.spectator.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class SpectatorBuilder
{
    private final LeagueShard platform;
    private final String      summonerId;
    
    private SpectatorBuilder(LeagueShard platform, String summonerId)
    {
        this.platform = platform;
        this.summonerId = summonerId;
    }
    
    public SpectatorBuilder()
    {
        this.platform = LeagueShard.UNKNOWN;
        this.summonerId = null;
    }
    
    public SpectatorBuilder withPlatform(LeagueShard platform)
    {
        return new SpectatorBuilder(platform, this.summonerId);
    }
    
    public SpectatorBuilder withSummonerId(String id)
    {
        return new SpectatorBuilder(this.platform, id);
    }
    
    public List<SpectatorGameInfo> getFeaturedGames()
    {
        if (this.platform == LeagueShard.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", platform);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_SPECTATOR_FEATURED, data);
        if (chl.isPresent())
        {
            return ((FeaturedGames) chl.get()).getGameList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V4_SPECTATOR_FEATURED)
                                                       .withPlatform(this.platform);
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            FeaturedGames fg = (FeaturedGames) ret;
            
            data.put("value", fg);
            DataCall.getCacheProvider().store(URLEndpoint.V4_SPECTATOR_FEATURED, data);
            
            return fg.getGameList();
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public SpectatorGameInfo getCurrentGame()
    {
        if (this.platform == LeagueShard.UNKNOWN || this.summonerId == null)
        {
            return null;
        }
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", platform);
        data.put("id", this.summonerId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_SPECTATOR_CURRENT, data);
        if (chl.isPresent())
        {
            return (SpectatorGameInfo) chl.get();
        }
        
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.summonerId)
                                                       .withEndpoint(URLEndpoint.V4_SPECTATOR_CURRENT)
                                                       .withPlatform(this.platform);
        
        try
        {
            SpectatorGameInfo fg = (SpectatorGameInfo) builder.build();
            
            data.put("value", fg);
            DataCall.getCacheProvider().store(URLEndpoint.V4_SPECTATOR_CURRENT, data);
            
            return fg;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
