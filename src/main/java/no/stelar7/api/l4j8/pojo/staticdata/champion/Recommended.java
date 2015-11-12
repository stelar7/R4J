package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.List;

public class Recommended
{
    List<Block> blocks;
    String      champion;
    String      map;
    String      mode;
    Boolean     priority;
    String      title;
    String      type;

    /**
     * Gets the blocks.
     *
     * @return the blocks
     */
    public List<Block> getBlocks()
    {
        return blocks;
    }

    /**
     * Gets the champion.
     *
     * @return the champion
     */
    public String getChampion()
    {
        return champion;
    }

    /**
     * Gets the map.
     *
     * @return the map
     */
    public String getMap()
    {
        return map;
    }

    /**
     * Gets the mode.
     *
     * @return the mode
     */
    public String getMode()
    {
        return mode;
    }

    /**
     * Gets the priority.
     *
     * @return the priority
     */
    public Boolean getPriority()
    {
        return priority;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType()
    {
        return type;
    }
}
