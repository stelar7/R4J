package no.stelar7.api.l4j8.impl.raw;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
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

import java.util.*;
import java.util.Map.Entry;

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
    
    public Map<Integer, StaticChampion> getChampions(String version, String locale)
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
    
    public List<StaticChampion> getChampionsFromId(List<Integer> freeChampionIds)
    {
        Map<Integer, StaticChampion> all    = getChampions();
        List<StaticChampion>         champs = new ArrayList<>();
        
        for (Integer id : freeChampionIds)
        {
            StaticChampion champ = all.get(id);
            if (champ != null)
            {
                champs.add(champ);
            }
        }
        
        return champs;
    }
    
    public List<StaticChampion> getChampionsFromId(List<Integer> freeChampionIds, String version, String locale)
    {
        Map<Integer, StaticChampion> all    = getChampions(version, locale);
        List<StaticChampion>         champs = new ArrayList<>();
        
        for (Integer id : freeChampionIds)
        {
            StaticChampion champ = all.get(id);
            if (champ != null)
            {
                champs.add(champ);
            }
        }
        
        return champs;
    }
    
    public Map<Integer, StaticChampion> getChampions()
    {
        return getChampions(null, null);
    }
    
    public StaticChampion getChampion(int id, String version, String locale)
    {
        return getChampions(version, locale).get(id);
    }
    
    public StaticChampion getChampion(int id)
    {
        return getChampion(id, null, null);
    }
    
    public Map<Integer, Item> getItems(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_ITEMS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getDD() : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_ITEMS, version, locale);
        if (chl.isPresent())
        {
            return ((ItemList) chl.get()).getData();
        }
        
        try
        {
            ItemList list = (ItemList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_ITEMS, list, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, Item> getItems()
    {
        return getItems(null, null);
    }
    
    
    public Item getItem(int id, String version, String locale)
    {
        return getItems(version, locale).get(id);
    }
    
    public Item getItem(int id)
    {
        return getItem(id, null, null);
    }
    
    public Map<String, String> getLanguageStrings(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_LANGUAGE_STRINGS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getDD() : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_LANGUAGE_STRINGS, version, locale);
        if (chl.isPresent())
        {
            return ((LanguageStrings) chl.get()).getData();
        }
        
        try
        {
            LanguageStrings list = (LanguageStrings) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_LANGUAGE_STRINGS, list, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<String, String> getLanguageStrings()
    {
        return getLanguageStrings(null, null);
    }
    
    /**
     * Returns a list of possible locales
     *
     * @return a list of strings avaliable in this language
     */
    @SuppressWarnings("unchecked")
    public List<String> getLanguages()
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_LANGUAGES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, "");
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, "");
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_LANGUAGES);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        try
        {
            List<String> list = (List<String>) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_LANGUAGES, list);
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public Map<String, MapDetails> getMaps(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_MAPS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getDD() : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_MAPS, version, locale);
        if (chl.isPresent())
        {
            return ((MapData) chl.get()).getData();
        }
        
        try
        {
            MapData list = (MapData) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_MAPS, list, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<String, MapDetails> getMaps()
    {
        return getMaps(null, null);
    }
    
    public Map<Integer, StaticMastery> getMasteries(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? "7.23.1" : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_MASTERIES, version, locale);
        if (chl.isPresent())
        {
            return ((StaticMasteryList) chl.get()).getData();
        }
        
        try
        {
            StaticMasteryList list = (StaticMasteryList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_MASTERIES, list, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticMastery> getMasteries()
    {
        return getMasteries(null, null);
    }
    
    public Map<String, List<List<MasteryTreeItem>>> getMasteryTree(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? "7.23.1" : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_MASTERIES, version, locale);
        if (chl.isPresent())
        {
            return ((StaticMasteryList) chl.get()).getTree();
        }
        
        try
        {
            StaticMasteryList list = (StaticMasteryList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_MASTERIES, list, version, locale);
            return list.getTree();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<String, List<List<MasteryTreeItem>>> getMasteryTree()
    {
        return getMasteryTree(null, null);
    }
    
    public StaticMastery getMastery(int id, String version, String locale)
    {
        return getMasteries(version, locale).get(id);
    }
    
    public StaticMastery getMastery(int id)
    {
        return getMastery(id, null, null);
    }
    
    public Map<Long, ProfileIconDetails> getProfileIcons(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_PROFILEICONS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getDD() : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_PROFILEICONS, version, locale);
        if (chl.isPresent())
        {
            return ((ProfileIconData) chl.get()).getData();
        }
        
        try
        {
            ProfileIconData list = (ProfileIconData) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_PROFILEICONS, list, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Long, ProfileIconDetails> getProfileIcons()
    {
        return getProfileIcons(null, null);
    }
    
    /**
     * Always returns euw
     */
    public Realm getRealm()
    {
        return getRealm(Platform.EUW1);
    }
    
    public Realm getRealm(Platform region)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withURLParameter(Constants.REGION_PLACEHOLDER, region.getRealmValue())
                                                       .withEndpoint(URLEndpoint.DDRAGON_REALMS);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_REALMS);
        if (chl.isPresent())
        {
            return (Realm) chl.get();
        }
        
        try
        {
            Realm list = (Realm) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_REALMS, list);
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticRune> getRunes(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_RUNES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? "7.23.1" : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_RUNES, version, locale);
        if (chl.isPresent())
        {
            return ((StaticRuneList) chl.get()).getData();
        }
        
        try
        {
            StaticRuneList list = (StaticRuneList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_RUNES, list, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticRune> getRunes()
    {
        return getRunes(null, null);
    }
    
    
    public StaticRune getRune(int id, String version, String locale)
    {
        return getRunes(version, locale).get(id);
    }
    
    public StaticRune getRune(int id)
    {
        return getRune(id, null, null);
    }
    
    
    public Map<Integer, StaticSummonerSpell> getSummonerSpells(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_SUMMONER_SPELLS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getDD() : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_SUMMONER_SPELLS, version, locale);
        if (chl.isPresent())
        {
            return ((StaticSummonerSpellList) chl.get()).getData();
        }
        
        try
        {
            StaticSummonerSpellList list = (StaticSummonerSpellList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_SUMMONER_SPELLS, list, version, locale);
            return list.getData();
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticSummonerSpell> getSummonerSpells()
    {
        return getSummonerSpells(null, null);
    }
    
    public StaticSummonerSpell getSummonerSpell(int id, String version, String locale)
    {
        return getSummonerSpells(version, locale).get(id);
    }
    
    public StaticSummonerSpell getSummonerSpell(int id)
    {
        return getSummonerSpell(id, null, null);
    }
    
    @SuppressWarnings("unchecked")
    public List<String> getVersions()
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_VERSIONS);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_VERSIONS);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        
        try
        {
            List<String> list = (List<String>) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_VERSIONS, list);
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    @SuppressWarnings("unchecked")
    public Map<Integer, StaticPerk> getPerks(String version, String locale)
    {
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_PERKS, version, locale);
        if (chl.isPresent())
        {
            return (Map<Integer, StaticPerk>) chl.get();
        }
        
        Map<Integer, StaticPerk> perks = new HashMap<>();
        for (Entry<Integer, PerkPath> path : getPerkPaths(version, locale).entrySet())
        {
            for (PerkSlot slot : path.getValue().getSlots())
            {
                for (StaticPerk perk : slot.getRunes())
                {
                    perks.put(perk.getId(), perk);
                }
            }
        }
        
        DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_PERKS, perks, version, locale);
        return perks;
    }
    
    public Map<Integer, StaticPerk> getPerks()
    {
        return getPerks(null, null);
    }
    
    public StaticPerk getPerk(int id, String version, String locale)
    {
        return getPerks(version, locale).get(id);
    }
    
    public StaticPerk getPerk(int id)
    {
        return getPerk(id, null, null);
    }
    
    @SuppressWarnings("unchecked")
    public Map<Integer, PerkPath> getPerkPaths(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withProxy(Constants.DDRAGON_PROXY)
                                                       .withEndpoint(URLEndpoint.DDRAGON_PERKPATHS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getDD() : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_PERKPATHS, version, locale);
        if (chl.isPresent())
        {
            return (Map<Integer, PerkPath>) chl.get();
        }
        
        try
        {
            List<PerkPath>         list  = (List<PerkPath>) builder.build();
            Map<Integer, PerkPath> paths = new HashMap<>();
            for (PerkPath path : list)
            {
                paths.put(path.getId(), path);
            }
            
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_PERKPATHS, paths, version, locale);
            return paths;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, PerkPath> getPerkPaths()
    {
        return getPerkPaths(null, null);
    }
    
    public PerkPath getPerkPath(int id, String version, String locale)
    {
        return getPerkPaths(version, locale).get(id);
    }
    
    public PerkPath getPerkPath(int id)
    {
        return getPerkPath(id, null, null);
    }
    
    public String getTarball(String version)
    {
        return "https://ddragon.leagueoflegends.com/cdn/dragontail-{version}.tgz".replace(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getDD() : version);
    }
    
    public String getTarball()
    {
        return getTarball(null);
    }
    
}
