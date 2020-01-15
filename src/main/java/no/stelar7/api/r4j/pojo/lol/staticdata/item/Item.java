package no.stelar7.api.r4j.pojo.lol.staticdata.item;

import no.stelar7.api.r4j.pojo.lol.staticdata.shared.*;

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
        return sanitize(this.description);
    }
    
    private String sanitize(String inData)
    {
        String outData = inData;
        outData = outData.replaceAll("<br>", "\n");
        outData = outData.replaceAll("<.+?>", "");
        return outData;
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
        return consumeOnFull == item.consumeOnFull &&
               consumed == item.consumed &&
               depth == item.depth &&
               hideFromAll == item.hideFromAll &&
               id == item.id &&
               inStore == item.inStore &&
               specialRecipe == item.specialRecipe &&
               stacks == item.stacks &&
               Objects.equals(effect, item.effect) &&
               Objects.equals(colloq, item.colloq) &&
               Objects.equals(description, item.description) &&
               Objects.equals(from, item.from) &&
               Objects.equals(gold, item.gold) &&
               Objects.equals(group, item.group) &&
               Objects.equals(image, item.image) &&
               Objects.equals(into, item.into) &&
               Objects.equals(maps, item.maps) &&
               Objects.equals(name, item.name) &&
               Objects.equals(plaintext, item.plaintext) &&
               Objects.equals(requiredChampion, item.requiredChampion) &&
               Objects.equals(rune, item.rune) &&
               Objects.equals(stats, item.stats) &&
               Objects.equals(tags, item.tags);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(effect, colloq, consumeOnFull, consumed, depth, description, from, gold, group, hideFromAll, id, image, inStore, into, maps, name, plaintext, requiredChampion, rune, specialRecipe, stacks, stats, tags);
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
               ", specialRecipe=" + specialRecipe +
               ", stacks=" + stacks +
               ", stats=" + stats +
               ", tags=" + tags +
               '}';
    }
}
