package no.stelar7.api.r4j.pojo.val.content;

import java.io.Serializable;
import java.util.*;

public class Content implements Serializable
{
    private static final long serialVersionUID = 4439671460876034863L;
    
    private String version;
    private List<ContentItem> characters;
    private List<ContentItem> maps;
    private List<ContentItem> chromas;
    private List<ContentItem> skins;
    private List<ContentItem> skinLevels;
    private List<ContentItem> equips;
    private List<ContentItem> gameModes;
    private List<ContentItem> sprays;
    private List<ContentItem> sprayLevels;
    private List<ContentItem> charms;
    private List<ContentItem> charmLevels;
    private List<ContentItem> playerCards;
    private List<ContentItem> playerTitles;
    
    public String getVersion()
    {
        return version;
    }
    
    public List<ContentItem> getCharacters()
    {
        return characters;
    }
    
    public List<ContentItem> getMaps()
    {
        return maps;
    }
    
    public List<ContentItem> getChromas()
    {
        return chromas;
    }
    
    public List<ContentItem> getSkins()
    {
        return skins;
    }
    
    public List<ContentItem> getSkinLevels()
    {
        return skinLevels;
    }
    
    public List<ContentItem> getEquips()
    {
        return equips;
    }
    
    public List<ContentItem> getGameModes()
    {
        return gameModes;
    }
    
    public List<ContentItem> getSprays()
    {
        return sprays;
    }
    
    public List<ContentItem> getSprayLevels()
    {
        return sprayLevels;
    }
    
    public List<ContentItem> getCharms()
    {
        return charms;
    }
    
    public List<ContentItem> getCharmLevels()
    {
        return charmLevels;
    }
    
    public List<ContentItem> getPlayerCards()
    {
        return playerCards;
    }
    
    public List<ContentItem> getPlayerTitles()
    {
        return playerTitles;
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
        Content content = (Content) o;
        return Objects.equals(version, content.version) &&
               Objects.equals(characters, content.characters) &&
               Objects.equals(maps, content.maps) &&
               Objects.equals(chromas, content.chromas) &&
               Objects.equals(skins, content.skins) &&
               Objects.equals(skinLevels, content.skinLevels) &&
               Objects.equals(equips, content.equips) &&
               Objects.equals(gameModes, content.gameModes) &&
               Objects.equals(sprays, content.sprays) &&
               Objects.equals(sprayLevels, content.sprayLevels) &&
               Objects.equals(charms, content.charms) &&
               Objects.equals(charmLevels, content.charmLevels) &&
               Objects.equals(playerCards, content.playerCards) &&
               Objects.equals(playerTitles, content.playerTitles);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(version, characters, maps, chromas, skins, skinLevels, equips, gameModes, sprays, sprayLevels, charms, charmLevels, playerCards, playerTitles);
    }
    
    @Override
    public String toString()
    {
        return "Content{" +
               "version='" + version + '\'' +
               ", characters=" + characters +
               ", maps=" + maps +
               ", chromas=" + chromas +
               ", skins=" + skins +
               ", skinLevels=" + skinLevels +
               ", equips=" + equips +
               ", gameModes=" + gameModes +
               ", sprays=" + sprays +
               ", sprayLevels=" + sprayLevels +
               ", charms=" + charms +
               ", charmLevels=" + charmLevels +
               ", playerCards=" + playerCards +
               ", playerTitles=" + playerTitles +
               '}';
    }
}
