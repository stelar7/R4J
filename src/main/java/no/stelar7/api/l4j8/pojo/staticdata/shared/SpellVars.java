package no.stelar7.api.l4j8.pojo.staticdata.shared;

import java.io.Serializable;
import java.util.*;

public class SpellVars implements Serializable
{
    private static final long serialVersionUID = -8372973077273055306L;
    
    private Object coeff;
    private String dyn;
    private String key;
    private String link;
    private String ranksWith;
    
    
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
        final SpellVars other = (SpellVars) obj;
        if (this.coeff == null)
        {
            if (other.coeff != null)
            {
                return false;
            }
        } else if (!this.coeff.equals(other.coeff))
        {
            return false;
        }
        if (this.dyn == null)
        {
            if (other.dyn != null)
            {
                return false;
            }
        } else if (!this.dyn.equals(other.dyn))
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
        if (this.link == null)
        {
            if (other.link != null)
            {
                return false;
            }
        } else if (!this.link.equals(other.link))
        {
            return false;
        }
        if (this.ranksWith == null)
        {
            return other.ranksWith == null;
        } else
        {
            return this.ranksWith.equals(other.ranksWith);
        }
    }
    
    /**
     * Gets the coeff.
     *
     * @return the coeff
     */
    public List<Double> getCoeff()
    {
        return this.coeff instanceof List ? (List<Double>) this.coeff : Collections.singletonList((Double) coeff);
    }
    
    /**
     * Gets the dyn.
     *
     * @return the dyn
     */
    public String getDyn()
    {
        return this.dyn;
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
     * Gets the link.
     *
     * @return the link
     */
    public String getLink()
    {
        return this.link;
    }
    
    /**
     * Gets the ranks with.
     *
     * @return the ranks with
     */
    public String getRanksWith()
    {
        return this.ranksWith;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.coeff == null) ? 0 : this.coeff.hashCode());
        result = (prime * result) + ((this.dyn == null) ? 0 : this.dyn.hashCode());
        result = (prime * result) + ((this.key == null) ? 0 : this.key.hashCode());
        result = (prime * result) + ((this.link == null) ? 0 : this.link.hashCode());
        result = (prime * result) + ((this.ranksWith == null) ? 0 : this.ranksWith.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "SpellVars{" +
               "coeff=" + coeff +
               ", dyn='" + dyn + '\'' +
               ", key='" + key + '\'' +
               ", link='" + link + '\'' +
               ", ranksWith='" + ranksWith + '\'' +
               '}';
    }
}
