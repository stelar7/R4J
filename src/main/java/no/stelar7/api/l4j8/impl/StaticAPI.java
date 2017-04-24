package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.flags.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.language.LanguageStrings;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapData;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.*;
import no.stelar7.api.l4j8.pojo.staticdata.profileicon.ProfileIconData;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.*;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.*;

import java.util.*;

public final class StaticAPI
{
    private static final StaticAPI INSTANCE = new StaticAPI();
    
    public static StaticAPI getInstance()
    {
        return StaticAPI.INSTANCE;
    }
    
    private StaticAPI()
    {
        // Hide public constructor
    }
    
    
    public StaticChampionList getChampions(Platform server, Optional<EnumSet<ChampDataFlags>> champData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(false))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_CHAMPIONS)
                                                       .withPlatform(server);
        
        champData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.CHAMPDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (StaticChampionList) builder.build();
    }
    
    public StaticChampion getChampion(Platform server, int id, Optional<EnumSet<ChampDataFlags>> champData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_CHAMPION_BY_ID)
                                                       .withPlatform(server);
        
        champData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.CHAMPDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (StaticChampion) builder.build();
    }
    
    public ItemList getItems(Platform server, Optional<EnumSet<ItemDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_ITEMS)
                                                       .withPlatform(server);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.ITEMLISTDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (ItemList) builder.build();
    }
    
    public Item getItem(Platform server, int id, Optional<EnumSet<ItemDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_ITEM_BY_ID)
                                                       .withPlatform(server);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.ITEMDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (Item) builder.build();
    }
    
    public LanguageStrings getLanguageStrings(Platform server, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_LANGUAGE_STRINGS)
                                                       .withPlatform(server);
        
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (LanguageStrings) builder.build();
    }
    
    /**
     * Returns a list of possible locales
     *
     * @param server the server to get from
     * @return a list of strings avaliable in this language
     */
    public List<String> getLanguages(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_LANGUAGES)
                                                       .withPlatform(server);
        
        return (List<String>) builder.build();
    }
    
    public MapData getMaps(Platform server, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_MAPS)
                                                       .withPlatform(server);
        
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (MapData) builder.build();
    }
    
    public MasteryList getMasteries(Platform server, Optional<EnumSet<MasteryDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_MASTERIES)
                                                       .withPlatform(server);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.MASTERYLISTDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (MasteryList) builder.build();
    }
    
    public Mastery getMastery(Platform server, int id, Optional<EnumSet<MasteryDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_MASTERY_BY_ID)
                                                       .withPlatform(server);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.MASTERYDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (Mastery) builder.build();
    }
    
    public ProfileIconData getProfileIcons(Platform server, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_PROFILEICONS)
                                                       .withPlatform(server);
        
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (ProfileIconData) builder.build();
    }
    
    public Realm getRealm(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_REALMS)
                                                       .withPlatform(server);
        return (Realm) builder.build();
    }
    
    public StaticRuneList getRunes(Platform server, Optional<EnumSet<RuneDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_RUNES)
                                                       .withPlatform(server);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RUNELISTDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (StaticRuneList) builder.build();
    }
    
    public StaticRune getRune(Platform server, int id, Optional<EnumSet<RuneDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_RUNE_BY_ID)
                                                       .withPlatform(server);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RUNEDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (StaticRune) builder.build();
    }
    
    
    public StaticSummonerSpellList getSummonerSpells(Platform server, Optional<EnumSet<SpellDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_SUMMONER_SPELLS)
                                                       .withPlatform(server);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RUNEDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (StaticSummonerSpellList) builder.build();
    }
    
    public StaticSummonerSpell getSummonerSpell(Platform server, int id, Optional<EnumSet<SpellDataFlags>> itemData, Optional<String> version, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_SUMMONER_SPELL_BY_ID)
                                                       .withPlatform(server);
        
        itemData.ifPresent(value -> value.forEach(flag -> builder.withURLData(Constants.RUNEDATA_PLACEHOLDER_DATA, flag.getValue())));
        version.ifPresent(value -> builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, value));
        locale.ifPresent(value -> builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, value));
        
        return (StaticSummonerSpell) builder.build();
    }
    
    
    public List<String> getVersions(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_VERSIONS)
                                                       .withPlatform(server);
        
        return (List<String>) builder.build();
    }
    
}
