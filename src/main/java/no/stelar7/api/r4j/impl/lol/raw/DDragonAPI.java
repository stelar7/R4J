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
    
    public static final String DEFAULT_BASE_URL = "https://ddragon.leagueoflegends.com/";
    
    private volatile String baseUrl = DEFAULT_BASE_URL;
    
    /**
     * @param baseUrl where to find Data Dragon, ie. a mirror of your own (the files of the tarball, served from a folder). Defaults to {@link #DEFAULT_BASE_URL}.
     */
    public void setBaseUrl(String baseUrl)
    {
        String url = baseUrl == null ? DEFAULT_BASE_URL : baseUrl;
        this.baseUrl = url.endsWith("/") ? url : url + "/";
    }
    
    public String getBaseUrl()
    {
        return baseUrl;
    }
    
    public Map<Integer, StaticChampion> getChampions(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_CHAMPION_MANY);
        
        String resolved = version != null ? version : latestVersion();
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticChampionList list = builder.buildCached(StaticChampionList.class, data);
        return list != null && list.getData() != null ? list.getData() : Collections.emptyMap();
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
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_ITEMS);
        
        String resolved = version != null ? version : latestVersion();
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        ItemList list = builder.buildCached(ItemList.class, data);
        return list != null && list.getData() != null ? list.getData() : Collections.emptyMap();
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
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_LANGUAGE_STRINGS);
        
        String resolved = version != null ? version : latestVersion();
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        LanguageStrings list = builder.buildCached(LanguageStrings.class, data);
        return list != null && list.getData() != null ? list.getData() : Collections.emptyMap();
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
                .withProxy(baseUrl + Constants.GSVR)
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
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_MAPS);
        
        String resolved = version != null ? version : latestVersion();
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        MapData list = builder.buildCached(MapData.class, data);
        return list != null && list.getData() != null ? list.getData() : Collections.emptyMap();
    }
    
    public Map<String, MapDetails> getMaps()
    {
        return getMaps(null, null);
    }
    
    public Map<Integer, StaticMastery> getMasteries(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
        String resolved = version != null ? version : realmVersion("mastery");
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticMasteryList list = builder.buildCached(StaticMasteryList.class, data);
        return list != null && list.getData() != null ? list.getData() : Collections.emptyMap();
    }
    
    public Map<Integer, StaticMastery> getMasteries()
    {
        return getMasteries(null, null);
    }
    
    public Map<String, List<List<MasteryTreeItem>>> getMasteryTree(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
        String resolved = version != null ? version : realmVersion("mastery");
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
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
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_PROFILEICONS);
        
        String resolved = version != null ? version : latestVersion();
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        ProfileIconData list = builder.buildCached(ProfileIconData.class, data);
        return list != null && list.getData() != null ? list.getData() : Collections.emptyMap();
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
        DataCallBuilder builder = getRealmCall(region);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(Realm.class, data);
    }

    // the call getRealm and its twin make
    private DataCallBuilder getRealmCall(LeagueShard region)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(baseUrl + Constants.GSVR)
                .withURLParameter(Constants.REGION_PLACEHOLDER, region.getRealmValue())
                .withEndpoint(URLEndpoint.DDRAGON_REALMS);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", region);

        return builder.withCacheKey(data);
    }
    
    public Map<Integer, StaticRune> getRunes(String version, String locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_RUNES);
        
        String resolved = version != null ? version : realmVersion("rune");
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticRuneList list = builder.buildCached(StaticRuneList.class, data);
        return list != null && list.getData() != null ? list.getData() : Collections.emptyMap();
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
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_SUMMONER_SPELLS);
        
        String resolved = version != null ? version : latestVersion();
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
        builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("version", version);
        data.put("locale", locale);
        
        StaticSummonerSpellList list = builder.buildCached(StaticSummonerSpellList.class, data);
        return list != null && list.getData() != null ? list.getData() : Collections.emptyMap();
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
        DataCallBuilder builder = getVersionsCall();
        
        
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

    // the call getVersions and its twin make
    private DataCallBuilder getVersionsCall()
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_VERSIONS);

        return builder;
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
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.DDRAGON_PERKPATHS);
        
        String resolved = version != null ? version : latestVersion();
        if (resolved == null)
        {
            // Data Dragon could not tell which version to use
            return Collections.emptyMap();
        }
        builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
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
    
    /**
     * @return the newest version, or null if Data Dragon did not answer with a list of versions
     */
    private String latestVersion()
    {
        List<String> versions = getVersions();
        return versions.isEmpty() ? null : versions.get(0);
    }
    
    /**
     * @return the version of data that is no longer updated (masteries and runes), or null if Data Dragon did not answer with a realm
     */
    private String realmVersion(String kind)
    {
        Realm realm = getRealm();
        return realm == null || realm.getN() == null ? null : realm.getN().get(kind);
    }
    
    /**
     * @return the url of the tarball of the version, or null if no version is given and Data Dragon could not tell which is the newest
     */
    @Nullable
    public String getTarball(String version)
    {
        String resolved = version != null ? version : latestVersion();
        return resolved == null ? null : (baseUrl + "cdn/dragontail-{version}.tgz").replace(Constants.VERSION_PLACEHOLDER, resolved);
    }
    
    public String getTarball()
    {
        return getTarball(null);
    }
    
    /**
     * Like {@link #getChampions}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticChampion>> getChampionsAsync(String version, String locale)
    {
        return versionOrLatestAsync(version).thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_CHAMPION_MANY);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(StaticChampionList.class, data).thenApply(list -> list != null && list.getData() != null ? list.getData() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getChampionsFromId}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<StaticChampion>> getChampionsFromIdAsync(List<Integer> freeChampionIds)
    {
        return getChampionsAsync().thenApply(all -> championsFromId(all, freeChampionIds));
    }
    
    /**
     * Like {@link #getChampionsFromId}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<StaticChampion>> getChampionsFromIdAsync(List<Integer> freeChampionIds, String version, String locale)
    {
        return getChampionsAsync(version, locale).thenApply(all -> championsFromId(all, freeChampionIds));
    }
    
    /**
     * Like {@link #getChampions}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticChampion>> getChampionsAsync()
    {
        return getChampionsAsync(null, null);
    }
    
    /**
     * Like {@link #getChampion}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticChampion> getChampionAsync(int id, String version, String locale)
    {
        return getChampionsAsync(version, locale).thenApply(all -> all.get(id));
    }
    
    /**
     * Like {@link #getChampion}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticChampion> getChampionAsync(int id)
    {
        return getChampionAsync(id, null, null);
    }
    
    /**
     * Like {@link #getItems}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, Item>> getItemsAsync(String version, String locale)
    {
        return versionOrLatestAsync(version).thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_ITEMS);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(ItemList.class, data).thenApply(list -> list != null && list.getData() != null ? list.getData() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getItems}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, Item>> getItemsAsync()
    {
        return getItemsAsync(null, null);
    }
    
    /**
     * Like {@link #getItem}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Item> getItemAsync(int id, String version, String locale)
    {
        return getItemsAsync(version, locale).thenApply(all -> all.get(id));
    }
    
    /**
     * Like {@link #getItem}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Item> getItemAsync(int id)
    {
        return getItemAsync(id, null, null);
    }
    
    /**
     * Like {@link #getLanguageStrings}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, String>> getLanguageStringsAsync(String version, String locale)
    {
        return versionOrLatestAsync(version).thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_LANGUAGE_STRINGS);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(LanguageStrings.class, data).thenApply(list -> list != null && list.getData() != null ? list.getData() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getLanguageStrings}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, String>> getLanguageStringsAsync()
    {
        return getLanguageStringsAsync(null, null);
    }
    
    /**
     * Like {@link #getLanguages}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> getLanguagesAsync()
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_LANGUAGES);

            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, "");
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, "");

            Map<String, Object> data = new LinkedHashMap<>();
            Optional<?>         chl  = context.getCacheProvider().get(URLEndpoint.DDRAGON_LANGUAGES, data);
            if (chl.isPresent())
            {
                return CompletableFuture.completedFuture((List<String>) chl.get());
            }

            return builder.buildAsync().thenApply(ret -> stringsFrom(ret, URLEndpoint.DDRAGON_LANGUAGES, data));
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getMaps}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, MapDetails>> getMapsAsync(String version, String locale)
    {
        return versionOrLatestAsync(version).thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_MAPS);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(MapData.class, data).thenApply(list -> list != null && list.getData() != null ? list.getData() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getMaps}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, MapDetails>> getMapsAsync()
    {
        return getMapsAsync(null, null);
    }
    
    /**
     * Like {@link #getMasteries}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticMastery>> getMasteriesAsync(String version, String locale)
    {
        return versionOrRealmAsync(version, "mastery").thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(StaticMasteryList.class, data).thenApply(list -> list != null && list.getData() != null ? list.getData() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getMasteries}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticMastery>> getMasteriesAsync()
    {
        return getMasteriesAsync(null, null);
    }
    
    /**
     * Like {@link #getMasteryTree}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, List<List<MasteryTreeItem>>>> getMasteryTreeAsync(String version, String locale)
    {
        return versionOrRealmAsync(version, "mastery").thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_MASTERIES);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(StaticMasteryList.class, data).thenApply(list -> list != null ? list.getTree() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getMasteryTree}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<String, List<List<MasteryTreeItem>>>> getMasteryTreeAsync()
    {
        return getMasteryTreeAsync(null, null);
    }
    
    /**
     * Like {@link #getMastery}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticMastery> getMasteryAsync(int id, String version, String locale)
    {
        return getMasteriesAsync(version, locale).thenApply(all -> all.get(id));
    }
    
    /**
     * Like {@link #getMastery}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticMastery> getMasteryAsync(int id)
    {
        return getMasteryAsync(id, null, null);
    }
    
    /**
     * Like {@link #getProfileIcons}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Long, ProfileIconDetails>> getProfileIconsAsync(String version, String locale)
    {
        return versionOrLatestAsync(version).thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_PROFILEICONS);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(ProfileIconData.class, data).thenApply(list -> list != null && list.getData() != null ? list.getData() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getProfileIcons}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Long, ProfileIconDetails>> getProfileIconsAsync()
    {
        return getProfileIconsAsync(null, null);
    }
    
    /**
     * Like {@link #getRealm}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Realm> getRealmAsync()
    {
        return getRealmAsync(LeagueShard.EUW1);
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
            DataCallBuilder builder = getRealmCall(region);
            Map<String, Object> data = builder.getCacheKey();
    
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
        return versionOrRealmAsync(version, "rune").thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_RUNES);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(StaticRuneList.class, data).thenApply(list -> list != null && list.getData() != null ? list.getData() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getRunes}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticRune>> getRunesAsync()
    {
        return getRunesAsync(null, null);
    }
    
    /**
     * Like {@link #getRune}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticRune> getRuneAsync(int id, String version, String locale)
    {
        return getRunesAsync(version, locale).thenApply(all -> all.get(id));
    }
    
    /**
     * Like {@link #getRune}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticRune> getRuneAsync(int id)
    {
        return getRuneAsync(id, null, null);
    }
    
    /**
     * Like {@link #getSummonerSpells}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticSummonerSpell>> getSummonerSpellsAsync(String version, String locale)
    {
        return versionOrLatestAsync(version).thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_SUMMONER_SPELLS);
        
            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);
        
            return builder.buildCachedAsync(StaticSummonerSpellList.class, data).thenApply(list -> list != null && list.getData() != null ? list.getData() : Collections.emptyMap());
        });
    }
    
    /**
     * Like {@link #getSummonerSpells}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticSummonerSpell>> getSummonerSpellsAsync()
    {
        return getSummonerSpellsAsync(null, null);
    }
    
    /**
     * Like {@link #getSummonerSpell}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticSummonerSpell> getSummonerSpellAsync(int id, String version, String locale)
    {
        return getSummonerSpellsAsync(version, locale).thenApply(all -> all.get(id));
    }
    
    /**
     * Like {@link #getSummonerSpell}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticSummonerSpell> getSummonerSpellAsync(int id)
    {
        return getSummonerSpellAsync(id, null, null);
    }
    
    /**
     * Like {@link #getVersions}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> getVersionsAsync()
    {
        try
        {
            DataCallBuilder builder = getVersionsCall();

            Map<String, Object> data = new LinkedHashMap<>();
            Optional<?>         chl  = context.getCacheProvider().get(URLEndpoint.DDRAGON_VERSIONS, data);
            if (chl.isPresent())
            {
                return CompletableFuture.completedFuture((List<String>) chl.get());
            }

            return builder.buildAsync().thenApply(ret -> stringsFrom(ret, URLEndpoint.DDRAGON_VERSIONS, data));
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getPerks}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticPerk>> getPerksAsync(String version, String locale)
    {
        try
        {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);

            Optional<?> chl = context.getCacheProvider().get(URLEndpoint.DDRAGON_PERKS, data);
            if (chl.isPresent())
            {
                return CompletableFuture.completedFuture((Map<Integer, StaticPerk>) chl.get());
            }

            return getPerkPathsAsync(version, locale).thenApply(paths -> {
                Map<Integer, StaticPerk> perks = new HashMap<>();
                for (Entry<Integer, PerkPath> path : paths.entrySet())
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
            });
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getPerks}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, StaticPerk>> getPerksAsync()
    {
        return getPerksAsync(null, null);
    }
    
    /**
     * Like {@link #getPerk}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticPerk> getPerkAsync(int id, String version, String locale)
    {
        return getPerksAsync(version, locale).thenApply(all -> all.get(id));
    }
    
    /**
     * Like {@link #getPerk}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<StaticPerk> getPerkAsync(int id)
    {
        return getPerkAsync(id, null, null);
    }
    
    /**
     * Like {@link #getPerkPaths}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, PerkPath>> getPerkPathsAsync(String version, String locale)
    {
        return versionOrLatestAsync(version).thenCompose(resolved -> {
            DataCallBuilder builder = new DataCallBuilder(context)
                    .withLimiters(false)
                    .withProxy(baseUrl + Constants.GSVR)
                    .withEndpoint(URLEndpoint.DDRAGON_PERKPATHS);

            if (resolved == null)
            {
                // Data Dragon could not tell which version to use
                return CompletableFuture.completedFuture(Collections.emptyMap());
            }
            builder.withURLParameter(Constants.VERSION_PLACEHOLDER, resolved);
            builder.withURLParameter(Constants.LOCALE_PLACEHOLDER, locale == null ? "en_US" : locale);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("version", version);
            data.put("locale", locale);

            Optional<?> chl = context.getCacheProvider().get(URLEndpoint.DDRAGON_PERKPATHS, data);
            if (chl.isPresent())
            {
                return CompletableFuture.completedFuture((Map<Integer, PerkPath>) chl.get());
            }

            return builder.buildAsync().thenApply(ret -> perkPathsFrom(ret, data));
        });
    }
    
    /**
     * Like {@link #getPerkPaths}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Map<Integer, PerkPath>> getPerkPathsAsync()
    {
        return getPerkPathsAsync(null, null);
    }
    
    /**
     * Like {@link #getPerkPath}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<PerkPath> getPerkPathAsync(int id, String version, String locale)
    {
        return getPerkPathsAsync(version, locale).thenApply(all -> all.get(id));
    }
    
    /**
     * Like {@link #getPerkPath}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<PerkPath> getPerkPathAsync(int id)
    {
        return getPerkPathAsync(id, null, null);
    }
    
    /**
     * Like {@link #getTarball}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<String> getTarballAsync(String version)
    {
        return versionOrLatestAsync(version).thenApply(resolved -> resolved == null ? null : (baseUrl + "cdn/dragontail-{version}.tgz").replace(Constants.VERSION_PLACEHOLDER, resolved));
    }
    
    /**
     * Like {@link #getTarball}, as a future: see the twin of the method it passes its arguments on to.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<String> getTarballAsync()
    {
        return getTarballAsync(null);
    }
    
    /**
     * The version to use for a call: the one given, or the newest one, which is looked up (without a thread waiting) when it is not in the cache
     */
    private CompletableFuture<String> versionOrLatestAsync(String version)
    {
        return version != null ? CompletableFuture.completedFuture(version) : getVersionsAsync().thenApply(versions -> versions.isEmpty() ? null : versions.get(0));
    }

    /**
     * The version to use for data that is no longer updated (masteries and runes): the one given, or the one the realm names
     */
    private CompletableFuture<String> versionOrRealmAsync(String version, String kind)
    {
        return version != null ? CompletableFuture.completedFuture(version) : getRealmAsync().thenApply(realm -> realm == null || realm.getN() == null ? null : realm.getN().get(kind));
    }

    private static List<StaticChampion> championsFromId(Map<Integer, StaticChampion> all, List<Integer> ids)
    {
        List<StaticChampion> champs = new ArrayList<>();
        for (Integer id : ids)
        {
            StaticChampion champ = all.get(id);
            if (champ != null)
            {
                champs.add(champ);
            }
        }

        return champs;
    }

    /**
     * What the answer to a call for a list of strings (versions, languages) means, like the blocking methods read it
     */
    private List<String> stringsFrom(Object ret, URLEndpoint endpoint, Map<String, Object> data)
    {
        try
        {
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }

            List<String> list = (List<String>) ret;

            data.put("value", list);
            context.getCacheProvider().store(endpoint, data);

            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }

    /**
     * What the answer to the call for the rune paths means, like the blocking method reads it
     */
    private Map<Integer, PerkPath> perkPathsFrom(Object ret, Map<String, Object> data)
    {
        try
        {
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
}
