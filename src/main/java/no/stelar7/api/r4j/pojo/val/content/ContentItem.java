package no.stelar7.api.r4j.pojo.val.content;

import java.io.Serializable;
import java.util.*;

public class ContentItem implements Serializable
{
    private static final long serialVersionUID = 1462032373801535374L;
    
    private String name;
    private Map<String, String> localizedNames;
    private String              assetName;
    
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
        return Objects.equals(name, that.name) &&
               Objects.equals(localizedNames, that.localizedNames) &&
               Objects.equals(assetName, that.assetName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, localizedNames, assetName);
    }
    
    @Override
    public String toString()
    {
        return "ContentItem{" +
               "name='" + name + '\'' +
               ", localizedNames=" + localizedNames +
               ", assetName='" + assetName + '\'' +
               '}';
    }
}
