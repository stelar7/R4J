package no.stelar7.api.l4j8.pojo.match;

import com.google.gson.JsonObject;
import no.stelar7.api.l4j8.basic.utils.Utils;
import no.stelar7.api.l4j8.pojo.match.tft.TFTMatch;

import java.io.Serializable;
import java.util.Objects;

public class GAMHSMatch implements Serializable
{
    private Object metadata;
    private Object info;
    
    public TFTMatch toTFTMatch()
    {
        return Utils.getGson().fromJson(Utils.getGson().toJsonTree(info), TFTMatch.class);
    }
    
    public JsonObject getMetadata()
    {
        return Utils.getGson().toJsonTree(metadata).getAsJsonObject();
    }
    
    public JsonObject getInfo()
    {
        return Utils.getGson().toJsonTree(info).getAsJsonObject();
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
               Objects.equals(info, that.info);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(metadata, info);
    }
    
    @Override
    public String toString()
    {
        return "GAMHSMatch{" +
               "metadata='" + metadata + '\'' +
               ", json='" + info + '\'' +
               '}';
    }
}
