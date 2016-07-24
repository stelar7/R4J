package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.util.*;

public class MasteryList
{
    Map<String, Mastery> data;
    MasteryTree          tree;

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
        final MasteryList other = (MasteryList) obj;
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
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, Mastery> getData()
    {
        return this.data;
    }

    /**
     * Gets the tree.
     *
     * @return the tree
     */
    public MasteryTree getTree()
    {
        return this.tree;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.data == null) ? 0 : this.data.hashCode());
        result = (prime * result) + ((this.tree == null) ? 0 : this.tree.hashCode());
        result = (prime * result) + super.hashCode();
        return result;
    }

}
