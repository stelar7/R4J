package no.stelar7.api.l4j8.pojo.staticdata.rune;

import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.util.*;

public class StaticRune
{
    private String               plaintext;
    private Boolean              hideFromAll;
    private Boolean              inStore;
    private List<String>         into;
    private Integer              id;
    private BasicDataStats       stats;
    private String               colloq;
    private Map<String, Boolean> maps;
    private Integer              specialRecipe;
    private Image                image;
    private String               description;
    private List<String>         tags;
    private String               requiredChampion;
    private List<String>         from;
    private String               group;
    private Boolean              consumeOnFull;
    private String               name;
    private Boolean              consumed;
    private String               sanitizedDescription;
    private Integer              depth;
    private MetaData             rune;
    private Integer              stacks;
    
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
        
        StaticRune rune1 = (StaticRune) o;
        
        if ((plaintext != null) ? !plaintext.equals(rune1.plaintext) : (rune1.plaintext != null))
        {
            return false;
        }
        if ((hideFromAll != null) ? !hideFromAll.equals(rune1.hideFromAll) : (rune1.hideFromAll != null))
        {
            return false;
        }
        if ((inStore != null) ? !inStore.equals(rune1.inStore) : (rune1.inStore != null))
        {
            return false;
        }
        if ((into != null) ? !into.equals(rune1.into) : (rune1.into != null))
        {
            return false;
        }
        if ((id != null) ? !id.equals(rune1.id) : (rune1.id != null))
        {
            return false;
        }
        if ((stats != null) ? !stats.equals(rune1.stats) : (rune1.stats != null))
        {
            return false;
        }
        if ((colloq != null) ? !colloq.equals(rune1.colloq) : (rune1.colloq != null))
        {
            return false;
        }
        if ((maps != null) ? !maps.equals(rune1.maps) : (rune1.maps != null))
        {
            return false;
        }
        if ((specialRecipe != null) ? !specialRecipe.equals(rune1.specialRecipe) : (rune1.specialRecipe != null))
        {
            return false;
        }
        if ((image != null) ? !image.equals(rune1.image) : (rune1.image != null))
        {
            return false;
        }
        if ((description != null) ? !description.equals(rune1.description) : (rune1.description != null))
        {
            return false;
        }
        if ((tags != null) ? !tags.equals(rune1.tags) : (rune1.tags != null))
        {
            return false;
        }
        if ((requiredChampion != null) ? !requiredChampion.equals(rune1.requiredChampion) : (rune1.requiredChampion != null))
        {
            return false;
        }
        if ((from != null) ? !from.equals(rune1.from) : (rune1.from != null))
        {
            return false;
        }
        if ((group != null) ? !group.equals(rune1.group) : (rune1.group != null))
        {
            return false;
        }
        if ((consumeOnFull != null) ? !consumeOnFull.equals(rune1.consumeOnFull) : (rune1.consumeOnFull != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(rune1.name) : (rune1.name != null))
        {
            return false;
        }
        if ((consumed != null) ? !consumed.equals(rune1.consumed) : (rune1.consumed != null))
        {
            return false;
        }
        if ((sanitizedDescription != null) ? !sanitizedDescription.equals(rune1.sanitizedDescription) : (rune1.sanitizedDescription != null))
        {
            return false;
        }
        if ((depth != null) ? !depth.equals(rune1.depth) : (rune1.depth != null))
        {
            return false;
        }
        if ((rune != null) ? !rune.equals(rune1.rune) : (rune1.rune != null))
        {
            return false;
        }
        return (stacks != null) ? stacks.equals(rune1.stacks) : (rune1.stacks == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = plaintext != null ? plaintext.hashCode() : 0;
        result = 31 * result + (hideFromAll != null ? hideFromAll.hashCode() : 0);
        result = 31 * result + (inStore != null ? inStore.hashCode() : 0);
        result = 31 * result + (into != null ? into.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        result = 31 * result + (colloq != null ? colloq.hashCode() : 0);
        result = 31 * result + (maps != null ? maps.hashCode() : 0);
        result = 31 * result + (specialRecipe != null ? specialRecipe.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (requiredChampion != null ? requiredChampion.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (consumeOnFull != null ? consumeOnFull.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (consumed != null ? consumed.hashCode() : 0);
        result = 31 * result + (sanitizedDescription != null ? sanitizedDescription.hashCode() : 0);
        result = 31 * result + (depth != null ? depth.hashCode() : 0);
        result = 31 * result + (rune != null ? rune.hashCode() : 0);
        result = 31 * result + (stacks != null ? stacks.hashCode() : 0);
        return result;
    }
    
    public String getPlaintext()
    {
        
        return plaintext;
    }
    
    public Boolean getHideFromAll()
    {
        return hideFromAll;
    }
    
    public Boolean getInStore()
    {
        return inStore;
    }
    
    public List<String> getInto()
    {
        return Collections.unmodifiableList(into);
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public BasicDataStats getStats()
    {
        return stats;
    }
    
    public Map<String, Boolean> getMaps()
    {
        return maps;
    }
    
    public Integer getSpecialRecipe()
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
    
    public Boolean getConsumeOnFull()
    {
        return consumeOnFull;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Boolean getConsumed()
    {
        return consumed;
    }
    
    public String getSanitizedDescription()
    {
        return sanitizedDescription;
    }
    
    public Integer getDepth()
    {
        return depth;
    }
    
    public MetaData getRune()
    {
        return rune;
    }
    
    public Integer getStacks()
    {
        return stacks;
    }
    
    public String getColloq()
    {
        
        return colloq;
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
