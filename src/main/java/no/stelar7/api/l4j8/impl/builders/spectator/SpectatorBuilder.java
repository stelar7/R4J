package no.stelar7.api.l4j8.impl.builders.spectator;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.spectator.*;

import java.util.*;

public class SpectatorBuilder
{
    private final Platform platform;
    private final Long     summonerId;
    
    private SpectatorBuilder(Platform platform, Long summonerId)
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
    
    public SpectatorBuilder withSummonerId(Long id)
    {
        return new SpectatorBuilder(this.platform, id);
    }
    
    public List<SpectatorGameInfo> getFeaturedGames()
    {
        if (this.platform == Platform.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_SPECTATOR_FEATURED)
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_SPECTATOR_FEATURED, this.platform);
        if (chl.isPresent())
        {
            return (List<SpectatorGameInfo>) chl.get();
        }
        
        FeaturedGames fg = (FeaturedGames) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_SPECTATOR_FEATURED, fg.getGameList(), this.platform);
        return fg.getGameList();
        
    }
    
    public SpectatorGameInfo getCurrentGame()
    {
        if (this.platform == Platform.UNKNOWN || this.summonerId == null)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(this.summonerId))
                                                       .withEndpoint(URLEndpoint.V3_SPECTATOR_CURRENT)
                                                       .withPlatform(this.platform);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_SPECTATOR_CURRENT, this.platform, this.summonerId);
        if (chl.isPresent())
        {
            return (SpectatorGameInfo) chl.get();
        }
        
        SpectatorGameInfo fg = (SpectatorGameInfo) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_SPECTATOR_CURRENT, fg, this.platform, this.summonerId);
        return fg;
    }
}
