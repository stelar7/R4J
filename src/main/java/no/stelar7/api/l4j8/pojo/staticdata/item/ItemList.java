package no.stelar7.api.l4j8.pojo.staticdata.item;

import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.util.*;

public class ItemList
{
    
    private BasicData         basic;
    private Map<String, Item> data;
    private List<Group>       groups;
    private List<ItemTree>    tree;
    
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
        if (this.basic == null)
        {
            if (other.basic != null)
            {
                return false;
            }
        } else if (!this.basic.equals(other.basic))
        {
            return false;
        }
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
            if (other.tree != null)
            {
                return false;
            }
        } else if (!this.tree.equals(other.tree))
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Gets the basic.
     *
     * @return the basic
     */
    public BasicData getBasic()
    {
        return this.basic;
    }
    
    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, Item> getData()
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
        result = (prime * result) + ((this.basic == null) ? 0 : this.basic.hashCode());
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
               "basic=" + basic +
               ", data=" + data +
               ", groups=" + groups +
               ", tree=" + tree +
               '}';
    }
}
