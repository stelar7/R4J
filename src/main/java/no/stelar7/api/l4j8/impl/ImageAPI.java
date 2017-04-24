package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.StaticRune;

import java.util.Optional;

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
    
    
    public String getProfileIcon(String iconid, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/profileicon";
        
        //http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/588.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + iconid + ".png";
    }
    
    public String getProfileIcon(Platform region, String summonerName)
    {
        // http://avatar.leagueoflegends.com/region/summonername.png
        return "http://avatar.leagueoflegends.com/" + region.toString() + Constants.SEPARATOR + summonerName + ".png";
    }
    
    public String getSplashArt(String championId, int skinNum)
    {
        Realm  realm = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn   = realm.getCDN();
        String path  = "img/champion/splash";
        
        // http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return cdn + Constants.SEPARATOR + path + Constants.SEPARATOR + championId + "_" + skinNum + ".png";
    }
    
    public String getSplashArt(Skin skin)
    {
        Realm  realm = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn   = realm.getCDN();
        String path  = "img/champion/splash";
        
        // http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return cdn + Constants.SEPARATOR + path + Constants.SEPARATOR + skin.getId() + "_" + skin.getNum() + ".png";
    }
    
    public String getLoadingScreenArt(String championId, int skinNum)
    {
        Realm  realm = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn   = realm.getCDN();
        String path  = "img/champion/loading";
        
        // http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        return cdn + Constants.SEPARATOR + path + Constants.SEPARATOR + championId + "_" + skinNum + ".png";
    }
    
    public String getLoadingScreenArt(Skin skin)
    {
        Realm  realm = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn   = realm.getCDN();
        String path  = "img/champion/loading";
        
        // http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        return cdn + Constants.SEPARATOR + path + Constants.SEPARATOR + skin.getId() + "_" + skin.getNum() + ".png";
    }
    
    
    public String getSquare(String championId, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/champion";
        
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + championId + ".png";
    }
    
    public String getPassive(String passiveId, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/passive";
        
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Anivia_P.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + passiveId + ".png";
    }
    
    public String getPassive(Passive passive, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/passive";
        
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Anivia_P.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + passive.getImage().getFull();
    }
    
    public String getAbility(String abilityId, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/spell";
        
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + abilityId + ".png";
    }
    
    public String getAbility(StaticChampionSpell spell, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/spell";
        
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + spell.getImage().getFull();
    }
    
    public String getSummonerSpell(SummonerSpellType spell, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/spell";
        
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + spell.getApiName() + ".png";
    }
    
    public String getItem(String id, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/item";
        
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/1001.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + id + ".png";
    }
    
    public String getMastery(String id, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/mastery";
        
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/6111.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + id + ".png";
    }
    
    public String getRune(String id, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/rune";
        
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + id + ".png";
    }
    
    public String getRune(StaticRune rune, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/rune";
        
        //  http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + rune.getImage().getFull();
    }
    
    
    public String getMap(MapType map, Optional<String> version)
    {
        Realm  realm         = StaticAPI.getInstance().getRealm(Platform.EUW1);
        String cdn           = realm.getCDN();
        String versionString = version.orElseGet(realm::getV);
        String path          = "img/map";
        
        // http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
        return cdn + Constants.SEPARATOR + versionString + Constants.SEPARATOR + path + Constants.SEPARATOR + "map" + map.getId() + ".png";
    }
    
    
}
