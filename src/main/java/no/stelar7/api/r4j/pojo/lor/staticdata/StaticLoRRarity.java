package no.stelar7.api.r4j.pojo.lor.staticdata;

import java.io.Serializable;
import java.util.Objects;

public class StaticLoRRarity implements Serializable
{
    private static final long serialVersionUID = -1068562072206539798L;
    private String name;
    private String nameRef;
    
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
        StaticLoRRarity that = (StaticLoRRarity) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(nameRef, that.nameRef);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, nameRef);
    }
    
    @Override
    public String toString()
    {
        return "StaticLoRRarity{" +
               "name='" + name + '\'' +
               ", nameRef='" + nameRef + '\'' +
               '}';
    }
}
