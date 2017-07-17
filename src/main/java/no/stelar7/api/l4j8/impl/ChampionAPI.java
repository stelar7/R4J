package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.champion.*;

import java.util.*;

@SuppressWarnings("unchecked")
public final class ChampionAPI
{
    private static final ChampionAPI INSTANCE = new ChampionAPI();
    
    public static ChampionAPI getInstance()
    {
        return ChampionAPI.INSTANCE;
    }
    
    private ChampionAPI()
    {
        // Hide public constructor
    }
    
    public List<Champion> getChampions(Platform server, boolean freeToPlay)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPIONS)
                                                       .withPlatform(server);
        if (freeToPlay)
        {
            builder.withURLData(Constants.FREE_TO_PLAY_PLACEHOLDER_DATA, "true");
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPIONS, server);
        if (chl.isPresent())
        {
            return (List<Champion>) chl.get();
        }
        
        
        ChampionList cl = (ChampionList) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPIONS, cl.getChampions());
        return cl.getChampions();
    }
    
    
    public Champion getChampion(Platform server, int id)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_CHAMPIONS_BY_ID)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_CHAMPIONS_BY_ID, server, id);
        if (chl.isPresent())
        {
            return (Champion) chl.get();
        }
        
        Champion ch = (Champion) builder.build();
        DataCall.getCacheProvider().store(URLEndpoint.V3_CHAMPIONS_BY_ID, ch);
        return ch;
    }
    
}
