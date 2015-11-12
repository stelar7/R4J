package no.stelar7.api.l4j8.pojo.staticdata.map;

import java.util.List;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

public class MapDetails
{
    Image      image;
    Long       mapId;
    String     mapName;
    List<Long> unpurchasableItemList;

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
     * Gets the map id.
     *
     * @return the map id
     */
    public Long getMapId()
    {
        return mapId;
    }

    /**
     * Gets the map name.
     *
     * @return the map name
     */
    public String getMapName()
    {
        return mapName;
    }

    /**
     * Gets the unpurchasable item list.
     *
     * @return the unpurchasable item list
     */
    public List<Long> getUnpurchasableItemList()
    {
        return unpurchasableItemList;
    }
}
