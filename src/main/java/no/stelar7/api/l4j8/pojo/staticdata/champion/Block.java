package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.List;

public class Block
{
    List<BlockItem> items;
    Boolean         recMath;
    String          type;

    /**
     * Gets the items.
     *
     * @return the items
     */
    public List<BlockItem> getItems()
    {
        return items;
    }

    /**
     * Gets the rec math.
     *
     * @return the rec math
     */
    public Boolean getRecMath()
    {
        return recMath;
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