package no.stelar7.api.r4j.pojo.lol.staticdata.map;

import no.stelar7.api.r4j.basic.constants.types.MapType;
import no.stelar7.api.r4j.pojo.lol.staticdata.shared.Image;

import java.io.Serializable;
import java.util.*;

public class MapDetails implements Serializable
{
    private static final long serialVersionUID = -3919670965437484875L;
    
    private Image   image;
    private MapType mapId;
    private String     mapName;
    private List<Long> unpurchasableItemList;
    
    
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
     * Gets the map id.
     *
     * @return the map id
     */
    public MapType getMapId()
    {
        return this.mapId;
    }
    
    /**
     * Gets the map name.
     *
     * @return the map name
     */
    public String getMapName()
    {
        return this.mapName;
    }
    
    /**
     * Gets the unpurchasable item list.
     *
     * @return the unpurchasable item list
     */
    public List<Long> getUnpurchasableItemList()
    {
        return (this.unpurchasableItemList == null) ? Collections.emptyList() : this.unpurchasableItemList;
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
        MapDetails that = (MapDetails) o;
        return Objects.equals(image, that.image) &&
               mapId == that.mapId &&
               Objects.equals(mapName, that.mapName) &&
               Objects.equals(unpurchasableItemList, that.unpurchasableItemList);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(image, mapId, mapName, unpurchasableItemList);
    }
    
    @Override
    public String toString()
    {
        return "MapDetails{" +
               "image=" + image +
               ", mapId=" + mapId +
               ", mapName='" + mapName + '\'' +
               ", unpurchasableItemList=" + unpurchasableItemList +
               '}';
    }
}
