package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.language.LanguageStrings;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapData;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.*;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.*;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.*;

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
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.ITEMDATA_PLACEHOLDER_DATA, flag.getValue())));
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
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.ITEMDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((Item) builder.build());
    }
    
    public Optional<LanguageStrings> getLanguageStrings(Platform server, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_LANGUAGE_STRINGS)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((LanguageStrings) builder.build());
    }
    
    /**
     * Returns a list of possible locales
     */
    public Optional<List<String>> getLanguages(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_LANGUAGES)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        return Optional.of((List<String>) builder.build());
    }
    
    public Optional<MapData> getMaps(Platform server, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_MAPS)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((MapData) builder.build());
    }
    
    public Optional<MasteryList> getMasteries(Platform server, Optional<EnumSet<MasteryDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_MASTERIES)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.MASTERYDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((MasteryList) builder.build());
    }
    
    public Optional<Mastery> getMastery(Platform server, int id, Optional<EnumSet<MasteryDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_MASTERY_BY_ID)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.MASTERYDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((Mastery) builder.build());
    }
    
    public Optional<Realm> getRealm(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_REALMS)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        return Optional.of((Realm) builder.build());
    }
    
    public Optional<StaticRuneList> getRunes(Platform server, Optional<EnumSet<RuneDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_RUNES)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RUNEDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((StaticRuneList) builder.build());
    }
    
    public Optional<StaticRune> getRune(Platform server, int id, Optional<EnumSet<RuneDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_RUNE_BY_ID)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RUNEDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((StaticRune) builder.build());
    }
    
    
    public Optional<SummonerSpellList> getSummonerSpells(Platform server, Optional<EnumSet<SpellDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_SUMMONER_SPELLS)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RUNEDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((SummonerSpellList) builder.build());
    }
    
    public Optional<SummonerSpell> getSummonerSpell(Platform server, int id, Optional<EnumSet<SpellDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_SUMMONER_SPELL_BY_ID)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RUNEDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return Optional.of((SummonerSpell) builder.build());
    }
    
    
    public Optional<List<String>> getVersions(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_VERSIONS)
                                                       .withPlatform(server)
                                                       .asVerbose(true);
        
        return Optional.of((List<String>) builder.build());
    }
    
}
