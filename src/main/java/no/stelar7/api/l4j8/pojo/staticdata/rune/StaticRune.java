package no.stelar7.api.l4j8.pojo.staticdata.rune;

import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.io.Serializable;
import java.util.*;

public class StaticRune  implements Serializable
{
    private String               plaintext;
    private boolean              hideFromAll;
    private boolean              inStore;
    private List<String>         into;
    private int                  id;
    private RuneStats            stats;
    private String               colloq;
    private Map<String, Boolean> maps;
    private int                  specialRecipe;
    private Image                image;
    private String               description;
    private List<String>         tags;
    private String               requiredChampion;
    private List<String>         from;
    private String               group;
    private boolean              consumeOnFull;
    private String               name;
    private boolean              consumed;
    private String               sanitizedDescription;
    private int                  depth;
    private MetaData             rune;
    private int                  stacks;
    
    
    public String getPlaintext()
    {
        return plaintext;
    }
    
    public boolean getHideFromAll()
    {
        return hideFromAll;
    }
    
    public boolean getInStore()
    {
        return inStore;
    }
    
    public List<String> getInto()
    {
        return Collections.unmodifiableList(into);
    }
    
    public int getId()
    {
        return id;
    }
    
    public RuneStats getStats()
    {
        return stats;
    }
    
    public Map<String, Boolean> getMaps()
    {
        return maps;
    }
    
    public int getSpecialRecipe()
    {
        return specialRecipe;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public List<String> getTags()
    {
        return Collections.unmodifiableList(tags);
    }
    
    public String getRequiredChampion()
    {
        return requiredChampion;
    }
    
    public List<String> getFrom()
    {
        return Collections.unmodifiableList(from);
    }
    
    public String getGroup()
    {
        return group;
    }
    
    public boolean getConsumeOnFull()
    {
        return consumeOnFull;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean getConsumed()
    {
        return consumed;
    }
    
    public String getSanitizedDescription()
    {
        return sanitizedDescription;
    }
    
    public int getDepth()
    {
        return depth;
    }
    
    public MetaData getRune()
    {
        return rune;
    }
    
    public int getStacks()
    {
        return stacks;
    }
    
    public String getColloq()
    {
        return colloq;
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
        
        StaticRune that = (StaticRune) o;
        
        if (hideFromAll != that.hideFromAll)
        {
            return false;
        }
        if (inStore != that.inStore)
        {
            return false;
        }
        if (id != that.id)
        {
            return false;
        }
        if (specialRecipe != that.specialRecipe)
        {
            return false;
        }
        if (consumeOnFull != that.consumeOnFull)
        {
            return false;
        }
        if (consumed != that.consumed)
        {
            return false;
        }
        if (depth != that.depth)
        {
            return false;
        }
        if (stacks != that.stacks)
        {
            return false;
        }
        if ((plaintext != null) ? !plaintext.equals(that.plaintext) : (that.plaintext != null))
        {
            return false;
        }
        if ((into != null) ? !into.equals(that.into) : (that.into != null))
        {
            return false;
        }
        if ((stats != null) ? !stats.equals(that.stats) : (that.stats != null))
        {
            return false;
        }
        if ((colloq != null) ? !colloq.equals(that.colloq) : (that.colloq != null))
        {
            return false;
        }
        if ((maps != null) ? !maps.equals(that.maps) : (that.maps != null))
        {
            return false;
        }
        if ((image != null) ? !image.equals(that.image) : (that.image != null))
        {
            return false;
        }
        if ((description != null) ? !description.equals(that.description) : (that.description != null))
        {
            return false;
        }
        if ((tags != null) ? !tags.equals(that.tags) : (that.tags != null))
        {
            return false;
        }
        if ((requiredChampion != null) ? !requiredChampion.equals(that.requiredChampion) : (that.requiredChampion != null))
        {
            return false;
        }
        if ((from != null) ? !from.equals(that.from) : (that.from != null))
        {
            return false;
        }
        if ((group != null) ? !group.equals(that.group) : (that.group != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(that.name) : (that.name != null))
        {
            return false;
        }
        if ((sanitizedDescription != null) ? !sanitizedDescription.equals(that.sanitizedDescription) : (that.sanitizedDescription != null))
        {
            return false;
        }
        return (rune != null) ? rune.equals(that.rune) : (that.rune == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = plaintext != null ? plaintext.hashCode() : 0;
        result = 31 * result + (hideFromAll ? 1 : 0);
        result = 31 * result + (inStore ? 1 : 0);
        result = 31 * result + (into != null ? into.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        result = 31 * result + (colloq != null ? colloq.hashCode() : 0);
        result = 31 * result + (maps != null ? maps.hashCode() : 0);
        result = 31 * result + specialRecipe;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (requiredChampion != null ? requiredChampion.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (consumeOnFull ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (consumed ? 1 : 0);
        result = 31 * result + (sanitizedDescription != null ? sanitizedDescription.hashCode() : 0);
        result = 31 * result + depth;
        result = 31 * result + (rune != null ? rune.hashCode() : 0);
        result = 31 * result + stacks;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "StaticRune{" +
               "plaintext='" + plaintext + '\'' +
               ", hideFromAll=" + hideFromAll +
               ", inStore=" + inStore +
               ", into=" + into +
               ", id=" + id +
               ", stats=" + stats +
               ", colloq='" + colloq + '\'' +
               ", maps=" + maps +
               ", specialRecipe=" + specialRecipe +
               ", image=" + image +
               ", description='" + description + '\'' +
               ", tags=" + tags +
               ", requiredChampion='" + requiredChampion + '\'' +
               ", from=" + from +
               ", group='" + group + '\'' +
               ", consumeOnFull=" + consumeOnFull +
               ", name='" + name + '\'' +
               ", consumed=" + consumed +
               ", sanitizedDescription='" + sanitizedDescription + '\'' +
               ", depth=" + depth +
               ", rune=" + rune +
               ", stacks=" + stacks +
               '}';
    }
}
