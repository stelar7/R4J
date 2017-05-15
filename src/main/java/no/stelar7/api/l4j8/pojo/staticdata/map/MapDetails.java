package no.stelar7.api.l4j8.pojo.staticdata.map;

import no.stelar7.api.l4j8.basic.constants.types.MapType;
import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

import java.util.*;

public class MapDetails
{
    private Image      image;
    private MapType    mapId;
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
        
        if ((image != null) ? !image.equals(that.image) : (that.image != null))
        {
            return false;
        }
        if (mapId != that.mapId)
        {
            return false;
        }
        if ((mapName != null) ? !mapName.equals(that.mapName) : (that.mapName != null))
        {
            return false;
        }
        return (unpurchasableItemList != null) ? unpurchasableItemList.equals(that.unpurchasableItemList) : (that.unpurchasableItemList == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (mapId != null ? mapId.hashCode() : 0);
        result = 31 * result + (mapName != null ? mapName.hashCode() : 0);
        result = 31 * result + (unpurchasableItemList != null ? unpurchasableItemList.hashCode() : 0);
        return result;
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
