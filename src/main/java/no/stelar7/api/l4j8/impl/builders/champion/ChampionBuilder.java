package no.stelar7.api.l4j8.impl.builders.champion;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.champion.*;

import java.util.*;

public class ChampionBuilder
{
    private final Platform platform;
    private final int      id;
    
    public ChampionBuilder()
    {
        this.platform = Platform.UNKNOWN;
        this.id = -1;
    }
    
    private ChampionBuilder(Platform platform, int id)
    {
        this.platform = platform;
        this.id = id;
    }
    
    public ChampionBuilder withPlatform(Platform platform)
    {
        return new ChampionBuilder(platform, this.id);
    }
    
    public ChampionBuilder withId(int id)
    {
        return new ChampionBuilder(this.platform, id);
    }
    
    public List<Champion> getFreeToPlay()
    {
        if (this.platform == Platform.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPIONS)
                                                       .withURLData(Constants.FREE_TO_PLAY_PLACEHOLDER_DATA, String.valueOf(true))
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPIONS, this.platform, String.valueOf(true));
        if (chl.isPresent())
        {
            return (List<Champion>) chl.get();
        }
        
        ChampionList cl = (ChampionList) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPIONS, cl.getChampions(), this.platform, String.valueOf(true));
        return cl.getChampions();
    }
    
    public List<Champion> getAll()
    {
        if (this.platform == Platform.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPIONS)
                                                       .withURLData(Constants.FREE_TO_PLAY_PLACEHOLDER_DATA, String.valueOf(false))
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPIONS, this.platform, String.valueOf(false));
        if (chl.isPresent())
        {
            return (List<Champion>) chl.get();
        }
        
        ChampionList cl = (ChampionList) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPIONS, cl.getChampions(), this.platform, String.valueOf(false));
        return cl.getChampions();
    }
    
    
    public Champion get()
    {
        if (this.platform == Platform.UNKNOWN || this.id < 0)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(this.id))
                                                       .withEndpoint(URLEndpoint.V3_CHAMPIONS_BY_ID)
                                                       .withPlatform(this.platform);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPIONS_BY_ID, this.platform, this.id);
        if (chl.isPresent())
        {
            return (Champion) chl.get();
        }
        
        Champion ch = (Champion) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPIONS_BY_ID, ch, this, platform, this.id);
        return ch;
    }
}
