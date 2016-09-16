package no.stelar7.api.l4j8.pojo.staticdata.map;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

import java.util.List;

public class MapDetails
{
    Image      image;
    Long       mapId;
    String     mapName;
    List<Long> unpurchasableItemList;

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final MapDetails other = (MapDetails) obj;
        if (this.image == null)
        {
            if (other.image != null)
            {
                return false;
            }
        } else if (!this.image.equals(other.image))
        {
            return false;
        }
        if (this.mapId == null)
        {
            if (other.mapId != null)
            {
                return false;
            }
        } else if (!this.mapId.equals(other.mapId))
        {
            return false;
        }
        if (this.mapName == null)
        {
            if (other.mapName != null)
            {
                return false;
            }
        } else if (!this.mapName.equals(other.mapName))
        {
            return false;
        }
        if (this.unpurchasableItemList == null)
        {
            if (other.unpurchasableItemList != null)
            {
                return false;
            }
        } else if (!this.unpurchasableItemList.equals(other.unpurchasableItemList))
        {
            return false;
        }
        return true;
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
     * Gets the map id.
     *
     * @return the map id
     */
    public Long getMapId()
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
        return this.unpurchasableItemList;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.image == null) ? 0 : this.image.hashCode());
        result = (prime * result) + ((this.mapId == null) ? 0 : this.mapId.hashCode());
        result = (prime * result) + ((this.mapName == null) ? 0 : this.mapName.hashCode());
        result = (prime * result) + ((this.unpurchasableItemList == null) ? 0 : this.unpurchasableItemList.hashCode());
        return result;
    }
}
