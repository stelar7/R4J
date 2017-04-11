package no.stelar7.api.l4j8.pojo.staticdata.realm;

import java.util.*;

public class Realm
{
    private String              cdn;
    private String              css;
    private String              dd;
    private String              l;
    private String              lg;
    private Map<String, String> n;
    private Integer             profileiconmax;
    private String              store;
    private String              v;
    
    
    
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
        final Realm other = (Realm) obj;
        if (this.cdn == null)
        {
            if (other.cdn != null)
            {
                return false;
            }
        } else if (!this.cdn.equals(other.cdn))
        {
            return false;
        }
        if (this.css == null)
        {
            if (other.css != null)
            {
                return false;
            }
        } else if (!this.css.equals(other.css))
        {
            return false;
        }
        if (this.dd == null)
        {
            if (other.dd != null)
            {
                return false;
            }
        } else if (!this.dd.equals(other.dd))
        {
            return false;
        }
        if (this.l == null)
        {
            if (other.l != null)
            {
                return false;
            }
        } else if (!this.l.equals(other.l))
        {
            return false;
        }
        if (this.lg == null)
        {
            if (other.lg != null)
            {
                return false;
            }
        } else if (!this.lg.equals(other.lg))
        {
            return false;
        }
        if (this.n == null)
        {
            if (other.n != null)
            {
                return false;
            }
        } else if (!this.n.equals(other.n))
        {
            return false;
        }
        if (this.profileiconmax == null)
        {
            if (other.profileiconmax != null)
            {
                return false;
            }
        } else if (!this.profileiconmax.equals(other.profileiconmax))
        {
            return false;
        }
        if (this.store == null)
        {
            if (other.store != null)
            {
                return false;
            }
        } else if (!this.store.equals(other.store))
        {
            return false;
        }
        if (this.v == null)
        {
            if (other.v != null)
            {
                return false;
            }
        } else if (!this.v.equals(other.v))
        {
            return false;
        }
        return true;
    }
    
    /**
     * The base CDN url.
     *
     * @return the cdn
     */
    public String getCdn()
    {
        return this.cdn;
    }
    
    /**
     * Latest changed version of Dragon Magic's css file.
     *
     * @return the css
     */
    public String getCss()
    {
        return this.css;
    }
    
    /**
     * Latest changed version of Dragon Magic.
     *
     * @return the dd
     */
    public String getDd()
    {
        return this.dd;
    }
    
    /**
     * Default language for this realm.
     *
     * @return the l
     */
    public String getL()
    {
        return this.l;
    }
    
    /**
     * Legacy script mode for IE6 or older.
     *
     * @return the lg
     */
    public String getLg()
    {
        return this.lg;
    }
    
    /**
     * Latest changed version for each data type listed.
     *
     * @return the n
     */
    public Map<String, String> getN()
    {
        return this.n;
    }
    
    /**
     * Special behavior number identifying the largest profileicon id that can be used under 500. Any profileicon that is requested between this number and 500 should be mapped to 0.
     *
     * @return the profileiconmax
     */
    public Integer getProfileiconmax()
    {
        return this.profileiconmax;
    }
    
    /**
     * Additional api data drawn from other sources that may be related to data dragon functionality.
     *
     * @return the store
     */
    public String getStore()
    {
        return this.store;
    }
    
    /**
     * Current version of this file for this realm.
     *
     * @return the v
     */
    public String getV()
    {
        return this.v;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.cdn == null) ? 0 : this.cdn.hashCode());
        result = (prime * result) + ((this.css == null) ? 0 : this.css.hashCode());
        result = (prime * result) + ((this.dd == null) ? 0 : this.dd.hashCode());
        result = (prime * result) + ((this.l == null) ? 0 : this.l.hashCode());
        result = (prime * result) + ((this.lg == null) ? 0 : this.lg.hashCode());
        result = (prime * result) + ((this.n == null) ? 0 : this.n.hashCode());
        result = (prime * result) + ((this.profileiconmax == null) ? 0 : this.profileiconmax.hashCode());
        result = (prime * result) + ((this.store == null) ? 0 : this.store.hashCode());
        result = (prime * result) + ((this.v == null) ? 0 : this.v.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Realm{" +
               "cdn='" + cdn + '\'' +
               ", css='" + css + '\'' +
               ", dd='" + dd + '\'' +
               ", l='" + l + '\'' +
               ", lg='" + lg + '\'' +
               ", n=" + n +
               ", profileiconmax=" + profileiconmax +
               ", store='" + store + '\'' +
               ", v='" + v + '\'' +
               '}';
    }
}
