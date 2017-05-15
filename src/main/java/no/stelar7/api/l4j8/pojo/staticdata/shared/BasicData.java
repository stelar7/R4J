package no.stelar7.api.l4j8.pojo.staticdata.shared;

import java.util.*;

public class BasicData
{
    private String               colloq;
    private boolean              consumeOnFull;
    private boolean              consumed;
    private int                  depth;
    private String               description;
    private List<String>         from;
    private Gold                 gold;
    private String               group;
    private boolean              hideFromAll;
    private int                  id;
    private Image                image;
    private boolean              inStore;
    private List<String>         into;
    private Map<String, Boolean> maps;
    private String               name;
    private String               plaintext;
    private String               requiredChampion;
    private MetaData             rune;
    private String               sanitizedDescription;
    private int                  specialRecipe;
    private int                  stacks;
    private BasicDataStats       stats;
    private List<String>         tags;
    
    
    /**
     * Gets the colloq.
     *
     * @return the colloq
     */
    public String getColloq()
    {
        return this.colloq;
    }
    
    /**
     * Gets the consumed.
     *
     * @return the consumed
     */
    public boolean getConsumed()
    {
        return this.consumed;
    }
    
    /**
     * Gets the consume on full.
     *
     * @return the consume on full
     */
    public boolean getConsumeOnFull()
    {
        return this.consumeOnFull;
    }
    
    /**
     * Gets the depth.
     *
     * @return the depth
     */
    public int getDepth()
    {
        return this.depth;
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * Gets the from.
     *
     * @return the from
     */
    public List<String> getFrom()
    {
        return this.from;
    }
    
    /**
     * Gets the gold.
     *
     * @return the gold
     */
    public Gold getGold()
    {
        return this.gold;
    }
    
    /**
     * Gets the group.
     *
     * @return the group
     */
    public String getGroup()
    {
        return this.group;
    }
    
    /**
     * Gets the hide from all.
     *
     * @return the hide from all
     */
    public boolean getHideFromAll()
    {
        return this.hideFromAll;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return this.image;
    }
    
    /**
     * Gets the in store.
     *
     * @return the in store
     */
    public boolean getInStore()
    {
        return this.inStore;
    }
    
    /**
     * Gets the into.
     *
     * @return the into
     */
    public List<String> getInto()
    {
        return this.into;
    }
    
    /**
     * Gets the maps.
     *
     * @return the maps
     */
    public Map<String, Boolean> getMaps()
    {
        return this.maps;
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the plaintext.
     *
     * @return the plaintext
     */
    public String getPlaintext()
    {
        return this.plaintext;
    }
    
    /**
     * Gets the required champion.
     *
     * @return the required champion
     */
    public String getRequiredChampion()
    {
        return this.requiredChampion;
    }
    
    /**
     * Gets the rune.
     *
     * @return the rune
     */
    public MetaData getRune()
    {
        return this.rune;
    }
    
    /**
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public String getSanitizedDescription()
    {
        return this.sanitizedDescription;
    }
    
    /**
     * Gets the special recipe.
     *
     * @return the special recipe
     */
    public int getSpecialRecipe()
    {
        return this.specialRecipe;
    }
    
    /**
     * Gets the stacks.
     *
     * @return the stacks
     */
    public int getStacks()
    {
        return this.stacks;
    }
    
    /**
     * Gets the stats.
     *
     * @return the stats
     */
    public BasicDataStats getStats()
    {
        return this.stats;
    }
    
    /**
     * Gets the tags.
     *
     * @return the tags
     */
    public List<String> getTags()
    {
        return this.tags;
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
        
        BasicData basicData = (BasicData) o;
        
        if (consumeOnFull != basicData.consumeOnFull)
        {
            return false;
        }
        if (consumed != basicData.consumed)
        {
            return false;
        }
        if (depth != basicData.depth)
        {
            return false;
        }
        if (hideFromAll != basicData.hideFromAll)
        {
            return false;
        }
        if (id != basicData.id)
        {
            return false;
        }
        if (inStore != basicData.inStore)
        {
            return false;
        }
        if (specialRecipe != basicData.specialRecipe)
        {
            return false;
        }
        if (stacks != basicData.stacks)
        {
            return false;
        }
        if ((colloq != null) ? !colloq.equals(basicData.colloq) : (basicData.colloq != null))
        {
            return false;
        }
        if ((description != null) ? !description.equals(basicData.description) : (basicData.description != null))
        {
            return false;
        }
        if ((from != null) ? !from.equals(basicData.from) : (basicData.from != null))
        {
            return false;
        }
        if ((gold != null) ? !gold.equals(basicData.gold) : (basicData.gold != null))
        {
            return false;
        }
        if ((group != null) ? !group.equals(basicData.group) : (basicData.group != null))
        {
            return false;
        }
        if ((image != null) ? !image.equals(basicData.image) : (basicData.image != null))
        {
            return false;
        }
        if ((into != null) ? !into.equals(basicData.into) : (basicData.into != null))
        {
            return false;
        }
        if ((maps != null) ? !maps.equals(basicData.maps) : (basicData.maps != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(basicData.name) : (basicData.name != null))
        {
            return false;
        }
        if ((plaintext != null) ? !plaintext.equals(basicData.plaintext) : (basicData.plaintext != null))
        {
            return false;
        }
        if ((requiredChampion != null) ? !requiredChampion.equals(basicData.requiredChampion) : (basicData.requiredChampion != null))
        {
            return false;
        }
        if ((rune != null) ? !rune.equals(basicData.rune) : (basicData.rune != null))
        {
            return false;
        }
        if ((sanitizedDescription != null) ? !sanitizedDescription.equals(basicData.sanitizedDescription) : (basicData.sanitizedDescription != null))
        {
            return false;
        }
        if ((stats != null) ? !stats.equals(basicData.stats) : (basicData.stats != null))
        {
            return false;
        }
        return (tags != null) ? tags.equals(basicData.tags) : (basicData.tags == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = colloq != null ? colloq.hashCode() : 0;
        result = 31 * result + (consumeOnFull ? 1 : 0);
        result = 31 * result + (consumed ? 1 : 0);
        result = 31 * result + depth;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (gold != null ? gold.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (hideFromAll ? 1 : 0);
        result = 31 * result + id;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (inStore ? 1 : 0);
        result = 31 * result + (into != null ? into.hashCode() : 0);
        result = 31 * result + (maps != null ? maps.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (plaintext != null ? plaintext.hashCode() : 0);
        result = 31 * result + (requiredChampion != null ? requiredChampion.hashCode() : 0);
        result = 31 * result + (rune != null ? rune.hashCode() : 0);
        result = 31 * result + (sanitizedDescription != null ? sanitizedDescription.hashCode() : 0);
        result = 31 * result + specialRecipe;
        result = 31 * result + stacks;
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "BasicData{" +
               "colloq='" + colloq + '\'' +
               ", consumeOnFull=" + consumeOnFull +
               ", consumed=" + consumed +
               ", depth=" + depth +
               ", description='" + description + '\'' +
               ", from=" + from +
               ", gold=" + gold +
               ", group='" + group + '\'' +
               ", hideFromAll=" + hideFromAll +
               ", id=" + id +
               ", image=" + image +
               ", inStore=" + inStore +
               ", into=" + into +
               ", maps=" + maps +
               ", name='" + name + '\'' +
               ", plaintext='" + plaintext + '\'' +
               ", requiredChampion='" + requiredChampion + '\'' +
               ", rune=" + rune +
               ", sanitizedDescription='" + sanitizedDescription + '\'' +
               ", specialRecipe=" + specialRecipe +
               ", stacks=" + stacks +
               ", stats=" + stats +
               ", tags=" + tags +
               '}';
    }
}
