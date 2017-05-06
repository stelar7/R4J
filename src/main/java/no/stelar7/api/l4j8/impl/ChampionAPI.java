package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.champion.*;

import java.util.*;

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
    
    public List<Champion> getChampions(Platform server, Boolean freeToPlay)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPIONS)
                                                       .withPlatform(server);
        if (freeToPlay != null)
        {
            builder.withURLData(Constants.FREE_TO_PLAY_PLACEHOLDER_DATA, freeToPlay.toString());
        }
        
        ChampionList cl = (ChampionList) builder.build();
        return cl.getChampions();
    }
    
    
    public Champion getChampion(Platform server, int id)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_CHAMPIONS_BY_ID)
                                                       .withPlatform(server);
        
        return (Champion) builder.build();
    }
    
}
