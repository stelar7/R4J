package no.stelar7.api.l4j8.pojo.staticdata.perk;

import java.io.Serializable;
import java.util.Objects;

public class StaticPerk implements Serializable
{
    private static final long serialVersionUID = 2415927793878298318L;
    private String name;
    private String key;
    private String shortDesc;
    private String longDesc;
    private String icon;
    private int    id;
    
    
    public String getName()
    {
        return name;
    }
    
    public String getKey()
    {
        return key;
    }
    
    public String getShortDesc()
    {
        return shortDesc;
    }
    
    public String getLongDesc()
    {
        return longDesc;
    }
    
    public String getIcon()
    {
        return icon;
    }
    
    public int getId()
    {
        return id;
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
        StaticPerk that = (StaticPerk) o;
        return id == that.id &&
               Objects.equals(name, that.name) &&
               Objects.equals(key, that.key) &&
               Objects.equals(shortDesc, that.shortDesc) &&
               Objects.equals(longDesc, that.longDesc) &&
               Objects.equals(icon, that.icon);
    }
    
    @Override
    public int hashCode()
    {
        
        return Objects.hash(name, key, shortDesc, longDesc, icon, id);
    }
    
    @Override
    public String toString()
    {
        return "StaticPerk{" +
               "name='" + name + '\'' +
               ", key='" + key + '\'' +
               ", shortDesc='" + shortDesc + '\'' +
               ", longDesc='" + longDesc + '\'' +
               ", icon='" + icon + '\'' +
               ", id=" + id +
               '}';
    }
}
