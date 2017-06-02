package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.*;

public class Recommended
{
    private List<Block> blocks;
    private String      champion;
    private String      map;
    private String      mode;
    private boolean     priority;
    private String      title;
    private String      type;
    
    /**
     * Gets the blocks.
     *
     * @return the blocks
     */
    public List<Block> getBlocks()
    {
        return this.blocks;
    }
    
    /**
     * Gets the champion.
     *
     * @return the champion
     */
    public String getChampion()
    {
        return this.champion;
    }
    
    /**
     * Gets the map.
     *
     * @return the map
     */
    public String getMap()
    {
        return this.map;
    }
    
    /**
     * Gets the mode.
     *
     * @return the mode
     */
    public String getMode()
    {
        return this.mode;
    }
    
    /**
     * Gets the priority.
     *
     * @return the priority
     */
    public boolean getPriority()
    {
        return this.priority;
    }
    
    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return this.title;
    }
    
    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType()
    {
        return this.type;
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
        
        Recommended that = (Recommended) o;
        
        if (priority != that.priority)
        {
            return false;
        }
        if ((blocks != null) ? !blocks.equals(that.blocks) : (that.blocks != null))
        {
            return false;
        }
        if ((champion != null) ? !champion.equals(that.champion) : (that.champion != null))
        {
            return false;
        }
        if ((map != null) ? !map.equals(that.map) : (that.map != null))
        {
            return false;
        }
        if ((mode != null) ? !mode.equals(that.mode) : (that.mode != null))
        {
            return false;
        }
        if ((title != null) ? !title.equals(that.title) : (that.title != null))
        {
            return false;
        }
        return (type != null) ? type.equals(that.type) : (that.type == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = blocks != null ? blocks.hashCode() : 0;
        result = 31 * result + (champion != null ? champion.hashCode() : 0);
        result = 31 * result + (map != null ? map.hashCode() : 0);
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        result = 31 * result + (priority ? 1 : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Recommended{" +
               "blocks=" + blocks +
               ", champion='" + champion + '\'' +
               ", map='" + map + '\'' +
               ", mode='" + mode + '\'' +
               ", priority=" + priority +
               ", title='" + title + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}
