package no.stelar7.api.r4j.impl.lol.raw;

import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.pojo.lol.cdragon.CDragonChampion;

import java.util.*;

/**
 * Champion data and assets from the CommunityDragon CDN (https://cdn.communitydragon.org).
 * <p>
 * CommunityDragon is run by the community, not by Riot. It is the only data source in this library that is not operated by Riot,
 * and nothing in the library uses it unless you call this class. It has no api key, and none of your keys are sent to it.
 * The data follows the files of the game client, so it can change shape with a patch; the classes it is read into ignore what they do not know.
 * <p>
 * It offers things Data Dragon does not: centered splash art, tiles, portraits, ability icons by key, champion select sounds, ward and honor images,
 * and champion data straight from the client. What it does not offer through the CDN is rune (perk) data and images.
 * <p>
 * A patch is "latest" (the default, used when null is given) or a full version like "15.1.1". A champion is its id ("1") or its key ("annie").
 * The methods returning a String return a url; an asset that does not exist for a patch (ie. the honor images on recent patches) answers 404.
 */
public final class CDragonAPI
{
    public static final String LATEST = "latest";

    public static final String DEFAULT_BASE_URL = "https://cdn.communitydragon.org/";

    public enum Ability
    {
        PASSIVE("passive"), Q("q"), W("w"), E("e"), R("r");

        private final String path;

        Ability(String path)
        {
            this.path = path;
        }
    }

    public enum ChampSelectSound
    {
        /**
         * What the champion says when banned
         */
        BAN("ban"),
        /**
         * What the champion says when picked
         */
        CHOOSE("choose"),
        /**
         * The sound effect played when the champion is picked
         */
        SFX("sfx");

        private final String path;

        ChampSelectSound(String path)
        {
            this.path = path;
        }
    }

    private static final CDragonAPI INSTANCE = new CDragonAPI(R4JContext.getDefault());

    public static CDragonAPI getInstance()
    {
        return CDragonAPI.INSTANCE;
    }

    /**
     * @param context the context to take the cache and settings from
     * @return the api belonging to the context
     */
    public static CDragonAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(CDragonAPI.class, CDragonAPI::new);
    }

    private final R4JContext context;

    private volatile String baseUrl = DEFAULT_BASE_URL;

    private CDragonAPI(R4JContext context)
    {
        this.context = context;
    }

    /**
     * @param baseUrl where to find the CDN, ie. a mirror of your own. Defaults to {@link #DEFAULT_BASE_URL}.
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

    private static String patch(String patch)
    {
        return patch == null || patch.isEmpty() ? LATEST : patch;
    }

    /**
     * Keys are lowercase in the urls ("annie"), ids are used as they are ("1")
     */
    private static String champion(String champion)
    {
        return champion.toLowerCase(Locale.ENGLISH);
    }

    private String url(String patch, String path)
    {
        return baseUrl + patch(patch) + "/" + path;
    }

    private String championUrl(String patch, String champion, String path)
    {
        return url(patch, "champion/" + champion(champion) + "/" + path);
    }

    private String skinUrl(String patch, String champion, String path, Integer skinNum)
    {
        return championUrl(patch, champion, skinNum == null ? path : path + "/skin/" + skinNum);
    }

    /**
     * The data of a champion on the latest patch, as the game client has it.
     *
     * @param championId the id of the champion
     * @return the champion, or null if it does not exist
     */
    public CDragonChampion getChampion(int championId)
    {
        return getChampion(null, String.valueOf(championId));
    }

    /**
     * The data of a champion, as the game client has it.
     *
     * @param patch    "latest" (or null), or a full version like "15.1.1"
     * @param champion the id ("1") or the key ("annie") of the champion
     * @return the champion, or null if it (or the patch) does not exist
     */
    @Nullable
    public CDragonChampion getChampion(String patch, String champion)
    {
        // made without limiters, which also means no api key is added to the request
        DataCallBuilder builder = new DataCallBuilder(context)
                .withLimiters(false)
                .withProxy(baseUrl + Constants.GSVR)
                .withEndpoint(URLEndpoint.CDRAGON_CHAMPION_DATA)
                .withURLParameter(Constants.VERSION_PLACEHOLDER, patch(patch))
                .withURLParameter(Constants.ID_PLACEHOLDER, champion(champion));

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("patch", patch(patch));
        data.put("champion", champion(champion));

        return builder.buildCached(CDragonChampion.class, data);
    }

    /**
     * @return the url of the square icon of the champion
     */
    public String getChampionSquare(String patch, String champion)
    {
        return championUrl(patch, champion, "square");
    }

    /**
     * @return the url of the square icon shown for an unknown champion
     */
    public String getGenericChampionSquare(String patch)
    {
        return url(patch, "champion/generic/square");
    }

    /**
     * @return the url of the splash art of the champion
     */
    public String getSplashArt(String patch, String champion)
    {
        return skinUrl(patch, champion, "splash-art", null);
    }

    /**
     * @param skinNum the number of the skin (the last three digits of its id, ie. 1 for skin 1001)
     * @return the url of the splash art of the skin
     */
    public String getSplashArt(String patch, String champion, int skinNum)
    {
        return skinUrl(patch, champion, "splash-art", skinNum);
    }

    /**
     * @return the url of the splash art of the champion, cropped around the champion
     */
    public String getCenteredSplashArt(String patch, String champion)
    {
        return skinUrl(patch, champion, "splash-art/centered", null);
    }

    /**
     * @param skinNum the number of the skin (the last three digits of its id, ie. 1 for skin 1001)
     * @return the url of the splash art of the skin, cropped around the champion
     */
    public String getCenteredSplashArt(String patch, String champion, int skinNum)
    {
        return skinUrl(patch, champion, "splash-art/centered", skinNum);
    }

    /**
     * @return the url of the tile (the small splash used in collections) of the champion
     */
    public String getTile(String patch, String champion)
    {
        return skinUrl(patch, champion, "tile", null);
    }

    /**
     * @param skinNum the number of the skin (the last three digits of its id, ie. 1 for skin 1001)
     * @return the url of the tile of the skin
     */
    public String getTile(String patch, String champion, int skinNum)
    {
        return skinUrl(patch, champion, "tile", skinNum);
    }

    /**
     * @return the url of the portrait (the loading screen art) of the champion
     */
    public String getPortrait(String patch, String champion)
    {
        return skinUrl(patch, champion, "portrait", null);
    }

    /**
     * @param skinNum the number of the skin (the last three digits of its id, ie. 1 for skin 1001)
     * @return the url of the portrait of the skin
     */
    public String getPortrait(String patch, String champion, int skinNum)
    {
        return skinUrl(patch, champion, "portrait", skinNum);
    }

    /**
     * @return the url of the icon of an ability of the champion
     */
    public String getAbilityIcon(String patch, String champion, Ability ability)
    {
        return championUrl(patch, champion, "ability-icon/" + ability.path);
    }

    /**
     * @return the url of a champion select sound of the champion (an ogg file)
     */
    public String getChampSelectSound(String patch, String champion, ChampSelectSound sound)
    {
        return championUrl(patch, champion, "champ-select/sounds/" + sound.path);
    }

    /**
     * @return the url of the image of a ward skin
     */
    public String getWard(String patch, int wardId)
    {
        return url(patch, "ward/" + wardId);
    }

    /**
     * @return the url of the shadow of a ward skin
     */
    public String getWardShadow(String patch, int wardId)
    {
        return url(patch, "ward/" + wardId + "/shadow");
    }

    /**
     * @return the url of a profile icon
     */
    public String getProfileIcon(String patch, int profileIconId)
    {
        return url(patch, "profile-icon/" + profileIconId);
    }

    /**
     * @return the url of the image shown when the honor level is unknown
     */
    public String getGenericHonor(String patch)
    {
        return url(patch, "honor/generic");
    }

    /**
     * @return the url of the image of an honor level
     */
    public String getHonor(String patch, int honorId)
    {
        return url(patch, "honor/" + honorId);
    }

    /**
     * @return the url of the image of a locked honor level
     */
    public String getLockedHonor(String patch, int honorId)
    {
        return url(patch, "honor/" + honorId + "/locked");
    }

    /**
     * @return the url of the image of a checkpoint of an honor level
     */
    public String getHonor(String patch, int honorId, int level)
    {
        return url(patch, "honor/" + honorId + "/level/" + level);
    }

    /**
     * @return the url of the emblem shown when the honor level is unknown
     */
    public String getGenericHonorEmblem(String patch)
    {
        return url(patch, "honor/emblem/generic");
    }

    /**
     * @return the url of the emblem of an honor level
     */
    public String getHonorEmblem(String patch, int honorId)
    {
        return url(patch, "honor/emblem/" + honorId);
    }

    /**
     * @return the url of the emblem of a locked honor level
     */
    public String getLockedHonorEmblem(String patch, int honorId)
    {
        return url(patch, "honor/emblem/" + honorId + "/locked");
    }

    /**
     * @return the url of the emblem of a checkpoint of an honor level
     */
    public String getHonorEmblem(String patch, int honorId, int level)
    {
        return url(patch, "honor/emblem/" + honorId + "/level/" + level);
    }
}
