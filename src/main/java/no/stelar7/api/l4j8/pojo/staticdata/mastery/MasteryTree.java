package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.util.*;

public class MasteryTree
{
    private List<MasteryTreeList> Cunning;
    private List<MasteryTreeList> Ferocity;
    private List<MasteryTreeList> Resolve;
    
    
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
        final MasteryTree other = (MasteryTree) obj;
        if (this.Cunning == null)
        {
            if (other.Cunning != null)
            {
                return false;
            }
        } else if (!this.Cunning.equals(other.Cunning))
        {
            return false;
        }
        if (this.Ferocity == null)
        {
            if (other.Ferocity != null)
            {
                return false;
            }
        } else if (!this.Ferocity.equals(other.Ferocity))
        {
            return false;
        }
        if (this.Resolve == null)
        {
            if (other.Resolve != null)
            {
                return false;
            }
        } else if (!this.Resolve.equals(other.Resolve))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the defense.
     *
     * @return the defense
     */
    public List<MasteryTreeList> getCunning()
    {
        return this.Cunning;
    }
    
    /**
     * Gets the offense.
     *
     * @return the offense
     */
    public List<MasteryTreeList> getFerocity()
    {
        return this.Ferocity;
    }
    
    /**
     * Gets the utility.
     *
     * @return the utility
     */
    public List<MasteryTreeList> getResolve()
    {
        return this.Resolve;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.Cunning == null) ? 0 : this.Cunning.hashCode());
        result = (prime * result) + ((this.Ferocity == null) ? 0 : this.Ferocity.hashCode());
        result = (prime * result) + ((this.Resolve == null) ? 0 : this.Resolve.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MasteryTree{" +
               "Cunning=" + Cunning +
               ", Ferocity=" + Ferocity +
               ", Resolve=" + Resolve +
               '}';
    }
}
