package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.io.Serializable;
import java.util.List;

public class MasteryTreeList implements Serializable
{
    private static final long serialVersionUID = 2322598625564961550L;
    
    private List<MasteryTreeItem> masteryTreeItems;
    
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
        final MasteryTreeList other = (MasteryTreeList) obj;
        if (this.masteryTreeItems == null)
        {
            return other.masteryTreeItems == null;
        } else
        {
            return this.masteryTreeItems.equals(other.masteryTreeItems);
        }
    }
    
    
    /**
     * Gets the mastery tree items.
     *
     * @return the mastery tree items
     */
    public List<MasteryTreeItem> getMasteryTreeItems()
    {
        return this.masteryTreeItems;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.masteryTreeItems == null) ? 0 : this.masteryTreeItems.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MasteryTreeList{" +
               "masteryTreeItems=" + masteryTreeItems +
               '}';
    }
}
