package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.staticdata.realm.Realm;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.item.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.language.LanguageStrings;
import no.stelar7.api.r4j.pojo.lol.staticdata.map.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.mastery.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.perk.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.profileicon.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.rune.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.summonerspell.*;

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
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_CHAMPION_MANY);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_CHAMPION_MANY, data);
        if (chl.isPresent())
        {
            return ((StaticChampionList) chl.get()).getData();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            StaticChampionList list = (StaticChampionList) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_CHAMPION_MANY, data);
            
            return list.getData();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_ITEMS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_ITEMS, data);
        if (chl.isPresent())
        {
            return ((ItemList) chl.get()).getData();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            ItemList list = (ItemList) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_ITEMS, data);
            
            return list.getData();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_LANGUAGE_STRINGS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_LANGUAGE_STRINGS, data);
        if (chl.isPresent())
        {
            return ((LanguageStrings) chl.get()).getData();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            LanguageStrings list = (LanguageStrings) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_LANGUAGE_STRINGS, data);
            
            return list.getData();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
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
    public List<String> getLanguages()
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_LANGUAGES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, "");
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, "");
        
        Map<String, Object> data = new TreeMap<>();
        Optional<?>         chl  = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_LANGUAGES, data);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<String> list = (List<String>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_LANGUAGES, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public Map<String, MapDetails> getMaps(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_MAPS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_MAPS, data);
        if (chl.isPresent())
        {
            return ((MapData) chl.get()).getData();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            MapData list = (MapData) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_MAPS, data);
            
            return list.getData();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
        }
    }
    
    public Map<String, MapDetails> getMaps()
    {
        return getMaps(null, null);
    }
    
    public Map<Integer, StaticMastery> getMasteries(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getN().get("mastery") : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_MASTERIES, data);
        if (chl.isPresent())
        {
            return ((StaticMasteryList) chl.get()).getData();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            StaticMasteryList list = (StaticMasteryList) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_MASTERIES, data);
            
            return list.getData();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
        }
    }
    
    public Map<Integer, StaticMastery> getMasteries()
    {
        return getMasteries(null, null);
    }
    
    public Map<String, List<List<MasteryTreeItem>>> getMasteryTree(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getN().get("mastery") : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_MASTERIES, data);
        if (chl.isPresent())
        {
            return ((StaticMasteryList) chl.get()).getTree();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            StaticMasteryList list = (StaticMasteryList) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_MASTERIES, data);
            
            return list.getTree();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_PROFILEICONS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_PROFILEICONS, data);
        if (chl.isPresent())
        {
            return ((ProfileIconData) chl.get()).getData();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            ProfileIconData list = (ProfileIconData) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_PROFILEICONS, data);
            
            return list.getData();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
        }
    }
    
    public Map<Long, ProfileIconDetails> getProfileIcons()
    {
        return getProfileIcons(null, null);
    }
    
    /**
     * Always returns euw
     * @return realm euw
     */
    public Realm getRealm()
    {
        return getRealm(LeagueShard.EUW1);
    }
    
    public Realm getRealm(LeagueShard region)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withURLParameter(Constants.REGION_PLACEHOLDER, region.getRealmValue())
                .withEndpoint(URLEndpoint.DDRAGON_REALMS);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", region);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_REALMS, data);
        if (chl.isPresent())
        {
            return (Realm) chl.get();
        }
        
        try
        {
            Realm list = (Realm) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_REALMS, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public Map<Integer, StaticRune> getRunes(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_RUNES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getN().get("rune") : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_RUNES, data);
        if (chl.isPresent())
        {
            return ((StaticRuneList) chl.get()).getData();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            StaticRuneList list = (StaticRuneList) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_RUNES, data);
            
            return list.getData();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_SUMMONER_SPELLS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_SUMMONER_SPELLS, data);
        if (chl.isPresent())
        {
            return ((StaticSummonerSpellList) chl.get()).getData();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            StaticSummonerSpellList list = (StaticSummonerSpellList) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_SUMMONER_SPELLS, data);
            
            return list.getData();
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
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
    
    public List<String> getVersions()
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_VERSIONS);
        
        
        Map<String, Object> data = new TreeMap<>();
        Optional<?>         chl  = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_VERSIONS, data);
        if (chl.isPresent())
        {
            return (List<String>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<String> list = (List<String>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_VERSIONS, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public Map<Integer, StaticPerk> getPerks(String version, String locale)
    {
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_PERKS, data);
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
        
        data.put("value", perks);
        DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_PERKS, data);
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
    
    public Map<Integer, PerkPath> getPerkPaths(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_PERKPATHS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.DDRAGON_PERKPATHS, data);
        if (chl.isPresent())
        {
            return (Map<Integer, PerkPath>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyMap();
            }
            
            List<PerkPath>         list  = (List<PerkPath>) ret;
            Map<Integer, PerkPath> paths = new HashMap<>();
            for (PerkPath path : list)
            {
                paths.put(path.getId(), path);
            }
            
            data.put("value", paths);
            DataCall.getCacheProvider().store(URLEndpoint.DDRAGON_PERKPATHS, data);
            
            return paths;
        } catch (ClassCastException e)
        {
            return Collections.emptyMap();
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
        return "https://ddragon.leagueoflegends.com/cdn/dragontail-{version}.tgz".replace(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
    }
    
    public String getTarball()
    {
        return getTarball(null);
    }
    
}
