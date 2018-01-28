package no.stelar7.api.l4j8.pojo.staticdata.item;

import java.io.Serializable;
import java.util.*;

public class ItemList implements Serializable
{
    private static final long serialVersionUID = 6556282881416448579L;
    
    private Map<Integer, Item> data;
    private List<Group>        groups;
    private List<ItemTree>     tree;
    
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
        final ItemList other = (ItemList) obj;
        if (this.data == null)
        {
            if (other.data != null)
            {
                return false;
            }
        } else if (!this.data.equals(other.data))
        {
            return false;
        }
        if (this.groups == null)
        {
            if (other.groups != null)
            {
                return false;
            }
        } else if (!this.groups.equals(other.groups))
        {
            return false;
        }
        if (this.tree == null)
        {
            return other.tree == null;
        } else
        {
            return this.tree.equals(other.tree);
        }
    
    }
    
    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<Integer, Item> getData()
    {
        return this.data;
    }
    
    /**
     * Gets the groups.
     *
     * @return the groups
     */
    public List<Group> getGroups()
    {
        return this.groups;
    }
    
    /**
     * Gets the tree.
     *
     * @return the tree
     */
    public List<ItemTree> getTree()
    {
        return this.tree;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.data == null) ? 0 : this.data.hashCode());
        result = (prime * result) + ((this.groups == null) ? 0 : this.groups.hashCode());
        result = (prime * result) + ((this.tree == null) ? 0 : this.tree.hashCode());
        result = (prime * result) + super.hashCode();
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ItemList{" +
               "data=" + data +
               ", groups=" + groups +
               ", tree=" + tree +
               '}';
    }
}
