package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.ChampDataFlags;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;

import java.util.*;

public final class StaticAPI
{
    private static final StaticAPI INSTANCE = new StaticAPI();
    
    static StaticAPI getInstance()
    {
        return StaticAPI.INSTANCE;
    }
    
    private StaticAPI()
    {
        // Hide public constructor
    }
    
    
    public Optional<StaticChampionList> getChampions(Platform server, Optional<EnumSet<ChampDataFlags>> champData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(false))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_CHAMPIONS)
                                                       .withPlatform(server);
        
        champData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.CHAMPDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((StaticChampionList) builder.build());
    }
    
    public Optional<StaticChampion> getChampion(Platform server, int id, Optional<EnumSet<ChampDataFlags>> champData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_CHAMPIONS_BY_ID)
                                                       .withPlatform(server)
                .asVerbose(true);
        
        champData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.CHAMPDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((StaticChampion) builder.build());
    }
    
}
