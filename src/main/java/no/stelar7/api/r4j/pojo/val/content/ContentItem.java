package no.stelar7.api.r4j.pojo.val.content;

import java.io.Serializable;
import java.util.*;

public class ContentItem implements Serializable
{
    private static final long serialVersionUID = 1462032373801535374L;
    
    private String              id;
    private String              name;
    private Map<String, String> localizedNames;
    private String              assetName;
    private String              assetPath;
    
    public String getId()
    {
        return id;
    }
    
    public String getAssetPath()
    {
        return assetPath;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Map<String, String> getLocalizedNames()
    {
        return localizedNames;
    }
    
    public String getAssetName()
    {
        return assetName;
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
        ContentItem that = (ContentItem) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(localizedNames, that.localizedNames) &&
               Objects.equals(assetName, that.assetName) &&
               Objects.equals(assetPath, that.assetPath);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, localizedNames, assetName, assetPath);
    }
    
    @Override
    public String toString()
    {
        return "ContentItem{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", localizedNames=" + localizedNames +
               ", assetName='" + assetName + '\'' +
               ", assetPath='" + assetPath + '\'' +
               '}';
    }
}
