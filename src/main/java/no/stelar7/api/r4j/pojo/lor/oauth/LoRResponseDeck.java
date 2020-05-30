package no.stelar7.api.r4j.pojo.lor.oauth;

import no.stelar7.api.r4j.impl.lor.LoRDeckCode;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRDeck;

import java.util.Objects;

public class LoRResponseDeck
{
    private final String id;
    private final String name;
    private final String code;
    
    public LoRResponseDeck(String id, String name, String code)
    {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public LoRDeck toDeck() {
        return LoRDeckCode.decode(this.code);
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
        LoRResponseDeck that = (LoRResponseDeck) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(code, that.code);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, code);
    }
    
    @Override
    public String toString()
    {
        return "LoRResponseDeck{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", code='" + code + '\'' +
               '}';
    }
}
