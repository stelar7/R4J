package no.stelar7.api.l4j8.impl.lol.builders.spectator;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.Pair;
import no.stelar7.api.l4j8.pojo.spectator.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class SpectatorBuilder
{
    private final Platform platform;
    private final String   summonerId;
    
    private SpectatorBuilder(Platform platform, String summonerId)
    {
        this.platform = platform;
        this.summonerId = summonerId;
    }
    
    public SpectatorBuilder()
    {
        this.platform = Platform.UNKNOWN;
        this.summonerId = null;
    }
    
    public SpectatorBuilder withPlatform(Platform platform)
    {
        return new SpectatorBuilder(platform, this.summonerId);
    }
    
    public SpectatorBuilder withSummonerId(String id)
    {
        return new SpectatorBuilder(this.platform, id);
    }
    
    public List<SpectatorGameInfo> getFeaturedGames()
    {
        if (this.platform == Platform.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_SPECTATOR_FEATURED, this.platform);
        if (chl.isPresent())
        {
            return ((FeaturedGames) chl.get()).getGameList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V4_SPECTATOR_FEATURED)
                                                       .withPlatform(this.platform);
        try
        {
            Object data = builder.build();
            if (data instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            FeaturedGames fg = (FeaturedGames) data;
            DataCall.getCacheProvider().store(URLEndpoint.V4_SPECTATOR_FEATURED, fg, this.platform);
            return fg.getGameList();
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public SpectatorGameInfo getCurrentGame()
    {
        if (this.platform == Platform.UNKNOWN || this.summonerId == null)
        {
            return null;
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V4_SPECTATOR_CURRENT, this.platform, this.summonerId);
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
            DataCall.getCacheProvider().store(URLEndpoint.V4_SPECTATOR_CURRENT, fg, this.platform, this.summonerId);
            return fg;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
