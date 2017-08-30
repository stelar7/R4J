package no.stelar7.api.l4j8.pojo.staticdata.realm;

import java.io.Serializable;
import java.util.Map;

public class Realm  implements Serializable
{
    private String              cdn;
    private String              css;
    private String              dd;
    private String              l;
    private String              lg;
    private Map<String, String> n;
    private int                 profileiconmax;
    private String              store;
    private String              v;
    
    
    /**
     * The base CDN url.
     *
     * @return the cdn
     */
    public String getCDN()
    {
        return this.cdn;
    }
    
    /**
     * Latest changed version of Dragon Magic's css file.
     *
     * @return the css
     */
    public String getCSS()
    {
        return this.css;
    }
    
    /**
     * Latest changed version of Dragon Magic.
     *
     * @return the dd
     */
    public String getDD()
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
    public String getLG()
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
    public int getProfileIconMax()
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
        
        Realm realm = (Realm) o;
        
        if (profileiconmax != realm.profileiconmax)
        {
            return false;
        }
        if ((cdn != null) ? !cdn.equals(realm.cdn) : (realm.cdn != null))
        {
            return false;
        }
        if ((css != null) ? !css.equals(realm.css) : (realm.css != null))
        {
            return false;
        }
        if ((dd != null) ? !dd.equals(realm.dd) : (realm.dd != null))
        {
            return false;
        }
        if ((l != null) ? !l.equals(realm.l) : (realm.l != null))
        {
            return false;
        }
        if ((lg != null) ? !lg.equals(realm.lg) : (realm.lg != null))
        {
            return false;
        }
        if ((n != null) ? !n.equals(realm.n) : (realm.n != null))
        {
            return false;
        }
        if ((store != null) ? !store.equals(realm.store) : (realm.store != null))
        {
            return false;
        }
        return (v != null) ? v.equals(realm.v) : (realm.v == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = cdn != null ? cdn.hashCode() : 0;
        result = 31 * result + (css != null ? css.hashCode() : 0);
        result = 31 * result + (dd != null ? dd.hashCode() : 0);
        result = 31 * result + (l != null ? l.hashCode() : 0);
        result = 31 * result + (lg != null ? lg.hashCode() : 0);
        result = 31 * result + (n != null ? n.hashCode() : 0);
        result = 31 * result + profileiconmax;
        result = 31 * result + (store != null ? store.hashCode() : 0);
        result = 31 * result + (v != null ? v.hashCode() : 0);
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
