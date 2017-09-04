package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.io.Serializable;

public class BlockItem implements Serializable
{
    
    private static final long serialVersionUID = -7656361974613593574L;
    
    private int count;
    private int id;
    
    
    /**
     * Gets the count.
     *
     * @return the count
     */
    public int getCount()
    {
        return this.count;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId()
    {
        return this.id;
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
        
        BlockItem blockItem = (BlockItem) o;
        
        if (count != blockItem.count)
        {
            return false;
        }
        return id == blockItem.id;
    }
    
    @Override
    public int hashCode()
    {
        int result = count;
        result = 31 * result + id;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "BlockItem{" +
               "count=" + count +
               ", id=" + id +
               '}';
    }
}
