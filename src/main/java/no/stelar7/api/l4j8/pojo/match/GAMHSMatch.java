package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.utils.Utils;

import java.io.Serializable;
import java.util.Objects;

public class GAMHSMatch implements Serializable
{
    private String metadata;
    private String json;
    
    public TFTMatch toTFTMatch()
    {
        return Utils.getGson().fromJson(json, TFTMatch.class);
    }
    
    public String getMetadata()
    {
        return metadata;
    }
    
    public String getJson()
    {
        return json;
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
        GAMHSMatch that = (GAMHSMatch) o;
        return Objects.equals(metadata, that.metadata) &&
               Objects.equals(json, that.json);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(metadata, json);
    }
    
    @Override
    public String toString()
    {
        return "GAMHSMatch{" +
               "metadata='" + metadata + '\'' +
               ", json='" + json + '\'' +
               '}';
    }
}
