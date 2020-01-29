package no.stelar7.api.r4j.pojo.lor.staticdata;

import java.util.Objects;

public class StaticLoRKeyword
{
    private String description;
    private String name;
    private String nameRef;
    
    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getNameRef()
    {
        return nameRef;
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
        StaticLoRKeyword that = (StaticLoRKeyword) o;
        return Objects.equals(description, that.description) &&
               Objects.equals(name, that.name) &&
               Objects.equals(nameRef, that.nameRef);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(description, name, nameRef);
    }
    
    @Override
    public String toString()
    {
        return "StaticLoRKeyword{" +
               "description='" + description + '\'' +
               ", name='" + name + '\'' +
               ", nameRef='" + nameRef + '\'' +
               '}';
    }
}
