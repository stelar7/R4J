package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.util.Map;

import no.stelar7.api.l4j8.basic.APIObject;

public class MasteryList implements APIObject
{
    Map<String, Mastery> data;
    MasteryTree          tree;
    String               type;
    String               version;

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, Mastery> getData()
    {
        return data;
    }

    /**
     * Gets the tree.
     *
     * @return the tree
     */
    public MasteryTree getTree()
    {
        return tree;
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
