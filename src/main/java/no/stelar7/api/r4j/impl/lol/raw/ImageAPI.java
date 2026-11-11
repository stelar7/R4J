package no.stelar7.api.r4j.impl.lol.raw;

import java.util.Map;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.Constants;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.item.Item;
import no.stelar7.api.r4j.pojo.lol.staticdata.mastery.StaticMastery;
import no.stelar7.api.r4j.pojo.lol.staticdata.realm.Realm;
import no.stelar7.api.r4j.pojo.lol.staticdata.rune.StaticRune;
import no.stelar7.api.r4j.pojo.lol.staticdata.summonerspell.StaticSummonerSpell;

public final class ImageAPI
{
    private static final ImageAPI INSTANCE = new ImageAPI(R4JContext.getDefault());
    
    public static ImageAPI getInstance()
    {
        return ImageAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static ImageAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(ImageAPI.class, ImageAPI::new);
    }

    private final R4JContext context;

    private ImageAPI(R4JContext context)
    {
        this.context = context;
    }
    
    private String buildImageURL(String version, String path, String file)
    {
        Realm  realm         = DDragonAPI.getInstance(context).getRealm();
        String cdn           = realm.getCDN();
        String versionString = version != null ? version : currentVersion(realm, path);

        String preReplace = cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + file;
        return preReplace.replace(" ", "%20");
    }

    /**
     * The version to use when none is given. Not everything is published for the current patch:
     * the old runes and masteries only exist for the last patch that had them, which the realm tells us.
     */
    private String currentVersion(Realm realm, String path)
    {
        String type = null;
        if (path.equals("img/rune"))
        {
            type = "rune";
        } else if (path.equals("img/mastery"))
        {
            type = "mastery";
        }

        Map<String, String> versions = realm.getN();
        return type != null && versions != null && versions.get(type) != null ? versions.get(type) : realm.getV();
    }

    /**
     * The art that is not tied to a patch (splash and loading screen art) has no version in its url
     */
    private String buildUnversionedImageURL(String path, String file)
    {
        Realm  realm = DDragonAPI.getInstance(context).getRealm();
        String cdn   = realm.getCDN();

        String preReplace = cdn + Constants.SEPARATOR + path + Constants.SEPARATOR + file;
        return preReplace.replace(" ", "%20");
    }

    private String buildSplashImageURL(String file)
    {
        return buildUnversionedImageURL("img/champion/splash", file);
    }


    /**
     * Gets profile icon.
     *
     * @param iconid  the iconid
     * @param version the version
     * @return the profile icon
     */
    public String getProfileIcon(String iconid, String version)
    {
        //https://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/588.png
        return buildImageURL(version, "img/profileicon", iconid + ".png");
    }
    
    /**
     * Gets profile icon.
     *
     * @param region       the region
     * @param summonerName the summoner name
     * @return the profile icon
     * @deprecated the avatar service this url points at no longer exists (the host does not answer), and summoner names are no longer used to look players up.
     * Get the icon id from the Summoner, and use {@link #getProfileIcon(String, String)}.
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public String getProfileIcon(LeagueShard region, String summonerName)
    {
        // http://avatar.leagueoflegends.com/region/summonername.png
        return "http://avatar.leagueoflegends.com/" + region.toString() + Constants.SEPARATOR + summonerName.replace(" ", "%20") + ".png";
    }
    
    /**
     * Gets splash art.
     *
     * @param championKey the champion key
     * @param skinNum     the skin num
     * @return the splash art
     */
    public String getSplashArt(String championKey, int skinNum)
    {
        // https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return buildSplashImageURL(championKey + "_" + skinNum + ".jpg");
    }
    
    /**
     * Gets splash art.
     *
     * @param championKey the champion key
     * @param skin        the skin
     * @return the splash art
     */
    public String getSplashArt(String championKey, Skin skin)
    {
        // https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return buildSplashImageURL(championKey + "_" + skin.getNum() + ".jpg");
    }
    
    /**
     * Gets loading screen art.
     *
     * @param championKey the key of the champion (ie. "Aatrox")
     * @param skinNum     the skin num
     * @return the loading screen art
     */
    public String getLoadingScreenArt(String championKey, int skinNum)
    {
        // https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        return buildUnversionedImageURL("img/champion/loading", championKey + "_" + skinNum + ".jpg");
    }
    
    /**
     * Gets loading screen art.
     *
     * @param skin the skin
     * @return the loading screen art
     */
    public String getLoadingScreenArt(Skin skin)
    {
        // https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        // the url needs the key of the champion; the id of a skin is the id of its champion followed by the three digits of the skin
        StaticChampion champion = DDragonAPI.getInstance(context).getChampion(skin.getId() / 1000);
        return getLoadingScreenArt(champion.getKey(), skin.getNum());
    }
    
    
    /**
     * Gets square.
     *
     * @param champ   the champ
     * @param version the version
     * @return the square
     */
    public String getSquare(StaticChampion champ, String version)
    {
        // https://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
        return buildImageURL(version, "img/champion", champ.getImage().getFull());
    }
    
    /**
     * Gets square.
     *
     * @param champKey the champ key
     * @param version  the version
     * @return the square
     */
    public String getSquare(String champKey, String version)
    {
        // https://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
        return buildImageURL(version, "img/champion", champKey + ".png");
    }
    
    
    /**
     * Gets ability.
     *
     * @param imageFull the spell.getImage().getFull()
     * @param version   the version
     * @return the ability
     */
    public String getPassive(String imageFull, String version)
    {
        // https://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Anivia_P.png
        return buildImageURL(version, "img/passive", imageFull);
    }
    
    /**
     * Gets passive.
     *
     * @param passive the passive
     * @param version the version
     * @return the passive
     */
    public String getPassive(Passive passive, String version)
    {
        // https://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Anivia_P.png
        return getPassive(passive.getImage().getFull(), version);
    }
    
    /**
     * Gets ability.
     *
     * @param spell   the spell
     * @param version the version
     * @return the ability
     */
    public String getAbility(StaticChampionSpell spell, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
        return buildImageURL(version, "img/spell", spell.getImage().getFull());
    }
    
    /**
     * Gets ability.
     *
     * @param imageFull the spell
     * @param version   the version
     * @return the ability
     */
    public String getAbility(String imageFull, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
        return buildImageURL(version, "img/spell", imageFull);
    }
    
    /**
     * Gets summoner spell.
     *
     * @param spell   the spell
     * @param version the version
     * @return the summoner spell
     */
    public String getSummonerSpell(SummonerSpellType spell, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
        return buildImageURL(version, "img/spell", spell.getApiName() + ".png");
    }
    
    /**
     * Gets summoner spell.
     *
     * @param spell   the spell
     * @param version the version
     * @return the summoner spell
     */
    public String getSummonerSpell(StaticSummonerSpell spell, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
        return buildImageURL(version, "img/spell", spell.getImage().getFull());
    }
    
    /**
     * Gets summoner spell.
     *
     * @param spell   the spell
     * @param version the version
     * @return the summoner spell
     */
    public String getSummonerSpell(String spell, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
        return buildImageURL(version, "img/spell", spell + ".png");
    }
    
    /**
     * Gets item.
     *
     * @param id      the id
     * @param version the version
     * @return the item
     */
    public String getItem(String id, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/1001.png
        return buildImageURL(version, "img/item", id + ".png");
    }
    
    
    /**
     * Gets item.
     *
     * @param id      the id
     * @param version the version
     * @return the item
     */
    public String getItem(Item id, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/1001.png
        return buildImageURL(version, "img/item", id.getImage().getFull());
    }
    
    /**
     * Gets mastery.
     *
     * @param id      the id
     * @param version the version
     * @return the mastery
     */
    public String getMastery(StaticMastery id, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/6111.png
        return buildImageURL(version, "img/mastery", id.getImage().getFull());
    }
    
    /**
     * Gets mastery.
     *
     * @param id      the id
     * @param version the version
     * @return the mastery
     */
    public String getMastery(String id, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/6111.png
        return buildImageURL(version, "img/mastery", id + ".png");
    }
    
    
    /**
     * Gets the spritesheet an image is taken from
     *
     * @param sprite  the sprite field from an image object
     * @param version the version
     * @return the spritesheet
     */
    public String getSpriteSheet(String sprite, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/7.8.1/img/sprite/mastery0.png
        return buildImageURL(version, "img/sprite", sprite);
    }
    
    /**
     * Gets rune.
     *
     * @param id      the id
     * @param version the version
     * @return the rune
     */
    public String getRune(String id, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
        return buildImageURL(version, "img/rune", id + ".png");
    }
    
    /**
     * Gets rune.
     *
     * @param rune    the rune
     * @param version the version
     * @return the rune
     */
    public String getRune(StaticRune rune, String version)
    {
        //  https://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
        return buildImageURL(version, "img/rune", rune.getImage().getFull());
    }
    
    
    /**
     * Gets map.
     *
     * @param map     the map
     * @param version the version
     * @return the map
     */
    public String getMap(MapType map, String version)
    {
        // https://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
        return buildImageURL(version, "img/map", "map" + map.getId() + ".png");
    }
    
    /**
     * Gets map.
     *
     * @param map     the map
     * @param version the version
     * @return the map
     */
    public String getMap(String map, String version)
    {
        // https://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
        return buildImageURL(version, "img/map", map + ".png");
    }
}
