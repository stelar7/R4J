package no.stelar7.api.r4j.pojo.lor.staticdata;

import java.util.Objects;

public class StaticLoRRegion
{
    private String name;
    private String nameRef;
    private String iconAbsolutePath;
    private String abbreviation;
    
    public String getName()
    {
        return name;
    }
    
    public String getNameRef()
    {
        return nameRef;
    }
    
    public String getIconAbsolutePath()
    {
        return iconAbsolutePath;
    }
    
    public String getAbbreviation()
    {
        return abbreviation;
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
        StaticLoRRegion that = (StaticLoRRegion) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(nameRef, that.nameRef) &&
               Objects.equals(iconAbsolutePath, that.iconAbsolutePath) &&
               Objects.equals(abbreviation, that.abbreviation);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, nameRef, iconAbsolutePath, abbreviation);
    }
    
    @Override
    public String toString()
    {
        return "StaticLoRRegion{" +
               "name='" + name + '\'' +
               ", nameRef='" + nameRef + '\'' +
               ", iconAbsolutePath='" + iconAbsolutePath + '\'' +
               ", abbreviation='" + abbreviation + '\'' +
               '}';
    }
}
