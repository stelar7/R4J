package no.stelar7.api.l4j8.impl.raw;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.flags.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.language.LanguageStrings;
import no.stelar7.api.l4j8.pojo.staticdata.map.*;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.*;
import no.stelar7.api.l4j8.pojo.staticdata.perk.*;
import no.stelar7.api.l4j8.pojo.staticdata.profileicon.*;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.*;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.*;

import javax.annotation.Nullable;
import java.util.*;

public final class DDragonAPI
{
    private static final DDragonAPI INSTANCE = new DDragonAPI();
    
    public static DDragonAPI getInstance()
    {
        return DDragonAPI.INSTANCE;
    }
    
    private DDragonAPI()
    {
        // Hide public constructor
    }
    
    public Map<Integer, StaticChampion> getChampions(@Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_CHAMPION_MANY);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getDD() : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_CHAMPION_MANY, version, locale);
        if (chl.isPresent())
        {
            return ((StaticChampionList) chl.get()).getData();
        }
        
        try
        {
            StaticChampionList list = (StaticChampionList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_CHAMPION_MANY, list, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticChampion> getChampions()
    {
        return getChampions(null, null);
    }
    
    public StaticChampion getChampion(Platform server, int id, @Nullable Set<ChampDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_CHAMPION_BY_ID)
                                                       .withPlatform(server);
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.CHAMPDATA_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.CHAMPDATA_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_CHAMPION_BY_ID, server, id, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return (StaticChampion) chl.get();
        }
        
        chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_CHAMPIONS, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            Map<Integer, StaticChampion> dataMap = ((StaticChampionList) chl.get()).getData();
            dataMap.forEach((k, v) -> DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_CHAMPION_BY_ID, v, server, k, dataFlags, version, locale));
            return dataMap.get(id);
        }
        
        try
        {
            StaticChampion list = (StaticChampion) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_CHAMPION_BY_ID, list, server, id, dataFlags, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public StaticChampion getChampion(int id)
    {
        return getChampion(Platform.EUW1, id, null, null, null);
    }
    
    public ItemList getItems(Platform server, @Nullable Set<ItemDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_ITEMS)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.ITEMLISTDATA_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.ITEMLISTDATA_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_ITEMS, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return (ItemList) chl.get();
        }
        
        try
        {
            ItemList list = (ItemList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_ITEMS, list, server, dataFlags, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public ItemList getItems()
    {
        return getItems(Platform.EUW1, null, null, null);
    }
    
    
    public Item getItem(Platform server, int id, @Nullable Set<ItemDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_ITEM_BY_ID)
                                                       .withPlatform(server);
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.ITEMDATA_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.ITEMDATA_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_ITEM_BY_ID, server, id, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return (Item) chl.get();
        }
        
        chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_ITEMS, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            Map<Integer, Item> dataMap = ((ItemList) chl.get()).getData();
            dataMap.forEach((k, v) -> DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_ITEM_BY_ID, v, server, k, dataFlags, version, locale));
            return dataMap.get(id);
        }
        
        try
        {
            Item list = (Item) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_ITEM_BY_ID, list, server, id, dataFlags, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Item getItem(int id)
    {
        return getItem(Platform.EUW1, id, null, null, null);
    }
    
    public Map<String, String> getLanguageStrings(Platform server, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_LANGUAGE_STRINGS)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_LANGUAGE_STRINGS, server, version, locale);
        if (chl.isPresent())
        {
            return ((LanguageStrings) chl.get()).getData();
        }
        
        try
        {
            LanguageStrings list = (LanguageStrings) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_LANGUAGE_STRINGS, list, server, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<String, String> getLanguageStrings()
    {
        return getLanguageStrings(Platform.EUW1, null, null);
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
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_LANGUAGES, server);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        try
        {
            List<String> list = (List<String>) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_LANGUAGES, list, server);
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public List<String> getLanguages()
    {
        return getLanguages(Platform.EUW1);
    }
    
    public Map<String, MapDetails> getMaps(Platform server, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_MAPS)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_MAPS, server, version, locale);
        if (chl.isPresent())
        {
            return ((MapData) chl.get()).getData();
        }
        
        try
        {
            MapData list = (MapData) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_MAPS, list, server, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<String, MapDetails> getMaps()
    {
        return getMaps(Platform.EUW1, null, null);
    }
    
    public Map<Integer, StaticMastery> getMasteries(Platform server, @Nullable Set<MasteryDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_MASTERIES)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.MASTERYLISTDATA_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.MASTERYLISTDATA_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_MASTERIES, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return ((StaticMasteryList) chl.get()).getData();
        }
        
        try
        {
            StaticMasteryList list = (StaticMasteryList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_MASTERIES, list, server, dataFlags, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticMastery> getMasteries()
    {
        return getMasteries(Platform.EUW1, null, null, null);
    }
    
    public Map<String, List<MasteryTreeList>> getMasteryTree(Platform server, @Nullable Set<MasteryDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_MASTERIES)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.MASTERYLISTDATA_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.MASTERYLISTDATA_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_MASTERIES, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return ((StaticMasteryList) chl.get()).getTree();
        }
        
        try
        {
            StaticMasteryList list = (StaticMasteryList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_MASTERIES, list, server, dataFlags, version, locale);
            return list.getTree();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<String, List<MasteryTreeList>> getMasteryTree()
    {
        return getMasteryTree(Platform.EUW1, null, null, null);
    }
    
    public StaticMastery getMastery(Platform server, int id, @Nullable Set<MasteryDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_MASTERY_BY_ID)
                                                       .withPlatform(server);
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.MASTERYDATA_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.MASTERYDATA_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_MASTERY_BY_ID, server, id, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return (StaticMastery) chl.get();
        }
        
        chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_MASTERIES, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            Map<Integer, StaticMastery> dataMap = ((StaticMasteryList) chl.get()).getData();
            dataMap.forEach((k, v) -> DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_MASTERY_BY_ID, v, server, k, dataFlags, version, locale));
            return dataMap.get(id);
        }
        
        try
        {
            StaticMastery list = (StaticMastery) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_MASTERY_BY_ID, list, server, id, dataFlags, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public StaticMastery getMastery(int id)
    {
        return getMastery(Platform.EUW1, id, null, null, null);
    }
    
    public Map<Long, ProfileIconDetails> getProfileIcons(Platform server, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_PROFILEICONS)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_PROFILEICONS, server, version, locale);
        if (chl.isPresent())
        {
            return ((ProfileIconData) chl.get()).getData();
        }
        
        try
        {
            ProfileIconData list = (ProfileIconData) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_PROFILEICONS, list, server, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Long, ProfileIconDetails> getProfileIcons()
    {
        return getProfileIcons(Platform.EUW1, null, null);
    }
    
    public Realm getRealm(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_REALMS)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_REALMS, server);
        if (chl.isPresent())
        {
            return (Realm) chl.get();
        }
        
        try
        {
            Realm list = (Realm) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_REALMS, list, server);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    public Realm getRealm()
    {
        return getRealm(Platform.EUW1);
    }
    
    public Map<Integer, StaticRune> getRunes(Platform server, @Nullable Set<RuneDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_RUNES)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.RUNELISTDATA_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.RUNELISTDATA_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_RUNES, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return ((StaticRuneList) chl.get()).getData();
        }
        
        try
        {
            StaticRuneList list = (StaticRuneList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_RUNES, list, server, dataFlags, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticRune> getRunes()
    {
        return getRunes(Platform.EUW1, null, null, null);
    }
    
    
    public StaticRune getRune(Platform server, int id, @Nullable Set<RuneDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_RUNE_BY_ID)
                                                       .withPlatform(server);
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.RUNEDATA_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.RUNEDATA_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_RUNE_BY_ID, server, id, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return (StaticRune) chl.get();
        }
        
        chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_RUNES, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            Map<Integer, StaticRune> dataMap = ((StaticRuneList) chl.get()).getData();
            dataMap.forEach((k, v) -> DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_RUNE_BY_ID, v, server, k, dataFlags, version, locale));
            return dataMap.get(id);
        }
        
        try
        {
            StaticRune list = (StaticRune) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_RUNE_BY_ID, list, server, id, dataFlags, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public StaticRune getRune(int id)
    {
        return getRune(Platform.EUW1, id, null, null, null);
    }
    
    
    public Map<Integer, StaticSummonerSpell> getSummonerSpells(Platform server, @Nullable Set<SpellDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_SUMMONER_SPELLS)
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withPlatform(server);
        
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.SUMMONERSPELLLIST_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.SUMMONERSPELLLIST_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_SUMMONER_SPELLS, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return ((StaticSummonerSpellList) chl.get()).getData();
        }
        
        try
        {
            StaticSummonerSpellList list = (StaticSummonerSpellList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_SUMMONER_SPELLS, list, server, dataFlags, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticSummonerSpell> getSummonerSpells()
    {
        return getSummonerSpells(Platform.EUW1, null, null, null);
    }
    
    public StaticSummonerSpell getSummonerSpell(Platform server, int id, @Nullable Set<SpellDataFlags> dataFlags, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withURLData(Constants.URL_PARAM_DATA_BY_ID, String.valueOf(true))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_SUMMONER_SPELL_BY_ID)
                                                       .withPlatform(server);
        
        
        if (dataFlags != null)
        {
            dataFlags.forEach(flag -> builder.withURLDataAsSet(Constants.SUMMONERSPELL_PLACEHOLDER_DATA, flag.getValue()));
        } else
        {
            builder.withURLDataAsSet(Constants.SUMMONERSPELL_PLACEHOLDER_DATA, ChampDataFlags.ALL.getValue());
        }
        
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_SUMMONER_SPELL_BY_ID, server, id, dataFlags, version, locale);
        if (chl.isPresent())
        {
            return (StaticSummonerSpell) chl.get();
        }
        
        chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_SUMMONER_SPELLS, server, dataFlags, version, locale);
        if (chl.isPresent())
        {
            Map<Integer, StaticSummonerSpell> dataMap = ((StaticSummonerSpellList) chl.get()).getData();
            dataMap.forEach((k, v) -> DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_SUMMONER_SPELL_BY_ID, v, server, k, dataFlags, version, locale));
            return dataMap.get(id);
        }
        
        try
        {
            StaticSummonerSpell list = (StaticSummonerSpell) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_SUMMONER_SPELL_BY_ID, list, server, id, dataFlags, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public StaticSummonerSpell getSummonerSpell(int id)
    {
        return getSummonerSpell(Platform.EUW1, id, null, null, null);
    }
    
    public List<String> getVersions(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_VERSIONS)
                                                       .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_VERSIONS, server);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        
        try
        {
            List<String> list = (List<String>) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_VERSIONS, list, server);
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public List<String> getVersions()
    {
        return getVersions(Platform.EUW1);
    }
    
    public List<StaticPerk> getPerks(Platform server, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_PERKS)
                                                       .withPlatform(server);
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_PERKS, server, version, locale);
        if (chl.isPresent())
        {
            return (List<StaticPerk>) chl.get();
        }
        
        try
        {
            List<StaticPerk> list = (List<StaticPerk>) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_PERKS, list, server, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public List<StaticPerk> getPerks()
    {
        return getPerks(Platform.EUW1, null, null);
    }
    
    public StaticPerk getPerk(Platform server, int id, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_PERK_BY_ID)
                                                       .withPlatform(server);
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_PERK_BY_ID, server, id, version, locale);
        if (chl.isPresent())
        {
            return (StaticPerk) chl.get();
        }
        
        try
        {
            StaticPerk list = (StaticPerk) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_PERK_BY_ID, list, server, id, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public StaticPerk getPerk(int id)
    {
        return getPerk(Platform.EUW1, id, null, null);
    }
    
    public List<PerkPath> getPerkPaths(Platform server, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_PERKPATHS)
                                                       .withPlatform(server);
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_PERKPATHS, server, version, locale);
        if (chl.isPresent())
        {
            return (List<PerkPath>) chl.get();
        }
        
        try
        {
            List<PerkPath> list = (List<PerkPath>) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_PERKPATHS, list, server, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public List<PerkPath> getPerkPaths()
    {
        return getPerkPaths(Platform.EUW1, null, null);
    }
    
    public PerkPath getPerkPath(Platform server, int id, @Nullable String version, @Nullable String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ID_PLACEHOLDER, String.valueOf(id))
                                                       .withEndpoint(URLEndpoint.V3_STATIC_PERKPATH_BY_ID)
                                                       .withPlatform(server);
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        if (locale != null)
        {
            builder.withURLData(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_PERKPATH_BY_ID, server, id, version, locale);
        if (chl.isPresent())
        {
            return (PerkPath) chl.get();
        }
        
        try
        {
            PerkPath list = (PerkPath) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_PERKPATH_BY_ID, list, server, id, version, locale);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public PerkPath getPerkPath(int id)
    {
        return getPerkPath(Platform.EUW1, id, null, null);
    }
    
    public String getTarball(Platform server, @Nullable String version)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_STATIC_TARBALL)
                                                       .withPlatform(server);
        
        if (version != null)
        {
            builder.withURLData(Constants.VERSION_PLACEHOLDER_DATA, version);
        }
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_STATIC_TARBALL, server, version);
        if (chl.isPresent())
        {
            return (String) chl.get();
        }
        
        try
        {
            String list = (String) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_STATIC_TARBALL, list, server, version);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public String getTarball()
    {
        return getTarball(Platform.EUW1, null);
    }
    
}
