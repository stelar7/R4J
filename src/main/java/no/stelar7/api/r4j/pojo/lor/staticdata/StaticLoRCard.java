package no.stelar7.api.r4j.pojo.lor.staticdata;

import java.io.Serializable;
import java.util.*;

public class StaticLoRCard implements Serializable
{
    private static final long serialVersionUID = 2237963538719986242L;
    private List<String> associatedCards;
    private List<String>             associatedCardRefs;
    private List<StaticLoRCardAsset> assets;
    private String                   region;
    private String                   regionRef;
    private int                      attack;
    private int                      cost;
    private int                      health;
    private String                   description;
    private String                   descriptionRaw;
    private String                   levelupDescription;
    private String                   levelupDescriptionRaw;
    private String                   flavorText;
    private String                   artistName;
    private String                   name;
    private String                   cardCode;
    private List<String>             keywords;
    private List<String>             keywordRefs;
    private String                   spellSpeed;
    private String                   spellSpeedRef;
    private String                   rarity;
    private String                   rarityRef;
    private String                   subtype;
    private String                   supertype;
    private String                   type;
    private boolean                  collectible;
    
    public List<String> getAssociatedCards()
    {
        return associatedCards;
    }
    
    public List<String> getAssociatedCardRefs()
    {
        return associatedCardRefs;
    }
    
    public List<StaticLoRCardAsset> getAssets()
    {
        return assets;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public String getRegionRef()
    {
        return regionRef;
    }
    
    public int getAttack()
    {
        return attack;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getDescriptionRaw()
    {
        return descriptionRaw;
    }
    
    public String getLevelupDescription()
    {
        return levelupDescription;
    }
    
    public String getLevelupDescriptionRaw()
    {
        return levelupDescriptionRaw;
    }
    
    public String getFlavorText()
    {
        return flavorText;
    }
    
    public String getArtistName()
    {
        return artistName;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getCardCode()
    {
        return cardCode;
    }
    
    public List<String> getKeywords()
    {
        return keywords;
    }
    
    public List<String> getKeywordRefs()
    {
        return keywordRefs;
    }
    
    public String getSpellSpeed()
    {
        return spellSpeed;
    }
    
    public String getSpellSpeedRef()
    {
        return spellSpeedRef;
    }
    
    public String getRarity()
    {
        return rarity;
    }
    
    public String getRarityRef()
    {
        return rarityRef;
    }
    
    public String getSubtype()
    {
        return subtype;
    }
    
    public String getSupertype()
    {
        return supertype;
    }
    
    public String getType()
    {
        return type;
    }
    
    public boolean isCollectible()
    {
        return collectible;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        StaticLoRCard that = (StaticLoRCard) o;
        return attack == that.attack &&
               cost == that.cost &&
               health == that.health &&
               collectible == that.collectible &&
               Objects.equals(associatedCards, that.associatedCards) &&
               Objects.equals(associatedCardRefs, that.associatedCardRefs) &&
               Objects.equals(assets, that.assets) &&
               Objects.equals(region, that.region) &&
               Objects.equals(regionRef, that.regionRef) &&
               Objects.equals(description, that.description) &&
               Objects.equals(descriptionRaw, that.descriptionRaw) &&
               Objects.equals(levelupDescription, that.levelupDescription) &&
               Objects.equals(levelupDescriptionRaw, that.levelupDescriptionRaw) &&
               Objects.equals(flavorText, that.flavorText) &&
               Objects.equals(artistName, that.artistName) &&
               Objects.equals(name, that.name) &&
               Objects.equals(cardCode, that.cardCode) &&
               Objects.equals(keywords, that.keywords) &&
               Objects.equals(keywordRefs, that.keywordRefs) &&
               Objects.equals(spellSpeed, that.spellSpeed) &&
               Objects.equals(spellSpeedRef, that.spellSpeedRef) &&
               Objects.equals(rarity, that.rarity) &&
               Objects.equals(rarityRef, that.rarityRef) &&
               Objects.equals(subtype, that.subtype) &&
               Objects.equals(supertype, that.supertype) &&
               Objects.equals(type, that.type);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(associatedCards, associatedCardRefs, assets, region, regionRef, attack, cost, health, description, descriptionRaw, levelupDescription, levelupDescriptionRaw, flavorText, artistName, name, cardCode, keywords, keywordRefs, spellSpeed, spellSpeedRef, rarity, rarityRef, subtype, supertype, type, collectible);
    }
    
    @Override
    public String toString()
    {
        return "StaticLoRCard{" +
               "associatedCards=" + associatedCards +
               ", associatedCardRefs=" + associatedCardRefs +
               ", assets=" + assets +
               ", region='" + region + '\'' +
               ", regionRef='" + regionRef + '\'' +
               ", attack=" + attack +
               ", cost=" + cost +
               ", health=" + health +
               ", description='" + description + '\'' +
               ", descriptionRaw='" + descriptionRaw + '\'' +
               ", levelupDescription='" + levelupDescription + '\'' +
               ", levelupDescriptionRaw='" + levelupDescriptionRaw + '\'' +
               ", flavorText='" + flavorText + '\'' +
               ", artistName='" + artistName + '\'' +
               ", name='" + name + '\'' +
               ", cardCode='" + cardCode + '\'' +
               ", keywords=" + keywords +
               ", keywordRefs=" + keywordRefs +
               ", spellSpeed='" + spellSpeed + '\'' +
               ", spellSpeedRef='" + spellSpeedRef + '\'' +
               ", rarity='" + rarity + '\'' +
               ", rarityRef='" + rarityRef + '\'' +
               ", subtype='" + subtype + '\'' +
               ", supertype='" + supertype + '\'' +
               ", type='" + type + '\'' +
               ", collectible=" + collectible +
               '}';
    }
}
