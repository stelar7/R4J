package no.stelar7.api.r4j.impl.lol.raw;

import org.jetbrains.annotations.Nullable;
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
    private static final DDragonAPI INSTANCE = new DDragonAPI(R4JContext.getDefault());
    
    public static DDragonAPI getInstance()
    {
        return DDragonAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static DDragonAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(DDragonAPI.class, DDragonAPI::new);
    }

    private final R4JContext context;

    private DDragonAPI(R4JContext context)
    {
        this.context = context;
    }
    
    public Map<Integer, StaticChampion> getChampions(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_CHAMPION_MANY);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticChampionList list = builder.buildCached(StaticChampionList.class, data);
        return list != null ? list.getData() : Collections.emptyMap();
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
    
    @Nullable
    public StaticChampion getChampion(int id)
    {
        return getChampion(id, null, null);
    }
    
    public Map<Integer, Item> getItems(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_ITEMS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        ItemList list = builder.buildCached(ItemList.class, data);
        return list != null ? list.getData() : Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_LANGUAGE_STRINGS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        LanguageStrings list = builder.buildCached(LanguageStrings.class, data);
        return list != null ? list.getData() : Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_LANGUAGES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, "");
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, "");
        
        Map<String, Object> data = new LinkedHashMap<>();
        Optional<?>         chl  = context.getCacheProvider().get(URLEndpoint.DDRAGON_LANGUAGES, data);
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
            context.getCacheProvider().store(URLEndpoint.DDRAGON_LANGUAGES, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public Map<String, MapDetails> getMaps(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_MAPS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        MapData list = builder.buildCached(MapData.class, data);
        return list != null ? list.getData() : Collections.emptyMap();
    }
    
    public Map<String, MapDetails> getMaps()
    {
        return getMaps(null, null);
    }
    
    public Map<Integer, StaticMastery> getMasteries(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getN().get("mastery") : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticMasteryList list = builder.buildCached(StaticMasteryList.class, data);
        return list != null ? list.getData() : Collections.emptyMap();
    }
    
    public Map<Integer, StaticMastery> getMasteries()
    {
        return getMasteries(null, null);
    }
    
    public Map<String, List<List<MasteryTreeItem>>> getMasteryTree(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getN().get("mastery") : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticMasteryList list = builder.buildCached(StaticMasteryList.class, data);
        return list != null ? list.getTree() : Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_PROFILEICONS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        ProfileIconData list = builder.buildCached(ProfileIconData.class, data);
        return list != null ? list.getData() : Collections.emptyMap();
    }
    
    public Map<Long, ProfileIconDetails> getProfileIcons()
    {
        return getProfileIcons(null, null);
    }
    
    /**
     * Always returns euw
     * @return realm euw
     */
    @Nullable
    public Realm getRealm()
    {
        return getRealm(LeagueShard.EUW1);
    }
    
    @Nullable
    public Realm getRealm(LeagueShard region)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withURLParameter(Constants.REGION_PLACEHOLDER, region.getRealmValue())
                .withEndpoint(URLEndpoint.DDRAGON_REALMS);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", region);
        
        return builder.buildCached(Realm.class, data);
    }
    
    public Map<Integer, StaticRune> getRunes(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_RUNES);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getRealm().getN().get("rune") : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticRuneList list = builder.buildCached(StaticRuneList.class, data);
        return list != null ? list.getData() : Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_SUMMONER_SPELLS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticSummonerSpellList list = builder.buildCached(StaticSummonerSpellList.class, data);
        return list != null ? list.getData() : Collections.emptyMap();
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
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_VERSIONS);
        
        
        Map<String, Object> data = new LinkedHashMap<>();
        Optional<?>         chl  = context.getCacheProvider().get(URLEndpoint.DDRAGON_VERSIONS, data);
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
            context.getCacheProvider().store(URLEndpoint.DDRAGON_VERSIONS, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public Map<Integer, StaticPerk> getPerks(String version, String locale)
    {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = context.getCacheProvider().get(URLEndpoint.DDRAGON_PERKS, data);
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
        context.getCacheProvider().store(URLEndpoint.DDRAGON_PERKS, data);
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
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(Constants.DDRAGON_PROXY)
                .withEndpoint(URLEndpoint.DDRAGON_PERKPATHS);
        
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, version == null ? getVersions().get(0) : version);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        Optional<?> chl = context.getCacheProvider().get(URLEndpoint.DDRAGON_PERKPATHS, data);
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
            context.getCacheProvider().store(URLEndpoint.DDRAGON_PERKPATHS, data);
            
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
