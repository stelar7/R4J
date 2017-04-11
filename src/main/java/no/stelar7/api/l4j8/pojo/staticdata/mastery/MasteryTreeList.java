package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.util.*;

public class MasteryTreeList
{
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
            if (other.masteryTreeItems != null)
            {
                return false;
            }
        } else if (!this.masteryTreeItems.equals(other.masteryTreeItems))
        {
            return false;
        }
        return true;
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
