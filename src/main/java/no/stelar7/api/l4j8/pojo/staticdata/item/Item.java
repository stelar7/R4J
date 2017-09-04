package no.stelar7.api.l4j8.pojo.staticdata.item;

import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.io.Serializable;
import java.util.*;

public class Item implements Serializable
{
    private static final long serialVersionUID = 7919515074046086833L;
    
    private Map<String, String>  effect;
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
    private InventoryDataStats   stats;
    private List<String>         tags;
    
    /**
     * Gets effect.
     *
     * @return the effect
     */
    public Map<String, String> getEffect()
    {
        return effect;
    }
    
    /**
     * Gets colloq.
     *
     * @return the colloq
     */
    public String getColloq()
    {
        return colloq;
    }
    
    /**
     * Is consume on full boolean.
     *
     * @return the boolean
     */
    public boolean isConsumeOnFull()
    {
        return consumeOnFull;
    }
    
    /**
     * Is consumed boolean.
     *
     * @return the boolean
     */
    public boolean isConsumed()
    {
        return consumed;
    }
    
    /**
     * Gets depth.
     *
     * @return the depth
     */
    public int getDepth()
    {
        return depth;
    }
    
    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Gets from.
     *
     * @return the from
     */
    public List<String> getFrom()
    {
        return from;
    }
    
    /**
     * Gets gold.
     *
     * @return the gold
     */
    public Gold getGold()
    {
        return gold;
    }
    
    /**
     * Gets group.
     *
     * @return the group
     */
    public String getGroup()
    {
        return group;
    }
    
    /**
     * Is hide from all boolean.
     *
     * @return the boolean
     */
    public boolean isHideFromAll()
    {
        return hideFromAll;
    }
    
    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }
    
    /**
     * Is in store boolean.
     *
     * @return the boolean
     */
    public boolean isInStore()
    {
        return inStore;
    }
    
    /**
     * Gets into.
     *
     * @return the into
     */
    public List<String> getInto()
    {
        return into;
    }
    
    /**
     * Gets maps.
     *
     * @return the maps
     */
    public Map<String, Boolean> getMaps()
    {
        return maps;
    }
    
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets plaintext.
     *
     * @return the plaintext
     */
    public String getPlaintext()
    {
        return plaintext;
    }
    
    /**
     * Gets required champion.
     *
     * @return the required champion
     */
    public String getRequiredChampion()
    {
        return requiredChampion;
    }
    
    /**
     * Gets rune.
     *
     * @return the rune
     */
    public MetaData getRune()
    {
        return rune;
    }
    
    /**
     * Gets sanitized description.
     *
     * @return the sanitized description
     */
    public String getSanitizedDescription()
    {
        return sanitizedDescription;
    }
    
    /**
     * Gets special recipe.
     *
     * @return the special recipe
     */
    public int getSpecialRecipe()
    {
        return specialRecipe;
    }
    
    /**
     * Gets stacks.
     *
     * @return the stacks
     */
    public int getStacks()
    {
        return stacks;
    }
    
    /**
     * Gets stats.
     *
     * @return the stats
     */
    public InventoryDataStats getStats()
    {
        return stats;
    }
    
    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<String> getTags()
    {
        return tags;
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
        
        Item item = (Item) o;
        
        if (consumeOnFull != item.consumeOnFull)
        {
            return false;
        }
        if (consumed != item.consumed)
        {
            return false;
        }
        if (depth != item.depth)
        {
            return false;
        }
        if (hideFromAll != item.hideFromAll)
        {
            return false;
        }
        if (id != item.id)
        {
            return false;
        }
        if (inStore != item.inStore)
        {
            return false;
        }
        if (specialRecipe != item.specialRecipe)
        {
            return false;
        }
        if (stacks != item.stacks)
        {
            return false;
        }
        if ((effect != null) ? !effect.equals(item.effect) : (item.effect != null))
        {
            return false;
        }
        if ((colloq != null) ? !colloq.equals(item.colloq) : (item.colloq != null))
        {
            return false;
        }
        if ((description != null) ? !description.equals(item.description) : (item.description != null))
        {
            return false;
        }
        if ((from != null) ? !from.equals(item.from) : (item.from != null))
        {
            return false;
        }
        if ((gold != null) ? !gold.equals(item.gold) : (item.gold != null))
        {
            return false;
        }
        if ((group != null) ? !group.equals(item.group) : (item.group != null))
        {
            return false;
        }
        if ((image != null) ? !image.equals(item.image) : (item.image != null))
        {
            return false;
        }
        if ((into != null) ? !into.equals(item.into) : (item.into != null))
        {
            return false;
        }
        if ((maps != null) ? !maps.equals(item.maps) : (item.maps != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(item.name) : (item.name != null))
        {
            return false;
        }
        if ((plaintext != null) ? !plaintext.equals(item.plaintext) : (item.plaintext != null))
        {
            return false;
        }
        if ((requiredChampion != null) ? !requiredChampion.equals(item.requiredChampion) : (item.requiredChampion != null))
        {
            return false;
        }
        if ((rune != null) ? !rune.equals(item.rune) : (item.rune != null))
        {
            return false;
        }
        if ((sanitizedDescription != null) ? !sanitizedDescription.equals(item.sanitizedDescription) : (item.sanitizedDescription != null))
        {
            return false;
        }
        if ((stats != null) ? !stats.equals(item.stats) : (item.stats != null))
        {
            return false;
        }
        return (tags != null) ? tags.equals(item.tags) : (item.tags == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = effect != null ? effect.hashCode() : 0;
        result = 31 * result + (colloq != null ? colloq.hashCode() : 0);
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
        return "Item{" +
               "effect=" + effect +
               ", colloq='" + colloq + '\'' +
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
