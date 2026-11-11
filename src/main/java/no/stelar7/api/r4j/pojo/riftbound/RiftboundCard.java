package no.stelar7.api.r4j.pojo.riftbound;

import java.io.Serializable;
import java.util.*;

public class RiftboundCard implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String             id;
    private long               collectorNumber;
    private String             set;
    private String             name;
    private String             description;
    private String             type;
    private String             rarity;
    private String             faction;
    private RiftboundCardStats stats;
    private RiftboundCardArt   art;
    private String             flavorText;
    private List<String>       tags;
    private String             subtitle;
    private String             effect;

    public String getId()
    {
        return id;
    }
    
    public long getCollectorNumber()
    {
        return collectorNumber;
    }
    
    public String getSet()
    {
        return set;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getRarity()
    {
        return rarity;
    }
    
    public String getFaction()
    {
        return faction;
    }
    
    public RiftboundCardStats getStats()
    {
        return stats;
    }
    
    public RiftboundCardArt getArt()
    {
        return art;
    }
    
    public String getFlavorText()
    {
        return flavorText;
    }
    
    public List<String> getTags()
    {
        return tags;
    }
    
    public String getSubtitle()
    {
        return subtitle;
    }
    
    public String getEffect()
    {
        return effect;
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
        RiftboundCard that = (RiftboundCard) o;
        return Objects.equals(id, that.id) &&
               collectorNumber == that.collectorNumber &&
               Objects.equals(set, that.set) &&
               Objects.equals(name, that.name) &&
               Objects.equals(description, that.description) &&
               Objects.equals(type, that.type) &&
               Objects.equals(rarity, that.rarity) &&
               Objects.equals(faction, that.faction) &&
               Objects.equals(stats, that.stats) &&
               Objects.equals(art, that.art) &&
               Objects.equals(flavorText, that.flavorText) &&
               Objects.equals(tags, that.tags) &&
               Objects.equals(subtitle, that.subtitle) &&
               Objects.equals(effect, that.effect);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, collectorNumber, set, name, description, type, rarity, faction, stats, art, flavorText, tags, subtitle, effect);
    }

    @Override
    public String toString()
    {
        return "RiftboundCard{" +
               "id=" + id +
               ", collectorNumber=" + collectorNumber +
               ", set=" + set +
               ", name=" + name +
               ", description=" + description +
               ", type=" + type +
               ", rarity=" + rarity +
               ", faction=" + faction +
               ", stats=" + stats +
               ", art=" + art +
               ", flavorText=" + flavorText +
               ", tags=" + tags +
               ", subtitle=" + subtitle +
               ", effect=" + effect +
               '}';
    }
}
