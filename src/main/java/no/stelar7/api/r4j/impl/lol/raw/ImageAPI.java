package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.realm.Realm;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.item.Item;
import no.stelar7.api.r4j.pojo.lol.staticdata.mastery.StaticMastery;
import no.stelar7.api.r4j.pojo.lol.staticdata.rune.StaticRune;
import no.stelar7.api.r4j.pojo.lol.staticdata.summonerspell.StaticSummonerSpell;

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
    
    
    private String buildImageURL(String version, String path, String file)
    {
        Realm  realm         = DDragonAPI.getInstance().getRealm();
        String cdn           = realm.getCDN();
        String versionString = version != null ? version : realm.getV();
        
        String preReplace = cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + file;
        return preReplace.replace(" ", "%20");
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
        //http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/588.png
        return buildImageURL(version, "img/profileicon", iconid + ".png");
    }
    
    /**
     * Gets profile icon.
     *
     * @param region       the region
     * @param summonerName the summoner name
     * @return the profile icon
     */
    public String getProfileIcon(LeagueShard region, String summonerName)
    {
        // http://avatar.leagueoflegends.com/region/summonername.png
        return "http://avatar.leagueoflegends.com/" + region.toString() + Constants.SEPARATOR + summonerName.replace(" ", "%20") + ".png";
    }
    
    /**
     * Gets splash art.
     *
     * @param championId the champion id
     * @param skinNum    the skin num
     * @return the splash art
     */
    public String getSplashArt(int championId, int skinNum)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return buildImageURL(null, "img/champion/splash", championId + "_" + skinNum + ".png");
    }
    
    /**
     * Gets splash art.
     *
     * @param skin the skin
     * @return the splash art
     */
    public String getSplashArt(Skin skin)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return buildImageURL(null, "img/champion/splash", skin.getId() + "_" + skin.getNum() + ".png");
    }
    
    /**
     * Gets loading screen art.
     *
     * @param championId the champion id
     * @param skinNum    the skin num
     * @return the loading screen art
     */
    public String getLoadingScreenArt(String championId, int skinNum)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        return buildImageURL(null, "img/champion/loading", championId + "_" + skinNum + ".png");
    }
    
    /**
     * Gets loading screen art.
     *
     * @param skin the skin
     * @return the loading screen art
     */
    public String getLoadingScreenArt(Skin skin)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        return buildImageURL(null, "img/champion/loading", skin.getId() + "_" + skin.getNum() + ".png");
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
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
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
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
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
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Anivia_P.png
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
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Anivia_P.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/1001.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/1001.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/6111.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/6111.png
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
        //  http://ddragon.leagueoflegends.com/cdn/7.8.1/img/sprite/mastery0.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
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
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
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
        // http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
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
        // http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
        return buildImageURL(version, "img/map", map + ".png");
    }
}
