package no.stelar7.api.l4j8.pojo.staticdata.realm;

import java.util.Map;

import no.stelar7.api.l4j8.basic.APIObject;

public class Realm implements APIObject
{
    String              cdn;
    String              css;
    String              dd;
    String              l;
    String              lg;
    Map<String, String> n;
    Integer             profileiconmax;
    String              store;
    String              v;

    /**
     * The base CDN url.
     *
     * @return the cdn
     */
    public String getCdn()
    {
        return cdn;
    }

    /**
     * Latest changed version of Dragon Magic's css file.
     *
     * @return the css
     */
    public String getCss()
    {
        return css;
    }

    /**
     * Latest changed version of Dragon Magic.
     *
     * @return the dd
     */
    public String getDd()
    {
        return dd;
    }

    /**
     * Default language for this realm.
     *
     * @return the l
     */
    public String getL()
    {
        return l;
    }

    /**
     * Legacy script mode for IE6 or older.
     *
     * @return the lg
     */
    public String getLg()
    {
        return lg;
    }

    /**
     * Latest changed version for each data type listed.
     *
     * @return the n
     */
    public Map<String, String> getN()
    {
        return n;
    }

    /**
     * Special behavior number identifying the largest profileicon id that can be used under 500. Any profileicon that is requested between this number and 500 should be mapped to 0.
     *
     * @return the profileiconmax
     */
    public Integer getProfileiconmax()
    {
        return profileiconmax;
    }

    /**
     * Additional api data drawn from other sources that may be related to data dragon functionality.
     *
     * @return the store
     */
    public String getStore()
    {
        return store;
    }

    /**
     * Current version of this file for this realm.
     *
     * @return the v
     */
    public String getV()
    {
        return v;
    }
}
