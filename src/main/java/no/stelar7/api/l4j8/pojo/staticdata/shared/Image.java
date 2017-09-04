package no.stelar7.api.l4j8.pojo.staticdata.shared;

import java.io.Serializable;

public class Image implements Serializable
{
    private static final long serialVersionUID = 4836185346156791918L;
    
    private String full;
    private String group;
    private String sprite;
    private int    h;
    private int    w;
    private int    x;
    private int    y;
    
    
    /**
     * Gets the full.
     *
     * @return the full
     */
    public String getFull()
    {
        return this.full;
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
     * Gets the h.
     *
     * @return the h
     */
    public int getH()
    {
        return this.h;
    }
    
    /**
     * Gets the sprite.
     *
     * @return the sprite
     */
    public String getSprite()
    {
        return this.sprite;
    }
    
    /**
     * Gets the w.
     *
     * @return the w
     */
    public int getW()
    {
        return this.w;
    }
    
    /**
     * Gets the x.
     *
     * @return the x
     */
    public int getX()
    {
        return this.x;
    }
    
    /**
     * Gets the y.
     *
     * @return the y
     */
    public int getY()
    {
        return this.y;
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
        
        Image image = (Image) o;
        
        if (h != image.h)
        {
            return false;
        }
        if (w != image.w)
        {
            return false;
        }
        if (x != image.x)
        {
            return false;
        }
        if (y != image.y)
        {
            return false;
        }
        if ((full != null) ? !full.equals(image.full) : (image.full != null))
        {
            return false;
        }
        if ((group != null) ? !group.equals(image.group) : (image.group != null))
        {
            return false;
        }
        return (sprite != null) ? sprite.equals(image.sprite) : (image.sprite == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = full != null ? full.hashCode() : 0;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (sprite != null ? sprite.hashCode() : 0);
        result = 31 * result + h;
        result = 31 * result + w;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Image{" +
               "full='" + full + '\'' +
               ", group='" + group + '\'' +
               ", h=" + h +
               ", sprite='" + sprite + '\'' +
               ", w=" + w +
               ", x=" + x +
               ", y=" + y +
               '}';
    }
}
