package no.stelar7.api.r4j.pojo.lor.oauth;

import java.io.Serializable;
import java.util.Objects;

public class LoRRequestDeck implements Serializable
{
    private static final long serialVersionUID = 5606481575576230766L;
    private final String name;
    private final String code;
    
    public LoRRequestDeck(String name, String code)
    {
        this.name = name;
        this.code = code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getCode()
    {
        return code;
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
        LoRRequestDeck that = (LoRRequestDeck) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(code, that.code);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name, code);
    }
    
    @Override
    public String toString()
    {
        return "LoRNameCode{" +
               "name='" + name + '\'' +
               ", code='" + code + '\'' +
               '}';
    }
}
