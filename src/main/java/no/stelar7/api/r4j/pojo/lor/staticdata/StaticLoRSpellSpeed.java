package no.stelar7.api.r4j.pojo.lor.staticdata;

import java.io.Serializable;
import java.util.Objects;

public class StaticLoRSpellSpeed implements Serializable
{
    private static final long serialVersionUID = -8371965603455715824L;
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
        StaticLoRSpellSpeed that = (StaticLoRSpellSpeed) o;
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
        return "StaticLoRSpellSpeed{" +
               "name='" + name + '\'' +
               ", nameRef='" + nameRef + '\'' +
               '}';
    }
}
