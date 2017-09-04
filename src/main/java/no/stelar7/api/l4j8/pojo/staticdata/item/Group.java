package no.stelar7.api.l4j8.pojo.staticdata.item;

import java.io.Serializable;

public class Group implements Serializable
{
    private static final long serialVersionUID = 231461349259911869L;
    
    private String MaxGroupOwnable;
    private String key;
    
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
        final Group other = (Group) obj;
        if (this.MaxGroupOwnable == null)
        {
            if (other.MaxGroupOwnable != null)
            {
                return false;
            }
        } else if (!this.MaxGroupOwnable.equals(other.MaxGroupOwnable))
        {
            return false;
        }
        if (this.key == null)
        {
            if (other.key != null)
            {
                return false;
            }
        } else if (!this.key.equals(other.key))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey()
    {
        return this.key;
    }
    
    /**
     * Gets the max group ownable.
     *
     * @return the max group ownable
     */
    public String getMaxGroupOwnable()
    {
        return this.MaxGroupOwnable;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.MaxGroupOwnable == null) ? 0 : this.MaxGroupOwnable.hashCode());
        result = (prime * result) + ((this.key == null) ? 0 : this.key.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Group{" +
               "MaxGroupOwnable='" + MaxGroupOwnable + '\'' +
               ", key='" + key + '\'' +
               '}';
    }
}
