package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.io.Serializable;
import java.util.*;

public class StaticMasteryList implements Serializable
{
    private static final long serialVersionUID = 214283211901387914L;
    
    private Map<Integer, StaticMastery>        data;
    private Map<String, List<MasteryTreeList>> tree;
    
    
    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<Integer, StaticMastery> getData()
    {
        return this.data;
    }
    
    /**
     * Gets the tree.
     *
     * @return the tree
     */
    public Map<String, List<MasteryTreeList>> getTree()
    {
        return this.tree;
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
        
        StaticMasteryList that = (StaticMasteryList) o;
        
        if ((data != null) ? !data.equals(that.data) : (that.data != null))
        {
            return false;
        }
        return (tree != null) ? tree.equals(that.tree) : (that.tree == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (tree != null ? tree.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "StaticMasteryList{" +
               "data=" + data +
               ", tree=" + tree +
               '}';
    }
}
