package no.stelar7.api.r4j.pojo.lol.cdragon;

import java.io.Serializable;
import java.util.*;

public class CDragonChampion implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int                    id;
    private String                 name;
    private String                 alias;
    private String                 title;
    private String                 shortBio;
    private boolean                isVisibleInClient;
    private CDragonTacticalInfo    tacticalInfo;
    private CDragonPlaystyleInfo   playstyleInfo;
    private CDragonChampionTagInfo championTagInfo;
    private String                 squarePortraitPath;
    private String                 stingerSfxPath;
    private String                 chooseVoPath;
    private String                 banVoPath;
    private List<String>           roles;
    private List<CDragonSkin>      skins;
    private CDragonPassive         passive;
    private List<CDragonSpell>     spells;

    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getAlias()
    {
        return alias;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getShortBio()
    {
        return shortBio;
    }
    
    public boolean isVisibleInClient()
    {
        return isVisibleInClient;
    }
    
    public CDragonTacticalInfo getTacticalInfo()
    {
        return tacticalInfo;
    }
    
    public CDragonPlaystyleInfo getPlaystyleInfo()
    {
        return playstyleInfo;
    }
    
    public CDragonChampionTagInfo getChampionTagInfo()
    {
        return championTagInfo;
    }
    
    public String getSquarePortraitPath()
    {
        return squarePortraitPath;
    }
    
    public String getStingerSfxPath()
    {
        return stingerSfxPath;
    }
    
    public String getChooseVoPath()
    {
        return chooseVoPath;
    }
    
    public String getBanVoPath()
    {
        return banVoPath;
    }
    
    public List<String> getRoles()
    {
        return roles;
    }
    
    public List<CDragonSkin> getSkins()
    {
        return skins;
    }
    
    public CDragonPassive getPassive()
    {
        return passive;
    }
    
    public List<CDragonSpell> getSpells()
    {
        return spells;
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
        CDragonChampion that = (CDragonChampion) o;
        return id == that.id &&
               Objects.equals(name, that.name) &&
               Objects.equals(alias, that.alias) &&
               Objects.equals(title, that.title) &&
               Objects.equals(shortBio, that.shortBio) &&
               isVisibleInClient == that.isVisibleInClient &&
               Objects.equals(tacticalInfo, that.tacticalInfo) &&
               Objects.equals(playstyleInfo, that.playstyleInfo) &&
               Objects.equals(championTagInfo, that.championTagInfo) &&
               Objects.equals(squarePortraitPath, that.squarePortraitPath) &&
               Objects.equals(stingerSfxPath, that.stingerSfxPath) &&
               Objects.equals(chooseVoPath, that.chooseVoPath) &&
               Objects.equals(banVoPath, that.banVoPath) &&
               Objects.equals(roles, that.roles) &&
               Objects.equals(skins, that.skins) &&
               Objects.equals(passive, that.passive) &&
               Objects.equals(spells, that.spells);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, alias, title, shortBio, isVisibleInClient, tacticalInfo, playstyleInfo, championTagInfo, squarePortraitPath, stingerSfxPath, chooseVoPath, banVoPath, roles, skins, passive, spells);
    }

    @Override
    public String toString()
    {
        return "CDragonChampion{" +
               "id=" + id +
               ", name=" + name +
               ", alias=" + alias +
               ", title=" + title +
               ", shortBio=" + shortBio +
               ", isVisibleInClient=" + isVisibleInClient +
               ", tacticalInfo=" + tacticalInfo +
               ", playstyleInfo=" + playstyleInfo +
               ", championTagInfo=" + championTagInfo +
               ", squarePortraitPath=" + squarePortraitPath +
               ", stingerSfxPath=" + stingerSfxPath +
               ", chooseVoPath=" + chooseVoPath +
               ", banVoPath=" + banVoPath +
               ", roles=" + roles +
               ", skins=" + skins +
               ", passive=" + passive +
               ", spells=" + spells +
               '}';
    }
}
