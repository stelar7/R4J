package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.Map;

public class ChampionList
{
    Map<String, Champion> data;
    String                format;
    Map<String, String>   keys;
    String                type;
    String                version;

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, Champion> getData()
    {
        return data;
    }

    /**
     * Gets the format.
     *
     * @return the format
     */
    public String getFormat()
    {
        return format;
    }

    /**
     * Gets the keys.
     *
     * @return the keys
     */
    public Map<String, String> getKeys()
    {
        return keys;
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
