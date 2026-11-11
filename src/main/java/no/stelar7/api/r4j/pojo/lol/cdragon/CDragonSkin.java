package no.stelar7.api.r4j.pojo.lol.cdragon;

import java.io.Serializable;
import java.util.*;

public class CDragonSkin implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int     id;
    private String  name;
    private boolean isBase;
    private String  skinClassification;
    private String  splashPath;
    private String  uncenteredSplashPath;
    private String  tilePath;
    private String  loadScreenPath;
    private String  skinType;
    private String  rarity;
    private boolean isLegacy;

    public int getId()
    {
        return id;
    }
    
    /**
     * @return the number of the skin within its champion (0 for the base skin), which is what the asset urls of CDragonAPI take
     */
    public int getNum()
    {
        return id % 1000;
    }

    public String getName()
    {
        return name;
    }
    
    public boolean isBase()
    {
        return isBase;
    }
    
    public String getSkinClassification()
    {
        return skinClassification;
    }
    
    public String getSplashPath()
    {
        return splashPath;
    }
    
    public String getUncenteredSplashPath()
    {
        return uncenteredSplashPath;
    }
    
    public String getTilePath()
    {
        return tilePath;
    }
    
    public String getLoadScreenPath()
    {
        return loadScreenPath;
    }
    
    public String getSkinType()
    {
        return skinType;
    }
    
    public String getRarity()
    {
        return rarity;
    }
    
    public boolean isLegacy()
    {
        return isLegacy;
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
        CDragonSkin that = (CDragonSkin) o;
        return id == that.id &&
               Objects.equals(name, that.name) &&
               isBase == that.isBase &&
               Objects.equals(skinClassification, that.skinClassification) &&
               Objects.equals(splashPath, that.splashPath) &&
               Objects.equals(uncenteredSplashPath, that.uncenteredSplashPath) &&
               Objects.equals(tilePath, that.tilePath) &&
               Objects.equals(loadScreenPath, that.loadScreenPath) &&
               Objects.equals(skinType, that.skinType) &&
               Objects.equals(rarity, that.rarity) &&
               isLegacy == that.isLegacy;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, isBase, skinClassification, splashPath, uncenteredSplashPath, tilePath, loadScreenPath, skinType, rarity, isLegacy);
    }

    @Override
    public String toString()
    {
        return "CDragonSkin{" +
               "id=" + id +
               ", name=" + name +
               ", isBase=" + isBase +
               ", skinClassification=" + skinClassification +
               ", splashPath=" + splashPath +
               ", uncenteredSplashPath=" + uncenteredSplashPath +
               ", tilePath=" + tilePath +
               ", loadScreenPath=" + loadScreenPath +
               ", skinType=" + skinType +
               ", rarity=" + rarity +
               ", isLegacy=" + isLegacy +
               '}';
    }
}
