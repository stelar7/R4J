package no.stelar7.api.l4j8.pojo.staticdata.perk;

import java.io.Serializable;

public class StaticPerk implements Serializable
{
    private static final long serialVersionUID = 2415927793878298318L;
    private String runePathName;
    private String runePathId;
    private String name;
    private String key;
    private String shortDesc;
    private String longDesc;
    private String icon;
    private int    id;
    
    public String getRunePathName()
    {
        return runePathName;
    }
    
    public String getRunePathId()
    {
        return runePathId;
    }
    
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
    public String toString()
    {
        return "StaticPerk{" +
               "runePathName='" + runePathName + '\'' +
               ", runePathId='" + runePathId + '\'' +
               ", name='" + name + '\'' +
               ", key='" + key + '\'' +
               ", shortDesc='" + shortDesc + '\'' +
               ", longDesc='" + longDesc + '\'' +
               ", icon='" + icon + '\'' +
               ", id=" + id +
               '}';
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
        
        if (id != that.id)
        {
            return false;
        }
        if ((runePathName != null) ? !runePathName.equals(that.runePathName) : (that.runePathName != null))
        {
            return false;
        }
        if ((runePathId != null) ? !runePathId.equals(that.runePathId) : (that.runePathId != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(that.name) : (that.name != null))
        {
            return false;
        }
        if ((key != null) ? !key.equals(that.key) : (that.key != null))
        {
            return false;
        }
        if ((shortDesc != null) ? !shortDesc.equals(that.shortDesc) : (that.shortDesc != null))
        {
            return false;
        }
        if ((longDesc != null) ? !longDesc.equals(that.longDesc) : (that.longDesc != null))
        {
            return false;
        }
        return (icon != null) ? icon.equals(that.icon) : (that.icon == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = runePathName != null ? runePathName.hashCode() : 0;
        result = 31 * result + (runePathId != null ? runePathId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (shortDesc != null ? shortDesc.hashCode() : 0);
        result = 31 * result + (longDesc != null ? longDesc.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
