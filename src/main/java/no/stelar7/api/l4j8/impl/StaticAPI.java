package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;

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
                                                       .withEndpoint(URLEndpoint.V3_STATIC_CHAMPION_BY_ID)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        champData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.CHAMPDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((StaticChampion) builder.build());
    }
    
    public Optional<ItemList> getItems(Platform server, Optional<EnumSet<ItemDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_ITEMS)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.CHAMPDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((ItemList) builder.build());
    }
    
    public Optional<Item> getItem(Platform server, int id, Optional<EnumSet<ItemDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_ITEM_BY_ID)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.CHAMPDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((Item) builder.build());
    }
    
    /*
        TODO:
        lol/static-data/v3/language-strings Retrieve language strings data.
        lol/static-data/v3/languages Retrieve supported languages data.
        lol/static-data/v3/maps Retrieve map data.
        lol/static-data/v3/masteries Retrieves mastery list.
        lol/static-data/v3/masteries/{id} Retrieves mastery item by its unique id.
        lol/static-data/v3/realms Retrieve realm data.
        lol/static-data/v3/runes Retrieves rune list.
        lol/static-data/v3/runes/{id} Retrieves rune by its unique id.
        lol/static-data/v3/summoner-spells Retrieves summoner spell list.
        lol/static-data/v3/summoner-spells/{id} Retrieves summoner spell by its unique id.
        lol/static-data/v3/versions
     */
    
    
}
