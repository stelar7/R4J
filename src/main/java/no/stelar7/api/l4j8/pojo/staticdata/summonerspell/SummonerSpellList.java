package no.stelar7.api.l4j8.pojo.staticdata.summonerspell;

import java.util.Map;

public class SummonerSpellList
{
    Map<String, SummonerSpell> data;
    String                     type;
    String                     version;

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, SummonerSpell> getData()
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
