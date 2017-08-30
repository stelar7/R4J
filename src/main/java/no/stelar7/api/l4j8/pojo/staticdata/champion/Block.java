package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.io.Serializable;
import java.util.List;

public class Block implements Serializable
{
    private List<BlockItem> items;
    private boolean         recMath;
    private String          type;
    
    
    /**
     * Gets the items.
     *
     * @return the items
     */
    public List<BlockItem> getItems()
    {
        return this.items;
    }
    
    /**
     * Gets the rec math.
     *
     * @return the rec math
     */
    public boolean getRecMath()
    {
        return this.recMath;
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
        
        Block block = (Block) o;
        
        if (recMath != block.recMath)
        {
            return false;
        }
        if ((items != null) ? !items.equals(block.items) : (block.items != null))
        {
            return false;
        }
        return (type != null) ? type.equals(block.type) : (block.type == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = items != null ? items.hashCode() : 0;
        result = 31 * result + (recMath ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Block{" +
               "items=" + items +
               ", recMath=" + recMath +
               ", type='" + type + '\'' +
               '}';
    }
}