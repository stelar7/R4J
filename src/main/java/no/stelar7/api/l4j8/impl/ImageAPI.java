package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.Item;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.StaticMastery;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.StaticRune;

import javax.annotation.Nullable;

public final class ImageAPI
{
    private static final ImageAPI INSTANCE = new ImageAPI();
    
    public static ImageAPI getInstance()
    {
        return ImageAPI.INSTANCE;
    }
    
    private ImageAPI()
    {
        // Hide public constructor
    }
    
    
    private String buildImageURL(@Nullable String version, String path, String file, @Nullable Platform rreg)
    {
        Platform region = rreg;
        if (region == null)
        {
            region = Platform.EUW1;
        }
        
        Realm  realm         = StaticAPI.getInstance().getRealm(region);
        String cdn           = realm.getCDN();
        String versionString = version != null ? version : realm.getV();
        
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + file;
    }
    
    
    /**
     * Gets profile icon.
     *
     * @param iconid  the iconid
     * @param version the version
     * @return the profile icon
     */
    public String getProfileIcon(String iconid, @Nullable String version, @Nullable Platform region)
    {
        //http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/588.png
        return buildImageURL(version, "img/profileicon", iconid + ".png", region);
    }
    
    /**
     * Gets profile icon.
     *
     * @param region       the region
     * @param summonerName the summoner name
     * @return the profile icon
     */
    public String getProfileIcon(Platform region, String summonerName)
    {
        // http://avatar.leagueoflegends.com/region/summonername.png
        return "http://avatar.leagueoflegends.com/" + region.toString() + Constants.SEPARATOR + summonerName + ".png";
    }
    
    /**
     * Gets splash art.
     *
     * @param championId the champion id
     * @param skinNum    the skin num
     * @return the splash art
     */
    public String getSplashArt(String championId, int skinNum, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return buildImageURL(null, "img/champion/splash", championId + "_" + skinNum + ".png", region);
    }
    
    /**
     * Gets splash art.
     *
     * @param skin the skin
     * @return the splash art
     */
    public String getSplashArt(Skin skin, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return buildImageURL(null, "img/champion/splash", skin.getId() + "_" + skin.getNum() + ".png", region);
    }
    
    /**
     * Gets loading screen art.
     *
     * @param championId the champion id
     * @param skinNum    the skin num
     * @return the loading screen art
     */
    public String getLoadingScreenArt(String championId, int skinNum, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        return buildImageURL(null, "img/champion/loading", championId + "_" + skinNum + ".png", region);
    }
    
    /**
     * Gets loading screen art.
     *
     * @param skin the skin
     * @return the loading screen art
     */
    public String getLoadingScreenArt(Skin skin, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        return buildImageURL(null, "img/champion/loading", skin.getId() + "_" + skin.getNum() + ".png", region);
    }
    
    
    /**
     * Gets square.
     *
     * @param champ   the champ
     * @param version the version
     * @return the square
     */
    public String getSquare(StaticChampion champ, @Nullable String version, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
        return buildImageURL(version, "img/champion", champ.getImage().getFull(), region);
    }
    
    /**
     * Gets square.
     *
     * @param champKey the champ key
     * @param version  the version
     * @return the square
     */
    public String getSquare(String champKey, @Nullable String version, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
        return buildImageURL(version, "img/champion", champKey + ".png", region);
    }
    
    /**
     * Gets passive.
     *
     * @param passiveId the passive id
     * @param version   the version
     * @return the passive
     */
    public String getPassive(String passiveId, @Nullable String version, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Anivia_P.png
        return buildImageURL(version, "img/passive", passiveId + ".png", region);
    }
    
    /**
     * Gets passive.
     *
     * @param passive the passive
     * @param version the version
     * @return the passive
     */
    public String getPassive(Passive passive, @Nullable String version, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Anivia_P.png
        return buildImageURL(version, "img/passive", passive.getImage().getFull(), region);
    }
    
    /**
     * Gets ability.
     *
     * @param spell   the spell
     * @param version the version
     * @return the ability
     */
    public String getAbility(StaticChampionSpell spell, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
        return buildImageURL(version, "img/spell", spell.getImage().getFull(), region);
    }
    
    /**
     * Gets summoner spell.
     *
     * @param spell   the spell
     * @param version the version
     * @return the summoner spell
     */
    public String getSummonerSpell(SummonerSpellType spell, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
        return buildImageURL(version, "img/spell", spell.getApiName() + ".png", region);
    }
    
    /**
     * Gets summoner spell.
     *
     * @param spell   the spell
     * @param version the version
     * @return the summoner spell
     */
    public String getSummonerSpell(String spell, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
        return buildImageURL(version, "img/spell", spell + ".png", region);
    }
    
    /**
     * Gets item.
     *
     * @param id      the id
     * @param version the version
     * @return the item
     */
    public String getItem(String id, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/1001.png
        return buildImageURL(version, "img/item", id + ".png", region);
    }
    
    
    /**
     * Gets item.
     *
     * @param id      the id
     * @param version the version
     * @return the item
     */
    public String getItem(Item id, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/1001.png
        return buildImageURL(version, "img/item", id.getImage().getFull(), region);
    }
    
    /**
     * Gets mastery.
     *
     * @param id      the id
     * @param version the version
     * @return the mastery
     */
    public String getMastery(StaticMastery id, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/6111.png
        return buildImageURL(version, "img/mastery", id.getImage().getFull(), region);
    }
    
    /**
     * Gets mastery.
     *
     * @param id      the id
     * @param version the version
     * @return the mastery
     */
    public String getMastery(String id, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/6111.png
        return buildImageURL(version, "img/mastery", id + ".png", region);
    }
    
    
    /**
     * Gets the spritesheet an image is taken from
     *
     * @param sprite  the sprite field from an image object
     * @param version the version
     * @return the spritesheet
     */
    public String getSpriteSheet(String sprite, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/7.8.1/img/sprite/mastery0.png
        return buildImageURL(version, "img/sprite", sprite, region);
    }
    
    /**
     * Gets rune.
     *
     * @param id      the id
     * @param version the version
     * @return the rune
     */
    public String getRune(String id, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
        return buildImageURL(version, "img/rune", id + ".png", region);
    }
    
    /**
     * Gets rune.
     *
     * @param rune    the rune
     * @param version the version
     * @return the rune
     */
    public String getRune(StaticRune rune, @Nullable String version, @Nullable Platform region)
    {
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
        return buildImageURL(version, "img/rune", rune.getImage().getFull(), region);
    }
    
    
    /**
     * Gets map.
     *
     * @param map     the map
     * @param version the version
     * @return the map
     */
    public String getMap(MapType map, @Nullable String version, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
        return buildImageURL(version, "img/map", map.getId() + ".png", region);
    }
    
    /**
     * Gets map.
     *
     * @param map     the map
     * @param version the version
     * @return the map
     */
    public String getMap(String map, @Nullable String version, @Nullable Platform region)
    {
        // http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
        return buildImageURL(version, "img/map", map + ".png", region);
    }
}
