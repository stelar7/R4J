package no.stelar7.api.l4j8.pojo.staticdata.rune;

import java.util.Map;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.pojo.staticdata.shared.BasicData;

public class RuneList implements APIObject
{
    BasicData         basic;
    Map<String, Rune> data;
    String            type;
    String            version;

    /**
     * Gets the basic.
     *
     * @return the basic
     */
    public BasicData getBasic()
    {
        return basic;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, Rune> getData()
    {
        return data;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion()
    {
        return version;
    }
}
