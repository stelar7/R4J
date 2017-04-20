package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.rune.StaticRune;

import java.util.Optional;

public final class ImageAPI
{
    private static final ImageAPI INSTANCE = new ImageAPI();
    
    static ImageAPI getInstance()
    {
        return ImageAPI.INSTANCE;
    }
    
    private ImageAPI()
    {
        // Hide public constructor
    }
    
    
    public String getProfileIcon(String iconid, Optional<String> version)
    {
        //http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/588.png
        return null;
    }
    
    public String getProfileIcon(Platform region, String summonerName)
    {
        // http://avatar.leagueoflegends.com/region/summonername.png
        return null;
    }
    
    public String getSplashArt(int championId, int skinNum, Optional<String> version)
    {
        // http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
        return null;
    }
    
    public String getSplashArt(Skin skin, Optional<String> version)
    {
        /* http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
    
        "skins": [
        {
            "id": 266000,
                "name": "default",
                "num": 0
        },
        */
        return null;
    }
    
    public String getLoadingScreenArt(int championId, int skinNum, Optional<String> version)
    {
        /* http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        
        champid-num
        266-001
        "skins": [
        {
                "id": 266001,
                "name": "Justicar Aatrox",
                "num": 1
        },
         */
        return null;
    }
    
    public String getLoadingScreenArt(Skin skin, Optional<String> version)
    {
        /* http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        
        champid-num
        266-001
        "skins": [
        {
                "id": 266001,
                "name": "Justicar Aatrox",
                "num": 1
        },
         */
        return null;
    }
    
    
    public String getSquare(int championId, Optional<String> version)
    {
        // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
        return null;
    }
    
    public String getPassive(int championId, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Cryophoenix_Rebirth.png
            "passive": {
                "name": "Rebirth",
                "description": "Upon dying, Anivia will revert into an egg. If the egg can survive for six seconds, she is gloriously reborn.",
                "sanitizedDescription": "Upon dying, Anivia will revert into an egg. If the egg can survive for six seconds, she is gloriously reborn.",
                "image": {
                    "full": "Anivia_P.png",
                    "sprite": "passive0.png",
                    "group": "passive",
                    "x": 240,
                    "y": 0,
                    "w": 48,
                    "h": 48
                }
            }
        }
         */
        return null;
    }
    
    public String getPassive(Passive passive, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/Cryophoenix_Rebirth.png
            "passive": {
                "name": "Rebirth",
                "description": "Upon dying, Anivia will revert into an egg. If the egg can survive for six seconds, she is gloriously reborn.",
                "sanitizedDescription": "Upon dying, Anivia will revert into an egg. If the egg can survive for six seconds, she is gloriously reborn.",
                "image": {
                    "full": "Anivia_P.png",
                    "sprite": "passive0.png",
                    "group": "passive",
                    "x": 240,
                    "y": 0,
                    "w": 48,
                    "h": 48
                }
            }
        }
         */
        return null;
    }
    
    public String getAbility(int championId, int abilityCount, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
            "spells": [
                {
                    "name": "Flash Frost",
                    "key": "FlashFrost",
                    "image": {
                        "full": "FlashFrost.png",
                        "sprite": "spell0.png",
                        "group": "spell",
                        "x": 192,
                        "y": 144,
                        "w": 48,
                        "h": 48
                    }
                },
         */
        return null;
    }
    
    public String getAbility(StaticChampionSpell spell, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/FlashFrost.png
            "spells": [
                {
                    "name": "Flash Frost",
                    "key": "FlashFrost",
                    "image": {
                        "full": "FlashFrost.png",
                        "sprite": "spell0.png",
                        "group": "spell",
                        "x": 192,
                        "y": 144,
                        "w": 48,
                        "h": 48
                    }
                },
         */
        return null;
    }
    
    public String getSummonerSpell(SummonerSpellType spell, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/SummonerFlash.png
            
         */
        return null;
    }
    
    public String getItem(int id, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/1001.png
         */
        return null;
    }
    
    public String getMastery(int id, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/6111.png
         */
        return null;
    }
    
    public String getRune(String name, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
            
            8001: {
               "name": "Mark of the Crippling Candy Cane",
                "description": "+2% critical damage",
                "image": {
                    "full": "8001.png",
                    "sprite": "rune0.png",
                    "group": "rune",
                    "x": 0,
                    "y": 240,
                    "w": 48,
                    "h": 48
                }
            }
         */
        return null;
    }
    
    public String getRune(StaticRune rune, Optional<String> version)
    {
        /*
            http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/8001.png
            
            8001: {
               "name": "Mark of the Crippling Candy Cane",
                "description": "+2% critical damage",
                "image": {
                    "full": "8001.png",
                    "sprite": "rune0.png",
                    "group": "rune",
                    "x": 0,
                    "y": 240,
                    "w": 48,
                    "h": 48
                }
            }
            
         */
        return null;
    }
    
    
    public String getMap(int id, Optional<String> version)
    {
        /*
          http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
         */
        return null;
    }
    
    public String getMap(MapType map, Optional<String> version)
    {
        /*
           http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png
         */
        return null;
    }
    
    
}
