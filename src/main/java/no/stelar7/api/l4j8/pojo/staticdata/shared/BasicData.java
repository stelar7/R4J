package no.stelar7.api.l4j8.pojo.staticdata.shared;

import java.util.List;
import java.util.Map;

public class BasicData
{
    String               colloq;
    Boolean              consumeOnFull;
    Boolean              consumed;
    Integer              depth;
    String               description;
    List<String>         from;
    Gold                 gold;
    String               group;
    Boolean              hideFromAll;
    Integer              id;
    Image                image;
    Boolean              inStore;
    List<String>         into;
    Map<String, Boolean> maps;
    String               name;
    String               plaintext;
    String               requiredChampion;
    MetaData             rune;
    String               sanitizedDescription;
    Integer              specialRecipe;
    Integer              stacks;
    BasicDataStats       stats;
    List<String>         tags;

    /**
     * Gets the colloq.
     *
     * @return the colloq
     */
    public String getColloq()
    {
        return colloq;
    }

    /**
     * Gets the consume on full.
     *
     * @return the consume on full
     */
    public Boolean getConsumeOnFull()
    {
        return consumeOnFull;
    }

    /**
     * Gets the consumed.
     *
     * @return the consumed
     */
    public Boolean getConsumed()
    {
        return consumed;
    }

    /**
     * Gets the depth.
     *
     * @return the depth
     */
    public Integer getDepth()
    {
        return depth;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public List<String> getFrom()
    {
        return from;
    }

    /**
     * Gets the gold.
     *
     * @return the gold
     */
    public Gold getGold()
    {
        return gold;
    }

    /**
     * Gets the group.
     *
     * @return the group
     */
    public String getGroup()
    {
        return group;
    }

    /**
     * Gets the hide from all.
     *
     * @return the hide from all
     */
    public Boolean getHideFromAll()
    {
        return hideFromAll;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Gets the in store.
     *
     * @return the in store
     */
    public Boolean getInStore()
    {
        return inStore;
    }

    /**
     * Gets the into.
     *
     * @return the into
     */
    public List<String> getInto()
    {
        return into;
    }

    /**
     * Gets the maps.
     *
     * @return the maps
     */
    public Map<String, Boolean> getMaps()
    {
        return maps;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the plaintext.
     *
     * @return the plaintext
     */
    public String getPlaintext()
    {
        return plaintext;
    }

    /**
     * Gets the required champion.
     *
     * @return the required champion
     */
    public String getRequiredChampion()
    {
        return requiredChampion;
    }

    /**
     * Gets the rune.
     *
     * @return the rune
     */
    public MetaData getRune()
    {
        return rune;
    }

    /**
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public String getSanitizedDescription()
    {
        return sanitizedDescription;
    }

    /**
     * Gets the special recipe.
     *
     * @return the special recipe
     */
    public Integer getSpecialRecipe()
    {
        return specialRecipe;
    }

    /**
     * Gets the stacks.
     *
     * @return the stacks
     */
    public Integer getStacks()
    {
        return stacks;
    }

    /**
     * Gets the stats.
     *
     * @return the stats
     */
    public BasicDataStats getStats()
    {
        return stats;
    }

    /**
     * Gets the tags.
     *
     * @return the tags
     */
    public List<String> getTags()
    {
        return tags;
    }
}
