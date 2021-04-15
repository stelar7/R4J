package no.stelar7.api.r4j.pojo.shared;

import com.google.gson.JsonObject;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.lor.match.*;
import no.stelar7.api.r4j.pojo.tft.*;

import java.io.Serializable;
import java.util.Objects;

public class GAMHSMatch implements Serializable
{
    private Object metadata;
    private Object info;
    
    public TFTMatch asTFTMatch()
    {
        return Utils.getGson().fromJson(Utils.getGson().toJsonTree(info), TFTMatch.class);
    }
    
    public TFTMetadata asTFTMetadata()
    {
        return Utils.getGson().fromJson(Utils.getGson().toJsonTree(metadata), TFTMetadata.class);
    }
    
    public LORMatch asLORMatch()
    {
        return Utils.getGson().fromJson(Utils.getGson().toJsonTree(info), LORMatch.class);
    }
    
    public LORMetadata asLORMetadata()
    {
        return Utils.getGson().fromJson(Utils.getGson().toJsonTree(metadata), LORMetadata.class);
    }
    
    public LOLMatch asLOLMatch()
    {
        return Utils.getGson().fromJson(Utils.getGson().toJsonTree(info), LOLMatch.class);
    }
    
    public LOLTimeline asLOLTimeline()
    {
        return Utils.getGson().fromJson(Utils.getGson().toJsonTree(info), LOLTimeline.class);
    }
    
    public LOLMetadata asLOLMatchMetadata()
    {
        return Utils.getGson().fromJson(Utils.getGson().toJsonTree(metadata), LOLMetadata.class);
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
