package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.champion.*;

import java.util.Optional;

public final class ChampionAPI
{
    private static final ChampionAPI INSTANCE = new ChampionAPI();
    
    static ChampionAPI getInstance()
    {
        return ChampionAPI.INSTANCE;
    }
    
    private ChampionAPI()
    {
        // Hide public constructor
    }
    
    public Optional<ChampionList> getChampions(Platform server, Optional<Boolean> freeToPlay)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_CHAMPIONS)
                                                       .withPlatform(server);
        freeToPlay.ifPresent(value -> builder.withURLData(Constants.FREE_TO_PLAY_PLACEHOLDER, String.valueOf(value)));
        
        return Optional.of((ChampionList) builder.build());
    }
    
    
    public Optional<Champion> getChampion(Platform server, int id)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_CHAMPIONS_BY_ID)
                                                       .withPlatform(server);
        
        return Optional.of((Champion) builder.build());
    }
    
}
