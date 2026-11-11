package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
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
    
    // ---- Generated: the ...Async twin of every method above. Do not edit by hand; they follow the blocking methods.
    
    /**
     * Like {@link #getChampions}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticChampion>> getChampionsAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(StaticChampionList.class, data).thenApply(list -> list != null ? list.getData() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getChampionsFromId} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<StaticChampion>> getChampionsFromIdAsync(List<Integer> freeChampionIds)
    {
        return context.callAsync(() -> getChampionsFromId(freeChampionIds));
    }
    
    /**
     * Runs {@link #getChampionsFromId} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<StaticChampion>> getChampionsFromIdAsync(List<Integer> freeChampionIds, String version, String locale)
    {
        return context.callAsync(() -> getChampionsFromId(freeChampionIds, version, locale));
    }
    
    /**
     * Runs {@link #getChampions} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticChampion>> getChampionsAsync()
    {
        return context.callAsync(() -> getChampions());
    }
    
    /**
     * Runs {@link #getChampion} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticChampion> getChampionAsync(int id, String version, String locale)
    {
        return context.callAsync(() -> getChampion(id, version, locale));
    }
    
    /**
     * Runs {@link #getChampion} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticChampion> getChampionAsync(int id)
    {
        return context.callAsync(() -> getChampion(id));
    }
    
    /**
     * Like {@link #getItems}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, Item>> getItemsAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(ItemList.class, data).thenApply(list -> list != null ? list.getData() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getItems} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, Item>> getItemsAsync()
    {
        return context.callAsync(() -> getItems());
    }
    
    /**
     * Runs {@link #getItem} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Item> getItemAsync(int id, String version, String locale)
    {
        return context.callAsync(() -> getItem(id, version, locale));
    }
    
    /**
     * Runs {@link #getItem} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Item> getItemAsync(int id)
    {
        return context.callAsync(() -> getItem(id));
    }
    
    /**
     * Like {@link #getLanguageStrings}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, String>> getLanguageStringsAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(LanguageStrings.class, data).thenApply(list -> list != null ? list.getData() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getLanguageStrings} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, String>> getLanguageStringsAsync()
    {
        return context.callAsync(() -> getLanguageStrings());
    }
    
    /**
     * Runs {@link #getLanguages} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> getLanguagesAsync()
    {
        return context.callAsync(() -> getLanguages());
    }
    
    /**
     * Like {@link #getMaps}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, MapDetails>> getMapsAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(MapData.class, data).thenApply(list -> list != null ? list.getData() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getMaps} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, MapDetails>> getMapsAsync()
    {
        return context.callAsync(() -> getMaps());
    }
    
    /**
     * Like {@link #getMasteries}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticMastery>> getMasteriesAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(StaticMasteryList.class, data).thenApply(list -> list != null ? list.getData() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getMasteries} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticMastery>> getMasteriesAsync()
    {
        return context.callAsync(() -> getMasteries());
    }
    
    /**
     * Like {@link #getMasteryTree}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, List<List<MasteryTreeItem>>>> getMasteryTreeAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(StaticMasteryList.class, data).thenApply(list -> list != null ? list.getTree() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getMasteryTree} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, List<List<MasteryTreeItem>>>> getMasteryTreeAsync()
    {
        return context.callAsync(() -> getMasteryTree());
    }
    
    /**
     * Runs {@link #getMastery} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticMastery> getMasteryAsync(int id, String version, String locale)
    {
        return context.callAsync(() -> getMastery(id, version, locale));
    }
    
    /**
     * Runs {@link #getMastery} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticMastery> getMasteryAsync(int id)
    {
        return context.callAsync(() -> getMastery(id));
    }
    
    /**
     * Like {@link #getProfileIcons}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Long, ProfileIconDetails>> getProfileIconsAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(ProfileIconData.class, data).thenApply(list -> list != null ? list.getData() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getProfileIcons} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Long, ProfileIconDetails>> getProfileIconsAsync()
    {
        return context.callAsync(() -> getProfileIcons());
    }
    
    /**
     * Runs {@link #getRealm} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Realm> getRealmAsync()
    {
        return context.callAsync(() -> getRealm());
    }
    
    /**
     * Like {@link #getRealm}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Realm> getRealmAsync(LeagueShard region)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(Constants.DDRAGON_PROXY)
                    .withURLParameter(Constants.REGION_PLACEHOLDER, region.getRealmValue())
                    .withEndpoint(URLEndpoint.DDRAGON_REALMS);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", region);
        
            return builder.buildCachedAsync(Realm.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getRunes}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticRune>> getRunesAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(StaticRuneList.class, data).thenApply(list -> list != null ? list.getData() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getRunes} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticRune>> getRunesAsync()
    {
        return context.callAsync(() -> getRunes());
    }
    
    /**
     * Runs {@link #getRune} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticRune> getRuneAsync(int id, String version, String locale)
    {
        return context.callAsync(() -> getRune(id, version, locale));
    }
    
    /**
     * Runs {@link #getRune} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticRune> getRuneAsync(int id)
    {
        return context.callAsync(() -> getRune(id));
    }
    
    /**
     * Like {@link #getSummonerSpells}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticSummonerSpell>> getSummonerSpellsAsync(String version, String locale)
    {
        try
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
        
            return builder.buildCachedAsync(StaticSummonerSpellList.class, data).thenApply(list -> list != null ? list.getData() : Collections.emptyMap());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getSummonerSpells} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticSummonerSpell>> getSummonerSpellsAsync()
    {
        return context.callAsync(() -> getSummonerSpells());
    }
    
    /**
     * Runs {@link #getSummonerSpell} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticSummonerSpell> getSummonerSpellAsync(int id, String version, String locale)
    {
        return context.callAsync(() -> getSummonerSpell(id, version, locale));
    }
    
    /**
     * Runs {@link #getSummonerSpell} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticSummonerSpell> getSummonerSpellAsync(int id)
    {
        return context.callAsync(() -> getSummonerSpell(id));
    }
    
    /**
     * Runs {@link #getVersions} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> getVersionsAsync()
    {
        return context.callAsync(() -> getVersions());
    }
    
    /**
     * Runs {@link #getPerks} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticPerk>> getPerksAsync(String version, String locale)
    {
        return context.callAsync(() -> getPerks(version, locale));
    }
    
    /**
     * Runs {@link #getPerks} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticPerk>> getPerksAsync()
    {
        return context.callAsync(() -> getPerks());
    }
    
    /**
     * Runs {@link #getPerk} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticPerk> getPerkAsync(int id, String version, String locale)
    {
        return context.callAsync(() -> getPerk(id, version, locale));
    }
    
    /**
     * Runs {@link #getPerk} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticPerk> getPerkAsync(int id)
    {
        return context.callAsync(() -> getPerk(id));
    }
    
    /**
     * Runs {@link #getPerkPaths} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, PerkPath>> getPerkPathsAsync(String version, String locale)
    {
        return context.callAsync(() -> getPerkPaths(version, locale));
    }
    
    /**
     * Runs {@link #getPerkPaths} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, PerkPath>> getPerkPathsAsync()
    {
        return context.callAsync(() -> getPerkPaths());
    }
    
    /**
     * Runs {@link #getPerkPath} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<PerkPath> getPerkPathAsync(int id, String version, String locale)
    {
        return context.callAsync(() -> getPerkPath(id, version, locale));
    }
    
    /**
     * Runs {@link #getPerkPath} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<PerkPath> getPerkPathAsync(int id)
    {
        return context.callAsync(() -> getPerkPath(id));
    }
    
    /**
     * Runs {@link #getTarball} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<String> getTarballAsync(String version)
    {
        return context.callAsync(() -> getTarball(version));
    }
    
    /**
     * Runs {@link #getTarball} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<String> getTarballAsync()
    {
        return context.callAsync(() -> getTarball());
    }
}
